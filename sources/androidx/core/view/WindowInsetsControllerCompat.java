package androidx.core.view;

import android.R;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;

/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_DEFAULT = 1;

    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;

    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
    private final Impl mImpl;

    /* loaded from: classes.dex */
    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(WindowInsetsControllerCompat windowInsetsControllerCompat, int i);
    }

    @Deprecated
    private WindowInsetsControllerCompat(WindowInsetsController insetsController) {
        this.mImpl = new Impl30(insetsController, this);
    }

    public WindowInsetsControllerCompat(Window window, View view) {
        this.mImpl = new Impl30(window, this);
    }

    @Deprecated
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(WindowInsetsController insetsController) {
        return new WindowInsetsControllerCompat(insetsController);
    }

    public void show(int types) {
        this.mImpl.show(types);
    }

    public void hide(int types) {
        this.mImpl.hide(types);
    }

    public boolean isAppearanceLightStatusBars() {
        return this.mImpl.isAppearanceLightStatusBars();
    }

    public void setAppearanceLightStatusBars(boolean isLight) {
        this.mImpl.setAppearanceLightStatusBars(isLight);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.mImpl.isAppearanceLightNavigationBars();
    }

    public void setAppearanceLightNavigationBars(boolean isLight) {
        this.mImpl.setAppearanceLightNavigationBars(isLight);
    }

    public void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat listener) {
        this.mImpl.controlWindowInsetsAnimation(types, durationMillis, interpolator, cancellationSignal, listener);
    }

    public void setSystemBarsBehavior(int behavior) {
        this.mImpl.setSystemBarsBehavior(behavior);
    }

    public int getSystemBarsBehavior() {
        return this.mImpl.getSystemBarsBehavior();
    }

    public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        this.mImpl.addOnControllableInsetsChangedListener(listener);
    }

    public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        this.mImpl.removeOnControllableInsetsChangedListener(listener);
    }

    /* loaded from: classes.dex */
    private static class Impl {
        Impl() {
        }

        void show(int types) {
        }

        void hide(int types) {
        }

        void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat listener) {
        }

        void setSystemBarsBehavior(int behavior) {
        }

        int getSystemBarsBehavior() {
            return 0;
        }

        public boolean isAppearanceLightStatusBars() {
            return false;
        }

        public void setAppearanceLightStatusBars(boolean isLight) {
        }

        public boolean isAppearanceLightNavigationBars() {
            return false;
        }

        public void setAppearanceLightNavigationBars(boolean isLight) {
        }

        void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }

        void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }
    }

    /* loaded from: classes.dex */
    private static class Impl20 extends Impl {
        private final View mView;
        protected final Window mWindow;

        Impl20(Window window, View view) {
            this.mWindow = window;
            this.mView = view;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void show(int typeMask) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    showForType(i);
                }
            }
        }

        private void showForType(int type) {
            switch (type) {
                case 1:
                    unsetSystemUiFlag(4);
                    unsetWindowFlag(1024);
                    return;
                case 2:
                    unsetSystemUiFlag(2);
                    return;
                case 8:
                    View view = this.mView;
                    if (view.isInEditMode() || view.onCheckIsTextEditor()) {
                        view.requestFocus();
                    } else {
                        view = this.mWindow.getCurrentFocus();
                    }
                    if (view == null) {
                        view = this.mWindow.findViewById(R.id.content);
                    }
                    if (view != null && view.hasWindowFocus()) {
                        final View finalView = view;
                        finalView.post(new Runnable() { // from class: androidx.core.view.WindowInsetsControllerCompat$Impl20$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                WindowInsetsControllerCompat.Impl20.lambda$showForType$0(finalView);
                            }
                        });
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$showForType$0(View finalView) {
            InputMethodManager imm = (InputMethodManager) finalView.getContext().getSystemService("input_method");
            imm.showSoftInput(finalView, 0);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void hide(int typeMask) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    hideForType(i);
                }
            }
        }

        private void hideForType(int type) {
            switch (type) {
                case 1:
                    setSystemUiFlag(4);
                    return;
                case 2:
                    setSystemUiFlag(2);
                    return;
                case 8:
                    ((InputMethodManager) this.mWindow.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mWindow.getDecorView().getWindowToken(), 0);
                    return;
                default:
                    return;
            }
        }

        protected void setSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | systemUiFlag);
        }

        protected void unsetSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (~systemUiFlag));
        }

        protected void setWindowFlag(int windowFlag) {
            this.mWindow.addFlags(windowFlag);
        }

        protected void unsetWindowFlag(int windowFlag) {
            this.mWindow.clearFlags(windowFlag);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat listener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void setSystemBarsBehavior(int behavior) {
            switch (behavior) {
                case 0:
                    unsetSystemUiFlag(6144);
                    return;
                case 1:
                    unsetSystemUiFlag(4096);
                    setSystemUiFlag(2048);
                    return;
                case 2:
                    unsetSystemUiFlag(2048);
                    setSystemUiFlag(4096);
                    return;
                default:
                    return;
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        int getSystemBarsBehavior() {
            return 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }
    }

    /* loaded from: classes.dex */
    private static class Impl23 extends Impl20 {
        Impl23(Window window, View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean isLight) {
            if (isLight) {
                unsetWindowFlag(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                setWindowFlag(Integer.MIN_VALUE);
                setSystemUiFlag(8192);
                return;
            }
            unsetSystemUiFlag(8192);
        }
    }

    /* loaded from: classes.dex */
    private static class Impl26 extends Impl23 {
        Impl26(Window window, View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean isLight) {
            if (isLight) {
                unsetWindowFlag(134217728);
                setWindowFlag(Integer.MIN_VALUE);
                setSystemUiFlag(16);
                return;
            }
            unsetSystemUiFlag(16);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Impl30 extends Impl {
        final WindowInsetsControllerCompat mCompatController;
        final WindowInsetsController mInsetsController;
        private final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> mListeners;
        protected Window mWindow;

        Impl30(Window window, WindowInsetsControllerCompat compatController) {
            this(window.getInsetsController(), compatController);
            this.mWindow = window;
        }

        Impl30(WindowInsetsController insetsController, WindowInsetsControllerCompat compatController) {
            this.mListeners = new SimpleArrayMap<>();
            this.mInsetsController = insetsController;
            this.mCompatController = compatController;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void show(int types) {
            if (this.mWindow != null) {
                int i = types & 8;
            }
            this.mInsetsController.show(types);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void hide(int types) {
            if (this.mWindow != null) {
                int i = types & 8;
            }
            this.mInsetsController.hide(types);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean isLight) {
            if (isLight) {
                if (this.mWindow != null) {
                    setSystemUiFlag(8192);
                }
                this.mInsetsController.setSystemBarsAppearance(8, 8);
            } else {
                if (this.mWindow != null) {
                    unsetSystemUiFlag(8192);
                }
                this.mInsetsController.setSystemBarsAppearance(0, 8);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean isLight) {
            if (isLight) {
                if (this.mWindow != null) {
                    setSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(16, 16);
            } else {
                if (this.mWindow != null) {
                    unsetSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(0, 16);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, final WindowInsetsAnimationControlListenerCompat listener) {
            WindowInsetsAnimationControlListener fwListener = new WindowInsetsAnimationControlListener() { // from class: androidx.core.view.WindowInsetsControllerCompat.Impl30.1
                private WindowInsetsAnimationControllerCompat mCompatAnimController = null;

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onReady(WindowInsetsAnimationController controller, int types2) {
                    WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(controller);
                    this.mCompatAnimController = windowInsetsAnimationControllerCompat;
                    listener.onReady(windowInsetsAnimationControllerCompat, types2);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onFinished(WindowInsetsAnimationController controller) {
                    listener.onFinished(this.mCompatAnimController);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onCancelled(WindowInsetsAnimationController controller) {
                    listener.onCancelled(controller == null ? null : this.mCompatAnimController);
                }
            };
            this.mInsetsController.controlWindowInsetsAnimation(types, durationMillis, interpolator, cancellationSignal, fwListener);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void setSystemBarsBehavior(int behavior) {
            this.mInsetsController.setSystemBarsBehavior(behavior);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        int getSystemBarsBehavior() {
            return this.mInsetsController.getSystemBarsBehavior();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void addOnControllableInsetsChangedListener(final OnControllableInsetsChangedListener listener) {
            if (this.mListeners.containsKey(listener)) {
                return;
            }
            WindowInsetsController.OnControllableInsetsChangedListener fwListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.WindowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i) {
                    WindowInsetsControllerCompat.Impl30.this.m42xeaa3df30(listener, windowInsetsController, i);
                }
            };
            this.mListeners.put(listener, fwListener);
            this.mInsetsController.addOnControllableInsetsChangedListener(fwListener);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$addOnControllableInsetsChangedListener$1$androidx-core-view-WindowInsetsControllerCompat$Impl30, reason: not valid java name */
        public /* synthetic */ void m42xeaa3df30(OnControllableInsetsChangedListener listener, WindowInsetsController controller, int typeMask) {
            if (this.mInsetsController == controller) {
                listener.onControllableInsetsChanged(this.mCompatController, typeMask);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
            WindowInsetsController.OnControllableInsetsChangedListener fwListener = this.mListeners.remove(listener);
            if (fwListener != null) {
                this.mInsetsController.removeOnControllableInsetsChangedListener(fwListener);
            }
        }

        protected void unsetSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (~systemUiFlag));
        }

        protected void setSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | systemUiFlag);
        }
    }
}
