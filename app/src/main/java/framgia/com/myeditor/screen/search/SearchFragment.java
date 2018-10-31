package framgia.com.myeditor.screen.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import framgia.com.myeditor.data.repository.ImageRepository;
import framgia.com.myeditor.data.source.local.ImageLocalDataSource;
import framgia.com.myeditor.data.source.remote.ImageRemoteDataSource;
import framgia.com.myeditor.databinding.FragmentSearchBinding;
import framgia.com.myeditor.screen.base.BaseFragment;
import framgia.com.myeditor.utils.rx.SchedulerProvider;

public class SearchFragment extends BaseFragment implements SearchView.OnQueryTextListener {

    public static final String TAG = "SearchFragment";
    private static SearchFragment sInstance;
    private FragmentActivity mContext;
    private SearchViewModel mViewModel;
    private FragmentSearchBinding mBinding;

    public static SearchFragment newInstance() {
        if (sInstance == null) {
            sInstance = new SearchFragment();
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
        initViewModel();
        initBinding(inflater, container);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
        if (((AppCompatActivity) mContext).getSupportActionBar() != null) {
            ((AppCompatActivity) mContext).getSupportActionBar().setTitle(R.string.titile_search);
        }
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mViewModel.putRecentSearchToRealm(query);
        mViewModel.subcribeCollection(query);
        mViewModel.subcribeRecentSearch();
        showRecycleSearch(View.VISIBLE);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) {
            mBinding.groupSearch.setVisibility(View.VISIBLE);
            showRecycleSearch(View.GONE);
        } else {
            mBinding.groupSearch.setVisibility(View.GONE);
        }
        return true;
    }

    public void clickItem(String item) {
        mBinding.searchViewCollection.setQuery(item, true);
        mViewModel.subcribeCollection(item);
    }

    public void deleteRecentSearch(RecentSearch recentSearch) {
        mViewModel.deleteRecentSearch(recentSearch);
        mViewModel.subcribeRecentSearch();
    }

    private void initBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mBinding.searchViewCollection.setOnQueryTextListener(this);
        mBinding.setListener(new HandleClick(getActivity()));
        mBinding.setViewModel(mViewModel);
    }

    private void initViewModel() {
        ImageDatabase database = ImageDatabase.getInstance(mContext);
        mViewModel = new SearchViewModel(new ImageRepository(ImageRemoteDataSource.getsInstance(),
                ImageLocalDataSource.getsInstance(database.mImageDAO(), mContext)),
                mContext.getSupportFragmentManager());
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
    }

    private void showRecycleSearch(int hide) {
        mBinding.recyclerSearch.setVisibility(hide);
    }
}
