package framgia.com.myeditor.screen.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import framgia.com.myeditor.R;
import framgia.com.myeditor.data.repository.ImageRepository;
import framgia.com.myeditor.data.source.local.ImageLocalDataSource;
import framgia.com.myeditor.data.source.remote.ImageRemoteDataSource;
import framgia.com.myeditor.databinding.FragmentHomeBinding;
import framgia.com.myeditor.utils.rx.SchedulerProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private static HomeFragment sInstance;
    private FragmentActivity mContext;
    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        if (sInstance == null) {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initBinding();
        return mBinding.getRoot();
    }

    private void initBinding() {
        ImageDatabase database = ImageDatabase.getInstance(mContext);
        mViewModel = HomeViewModel(mContext, mBinding.viewPagerImage,
                mContext.getSupportFragmentManager(),
                new ImageRepository(ImageRemoteDataSource.getsInstance(),
                        ImageLocalDataSource.getsInstance(database.mImageDAO(), mContext)));
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        mBinding.setViewModel(mViewModel);
        initViews();
    }

    private void initViews() {
        mBinding.recyclerNewImage.addOnScrollListener(new EndlessScrollListener(
                (LinearLayoutManager) mBinding.recyclerNewImage.getLayoutManager()) {
            @Override
            protected void OnLoadMore() {
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mViewModel.OnNewsLoadMore(mBinding.progressBar);
            }
        });
        mBinding.recyclerCollection.addOnScrollListener(new EndlessScrollListener(
                (LinearLayoutManager) mBinding.recyclerCollection.getLayoutManager()) {
            @Override
            protected void OnLoadMore() {
                mViewModel.OnCollectionLoadMore();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
