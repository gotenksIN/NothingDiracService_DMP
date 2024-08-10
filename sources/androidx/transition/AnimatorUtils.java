package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: classes3.dex */
class AnimatorUtils {

    /* loaded from: classes3.dex */
    interface AnimatorPauseListenerCompat {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addPauseListener(Animator animator, AnimatorListenerAdapter listener) {
        Api19Impl.addPauseListener(animator, listener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pause(Animator animator) {
        Api19Impl.pause(animator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resume(Animator animator) {
        Api19Impl.resume(animator);
    }

    private AnimatorUtils() {
    }

    /* loaded from: classes3.dex */
    static class Api19Impl {
        private Api19Impl() {
        }

        static void addPauseListener(Animator animator, AnimatorListenerAdapter listener) {
            animator.addPauseListener(listener);
        }

        static void pause(Animator animator) {
            animator.pause();
        }

        static void resume(Animator animator) {
            animator.resume();
        }
    }
}
