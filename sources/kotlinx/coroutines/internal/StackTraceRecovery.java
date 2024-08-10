package kotlinx.coroutines.internal;

import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.DebugKt;

/* compiled from: StackTraceRecovery.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u001a\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\t\u001a\u00020\u0001H\u0007\u001a9\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u0002H\u000b2\u0006\u0010\u000e\u001a\u0002H\u000b2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00102\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002\u001a1\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\fH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a+\u0010\u001f\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002¢\u0006\u0002\u0010 \u001a\u001f\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a,\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030#H\u0080\b¢\u0006\u0002\u0010$\u001a!\u0010%\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0002¢\u0006\u0002\u0010\"\u001a \u0010&\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0080\b¢\u0006\u0002\u0010\"\u001a\u001f\u0010'\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a1\u0010(\u001a\u0018\u0012\u0004\u0012\u0002H\u000b\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00190)\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020,*\u00060\u0007j\u0002`\b2\n\u0010-\u001a\u00060\u0007j\u0002`\bH\u0002\u001a#\u0010.\u001a\u00020/*\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0006\u00100\u001a\u00020\u0001H\u0002¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\u0017*\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000\u001a\u0010\u00103\u001a\u00020,*\u00060\u0007j\u0002`\bH\u0000\u001a\u001b\u00104\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010\"\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u00105\"\u00020\u00142\u00020\u0014*\f\b\u0000\u00106\"\u00020\u00072\u00020\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"baseContinuationImplClass", "", "baseContinuationImplClassName", "kotlin.jvm.PlatformType", "stackTraceRecoveryClass", "stackTraceRecoveryClassName", "artificialFrame", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "message", "createFinalException", "E", "", "cause", "result", "resultStackTrace", "Ljava/util/ArrayDeque;", "(Ljava/lang/Throwable;Ljava/lang/Throwable;Ljava/util/ArrayDeque;)Ljava/lang/Throwable;", "createStackTrace", "continuation", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "mergeRecoveredTraces", "", "recoveredStacktrace", "", "([Ljava/lang/StackTraceElement;Ljava/util/ArrayDeque;)V", "recoverAndThrow", "", "exception", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverFromStackFrame", "(Ljava/lang/Throwable;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/lang/Throwable;", "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Throwable;", "tryCopyAndVerify", "unwrap", "unwrapImpl", "causeAndStacktrace", "Lkotlin/Pair;", "(Ljava/lang/Throwable;)Lkotlin/Pair;", "elementWiseEquals", "", "e", "frameIndex", "", "methodName", "([Ljava/lang/StackTraceElement;Ljava/lang/String;)I", "initCause", "isArtificial", "sanitizeStackTrace", "CoroutineStackFrame", "StackTraceElement", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* renamed from: kotlinx.coroutines.internal.StackTraceRecoveryKt, reason: use source file name */
/* loaded from: classes4.dex */
public final class StackTraceRecovery {
    private static final String baseContinuationImplClass = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
    private static final String baseContinuationImplClassName;
    private static final String stackTraceRecoveryClass = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
    private static final String stackTraceRecoveryClassName;

    public static /* synthetic */ void CoroutineStackFrame$annotations() {
    }

    public static /* synthetic */ void StackTraceElement$annotations() {
    }

    static {
        Object obj;
        Object obj2;
        Object obj3 = stackTraceRecoveryClass;
        Object obj4 = baseContinuationImplClass;
        try {
            Result.Companion companion = Result.INSTANCE;
            obj = Result.m856constructorimpl(Class.forName(baseContinuationImplClass).getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            obj = Result.m856constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m859exceptionOrNullimpl(obj) == null) {
            obj4 = obj;
        }
        baseContinuationImplClassName = (String) obj4;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            obj2 = Result.m856constructorimpl(Class.forName(stackTraceRecoveryClass).getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            obj2 = Result.m856constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m859exceptionOrNullimpl(obj2) == null) {
            obj3 = obj2;
        }
        stackTraceRecoveryClassName = (String) obj3;
    }

    public static final <E extends Throwable> E recoverStackTrace(E exception) {
        Throwable tryCopyAndVerify;
        Intrinsics.checkNotNullParameter(exception, "exception");
        return (DebugKt.getRECOVER_STACK_TRACES() && (tryCopyAndVerify = tryCopyAndVerify(exception)) != null) ? (E) sanitizeStackTrace(tryCopyAndVerify) : exception;
    }

    private static final <E extends Throwable> E sanitizeStackTrace(E e) {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = e.getStackTrace();
        int size = stackTrace.length;
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        String stackTraceRecoveryClassName2 = stackTraceRecoveryClassName;
        Intrinsics.checkNotNullExpressionValue(stackTraceRecoveryClassName2, "stackTraceRecoveryClassName");
        int lastIntrinsic = frameIndex(stackTrace, stackTraceRecoveryClassName2);
        int startIndex = lastIntrinsic + 1;
        String baseContinuationImplClassName2 = baseContinuationImplClassName;
        Intrinsics.checkNotNullExpressionValue(baseContinuationImplClassName2, "baseContinuationImplClassName");
        int endIndex = frameIndex(stackTrace, baseContinuationImplClassName2);
        int adjustment = endIndex == -1 ? 0 : size - endIndex;
        int i = (size - lastIntrinsic) - adjustment;
        StackTraceElement[] trace = new StackTraceElement[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stackTraceElement = artificialFrame("Coroutine boundary");
            } else {
                stackTraceElement = stackTrace[(startIndex + i2) - 1];
            }
            trace[i2] = stackTraceElement;
        }
        e.setStackTrace(trace);
        return e;
    }

    public static final <E extends Throwable> E recoverStackTrace(E exception, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
            return (E) recoverFromStackFrame(exception, (CoroutineStackFrame) continuation);
        }
        return exception;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E extends Throwable> E recoverFromStackFrame(E e, CoroutineStackFrame coroutineStackFrame) {
        Pair causeAndStacktrace = causeAndStacktrace(e);
        Throwable th = (Throwable) causeAndStacktrace.component1();
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) causeAndStacktrace.component2();
        Throwable tryCopyAndVerify = tryCopyAndVerify(th);
        if (tryCopyAndVerify == null) {
            return e;
        }
        ArrayDeque<StackTraceElement> createStackTrace = createStackTrace(coroutineStackFrame);
        if (createStackTrace.isEmpty()) {
            return e;
        }
        if (th != e) {
            mergeRecoveredTraces(stackTraceElementArr, createStackTrace);
        }
        return (E) createFinalException(th, tryCopyAndVerify, createStackTrace);
    }

    private static final <E extends Throwable> E tryCopyAndVerify(E e) {
        E e2 = (E) ExceptionsConstructorKt.tryCopyException(e);
        if (e2 == null) {
            return null;
        }
        if ((e instanceof CopyableThrowable) || Intrinsics.areEqual(e2.getMessage(), e.getMessage())) {
            return e2;
        }
        return null;
    }

    private static final <E extends Throwable> E createFinalException(E e, E e2, ArrayDeque<StackTraceElement> arrayDeque) {
        arrayDeque.addFirst(artificialFrame("Coroutine boundary"));
        StackTraceElement[] causeTrace = e.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(causeTrace, "causeTrace");
        String baseContinuationImplClassName2 = baseContinuationImplClassName;
        Intrinsics.checkNotNullExpressionValue(baseContinuationImplClassName2, "baseContinuationImplClassName");
        int size = frameIndex(causeTrace, baseContinuationImplClassName2);
        int i = 0;
        if (size == -1) {
            ArrayDeque<StackTraceElement> $this$toTypedArray$iv = arrayDeque;
            e2.setStackTrace((StackTraceElement[]) $this$toTypedArray$iv.toArray(new StackTraceElement[0]));
            return e2;
        }
        StackTraceElement[] mergedStackTrace = new StackTraceElement[arrayDeque.size() + size];
        for (int i2 = 0; i2 < size; i2++) {
            mergedStackTrace[i2] = causeTrace[i2];
        }
        Iterator<StackTraceElement> it = arrayDeque.iterator();
        while (it.hasNext()) {
            int index = i;
            i++;
            StackTraceElement element = it.next();
            mergedStackTrace[size + index] = element;
        }
        e2.setStackTrace(mergedStackTrace);
        return e2;
    }

    private static final <E extends Throwable> Pair<E, StackTraceElement[]> causeAndStacktrace(E e) {
        boolean z;
        Throwable cause = e.getCause();
        if (cause != null && Intrinsics.areEqual(cause.getClass(), e.getClass())) {
            StackTraceElement[] currentTrace = e.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(currentTrace, "currentTrace");
            int length = currentTrace.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    StackTraceElement it = currentTrace[i];
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (isArtificial(it)) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return TuplesKt.to(cause, currentTrace);
            }
            return TuplesKt.to(e, new StackTraceElement[0]);
        }
        return TuplesKt.to(e, new StackTraceElement[0]);
    }

    private static final void mergeRecoveredTraces(StackTraceElement[] recoveredStacktrace, ArrayDeque<StackTraceElement> arrayDeque) {
        int index$iv = 0;
        int length = recoveredStacktrace.length;
        while (true) {
            if (index$iv < length) {
                StackTraceElement it = recoveredStacktrace[index$iv];
                if (isArtificial(it)) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        int startIndex = index$iv + 1;
        int lastFrameIndex = recoveredStacktrace.length - 1;
        int i = lastFrameIndex;
        if (startIndex > i) {
            return;
        }
        while (true) {
            StackTraceElement element = recoveredStacktrace[i];
            StackTraceElement last = arrayDeque.getLast();
            Intrinsics.checkNotNullExpressionValue(last, "result.last");
            if (elementWiseEquals(element, last)) {
                arrayDeque.removeLast();
            }
            arrayDeque.addFirst(recoveredStacktrace[i]);
            if (i == startIndex) {
                return;
            } else {
                i--;
            }
        }
    }

    public static final Object recoverAndThrow(Throwable exception, Continuation<?> continuation) {
        if (!DebugKt.getRECOVER_STACK_TRACES()) {
            throw exception;
        }
        if (continuation instanceof CoroutineStackFrame) {
            throw recoverFromStackFrame(exception, (CoroutineStackFrame) continuation);
        }
        throw exception;
    }

    private static final Object recoverAndThrow$$forInline(Throwable exception, Continuation<?> continuation) {
        if (!DebugKt.getRECOVER_STACK_TRACES()) {
            throw exception;
        }
        InlineMarker.mark(0);
        Continuation<?> it = continuation;
        if (it instanceof CoroutineStackFrame) {
            throw recoverFromStackFrame(exception, (CoroutineStackFrame) it);
        }
        throw exception;
    }

    public static final <E extends Throwable> E unwrap(E exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return !DebugKt.getRECOVER_STACK_TRACES() ? exception : (E) unwrapImpl(exception);
    }

    public static final <E extends Throwable> E unwrapImpl(E exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        E e = (E) exception.getCause();
        if (e == null || !Intrinsics.areEqual(e.getClass(), exception.getClass())) {
            return exception;
        }
        StackTraceElement[] stackTrace = exception.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "exception.stackTrace");
        StackTraceElement[] stackTraceElementArr = stackTrace;
        int length = stackTraceElementArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            StackTraceElement it = stackTraceElementArr[i];
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (isArtificial(it)) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return e;
        }
        return exception;
    }

    private static final ArrayDeque<StackTraceElement> createStackTrace(CoroutineStackFrame continuation) {
        CoroutineStackFrame callerFrame;
        ArrayDeque stack = new ArrayDeque();
        StackTraceElement it = continuation.getStackTraceElement();
        if (it != null) {
            stack.add(it);
        }
        CoroutineStackFrame last = continuation;
        while (true) {
            CoroutineStackFrame coroutineStackFrame = last instanceof CoroutineStackFrame ? last : null;
            if (coroutineStackFrame == null || (callerFrame = coroutineStackFrame.getCallerFrame()) == null) {
                break;
            }
            last = callerFrame;
            StackTraceElement it2 = last.getStackTraceElement();
            if (it2 != null) {
                stack.add(it2);
            }
        }
        return stack;
    }

    public static final StackTraceElement artificialFrame(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new StackTraceElement("\b\b\b(" + message, "\b", "\b", -1);
    }

    public static final boolean isArtificial(StackTraceElement $this$isArtificial) {
        Intrinsics.checkNotNullParameter($this$isArtificial, "<this>");
        String className = $this$isArtificial.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "className");
        return StringsKt.startsWith$default(className, "\b\b\b", false, 2, (Object) null);
    }

    private static final int frameIndex(StackTraceElement[] $this$frameIndex, String methodName) {
        int length = $this$frameIndex.length;
        for (int index$iv = 0; index$iv < length; index$iv++) {
            StackTraceElement it = $this$frameIndex[index$iv];
            if (Intrinsics.areEqual(methodName, it.getClassName())) {
                return index$iv;
            }
        }
        return -1;
    }

    private static final boolean elementWiseEquals(StackTraceElement $this$elementWiseEquals, StackTraceElement e) {
        return $this$elementWiseEquals.getLineNumber() == e.getLineNumber() && Intrinsics.areEqual($this$elementWiseEquals.getMethodName(), e.getMethodName()) && Intrinsics.areEqual($this$elementWiseEquals.getFileName(), e.getFileName()) && Intrinsics.areEqual($this$elementWiseEquals.getClassName(), e.getClassName());
    }

    public static final void initCause(Throwable $this$initCause, Throwable cause) {
        Intrinsics.checkNotNullParameter($this$initCause, "<this>");
        Intrinsics.checkNotNullParameter(cause, "cause");
        $this$initCause.initCause(cause);
    }
}
