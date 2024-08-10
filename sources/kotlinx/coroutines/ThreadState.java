package kotlinx.coroutines;

import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

/* compiled from: Interruptible.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0006\u0010\u0018\u001a\u00020\u0006R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/ThreadState;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "_state", "Lkotlinx/atomicfu/AtomicInt;", "cancelHandle", "Lkotlinx/coroutines/DisposableHandle;", "targetThread", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "clearInterrupt", "invalidState", "", "state", "", "invoke", "setup", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
final class ThreadState implements Function1<Throwable, Unit> {
    private final AtomicInt _state;
    private DisposableHandle cancelHandle;
    private final Job job;
    private final Thread targetThread;

    public ThreadState(Job job) {
        Intrinsics.checkNotNullParameter(job, "job");
        this.job = job;
        this._state = AtomicFU.atomic(0);
        this.targetThread = Thread.currentThread();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    public final void setup() {
        int state;
        this.cancelHandle = this.job.invokeOnCompletion(true, true, this);
        AtomicInt $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            switch (state) {
                case 0:
                    break;
                case 1:
                default:
                    invalidState(state);
                    throw new ExceptionsH();
                case 2:
                case 3:
                    return;
            }
        } while (!this._state.compareAndSet(state, 0));
    }

    public final void clearInterrupt() {
        AtomicInt $this$loop$iv = this._state;
        while (true) {
            int state = $this$loop$iv.getValue();
            switch (state) {
                case 0:
                    if (!this._state.compareAndSet(state, 1)) {
                        break;
                    } else {
                        DisposableHandle disposableHandle = this.cancelHandle;
                        if (disposableHandle != null) {
                            disposableHandle.dispose();
                            return;
                        }
                        return;
                    }
                case 1:
                default:
                    invalidState(state);
                    throw new ExceptionsH();
                case 2:
                    break;
                case 3:
                    Thread.interrupted();
                    return;
            }
        }
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(Throwable cause) {
        int state;
        AtomicInt $this$loop$iv = this._state;
        do {
            state = $this$loop$iv.getValue();
            switch (state) {
                case 0:
                    break;
                case 1:
                case 2:
                case 3:
                    return;
                default:
                    invalidState(state);
                    throw new ExceptionsH();
            }
        } while (!this._state.compareAndSet(state, 2));
        this.targetThread.interrupt();
        this._state.setValue(3);
    }

    private final Void invalidState(int state) {
        throw new IllegalStateException(("Illegal state " + state).toString());
    }
}
