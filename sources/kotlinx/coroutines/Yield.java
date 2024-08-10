package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: Yield.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"yield", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* renamed from: kotlinx.coroutines.YieldKt, reason: use source file name */
/* loaded from: classes4.dex */
public final class Yield {
    public static final Object yield(Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        CoroutineContext context = continuation.getContext();
        JobKt.ensureActive(context);
        Continuation intercepted = IntrinsicsKt.intercepted(continuation);
        DispatchedContinuation cont = intercepted instanceof DispatchedContinuation ? (DispatchedContinuation) intercepted : null;
        if (cont == null) {
            coroutine_suspended = Unit.INSTANCE;
        } else {
            if (cont.dispatcher.isDispatchNeeded(context)) {
                cont.dispatchYield$external__kotlinx_coroutines__android_common__kotlinx_coroutines(context, Unit.INSTANCE);
            } else {
                YieldContext yieldContext = new YieldContext();
                cont.dispatchYield$external__kotlinx_coroutines__android_common__kotlinx_coroutines(context.plus(yieldContext), Unit.INSTANCE);
                if (yieldContext.dispatcherWasUnconfined) {
                    coroutine_suspended = DispatchedContinuationKt.yieldUndispatched(cont) ? IntrinsicsKt.getCOROUTINE_SUSPENDED() : Unit.INSTANCE;
                }
            }
            coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }
}
