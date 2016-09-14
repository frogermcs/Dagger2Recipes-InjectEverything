# Dagger2Recipes: InjectEverything
Example app which shows how to make use from Multibindings and AutoFactory in Dagger 2 

---

When we decide to inject as many dependencies as possible in our app there are a couple tools which can be helpful with this. Thanks to Multibinding feature and AutoFactory library we will be able to inject objects like RecyclerView adapter or different ViewHolders (especially useful when we show different types of views in our collection view). 

This recipe shows how to make use Multibinding and Autofactory and have this code:

```java
public class RepositoriesListAdapter extends RecyclerView.Adapter {

    private Map<Integer, RepositoriesListViewHolderFactory> viewHolderFactories;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewHolderFactories.get(viewType).createViewHolder(parent); }

    @Override
    public int getItemViewType(int position) {
        Repository repository = repositories.get(position);
        if (repository.stargazers_count > 500) {
            if (repository.forks_count > 100) {
                return Repository.TYPE_FEATURED;
            }
            return Repository.TYPE_BIG;
        }
        return Repository.TYPE_NORMAL;
    }

    //…

}
```

instead of this:

```java
public class RepositoriesListAdapter extends RecyclerView.Adapter {
    //...

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder = null;
        if (viewType == Repository.TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_normal, parent, false);
            viewHolder = new RepositoryViewHolderNormal();
        } else if (viewType == Repository.TYPE_BIG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_big, parent, false);
            viewHolder = new RepositoryViewHolderBig(view);
        } else if (viewType == Repository.TYPE_FEATURED) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_featured, parent, false);
            viewHolder = new RepositoryViewHolderFeatured(view);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        Repository repository = repositories.get(position);
        if (repository.stargazers_count > 500) {
            if (repository.forks_count > 100) {
                return Repository.TYPE_FEATURED;
            }
            return Repository.TYPE_BIG;
        }
        
        return Repository.TYPE_NORMAL; 
    }
    
    //...
}

```

Check blog post: [Inject everything - ViewHolder and Dagger 2 (with Multibinding and AutoFactory example)](http://frogermcs.github.io/inject-everything-viewholder-and-dagger-2-example/) for more details.
