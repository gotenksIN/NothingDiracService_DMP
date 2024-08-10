package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: Timeout.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\n\b\u0001\u0010\u0002 \u0000*\u0002H\u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u0005B\u001b\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\nJ\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/TimeoutCoroutine;", "U", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "time", "", "uCont", "Lkotlin/coroutines/Continuation;", "(JLkotlin/coroutines/Continuation;)V", "nameString", "", "nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "run", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
final class TimeoutCoroutine<U, T extends U> extends ScopeCoroutine<T> implements Runnable {
    public final long time;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeoutCoroutine(long time, Continuation<? super U> uCont) {
        super(uCont.getContext(), uCont);
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        this.time = time;
    }

    @Override // java.lang.Runnable
    public void run() {
        cancelCoroutine(TimeoutKt.TimeoutCancellationException(this.time, this));
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    public String nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return super.nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() + "(timeMillis=" + this.time + ")";
    }
}
