package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;

/* compiled from: WorkQueue.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001aH\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0000J\u000e\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0000J\u0018\u0010#\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0015H\u0002J\u000e\u0010%\u001a\u00020\u0018*\u0004\u0018\u00010\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "()V", "blockingTasksInBuffer", "Lkotlinx/atomicfu/AtomicInt;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "bufferSize", "", "getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()I", "consumerIndex", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "size", "getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "add", "task", "fair", "", "addLast", "offloadAllWorkTo", "", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "poll", "pollBuffer", "pollTo", "queue", "tryStealBlockingFrom", "", "victim", "tryStealFrom", "tryStealLastScheduled", "blockingOnly", "decrementIfBlocking", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WorkQueue {
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    private final AtomicRef<Task> lastScheduledTask = AtomicFU.atomic((Object) null);
    private final AtomicInt producerIndex = AtomicFU.atomic(0);
    private final AtomicInt consumerIndex = AtomicFU.atomic(0);
    private final AtomicInt blockingTasksInBuffer = AtomicFU.atomic(0);

    public final int getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.producerIndex.getValue() - this.consumerIndex.getValue();
    }

    public final int getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.lastScheduledTask.getValue() != null ? getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() + 1 : getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
    }

    public final Task poll() {
        Task andSet = this.lastScheduledTask.getAndSet(null);
        return andSet == null ? pollBuffer() : andSet;
    }

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    public final Task add(Task task, boolean fair) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (fair) {
            return addLast(task);
        }
        Task previous = this.lastScheduledTask.getAndSet(task);
        if (previous == null) {
            return null;
        }
        return addLast(previous);
    }

    private final Task addLast(Task task) {
        if (task.taskContext.getTaskMode() == 1) {
            this.blockingTasksInBuffer.incrementAndGet();
        }
        if (getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 127) {
            return task;
        }
        int nextIndex = this.producerIndex.getValue() & WorkQueueKt.MASK;
        while (this.buffer.get(nextIndex) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(nextIndex, task);
        this.producerIndex.incrementAndGet();
        return null;
    }

    public final long tryStealFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 0 ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        Task task = victim.pollBuffer();
        if (task != null) {
            Task notAdded = add$default(this, task, false, 2, null);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (notAdded == null) {
                    return -1L;
                }
                throw new AssertionError();
            }
            return -1L;
        }
        return tryStealLastScheduled(victim, false);
    }

    public final long tryStealBlockingFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 0 ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        int end = victim.producerIndex.getValue();
        AtomicReferenceArray buffer = victim.buffer;
        for (int start = victim.consumerIndex.getValue(); start != end; start++) {
            int index = start & WorkQueueKt.MASK;
            if (victim.blockingTasksInBuffer.getValue() == 0) {
                break;
            }
            Task value = buffer.get(index);
            if (value != null) {
                Task $this$isBlocking$iv = value.taskContext.getTaskMode() == 1 ? 1 : null;
                if ($this$isBlocking$iv != null && buffer.compareAndSet(index, value, null)) {
                    victim.blockingTasksInBuffer.decrementAndGet();
                    add$default(this, value, false, 2, null);
                    return -1L;
                }
            }
        }
        return tryStealLastScheduled(victim, true);
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        Intrinsics.checkNotNullParameter(globalQueue, "globalQueue");
        Task it = this.lastScheduledTask.getAndSet(null);
        if (it != null) {
            globalQueue.addLast(it);
        }
        do {
        } while (pollTo(globalQueue));
    }

    private final long tryStealLastScheduled(WorkQueue victim, boolean blockingOnly) {
        Task lastScheduled;
        do {
            lastScheduled = victim.lastScheduledTask.getValue();
            if (lastScheduled == null) {
                return -2L;
            }
            if (blockingOnly) {
                if (!(lastScheduled.taskContext.getTaskMode() == 1)) {
                    return -2L;
                }
            }
            long time = TasksKt.schedulerTimeSource.nanoTime();
            long staleness = time - lastScheduled.submissionTime;
            if (staleness < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
                return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - staleness;
            }
        } while (!victim.lastScheduledTask.compareAndSet(lastScheduled, null));
        add$default(this, lastScheduled, false, 2, null);
        return -1L;
    }

    private final boolean pollTo(GlobalQueue queue) {
        Task task = pollBuffer();
        if (task == null) {
            return false;
        }
        queue.addLast(task);
        return true;
    }

    private final Task pollBuffer() {
        Task value;
        while (true) {
            int tailLocal = this.consumerIndex.getValue();
            if (tailLocal - this.producerIndex.getValue() == 0) {
                return null;
            }
            int index = tailLocal & WorkQueueKt.MASK;
            if (this.consumerIndex.compareAndSet(tailLocal, tailLocal + 1) && (value = this.buffer.getAndSet(index, null)) != null) {
                decrementIfBlocking(value);
                return value;
            }
        }
    }

    private final void decrementIfBlocking(Task $this$decrementIfBlocking) {
        if ($this$decrementIfBlocking == null) {
            return;
        }
        Task $this$isBlocking$iv = $this$decrementIfBlocking.taskContext.getTaskMode() == 1 ? 1 : null;
        if ($this$isBlocking$iv != null) {
            int value = this.blockingTasksInBuffer.decrementAndGet();
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(value >= 0)) {
                    throw new AssertionError();
                }
            }
        }
    }
}
