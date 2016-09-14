package com.frogermcs.recipes.dagger_inject_everything.ui.activity.presenter;

import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import com.frogermcs.recipes.dagger_inject_everything.data.api.RepositoriesManager;
import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.ActivityScope;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.RepositoriesListActivity;
import com.frogermcs.recipes.dagger_inject_everything.utils.SimpleObserver;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
public class RepositoriesListActivityPresenter {
    private RepositoriesListActivity repositoriesListActivity;
    private RepositoriesManager repositoriesManager;

    @Inject
    public RepositoriesListActivityPresenter(RepositoriesListActivity repositoriesListActivity,
                                             RepositoriesManager repositoriesManager) {
        this.repositoriesListActivity = repositoriesListActivity;
        this.repositoriesManager = repositoriesManager;
    }

    public void loadRepositories() {
        repositoriesListActivity.showLoading(true);
        repositoriesManager.getUsersRepositories().subscribe(new SimpleObserver<ImmutableList<Repository>>() {
            @Override
            public void onNext(ImmutableList<Repository> repositories) {
                repositoriesListActivity.showLoading(false);
                repositoriesListActivity.setRepositories(repositories);
            }

            @Override
            public void onError(Throwable e) {
                repositoriesListActivity.showLoading(false);
            }
        });
    }

}
