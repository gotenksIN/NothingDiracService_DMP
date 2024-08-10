package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicFU_commonKt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;

/* compiled from: LockFreeTaskQueue.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 ,*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002,-B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J \u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001c\u001a\u00020\u0006J1\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n2\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u0006J&\u0010!\u001a\b\u0012\u0004\u0012\u0002H#0\"\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H#0%J\b\u0010&\u001a\u00020\u001aH\u0002J\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\b\u0010(\u001a\u0004\u0018\u00010\u0002J,\u0010)\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n2\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0002R(\u0010\b\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006."}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "E", "", "capacity", "", "singleConsumer", "", "(IZ)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/Core;", "_state", "Lkotlinx/atomicfu/AtomicLong;", "array", "Lkotlinx/atomicfu/AtomicArray;", "isEmpty", "()Z", "mask", "size", "getSize", "()I", "addLast", "element", "(Ljava/lang/Object;)I", "allocateNextCopy", "state", "", "allocateOrGetNextCopy", "close", "fillPlaceholder", "index", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "isClosed", "map", "", "R", "transform", "Lkotlin/Function1;", "markFrozen", "next", "removeFirstOrNull", "removeSlowPath", "oldHead", "newHead", "Companion", "Placeholder", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    public static final int CAPACITY_BITS = 30;
    public static final long CLOSED_MASK = 2305843009213693952L;
    public static final int CLOSED_SHIFT = 61;
    public static final long FROZEN_MASK = 1152921504606846976L;
    public static final int FROZEN_SHIFT = 60;
    public static final long HEAD_MASK = 1073741823;
    public static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    public static final int MAX_CAPACITY_MASK = 1073741823;
    public static final int MIN_ADD_SPIN_CAPACITY = 1024;
    public static final long TAIL_MASK = 1152921503533105152L;
    public static final int TAIL_SHIFT = 30;
    private final AtomicRef<LockFreeTaskQueueCore<E>> _next = AtomicFU.atomic((Object) null);
    private final AtomicLong _state = AtomicFU.atomic(0L);
    private final AtomicArray<Object> array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");

    public LockFreeTaskQueueCore(int capacity, boolean singleConsumer) {
        this.capacity = capacity;
        this.singleConsumer = singleConsumer;
        int i = capacity - 1;
        this.mask = i;
        this.array = AtomicFU_commonKt.atomicArrayOfNulls(capacity);
        if (!(i <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if ((i & capacity) == 0) {
        } else {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public final boolean isEmpty() {
        Companion companion = INSTANCE;
        long $this$withState$iv = this._state.getValue();
        int head$iv = (int) ((HEAD_MASK & $this$withState$iv) >> 0);
        int tail$iv = (int) ((TAIL_MASK & $this$withState$iv) >> 30);
        return head$iv == tail$iv;
    }

    public final int getSize() {
        Companion companion = INSTANCE;
        long $this$withState$iv = this._state.getValue();
        int head$iv = (int) ((HEAD_MASK & $this$withState$iv) >> 0);
        int tail$iv = (int) ((TAIL_MASK & $this$withState$iv) >> 30);
        int head = (tail$iv - head$iv) & MAX_CAPACITY_MASK;
        return head;
    }

    public final boolean close() {
        long cur$iv;
        long upd$iv;
        AtomicLong $this$update$iv = this._state;
        do {
            cur$iv = $this$update$iv.getValue();
            if ((cur$iv & CLOSED_MASK) != 0) {
                return true;
            }
            if ((FROZEN_MASK & cur$iv) != 0) {
                return false;
            }
            upd$iv = cur$iv | CLOSED_MASK;
        } while (!$this$update$iv.compareAndSet(cur$iv, upd$iv));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0073, code lost:            return 1;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int addLast(E r27) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            java.lang.String r2 = "element"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            kotlinx.atomicfu.AtomicLong r2 = r0._state
            r3 = 0
        Lc:
            long r4 = r2.getValue()
            r6 = 0
            r7 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r7 = r7 & r4
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L22
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r7 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.INSTANCE
            int r7 = r7.addFailReason(r4)
            return r7
        L22:
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r7 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.INSTANCE
            r11 = r4
            r8 = 0
            r13 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r13 = r13 & r11
            r15 = 0
            long r13 = r13 >> r15
            int r13 = (int) r13
            r16 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r16 = r11 & r16
            r14 = 30
            long r9 = r16 >> r14
            int r9 = (int) r9
            r10 = r13
            r14 = r9
            r16 = 0
            int r15 = r0.mask
            int r20 = r14 + 2
            r21 = r2
            r2 = r20 & r15
            r20 = r3
            r3 = r10 & r15
            r22 = 1
            if (r2 != r3) goto L4e
            return r22
        L4e:
            boolean r2 = r0.singleConsumer
            if (r2 != 0) goto L74
            kotlinx.atomicfu.AtomicArray<java.lang.Object> r2 = r0.array
            r3 = r14 & r15
            kotlinx.atomicfu.AtomicRef r2 = r2.get(r3)
            java.lang.Object r2 = r2.getValue()
            if (r2 == 0) goto L74
            int r2 = r0.capacity
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 < r3) goto L73
            int r3 = r14 - r10
            r17 = 1073741823(0x3fffffff, float:1.9999999)
            r3 = r3 & r17
            int r2 = r2 >> 1
            if (r3 <= r2) goto L72
            goto L73
        L72:
            goto Lbc
        L73:
            return r22
        L74:
            int r2 = r14 + 1
            r3 = 1073741823(0x3fffffff, float:1.9999999)
            r2 = r2 & r3
            kotlinx.atomicfu.AtomicLong r3 = r0._state
            r22 = r6
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r6 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.INSTANCE
            r23 = r7
            long r6 = r6.updateTail(r4, r2)
            boolean r3 = r3.compareAndSet(r4, r6)
            if (r3 == 0) goto Lb9
            kotlinx.atomicfu.AtomicArray<java.lang.Object> r3 = r0.array
            r6 = r14 & r15
            kotlinx.atomicfu.AtomicRef r3 = r3.get(r6)
            r3.setValue(r1)
            r3 = r26
        L99:
            kotlinx.atomicfu.AtomicLong r6 = r3._state
            long r6 = r6.getValue()
            r24 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r6 = r6 & r24
            r18 = 0
            int r6 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r6 == 0) goto Lb7
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r6 = r3.next()
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r6 = r6.fillPlaceholder(r14, r1)
            if (r6 != 0) goto Lb5
            goto Lb7
        Lb5:
            r3 = r6
            goto L99
        Lb7:
            r6 = 0
            return r6
        Lb9:
        Lbc:
            r3 = r20
            r2 = r21
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeTaskQueueCore.addLast(java.lang.Object):int");
    }

    private final LockFreeTaskQueueCore<E> fillPlaceholder(int index, E element) {
        Object old = this.array.get(this.mask & index).getValue();
        if ((old instanceof Placeholder) && ((Placeholder) old).index == index) {
            this.array.get(this.mask & index).setValue(element);
            return this;
        }
        return null;
    }

    public final Object removeFirstOrNull() {
        AtomicLong $this$loop$iv = this._state;
        int $i$f$loop = 0;
        while (true) {
            long state = $this$loop$iv.getValue();
            if ((FROZEN_MASK & state) != 0) {
                return REMOVE_FROZEN;
            }
            Companion companion = INSTANCE;
            int head$iv = (int) ((HEAD_MASK & state) >> 0);
            int tail$iv = (int) ((TAIL_MASK & state) >> 30);
            int i = this.mask;
            AtomicLong $this$loop$iv2 = $this$loop$iv;
            int $i$f$loop2 = $i$f$loop;
            int $i$f$loop3 = head$iv & i;
            if ((tail$iv & i) == $i$f$loop3) {
                return null;
            }
            Object element = this.array.get(head$iv & i).getValue();
            if (element == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else {
                if (element instanceof Placeholder) {
                    return null;
                }
                int newHead = (head$iv + 1) & MAX_CAPACITY_MASK;
                if (!this._state.compareAndSet(state, INSTANCE.updateHead(state, newHead))) {
                    if (this.singleConsumer) {
                        LockFreeTaskQueueCore cur = this;
                        while (true) {
                            LockFreeTaskQueueCore removeSlowPath = cur.removeSlowPath(head$iv, newHead);
                            if (removeSlowPath == null) {
                                return element;
                            }
                            cur = removeSlowPath;
                        }
                    }
                } else {
                    this.array.get(this.mask & head$iv).setValue(null);
                    return element;
                }
            }
            $this$loop$iv = $this$loop$iv2;
            $i$f$loop = $i$f$loop2;
        }
    }

    private final LockFreeTaskQueueCore<E> removeSlowPath(int oldHead, int newHead) {
        AtomicLong $this$loop$iv = this._state;
        int $i$f$loop = 0;
        while (true) {
            long state = $this$loop$iv.getValue();
            Companion companion = INSTANCE;
            int head$iv = (int) ((HEAD_MASK & state) >> 0);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(head$iv == oldHead)) {
                    throw new AssertionError();
                }
            }
            if ((FROZEN_MASK & state) != 0) {
                return next();
            }
            AtomicLong $this$loop$iv2 = $this$loop$iv;
            int $i$f$loop2 = $i$f$loop;
            if (!this._state.compareAndSet(state, INSTANCE.updateHead(state, newHead))) {
                $this$loop$iv = $this$loop$iv2;
                $i$f$loop = $i$f$loop2;
            } else {
                this.array.get(this.mask & head$iv).setValue(null);
                return null;
            }
        }
    }

    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    private final long markFrozen() {
        long cur$iv;
        long upd$iv;
        AtomicLong $this$updateAndGet$iv = this._state;
        do {
            cur$iv = $this$updateAndGet$iv.getValue();
            if ((cur$iv & FROZEN_MASK) == 0) {
                upd$iv = cur$iv | FROZEN_MASK;
            } else {
                return cur$iv;
            }
        } while (!$this$updateAndGet$iv.compareAndSet(cur$iv, upd$iv));
        return upd$iv;
    }

    private final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long state) {
        AtomicRef $this$loop$iv = this._next;
        while (true) {
            LockFreeTaskQueueCore<E> next = $this$loop$iv.getValue();
            if (next != null) {
                return next;
            }
            this._next.compareAndSet(null, allocateNextCopy(state));
        }
    }

    private final LockFreeTaskQueueCore<E> allocateNextCopy(long state) {
        LockFreeTaskQueueCore next = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
        Companion companion = INSTANCE;
        int head$iv = (int) ((HEAD_MASK & state) >> 0);
        int tail$iv = (int) ((TAIL_MASK & state) >> 30);
        int index = head$iv;
        while (true) {
            int i = this.mask;
            if ((index & i) != (tail$iv & i)) {
                Object value = this.array.get(i & index).getValue();
                if (value == null) {
                    value = new Placeholder(index);
                }
                next.array.get(next.mask & index).setValue(value);
                index++;
            } else {
                next._state.setValue(INSTANCE.wo(state, FROZEN_MASK));
                return next;
            }
        }
    }

    public final <R> List<R> map(Function1<? super E, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList res = new ArrayList(this.capacity);
        Companion companion = INSTANCE;
        long $this$withState$iv = this._state.getValue();
        int head$iv = (int) ((HEAD_MASK & $this$withState$iv) >> 0);
        int tail$iv = (int) ((TAIL_MASK & $this$withState$iv) >> 30);
        int index = head$iv;
        while (true) {
            int i = this.mask;
            if ((index & i) != (tail$iv & i)) {
                Object element = this.array.get(i & index).getValue();
                if (element != null && !(element instanceof Placeholder)) {
                    res.add(transform.invoke(element));
                }
                index++;
            } else {
                return res;
            }
        }
    }

    public final boolean isClosed() {
        return (this._state.getValue() & CLOSED_MASK) != 0;
    }

    /* compiled from: LockFreeTaskQueue.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "index", "", "(I)V", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Placeholder {
        public final int index;

        public Placeholder(int index) {
            this.index = index;
        }
    }

    /* compiled from: LockFreeTaskQueue.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u00020\u0004*\u00020\tJ\u0012\u0010\u0017\u001a\u00020\t*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0004J\u0012\u0010\u0019\u001a\u00020\t*\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0004JS\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0001\u0010\u001c*\u00020\t26\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010#J\u0015\u0010$\u001a\u00020\t*\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0086\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006&"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "MIN_ADD_SPIN_CAPACITY", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long wo(long $this$wo, long other) {
            return (~other) & $this$wo;
        }

        public final long updateHead(long $this$updateHead, int newHead) {
            return wo($this$updateHead, LockFreeTaskQueueCore.HEAD_MASK) | (newHead << 0);
        }

        public final long updateTail(long $this$updateTail, int newTail) {
            return wo($this$updateTail, LockFreeTaskQueueCore.TAIL_MASK) | (newTail << 30);
        }

        public final <T> T withState(long $this$withState, Function2<? super Integer, ? super Integer, ? extends T> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            int head = (int) ((LockFreeTaskQueueCore.HEAD_MASK & $this$withState) >> 0);
            int tail = (int) ((LockFreeTaskQueueCore.TAIL_MASK & $this$withState) >> 30);
            return block.invoke(Integer.valueOf(head), Integer.valueOf(tail));
        }

        public final int addFailReason(long $this$addFailReason) {
            return (LockFreeTaskQueueCore.CLOSED_MASK & $this$addFailReason) != 0 ? 2 : 1;
        }
    }
}
