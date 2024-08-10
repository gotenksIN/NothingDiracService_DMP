package kotlinx.coroutines.internal;

import androidx.core.internal.view.SupportMenu;
import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.Segment;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u0014\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\r\u0010\u0018\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "id", "", "prev", "pointers", "", "(JLkotlinx/coroutines/internal/Segment;I)V", "cleanedAndPointers", "Lkotlinx/atomicfu/AtomicInt;", "getId", "()J", "maxSlots", "getMaxSlots", "()I", "removed", "", "getRemoved", "()Z", "decPointers", "decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "onSlotCleaned", "", "tryIncPointers", "tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    private final AtomicInt cleanedAndPointers;
    private final long id;

    public abstract int getMaxSlots();

    public Segment(long id, S s, int pointers) {
        super(s);
        this.id = id;
        this.cleanedAndPointers = AtomicFU.atomic(pointers << 16);
    }

    public final long getId() {
        return this.id;
    }

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers.getValue() == getMaxSlots() && !isTail();
    }

    public final boolean tryIncPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        int cur$iv;
        AtomicInt $this$addConditionally$iv = this.cleanedAndPointers;
        do {
            cur$iv = $this$addConditionally$iv.getValue();
            int it = (cur$iv != getMaxSlots() || isTail()) ? 1 : 0;
            if (it == 0) {
                return false;
            }
        } while (!$this$addConditionally$iv.compareAndSet(cur$iv, cur$iv + 65536));
        return true;
    }

    public final boolean decPointers$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.cleanedAndPointers.addAndGet(SupportMenu.CATEGORY_MASK) == getMaxSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (this.cleanedAndPointers.incrementAndGet() != getMaxSlots() || isTail()) {
            return;
        }
        remove();
    }
}
