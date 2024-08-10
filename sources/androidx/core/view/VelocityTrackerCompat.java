package androidx.core.view;

import android.view.VelocityTracker;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class VelocityTrackerCompat {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VelocityTrackableMotionEventAxis {
    }

    @Deprecated
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }

    public static boolean isAxisSupported(VelocityTracker tracker, int axis) {
        return Api34Impl.isAxisSupported(tracker, axis);
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis) {
        return Api34Impl.getAxisVelocity(tracker, axis);
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis, int pointerId) {
        return Api34Impl.getAxisVelocity(tracker, axis, pointerId);
    }

    /* loaded from: classes.dex */
    private static class Api34Impl {
        private Api34Impl() {
        }

        static boolean isAxisSupported(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.isAxisSupported(axis);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis, int id) {
            return velocityTracker.getAxisVelocity(axis, id);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.getAxisVelocity(axis);
        }
    }

    private VelocityTrackerCompat() {
    }
}
