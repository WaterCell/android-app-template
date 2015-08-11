package jp.water_cell.android.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// @Config用の設定はapp/src/test/resources/robolectric.propertiesに移動
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    @Test
    public void JsonObjectが既定のJSONをパースできること() throws Exception {

        final String json = "{\"id\": 1, \"name\": \"hoge\"}";

        MainActivity.JsonObject actual = JsonObjectGen.get(json);
        MainActivity.JsonObject expected = new MainActivity.JsonObject();
        expected.setId(1);
        expected.setName("hoge");

        assertThat(actual, is(expected));
    }

}
