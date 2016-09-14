package com.frogermcs.recipes.dagger_inject_everything;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
    public class MyApplication extends Application {

    private AppComponent appComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}