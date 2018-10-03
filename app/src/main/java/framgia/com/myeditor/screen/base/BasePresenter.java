package framgia.com.myeditor.screen.base;

/**
 * Created by CuD HniM on 18/10/03.
 */

public interface BasePresenter<T> {

    void setView(T view);

    void onStart();

    void onStop();
}
