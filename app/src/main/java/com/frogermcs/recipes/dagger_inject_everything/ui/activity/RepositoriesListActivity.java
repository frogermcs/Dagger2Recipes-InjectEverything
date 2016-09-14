package com.frogermcs.recipes.dagger_inject_everything.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import butterknife.Bind;
import com.frogermcs.recipes.dagger_inject_everything.MyApplication;
import com.frogermcs.recipes.dagger_inject_everything.R;
import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.module.RepositoriesListActivityModule;
import com.frogermcs.recipes.dagger_inject_everything.ui.activity.presenter.RepositoriesListActivityPresenter;
import com.frogermcs.recipes.dagger_inject_everything.ui.adapter.RepositoriesListAdapter;


public class RepositoriesListActivity extends BaseActivity {
    @Bind(R.id.rvRepositories)
    RecyclerView rvRepositories;
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    RepositoriesListAdapter repositoriesListAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        setupRepositoriesListView();
        presenter.loadRepositories();
    }

    @Override
    protected void setupActivityComponent() {
        MyApplication.get(this)
                .getAppComponent()
                .plus(new RepositoriesListActivityModule(this))
                .inject(this);
    }

    private void setupRepositoriesListView() {
        rvRepositories.setAdapter(repositoriesListAdapter);
        rvRepositories.setLayoutManager(linearLayoutManager);
    }

    public void showLoading(boolean loading) {
        rvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setRepositories(ImmutableList<Repository> repositories) {
        repositoriesListAdapter.updateRepositoriesList(repositories);
    }

    public void onRepositoryClick(Repository repository) {
        Toast.makeText(this, "Repository: " + repository.name, Toast.LENGTH_SHORT).show();
    }
}
