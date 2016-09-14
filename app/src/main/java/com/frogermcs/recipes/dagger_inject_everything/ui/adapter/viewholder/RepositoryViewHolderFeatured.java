package com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.frogermcs.recipes.dagger_inject_everything.R;
import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */
@AutoFactory(implementing = RepositoriesListViewHolderFactory.class)
public class RepositoryViewHolderFeatured extends RepositoryViewHolder {

    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvStars)
    TextView tvStars;
    @Bind(R.id.tvForks)
    TextView tvForks;

    public RepositoryViewHolderFeatured(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_featured, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Repository repository) {
        tvName.setText(repository.name);
        tvStars.setText("Stars: " + repository.stargazers_count);
        tvForks.setText("Forks: " + repository.forks_count);
    }
}
