package com.frogermcs.recipes.dagger_inject_everything.ui.activity.component;

import dagger.Subcomponent;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.ActivityScope;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.RepositoriesListActivity;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.module.RepositoriesListActivityModule;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.presenter.RepositoriesListActivityPresenter;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoriesListActivityModule.class
)
public interface RepositoriesListActivityComponent {

    RepositoriesListActivity inject(RepositoriesListActivity repositoriesListActivity);

}