package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;

/* compiled from: Select.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/selects/SeqNumber;", "", "()V", "number", "Lkotlinx/atomicfu/AtomicLong;", "next", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SeqNumber {
    private final AtomicLong number = AtomicFU.atomic(1L);

    public final long next() {
        return this.number.incrementAndGet();
    }
}
