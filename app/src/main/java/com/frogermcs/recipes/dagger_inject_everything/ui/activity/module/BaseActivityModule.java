package com.frogermcs.recipes.dagger_inject_everything.ui.activity.module;

import dagger.Module;
import dagger.Provides;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.ActivityScope;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.BaseActivity;

/**
 * Created by froger_mcs on 16/07/16.
 */

@Module
public abstract class BaseActivityModule<T extends BaseActivity> {
    protected final T activity;

    public BaseActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    T provideActivity() {
        return activity;
    }
}
