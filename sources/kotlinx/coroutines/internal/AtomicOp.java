package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;

/* compiled from: Atomic.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001f\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u001dR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/internal/AtomicOp;", "T", "Lkotlinx/coroutines/internal/OpDescriptor;", "()V", "_consensus", "Lkotlinx/atomicfu/AtomicRef;", "", "atomicOp", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "consensus", "getConsensus", "()Ljava/lang/Object;", "isDecided", "", "()Z", "opSequence", "", "getOpSequence", "()J", "complete", "", "affected", "failure", "(Ljava/lang/Object;Ljava/lang/Object;)V", "decide", "decision", "perform", "prepare", "(Ljava/lang/Object;)Ljava/lang/Object;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class AtomicOp<T> extends OpDescriptor {
    private final AtomicRef<Object> _consensus = AtomicFU.atomic(AtomicKt.NO_DECISION);

    public abstract void complete(T affected, Object failure);

    public abstract Object prepare(T affected);

    public final Object getConsensus() {
        return this._consensus.getValue();
    }

    public final boolean isDecided() {
        return this._consensus.getValue() != AtomicKt.NO_DECISION;
    }

    public long getOpSequence() {
        return 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp<?> getAtomicOp() {
        return this;
    }

    public final Object decide(Object decision) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(decision != AtomicKt.NO_DECISION)) {
                throw new AssertionError();
            }
        }
        Object current = this._consensus.getValue();
        return current != AtomicKt.NO_DECISION ? current : this._consensus.compareAndSet(AtomicKt.NO_DECISION, decision) ? decision : this._consensus.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object affected) {
        Object decision = this._consensus.getValue();
        if (decision == AtomicKt.NO_DECISION) {
            decision = decide(prepare(affected));
        }
        complete(affected, decision);
        return decision;
    }
}
