package kotlinx.coroutines.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a8\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00052!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00070\u000bH\u0082\b\u001a!\u0010\u000f\u001a\u0002H\u0010\"\u000e\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u0011*\u0002H\u0010H\u0000¢\u0006\u0002\u0010\u0012\u001a~\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u0016*\b\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u0002H\u001528\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u0001H\u0015¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u0002H\u00150\u001cH\u0080\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001e\u001am\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u0016*\u0002H\u00152\u0006\u0010\u0018\u001a\u00020\u001928\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u0001H\u0015¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u0002H\u00150\u001cH\u0082\bø\u0001\u0001¢\u0006\u0002\u0010 \u001a0\u0010!\u001a\u00020\u0007\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u0016*\b\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\"\u001a\u0002H\u0015H\u0082\b¢\u0006\u0002\u0010#\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006$"}, d2 = {"CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "getCLOSED$annotations", "()V", "POINTERS_SHIFT", "", "addConditionally", "", "Lkotlinx/atomicfu/AtomicInt;", "delta", "condition", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "cur", "close", "N", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "findSegmentAndMoveForward", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "Lkotlinx/atomicfu/AtomicRef;", "id", "", "startFrom", "createNewSegment", "Lkotlin/Function2;", "prev", "(Lkotlinx/atomicfu/AtomicRef;JLkotlinx/coroutines/internal/Segment;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "findSegmentInternal", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "moveForward", TypedValues.TransitionType.S_TO, "(Lkotlinx/atomicfu/AtomicRef;Lkotlinx/coroutines/internal/Segment;)Z", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ConcurrentLinkedListKt {
    private static final Symbol CLOSED = new Symbol("CLOSED");
    private static final int POINTERS_SHIFT = 16;

    public static final /* synthetic */ Symbol access$getCLOSED$p() {
        return CLOSED;
    }

    private static /* synthetic */ void getCLOSED$annotations() {
    }

    private static final <S extends Segment<S>> Object findSegmentInternal(S s, long id, Function2<? super Long, ? super S, ? extends S> function2) {
        Segment cur = s;
        while (true) {
            if (cur.getId() < id || cur.getRemoved()) {
                ConcurrentLinkedListNode this_$iv = cur;
                Object it$iv = this_$iv.getNextOrClosed();
                if (it$iv == CLOSED) {
                    return SegmentOrClosed.m2406constructorimpl(CLOSED);
                }
                Segment next = (Segment) ((ConcurrentLinkedListNode) it$iv);
                if (next != null) {
                    cur = next;
                } else {
                    Segment newTail = function2.invoke(Long.valueOf(cur.getId() + 1), cur);
                    if (cur.trySetNext(newTail)) {
                        if (cur.getRemoved()) {
                            cur.remove();
                        }
                        cur = newTail;
                    }
                }
            } else {
                return SegmentOrClosed.m2406constructorimpl(cur);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <S extends Segment<S>> boolean moveForward(AtomicRef<S> atomicRef, S s) {
        while (true) {
            Segment cur = (Segment) atomicRef.getValue();
            if (cur.getId() >= s.getId()) {
                return true;
            }
            if (!s.tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                return false;
            }
            if (atomicRef.compareAndSet(cur, s)) {
                if (cur.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                    cur.remove();
                }
                return true;
            }
            if (s.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                s.remove();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <S extends Segment<S>> Object findSegmentAndMoveForward(AtomicRef<S> atomicRef, long id, S startFrom, Function2<? super Long, ? super S, ? extends S> createNewSegment) {
        Object m2406constructorimpl;
        Object s;
        boolean z;
        Intrinsics.checkNotNullParameter(atomicRef, "<this>");
        Intrinsics.checkNotNullParameter(startFrom, "startFrom");
        Intrinsics.checkNotNullParameter(createNewSegment, "createNewSegment");
        do {
            Segment cur$iv = startFrom;
            while (true) {
                if (cur$iv.getId() < id || cur$iv.getRemoved()) {
                    ConcurrentLinkedListNode this_$iv$iv = cur$iv;
                    Object it$iv$iv = this_$iv$iv.getNextOrClosed();
                    if (it$iv$iv == CLOSED) {
                        m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(CLOSED);
                        break;
                    }
                    Segment next$iv = (Segment) ((ConcurrentLinkedListNode) it$iv$iv);
                    if (next$iv != null) {
                        cur$iv = next$iv;
                    } else {
                        S newTail$iv = createNewSegment.invoke(Long.valueOf(cur$iv.getId() + 1), cur$iv);
                        if (cur$iv.trySetNext(newTail$iv)) {
                            if (cur$iv.getRemoved()) {
                                cur$iv.remove();
                            }
                            cur$iv = newTail$iv;
                        }
                    }
                } else {
                    m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(cur$iv);
                    break;
                }
            }
            s = m2406constructorimpl;
            if (!SegmentOrClosed.m2411isClosedimpl(s)) {
                Segment to$iv = SegmentOrClosed.m2409getSegmentimpl(s);
                while (true) {
                    Segment cur$iv2 = (Segment) atomicRef.getValue();
                    z = true;
                    if (cur$iv2.getId() >= to$iv.getId()) {
                        break;
                    }
                    if (!to$iv.tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                        z = false;
                        break;
                    }
                    if (atomicRef.compareAndSet(cur$iv2, to$iv)) {
                        if (cur$iv2.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                            cur$iv2.remove();
                        }
                    } else if (to$iv.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                        to$iv.remove();
                    }
                }
            } else {
                break;
            }
        } while (!z);
        return s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    public static final <N extends ConcurrentLinkedListNode<N>> N close(N n) {
        Intrinsics.checkNotNullParameter(n, "<this>");
        N n2 = n;
        while (true) {
            ConcurrentLinkedListNode this_$iv = n2;
            Object it$iv = this_$iv.getNextOrClosed();
            if (it$iv == CLOSED) {
                return n2;
            }
            ?? r5 = (ConcurrentLinkedListNode) it$iv;
            if (r5 == 0) {
                if (n2.markAsClosed()) {
                    return n2;
                }
            } else {
                n2 = r5;
            }
        }
    }

    private static final boolean addConditionally(AtomicInt $this$addConditionally, int delta, Function1<? super Integer, Boolean> function1) {
        int cur;
        do {
            cur = $this$addConditionally.getValue();
            if (!function1.invoke(Integer.valueOf(cur)).booleanValue()) {
                return false;
            }
        } while (!$this$addConditionally.compareAndSet(cur, cur + delta));
        return true;
    }
}
