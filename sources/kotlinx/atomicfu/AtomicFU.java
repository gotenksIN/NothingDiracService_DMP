package kotlinx.atomicfu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.TraceBase;

/* compiled from: AtomicFU.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a)\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0018\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\t2\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000b\u001a\u0018\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\r\u001a\u0018\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u000e"}, d2 = {"atomic", "Lkotlinx/atomicfu/AtomicRef;", "T", "initial", "(Ljava/lang/Object;)Lkotlinx/atomicfu/AtomicRef;", "trace", "Lkotlinx/atomicfu/TraceBase;", "(Ljava/lang/Object;Lkotlinx/atomicfu/TraceBase;)Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/AtomicBoolean;", "", "Lkotlinx/atomicfu/AtomicInt;", "", "Lkotlinx/atomicfu/AtomicLong;", "", "external__kotlinx.atomicfu__android_common__kotlinx_atomicfu"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AtomicFU {
    public static final <T> AtomicRef<T> atomic(T t, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicRef<>(t, trace);
    }

    public static /* synthetic */ AtomicRef atomic$default(Object obj, TraceBase traceBase, int i, Object obj2) {
        if ((i & 2) != 0) {
            traceBase = TraceBase.None.INSTANCE;
        }
        return atomic(obj, traceBase);
    }

    public static final <T> AtomicRef<T> atomic(T t) {
        return atomic(t, TraceBase.None.INSTANCE);
    }

    public static final AtomicInt atomic(int initial, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicInt(initial, trace);
    }

    public static /* synthetic */ AtomicInt atomic$default(int i, TraceBase traceBase, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            traceBase = TraceBase.None.INSTANCE;
        }
        return atomic(i, traceBase);
    }

    public static final AtomicInt atomic(int initial) {
        return atomic(initial, (TraceBase) TraceBase.None.INSTANCE);
    }

    public static final AtomicLong atomic(long initial, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicLong(initial, trace);
    }

    public static /* synthetic */ AtomicLong atomic$default(long j, TraceBase traceBase, int i, Object obj) {
        if ((i & 2) != 0) {
            traceBase = TraceBase.None.INSTANCE;
        }
        return atomic(j, traceBase);
    }

    public static final AtomicLong atomic(long initial) {
        return atomic(initial, TraceBase.None.INSTANCE);
    }

    public static final AtomicBoolean atomic(boolean initial, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicBoolean(initial, trace);
    }

    public static /* synthetic */ AtomicBoolean atomic$default(boolean z, TraceBase traceBase, int i, Object obj) {
        if ((i & 2) != 0) {
            traceBase = TraceBase.None.INSTANCE;
        }
        return atomic(z, traceBase);
    }

    public static final AtomicBoolean atomic(boolean initial) {
        return atomic(initial, TraceBase.None.INSTANCE);
    }
}
