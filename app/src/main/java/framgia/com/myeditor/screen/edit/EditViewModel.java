package framgia.com.myeditor.screen.edit;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import framgia.com.myeditor.data.repository.ImageRepository;
import framgia.com.myeditor.databinding.FragmentEditBinding;
import framgia.com.myeditor.screen.base.BaseViewModel;
import framgia.com.myeditor.screen.edit.adapter.EditAdapter;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/10/10.
 */
public class EditViewModel extends BaseViewModel implements LifecycleOwner {

    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private LifecycleRegistry mLifecycleRegistry;
    private MutableLiveData mEditData;
    private ObservableField<EditAdapter> mEditObservableField = new ObservableField<>();
    private EditAdapter mEditAdapter;
    private List<ItemEdit> mEditList;
    private RecyclerView mRecyclerView;

    EditViewModel(Context context, ImageRepository repository, FragmentEditBinding binding,
            OnUpdateUIListener onUpdateUIListener) {
        mContext = context;
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        mEditList = new ArrayList<>();
        mEditData = new MutableLiveData();
        mEditAdapter = new EditAdapter(mEditList, repository, binding, onUpdateUIListener);
        mRecyclerView = binding.recyclerEdit;
        initData();
    }

    public ObservableField<EditAdapter> getEditObservableField() {
        return mEditObservableField;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onStart() {
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    private void initData() {
        mEditObservableField.set(mEditAdapter);
        mEditList.add(new ItemEdit.Builder().mName(
                mContext.getResources().getString(R.string.edit_brightness))
                .mImageView(R.drawable.ic_brightnes)
                .build());
        mEditList.add(new ItemEdit.Builder().mName(
                mContext.getResources().getString(R.string.edit_contrast))
                .mImageView(R.drawable.ic_contrast)
                .build());
        mEditList.add(
                new ItemEdit.Builder().mName(mContext.getResources().getString(R.string.edit_crop))
                        .mImageView(R.drawable.ic_crop)
                        .build());
        mEditList.add(
                new ItemEdit.Builder().mName(mContext.getResources().getString(R.string.edit_draw))
                        .mImageView(R.drawable.ic_draw)
                        .build());
        mEditList.add(
                new ItemEdit.Builder().mName(mContext.getResources().getString(R.string.edit_add))
                        .mImageView(R.drawable.ic_add)
                        .build());
        mEditAdapter.setEditList(mEditList);
        mRecyclerView.setAdapter(mEditAdapter);
        mEditData.setValue(mEditList);
    }
}
