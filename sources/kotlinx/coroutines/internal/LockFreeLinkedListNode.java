package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStrings;

/* compiled from: LockFreeLinkedList.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0017\u0018\u00002\u00020\u0001:\u0005?@ABCB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000fJ(\u0010\u0017\u001a\u00020\t2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\u000e\b\u0004\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0019H\u0086\bø\u0001\u0000J0\u0010\u001a\u001a\u00020\t2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\u0016\u0010\u001b\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u000f\u0012\u0004\u0012\u00020\t0\u001cH\u0086\bø\u0001\u0000J@\u0010\u001d\u001a\u00020\t2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\u0016\u0010\u001b\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u000f\u0012\u0004\u0012\u00020\t0\u001c2\u000e\b\u0004\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0019H\u0086\bø\u0001\u0000J \u0010\u001e\u001a\u00020\t2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0001J\u0012\u0010\u001f\u001a\u00020\t2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000fJ\u001b\u0010 \u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0082\u0010J'\u0010#\u001a\b\u0012\u0004\u0012\u0002H%0$\"\f\b\u0000\u0010%*\u00060\u0000j\u0002`\u000f2\u0006\u0010\u0016\u001a\u0002H%¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\f\u0012\b\u0012\u00060\u0000j\u0002`\u000f0(J\u0019\u0010)\u001a\u00060\u0000j\u0002`\u000f2\n\u0010*\u001a\u00060\u0000j\u0002`\u000fH\u0082\u0010J\u0014\u0010+\u001a\u00020\u00152\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0002J\u0006\u0010,\u001a\u00020\u0015J\b\u0010-\u001a\u00020\u0015H\u0001J(\u0010.\u001a\u00020/2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\u000e\b\u0004\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0019H\u0081\bø\u0001\u0000J\u0010\u00100\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000fH\u0014J\b\u00101\u001a\u00020\tH\u0016J/\u00102\u001a\u0004\u0018\u0001H%\"\u0006\b\u0000\u0010%\u0018\u00012\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\t0\u001cH\u0086\bø\u0001\u0000¢\u0006\u0002\u00103J\u000e\u00104\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000fJ\u0010\u00105\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000fH\u0001J\b\u00106\u001a\u00020\u0007H\u0002J\b\u00107\u001a\u000208H\u0016J(\u00109\u001a\u00020:2\n\u0010\u0016\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000f2\u0006\u0010;\u001a\u00020/H\u0001J%\u0010<\u001a\u00020\u00152\n\u0010=\u001a\u00060\u0000j\u0002`\u000f2\n\u0010\u000b\u001a\u00060\u0000j\u0002`\u000fH\u0000¢\u0006\u0002\b>R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00060\u0000j\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0012\u001a\u00060\u0000j\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006D"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "", "()V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "_removedRef", "Lkotlinx/coroutines/internal/Removed;", "isRemoved", "", "()Z", "next", "getNext", "()Ljava/lang/Object;", "nextNode", "Lkotlinx/coroutines/internal/Node;", "getNextNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "prevNode", "getPrevNode", "addLast", "", "node", "addLastIf", "condition", "Lkotlin/Function0;", "addLastIfPrev", "predicate", "Lkotlin/Function1;", "addLastIfPrevAndIf", "addNext", "addOneIfEmpty", "correctPrev", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "describeAddLast", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeRemoveFirst", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "findPrevNonRemoved", "current", "finishAdd", "helpRemove", "helpRemovePrev", "makeCondAddOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "nextIfRemoved", "remove", "removeFirstIfIsInstanceOfOrPeekIf", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "removeFirstOrNull", "removeOrNext", "removed", "toString", "", "tryCondAddNext", "", "condAdd", "validateNode", "prev", "validateNode$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "AbstractAtomicDesc", "AddLastDesc", "CondAddOp", "PrepareOp", "RemoveFirstDesc", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class LockFreeLinkedListNode {
    private final AtomicRef<Object> _next = AtomicFU.atomic(this);
    private final AtomicRef<LockFreeLinkedListNode> _prev = AtomicFU.atomic(this);
    private final AtomicRef<Removed> _removedRef = AtomicFU.atomic((Object) null);

    /* JADX INFO: Access modifiers changed from: private */
    public final Removed removed() {
        Removed value = this._removedRef.getValue();
        if (value != null) {
            return value;
        }
        Removed it = new Removed(this);
        this._removedRef.lazySet(it);
        return it;
    }

    /* compiled from: LockFreeLinkedList.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b!\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0011\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0002j\u0002`\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0014\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "newNode", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "oldNext", "complete", "", "affected", "failure", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode newNode) {
            Intrinsics.checkNotNullParameter(newNode, "newNode");
            this.newNode = newNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(LockFreeLinkedListNode affected, Object failure) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            boolean success = failure == null;
            LockFreeLinkedListNode update = success ? this.newNode : this.oldNext;
            if (update != null && affected._next.compareAndSet(this, update) && success) {
                LockFreeLinkedListNode lockFreeLinkedListNode = this.newNode;
                LockFreeLinkedListNode lockFreeLinkedListNode2 = this.oldNext;
                Intrinsics.checkNotNull(lockFreeLinkedListNode2);
                lockFreeLinkedListNode.finishAdd(lockFreeLinkedListNode2);
            }
        }
    }

    public final CondAddOp makeCondAddOp(LockFreeLinkedListNode node, Function0<Boolean> condition) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(condition, "condition");
        return new LockFreeLinkedListNode$makeCondAddOp$1(node, condition);
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public final Object getNext() {
        AtomicRef $this$loop$iv = this._next;
        while (true) {
            Object next = $this$loop$iv.getValue();
            if (!(next instanceof OpDescriptor)) {
                return next;
            }
            ((OpDescriptor) next).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        return LockFreeLinkedListKt.unwrap(getNext());
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev = correctPrev(null);
        return correctPrev == null ? findPrevNonRemoved(this._prev.getValue()) : correctPrev;
    }

    private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode current) {
        while (current.isRemoved()) {
            current = current._prev.getValue();
        }
        return current;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0014 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean addOneIfEmpty(kotlinx.coroutines.internal.LockFreeLinkedListNode r3) {
        /*
            r2 = this;
            java.lang.String r0 = "node"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            kotlinx.atomicfu.AtomicRef<kotlinx.coroutines.internal.LockFreeLinkedListNode> r0 = r3._prev
            r0.lazySet(r2)
            kotlinx.atomicfu.AtomicRef<java.lang.Object> r0 = r3._next
            r0.lazySet(r2)
        Lf:
            java.lang.Object r0 = r2.getNext()
            if (r0 == r2) goto L18
            r1 = 0
            return r1
        L18:
            kotlinx.atomicfu.AtomicRef<java.lang.Object> r1 = r2._next
            boolean r1 = r1.compareAndSet(r2, r3)
            if (r1 == 0) goto Lf
            r3.finishAdd(r2)
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.addOneIfEmpty(kotlinx.coroutines.internal.LockFreeLinkedListNode):boolean");
    }

    public final void addLast(LockFreeLinkedListNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        do {
        } while (!getPrevNode().addNext(node, this));
    }

    public final <T extends LockFreeLinkedListNode> AddLastDesc<T> describeAddLast(T node) {
        Intrinsics.checkNotNullParameter(node, "node");
        return new AddLastDesc<>(this, node);
    }

    public final boolean addLastIf(LockFreeLinkedListNode node, Function0<Boolean> condition) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(condition, "condition");
        CondAddOp condAdd = new LockFreeLinkedListNode$makeCondAddOp$1(node, condition);
        while (true) {
            LockFreeLinkedListNode prev = getPrevNode();
            switch (prev.tryCondAddNext(node, this, condAdd)) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }
    }

    public final boolean addLastIfPrev(LockFreeLinkedListNode node, Function1<? super LockFreeLinkedListNode, Boolean> predicate) {
        LockFreeLinkedListNode prev;
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        do {
            prev = getPrevNode();
            if (!predicate.invoke(prev).booleanValue()) {
                return false;
            }
        } while (!prev.addNext(node, this));
        return true;
    }

    public final boolean addLastIfPrevAndIf(LockFreeLinkedListNode node, Function1<? super LockFreeLinkedListNode, Boolean> predicate, Function0<Boolean> condition) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.checkNotNullParameter(condition, "condition");
        CondAddOp condAdd = new LockFreeLinkedListNode$makeCondAddOp$1(node, condition);
        while (true) {
            LockFreeLinkedListNode prev = getPrevNode();
            if (!predicate.invoke(prev).booleanValue()) {
                return false;
            }
            switch (prev.tryCondAddNext(node, this, condAdd)) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }
    }

    public final boolean addNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(next, "next");
        node._prev.lazySet(this);
        node._next.lazySet(next);
        if (!this._next.compareAndSet(next, node)) {
            return false;
        }
        node.finishAdd(next);
        return true;
    }

    public final int tryCondAddNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next, CondAddOp condAdd) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(next, "next");
        Intrinsics.checkNotNullParameter(condAdd, "condAdd");
        node._prev.lazySet(this);
        node._next.lazySet(next);
        condAdd.oldNext = next;
        if (this._next.compareAndSet(next, condAdd)) {
            return condAdd.perform(this) == null ? 1 : 2;
        }
        return 0;
    }

    /* renamed from: remove */
    public boolean mo2402remove() {
        return removeOrNext() == null;
    }

    public final LockFreeLinkedListNode removeOrNext() {
        Object next;
        Removed removed;
        do {
            next = getNext();
            if (next instanceof Removed) {
                return ((Removed) next).ref;
            }
            if (next == this) {
                return (LockFreeLinkedListNode) next;
            }
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            removed = ((LockFreeLinkedListNode) next).removed();
        } while (!this._next.compareAndSet(next, removed));
        ((LockFreeLinkedListNode) next).correctPrev(null);
        return null;
    }

    public final void helpRemove() {
        Object next = getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
        ((Removed) next).ref.helpRemovePrev();
    }

    public final void helpRemovePrev() {
        LockFreeLinkedListNode node = this;
        while (true) {
            Object next = node.getNext();
            if (next instanceof Removed) {
                node = ((Removed) next).ref;
            } else {
                node.correctPrev(null);
                return;
            }
        }
    }

    public final LockFreeLinkedListNode removeFirstOrNull() {
        while (true) {
            Object next = getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            LockFreeLinkedListNode first = (LockFreeLinkedListNode) next;
            if (first == this) {
                return null;
            }
            if (first.mo2402remove()) {
                return first;
            }
            first.helpRemove();
        }
    }

    public final RemoveFirstDesc<LockFreeLinkedListNode> describeRemoveFirst() {
        return new RemoveFirstDesc<>(this);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object, kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    public final /* synthetic */ <T> T removeFirstIfIsInstanceOfOrPeekIf(Function1<? super T, Boolean> predicate) {
        LockFreeLinkedListNode removeOrNext;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        while (true) {
            Object next = getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            if (lockFreeLinkedListNode == this) {
                return null;
            }
            Intrinsics.reifiedOperationMarker(3, "T");
            if (!(lockFreeLinkedListNode instanceof Object)) {
                return null;
            }
            if ((predicate.invoke(lockFreeLinkedListNode).booleanValue() && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                return lockFreeLinkedListNode;
            }
            removeOrNext.helpRemovePrev();
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u00020\u0004B\u0019\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003H\u0014J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0013\u001a\u00020\u0019H\u0014J\u0018\u0010\u001a\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0004J \u0010\u001d\u001a\u00020\u00192\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003H\u0016R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\u0006\u001a\u00028\u00008\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0018\u0010\u000e\u001a\u00060\u0002j\u0002`\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0005\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "node", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "finishOnSuccess", "", "affected", "next", "finishPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "retry", "", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static class AddLastDesc<T extends LockFreeLinkedListNode> extends AbstractAtomicDesc {
        private final AtomicRef<LockFreeLinkedListNode> _affectedNode;
        public final T node;
        public final LockFreeLinkedListNode queue;

        public AddLastDesc(LockFreeLinkedListNode queue, T node) {
            Intrinsics.checkNotNullParameter(queue, "queue");
            Intrinsics.checkNotNullParameter(node, "node");
            this.queue = queue;
            this.node = node;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(((LockFreeLinkedListNode) node)._next.getValue() == node && ((LockFreeLinkedListNode) node)._prev.getValue() == node)) {
                    throw new AssertionError();
                }
            }
            this._affectedNode = AtomicFU.atomic((Object) null);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode takeAffectedNode(OpDescriptor op) {
            Intrinsics.checkNotNullParameter(op, "op");
            return this.queue.correctPrev(op);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode getAffectedNode() {
            return this._affectedNode.getValue();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* renamed from: getOriginalNext, reason: from getter */
        protected final LockFreeLinkedListNode getQueue() {
            return this.queue;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected boolean retry(LockFreeLinkedListNode affected, Object next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            return next != this.queue;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void finishPrepare(PrepareOp prepareOp) {
            Intrinsics.checkNotNullParameter(prepareOp, "prepareOp");
            this._affectedNode.compareAndSet(null, prepareOp.affected);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            ((LockFreeLinkedListNode) this.node)._prev.compareAndSet(this.node, affected);
            ((LockFreeLinkedListNode) this.node)._next.compareAndSet(this.node, this.queue);
            return this.node;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            this.node.finishAdd(this.queue);
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u0005H\u0014J \u0010\u0017\u001a\u00020\u00182\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0019\u001a\u00060\u0004j\u0002`\u0005H\u0004J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0019\u001a\u00020\u0015H\u0004J\u0018\u0010\u001f\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00052\u0006\u0010 \u001a\u00020!H\u0004J\u001e\u0010\"\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0019\u001a\u00060\u0004j\u0002`\u0005R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0003\u001a\u00060\u0004j\u0002`\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "_originalNext", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "result", "getResult$annotations", "()V", "getResult", "()Ljava/lang/Object;", "failure", "", "affected", "finishOnSuccess", "", "next", "finishPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "retry", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static class RemoveFirstDesc<T> extends AbstractAtomicDesc {
        private final AtomicRef<LockFreeLinkedListNode> _affectedNode;
        private final AtomicRef<LockFreeLinkedListNode> _originalNext;
        public final LockFreeLinkedListNode queue;

        public static /* synthetic */ void getResult$annotations() {
        }

        public RemoveFirstDesc(LockFreeLinkedListNode queue) {
            Intrinsics.checkNotNullParameter(queue, "queue");
            this.queue = queue;
            this._affectedNode = AtomicFU.atomic((Object) null);
            this._originalNext = AtomicFU.atomic((Object) null);
        }

        public final T getResult() {
            Object affectedNode = getAffectedNode();
            Intrinsics.checkNotNull(affectedNode);
            return (T) affectedNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode takeAffectedNode(OpDescriptor op) {
            Intrinsics.checkNotNullParameter(op, "op");
            AtomicRef $this$loop$iv = this.queue._next;
            while (true) {
                Object next = $this$loop$iv.getValue();
                if (next instanceof OpDescriptor) {
                    if (op.isEarlierThan((OpDescriptor) next)) {
                        return null;
                    }
                    ((OpDescriptor) next).perform(this.queue);
                } else {
                    Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    return (LockFreeLinkedListNode) next;
                }
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode getAffectedNode() {
            return this._affectedNode.getValue();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* renamed from: getOriginalNext */
        protected final LockFreeLinkedListNode getQueue() {
            return this._originalNext.getValue();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (affected == this.queue) {
                return LockFreeLinkedListKt.getLIST_EMPTY();
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final boolean retry(LockFreeLinkedListNode affected, Object next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            if (!(next instanceof Removed)) {
                return false;
            }
            ((Removed) next).ref.helpRemovePrev();
            return true;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void finishPrepare(PrepareOp prepareOp) {
            Intrinsics.checkNotNullParameter(prepareOp, "prepareOp");
            this._affectedNode.compareAndSet(null, prepareOp.affected);
            this._originalNext.compareAndSet(null, prepareOp.next);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            return next.removed();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            next.correctPrev(null);
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0002\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "next", "desc", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;)V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "finishPrepare", "", "perform", "", "toString", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class PrepareOp extends OpDescriptor {
        public final LockFreeLinkedListNode affected;
        public final AbstractAtomicDesc desc;
        public final LockFreeLinkedListNode next;

        public PrepareOp(LockFreeLinkedListNode affected, LockFreeLinkedListNode next, AbstractAtomicDesc desc) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.affected = affected;
            this.next = next;
            this.desc = desc;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp<?> getAtomicOp() {
            return this.desc.getAtomicOp();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object perform(Object affected) {
            Object consensus;
            Object update;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(affected == this.affected)) {
                    throw new AssertionError();
                }
            }
            Intrinsics.checkNotNull(affected, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            Object decision = this.desc.onPrepare(this);
            if (decision == LockFreeLinkedList_commonKt.REMOVE_PREPARED) {
                LockFreeLinkedListNode next = this.next;
                Removed removed = next.removed();
                if (((LockFreeLinkedListNode) affected)._next.compareAndSet(this, removed)) {
                    this.desc.onRemoved((LockFreeLinkedListNode) affected);
                    next.correctPrev(null);
                }
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (decision != null) {
                consensus = getAtomicOp().decide(decision);
            } else {
                consensus = getAtomicOp().getConsensus();
            }
            if (consensus == AtomicKt.NO_DECISION) {
                update = getAtomicOp();
            } else {
                update = consensus == null ? this.desc.updatedNext((LockFreeLinkedListNode) affected, this.next) : this.next;
            }
            ((LockFreeLinkedListNode) affected)._next.compareAndSet(this, update);
            return null;
        }

        public final void finishPrepare() {
            this.desc.finishPrepare(this);
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            return "PrepareOp(op=" + getAtomicOp() + ")";
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u0005H\u0014J \u0010\u0011\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0012\u001a\u00060\u0004j\u0002`\u0005H$J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u0005H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0018\u0010\u001b\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00052\u0006\u0010\f\u001a\u00020\u001cH\u0014J \u0010\u001d\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0012\u001a\u00060\u0004j\u0002`\u0005H&R\u001a\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "()V", "affectedNode", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "getAffectedNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "complete", "", "op", "Lkotlinx/coroutines/internal/AtomicOp;", "failure", "", "affected", "finishOnSuccess", "next", "finishPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "onPrepare", "onRemoved", "prepare", "retry", "", "takeAffectedNode", "Lkotlinx/coroutines/internal/OpDescriptor;", "updatedNext", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static abstract class AbstractAtomicDesc extends AtomicDesc {
        protected abstract void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next);

        public abstract void finishPrepare(PrepareOp prepareOp);

        protected abstract LockFreeLinkedListNode getAffectedNode();

        /* renamed from: getOriginalNext */
        protected abstract LockFreeLinkedListNode getQueue();

        public abstract Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next);

        protected LockFreeLinkedListNode takeAffectedNode(OpDescriptor op) {
            Intrinsics.checkNotNullParameter(op, "op");
            LockFreeLinkedListNode affectedNode = getAffectedNode();
            Intrinsics.checkNotNull(affectedNode);
            return affectedNode;
        }

        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            return null;
        }

        protected boolean retry(LockFreeLinkedListNode affected, Object next) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            Intrinsics.checkNotNullParameter(next, "next");
            return false;
        }

        public Object onPrepare(PrepareOp prepareOp) {
            Intrinsics.checkNotNullParameter(prepareOp, "prepareOp");
            finishPrepare(prepareOp);
            return null;
        }

        public void onRemoved(LockFreeLinkedListNode affected) {
            Intrinsics.checkNotNullParameter(affected, "affected");
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x006d, code lost:            if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L39;     */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:            if (r5 != null) goto L34;     */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:            r7 = true;     */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:            if (r7 == false) goto L37;     */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x007d, code lost:            throw new java.lang.AssertionError();     */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:            r7 = false;     */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007e, code lost:            return null;     */
        @Override // kotlinx.coroutines.internal.AtomicDesc
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object prepare(kotlinx.coroutines.internal.AtomicOp<?> r9) {
            /*
                r8 = this;
                java.lang.String r0 = "op"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            L5:
                r0 = r9
                kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
                kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = r8.takeAffectedNode(r0)
                if (r0 != 0) goto L12
                java.lang.Object r0 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
                return r0
            L12:
                kotlinx.atomicfu.AtomicRef r1 = kotlinx.coroutines.internal.LockFreeLinkedListNode.access$get_next$p(r0)
                java.lang.Object r1 = r1.getValue()
                r2 = 0
                if (r1 != r9) goto L1e
                return r2
            L1e:
                boolean r3 = r9.isDecided()
                if (r3 == 0) goto L25
                return r2
            L25:
                boolean r3 = r1 instanceof kotlinx.coroutines.internal.OpDescriptor
                if (r3 == 0) goto L3c
                r2 = r1
                kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
                boolean r2 = r9.isEarlierThan(r2)
                if (r2 == 0) goto L35
                java.lang.Object r2 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
                return r2
            L35:
                r2 = r1
                kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
                r2.perform(r0)
                goto L5
            L3c:
                java.lang.Object r3 = r8.failure(r0)
                if (r3 == 0) goto L43
                return r3
            L43:
                boolean r4 = r8.retry(r0, r1)
                if (r4 != 0) goto L5
                kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp r4 = new kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp
                java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r5)
                r5 = r1
                kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
                r4.<init>(r0, r5, r8)
                kotlinx.atomicfu.AtomicRef r5 = kotlinx.coroutines.internal.LockFreeLinkedListNode.access$get_next$p(r0)
                boolean r5 = r5.compareAndSet(r1, r4)
                if (r5 == 0) goto L5
            L61:
                java.lang.Object r5 = r4.perform(r0)     // Catch: java.lang.Throwable -> L7f
                java.lang.Object r6 = kotlinx.coroutines.internal.LockFreeLinkedList_commonKt.REMOVE_PREPARED     // Catch: java.lang.Throwable -> L7f
                if (r5 == r6) goto L5
                boolean r6 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch: java.lang.Throwable -> L7f
                if (r6 == 0) goto L7e
                r6 = 0
                if (r5 != 0) goto L74
                r7 = 1
                goto L75
            L74:
                r7 = 0
            L75:
                if (r7 == 0) goto L78
                goto L7e
            L78:
                java.lang.AssertionError r2 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L7f
                r2.<init>()     // Catch: java.lang.Throwable -> L7f
                throw r2     // Catch: java.lang.Throwable -> L7f
            L7e:
                return r2
            L7f:
                r2 = move-exception
                kotlinx.atomicfu.AtomicRef r5 = kotlinx.coroutines.internal.LockFreeLinkedListNode.access$get_next$p(r0)
                r5.compareAndSet(r4, r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc.prepare(kotlinx.coroutines.internal.AtomicOp):java.lang.Object");
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public final void complete(AtomicOp<?> op, Object failure) {
            Intrinsics.checkNotNullParameter(op, "op");
            boolean success = failure == null;
            LockFreeLinkedListNode affectedNode = getAffectedNode();
            if (affectedNode == null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(success ? false : true)) {
                        throw new AssertionError();
                    }
                    return;
                }
                return;
            }
            LockFreeLinkedListNode queue = getQueue();
            if (queue == null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(success ? false : true)) {
                        throw new AssertionError();
                    }
                    return;
                }
                return;
            }
            Object update = success ? updatedNext(affectedNode, queue) : queue;
            if (affectedNode._next.compareAndSet(op, update) && success) {
                finishOnSuccess(affectedNode, queue);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAdd(LockFreeLinkedListNode next) {
        LockFreeLinkedListNode nextPrev;
        AtomicRef $this$loop$iv = next._prev;
        do {
            nextPrev = $this$loop$iv.getValue();
            if (getNext() != next) {
                return;
            }
        } while (!next._prev.compareAndSet(nextPrev, this));
        if (isRemoved()) {
            next.correctPrev(null);
        }
    }

    protected LockFreeLinkedListNode nextIfRemoved() {
        Object next = getNext();
        Removed removed = next instanceof Removed ? (Removed) next : null;
        if (removed != null) {
            return removed.ref;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LockFreeLinkedListNode correctPrev(OpDescriptor op) {
        while (true) {
            LockFreeLinkedListNode oldPrev = this._prev.getValue();
            LockFreeLinkedListNode prev = oldPrev;
            LockFreeLinkedListNode last = null;
            while (true) {
                Object prevNext = prev._next.getValue();
                if (prevNext == this) {
                    if (oldPrev == prev) {
                        return prev;
                    }
                    if (this._prev.compareAndSet(oldPrev, prev)) {
                        return prev;
                    }
                } else {
                    if (isRemoved()) {
                        return null;
                    }
                    if (prevNext == op) {
                        return prev;
                    }
                    if (prevNext instanceof OpDescriptor) {
                        if (op != null && op.isEarlierThan((OpDescriptor) prevNext)) {
                            return null;
                        }
                        ((OpDescriptor) prevNext).perform(prev);
                    } else if (prevNext instanceof Removed) {
                        if (last != null) {
                            if (!last._next.compareAndSet(prev, ((Removed) prevNext).ref)) {
                                break;
                            }
                            prev = last;
                            last = null;
                        } else {
                            prev = prev._prev.getValue();
                        }
                    } else {
                        last = prev;
                        Intrinsics.checkNotNull(prevNext, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                        prev = (LockFreeLinkedListNode) prevNext;
                    }
                }
            }
        }
    }

    public final void validateNode$external__kotlinx_coroutines__android_common__kotlinx_coroutines(LockFreeLinkedListNode prev, LockFreeLinkedListNode next) {
        Intrinsics.checkNotNullParameter(prev, "prev");
        Intrinsics.checkNotNullParameter(next, "next");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((prev == this._prev.getValue() ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(next == this._next.getValue())) {
                throw new AssertionError();
            }
        }
    }

    public String toString() {
        return new PropertyReference0Impl(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return DebugStrings.getClassSimpleName(this.receiver);
            }
        } + "@" + DebugStrings.getHexAddress(this);
    }
}
