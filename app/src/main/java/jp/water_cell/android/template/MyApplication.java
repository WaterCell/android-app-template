package jp.water_cell.android.template;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import jp.water_cell.android.template.prngfix.PRNGFixes;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PRNGFixes.apply();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        multiDexInstall();
    }

    /**
     * Multi-dex対応 http://qiita.com/KeithYokoma/items/385a94988beb2d7d8043
     */
    protected void multiDexInstall() {
        MultiDex.install(this);
    }
}
