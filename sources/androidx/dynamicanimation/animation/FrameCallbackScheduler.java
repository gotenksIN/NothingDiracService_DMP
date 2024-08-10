package androidx.dynamicanimation.animation;

/* loaded from: classes2.dex */
public interface FrameCallbackScheduler {
    boolean isCurrentThread();

    void postFrameCallback(Runnable runnable);
}
