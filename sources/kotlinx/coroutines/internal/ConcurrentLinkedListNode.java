package kotlinx.coroutines.internal;

import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\tJ!\u0010\u001b\u001a\u0004\u0018\u00018\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u0019J\u0013\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00028\u0000¢\u0006\u0002\u0010#R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00018\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0003\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0012\u0010\u0014\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\nR\u0014\u0010\u0016\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "", "prev", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "isTail", "", "()Z", "leftmostAliveNode", "getLeftmostAliveNode", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "next", "getNext", "nextOrClosed", "getNextOrClosed", "()Ljava/lang/Object;", "getPrev", "removed", "getRemoved", "rightmostAliveNode", "getRightmostAliveNode", "cleanPrev", "", "markAsClosed", "nextOrIfClosed", "onClosedAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "remove", "trySetNext", "value", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    private final AtomicRef<Object> _next = AtomicFU.atomic((Object) null);
    private final AtomicRef<N> _prev;

    public abstract boolean getRemoved();

    public ConcurrentLinkedListNode(N n) {
        this._prev = AtomicFU.atomic(n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getNextOrClosed() {
        return this._next.getValue();
    }

    public final N nextOrIfClosed(Function0 onClosedAction) {
        Intrinsics.checkNotNullParameter(onClosedAction, "onClosedAction");
        Object it = getNextOrClosed();
        if (it == ConcurrentLinkedListKt.access$getCLOSED$p()) {
            onClosedAction.invoke();
            throw new ExceptionsH();
        }
        return (N) it;
    }

    public final N getNext() {
        Object it$iv = getNextOrClosed();
        if (it$iv == ConcurrentLinkedListKt.access$getCLOSED$p()) {
            return null;
        }
        return (N) it$iv;
    }

    public final boolean trySetNext(N value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return this._next.compareAndSet(null, value);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    public final N getPrev() {
        return this._prev.getValue();
    }

    public final void cleanPrev() {
        this._prev.lazySet(null);
    }

    public final boolean markAsClosed() {
        return this._next.compareAndSet(null, ConcurrentLinkedListKt.access$getCLOSED$p());
    }

    public final void remove() {
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!isTail())) {
            throw new AssertionError();
        }
        while (true) {
            N leftmostAliveNode = getLeftmostAliveNode();
            ConcurrentLinkedListNode next = getRightmostAliveNode();
            next._prev.setValue(leftmostAliveNode);
            if (leftmostAliveNode != null) {
                leftmostAliveNode._next.setValue(next);
            }
            if (!next.getRemoved() && (leftmostAliveNode == null || !leftmostAliveNode.getRemoved())) {
                return;
            }
        }
    }

    private final N getLeftmostAliveNode() {
        ConcurrentLinkedListNode prev = getPrev();
        while (prev != null && prev.getRemoved()) {
            ConcurrentLinkedListNode cur = prev._prev.getValue();
            prev = cur;
        }
        return prev;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode, java.lang.Object] */
    private final N getRightmostAliveNode() {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!isTail())) {
            throw new AssertionError();
        }
        N next = getNext();
        Intrinsics.checkNotNull(next);
        while (next.getRemoved()) {
            ?? next2 = next.getNext();
            Intrinsics.checkNotNull(next2);
            next = next2;
        }
        return next;
    }
}
