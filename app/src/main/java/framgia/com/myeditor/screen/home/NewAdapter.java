package framgia.com.myeditor.screen.home;

import android.databinding.DataBindingUtil;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import framgia.com.myeditor.databinding.ItemRandomImageBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/10/04.
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewHolder> {

    private List<Image> mListNewImage;

    NewAdapter() {
        mListNewImage = new ArrayList<>();
    }

    void setListNewImage(List<Image> listNewImage) {
        mListNewImage.addAll(listNewImage.subList(mListNewImage.size(), listNewImage.size()));
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemRandomImageBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_random_image, viewGroup, false);
        return new NewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder newHolder, int i) {
        newHolder.binding(mListNewImage.get(i));
    }

    @Override
    public int getItemCount() {
        return mListNewImage == null ? 0 : mListNewImage.size();
    }

    static class NewHolder extends RecyclerView.ViewHolder {

        private ItemRandomImageBinding mBinding;

        NewHolder(@NonNull ItemRandomImageBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        void binding(Image image) {
            mBinding.setItem(new ItemViewPager.Builder().mPath(image.getUrls().getRegular())
                    .mLikeByUser(image.getLikedByUser())
                    .mUserName(image.getUser().getUsername())
                    .build());
            mBinding.executePendingBindings();
        }
    }
}
