package framgia.com.myeditor.screen.home;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import framgia.com.myeditor.R;
import framgia.com.myeditor.data.model.ImageRandom;

/**
 * Created by CuD HniM on 18/10/05.
 */
public class HandleItemClick {
    private Context mContext;
    private FragmentManager mManager;

    public HandleItemClick(Context context, FragmentManager manager) {
        mContext = context;
        mManager = manager;
    }

    public void OnItemClickListener(ImageRandom imageRandom) {
        FragmentTransactionUtils.addFragment(mManager,
                ImageDetailsFragment.newInstance(imageRandom), R.id.frame_main,
                ImageDetailsFragment.TAG, true);
    }

    public void OnItemCollectionClickListener(Collection collection) {
        FragmentTransactionUtils.addFragment(mManager,
                CollectionFragment.newInstance(collection.getId(), collection.getTitle()),
                R.id.relative_main, CollectionFragment.TAG, true);
    }
}
