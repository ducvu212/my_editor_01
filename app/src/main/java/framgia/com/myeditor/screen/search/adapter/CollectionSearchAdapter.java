package framgia.com.myeditor.screen.search.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import framgia.com.myeditor.data.model.Collection;
import framgia.com.myeditor.databinding.ItemCollectionSearchBinding;
import framgia.com.myeditor.screen.search.HandleClick;
import java.util.ArrayList;
import java.util.List;

import static framgia.com.myeditor.screen.home.adapter.RandomPagerAdapter.NUMBER_ZERO;

public class CollectionSearchAdapter
        extends RecyclerView.Adapter<CollectionSearchAdapter.CollectionHolder> {
    private List<Collection> mCollections;
    private FragmentManager mManager;

    public CollectionSearchAdapter(FragmentManager manager) {
        mCollections = new ArrayList<>();
        mManager = manager;
    }

    public void setCollections(List<Collection> collections) {
        mCollections.clear();
        mCollections.addAll(collections);
    }

    @NonNull
    @Override
    public CollectionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCollectionSearchBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_collection_search, viewGroup, false);
        return new CollectionHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionHolder collectionHolder, int i) {
        collectionHolder.binding(mCollections.get(i), mManager);
    }

    @Override
    public int getItemCount() {
        return mCollections == null ? NUMBER_ZERO : mCollections.size();
    }

    static class CollectionHolder extends RecyclerView.ViewHolder {
        private ItemCollectionSearchBinding mBinding;

        CollectionHolder(@NonNull ItemCollectionSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void binding(Collection collection, FragmentManager manager) {
            mBinding.setCollection(collection);
            mBinding.setListener(new HandleClick(manager));
            mBinding.executePendingBindings();
        }
    }
}
