package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Method;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;

/* compiled from: HandlerDispatcher.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0011\u0010\b\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0002\u001a\u0016\u0010\u000e\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0002\u001a\u001d\u0010\u000f\u001a\u00020\u0003*\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007¢\u0006\u0002\b\u0013\u001a\u0014\u0010\u0014\u001a\u00020\u0010*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"MAX_DELAY", "", "Main", "Lkotlinx/coroutines/android/HandlerDispatcher;", "getMain$annotations", "()V", "choreographer", "Landroid/view/Choreographer;", "awaitFrame", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postFrameCallback", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "updateChoreographerAndPostFrameCallback", "asCoroutineDispatcher", "Landroid/os/Handler;", "name", "", TypedValues.TransitionType.S_FROM, "asHandler", "Landroid/os/Looper;", "async", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines_android"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class HandlerDispatcherKt {
    private static final long MAX_DELAY = 4611686018427387903L;
    public static final HandlerDispatcher Main;
    private static volatile Choreographer choreographer;

    public static final HandlerDispatcher from(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<this>");
        return from$default(handler, null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Dispatchers.Main instead")
    public static /* synthetic */ void getMain$annotations() {
    }

    public static /* synthetic */ HandlerDispatcher from$default(Handler handler, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return from(handler, str);
    }

    public static final HandlerDispatcher from(Handler $this$asCoroutineDispatcher, String name) {
        Intrinsics.checkNotNullParameter($this$asCoroutineDispatcher, "<this>");
        return new HandlerContext($this$asCoroutineDispatcher, name);
    }

    public static final Handler asHandler(Looper $this$asHandler, boolean async) {
        Intrinsics.checkNotNullParameter($this$asHandler, "<this>");
        if (!async) {
            return new Handler($this$asHandler);
        }
        Method factoryMethod = Handler.class.getDeclaredMethod("createAsync", Looper.class);
        Object invoke = factoryMethod.invoke(null, $this$asHandler);
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) invoke;
    }

    static {
        Object m856constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Looper mainLooper = Looper.getMainLooper();
            Intrinsics.checkNotNullExpressionValue(mainLooper, "getMainLooper()");
            m856constructorimpl = Result.m856constructorimpl(new HandlerContext(asHandler(mainLooper, true), null, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m856constructorimpl = Result.m856constructorimpl(ResultKt.createFailure(th));
        }
        Main = (HandlerDispatcher) (Result.m862isFailureimpl(m856constructorimpl) ? null : m856constructorimpl);
    }

    public static final Object awaitFrame(Continuation<? super Long> continuation) {
        Choreographer choreographer2 = choreographer;
        if (choreographer2 != null) {
            CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellable$iv.initCancellability();
            CancellableContinuationImpl cont = cancellable$iv;
            postFrameCallback(choreographer2, cont);
            Object result = cancellable$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbes.probeCoroutineSuspended(continuation);
            }
            return result;
        }
        CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv2.initCancellability();
        final CancellableContinuationImpl cont2 = cancellable$iv2;
        Dispatchers.getMain().mo2403dispatch(EmptyCoroutineContext.INSTANCE, new Runnable() { // from class: kotlinx.coroutines.android.HandlerDispatcherKt$awaitFrame$lambda$3$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(CancellableContinuation.this);
            }
        });
        Object result2 = cancellable$iv2.getResult();
        if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateChoreographerAndPostFrameCallback(CancellableContinuation<? super Long> cancellableContinuation) {
        Choreographer it = choreographer;
        if (it == null) {
            it = Choreographer.getInstance();
            Intrinsics.checkNotNull(it);
            choreographer = it;
        }
        postFrameCallback(it, cancellableContinuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postFrameCallback(Choreographer choreographer2, final CancellableContinuation<? super Long> cancellableContinuation) {
        choreographer2.postFrameCallback(new Choreographer.FrameCallback() { // from class: kotlinx.coroutines.android.HandlerDispatcherKt$postFrameCallback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long nanos) {
                CancellableContinuation $this$doFrame_u24lambda_u240 = cancellableContinuation;
                $this$doFrame_u24lambda_u240.resumeUndispatched(Dispatchers.getMain(), Long.valueOf(nanos));
            }
        });
    }
}
