package androidx.core.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class TypedValueCompat {
    private static final float INCHES_PER_MM = 0.03937008f;
    private static final float INCHES_PER_PT = 0.013888889f;

    private TypedValueCompat() {
    }

    public static float deriveDimension(int unitToConvertTo, float pixelValue, DisplayMetrics metrics) {
        return Api34Impl.deriveDimension(unitToConvertTo, pixelValue, metrics);
    }

    public static float dpToPx(float dpValue, DisplayMetrics metrics) {
        return TypedValue.applyDimension(1, dpValue, metrics);
    }

    public static float pxToDp(float pixelValue, DisplayMetrics metrics) {
        return deriveDimension(1, pixelValue, metrics);
    }

    public static float spToPx(float spValue, DisplayMetrics metrics) {
        return TypedValue.applyDimension(2, spValue, metrics);
    }

    public static float pxToSp(float pixelValue, DisplayMetrics metrics) {
        return deriveDimension(2, pixelValue, metrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static float deriveDimension(int unitToConvertTo, float pixelValue, DisplayMetrics metrics) {
            return TypedValue.deriveDimension(unitToConvertTo, pixelValue, metrics);
        }
    }
}
