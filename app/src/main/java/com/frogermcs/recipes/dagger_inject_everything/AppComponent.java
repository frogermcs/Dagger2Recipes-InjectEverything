package com.frogermcs.recipes.dagger_inject_everything;

import javax.inject.Singleton;

import dagger.Component;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.component.RepositoriesListActivityComponent;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.module.RepositoriesListActivityModule;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    RepositoriesListActivityComponent plus(RepositoriesListActivityModule module);

}