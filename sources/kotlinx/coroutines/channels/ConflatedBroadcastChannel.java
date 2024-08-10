package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.Undispatched;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

/* compiled from: ConflatedBroadcastChannel.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 B*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004ABCDB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J=\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001a2\u0014\u0010\u001c\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0018\u00010\u001a2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002¢\u0006\u0002\u0010\u001eJ\u0012\u0010\u001f\u001a\u00020\f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0017J\u0018\u0010\u001f\u001a\u00020\"2\u000e\u0010 \u001a\n\u0018\u00010#j\u0004\u0018\u0001`$H\u0016J\u0012\u0010%\u001a\u00020\f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0016\u0010&\u001a\u00020\"2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002J\"\u0010'\u001a\u00020\"2\u0018\u0010(\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010!\u0012\u0004\u0012\u00020\"0)j\u0002`*H\u0016J\u0012\u0010+\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u0017\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010/J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0016JV\u00102\u001a\u00020\"\"\u0004\b\u0001\u001032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H3052\u0006\u0010.\u001a\u00028\u00002(\u00106\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H308\u0012\u0006\u0012\u0004\u0018\u00010\b07H\u0002ø\u0001\u0000¢\u0006\u0002\u00109J=\u0010:\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0018\u00010\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001a2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002¢\u0006\u0002\u0010\u001eJ\u0019\u0010;\u001a\u00020\"2\u0006\u0010.\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010<J&\u0010=\u001a\b\u0012\u0004\u0012\u00020\"0>2\u0006\u0010.\u001a\u00028\u0000H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0014\u0010\u0005\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "value", "(Ljava/lang/Object;)V", "()V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "_updating", "Lkotlinx/atomicfu/AtomicInt;", "isClosedForSend", "", "()Z", "onCloseHandler", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "getValue$annotations", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull", "addSubscriber", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "list", "subscriber", "([Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "close", "closeSubscriber", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "invokeOnCloseHandler", "offerInternal", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "element", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "registerSelectSend", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "removeSubscriber", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "Closed", "Companion", "State", "Subscriber", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {

    @Deprecated
    private static final State<Object> INITIAL_STATE;

    @Deprecated
    private static final Symbol UNDEFINED;
    private final AtomicRef<Object> _state;
    private final AtomicInt _updating;
    private final AtomicRef<Object> onCloseHandler;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final Closed CLOSED = new Closed(null);

    public static /* synthetic */ void getValue$annotations() {
    }

    public ConflatedBroadcastChannel() {
        this._state = AtomicFU.atomic(INITIAL_STATE);
        this._updating = AtomicFU.atomic(0);
        this.onCloseHandler = AtomicFU.atomic((Object) null);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return BroadcastChannel.DefaultImpls.offer(this, e);
    }

    public ConflatedBroadcastChannel(E e) {
        this();
        this._state.lazySet(new State(e, null));
    }

    /* compiled from: ConflatedBroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Companion;", "", "()V", "CLOSED", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "INITIAL_STATE", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Symbol symbol = new Symbol("UNDEFINED");
        UNDEFINED = symbol;
        INITIAL_STATE = new State<>(symbol, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ConflatedBroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0014\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R \u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "E", "", "value", "subscribers", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "(Ljava/lang/Object;[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)V", "[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class State<E> {
        public final Subscriber<E>[] subscribers;
        public final Object value;

        public State(Object value, Subscriber<E>[] subscriberArr) {
            this.value = value;
            this.subscribers = subscriberArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ConflatedBroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "", "closeCause", "", "(Ljava/lang/Throwable;)V", "sendException", "getSendException", "()Ljava/lang/Throwable;", "valueException", "getValueException", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Closed {
        public final Throwable closeCause;

        public Closed(Throwable closeCause) {
            this.closeCause = closeCause;
        }

        public final Throwable getSendException() {
            Throwable th = this.closeCause;
            return th == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }

        public final Throwable getValueException() {
            Throwable th = this.closeCause;
            return th == null ? new IllegalStateException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }
    }

    public final E getValue() {
        Object value = this._state.getValue();
        if (value instanceof Closed) {
            throw ((Closed) value).getValueException();
        }
        if (value instanceof State) {
            if (((State) value).value == UNDEFINED) {
                throw new IllegalStateException("No value");
            }
            return (E) ((State) value).value;
        }
        throw new IllegalStateException(("Invalid state " + value).toString());
    }

    public final E getValueOrNull() {
        Object value = this._state.getValue();
        if (value instanceof Closed) {
            return null;
        }
        if (value instanceof State) {
            Symbol symbol = UNDEFINED;
            E e = (E) ((State) value).value;
            if (e == symbol) {
                return null;
            }
            return e;
        }
        throw new IllegalStateException(("Invalid state " + value).toString());
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._state.getValue() instanceof Closed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> openSubscription() {
        Object state;
        State update;
        Subscriber subscriber = new Subscriber(this);
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (state instanceof Closed) {
                subscriber.cancel(((Closed) state).closeCause);
                return subscriber;
            }
            if (state instanceof State) {
                if (((State) state).value != UNDEFINED) {
                    subscriber.offerInternal(((State) state).value);
                }
                Object obj = ((State) state).value;
                Intrinsics.checkNotNull(state, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E of kotlinx.coroutines.channels.ConflatedBroadcastChannel.openSubscription$lambda$1>");
                update = new State(obj, addSubscriber(((State) state).subscribers, subscriber));
            } else {
                throw new IllegalStateException(("Invalid state " + state).toString());
            }
        } while (!this._state.compareAndSet(state, update));
        return subscriber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeSubscriber(Subscriber<E> subscriber) {
        Object state;
        State update;
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (state instanceof Closed) {
                return;
            }
            if (state instanceof State) {
                Object obj = ((State) state).value;
                Intrinsics.checkNotNull(state, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E of kotlinx.coroutines.channels.ConflatedBroadcastChannel.closeSubscriber$lambda$2>");
                Subscriber<E>[] subscriberArr = ((State) state).subscribers;
                Intrinsics.checkNotNull(subscriberArr);
                update = new State(obj, removeSubscriber(subscriberArr, subscriber));
            } else {
                throw new IllegalStateException(("Invalid state " + state).toString());
            }
        } while (!this._state.compareAndSet(state, update));
    }

    private final Subscriber<E>[] addSubscriber(Subscriber<E>[] list, Subscriber<E> subscriber) {
        if (list != null) {
            return (Subscriber[]) ArraysKt.plus(list, subscriber);
        }
        Subscriber<E>[] subscriberArr = new Subscriber[1];
        for (int i = 0; i < 1; i++) {
            subscriberArr[i] = subscriber;
        }
        return subscriberArr;
    }

    private final Subscriber<E>[] removeSubscriber(Subscriber<E>[] list, Subscriber<E> subscriber) {
        int n = list.length;
        int i = ArraysKt.indexOf(list, subscriber);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(i >= 0)) {
                throw new AssertionError();
            }
        }
        if (n == 1) {
            return null;
        }
        Subscriber[] update = new Subscriber[n - 1];
        ArraysKt.copyInto$default(list, update, 0, 0, i, 6, (Object) null);
        ArraysKt.copyInto$default(list, update, i, i + 1, 0, 8, (Object) null);
        return update;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* renamed from: close, reason: merged with bridge method [inline-methods] */
    public boolean cancel(Throwable cause) {
        Object state;
        int i;
        Closed update;
        AtomicRef $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            if (state instanceof Closed) {
                return false;
            }
            if (state instanceof State) {
                update = cause == null ? CLOSED : new Closed(cause);
            } else {
                throw new IllegalStateException(("Invalid state " + state).toString());
            }
        } while (!this._state.compareAndSet(state, update));
        Intrinsics.checkNotNull(state, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E of kotlinx.coroutines.channels.ConflatedBroadcastChannel.close$lambda$5>");
        Subscriber<E>[] subscriberArr = ((State) state).subscribers;
        if (subscriberArr != null) {
            for (Subscriber<E> subscriber : subscriberArr) {
                subscriber.cancel(cause);
            }
        }
        invokeOnCloseHandler(cause);
        return true;
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
        Object state = this._state.getValue();
        if ((state instanceof Closed) && this.onCloseHandler.compareAndSet(handler, AbstractChannelKt.HANDLER_INVOKED)) {
            handler.invoke(((Closed) state).closeCause);
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(CancellationException cause) {
        cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E e, Continuation<? super Unit> continuation) {
        Closed it = offerInternal(e);
        if (it != null) {
            throw it.getSendException();
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public Object mo2359trySendJP2dKIU(E element) {
        Closed it = offerInternal(element);
        if (it != null) {
            return ChannelResult.INSTANCE.m2377closedJP2dKIU(it.getSendException());
        }
        return ChannelResult.INSTANCE.m2379successJP2dKIU(Unit.INSTANCE);
    }

    private final Closed offerInternal(E element) {
        Closed closed = null;
        if (!this._updating.compareAndSet(0, 1)) {
            return null;
        }
        try {
            AtomicRef $this$loop$iv = this._state;
            while (true) {
                Object state = $this$loop$iv.getValue();
                if (state instanceof Closed) {
                    closed = (Closed) state;
                    break;
                }
                if (state instanceof State) {
                    Intrinsics.checkNotNull(state, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E of kotlinx.coroutines.channels.ConflatedBroadcastChannel.offerInternal$lambda$9>");
                    State update = new State(element, ((State) state).subscribers);
                    if (this._state.compareAndSet(state, update)) {
                        Subscriber<E>[] subscriberArr = ((State) state).subscribers;
                        if (subscriberArr != null) {
                            for (Subscriber<E> subscriber : subscriberArr) {
                                subscriber.offerInternal(element);
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException(("Invalid state " + state).toString());
                }
            }
            return closed;
        } finally {
            this._updating.setValue(0);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>(this) { // from class: kotlinx.coroutines.channels.ConflatedBroadcastChannel$onSend$1
            final /* synthetic */ ConflatedBroadcastChannel<E> this$0;

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
        if (select.trySelect()) {
            Closed it = offerInternal(element);
            if (it != null) {
                select.resumeSelectWithException(it.getSendException());
            } else {
                Undispatched.startCoroutineUnintercepted(block, this, select.getCompletion());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ConflatedBroadcastChannel.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/ConflatedChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "(Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;)V", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "onCancelIdempotent", "", "wasClosed", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Subscriber<E> extends ConflatedChannel<E> implements ReceiveChannel<E> {
        private final ConflatedBroadcastChannel<E> broadcastChannel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Subscriber(ConflatedBroadcastChannel<E> broadcastChannel) {
            super(null);
            Intrinsics.checkNotNullParameter(broadcastChannel, "broadcastChannel");
            this.broadcastChannel = broadcastChannel;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractChannel
        public void onCancelIdempotent(boolean wasClosed) {
            if (wasClosed) {
                this.broadcastChannel.closeSubscriber(this);
            }
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractSendChannel
        public Object offerInternal(E element) {
            return super.offerInternal(element);
        }
    }
}
