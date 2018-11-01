package framgia.com.myeditor.screen.edit;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

import static framgia.com.myeditor.screen.home.BindingHome.ONE_HUNDRED;

/**
 * Created by CuD HniM on 18/10/10.
 */
public class ColorFilterGenerator {

    private static final int THRESHOLD = 127;
    public static ColorMatrix mBrightnessCM;
    private static double DELTA_INDEX[] = {
            0, 0.01, 0.02, 0.04, 0.05, 0.06, 0.07, 0.08, 0.1, 0.11, 0.12, 0.14, 0.15, 0.16, 0.17,
            0.18, 0.20, 0.21, 0.22, 0.24, 0.25, 0.27, 0.28, 0.30, 0.32, 0.34, 0.36, 0.38, 0.40,
            0.42, 0.44, 0.46, 0.48, 0.5, 0.53, 0.56, 0.59, 0.62, 0.65, 0.68, 0.71, 0.74, 0.77, 0.80,
            0.83, 0.86, 0.89, 0.92, 0.95, 0.98, 1.0, 1.06, 1.12, 1.18, 1.24, 1.30, 1.36, 1.42, 1.48,
            1.54, 1.60, 1.66, 1.72, 1.78, 1.84, 1.90, 1.96, 2.0, 2.12, 2.25, 2.37, 2.50, 2.62, 2.75,
            2.87, 3.0, 3.2, 3.4, 3.6, 3.8, 4.0, 4.3, 4.7, 4.9, 5.0, 5.5, 6.0, 6.5, 6.8, 7.0, 7.3,
            7.5, 7.8, 8.0, 8.4, 8.7, 9.0, 9.4, 9.6, 9.8, 10.0
    };

    public static ColorFilter adjustContrast(float value) {
        ColorMatrix cm = new ColorMatrix();
        adjustContrast(cm, (int) value);
        return new ColorMatrixColorFilter(cm);
    }

    public static ColorFilter adjustBrightness(int value) {
        ColorMatrix cm = new ColorMatrix();
        adjustBrightness(cm, value);
        return new ColorMatrixColorFilter(cm);
    }

    private static void adjustContrast(ColorMatrix cm, int value) {
        value = (int) cleanValue(value, ONE_HUNDRED);
        if (value == 0) {
            return;
        }
        float x;
        if (value < 0) {
            x = THRESHOLD + value / ONE_HUNDRED * THRESHOLD;
        } else {
            x = (float) DELTA_INDEX[value];
            x = x * THRESHOLD + THRESHOLD;
        }
        //Array chứa các thông số của ảnh sau khi thay đổi độ tương phản
        float[] mat = new float[] {
                x / THRESHOLD, 0, 0, 0, 0.5f * (THRESHOLD - x), 0, x / THRESHOLD, 0, 0,
                0.5f * (THRESHOLD - x), 0, 0, x / THRESHOLD, 0, 0.5f * (THRESHOLD - x), 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1
        };
        cm.postConcat(new ColorMatrix(mat));
    }

    private static void adjustBrightness(ColorMatrix cm, float value) {
        value = cleanValue(value, ONE_HUNDRED);
        if (value == 0) {
            return;
        }
        //Array chứa các thông số của ảnh sau khi thay đổi độ sáng
        float[] mat = new float[] {
                1, 0, 0, 0, value, 0, 1, 0, 0, value, 0, 0, 1, 0, value, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                1
        };
        cm.postConcat(new ColorMatrix(mat));
        mBrightnessCM = cm;
    }

    private static float cleanValue(float val, float limit) {
        return Math.min(limit, Math.max(-limit, val));
    }
}
