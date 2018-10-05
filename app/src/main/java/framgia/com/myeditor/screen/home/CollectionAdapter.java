package framgia.com.myeditor.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import framgia.com.myeditor.databinding.ItemColectionBinding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionHolder> {
    private List<Collection> mListCollections;

    CollectionAdapter() {
        mListCollections = new ArrayList<>();
    }

    void setListCollections(List<Collection> listCollections) {
        mListCollections.addAll(
                listCollections.subList(mListCollections.size(), listCollections.size()));
    }

    @NonNull
    @Override
    public CollectionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemColectionBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_colection, viewGroup, false);
        return new CollectionHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionHolder collectionHolder, int i) {
        collectionHolder.binding(mListCollections.get(i));
    }

    @Override
    public int getItemCount() {
        return mListCollections == null ? 0 : mListCollections.size();
    }

    static class CollectionHolder extends RecyclerView.ViewHolder {
        private ItemColectionBinding mBinding;

        CollectionHolder(@NonNull ItemColectionBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        public void binding(Collection collection) {
            mBinding.setCollection(collection);
            mBinding.executePendingBindings();
        }
    }
}
