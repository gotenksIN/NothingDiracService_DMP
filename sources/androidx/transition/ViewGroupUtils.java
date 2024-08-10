package androidx.transition;

import android.view.ViewGroup;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
class ViewGroupUtils {
    private static Method sGetChildDrawingOrderMethod;
    private static boolean sGetChildDrawingOrderMethodFetched;
    private static boolean sTryHiddenSuppressLayout = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewGroupOverlayImpl getOverlay(ViewGroup group) {
        return new ViewGroupOverlayApi18(group);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void suppressLayout(ViewGroup group, boolean suppress) {
        Api29Impl.suppressLayout(group, suppress);
    }

    private static void hiddenSuppressLayout(ViewGroup group, boolean suppress) {
        if (sTryHiddenSuppressLayout) {
            try {
                Api29Impl.suppressLayout(group, suppress);
            } catch (NoSuchMethodError e) {
                sTryHiddenSuppressLayout = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getChildDrawingOrder(ViewGroup viewGroup, int i) {
        return Api29Impl.getChildDrawingOrder(viewGroup, i);
    }

    private ViewGroupUtils() {
    }

    /* loaded from: classes3.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static void suppressLayout(ViewGroup viewGroup, boolean suppress) {
            viewGroup.suppressLayout(suppress);
        }

        static int getChildDrawingOrder(ViewGroup viewGroup, int drawingPosition) {
            return viewGroup.getChildDrawingOrder(drawingPosition);
        }
    }
}
