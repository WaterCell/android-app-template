package jp.water_cell.android.template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tv_display)
    TextView tvDisplay;

    @Bind(R.id.iv_picasso)
    ImageView ivPicasso;

    private PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // アプリアイコンを左上に表示するのは API 21 から非推奨。どうしてもやりたければロゴとして出す
            // toolbar.setLogo(R.drawable.ic_launcher);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_butter_knife)
    void onClickBtnButterKnife() {
        tvDisplay.setText(tvDisplay.getText() + "Hello Butter Knife!\n");
    }

    @OnClick(R.id.btn_jpp)
    void onClickBtnJpp() {
        final JsonObject object = new JsonObject();
        object.setId(new Random().nextInt(100));
        object.setName(UUID.randomUUID().toString().substring(0, 8));

        try {
            final StringWriter writer = new StringWriter();
            JsonObjectGen.encodeNullToNull(writer, object);
            final String json = writer.toString();

            tvDisplay.setText(tvDisplay.getText() + "'" + json + "'\n" + JsonObjectGen.get(json) + "\n");
        } catch (IOException | JsonFormatException e) {
            Log.w(TAG, e);
        }
    }

    @OnClick(R.id.btn_ok)
    void onClickBtnOk() {
        startService(new Intent(this, MyService.class));
    }

    /**
     * メソッド名をonEventMainThreadとすることでメインスレッドで動作
     */
    public void onEventMainThread(MyService.MyEvent myEvent) {
        Picasso.with(this)
                .load(myEvent.getUrl())
                .placeholder(android.R.drawable.ic_menu_search)
                .error(android.R.drawable.ic_delete)
                .into(ivPicasso);

        if (attacher == null) {
            attacher = new PhotoViewAttacher(ivPicasso);
        } else {
            attacher.update();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (attacher != null) {
            attacher.cleanup();
        }
    }

    @JsonModel
    public static class JsonObject {

        @JsonKey
        private int id;

        @JsonKey
        private String name;

        public JsonObject() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "JsonObject{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JsonObject that = (JsonObject) o;

            if (id != that.id) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
