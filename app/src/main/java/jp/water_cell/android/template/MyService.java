package jp.water_cell.android.template;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

public class MyService extends IntentService {

    private static final String TAG = MyService.class.getSimpleName();

    public MyService() {
        super(TAG);
    }

    public MyService(String name) {
        super(name);
    }

    /**
     * google画像検索からランダムでラーメン画像のurlを取得
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "start");

        try {
            final String body = new OkHttpClient()
                    .newCall(new Request.Builder()
                            .url(new Uri.Builder()
                                    .scheme("https")
                                    .authority("www.google.co.jp")
                                    .path("search")
                                    .appendQueryParameter("q", "ラーメン")
                                    .appendQueryParameter("tbm", "isch")
                                    .toString())
                            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36")
                            .get()
                            .build())
                    .execute()
                    .body()
                    .string();

            final Pattern pattern = Pattern.compile("https?://[^<>:;]+?\\.jpg");
            final Matcher matcher = pattern.matcher(body);

            final List<String> urls = new ArrayList<>();

            if (matcher.find()) {
                Log.d(TAG, "match");
                for (int i = 0; matcher.find(i); i = matcher.end()) {
                    urls.add(matcher.group());
                }

                // イベントの発火
                EventBus.getDefault().post(new MyEvent(urls.get(new Random().nextInt(urls.size()))));
            } else {
                Log.d(TAG, "no match");
            }
        } catch (IOException e) {
            Log.w(TAG, e);
        }

        Log.d(TAG, "finish");
    }

    public static class MyEvent {

        private String url;

        public MyEvent(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
