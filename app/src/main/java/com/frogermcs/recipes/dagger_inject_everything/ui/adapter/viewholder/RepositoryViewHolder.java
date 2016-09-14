package com.frogermcs.recipes.dagger_inject_everything.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frogermcs.recipes.dagger_inject_everything.data.model.Repository;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */

public abstract class RepositoryViewHolder extends RecyclerView.ViewHolder {
    public RepositoryViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Repository repository);
}
