package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Future.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, d2 = {"cancelFutureOnCancellation", "", "Lkotlinx/coroutines/CancellableContinuation;", "future", "Ljava/util/concurrent/Future;", "cancelFutureOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes4.dex */
public final /* synthetic */ class JobKt__FutureKt {
    public static final DisposableHandle cancelFutureOnCompletion(Job $this$cancelFutureOnCompletion, Future<?> future) {
        Intrinsics.checkNotNullParameter($this$cancelFutureOnCompletion, "<this>");
        Intrinsics.checkNotNullParameter(future, "future");
        return $this$cancelFutureOnCompletion.invokeOnCompletion(new CancelFutureOnCompletion(future));
    }

    public static final void cancelFutureOnCancellation(CancellableContinuation<?> cancellableContinuation, Future<?> future) {
        Intrinsics.checkNotNullParameter(cancellableContinuation, "<this>");
        Intrinsics.checkNotNullParameter(future, "future");
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel(future));
    }
}
