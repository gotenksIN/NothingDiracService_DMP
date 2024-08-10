package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import androidx.collection.SimpleArrayMap;
import androidx.dynamicanimation.animation.AnimationHandler;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AnimationHandler {
    private static final long FRAME_DELAY_MS = 10;
    private static final ThreadLocal<AnimationHandler> sAnimatorHandler = new ThreadLocal<>();
    public DurationScaleChangeListener mDurationScaleChangeListener;
    private FrameCallbackScheduler mScheduler;
    private final SimpleArrayMap<AnimationFrameCallback, Long> mDelayedCallbackStartTime = new SimpleArrayMap<>();
    final ArrayList<AnimationFrameCallback> mAnimationCallbacks = new ArrayList<>();
    private final AnimationCallbackDispatcher mCallbackDispatcher = new AnimationCallbackDispatcher();
    private final Runnable mRunnable = new Runnable() { // from class: androidx.dynamicanimation.animation.AnimationHandler$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            AnimationHandler.this.m47x83fff5a8();
        }
    };
    long mCurrentFrameTime = 0;
    private boolean mListDirty = false;
    public float mDurationScale = 1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j);
    }

    /* loaded from: classes2.dex */
    public interface DurationScaleChangeListener {
        boolean register();

        boolean unregister();
    }

    /* loaded from: classes2.dex */
    private class AnimationCallbackDispatcher {
        private AnimationCallbackDispatcher() {
        }

        void dispatchAnimationFrame() {
            AnimationHandler.this.mCurrentFrameTime = SystemClock.uptimeMillis();
            AnimationHandler animationHandler = AnimationHandler.this;
            animationHandler.doAnimationFrame(animationHandler.mCurrentFrameTime);
            if (AnimationHandler.this.mAnimationCallbacks.size() > 0) {
                AnimationHandler.this.mScheduler.postFrameCallback(AnimationHandler.this.mRunnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-dynamicanimation-animation-AnimationHandler, reason: not valid java name */
    public /* synthetic */ void m47x83fff5a8() {
        this.mCallbackDispatcher.dispatchAnimationFrame();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnimationHandler getInstance() {
        ThreadLocal<AnimationHandler> threadLocal = sAnimatorHandler;
        if (threadLocal.get() == null) {
            AnimationHandler handler = new AnimationHandler(new FrameCallbackScheduler16());
            threadLocal.set(handler);
        }
        return threadLocal.get();
    }

    public AnimationHandler(FrameCallbackScheduler scheduler) {
        this.mScheduler = scheduler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAnimationFrameCallback(AnimationFrameCallback callback, long delay) {
        if (this.mAnimationCallbacks.size() == 0) {
            this.mScheduler.postFrameCallback(this.mRunnable);
            this.mDurationScale = ValueAnimator.getDurationScale();
            if (this.mDurationScaleChangeListener == null) {
                this.mDurationScaleChangeListener = new DurationScaleChangeListener33();
            }
            this.mDurationScaleChangeListener.register();
        }
        if (!this.mAnimationCallbacks.contains(callback)) {
            this.mAnimationCallbacks.add(callback);
        }
        if (delay > 0) {
            this.mDelayedCallbackStartTime.put(callback, Long.valueOf(SystemClock.uptimeMillis() + delay));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeCallback(AnimationFrameCallback callback) {
        this.mDelayedCallbackStartTime.remove(callback);
        int id = this.mAnimationCallbacks.indexOf(callback);
        if (id >= 0) {
            this.mAnimationCallbacks.set(id, null);
            this.mListDirty = true;
        }
    }

    void doAnimationFrame(long frameTime) {
        long currentTime = SystemClock.uptimeMillis();
        for (int i = 0; i < this.mAnimationCallbacks.size(); i++) {
            AnimationFrameCallback callback = this.mAnimationCallbacks.get(i);
            if (callback != null && isCallbackDue(callback, currentTime)) {
                callback.doAnimationFrame(frameTime);
            }
        }
        cleanUpList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCurrentThread() {
        return this.mScheduler.isCurrentThread();
    }

    private boolean isCallbackDue(AnimationFrameCallback callback, long currentTime) {
        Long startTime = this.mDelayedCallbackStartTime.get(callback);
        if (startTime == null) {
            return true;
        }
        if (startTime.longValue() < currentTime) {
            this.mDelayedCallbackStartTime.remove(callback);
            return true;
        }
        return false;
    }

    private void cleanUpList() {
        if (this.mListDirty) {
            for (int i = this.mAnimationCallbacks.size() - 1; i >= 0; i--) {
                if (this.mAnimationCallbacks.get(i) == null) {
                    this.mAnimationCallbacks.remove(i);
                }
            }
            if (this.mAnimationCallbacks.size() == 0) {
                this.mDurationScaleChangeListener.unregister();
            }
            this.mListDirty = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameCallbackScheduler getScheduler() {
        return this.mScheduler;
    }

    /* loaded from: classes2.dex */
    static final class FrameCallbackScheduler16 implements FrameCallbackScheduler {
        private final Choreographer mChoreographer = Choreographer.getInstance();
        private final Looper mLooper = Looper.myLooper();

        FrameCallbackScheduler16() {
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public void postFrameCallback(final Runnable frameCallback) {
            this.mChoreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j) {
                    frameCallback.run();
                }
            });
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public boolean isCurrentThread() {
            return Thread.currentThread() == this.mLooper.getThread();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class FrameCallbackScheduler14 implements FrameCallbackScheduler {
        private final Handler mHandler = new Handler(Looper.myLooper());
        private long mLastFrameTime;

        FrameCallbackScheduler14() {
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public void postFrameCallback(final Runnable frameCallback) {
            long delay = AnimationHandler.FRAME_DELAY_MS - (SystemClock.uptimeMillis() - this.mLastFrameTime);
            this.mHandler.postDelayed(new Runnable() { // from class: androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackScheduler14$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AnimationHandler.FrameCallbackScheduler14.this.m49x7ba8ba(frameCallback);
                }
            }, Math.max(delay, 0L));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$postFrameCallback$0$androidx-dynamicanimation-animation-AnimationHandler$FrameCallbackScheduler14, reason: not valid java name */
        public /* synthetic */ void m49x7ba8ba(Runnable frameCallback) {
            this.mLastFrameTime = SystemClock.uptimeMillis();
            frameCallback.run();
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public boolean isCurrentThread() {
            return Thread.currentThread() == this.mHandler.getLooper().getThread();
        }
    }

    public float getDurationScale() {
        return this.mDurationScale;
    }

    /* loaded from: classes2.dex */
    public class DurationScaleChangeListener33 implements DurationScaleChangeListener {
        ValueAnimator.DurationScaleChangeListener mListener;

        public DurationScaleChangeListener33() {
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean register() {
            if (this.mListener == null) {
                ValueAnimator.DurationScaleChangeListener durationScaleChangeListener = new ValueAnimator.DurationScaleChangeListener() { // from class: androidx.dynamicanimation.animation.AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0
                    @Override // android.animation.ValueAnimator.DurationScaleChangeListener
                    public final void onChanged(float f) {
                        AnimationHandler.DurationScaleChangeListener33.this.m48xb804c881(f);
                    }
                };
                this.mListener = durationScaleChangeListener;
                return ValueAnimator.registerDurationScaleChangeListener(durationScaleChangeListener);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$register$0$androidx-dynamicanimation-animation-AnimationHandler$DurationScaleChangeListener33, reason: not valid java name */
        public /* synthetic */ void m48xb804c881(float scale) {
            AnimationHandler.this.mDurationScale = scale;
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean unregister() {
            boolean unregistered = ValueAnimator.unregisterDurationScaleChangeListener(this.mListener);
            this.mListener = null;
            return unregistered;
        }
    }
}
