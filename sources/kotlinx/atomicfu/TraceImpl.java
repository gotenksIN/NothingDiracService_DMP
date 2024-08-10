package kotlinx.atomicfu;

import androidx.core.app.NotificationCompat;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Trace.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0017"}, d2 = {"Lkotlinx/atomicfu/TraceImpl;", "Lkotlinx/atomicfu/TraceBase;", "size", "", "format", "Lkotlinx/atomicfu/TraceFormat;", "(ILkotlinx/atomicfu/TraceFormat;)V", "index", "Ljava/util/concurrent/atomic/AtomicInteger;", "mask", "trace", "", "", "[Ljava/lang/Object;", "append", "", NotificationCompat.CATEGORY_EVENT, "event1", "event2", "event3", "event4", "toString", "", "external__kotlinx.atomicfu__android_common__kotlinx_atomicfu"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
final class TraceImpl extends TraceBase {
    private final TraceFormat format;
    private final AtomicInteger index;
    private final int mask;
    private final int size;
    private final Object[] trace;

    public TraceImpl(int size, TraceFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
        if (!(size >= 1)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int highestOneBit = Integer.highestOneBit((size << 1) - 1);
        this.size = highestOneBit;
        this.mask = highestOneBit - 1;
        this.trace = new Object[highestOneBit];
        this.index = new AtomicInteger(0);
    }

    @Override // kotlinx.atomicfu.TraceBase
    public void append(Object event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i = this.index.getAndIncrement();
        this.trace[this.mask & i] = event;
    }

    @Override // kotlinx.atomicfu.TraceBase
    public void append(Object event1, Object event2) {
        Intrinsics.checkNotNullParameter(event1, "event1");
        Intrinsics.checkNotNullParameter(event2, "event2");
        int i = this.index.getAndAdd(2);
        Object[] objArr = this.trace;
        int i2 = this.mask;
        objArr[i & i2] = event1;
        objArr[i2 & (i + 1)] = event2;
    }

    @Override // kotlinx.atomicfu.TraceBase
    public void append(Object event1, Object event2, Object event3) {
        Intrinsics.checkNotNullParameter(event1, "event1");
        Intrinsics.checkNotNullParameter(event2, "event2");
        Intrinsics.checkNotNullParameter(event3, "event3");
        int i = this.index.getAndAdd(3);
        Object[] objArr = this.trace;
        int i2 = this.mask;
        objArr[i & i2] = event1;
        objArr[(i + 1) & i2] = event2;
        objArr[i2 & (i + 2)] = event3;
    }

    @Override // kotlinx.atomicfu.TraceBase
    public void append(Object event1, Object event2, Object event3, Object event4) {
        Intrinsics.checkNotNullParameter(event1, "event1");
        Intrinsics.checkNotNullParameter(event2, "event2");
        Intrinsics.checkNotNullParameter(event3, "event3");
        Intrinsics.checkNotNullParameter(event4, "event4");
        int i = this.index.getAndAdd(4);
        Object[] objArr = this.trace;
        int i2 = this.mask;
        objArr[i & i2] = event1;
        objArr[(i + 1) & i2] = event2;
        objArr[(i + 2) & i2] = event3;
        objArr[i2 & (i + 3)] = event4;
    }

    public String toString() {
        StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
        int index = this.index.get();
        int start = this.mask & index;
        int i = this.size;
        int i2 = index > i ? index - i : 0;
        int pos = start;
        int cnt = 0;
        do {
            Object s = this.trace[pos];
            if (s != null) {
                int cnt2 = cnt + 1;
                if (cnt > 0) {
                    $this$toString_u24lambda_u240.append('\n');
                }
                $this$toString_u24lambda_u240.append(this.format.format(i2, s));
                i2++;
                cnt = cnt2;
            }
            int cnt3 = pos + 1;
            pos = cnt3 & this.mask;
        } while (pos != start);
        String sb = $this$toString_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
