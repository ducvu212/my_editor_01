package framgia.com.myeditor.screen.edit.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import framgia.com.myeditor.data.repository.ImageRepository;
import framgia.com.myeditor.databinding.FragmentEditBinding;
import framgia.com.myeditor.databinding.ItemEditBinding;
import framgia.com.myeditor.screen.edit.HandleItemEditClick;
import framgia.com.myeditor.screen.edit.OnUpdateUIListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/10/10.
 */
public class EditAdapter extends RecyclerView.Adapter<EditAdapter.EditViewHolder> {

    private List<ItemEdit> mEditList;
    private ImageRepository mImageRepository;
    private FragmentEditBinding mBinding;
    private OnUpdateUIListener mOnUpdateUIListener;

    public EditAdapter(List<ItemEdit> editList, ImageRepository repository,
            FragmentEditBinding binding, OnUpdateUIListener onUpdateUIListener) {
        mEditList = new ArrayList<>();
        mEditList.addAll(editList);
        mBinding = binding;
        mImageRepository = repository;
        mOnUpdateUIListener = onUpdateUIListener;
    }

    public void setEditList(List<ItemEdit> editList) {
        mEditList.addAll(editList.subList(mEditList.size(), editList.size()));
    }

    @NonNull
    @Override
    public EditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEditBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_edit, parent, false);
        return new EditViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EditViewHolder holder, int position) {
        holder.binding(mEditList.get(position), mImageRepository, mBinding, mOnUpdateUIListener);
    }

    @Override
    public int getItemCount() {
        return mEditList == null ? 0 : mEditList.size();
    }

    static class EditViewHolder extends RecyclerView.ViewHolder {

        private ItemEditBinding mBinding;

        EditViewHolder(ItemEditBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        void binding(ItemEdit edit, ImageRepository imageRepository, FragmentEditBinding binding,
                OnUpdateUIListener onUpdateUIListener) {
            mBinding.setItem(edit);
            mBinding.setListener(new HandleItemEditClick(binding, onUpdateUIListener));
            mBinding.executePendingBindings();
        }
    }
}
