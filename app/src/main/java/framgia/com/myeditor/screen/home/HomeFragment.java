package framgia.com.myeditor.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import framgia.com.myeditor.databinding.FragmentHomeBinding;
import framgia.com.myeditor.utils.rx.SchedulerProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initBinding();
        return mBinding.getRoot();
    }

    private void initBinding() {
        mViewModel = new HomeViewModel(getContext(), mBinding.viewPagerImage,
                new ImageRepository(ImageRemoteDataSource.getsInstance(),
                        ImageLocalDataSource.getsInstance()));
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
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
