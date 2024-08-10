package androidx.transition;

import android.graphics.Canvas;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
class CanvasUtils {
    private static Method sInorderBarrierMethod;
    private static boolean sOrderMethodsFetched;
    private static Method sReorderBarrierMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableZ(Canvas canvas, boolean enable) {
        if (enable) {
            Api29Impl.enableZ(canvas);
        } else {
            Api29Impl.disableZ(canvas);
        }
    }

    private CanvasUtils() {
    }

    /* loaded from: classes3.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static void enableZ(Canvas canvas) {
            canvas.enableZ();
        }

        static void disableZ(Canvas canvas) {
            canvas.disableZ();
        }
    }
}
