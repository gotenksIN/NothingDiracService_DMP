package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicFU_commonKt;
import kotlinx.coroutines.internal.Segment;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J%\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0086\bJ\u0013\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u0006H\u0086\bJ\u001d\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0086\bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0086\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "Lkotlinx/coroutines/internal/Segment;", "id", "", "prev", "pointers", "", "(JLkotlinx/coroutines/sync/SemaphoreSegment;I)V", "acquirers", "Lkotlinx/atomicfu/AtomicArray;", "", "getAcquirers", "()Lkotlinx/atomicfu/AtomicArray;", "maxSlots", "getMaxSlots", "()I", "cancel", "", "index", "cas", "", "expected", "value", "get", "getAndSet", "set", "toString", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    private final AtomicArray<Object> acquirers;

    public SemaphoreSegment(long id, SemaphoreSegment prev, int pointers) {
        super(id, prev, pointers);
        int i;
        i = SemaphoreKt.SEGMENT_SIZE;
        this.acquirers = AtomicFU_commonKt.atomicArrayOfNulls(i);
    }

    public final AtomicArray<Object> getAcquirers() {
        return this.acquirers;
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        int i;
        i = SemaphoreKt.SEGMENT_SIZE;
        return i;
    }

    public final Object get(int index) {
        return getAcquirers().get(index).getValue();
    }

    public final void set(int index, Object value) {
        getAcquirers().get(index).setValue(value);
    }

    public final boolean cas(int index, Object expected, Object value) {
        return getAcquirers().get(index).compareAndSet(expected, value);
    }

    public final Object getAndSet(int index, Object value) {
        return getAcquirers().get(index).getAndSet(value);
    }

    public final void cancel(int index) {
        Object value$iv;
        value$iv = SemaphoreKt.CANCELLED;
        getAcquirers().get(index).setValue(value$iv);
        onSlotCleaned();
    }

    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + "]";
    }
}
