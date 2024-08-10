package kotlinx.coroutines.channels;

import java.util.ArrayList;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStrings;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecovery;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.Undispatched;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* compiled from: AbstractChannel.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004Z[\\]B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010%\u001a\u00020\u00132\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010(\u001a\u00020)H\u0002J!\u0010*\u001a\u000e\u0012\u0002\b\u00030+j\u0006\u0012\u0002\b\u0003`,2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010.J\u001b\u0010/\u001a\b\u0012\u0004\u0012\u00028\u0000002\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u00101J\u0012\u00102\u001a\u0004\u0018\u00010\u001a2\u0006\u00103\u001a\u000204H\u0014J\u0014\u00105\u001a\u00020\u00052\n\u00106\u001a\u0006\u0012\u0002\b\u00030\rH\u0002J!\u00107\u001a\u00020'2\u0006\u0010-\u001a\u00028\u00002\n\u00106\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0002\u00108J\u0014\u00107\u001a\u00020'2\n\u00106\u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\"\u00109\u001a\u00020\u00052\u0018\u0010:\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`;H\u0016J\u0012\u0010<\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0015\u0010=\u001a\u00020\u00132\u0006\u0010-\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010>J\u0015\u0010?\u001a\u00020\u001a2\u0006\u0010-\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010@J!\u0010A\u001a\u00020\u001a2\u0006\u0010-\u001a\u00028\u00002\n\u0010B\u001a\u0006\u0012\u0002\b\u00030CH\u0014¢\u0006\u0002\u0010DJ\u0010\u0010E\u001a\u00020\u00052\u0006\u00106\u001a\u00020FH\u0014JV\u0010G\u001a\u00020\u0005\"\u0004\b\u0001\u0010H2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002HH0C2\u0006\u0010-\u001a\u00028\u00002(\u0010I\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002HH0K\u0012\u0006\u0012\u0004\u0018\u00010\u001a0JH\u0002ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u00103\u001a\u00020\u00052\u0006\u0010-\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u001b\u0010N\u001a\b\u0012\u0002\b\u0003\u0018\u00010O2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010PJ\u0019\u0010Q\u001a\u00020\u00052\u0006\u0010-\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0010\u0010R\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010OH\u0014J\n\u0010S\u001a\u0004\u0018\u000104H\u0004J\b\u0010T\u001a\u00020\tH\u0016J$\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00050V2\u0006\u0010-\u001a\u00028\u0000ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\bW\u0010@J)\u0010X\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030K2\u0006\u0010-\u001a\u00028\u00002\n\u00106\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0002\u0010YR\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0012\u0010\u0012\u001a\u00020\u0013X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0013X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR*\u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00068\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006^"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "closedForReceive", "Lkotlinx/coroutines/channels/Closed;", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForSend", "getClosedForSend", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "isClosedForSend", "isFullImpl", "onCloseHandler", "Lkotlinx/atomicfu/AtomicRef;", "", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queueDebugStateString", "getQueueDebugStateString", "close", "cause", "", "countQueueSize", "", "describeSendBuffered", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "element", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeTryOffer", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "enqueueSend", "send", "Lkotlinx/coroutines/channels/Send;", "helpClose", "closed", "helpCloseAndGetSendException", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "invokeOnClose", "handler", "Lkotlinx/coroutines/channels/Handler;", "invokeOnCloseHandler", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onClosedIdempotent", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "registerSelectSend", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBuffered", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendSuspend", "takeFirstReceiveOrPeekClosed", "takeFirstSendOrPeekClosed", "toString", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    protected final Function1<E, Unit> onUndeliveredElement;
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private final AtomicRef<Object> onCloseHandler = AtomicFU.atomic((Object) null);

    protected abstract boolean isBufferAlwaysFull();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBufferFull();

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSendChannel(Function1<? super E, Unit> function1) {
        this.onUndeliveredElement = function1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object offerInternal(E element) {
        ReceiveOrClosed receive;
        Symbol token;
        do {
            receive = takeFirstReceiveOrPeekClosed();
            if (receive == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            token = receive.tryResumeReceive(element, null);
        } while (token == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        receive.completeResumeReceive(element);
        return receive.getOfferResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        Intrinsics.checkNotNullParameter(select, "select");
        TryOfferDesc offerOp = describeTryOffer(element);
        Object failure = select.performAtomicTrySelect(offerOp);
        if (failure != null) {
            return failure;
        }
        ReceiveOrClosed receive = offerOp.getResult();
        receive.completeResumeReceive(element);
        return receive.getOfferResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        Closed closed = prevNode instanceof Closed ? (Closed) prevNode : null;
        if (closed == null) {
            return null;
        }
        Closed it = closed;
        helpClose(it);
        return closed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        Closed closed = nextNode instanceof Closed ? (Closed) nextNode : null;
        if (closed == null) {
            return null;
        }
        Closed it = closed;
        helpClose(it);
        return closed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode first$iv;
        LockFreeLinkedListNode next$iv;
        LockFreeLinkedListNode this_$iv = this.queue;
        while (true) {
            Object next = this_$iv.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            first$iv = (LockFreeLinkedListNode) next;
            if (first$iv == this_$iv) {
                first$iv = null;
                break;
            }
            if (!(first$iv instanceof Send)) {
                first$iv = null;
                break;
            }
            Send it = (Send) first$iv;
            if (((it instanceof Closed) && !first$iv.isRemoved()) || (next$iv = first$iv.removeOrNext()) == null) {
                break;
            }
            next$iv.helpRemovePrev();
        }
        return (Send) first$iv;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListNode this_$iv = this.queue;
        LockFreeLinkedListNode node$iv = new SendBuffered(element);
        do {
            prevNode = this_$iv.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(node$iv, this_$iv));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E element) {
        return new SendBufferedDesc(this.queue, element);
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SendBufferedDesc(LockFreeLinkedListHead queue, E e) {
            super(queue, new SendBuffered(e));
            Intrinsics.checkNotNullParameter(queue, "queue");
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e, Continuation<? super Unit> continuation) {
        Object sendSuspend;
        return (offerInternal(e) != AbstractChannelKt.OFFER_SUCCESS && (sendSuspend = sendSuspend(e, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? sendSuspend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(E element) {
        UndeliveredElementException it;
        try {
            return SendChannel.DefaultImpls.offer(this, element);
        } catch (Throwable e) {
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, element, null, 2, null)) != null) {
                ExceptionsKt.addSuppressed(it, e);
                throw it;
            }
            throw e;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    public final Object mo2359trySendJP2dKIU(E element) {
        Object result = offerInternal(element);
        if (result == AbstractChannelKt.OFFER_SUCCESS) {
            return ChannelResult.INSTANCE.m2379successJP2dKIU(Unit.INSTANCE);
        }
        if (result == AbstractChannelKt.OFFER_FAILED) {
            Closed closedForSend = getClosedForSend();
            return closedForSend == null ? ChannelResult.INSTANCE.m2378failurePtdJZtk() : ChannelResult.INSTANCE.m2377closedJP2dKIU(helpCloseAndGetSendException(closedForSend));
        }
        if (result instanceof Closed) {
            return ChannelResult.INSTANCE.m2377closedJP2dKIU(helpCloseAndGetSendException((Closed) result));
        }
        throw new IllegalStateException(("trySend returned " + result).toString());
    }

    private final Throwable helpCloseAndGetSendException(Closed<?> closed) {
        helpClose(closed);
        return closed.getSendException();
    }

    private final Throwable helpCloseAndGetSendException(E element, Closed<?> closed) {
        UndeliveredElementException it;
        helpClose(closed);
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, element, null, 2, null)) != null) {
            ExceptionsKt.addSuppressed(it, closed.getSendException());
            throw it;
        }
        return closed.getSendException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sendSuspend(E e, Continuation<? super Unit> continuation) {
        SendElement send;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cont = cancellable$iv;
        while (true) {
            if (isFullImpl()) {
                Function1<E, Unit> function1 = this.onUndeliveredElement;
                if (function1 == null) {
                    send = new SendElement(e, cont);
                } else {
                    send = new SendElementWithUndeliveredHandler(e, cont, function1);
                }
                Object enqueueResult = enqueueSend(send);
                if (enqueueResult == null) {
                    CancellableContinuationKt.removeOnCancellation(cont, send);
                    break;
                }
                if (enqueueResult instanceof Closed) {
                    helpCloseAndResumeWithSendException(cont, e, (Closed) enqueueResult);
                    break;
                }
                if (enqueueResult != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueResult instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueResult).toString());
                }
            }
            Object offerResult = offerInternal(e);
            if (offerResult == AbstractChannelKt.OFFER_SUCCESS) {
                Result.Companion companion = Result.INSTANCE;
                cont.resumeWith(Result.m856constructorimpl(Unit.INSTANCE));
                break;
            }
            if (offerResult != AbstractChannelKt.OFFER_FAILED) {
                if (offerResult instanceof Closed) {
                    helpCloseAndResumeWithSendException(cont, e, (Closed) offerResult);
                } else {
                    throw new IllegalStateException(("offerInternal returned " + offerResult).toString());
                }
            }
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, E e, Closed<?> closed) {
        UndeliveredElementException it;
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            ExceptionsKt.addSuppressed(it, sendException);
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m856constructorimpl(ResultKt.createFailure(it)));
        } else {
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m856constructorimpl(ResultKt.createFailure(sendException)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0057, code lost:            if (r4 != false) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:            return kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:            return null;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object enqueueSend(kotlinx.coroutines.channels.Send r8) {
        /*
            r7 = this;
            java.lang.String r0 = "send"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = r7.isBufferAlwaysFull()
            if (r0 == 0) goto L28
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r7.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
        L10:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r0.getPrevNode()
            r3 = r2
            r4 = 0
            boolean r5 = r3 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r5 == 0) goto L1c
            return r3
        L1c:
            r3 = r8
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            boolean r3 = r2.addNext(r3, r0)
            if (r3 == 0) goto L10
            goto L5c
        L28:
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r7.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
            r3 = 0
            kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 r4 = new kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            r5 = r8
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            r4.<init>(r5)
            kotlinx.coroutines.internal.LockFreeLinkedListNode$CondAddOp r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp) r4
            r2 = r4
        L3b:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = r0.getPrevNode()
            r4 = r3
            r5 = 0
            boolean r6 = r4 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r6 == 0) goto L47
            return r4
        L47:
            r4 = r8
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
            int r4 = r3.tryCondAddNext(r4, r0, r2)
            switch(r4) {
                case 1: goto L56;
                case 2: goto L54;
                default: goto L53;
            }
        L53:
            goto L3b
        L54:
            r4 = 0
            goto L57
        L56:
            r4 = 1
        L57:
            if (r4 != 0) goto L5c
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            return r0
        L5c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.enqueueSend(kotlinx.coroutines.channels.Send):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(Throwable cause) {
        boolean z;
        Closed actuallyClosed;
        Closed closed = new Closed(cause);
        LockFreeLinkedListHead this_$iv = this.queue;
        while (true) {
            LockFreeLinkedListNode prev$iv = this_$iv.getPrevNode();
            z = true;
            if (!(!(prev$iv instanceof Closed))) {
                z = false;
                break;
            }
            if (prev$iv.addNext(closed, this_$iv)) {
                break;
            }
        }
        boolean closeAdded = z;
        if (closeAdded) {
            actuallyClosed = closed;
        } else {
            LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
            Intrinsics.checkNotNull(prevNode, "null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            actuallyClosed = (Closed) prevNode;
        }
        helpClose(actuallyClosed);
        if (closeAdded) {
            invokeOnCloseHandler(cause);
        }
        return closeAdded;
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object handler = this.onCloseHandler.getValue();
        if (handler != null && handler != AbstractChannelKt.HANDLER_INVOKED && this.onCloseHandler.compareAndSet(handler, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(handler, 1)).invoke(cause);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (!this.onCloseHandler.compareAndSet(null, handler)) {
            Object value = this.onCloseHandler.getValue();
            if (value == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + value);
        }
        Closed closedToken = getClosedForSend();
        if (closedToken != null && this.onCloseHandler.compareAndSet(handler, AbstractChannelKt.HANDLER_INVOKED)) {
            handler.invoke(closedToken.closeCause);
        }
    }

    private final void helpClose(Closed<?> closed) {
        Object closedList = InlineList.m2394constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            Receive previous = prevNode instanceof Receive ? (Receive) prevNode : null;
            if (previous == null) {
                break;
            } else if (!previous.mo2402remove()) {
                previous.helpRemove();
            } else {
                closedList = InlineList.m2399plusFjFbRPM(closedList, previous);
            }
        }
        if (closedList != null) {
            if (!(closedList instanceof ArrayList)) {
                Receive it = (Receive) closedList;
                it.resumeReceiveClosed(closed);
            } else {
                Intrinsics.checkNotNull(closedList, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
                ArrayList list$iv = (ArrayList) closedList;
                for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
                    Receive it2 = (Receive) list$iv.get(i$iv);
                    it2.resumeReceiveClosed(closed);
                }
            }
        }
        onClosedIdempotent(closed);
    }

    protected void onClosedIdempotent(LockFreeLinkedListNode closed) {
        Intrinsics.checkNotNullParameter(closed, "closed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ?? r2;
        LockFreeLinkedListNode next$iv;
        LockFreeLinkedListNode this_$iv = this.queue;
        while (true) {
            Object next = this_$iv.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            r2 = (LockFreeLinkedListNode) next;
            if (r2 == this_$iv) {
                r2 = 0;
                break;
            }
            if (!(r2 instanceof ReceiveOrClosed)) {
                r2 = 0;
                break;
            }
            ReceiveOrClosed it = (ReceiveOrClosed) r2;
            if (((it instanceof Closed) && !r2.isRemoved()) || (next$iv = r2.removeOrNext()) == null) {
                break;
            }
            next$iv.helpRemovePrev();
        }
        return (ReceiveOrClosed) r2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final TryOfferDesc<E> describeTryOffer(E element) {
        return new TryOfferDesc<>(element, this.queue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: AbstractChannel.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        public final E element;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TryOfferDesc(E e, LockFreeLinkedListHead queue) {
            super(queue);
            Intrinsics.checkNotNullParameter(queue, "queue");
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Intrinsics.checkNotNullParameter(prepareOp, "prepareOp");
            Object obj = prepareOp.affected;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E of kotlinx.coroutines.channels.AbstractSendChannel.TryOfferDesc>");
            ReceiveOrClosed affected = (ReceiveOrClosed) obj;
            Symbol token = affected.tryResumeReceive(this.element, prepareOp);
            if (token == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (token == AtomicKt.RETRY_ATOMIC) {
                return AtomicKt.RETRY_ATOMIC;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (token == CancellableContinuationImplKt.RESUME_TOKEN) {
                    return null;
                }
                throw new AssertionError();
            }
            return null;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>(this) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            final /* synthetic */ AbstractSendChannel<E> this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(SelectInstance<? super R> select, E param, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
                Intrinsics.checkNotNullParameter(select, "select");
                Intrinsics.checkNotNullParameter(block, "block");
                this.this$0.registerSelectSend(select, param, block);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
        while (!select.isSelected()) {
            if (isFullImpl()) {
                SendSelect node = new SendSelect(element, this, select, block);
                Object enqueueResult = enqueueSend(node);
                if (enqueueResult == null) {
                    select.disposeOnSelect(node);
                    return;
                } else {
                    if (enqueueResult instanceof Closed) {
                        throw StackTraceRecovery.recoverStackTrace(helpCloseAndGetSendException(element, (Closed) enqueueResult));
                    }
                    if (enqueueResult != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueResult instanceof Receive)) {
                        throw new IllegalStateException(("enqueueSend returned " + enqueueResult + " ").toString());
                    }
                }
            }
            Object offerResult = offerSelectInternal(element, select);
            if (offerResult == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (offerResult != AbstractChannelKt.OFFER_FAILED && offerResult != AtomicKt.RETRY_ATOMIC) {
                if (offerResult == AbstractChannelKt.OFFER_SUCCESS) {
                    Undispatched.startCoroutineUnintercepted(block, this, select.getCompletion());
                    return;
                } else {
                    if (!(offerResult instanceof Closed)) {
                        throw new IllegalStateException(("offerSelectInternal returned " + offerResult).toString());
                    }
                    throw StackTraceRecovery.recoverStackTrace(helpCloseAndGetSendException(element, (Closed) offerResult));
                }
            }
        }
    }

    public String toString() {
        return DebugStrings.getClassSimpleName(this) + "@" + DebugStrings.getHexAddress(this) + "{" + getQueueDebugStateString() + "}" + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String result;
        LockFreeLinkedListNode head = this.queue.getNextNode();
        if (head == this.queue) {
            return "EmptyQueue";
        }
        if (head instanceof Closed) {
            result = head.toString();
        } else if (head instanceof Receive) {
            result = "ReceiveQueued";
        } else {
            result = head instanceof Send ? "SendQueued" : "UNEXPECTED:" + head;
        }
        LockFreeLinkedListNode tail = this.queue.getPrevNode();
        if (tail == head) {
            return result;
        }
        String result2 = result + ",queueSize=" + countQueueSize();
        return tail instanceof Closed ? result2 + ",closedForSend=" + tail : result2;
    }

    private final int countQueueSize() {
        int size = 0;
        LockFreeLinkedListHead this_$iv = this.queue;
        Object next = this_$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
            if (cur$iv instanceof LockFreeLinkedListNode) {
                size++;
            }
        }
        return size;
    }

    protected String getBufferDebugString() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractChannel.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BV\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t\u0012(\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016R7\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "undeliveredElement", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SendSelect<E, R> extends Send implements DisposableHandle {
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        public final AbstractSendChannel<E> channel;
        private final E pollResult;
        public final SelectInstance<R> select;

        @Override // kotlinx.coroutines.channels.Send
        /* renamed from: getPollResult */
        public E getElement() {
            return this.pollResult;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(E e, AbstractSendChannel<E> channel, SelectInstance<? super R> select, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(select, "select");
            Intrinsics.checkNotNullParameter(block, "block");
            this.pollResult = e;
            this.channel = channel;
            this.select = select;
            this.block = block;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this.select.trySelectOther(otherOp);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            CancellableKt.startCoroutineCancellable$default(this.block, this.channel, this.select.getCompletion(), null, 4, null);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (mo2402remove()) {
                undeliveredElement();
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            Intrinsics.checkNotNullParameter(closed, "closed");
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public void undeliveredElement() {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                OnUndeliveredElementKt.callUndeliveredElement(function1, getElement(), this.select.getCompletion().getContext());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect@" + DebugStrings.getHexAddress(this) + "(" + getElement() + ")[" + this.channel + ", " + this.select + "]";
        }
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SendBuffered<E> extends Send {
        public final E element;

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        /* renamed from: getPollResult, reason: from getter */
        public Object getElement() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (otherOp != null) {
                otherOp.finishPrepare();
            }
            return symbol;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            Intrinsics.checkNotNullParameter(closed, "closed");
            if (DebugKt.getASSERTIONS_ENABLED()) {
                throw new AssertionError();
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendBuffered@" + DebugStrings.getHexAddress(this) + "(" + this.element + ")";
        }
    }
}
