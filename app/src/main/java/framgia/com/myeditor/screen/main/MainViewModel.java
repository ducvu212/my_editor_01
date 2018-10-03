package framgia.com.myeditor.screen.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import framgia.com.myeditor.screen.base.BaseViewModel;

/**
 * Created by CuD HniM on 18/10/03.
 */
public class MainViewModel extends BaseViewModel implements LifecycleOwner {
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }
}
