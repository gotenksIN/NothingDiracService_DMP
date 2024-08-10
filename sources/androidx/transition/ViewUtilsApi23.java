package androidx.transition;

import android.view.View;

/* loaded from: classes3.dex */
class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    @Override // androidx.transition.ViewUtilsBase
    public void setTransitionVisibility(View view, int visibility) {
        if (sTryHiddenSetTransitionVisibility) {
            try {
                Api29Impl.setTransitionVisibility(view, visibility);
            } catch (NoSuchMethodError e) {
                sTryHiddenSetTransitionVisibility = false;
            }
        }
    }

    /* loaded from: classes3.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static void setTransitionVisibility(View view, int visibility) {
            view.setTransitionVisibility(visibility);
        }
    }
}
