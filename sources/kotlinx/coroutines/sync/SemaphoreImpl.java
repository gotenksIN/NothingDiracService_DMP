package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancelHandlerBase;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\u0016\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0014H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\u0012\u0010 \u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00140\u001cH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "permits", "", "acquiredPermits", "(II)V", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "enqIdx", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "onCancellationRelease", "Lkotlin/Function1;", "", "", "tail", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "addAcquireToQueue", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "release", "tryAcquire", "tryResumeNextFromQueue", "tryResumeAcquire", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SemaphoreImpl implements Semaphore {
    private final AtomicInt _availablePermits;
    private final AtomicLong deqIdx = AtomicFU.atomic(0L);
    private final AtomicLong enqIdx = AtomicFU.atomic(0L);
    private final AtomicRef<SemaphoreSegment> head;
    private final Function1<Throwable, Unit> onCancellationRelease;
    private final int permits;
    private final AtomicRef<SemaphoreSegment> tail;

    public SemaphoreImpl(int permits, int acquiredPermits) {
        this.permits = permits;
        if (!(permits > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + permits).toString());
        }
        if (!(acquiredPermits >= 0 && acquiredPermits <= permits)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + permits).toString());
        }
        SemaphoreSegment s = new SemaphoreSegment(0L, null, 2);
        this.head = AtomicFU.atomic(s);
        this.tail = AtomicFU.atomic(s);
        this._availablePermits = AtomicFU.atomic(permits - acquiredPermits);
        this.onCancellationRelease = new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "<anonymous parameter 0>");
                SemaphoreImpl.this.release();
            }
        };
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits.getValue(), 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int p;
        AtomicInt $this$loop$iv = this._availablePermits;
        do {
            p = $this$loop$iv.getValue();
            if (p <= 0) {
                return false;
            }
        } while (!this._availablePermits.compareAndSet(p, p - 1));
        return true;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation<? super Unit> continuation) {
        Object acquireSlowPath;
        int p = this._availablePermits.getAndDecrement();
        return (p <= 0 && (acquireSlowPath = acquireSlowPath(continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? acquireSlowPath : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cont = cancellable$iv;
        while (true) {
            if (addAcquireToQueue(cont)) {
                break;
            }
            int p = this._availablePermits.getAndDecrement();
            if (p > 0) {
                cont.resume(Unit.INSTANCE, this.onCancellationRelease);
                break;
            }
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        int cur$iv;
        int upd$iv;
        do {
            AtomicInt $this$getAndUpdate$iv = this._availablePermits;
            do {
                cur$iv = $this$getAndUpdate$iv.getValue();
                int i = this.permits;
                if (!(cur$iv < i)) {
                    throw new IllegalStateException(("The number of released permits cannot be greater than " + i).toString());
                }
                upd$iv = cur$iv + 1;
            } while (!$this$getAndUpdate$iv.compareAndSet(cur$iv, upd$iv));
            if (cur$iv >= 0) {
                return;
            }
        } while (!tryResumeNextFromQueue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cont) {
        int i;
        SemaphoreSegment curTail;
        Object m2406constructorimpl;
        Segment createSegment;
        Object s$iv;
        int i2;
        Object expected$iv;
        Object value$iv;
        Symbol symbol;
        Segment to$iv$iv;
        SemaphoreSegment s$iv2 = this.tail.getValue();
        long enqIdx = this.enqIdx.getAndIncrement();
        AtomicRef $this$findSegmentAndMoveForward$iv = this.tail;
        i = SemaphoreKt.SEGMENT_SIZE;
        long id$iv = enqIdx / i;
        while (true) {
            SemaphoreSegment $this$findSegmentInternal$iv$iv = s$iv2;
            Segment cur$iv$iv = $this$findSegmentInternal$iv$iv;
            while (true) {
                if (cur$iv$iv.getId() < id$iv || cur$iv$iv.getRemoved()) {
                    ConcurrentLinkedListNode this_$iv$iv$iv = cur$iv$iv;
                    Object it$iv$iv$iv = this_$iv$iv$iv.getNextOrClosed();
                    curTail = s$iv2;
                    if (it$iv$iv$iv == ConcurrentLinkedListKt.CLOSED) {
                        m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    Segment next$iv$iv = (Segment) ((ConcurrentLinkedListNode) it$iv$iv$iv);
                    if (next$iv$iv != null) {
                        cur$iv$iv = next$iv$iv;
                        s$iv2 = curTail;
                    } else {
                        long p0 = cur$iv$iv.getId() + 1;
                        SemaphoreSegment p1 = (SemaphoreSegment) cur$iv$iv;
                        createSegment = SemaphoreKt.createSegment(p0, p1);
                        Segment newTail$iv$iv = createSegment;
                        if (!cur$iv$iv.trySetNext(newTail$iv$iv)) {
                            s$iv2 = curTail;
                        } else {
                            if (cur$iv$iv.getRemoved()) {
                                cur$iv$iv.remove();
                            }
                            cur$iv$iv = newTail$iv$iv;
                            s$iv2 = curTail;
                        }
                    }
                } else {
                    m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(cur$iv$iv);
                    curTail = s$iv2;
                    break;
                }
            }
            s$iv = m2406constructorimpl;
            if (SegmentOrClosed.m2411isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv2 = SegmentOrClosed.m2409getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv2 = (Segment) $this$findSegmentAndMoveForward$iv.getValue();
                if (cur$iv$iv2.getId() >= to$iv$iv2.getId()) {
                    to$iv$iv = 1;
                    break;
                }
                if (!to$iv$iv2.tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                    to$iv$iv = null;
                    break;
                }
                if ($this$findSegmentAndMoveForward$iv.compareAndSet(cur$iv$iv2, to$iv$iv2)) {
                    if (cur$iv$iv2.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                        cur$iv$iv2.remove();
                    }
                    to$iv$iv = 1;
                } else if (to$iv$iv2.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                    to$iv$iv2.remove();
                }
            }
            if (to$iv$iv != null) {
                break;
            }
            s$iv2 = curTail;
        }
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m2409getSegmentimpl(s$iv);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        int i3 = (int) (enqIdx % i2);
        if (!segment.getAcquirers().get(i3).compareAndSet(null, cont)) {
            expected$iv = SemaphoreKt.PERMIT;
            value$iv = SemaphoreKt.TAKEN;
            if (segment.getAcquirers().get(i3).compareAndSet(expected$iv, value$iv)) {
                cont.resume(Unit.INSTANCE, this.onCancellationRelease);
                return true;
            }
            if (!DebugKt.getASSERTIONS_ENABLED()) {
                return false;
            }
            Object value = segment.getAcquirers().get(i3).getValue();
            symbol = SemaphoreKt.BROKEN;
            if (value == symbol) {
                return false;
            }
            throw new AssertionError();
        }
        CancelHandlerBase $this$asHandler$iv = new CancelSemaphoreAcquisitionHandler(segment, i3);
        cont.invokeOnCancellation($this$asHandler$iv);
        return true;
    }

    private final boolean tryResumeNextFromQueue() {
        int i;
        Object m2406constructorimpl;
        Segment createSegment;
        Object s$iv;
        int i2;
        int i3;
        Object value$iv;
        Symbol symbol;
        int i4;
        Object expected$iv;
        Object value$iv2;
        Symbol symbol2;
        boolean z;
        Segment curHead = (SemaphoreSegment) this.head.getValue();
        long deqIdx = this.deqIdx.getAndIncrement();
        i = SemaphoreKt.SEGMENT_SIZE;
        long id = deqIdx / i;
        AtomicRef $this$findSegmentAndMoveForward$iv = this.head;
        do {
            Segment $this$findSegmentInternal$iv$iv = curHead;
            Segment cur$iv$iv = $this$findSegmentInternal$iv$iv;
            while (true) {
                if (cur$iv$iv.getId() < id || cur$iv$iv.getRemoved()) {
                    ConcurrentLinkedListNode this_$iv$iv$iv = cur$iv$iv;
                    Object it$iv$iv$iv = this_$iv$iv$iv.getNextOrClosed();
                    if (it$iv$iv$iv == ConcurrentLinkedListKt.CLOSED) {
                        m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    Segment next$iv$iv = (Segment) ((ConcurrentLinkedListNode) it$iv$iv$iv);
                    if (next$iv$iv != null) {
                        cur$iv$iv = next$iv$iv;
                    } else {
                        long p0 = cur$iv$iv.getId() + 1;
                        SemaphoreSegment p1 = (SemaphoreSegment) cur$iv$iv;
                        createSegment = SemaphoreKt.createSegment(p0, p1);
                        Segment newTail$iv$iv = createSegment;
                        if (cur$iv$iv.trySetNext(newTail$iv$iv)) {
                            if (cur$iv$iv.getRemoved()) {
                                cur$iv$iv.remove();
                            }
                            cur$iv$iv = newTail$iv$iv;
                        }
                    }
                } else {
                    m2406constructorimpl = SegmentOrClosed.m2406constructorimpl(cur$iv$iv);
                    break;
                }
            }
            s$iv = m2406constructorimpl;
            if (SegmentOrClosed.m2411isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m2409getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv2 = (Segment) $this$findSegmentAndMoveForward$iv.getValue();
                if (cur$iv$iv2.getId() >= to$iv$iv.getId()) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                    z = false;
                    break;
                }
                if ($this$findSegmentAndMoveForward$iv.compareAndSet(cur$iv$iv2, to$iv$iv)) {
                    if (cur$iv$iv2.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                        cur$iv$iv2.remove();
                    }
                    z = true;
                } else if (to$iv$iv.decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                    to$iv$iv.remove();
                }
            }
        } while (!z);
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m2409getSegmentimpl(s$iv);
        segment.cleanPrev();
        if (segment.getId() > id) {
            return false;
        }
        i3 = SemaphoreKt.SEGMENT_SIZE;
        int i5 = (int) (deqIdx % i3);
        value$iv = SemaphoreKt.PERMIT;
        Object value$iv3 = segment.getAcquirers().get(i5).getAndSet(value$iv);
        if (value$iv3 == null) {
            i4 = SemaphoreKt.MAX_SPIN_CYCLES;
            for (i2 = 0; i2 < i4; i2++) {
                Object value = segment.getAcquirers().get(i5).getValue();
                symbol2 = SemaphoreKt.TAKEN;
                if (value == symbol2) {
                    return true;
                }
            }
            expected$iv = SemaphoreKt.PERMIT;
            value$iv2 = SemaphoreKt.BROKEN;
            return !segment.getAcquirers().get(i5).compareAndSet(expected$iv, value$iv2);
        }
        symbol = SemaphoreKt.CANCELLED;
        if (value$iv3 == symbol) {
            return false;
        }
        return tryResumeAcquire((CancellableContinuation) value$iv3);
    }

    private final boolean tryResumeAcquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object token = cancellableContinuation.tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
        if (token == null) {
            return false;
        }
        cancellableContinuation.completeResume(token);
        return true;
    }
}
