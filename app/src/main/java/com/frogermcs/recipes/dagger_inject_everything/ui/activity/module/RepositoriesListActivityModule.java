package com.frogermcs.recipes.dagger_inject_everything.ui.activity.module;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.ActivityScope;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.RepositoriesListActivity;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.RepositoriesListAdapter;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder.RepositoriesListViewHolderFactory;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder.RepositoryViewHolderBigFactory;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder.RepositoryViewHolderFeaturedFactory;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder.RepositoryViewHolderNormalFactory;

import java.util.Map;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoriesListActivityModule extends BaseActivityModule<RepositoriesListActivity> {

    public RepositoriesListActivityModule(RepositoriesListActivity activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    RepositoriesListAdapter provideRepositoriesListAdapter(RepositoriesListActivity repositoriesListActivity,
                                                           Map<Integer, RepositoriesListViewHolderFactory> viewHolderFactories) {
        return new RepositoriesListAdapter(repositoriesListActivity, viewHolderFactories);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(RepositoriesListActivity repositoriesListActivity) {
        return new LinearLayoutManager(repositoriesListActivity);
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_NORMAL)
    RepositoriesListViewHolderFactory provideViewHolderNormalFactory() {
        return new RepositoryViewHolderNormalFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_BIG)
    RepositoriesListViewHolderFactory provideViewHolderBigFactory() {
        return new RepositoryViewHolderBigFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_FEATURED)
    RepositoriesListViewHolderFactory provideViewHolderFeaturedFactory() {
        return new RepositoryViewHolderFeaturedFactory();
    }
}