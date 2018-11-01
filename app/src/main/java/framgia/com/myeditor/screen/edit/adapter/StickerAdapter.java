package framgia.com.myeditor.screen.edit.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import framgia.com.myeditor.databinding.ItemStickerBinding;
import framgia.com.myeditor.screen.edit.HandleItemEditClick;
import framgia.com.myeditor.screen.edit.OnUpdateUIListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/10/24.
 */
public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerViewHolder> {

    private List<ItemSticker> mStickerList;
    private OnUpdateUIListener mOnUpdateUiListener;

    public StickerAdapter(OnUpdateUIListener onUpdateUIListener) {
        mStickerList = new ArrayList<>();
        mOnUpdateUiListener = onUpdateUIListener;
    }

    public void setStickerList(List<ItemSticker> stickerList) {
        mStickerList = stickerList;
    }

    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStickerBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_sticker, parent, false);
        return new StickerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {
        holder.binding(mStickerList.get(position), mOnUpdateUiListener);
    }

    @Override
    public int getItemCount() {
        return mStickerList == null ? 0 : mStickerList.size();
    }

    static class StickerViewHolder extends RecyclerView.ViewHolder {

        private ItemStickerBinding mBinding;

        StickerViewHolder(ItemStickerBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        void binding(ItemSticker itemSticker, OnUpdateUIListener onUpdateUiListener) {
            mBinding.setItem(itemSticker);
            mBinding.setListener(new HandleItemEditClick(onUpdateUiListener));
            mBinding.executePendingBindings();
        }
    }
}
