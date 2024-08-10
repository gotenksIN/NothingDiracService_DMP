package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecovery;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: CancellableContinuationImpl.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u001b\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u000fH\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/J;\u0010*\u001a\u00020+2'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+00j\u0002`32\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0017\u00104\u001a\u00020+2\f\u00105\u001a\b\u0012\u0004\u0012\u00020+06H\u0082\bJ1\u00107\u001a\u00020+2!\u00108\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+002\u0006\u0010.\u001a\u00020/J\u0012\u00109\u001a\u00020\u001a2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u001f\u0010:\u001a\u00020+2\b\u0010;\u001a\u0004\u0018\u00010\u000f2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\b<J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020\u000fH\u0016J\r\u0010@\u001a\u00020+H\u0000¢\u0006\u0002\bAJ\b\u0010B\u001a\u00020+H\u0002J\u0010\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\tH\u0002J\u0010\u0010E\u001a\u00020/2\u0006\u0010F\u001a\u00020GH\u0016J\u0019\u0010H\u001a\u0004\u0018\u00010/2\b\u0010 \u001a\u0004\u0018\u00010\u000fH\u0010¢\u0006\u0002\bIJ\n\u0010J\u001a\u0004\u0018\u00010\u000fH\u0001J\u0010\u0010K\u001a\n\u0018\u00010Lj\u0004\u0018\u0001`MH\u0016J\u001f\u0010N\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010 \u001a\u0004\u0018\u00010\u000fH\u0010¢\u0006\u0004\bO\u0010PJ\b\u0010Q\u001a\u00020+H\u0016J\n\u0010R\u001a\u0004\u0018\u00010\u001fH\u0002J1\u0010S\u001a\u00020+2'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+00j\u0002`3H\u0016J\b\u0010T\u001a\u00020\u001aH\u0002J1\u0010U\u001a\u00020-2'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+00j\u0002`3H\u0002J;\u0010V\u001a\u00020+2'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+00j\u0002`32\b\u0010 \u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010W\u001a\u00020$H\u0014J\u0015\u0010X\u001a\u00020+2\u0006\u0010.\u001a\u00020/H\u0000¢\u0006\u0002\bYJ\b\u0010Z\u001a\u00020+H\u0002J\b\u0010[\u001a\u00020\u001aH\u0001J:\u0010\\\u001a\u00020+2\u0006\u0010]\u001a\u00028\u00002#\u00108\u001a\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+\u0018\u000100H\u0016¢\u0006\u0002\u0010^JA\u0010_\u001a\u00020+2\b\u0010)\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\t2%\b\u0002\u00108\u001a\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+\u0018\u000100H\u0002J\u001e\u0010`\u001a\u00020+2\f\u0010a\u001a\b\u0012\u0004\u0012\u00028\u00000bH\u0016ø\u0001\u0000¢\u0006\u0002\u0010cJS\u0010d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010 \u001a\u00020e2\b\u0010)\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\t2#\u00108\u001a\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+\u0018\u0001002\b\u0010f\u001a\u0004\u0018\u00010\u000fH\u0002J\u000f\u0010g\u001a\u0004\u0018\u00010\u000fH\u0010¢\u0006\u0002\bhJ\b\u0010i\u001a\u00020$H\u0016J\b\u0010j\u001a\u00020\u001aH\u0002J!\u0010j\u001a\u0004\u0018\u00010\u000f2\u0006\u0010]\u001a\u00028\u00002\b\u0010f\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010kJF\u0010j\u001a\u0004\u0018\u00010\u000f2\u0006\u0010]\u001a\u00028\u00002\b\u0010f\u001a\u0004\u0018\u00010\u000f2#\u00108\u001a\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+\u0018\u000100H\u0016¢\u0006\u0002\u0010lJC\u0010m\u001a\u0004\u0018\u00010n2\b\u0010)\u001a\u0004\u0018\u00010\u000f2\b\u0010f\u001a\u0004\u0018\u00010\u000f2#\u00108\u001a\u001f\u0012\u0013\u0012\u00110/¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020+\u0018\u000100H\u0002J\u0012\u0010o\u001a\u0004\u0018\u00010\u000f2\u0006\u0010p\u001a\u00020/H\u0016J\b\u0010q\u001a\u00020\u001aH\u0002J\u0019\u0010r\u001a\u00020+*\u00020s2\u0006\u0010]\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010tJ\u0014\u0010u\u001a\u00020+*\u00020s2\u0006\u0010p\u001a\u00020/H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\u0004\u0018\u00010\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006v"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "delegate", "Lkotlin/coroutines/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/Continuation;I)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "parentHandle", "Lkotlinx/coroutines/DisposableHandle;", "state", "getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()Ljava/lang/Object;", "stateDebugRepresentation", "", "getStateDebugRepresentation", "()Ljava/lang/String;", "alreadyResumedError", "", "proposedUpdate", "callCancelHandler", "", "handler", "Lkotlinx/coroutines/CancelHandler;", "cause", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "callCancelHandlerSafely", "block", "Lkotlin/Function0;", "callOnCancellation", "onCancellation", "cancel", "cancelCompletedResult", "takenState", "cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "cancelLater", "completeResume", "token", "detachChild", "detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "detachChildIfNonResuable", "dispatchResume", "mode", "getContinuationCancellationCause", "parent", "Lkotlinx/coroutines/Job;", "getExceptionalResult", "getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "getResult", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getSuccessfulResult", "getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "(Ljava/lang/Object;)Ljava/lang/Object;", "initCancellability", "installParentHandle", "invokeOnCancellation", "isReusable", "makeCancelHandler", "multipleHandlersError", "nameString", "parentCancelled", "parentCancelled$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "releaseClaimedReusableContinuation", "resetStateReusable", "resume", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeImpl", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resumedState", "Lkotlinx/coroutines/NotCompleted;", "idempotent", "takeState", "takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "toString", "tryResume", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryResumeImpl", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeWithException", "exception", "trySuspend", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    private final AtomicInt _decision;
    private final AtomicRef<Object> _state;
    private final CoroutineContext context;
    private final Continuation<T> delegate;
    private DisposableHandle parentHandle;

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.delegate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(Continuation<? super T> delegate, int resumeMode) {
        super(resumeMode);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(resumeMode != -1)) {
                throw new AssertionError();
            }
        }
        this.context = delegate.get$context();
        this._decision = AtomicFU.atomic(0);
        this._state = AtomicFU.atomic(Active.INSTANCE);
    }

    @Override // kotlin.coroutines.Continuation
    /* renamed from: getContext, reason: from getter */
    public CoroutineContext get$context() {
        return this.context;
    }

    public final Object getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this._state.getValue();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof NotCompleted);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof CancelledContinuation;
    }

    private final String getStateDebugRepresentation() {
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        return state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof NotCompleted ? "Active" : state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof CancelledContinuation ? "Cancelled" : "Completed";
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void initCancellability() {
        DisposableHandle handle = installParentHandle();
        if (handle != null && isCompleted()) {
            handle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    private final boolean isReusable() {
        if (DispatchedTaskKt.isReusableMode(this.resumeMode)) {
            Continuation<T> continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).isReusable()) {
                return true;
            }
        }
        return false;
    }

    public final boolean resetStateReusable() {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((this.resumeMode == 2 ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((this.parentHandle != NonDisposableHandle.INSTANCE ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        Object state = this._state.getValue();
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state instanceof NotCompleted))) {
            throw new AssertionError();
        }
        if ((state instanceof CompletedContinuation) && ((CompletedContinuation) state).idempotentResume != null) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            return false;
        }
        this._decision.setValue(0);
        this._state.setValue(Active.INSTANCE);
        return true;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object takenState, Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (state instanceof CompletedExceptionally) {
                return;
            }
            if (state instanceof CompletedContinuation) {
                if (!(!((CompletedContinuation) state).getCancelled())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                CompletedContinuation update = CompletedContinuation.copy$default((CompletedContinuation) state, null, null, null, null, cause, 15, null);
                if (this._state.compareAndSet(state, update)) {
                    ((CompletedContinuation) state).invokeHandlers(this, cause);
                    return;
                }
            } else if (this._state.compareAndSet(state, new CompletedContinuation(state, null, null, null, cause, 14, null))) {
                return;
            }
        }
    }

    private final boolean cancelLater(Throwable cause) {
        if (!isReusable()) {
            return false;
        }
        Continuation<T> continuation = this.delegate;
        Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        DispatchedContinuation dispatched = (DispatchedContinuation) continuation;
        return dispatched.postponeCancellation(cause);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(Throwable cause) {
        Object state;
        CancelledContinuation update;
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (!(state instanceof NotCompleted)) {
                return false;
            }
            update = new CancelledContinuation(this, cause, state instanceof CancelHandler);
        } while (!this._state.compareAndSet(state, update));
        CancelHandler it = state instanceof CancelHandler ? (CancelHandler) state : null;
        if (it != null) {
            callCancelHandler(it, cause);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    public final void parentCancelled$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (cancelLater(cause)) {
            return;
        }
        cancel(cause);
        detachChildIfNonResuable();
    }

    private final void callCancelHandlerSafely(Function0<Unit> block) {
        try {
            block.invoke();
        } catch (Throwable ex) {
            CoroutineExceptionHandlerKt.handleCoroutineException(get$context(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, ex));
        }
    }

    private final void callCancelHandler(Function1<? super Throwable, Unit> handler, Throwable cause) {
        try {
            handler.invoke(cause);
        } catch (Throwable ex$iv) {
            CoroutineExceptionHandlerKt.handleCoroutineException(get$context(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, ex$iv));
        }
    }

    public final void callCancelHandler(CancelHandler handler, Throwable cause) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            handler.invoke(cause);
        } catch (Throwable ex$iv) {
            CoroutineExceptionHandlerKt.handleCoroutineException(get$context(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, ex$iv));
        }
    }

    public final void callOnCancellation(Function1<? super Throwable, Unit> onCancellation, Throwable cause) {
        Intrinsics.checkNotNullParameter(onCancellation, "onCancellation");
        Intrinsics.checkNotNullParameter(cause, "cause");
        try {
            onCancellation.invoke(cause);
        } catch (Throwable ex) {
            CoroutineExceptionHandlerKt.handleCoroutineException(get$context(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, ex));
        }
    }

    public Throwable getContinuationCancellationCause(Job parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return parent.getCancellationException();
    }

    private final boolean trySuspend() {
        AtomicInt $this$loop$iv = this._decision;
        do {
            int decision = $this$loop$iv.getValue();
            switch (decision) {
                case 0:
                    break;
                case 1:
                default:
                    throw new IllegalStateException("Already suspended".toString());
                case 2:
                    return false;
            }
        } while (!this._decision.compareAndSet(0, 1));
        return true;
    }

    private final boolean tryResume() {
        AtomicInt $this$loop$iv = this._decision;
        do {
            int decision = $this$loop$iv.getValue();
            switch (decision) {
                case 0:
                    break;
                case 1:
                    return false;
                default:
                    throw new IllegalStateException("Already resumed".toString());
            }
        } while (!this._decision.compareAndSet(0, 2));
        return true;
    }

    public final Object getResult() {
        Job job;
        boolean isReusable = isReusable();
        if (trySuspend()) {
            if (this.parentHandle == null) {
                installParentHandle();
            }
            if (isReusable) {
                releaseClaimedReusableContinuation();
            }
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation();
        }
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (!(state instanceof CompletedExceptionally)) {
            if (DispatchedTaskKt.isCancellableMode(this.resumeMode) && (job = (Job) get$context().get(Job.INSTANCE)) != null && !job.isActive()) {
                CancellationException cause = job.getCancellationException();
                cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(state, cause);
                if (!DebugKt.getRECOVER_STACK_TRACES() || !(this instanceof CoroutineStackFrame)) {
                    throw cause;
                }
                throw StackTraceRecovery.recoverFromStackFrame(cause, this);
            }
            return getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(state);
        }
        Throwable exception$iv = ((CompletedExceptionally) state).cause;
        if (DebugKt.getRECOVER_STACK_TRACES() && (this instanceof CoroutineStackFrame)) {
            throw StackTraceRecovery.recoverFromStackFrame(exception$iv, this);
        }
        throw exception$iv;
    }

    private final DisposableHandle installParentHandle() {
        Job parent = (Job) get$context().get(Job.INSTANCE);
        if (parent == null) {
            return null;
        }
        CompletionHandlerBase $this$asHandler$iv = new ChildContinuation(this);
        DisposableHandle handle = Job.DefaultImpls.invokeOnCompletion$default(parent, true, false, $this$asHandler$iv, 2, null);
        this.parentHandle = handle;
        return handle;
    }

    private final void releaseClaimedReusableContinuation() {
        Throwable cancellationCause;
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation == null || (cancellationCause = dispatchedContinuation.tryReleaseClaimedContinuation(this)) == null) {
            return;
        }
        detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        cancel(cancellationCause);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        resumeImpl$default(this, CompletionStateKt.toState(result, this), this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T value, Function1<? super Throwable, Unit> onCancellation) {
        resumeImpl(value, this.resumeMode, onCancellation);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        CancelHandler cancelHandler = makeCancelHandler(handler);
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state instanceof Active) {
                if (this._state.compareAndSet(state, cancelHandler)) {
                    return;
                }
            } else if (state instanceof CancelHandler) {
                multipleHandlersError(handler, state);
            } else {
                if (state instanceof CompletedExceptionally) {
                    if (!((CompletedExceptionally) state).makeHandled()) {
                        multipleHandlersError(handler, state);
                    }
                    if (state instanceof CancelledContinuation) {
                        CompletedExceptionally completedExceptionally = state instanceof CompletedExceptionally ? (CompletedExceptionally) state : null;
                        callCancelHandler(handler, completedExceptionally != null ? completedExceptionally.cause : null);
                        return;
                    }
                    return;
                }
                if (state instanceof CompletedContinuation) {
                    if (((CompletedContinuation) state).cancelHandler != null) {
                        multipleHandlersError(handler, state);
                    }
                    if (cancelHandler instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (((CompletedContinuation) state).getCancelled()) {
                        callCancelHandler(handler, ((CompletedContinuation) state).cancelCause);
                        return;
                    } else {
                        CompletedContinuation update = CompletedContinuation.copy$default((CompletedContinuation) state, null, cancelHandler, null, null, null, 29, null);
                        if (this._state.compareAndSet(state, update)) {
                            return;
                        }
                    }
                } else {
                    if (cancelHandler instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    CompletedContinuation update2 = new CompletedContinuation(state, cancelHandler, null, null, null, 28, null);
                    if (this._state.compareAndSet(state, update2)) {
                        return;
                    }
                }
            }
        }
    }

    private final void multipleHandlersError(Function1<? super Throwable, Unit> handler, Object state) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + handler + ", already has " + state).toString());
    }

    private final CancelHandler makeCancelHandler(Function1<? super Throwable, Unit> handler) {
        return handler instanceof CancelHandler ? (CancelHandler) handler : new InvokeOnCancel(handler);
    }

    private final void dispatchResume(int mode) {
        if (tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, mode);
    }

    private final Object resumedState(NotCompleted state, Object proposedUpdate, int resumeMode, Function1<? super Throwable, Unit> onCancellation, Object idempotent) {
        if (proposedUpdate instanceof CompletedExceptionally) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if ((idempotent == null ? 1 : 0) == 0) {
                    throw new AssertionError();
                }
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(onCancellation == null)) {
                    throw new AssertionError();
                }
            }
        } else if ((DispatchedTaskKt.isCancellableMode(resumeMode) || idempotent != null) && (onCancellation != null || (((state instanceof CancelHandler) && !(state instanceof BeforeResumeCancelHandler)) || idempotent != null))) {
            return new CompletedContinuation(proposedUpdate, state instanceof CancelHandler ? (CancelHandler) state : null, onCancellation, idempotent, null, 16, null);
        }
        return proposedUpdate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void resumeImpl$default(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        cancellableContinuationImpl.resumeImpl(obj, i, function1);
    }

    private final void resumeImpl(Object proposedUpdate, int resumeMode, Function1<? super Throwable, Unit> onCancellation) {
        Object state;
        Object update;
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (state instanceof NotCompleted) {
                update = resumedState((NotCompleted) state, proposedUpdate, resumeMode, onCancellation, null);
            } else {
                if ((state instanceof CancelledContinuation) && ((CancelledContinuation) state).makeResumed()) {
                    if (onCancellation != null) {
                        callOnCancellation(onCancellation, ((CancelledContinuation) state).cause);
                        return;
                    }
                    return;
                }
                alreadyResumedError(proposedUpdate);
                throw new ExceptionsH();
            }
        } while (!this._state.compareAndSet(state, update));
        detachChildIfNonResuable();
        dispatchResume(resumeMode);
    }

    private final Symbol tryResumeImpl(Object proposedUpdate, Object idempotent, Function1<? super Throwable, Unit> onCancellation) {
        Object state;
        Object update;
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (state instanceof NotCompleted) {
                update = resumedState((NotCompleted) state, proposedUpdate, this.resumeMode, onCancellation, idempotent);
            } else {
                if (!(state instanceof CompletedContinuation) || idempotent == null || ((CompletedContinuation) state).idempotentResume != idempotent) {
                    return null;
                }
                if (!DebugKt.getASSERTIONS_ENABLED() || Intrinsics.areEqual(((CompletedContinuation) state).result, proposedUpdate)) {
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
                throw new AssertionError();
            }
        } while (!this._state.compareAndSet(state, update));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    private final Void alreadyResumedError(Object proposedUpdate) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + proposedUpdate).toString());
    }

    private final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
    }

    public final void detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        DisposableHandle handle = this.parentHandle;
        if (handle == null) {
            return;
        }
        handle.dispose();
        this.parentHandle = NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T value, Object idempotent) {
        return tryResumeImpl(value, idempotent, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T value, Object idempotent, Function1<? super Throwable, Unit> onCancellation) {
        return tryResumeImpl(value, idempotent, onCancellation);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResumeWithException(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return tryResumeImpl(new CompletedExceptionally(exception, false, 2, null), null, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object token) {
        Intrinsics.checkNotNullParameter(token, "token");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        dispatchResume(this.resumeMode);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(CoroutineDispatcher $this$resumeUndispatched, T t) {
        Intrinsics.checkNotNullParameter($this$resumeUndispatched, "<this>");
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dc = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl$default(this, t, (dc != null ? dc.dispatcher : null) == $this$resumeUndispatched ? 4 : this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(CoroutineDispatcher $this$resumeUndispatchedWithException, Throwable exception) {
        Intrinsics.checkNotNullParameter($this$resumeUndispatchedWithException, "<this>");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dc = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl$default(this, new CompletedExceptionally(exception, false, 2, null), (dc != null ? dc.dispatcher : null) == $this$resumeUndispatchedWithException ? 4 : this.resumeMode, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object state) {
        return state instanceof CompletedContinuation ? (T) ((CompletedContinuation) state).result : state;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object state) {
        Throwable it = super.getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(state);
        if (it == null) {
            return null;
        }
        Continuation continuation$iv = this.delegate;
        if (DebugKt.getRECOVER_STACK_TRACES() && (continuation$iv instanceof CoroutineStackFrame)) {
            return StackTraceRecovery.recoverFromStackFrame(it, (CoroutineStackFrame) continuation$iv);
        }
        return it;
    }

    public String toString() {
        return nameString() + "(" + DebugStrings.toDebugString(this.delegate) + "){" + getStateDebugRepresentation() + "}@" + DebugStrings.getHexAddress(this);
    }

    protected String nameString() {
        return "CancellableContinuation";
    }
}
