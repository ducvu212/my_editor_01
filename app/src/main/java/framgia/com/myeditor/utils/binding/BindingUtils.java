package framgia.com.myeditor.utils.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by CuD HniM on 18/10/03.
 */

public final class BindingUtils {

    private BindingUtils() {
        // No-op
    }

    /**
     * setErrorMessage for TextInputLayout
     */
    @BindingAdapter({ "errorText" })
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }

    /**
     * setAdapter For RecyclerView
     */
    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
