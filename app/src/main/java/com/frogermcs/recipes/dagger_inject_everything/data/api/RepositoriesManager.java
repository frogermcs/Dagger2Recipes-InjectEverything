package com.frogermcs.recipes.dagger_inject_everything.data.api;

import android.app.Application;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.frogermcs.recipes.dagger_inject_everything.data.api.response.RepositoryResponse;
import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class RepositoriesManager {
    private Application application;
    private Gson gson;

    public RepositoriesManager(Application application, Gson gson) {
        this.application = application;
        this.gson = gson;
    }

    public Observable<ImmutableList<Repository>> getUsersRepositories() {
        return Observable.defer(new Func0<Observable<List<RepositoryResponse>>>() {
            @Override
            public Observable<List<RepositoryResponse>> call() {
                String json = null;
                try {
                    InputStream is = application.getAssets().open("repos.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                    Type listType = new TypeToken<ArrayList<RepositoryResponse>>() {
                    }.getType();
                    List<RepositoryResponse> repositoryResponses = gson.fromJson(json, listType);
                    return Observable.just(repositoryResponses);
                } catch (IOException ex) {
                    return Observable.error(ex);
                }
            }
        })
                .map(new Func1<List<RepositoryResponse>, ImmutableList<Repository>>() {
                    @Override
                    public ImmutableList<Repository> call(List<RepositoryResponse> repositoriesListResponse) {
                        final ImmutableList.Builder<Repository> listBuilder = ImmutableList.builder();
                        for (RepositoryResponse repositoryResponse : repositoriesListResponse) {
                            Repository repository = new Repository();
                            repository.id = repositoryResponse.id;
                            repository.name = repositoryResponse.name;
                            repository.url = repositoryResponse.url;
                            repository.stargazers_count = repositoryResponse.stargazers_count;
                            repository.forks_count = repositoryResponse.forks_count;
                            listBuilder.add(repository);
                        }
                        return listBuilder.build();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
