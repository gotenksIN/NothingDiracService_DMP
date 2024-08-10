package kotlinx.coroutines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThreadPoolDispatcher.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ThreadPoolDispatcherKt {
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return newFixedThreadPoolContext(1, name);
    }

    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(final int nThreads, final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (!(nThreads >= 1)) {
            throw new IllegalArgumentException(("Expected at least one thread, but " + nThreads + " specified").toString());
        }
        final AtomicInteger threadNo = new AtomicInteger();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(nThreads, new ThreadFactory() { // from class: kotlinx.coroutines.ThreadPoolDispatcherKt$newFixedThreadPoolContext$executor$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                String str;
                if (nThreads == 1) {
                    str = name;
                } else {
                    str = name + "-" + threadNo.incrementAndGet();
                }
                Thread t = new Thread(runnable, str);
                t.setDaemon(true);
                return t;
            }
        });
        Intrinsics.checkNotNullExpressionValue(executor, "executor");
        return ExecutorsKt.from((ExecutorService) executor);
    }
}
