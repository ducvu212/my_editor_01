package framgia.com.myeditor.utils.rx;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by CuD HniM on 18/10/03.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
