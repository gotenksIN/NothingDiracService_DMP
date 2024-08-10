package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: ArrayChannel.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012 \u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n¢\u0006\u0002\u0010\u000bJ\u001d\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u00162\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000(H\u0014J\u0012\u0010)\u001a\u0004\u0018\u00010\u000e2\u0006\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0015\u0010-\u001a\u00020\u000e2\u0006\u0010$\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010.J!\u0010/\u001a\u00020\u000e2\u0006\u0010$\u001a\u00028\u00002\n\u00100\u001a\u0006\u0012\u0002\b\u000301H\u0014¢\u0006\u0002\u00102J\u0010\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\u0016H\u0014J\n\u00105\u001a\u0004\u0018\u00010\u000eH\u0014J\u0016\u00106\u001a\u0004\u0018\u00010\u000e2\n\u00100\u001a\u0006\u0012\u0002\b\u000301H\u0014J\u0012\u00107\u001a\u0004\u0018\u0001082\u0006\u0010#\u001a\u00020\u0004H\u0002R\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00168DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0017R\u0014\u0010\u001b\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0017R\u0012\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "head", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "size", "Lkotlinx/atomicfu/AtomicInt;", "enqueueElement", "currentSize", "element", "(ILjava/lang/Object;)V", "enqueueReceiveInternal", "receive", "Lkotlinx/coroutines/channels/Receive;", "enqueueSend", "send", "Lkotlinx/coroutines/channels/Send;", "ensureCapacity", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotent", "wasClosed", "pollInternal", "pollSelectInternal", "updateBufferSize", "Lkotlinx/coroutines/internal/Symbol;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private final BufferOverflow onBufferOverflow;
    private final AtomicInt size;

    /* compiled from: ArrayChannel.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            try {
                iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayChannel(int capacity, BufferOverflow onBufferOverflow, Function1<? super E, Unit> function1) {
        super(function1);
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        this.capacity = capacity;
        this.onBufferOverflow = onBufferOverflow;
        if (!(capacity >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + capacity + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        Object[] $this$buffer_u24lambda_u241 = new Object[Math.min(capacity, 8)];
        ArraysKt.fill$default($this$buffer_u24lambda_u241, AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
        this.buffer = $this$buffer_u24lambda_u241;
        this.size = AtomicFU.atomic(0);
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.size.getValue() == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size.getValue() == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:            if (r5 == 0) goto L13;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:            r6 = takeFirstReceiveOrPeekClosed();     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:            if (r6 != null) goto L16;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:            if ((r6 instanceof kotlinx.coroutines.channels.Closed) == false) goto L21;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:            kotlin.jvm.internal.Intrinsics.checkNotNull(r6);        r6 = r6.tryResumeReceive(r10, null);     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004a, code lost:            if (r6 == null) goto L46;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:            if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:            if (r6 != kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN) goto L28;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:            r8 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:            if (r8 == false) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:            throw new java.lang.AssertionError();     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0059, code lost:            r8 = false;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0063, code lost:            r9.size.setValue(r5);        r4 = kotlin.Unit.INSTANCE;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006b, code lost:            r3.unlock();        r6.completeResumeReceive(r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0075, code lost:            return r6.getOfferResult();     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0038, code lost:            r9.size.setValue(r5);     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0041, code lost:            return r6;     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0076, code lost:            enqueueElement(r5, r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007e, code lost:            return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object offerInternal(E r10) {
        /*
            r9 = this;
            r0 = 0
            java.util.concurrent.locks.ReentrantLock r1 = r9.lock
            r2 = 0
            r3 = r1
            java.util.concurrent.locks.Lock r3 = (java.util.concurrent.locks.Lock) r3
            r3.lock()
            r4 = 0
            kotlinx.atomicfu.AtomicInt r5 = r9.size     // Catch: java.lang.Throwable -> L7f
            int r5 = r5.getValue()     // Catch: java.lang.Throwable -> L7f
            kotlinx.coroutines.channels.Closed r6 = r9.getClosedForSend()     // Catch: java.lang.Throwable -> L7f
            if (r6 == 0) goto L1d
            r7 = 0
            r3.unlock()
            return r6
        L1d:
            kotlinx.coroutines.internal.Symbol r6 = r9.updateBufferSize(r5)     // Catch: java.lang.Throwable -> L7f
            if (r6 == 0) goto L29
            r7 = 0
            r3.unlock()
            return r6
        L29:
            if (r5 != 0) goto L76
        L2b:
        L2c:
            kotlinx.coroutines.channels.ReceiveOrClosed r6 = r9.takeFirstReceiveOrPeekClosed()     // Catch: java.lang.Throwable -> L7f
            if (r6 != 0) goto L33
            goto L76
        L33:
            r0 = r6
            boolean r6 = r0 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L7f
            if (r6 == 0) goto L42
            kotlinx.atomicfu.AtomicInt r6 = r9.size     // Catch: java.lang.Throwable -> L7f
            r6.setValue(r5)     // Catch: java.lang.Throwable -> L7f
            r3.unlock()
            return r0
        L42:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L7f
            r6 = 0
            kotlinx.coroutines.internal.Symbol r6 = r0.tryResumeReceive(r10, r6)     // Catch: java.lang.Throwable -> L7f
            if (r6 == 0) goto L2b
            boolean r7 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch: java.lang.Throwable -> L7f
            if (r7 == 0) goto L63
            r7 = 0
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN     // Catch: java.lang.Throwable -> L7f
            if (r6 != r8) goto L59
            r8 = 1
            goto L5a
        L59:
            r8 = 0
        L5a:
            if (r8 == 0) goto L5d
            goto L63
        L5d:
            java.lang.AssertionError r7 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L7f
            r7.<init>()     // Catch: java.lang.Throwable -> L7f
            throw r7     // Catch: java.lang.Throwable -> L7f
        L63:
            kotlinx.atomicfu.AtomicInt r7 = r9.size     // Catch: java.lang.Throwable -> L7f
            r7.setValue(r5)     // Catch: java.lang.Throwable -> L7f
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L7f
            r3.unlock()
            r0.completeResumeReceive(r10)
            java.lang.Object r1 = r0.getOfferResult()
            return r1
        L76:
            r9.enqueueElement(r5, r10)     // Catch: java.lang.Throwable -> L7f
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L7f
            r3.unlock()
            return r6
        L7f:
            r4 = move-exception
            r3.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:            if (r5 == 0) goto L13;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:            r6 = describeTryOffer(r12);        r7 = r13.performAtomicTrySelect(r6);     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:            if (r7 != null) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:            if (r7 == kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0066, code lost:            if (r7 == kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC) goto L47;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:            if (r7 == kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()) goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:            if ((r7 instanceof kotlinx.coroutines.channels.Closed) == false) goto L27;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:            throw new java.lang.IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + r7).toString());     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0090, code lost:            r11.size.setValue(r5);     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0099, code lost:            return r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x003f, code lost:            r11.size.setValue(r5);        r8 = r6.getResult();        r4 = kotlin.Unit.INSTANCE;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004c, code lost:            r3.unlock();        kotlin.jvm.internal.Intrinsics.checkNotNull(r8);        ((kotlinx.coroutines.channels.ReceiveOrClosed) r8).completeResumeReceive(r12);     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005f, code lost:            return ((kotlinx.coroutines.channels.ReceiveOrClosed) r8).getOfferResult();     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009e, code lost:            if (r13.trySelect() != false) goto L37;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a0, code lost:            r11.size.setValue(r5);     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ac, code lost:            return kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED();     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ad, code lost:            enqueueElement(r5, r12);     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b5, code lost:            return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object offerSelectInternal(E r12, kotlinx.coroutines.selects.SelectInstance<?> r13) {
        /*
            r11 = this;
            java.lang.String r0 = "select"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r0 = 0
            java.util.concurrent.locks.ReentrantLock r1 = r11.lock
            r2 = 0
            r3 = r1
            java.util.concurrent.locks.Lock r3 = (java.util.concurrent.locks.Lock) r3
            r3.lock()
            r4 = 0
            kotlinx.atomicfu.AtomicInt r5 = r11.size     // Catch: java.lang.Throwable -> Lb6
            int r5 = r5.getValue()     // Catch: java.lang.Throwable -> Lb6
            kotlinx.coroutines.channels.Closed r6 = r11.getClosedForSend()     // Catch: java.lang.Throwable -> Lb6
            if (r6 == 0) goto L22
            r7 = 0
            r3.unlock()
            return r6
        L22:
            kotlinx.coroutines.internal.Symbol r6 = r11.updateBufferSize(r5)     // Catch: java.lang.Throwable -> Lb6
            if (r6 == 0) goto L2e
            r7 = 0
            r3.unlock()
            return r6
        L2e:
            if (r5 != 0) goto L9a
        L30:
        L31:
            kotlinx.coroutines.channels.AbstractSendChannel$TryOfferDesc r6 = r11.describeTryOffer(r12)     // Catch: java.lang.Throwable -> Lb6
            r7 = r6
            kotlinx.coroutines.internal.AtomicDesc r7 = (kotlinx.coroutines.internal.AtomicDesc) r7     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r7 = r13.performAtomicTrySelect(r7)     // Catch: java.lang.Throwable -> Lb6
            if (r7 != 0) goto L60
            kotlinx.atomicfu.AtomicInt r8 = r11.size     // Catch: java.lang.Throwable -> Lb6
            r8.setValue(r5)     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r8 = r6.getResult()     // Catch: java.lang.Throwable -> Lb6
            r0 = r8
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb6
            r3.unlock()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1 = r0
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            r1.completeResumeReceive(r12)
            r1 = r0
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            java.lang.Object r1 = r1.getOfferResult()
            return r1
        L60:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED     // Catch: java.lang.Throwable -> Lb6
            if (r7 == r8) goto L9a
            java.lang.Object r8 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch: java.lang.Throwable -> Lb6
            if (r7 == r8) goto L30
            java.lang.Object r8 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lb6
            if (r7 == r8) goto L90
            boolean r8 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> Lb6
            if (r8 == 0) goto L73
            goto L90
        L73:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb6
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb6
            r9.<init>()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r10 = "performAtomicTrySelect(describeTryOffer) returned "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> Lb6
            java.lang.StringBuilder r9 = r9.append(r7)     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb6
            r8.<init>(r9)     // Catch: java.lang.Throwable -> Lb6
            throw r8     // Catch: java.lang.Throwable -> Lb6
        L90:
            kotlinx.atomicfu.AtomicInt r8 = r11.size     // Catch: java.lang.Throwable -> Lb6
            r8.setValue(r5)     // Catch: java.lang.Throwable -> Lb6
            r3.unlock()
            return r7
        L9a:
            boolean r6 = r13.trySelect()     // Catch: java.lang.Throwable -> Lb6
            if (r6 != 0) goto Lad
            kotlinx.atomicfu.AtomicInt r6 = r11.size     // Catch: java.lang.Throwable -> Lb6
            r6.setValue(r5)     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r6 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lb6
            r3.unlock()
            return r6
        Lad:
            r11.enqueueElement(r5, r12)     // Catch: java.lang.Throwable -> Lb6
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> Lb6
            r3.unlock()
            return r6
        Lb6:
            r4 = move-exception
            r3.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerSelectInternal(java.lang.Object, kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object enqueueSend(Send send) {
        Intrinsics.checkNotNullParameter(send, "send");
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    private final Symbol updateBufferSize(int currentSize) {
        if (currentSize < this.capacity) {
            this.size.setValue(currentSize + 1);
            return null;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()]) {
            case 1:
                return AbstractChannelKt.OFFER_FAILED;
            case 2:
                return AbstractChannelKt.OFFER_SUCCESS;
            case 3:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void enqueueElement(int currentSize, E element) {
        if (currentSize < this.capacity) {
            ensureCapacity(currentSize);
            Object[] objArr = this.buffer;
            objArr[(this.head + currentSize) % objArr.length] = element;
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.onBufferOverflow == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        Object[] objArr2 = this.buffer;
        int i = this.head;
        objArr2[i % objArr2.length] = null;
        objArr2[(i + currentSize) % objArr2.length] = element;
        this.head = (i + 1) % objArr2.length;
    }

    private final void ensureCapacity(int currentSize) {
        Object[] objArr = this.buffer;
        if (currentSize >= objArr.length) {
            int newSize = Math.min(objArr.length * 2, this.capacity);
            Object[] newBuffer = new Object[newSize];
            for (int i = 0; i < currentSize; i++) {
                Object[] objArr2 = this.buffer;
                newBuffer[i] = objArr2[(this.head + i) % objArr2.length];
            }
            ArraysKt.fill((Symbol[]) newBuffer, AbstractChannelKt.EMPTY, currentSize, newSize);
            this.buffer = newBuffer;
            this.head = 0;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollInternal() {
        Send send = null;
        boolean resumed = false;
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            int size = this.size.getValue();
            if (size == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i = this.head;
            Object result = objArr[i];
            objArr[i] = null;
            this.size.setValue(size - 1);
            Object replacement = AbstractChannelKt.POLL_FAILED;
            if (size == this.capacity) {
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        break;
                    }
                    send = takeFirstSendOrPeekClosed;
                    Intrinsics.checkNotNull(send);
                    Symbol token = send.tryResumeSend(null);
                    if (token != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        resumed = true;
                        replacement = send.getElement();
                    } else {
                        send.undeliveredElement();
                    }
                }
            }
            if (replacement != AbstractChannelKt.POLL_FAILED && !(replacement instanceof Closed)) {
                this.size.setValue(size);
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + size) % objArr2.length] = replacement;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (resumed) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return result;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:            if (r7 == r14.capacity) goto L13;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003e, code lost:            r9 = describeTryPoll();        r10 = r15.performAtomicTrySelect(r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:            if (r10 != null) goto L16;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0060, code lost:            if (r10 == kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED) goto L49;     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0064, code lost:            if (r10 == kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC) goto L52;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006a, code lost:            if (r10 != kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:            r14.size.setValue(r7);        r14.buffer[r14.head] = r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:            return r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:            if ((r10 instanceof kotlinx.coroutines.channels.Closed) == false) goto L28;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:            r0 = r10;        r1 = true;        r8 = r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a0, code lost:            throw new java.lang.IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + r10).toString());     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004c, code lost:            r0 = r9.getResult();        r1 = true;        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);        r8 = ((kotlinx.coroutines.channels.Send) r0).getElement();     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a3, code lost:            if (r8 == kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a7, code lost:            if ((r8 instanceof kotlinx.coroutines.channels.Closed) != false) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a9, code lost:            r14.size.setValue(r7);        r9 = r14.buffer;        r9[(r14.head + r7) % r9.length] = r8;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d1, code lost:            r14.head = (r14.head + 1) % r14.buffer.length;        r6 = kotlin.Unit.INSTANCE;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e1, code lost:            if (r1 == false) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e3, code lost:            kotlin.jvm.internal.Intrinsics.checkNotNull(r0);        ((kotlinx.coroutines.channels.Send) r0).completeResumeSend();     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ec, code lost:            return r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bc, code lost:            if (r15.trySelect() != false) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00be, code lost:            r14.size.setValue(r7);        r14.buffer[r14.head] = r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d0, code lost:            return kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED();     */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object pollSelectInternal(kotlinx.coroutines.selects.SelectInstance<?> r15) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        Intrinsics.checkNotNullParameter(receive, "receive");
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean wasClosed) {
        Function1 onUndeliveredElement = this.onUndeliveredElement;
        UndeliveredElementException undeliveredElementException = null;
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            int value = this.size.getValue();
            for (int i = 0; i < value; i++) {
                Object value2 = this.buffer[this.head];
                if (onUndeliveredElement != null && value2 != AbstractChannelKt.EMPTY) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(onUndeliveredElement, value2, undeliveredElementException);
                }
                this.buffer[this.head] = AbstractChannelKt.EMPTY;
                this.head = (this.head + 1) % this.buffer.length;
            }
            this.size.setValue(0);
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            super.onCancelIdempotent(wasClosed);
            if (undeliveredElementException != null) {
                UndeliveredElementException it = undeliveredElementException;
                throw it;
            }
        } catch (Throwable it2) {
            reentrantLock.unlock();
            throw it2;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size.getValue() + ")";
    }
}
