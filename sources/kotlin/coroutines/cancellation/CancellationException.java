package kotlin.coroutines.cancellation;

import kotlin.Metadata;

/* compiled from: CancellationException.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b*\u001a\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¨\u0006\n"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "Lkotlin/SinceKotlin;", "version", "1.4", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* renamed from: kotlin.coroutines.cancellation.CancellationExceptionKt, reason: use source file name */
/* loaded from: classes4.dex */
public final class CancellationException {
    public static /* synthetic */ void CancellationException$annotations() {
    }

    private static final java.util.concurrent.CancellationException CancellationException(String message, Throwable cause) {
        java.util.concurrent.CancellationException it = new java.util.concurrent.CancellationException(message);
        it.initCause(cause);
        return it;
    }

    private static final java.util.concurrent.CancellationException CancellationException(Throwable cause) {
        java.util.concurrent.CancellationException it = new java.util.concurrent.CancellationException(cause != null ? cause.toString() : null);
        it.initCause(cause);
        return it;
    }
}
