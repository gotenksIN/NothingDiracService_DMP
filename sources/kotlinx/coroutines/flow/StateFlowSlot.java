package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StateFlow.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016J\u0011\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ'\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f0\u000e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\bR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "Lkotlinx/coroutines/flow/StateFlowImpl;", "()V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "allocateLocked", "", "flow", "awaitPending", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "freeLocked", "", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {
    private final AtomicRef<Object> _state = AtomicFU.atomic((Object) null);

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(StateFlowImpl<?> flow) {
        Symbol symbol;
        Intrinsics.checkNotNullParameter(flow, "flow");
        if (this._state.getValue() != null) {
            return false;
        }
        AtomicRef<Object> atomicRef = this._state;
        symbol = StateFlowKt.NONE;
        atomicRef.setValue(symbol);
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public Continuation<Unit>[] freeLocked(StateFlowImpl<?> flow) {
        Intrinsics.checkNotNullParameter(flow, "flow");
        this._state.setValue(null);
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void makePending() {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state == null) {
                return;
            }
            symbol = StateFlowKt.PENDING;
            if (state == symbol) {
                return;
            }
            symbol2 = StateFlowKt.NONE;
            if (state == symbol2) {
                AtomicRef<Object> atomicRef = this._state;
                symbol3 = StateFlowKt.PENDING;
                if (atomicRef.compareAndSet(state, symbol3)) {
                    return;
                }
            } else {
                AtomicRef<Object> atomicRef2 = this._state;
                symbol4 = StateFlowKt.NONE;
                if (atomicRef2.compareAndSet(state, symbol4)) {
                    Result.Companion companion = Result.INSTANCE;
                    ((CancellableContinuationImpl) state).resumeWith(Result.m856constructorimpl(Unit.INSTANCE));
                    return;
                }
            }
        }
    }

    public final boolean takePending() {
        Symbol symbol;
        Symbol symbol2;
        AtomicRef<Object> atomicRef = this._state;
        symbol = StateFlowKt.NONE;
        Object state = atomicRef.getAndSet(symbol);
        Intrinsics.checkNotNull(state);
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state instanceof CancellableContinuationImpl))) {
            throw new AssertionError();
        }
        symbol2 = StateFlowKt.PENDING;
        return state == symbol2;
    }

    public final Object awaitPending(Continuation<? super Unit> continuation) {
        Symbol symbol;
        Symbol symbol2;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv;
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(this._state.getValue() instanceof CancellableContinuationImpl))) {
            throw new AssertionError();
        }
        AtomicRef atomicRef = this._state;
        symbol = StateFlowKt.NONE;
        if (!atomicRef.compareAndSet(symbol, cancellableContinuationImpl)) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                Object value = this._state.getValue();
                symbol2 = StateFlowKt.PENDING;
                if (!(value == symbol2)) {
                    throw new AssertionError();
                }
            }
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m856constructorimpl(Unit.INSTANCE));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }
}
