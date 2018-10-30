package framgia.com.myeditor.screen.home;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;
import framgia.com.myeditor.BuildConfig;
import framgia.com.myeditor.data.model.Collection;
import framgia.com.myeditor.data.model.Image;
import framgia.com.myeditor.data.model.ImageRandom;
import framgia.com.myeditor.data.repository.ImageRepository;
import framgia.com.myeditor.screen.base.BaseViewModel;
import framgia.com.myeditor.utils.common.DisplayUtils;
import framgia.com.myeditor.utils.rx.BaseSchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;
import static framgia.com.myeditor.screen.home.BindingHome.NUMBER_CONVERT;
import static framgia.com.myeditor.screen.home.BindingHome.ONE_HUNDRED;

/**
 * Created by CuD HniM on 18/10/03.
 */
public class HomeViewModel extends BaseViewModel implements LifecycleOwner {

    private static final long TIME_DELAY = 10000;
    private static final long TIME_PRERIOD = 10000;
    private Context mContext;
    private BaseSchedulerProvider mSchedulerProvider;
    private ImageRepository mRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private LifecycleRegistry mLifecycleRegistry;
    private List<Collection> mCollections;
    private List<Image> mNewList;
    private RandomPagerAdapter mAdapter;
    private CollectionAdapter mCollectionAdapter;
    private NewAdapter mNewAdapter;
    private List<Image> mImages;
    private ViewPager mPager;
    private ImageRandom mImageRandom;
    private int mPageNumber = 1;
    private int mPageCollectionNumber = 1;
    private ProgressBar mProgressBar;
    private MutableLiveData mRandomData;
    private MutableLiveData mCollectionData;
    private MutableLiveData mNewData;
    private ObservableField<NewAdapter> mNewObservableField = new ObservableField<>();
    private ObservableField<RandomPagerAdapter> mRandomObservableField = new ObservableField<>();
    private ObservableField<CollectionAdapter> mCollectionObservableField = new ObservableField<>();

    HomeViewModel(Context context, ViewPager viewPager, FragmentManager manager,
            ImageRepository repository) {
        mContext = context;
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        mRepository = repository;
        mPager = viewPager;
        mRandomData = new MutableLiveData();
        mCollectionData = new MutableLiveData();
        mNewData = new MutableLiveData();
        mImages = new ArrayList<>();
        mCollections = new ArrayList<>();
        mNewList = new ArrayList<>();
        mCollectionAdapter = new CollectionAdapter(context, manager);
        mNewAdapter = new NewAdapter(mContext, manager);
        initImageSize();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onStart() {
        subscribeData();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    void OnNewsLoadMore(ProgressBar progressBar) {
        mProgressBar = progressBar;
        mPageNumber++;
        getNews(mNewAdapter, mPageNumber);
    }

    private void getAllImage() {
        Disposable disposable = mRepository.getAllImages()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(imageRandoms -> {
                    for (ImageRandom image : imageRandoms) {
                        insertImage(image);
                    }
                }, throwable -> DisplayUtils.makeToast(mContext, throwable.getMessage()), () -> {
                });
        mCompositeDisposable.add(disposable);
    }

    void OnCollectionLoadMore() {
        mPageCollectionNumber++;
        getCollections(mCollectionAdapter, mPageCollectionNumber);
    }

    public ObservableField<RandomPagerAdapter> getRandomObservableField() {
        return mRandomObservableField;
    }

    public ObservableField<CollectionAdapter> getCollectionObservableField() {
        return mCollectionObservableField;
    }

    public ObservableField<NewAdapter> getNewObservableField() {
        return mNewObservableField;
    }

    private LiveData<List<Image>> getRandomImage(RandomPagerAdapter pagerAdapter) {
        mRandomObservableField.set(pagerAdapter);
        Disposable disposable = mRepository.getRandomImage()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(images -> {
                    mImages.addAll(images);
                    initRandomImages();
                    mRandomData.setValue(mImages);
                }, throwable -> {
                    mRandomData.setValue(null);
                    DisplayUtils.makeToast(mContext, throwable.getMessage());
                });
        mCompositeDisposable.add(disposable);
        return mRandomData;
    }

    private LiveData<List<Collection>> getCollections(CollectionAdapter adapter, int page) {
        mCollectionObservableField.set(adapter);
        Disposable disposable = mRepository.getCollections(page)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(collections -> {
                    mCollections.addAll(collections);
                    mCollectionData.setValue(mCollections);
                }, throwable -> {
                    mCollectionData.setValue(null);
                    DisplayUtils.makeToast(mContext, throwable.getMessage());
                });
        mCompositeDisposable.add(disposable);
        return mCollectionData;
    }

    private LiveData<List<Image>> getNews(NewAdapter adapter, int page) {
        mNewObservableField.set(adapter);
        Disposable disposable = mRepository.getNewImages(page, BuildConfig.API_KEY)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(images -> {
                    mNewList.addAll(images);
                    mNewData.setValue(mNewList);
                    for (Image image : images) {
                        ImageRandom imageRandom = new ImageRandom.Builder().mImageId(image.getId())
                                .mRawImage(image.getUrls().getRaw())
                                .mPath(image.getUrls().getRegular())
                                .mUserName(image.getUser().getUsername())
                                .mType(ImageType.REMOTE)
                                .build();
                        insertImage(imageRandom);
                    }
                }, throwable -> {
                    mNewData.setValue(null);
                    DisplayUtils.makeToast(mContext, throwable.getMessage());
                });
        mCompositeDisposable.add(disposable);
        return mNewData;
    }

    private void subscribeData() {
        getRandomImage(mAdapter).observe(this, images -> {
            mAdapter.notifyDataSetChanged();
            mAdapter.setRandomList(images);
        });

        getCollections(mCollectionAdapter, mPageCollectionNumber).observe(this, collections -> {
            mCollectionAdapter.setCollections(collections);
            mCollectionAdapter.notifyDataSetChanged();
        });

        getNews(mNewAdapter, mPageNumber).observe(this, images -> {
            if (mProgressBar != null) {
                mProgressBar.setVisibility(GONE);
            }
            mNewAdapter.setNewImages(images);
            mNewAdapter.notifyDataSetChanged();
        });
    }

    private void initRandomImages() {
        mAdapter = new RandomPagerAdapter();
        mAdapter.setRandomList(mImages);
        mAdapter.notifyDataSetChanged();
        mPager.setAdapter(mAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new RandomTimer(), TIME_DELAY, TIME_PRERIOD);
    }

    public void initImageSize() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        float ratio = (ONE_HUNDRED * mPager.getMeasuredHeight()) / width;
        new BindingHome(width, height, (float) (ratio * NUMBER_CONVERT), ImageType.REMOTE);
    }

    void insertImage(ImageRandom imageRandom) {
        Disposable disposable = Observable.create(emitter -> {
            mRepository.insertImage(imageRandom);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.io()).observeOn(mSchedulerProvider.ui()).subscribe(o -> {

        }, throwable -> DisplayUtils.makeToast(mContext, throwable.getMessage()), () -> {

        });
        mCompositeDisposable.add(disposable);
    }

    private class RandomTimer extends TimerTask {
        private int mIndex = 0;

        @Override
        public void run() {
            ((Activity) mContext).runOnUiThread(() -> {
                if (mIndex == mImages.size()) {
                    mIndex = 0;
                }
                mPager.setCurrentItem(mIndex);
                mIndex++;
            });
        }
    }
}
