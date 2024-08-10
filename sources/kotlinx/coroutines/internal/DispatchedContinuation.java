package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStrings;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

/* compiled from: DispatchedContinuation.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\u001b\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\u001f\u001a\u00020 J\u001f\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\f2\u0006\u0010#\u001a\u00020$H\u0010¢\u0006\u0002\b%J\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001cJ\u001f\u0010'\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010(\u001a\u00028\u0000H\u0000¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\n\u0018\u00010,j\u0004\u0018\u0001`-H\u0016J\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020/2\u0006\u0010#\u001a\u00020$J\u0006\u00101\u001a\u00020 JI\u00102\u001a\u00020 2\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u0000042%\b\b\u00105\u001a\u001f\u0012\u0013\u0012\u00110$¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020 \u0018\u000106H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020/2\b\u0010;\u001a\u0004\u0018\u00010\fH\u0086\bJ\u001f\u0010<\u001a\u00020 2\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000004H\u0086\bø\u0001\u0001¢\u0006\u0002\u0010=J\u001e\u0010>\u001a\u00020 2\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000004H\u0016ø\u0001\u0001¢\u0006\u0002\u0010=J\u000f\u0010?\u001a\u0004\u0018\u00010\fH\u0010¢\u0006\u0002\b@J\b\u0010A\u001a\u00020BH\u0016J\u0014\u0010C\u001a\u0004\u0018\u00010$2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030DR\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u0004\u0018\u00010\f8\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006E"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "_reusableCancellableContinuation", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "get_state$external__kotlinx_coroutines__android_common__kotlinx_coroutines$annotations", "()V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "countOrElement", "delegate", "getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()Lkotlin/coroutines/Continuation;", "reusableCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "getReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "awaitReusability", "", "cancelCompletedResult", "takenState", "cause", "", "cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "claimReusableCancellableContinuation", "dispatchYield", "value", "dispatchYield$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "isReusable", "", "postponeCancellation", "release", "resumeCancellableWith", "result", "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeCancelled", "state", "resumeUndispatchedWith", "(Ljava/lang/Object;)V", "resumeWith", "takeState", "takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "toString", "", "tryReleaseClaimedContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private final AtomicRef<Object> _reusableCancellableContinuation;
    public Object _state;
    public final Continuation<T> continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    public static /* synthetic */ void get_state$external__kotlinx_coroutines__android_common__kotlinx_coroutines$annotations() {
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher dispatcher, Continuation<? super T> continuation) {
        super(-1);
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        this.dispatcher = dispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
        this._reusableCancellableContinuation = AtomicFU.atomic((Object) null);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    private final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object value = this._reusableCancellableContinuation.getValue();
        if (value instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) value;
        }
        return null;
    }

    public final boolean isReusable() {
        return this._reusableCancellableContinuation.getValue() != null;
    }

    public final void awaitReusability() {
        Object it;
        AtomicRef $this$loop$iv = this._reusableCancellableContinuation;
        do {
            it = $this$loop$iv.getValue();
        } while (it == DispatchedContinuationKt.REUSABLE_CLAIMED);
    }

    public final void release() {
        awaitReusability();
        CancellableContinuationImpl<?> reusableCancellableContinuation = getReusableCancellableContinuation();
        if (reusableCancellableContinuation != null) {
            reusableCancellableContinuation.detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
    }

    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        AtomicRef $this$loop$iv = this._reusableCancellableContinuation;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state == null) {
                this._reusableCancellableContinuation.setValue(DispatchedContinuationKt.REUSABLE_CLAIMED);
                return null;
            }
            if (state instanceof CancellableContinuationImpl) {
                if (this._reusableCancellableContinuation.compareAndSet(state, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) state;
                }
            } else if (state != DispatchedContinuationKt.REUSABLE_CLAIMED && !(state instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + state).toString());
            }
        }
    }

    public final Throwable tryReleaseClaimedContinuation(CancellableContinuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        AtomicRef $this$loop$iv = this._reusableCancellableContinuation;
        do {
            Object state = $this$loop$iv.getValue();
            if (state != DispatchedContinuationKt.REUSABLE_CLAIMED) {
                if (state instanceof Throwable) {
                    if (!this._reusableCancellableContinuation.compareAndSet(state, null)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    return (Throwable) state;
                }
                throw new IllegalStateException(("Inconsistent state " + state).toString());
            }
        } while (!this._reusableCancellableContinuation.compareAndSet(DispatchedContinuationKt.REUSABLE_CLAIMED, continuation));
        return null;
    }

    public final boolean postponeCancellation(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        AtomicRef $this$loop$iv = this._reusableCancellableContinuation;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (Intrinsics.areEqual(state, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                if (this._reusableCancellableContinuation.compareAndSet(DispatchedContinuationKt.REUSABLE_CLAIMED, cause)) {
                    return true;
                }
            } else {
                if (state instanceof Throwable) {
                    return true;
                }
                if (this._reusableCancellableContinuation.compareAndSet(state, null)) {
                    return false;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        Object state = this._state;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(state != DispatchedContinuationKt.access$getUNDEFINED$p())) {
                throw new AssertionError();
            }
        }
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        return state;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        CoroutineContext context$iv;
        Object oldValue$iv;
        CoroutineContext context = this.continuation.getContext();
        Object state = CompletionStateKt.toState$default(result, null, 1, null);
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = state;
            this.resumeMode = 0;
            this.dispatcher.mo2403dispatch(context, this);
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
        }
        EventLoop eventLoop$iv = ThreadLocalEventLoop.INSTANCE.getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (eventLoop$iv.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 0;
            eventLoop$iv.dispatchUnconfined(this);
            return;
        }
        DispatchedContinuation<T> $this$runUnconfinedEventLoop$iv$iv = this;
        eventLoop$iv.incrementUseCount(true);
        try {
            context$iv = getContext();
            Object countOrElement$iv = this.countOrElement;
            oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, countOrElement$iv);
        } finally {
            try {
            } finally {
            }
        }
        try {
            this.continuation.resumeWith(result);
            Unit unit = Unit.INSTANCE;
            do {
            } while (eventLoop$iv.processUnconfinedEvent());
        } finally {
            ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:13|14|15|(3:75|76|(9:78|79|18|(14:20|21|22|(2:59|60)(1:24)|25|26|27|28|29|30|31|32|(1:45)|36)(1:73)|37|(1:38)|41|42|43))|17|18|(0)(0)|37|(1:38)|41|42|43) */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0134, code lost:            r0 = th;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0120  */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void resumeCancellableWith(java.lang.Object r23, kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r24) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuation.resumeCancellableWith(java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object takenState, Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (takenState instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) takenState).onCancellation.invoke(cause);
        }
    }

    public final boolean resumeCancelled(Object state) {
        Job job = (Job) getContext().get(Job.INSTANCE);
        if (job != null && !job.isActive()) {
            CancellationException cause = job.getCancellationException();
            cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(state, cause);
            Result.Companion companion = Result.INSTANCE;
            resumeWith(Result.m856constructorimpl(ResultKt.createFailure(cause)));
            return true;
        }
        return false;
    }

    public final void resumeUndispatchedWith(Object result) {
        UndispatchedCoroutine undispatchedCompletion$iv;
        Continuation continuation$iv = this.continuation;
        Object countOrElement$iv = this.countOrElement;
        CoroutineContext context$iv = continuation$iv.getContext();
        Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, countOrElement$iv);
        if (oldValue$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCompletion$iv = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv, context$iv, oldValue$iv);
        } else {
            undispatchedCompletion$iv = null;
        }
        try {
            this.continuation.resumeWith(result);
            Unit unit = Unit.INSTANCE;
        } finally {
            InlineMarker.finallyStart(1);
            if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final void dispatchYield$external__kotlinx_coroutines__android_common__kotlinx_coroutines(CoroutineContext context, T value) {
        Intrinsics.checkNotNullParameter(context, "context");
        this._state = value;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(context, this);
    }

    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStrings.toDebugString(this.continuation) + "]";
    }
}
