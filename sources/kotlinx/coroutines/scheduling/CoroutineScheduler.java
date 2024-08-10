package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStrings;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: CoroutineScheduler.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \b\u0000\u0018\u0000 H2\u00020\u00012\u00020\u0002:\u0003HIJB)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0011\u0010\r\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0007H\u0086\bJ\u0011\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0007H\u0082\bJ\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0004H\u0002J\u001a\u0010&\u001a\u00020 2\n\u0010'\u001a\u00060(j\u0002`)2\u0006\u0010*\u001a\u00020+J\u0011\u0010\u0012\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0007H\u0082\bJ\u000e\u0010,\u001a\b\u0018\u00010\u001dR\u00020\u0000H\u0002J\t\u0010-\u001a\u00020$H\u0082\bJ\t\u0010.\u001a\u00020\u0004H\u0082\bJ&\u0010/\u001a\u00020$2\n\u0010'\u001a\u00060(j\u0002`)2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00100\u001a\u00020\u0018J\u0014\u00101\u001a\u00020$2\n\u00102\u001a\u00060(j\u0002`)H\u0016J\t\u00103\u001a\u00020\u0007H\u0082\bJ\t\u00104\u001a\u00020\u0004H\u0082\bJ\u0014\u00105\u001a\u00020\u00042\n\u00106\u001a\u00060\u001dR\u00020\u0000H\u0002J\u000e\u00107\u001a\b\u0018\u00010\u001dR\u00020\u0000H\u0002J\u0012\u00108\u001a\u00020\u00182\n\u00106\u001a\u00060\u001dR\u00020\u0000J\"\u00109\u001a\u00020$2\n\u00106\u001a\u00060\u001dR\u00020\u00002\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004J\t\u0010<\u001a\u00020\u0007H\u0082\bJ\u000e\u0010=\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020\u0007J\u0010\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020\u0018H\u0002J\u0006\u0010B\u001a\u00020$J\b\u0010C\u001a\u00020\tH\u0016J\t\u0010D\u001a\u00020\u0018H\u0082\bJ\u0012\u0010E\u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u0007H\u0002J\b\u0010F\u001a\u00020\u0018H\u0002J$\u0010G\u001a\u0004\u0018\u00010 *\b\u0018\u00010\u001dR\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u00100\u001a\u00020\u0018H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\u00048Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\u00020\u00048Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0010\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00158\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u001dR\u00020\u00000\u001c8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;", "corePoolSize", "", "maxPoolSize", "idleWorkerKeepAliveNs", "", "schedulerName", "", "(IIJLjava/lang/String;)V", "_isTerminated", "Lkotlinx/atomicfu/AtomicBoolean;", "availableCpuPermits", "getAvailableCpuPermits", "()I", "controlState", "Lkotlinx/atomicfu/AtomicLong;", "createdWorkers", "getCreatedWorkers", "globalBlockingQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalCpuQueue", "isTerminated", "", "()Z", "parkedWorkersStack", "workers", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "addToGlobalQueue", "task", "Lkotlinx/coroutines/scheduling/Task;", "state", "blockingTasks", "close", "", "createNewWorker", "createTask", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "taskContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "currentWorker", "decrementBlockingTasks", "decrementCreatedWorkers", "dispatch", "tailDispatch", "execute", "command", "incrementBlockingTasks", "incrementCreatedWorkers", "parkedWorkersStackNextIndex", "worker", "parkedWorkersStackPop", "parkedWorkersStackPush", "parkedWorkersStackTopUpdate", "oldIndex", "newIndex", "releaseCpuPermit", "runSafely", "shutdown", "timeout", "signalBlockingWork", "skipUnpark", "signalCpuWork", "toString", "tryAcquireCpuPermit", "tryCreateWorker", "tryUnpark", "submitToLocalQueue", "Companion", "Worker", "WorkerState", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    private static final long BLOCKING_MASK = 4398044413952L;
    private static final int BLOCKING_SHIFT = 21;
    private static final int CLAIMED = 0;
    private static final long CPU_PERMITS_MASK = 9223367638808264704L;
    private static final int CPU_PERMITS_SHIFT = 42;
    private static final long CREATED_MASK = 2097151;
    public static final int MAX_SUPPORTED_POOL_SIZE = 2097150;
    public static final int MIN_SUPPORTED_POOL_SIZE = 1;
    private static final int PARKED = -1;
    private static final long PARKED_INDEX_MASK = 2097151;
    private static final long PARKED_VERSION_INC = 2097152;
    private static final long PARKED_VERSION_MASK = -2097152;
    private static final int TERMINATED = 1;
    private final AtomicBoolean _isTerminated;
    private final AtomicLong controlState;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    private final AtomicLong parkedWorkersStack;
    public final String schedulerName;
    public final ResizableAtomicArray<Worker> workers;
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");

    /* compiled from: CoroutineScheduler.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WorkerState.values().length];
            try {
                iArr[WorkerState.PARKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[WorkerState.BLOCKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[WorkerState.DORMANT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[WorkerState.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: CoroutineScheduler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "DORMANT", "TERMINATED", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int corePoolSize, int maxPoolSize, long idleWorkerKeepAliveNs, String schedulerName) {
        Intrinsics.checkNotNullParameter(schedulerName, "schedulerName");
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.idleWorkerKeepAliveNs = idleWorkerKeepAliveNs;
        this.schedulerName = schedulerName;
        if (!(corePoolSize >= 1)) {
            throw new IllegalArgumentException(("Core pool size " + corePoolSize + " should be at least 1").toString());
        }
        if (!(maxPoolSize >= corePoolSize)) {
            throw new IllegalArgumentException(("Max pool size " + maxPoolSize + " should be greater than or equals to core pool size " + corePoolSize).toString());
        }
        if (!(maxPoolSize <= 2097150)) {
            throw new IllegalArgumentException(("Max pool size " + maxPoolSize + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (!(idleWorkerKeepAliveNs > 0)) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + idleWorkerKeepAliveNs + " must be positive").toString());
        }
        this.globalCpuQueue = new GlobalQueue();
        this.globalBlockingQueue = new GlobalQueue();
        this.parkedWorkersStack = AtomicFU.atomic(0L);
        this.workers = new ResizableAtomicArray<>(corePoolSize + 1);
        this.controlState = AtomicFU.atomic(corePoolSize << 42);
        this._isTerminated = AtomicFU.atomic(false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ CoroutineScheduler(int r7, int r8, long r9, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto L8
            long r9 = kotlinx.coroutines.scheduling.TasksKt.IDLE_WORKER_KEEP_ALIVE_NS
            r3 = r9
            goto L9
        L8:
            r3 = r9
        L9:
            r9 = r12 & 8
            if (r9 == 0) goto L11
            java.lang.String r11 = "DefaultDispatcher"
            r5 = r11
            goto L12
        L11:
            r5 = r11
        L12:
            r0 = r6
            r1 = r7
            r2 = r8
            r0.<init>(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.<init>(int, int, long, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private final boolean addToGlobalQueue(Task task) {
        if (task.taskContext.getTaskMode() == 1) {
            return this.globalBlockingQueue.addLast(task);
        }
        return this.globalCpuQueue.addLast(task);
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int oldIndex, int newIndex) {
        int updIndex;
        Intrinsics.checkNotNullParameter(worker, "worker");
        AtomicLong $this$loop$iv = this.parkedWorkersStack;
        while (true) {
            long top = $this$loop$iv.getValue();
            int index = (int) (2097151 & top);
            long updVersion = (PARKED_VERSION_INC + top) & PARKED_VERSION_MASK;
            if (index == oldIndex) {
                if (newIndex == 0) {
                    updIndex = parkedWorkersStackNextIndex(worker);
                } else {
                    updIndex = newIndex;
                }
            } else {
                updIndex = index;
            }
            if (updIndex >= 0 && this.parkedWorkersStack.compareAndSet(top, updIndex | updVersion)) {
                return;
            }
        }
    }

    public final boolean parkedWorkersStackPush(Worker worker) {
        long top;
        long updVersion;
        int updIndex;
        Intrinsics.checkNotNullParameter(worker, "worker");
        if (worker.getNextParkedWorker() != NOT_IN_STACK) {
            return false;
        }
        AtomicLong $this$loop$iv = this.parkedWorkersStack;
        do {
            top = $this$loop$iv.getValue();
            int index = (int) (2097151 & top);
            updVersion = (PARKED_VERSION_INC + top) & PARKED_VERSION_MASK;
            updIndex = worker.getIndexInArray();
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if ((updIndex != 0 ? 1 : 0) == 0) {
                    throw new AssertionError();
                }
            }
            worker.setNextParkedWorker(this.workers.get(index));
        } while (!this.parkedWorkersStack.compareAndSet(top, updIndex | updVersion));
        return true;
    }

    private final Worker parkedWorkersStackPop() {
        AtomicLong $this$loop$iv = this.parkedWorkersStack;
        while (true) {
            long top = $this$loop$iv.getValue();
            int index = (int) (2097151 & top);
            Worker worker = this.workers.get(index);
            if (worker == null) {
                return null;
            }
            long updVersion = (PARKED_VERSION_INC + top) & PARKED_VERSION_MASK;
            int updIndex = parkedWorkersStackNextIndex(worker);
            if (updIndex >= 0 && this.parkedWorkersStack.compareAndSet(top, updIndex | updVersion)) {
                worker.setNextParkedWorker(NOT_IN_STACK);
                return worker;
            }
        }
    }

    private final int parkedWorkersStackNextIndex(Worker worker) {
        Object next = worker.getNextParkedWorker();
        while (next != NOT_IN_STACK) {
            if (next == null) {
                return 0;
            }
            Worker nextWorker = (Worker) next;
            int updIndex = nextWorker.getIndexInArray();
            if (updIndex != 0) {
                return updIndex;
            }
            next = nextWorker.getNextParkedWorker();
        }
        return -1;
    }

    private final int getCreatedWorkers() {
        return (int) (this.controlState.getValue() & 2097151);
    }

    private final int getAvailableCpuPermits() {
        long state$iv = this.controlState.getValue();
        return (int) ((CPU_PERMITS_MASK & state$iv) >> 42);
    }

    private final int createdWorkers(long state) {
        return (int) (2097151 & state);
    }

    private final int blockingTasks(long state) {
        return (int) ((BLOCKING_MASK & state) >> 21);
    }

    public final int availableCpuPermits(long state) {
        return (int) ((CPU_PERMITS_MASK & state) >> 42);
    }

    private final int incrementCreatedWorkers() {
        long state$iv = this.controlState.incrementAndGet();
        return (int) (2097151 & state$iv);
    }

    private final int decrementCreatedWorkers() {
        long state$iv = this.controlState.getAndDecrement();
        return (int) (2097151 & state$iv);
    }

    private final long incrementBlockingTasks() {
        return this.controlState.addAndGet(PARKED_VERSION_INC);
    }

    private final void decrementBlockingTasks() {
        this.controlState.addAndGet(PARKED_VERSION_MASK);
    }

    private final boolean tryAcquireCpuPermit() {
        long state;
        long update;
        AtomicLong $this$loop$iv = this.controlState;
        do {
            state = $this$loop$iv.getValue();
            int available = (int) ((CPU_PERMITS_MASK & state) >> 42);
            if (available == 0) {
                return false;
            }
            update = state - 4398046511104L;
        } while (!this.controlState.compareAndSet(state, update));
        return true;
    }

    private final long releaseCpuPermit() {
        return this.controlState.addAndGet(4398046511104L);
    }

    public final boolean isTerminated() {
        return this._isTerminated.getValue();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        Intrinsics.checkNotNullParameter(command, "command");
        dispatch$default(this, command, null, false, 6, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        shutdown(10000L);
    }

    public final void shutdown(long timeout) {
        int created;
        Task task;
        if (this._isTerminated.compareAndSet(false, true)) {
            Worker currentWorker = currentWorker();
            Object lock$iv = this.workers;
            synchronized (lock$iv) {
                created = (int) (this.controlState.getValue() & 2097151);
            }
            int i = 1;
            if (1 <= created) {
                while (true) {
                    Worker worker = this.workers.get(i);
                    Intrinsics.checkNotNull(worker);
                    Worker worker2 = worker;
                    if (worker2 != currentWorker) {
                        while (worker2.isAlive()) {
                            LockSupport.unpark(worker2);
                            worker2.join(timeout);
                        }
                        WorkerState state = worker2.state;
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if ((state == WorkerState.TERMINATED ? 1 : 0) == 0) {
                                throw new AssertionError();
                            }
                        }
                        worker2.localQueue.offloadAllWorkTo(this.globalBlockingQueue);
                    }
                    if (i == created) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            this.globalBlockingQueue.close();
            this.globalCpuQueue.close();
            while (true) {
                if (currentWorker != null) {
                    task = currentWorker.findTask(true);
                    if (task != null) {
                        continue;
                        runSafely(task);
                    }
                }
                task = this.globalCpuQueue.removeFirstOrNull();
                if (task == null && (task = this.globalBlockingQueue.removeFirstOrNull()) == null) {
                    break;
                }
                runSafely(task);
            }
            if (currentWorker != null) {
                currentWorker.tryReleaseCpu(WorkerState.TERMINATED);
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                long state$iv$iv = this.controlState.getValue();
                if (!(((int) ((CPU_PERMITS_MASK & state$iv$iv) >> 42)) == this.corePoolSize)) {
                    throw new AssertionError();
                }
            }
            this.parkedWorkersStack.setValue(0L);
            this.controlState.setValue(0L);
        }
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            taskContext = TasksKt.NonBlockingContext;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.dispatch(runnable, taskContext, z);
    }

    public final void dispatch(Runnable block, TaskContext taskContext, boolean tailDispatch) {
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        if (timeSource != null) {
            timeSource.trackTask();
        }
        Task task = createTask(block, taskContext);
        Worker currentWorker = currentWorker();
        Task notAdded = submitToLocalQueue(currentWorker, task, tailDispatch);
        if (notAdded != null && !addToGlobalQueue(notAdded)) {
            throw new RejectedExecutionException(this.schedulerName + " was terminated");
        }
        boolean skipUnpark = tailDispatch && currentWorker != null;
        if (task.taskContext.getTaskMode() == 0) {
            if (skipUnpark) {
                return;
            }
            signalCpuWork();
            return;
        }
        signalBlockingWork(skipUnpark);
    }

    public final Task createTask(Runnable block, TaskContext taskContext) {
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
        if (block instanceof Task) {
            ((Task) block).submissionTime = nanoTime;
            ((Task) block).taskContext = taskContext;
            return (Task) block;
        }
        return new TaskImpl(block, nanoTime, taskContext);
    }

    private final void signalBlockingWork(boolean skipUnpark) {
        long stateSnapshot = this.controlState.addAndGet(PARKED_VERSION_INC);
        if (skipUnpark || tryUnpark() || tryCreateWorker(stateSnapshot)) {
            return;
        }
        tryUnpark();
    }

    public final void signalCpuWork() {
        if (tryUnpark() || tryCreateWorker$default(this, 0L, 1, null)) {
            return;
        }
        tryUnpark();
    }

    static /* synthetic */ boolean tryCreateWorker$default(CoroutineScheduler coroutineScheduler, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineScheduler.controlState.getValue();
        }
        return coroutineScheduler.tryCreateWorker(j);
    }

    private final boolean tryCreateWorker(long state) {
        int created = (int) (2097151 & state);
        int blocking = (int) ((BLOCKING_MASK & state) >> 21);
        int cpuWorkers = RangesKt.coerceAtLeast(created - blocking, 0);
        if (cpuWorkers < this.corePoolSize) {
            int newCpuWorkers = createNewWorker();
            if (newCpuWorkers == 1 && this.corePoolSize > 1) {
                createNewWorker();
            }
            if (newCpuWorkers > 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean tryUnpark() {
        Worker worker;
        do {
            worker = parkedWorkersStackPop();
            if (worker == null) {
                return false;
            }
        } while (!worker.getWorkerCtl().compareAndSet(-1, 0));
        LockSupport.unpark(worker);
        return true;
    }

    private final int createNewWorker() {
        Object lock$iv = this.workers;
        synchronized (lock$iv) {
            if (isTerminated()) {
                return -1;
            }
            long state = this.controlState.getValue();
            int created = (int) (state & 2097151);
            int blocking = (int) ((BLOCKING_MASK & state) >> 21);
            int cpuWorkers = RangesKt.coerceAtLeast(created - blocking, 0);
            if (cpuWorkers >= this.corePoolSize) {
                return 0;
            }
            if (created >= this.maxPoolSize) {
                return 0;
            }
            int newIndex = ((int) (this.controlState.getValue() & 2097151)) + 1;
            if (newIndex > 0 && this.workers.get(newIndex) == null) {
                Worker worker = new Worker(this, newIndex);
                this.workers.setSynchronized(newIndex, worker);
                long state$iv$iv = this.controlState.incrementAndGet();
                if (!(newIndex == ((int) (state$iv$iv & 2097151)))) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                worker.start();
                return cpuWorkers + 1;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final Task submitToLocalQueue(Worker $this$submitToLocalQueue, Task task, boolean tailDispatch) {
        if ($this$submitToLocalQueue == null || $this$submitToLocalQueue.state == WorkerState.TERMINATED) {
            return task;
        }
        if (task.taskContext.getTaskMode() == 0 && $this$submitToLocalQueue.state == WorkerState.BLOCKING) {
            return task;
        }
        $this$submitToLocalQueue.mayHaveLocalTasks = true;
        return $this$submitToLocalQueue.localQueue.add(task, tailDispatch);
    }

    private final Worker currentWorker() {
        Thread currentThread = Thread.currentThread();
        Worker worker = currentThread instanceof Worker ? (Worker) currentThread : null;
        if (worker == null) {
            return null;
        }
        Worker it = worker;
        if (Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            return worker;
        }
        return null;
    }

    public String toString() {
        int parkedWorkers = 0;
        int blockingWorkers = 0;
        int cpuWorkers = 0;
        int dormant = 0;
        int terminated = 0;
        ArrayList queueSizes = new ArrayList();
        int currentLength = this.workers.currentLength();
        for (int index = 1; index < currentLength; index++) {
            Worker worker = this.workers.get(index);
            if (worker != null) {
                int queueSize = worker.localQueue.getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
                switch (WhenMappings.$EnumSwitchMapping$0[worker.state.ordinal()]) {
                    case 1:
                        parkedWorkers++;
                        break;
                    case 2:
                        blockingWorkers++;
                        queueSizes.add(queueSize + "b");
                        break;
                    case 3:
                        cpuWorkers++;
                        queueSizes.add(queueSize + "c");
                        break;
                    case 4:
                        dormant++;
                        if (queueSize > 0) {
                            queueSizes.add(queueSize + "d");
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        terminated++;
                        break;
                }
            }
        }
        long state = this.controlState.getValue();
        return this.schedulerName + "@" + DebugStrings.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + cpuWorkers + ", blocking = " + blockingWorkers + ", parked = " + parkedWorkers + ", dormant = " + dormant + ", terminated = " + terminated + "}, running workers queues = " + queueSizes + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int) (state & 2097151)) + ", blocking tasks = " + ((int) ((state & BLOCKING_MASK) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int) ((state & CPU_PERMITS_MASK) >> 42))) + "}]";
    }

    public final void runSafely(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        try {
            task.run();
        } catch (Throwable e) {
            try {
                Thread thread = Thread.currentThread();
                thread.getUncaughtExceptionHandler().uncaughtException(thread, e);
                AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
                if (timeSource == null) {
                }
            } finally {
                AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
                if (timeSource2 != null) {
                    timeSource2.unTrackTask();
                }
            }
        }
    }

    /* compiled from: CoroutineScheduler.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0003H\u0002J\u0010\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0003H\u0002J\u0010\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010+\u001a\u00020\u000eH\u0002J\u0010\u0010,\u001a\u0004\u0018\u00010)2\u0006\u0010+\u001a\u00020\u000eJ\u0010\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020\u0003H\u0002J\b\u0010/\u001a\u00020\u000eH\u0002J\u000e\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u0003J\b\u00102\u001a\u00020$H\u0002J\n\u00103\u001a\u0004\u0018\u00010)H\u0002J\b\u00104\u001a\u00020$H\u0016J\b\u00105\u001a\u00020$H\u0002J\b\u00106\u001a\u00020\u000eH\u0002J\b\u00107\u001a\u00020$H\u0002J\u000e\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u001dJ\u0012\u0010:\u001a\u0004\u0018\u00010)2\u0006\u0010;\u001a\u00020\u000eH\u0002J\b\u0010<\u001a\u00020$H\u0002R$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00198Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006="}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Ljava/lang/Thread;", "index", "", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", "indexInArray", "getIndexInArray", "()I", "setIndexInArray", "(I)V", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "mayHaveLocalTasks", "", "minDelayUntilStealableTaskNs", "", "nextParkedWorker", "", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "rngState", "scheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "getScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "terminationDeadline", "workerCtl", "Lkotlinx/atomicfu/AtomicInt;", "getWorkerCtl", "()Lkotlinx/atomicfu/AtomicInt;", "afterTask", "", "taskMode", "beforeTask", "executeTask", "task", "Lkotlinx/coroutines/scheduling/Task;", "findAnyTask", "scanLocalQueue", "findTask", "idleReset", "mode", "inStack", "nextInt", "upperBound", "park", "pollGlobalQueues", "run", "runWorker", "tryAcquireCpuPermit", "tryPark", "tryReleaseCpu", "newState", "trySteal", "blockingOnly", "tryTerminateWorker", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class Worker extends Thread {
        private volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        private long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        private int rngState;
        public WorkerState state;
        private long terminationDeadline;
        private final AtomicInt workerCtl;

        private Worker() {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.state = WorkerState.DORMANT;
            this.workerCtl = AtomicFU.atomic(0);
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            this.rngState = Random.INSTANCE.nextInt();
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final void setIndexInArray(int index) {
            setName(CoroutineScheduler.this.schedulerName + "-worker-" + (index == 0 ? "TERMINATED" : String.valueOf(index)));
            this.indexInArray = index;
        }

        public Worker(CoroutineScheduler this$0, int index) {
            this();
            setIndexInArray(index);
        }

        public final CoroutineScheduler getScheduler() {
            return CoroutineScheduler.this;
        }

        public final AtomicInt getWorkerCtl() {
            return this.workerCtl;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final void setNextParkedWorker(Object obj) {
            this.nextParkedWorker = obj;
        }

        private final boolean tryAcquireCpuPermit() {
            CoroutineScheduler this_$iv;
            if (this.state == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            CoroutineScheduler this_$iv2 = CoroutineScheduler.this;
            AtomicLong $this$loop$iv$iv = this_$iv2.controlState;
            while (true) {
                long state$iv = $this$loop$iv$iv.getValue();
                int available$iv = (int) ((CoroutineScheduler.CPU_PERMITS_MASK & state$iv) >> 42);
                if (available$iv == 0) {
                    this_$iv = null;
                    break;
                }
                long update$iv = state$iv - 4398046511104L;
                if (this_$iv2.controlState.compareAndSet(state$iv, update$iv)) {
                    this_$iv = 1;
                    break;
                }
            }
            if (this_$iv != null) {
                this.state = WorkerState.CPU_ACQUIRED;
                return true;
            }
            return false;
        }

        public final boolean tryReleaseCpu(WorkerState newState) {
            Intrinsics.checkNotNullParameter(newState, "newState");
            WorkerState previousState = this.state;
            boolean hadCpu = previousState == WorkerState.CPU_ACQUIRED;
            if (hadCpu) {
                CoroutineScheduler this_$iv = CoroutineScheduler.this;
                this_$iv.controlState.addAndGet(4398046511104L);
            }
            if (previousState != newState) {
                this.state = newState;
            }
            return hadCpu;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            runWorker();
        }

        private final void runWorker() {
            boolean rescanned = false;
            while (!CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                Task task = findTask(this.mayHaveLocalTasks);
                if (task != null) {
                    rescanned = false;
                    this.minDelayUntilStealableTaskNs = 0L;
                    executeTask(task);
                } else {
                    this.mayHaveLocalTasks = false;
                    if (this.minDelayUntilStealableTaskNs != 0) {
                        if (!rescanned) {
                            rescanned = true;
                        } else {
                            rescanned = false;
                            tryReleaseCpu(WorkerState.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                            this.minDelayUntilStealableTaskNs = 0L;
                        }
                    } else {
                        tryPark();
                    }
                }
            }
            tryReleaseCpu(WorkerState.TERMINATED);
        }

        private final void tryPark() {
            if (!inStack()) {
                CoroutineScheduler.this.parkedWorkersStackPush(this);
                return;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(this.localQueue.getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 0)) {
                    throw new AssertionError();
                }
            }
            this.workerCtl.setValue(-1);
            while (inStack() && this.workerCtl.getValue() == -1 && !CoroutineScheduler.this.isTerminated() && this.state != WorkerState.TERMINATED) {
                tryReleaseCpu(WorkerState.PARKING);
                Thread.interrupted();
                park();
            }
        }

        private final boolean inStack() {
            return this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK;
        }

        private final void executeTask(Task task) {
            int taskMode = task.taskContext.getTaskMode();
            idleReset(taskMode);
            beforeTask(taskMode);
            CoroutineScheduler.this.runSafely(task);
            afterTask(taskMode);
        }

        private final void beforeTask(int taskMode) {
            if (taskMode != 0 && tryReleaseCpu(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.signalCpuWork();
            }
        }

        private final void afterTask(int taskMode) {
            if (taskMode == 0) {
                return;
            }
            CoroutineScheduler this_$iv = CoroutineScheduler.this;
            this_$iv.controlState.addAndGet(CoroutineScheduler.PARKED_VERSION_MASK);
            WorkerState currentState = this.state;
            if (currentState != WorkerState.TERMINATED) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(currentState == WorkerState.BLOCKING)) {
                        throw new AssertionError();
                    }
                }
                this.state = WorkerState.DORMANT;
            }
        }

        public final int nextInt(int upperBound) {
            int r = this.rngState;
            int r2 = r ^ (r << 13);
            int r3 = r2 ^ (r2 >> 17);
            int r4 = r3 ^ (r3 << 5);
            this.rngState = r4;
            int mask = upperBound - 1;
            if ((mask & upperBound) == 0) {
                return r4 & mask;
            }
            return (Integer.MAX_VALUE & r4) % upperBound;
        }

        private final void park() {
            if (this.terminationDeadline == 0) {
                this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.idleWorkerKeepAliveNs);
            if (System.nanoTime() - this.terminationDeadline >= 0) {
                this.terminationDeadline = 0L;
                tryTerminateWorker();
            }
        }

        private final void tryTerminateWorker() {
            Object lock$iv = CoroutineScheduler.this.workers;
            CoroutineScheduler this_$iv = CoroutineScheduler.this;
            synchronized (lock$iv) {
                if (this_$iv.isTerminated()) {
                    return;
                }
                int value = (int) (this_$iv.controlState.getValue() & 2097151);
                int $i$f$getCreatedWorkers = this_$iv.corePoolSize;
                if (value <= $i$f$getCreatedWorkers) {
                    return;
                }
                if (this.workerCtl.compareAndSet(-1, 1)) {
                    int oldIndex = this.indexInArray;
                    setIndexInArray(0);
                    this_$iv.parkedWorkersStackTopUpdate(this, oldIndex, 0);
                    long state$iv$iv = this_$iv.controlState.getAndDecrement();
                    int lastIndex = (int) (2097151 & state$iv$iv);
                    if (lastIndex != oldIndex) {
                        Worker worker = this_$iv.workers.get(lastIndex);
                        Intrinsics.checkNotNull(worker);
                        Worker lastWorker = worker;
                        this_$iv.workers.setSynchronized(oldIndex, lastWorker);
                        lastWorker.setIndexInArray(oldIndex);
                        this_$iv.parkedWorkersStackTopUpdate(lastWorker, lastIndex, oldIndex);
                    }
                    this_$iv.workers.setSynchronized(lastIndex, null);
                    Unit unit = Unit.INSTANCE;
                    this.state = WorkerState.TERMINATED;
                }
            }
        }

        private final void idleReset(int mode) {
            this.terminationDeadline = 0L;
            if (this.state == WorkerState.PARKING) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(mode == 1)) {
                        throw new AssertionError();
                    }
                }
                this.state = WorkerState.BLOCKING;
            }
        }

        public final Task findTask(boolean scanLocalQueue) {
            Task task;
            if (tryAcquireCpuPermit()) {
                return findAnyTask(scanLocalQueue);
            }
            if (scanLocalQueue) {
                task = this.localQueue.poll();
                if (task == null) {
                    task = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
                }
            } else {
                task = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            return task == null ? trySteal(true) : task;
        }

        private final Task findAnyTask(boolean scanLocalQueue) {
            Task it;
            Task it2;
            if (scanLocalQueue) {
                boolean globalFirst = nextInt(CoroutineScheduler.this.corePoolSize * 2) == 0;
                if (globalFirst && (it2 = pollGlobalQueues()) != null) {
                    return it2;
                }
                Task it3 = this.localQueue.poll();
                if (it3 != null) {
                    return it3;
                }
                if (!globalFirst && (it = pollGlobalQueues()) != null) {
                    return it;
                }
            } else {
                Task it4 = pollGlobalQueues();
                if (it4 != null) {
                    return it4;
                }
            }
            return trySteal(false);
        }

        private final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task it = CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                if (it != null) {
                    return it;
                }
                return CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            Task it2 = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            if (it2 != null) {
                return it2;
            }
            return CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
        }

        private final Task trySteal(boolean blockingOnly) {
            int currentIndex;
            long tryStealFrom;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if ((this.localQueue.getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 0 ? 1 : 0) == 0) {
                    throw new AssertionError();
                }
            }
            CoroutineScheduler this_$iv = CoroutineScheduler.this;
            int created = (int) (this_$iv.controlState.getValue() & 2097151);
            if (created < 2) {
                return null;
            }
            int currentIndex2 = nextInt(created);
            long minDelay = Long.MAX_VALUE;
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            int i = 0;
            while (true) {
                if (i >= created) {
                    this.minDelayUntilStealableTaskNs = minDelay != Long.MAX_VALUE ? minDelay : 0L;
                    return null;
                }
                int currentIndex3 = currentIndex2 + 1;
                if (currentIndex3 > created) {
                    currentIndex3 = 1;
                }
                Worker worker = coroutineScheduler.workers.get(currentIndex3);
                if (worker == null || worker == this) {
                    currentIndex = currentIndex3;
                } else {
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (!(this.localQueue.getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 0)) {
                            throw new AssertionError();
                        }
                    }
                    if (blockingOnly) {
                        tryStealFrom = this.localQueue.tryStealBlockingFrom(worker.localQueue);
                    } else {
                        tryStealFrom = this.localQueue.tryStealFrom(worker.localQueue);
                    }
                    long stealResult = tryStealFrom;
                    currentIndex = currentIndex3;
                    if (stealResult == -1) {
                        return this.localQueue.poll();
                    }
                    if (stealResult > 0) {
                        minDelay = Math.min(minDelay, stealResult);
                    }
                }
                i++;
                currentIndex2 = currentIndex;
            }
        }
    }
}
