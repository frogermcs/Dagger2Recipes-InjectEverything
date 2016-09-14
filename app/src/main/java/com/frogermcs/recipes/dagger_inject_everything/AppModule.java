package com.frogermcs.recipes.dagger_inject_everything;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.frogermcs.recipes.dagger_inject_everything.data.api.RepositoriesManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences("App", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RepositoriesManager provideRepositoriesManager(Gson gson) {
        return new RepositoriesManager(application, gson);
    }
}