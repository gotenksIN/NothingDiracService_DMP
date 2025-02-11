package androidx.tracing;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public final class Trace {
    static final String TAG = "Trace";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static boolean sHasAppTracingEnabled;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    public static boolean isEnabled() {
        return TraceApi29Impl.isEnabled();
    }

    public static void forceEnableAppTracing() {
    }

    public static void beginSection(String label) {
        TraceApi18Impl.beginSection(label);
    }

    public static void endSection() {
        TraceApi18Impl.endSection();
    }

    public static void beginAsyncSection(String methodName, int cookie) {
        TraceApi29Impl.beginAsyncSection(methodName, cookie);
    }

    public static void endAsyncSection(String methodName, int cookie) {
        TraceApi29Impl.endAsyncSection(methodName, cookie);
    }

    public static void setCounter(String counterName, int counterValue) {
        TraceApi29Impl.setCounter(counterName, counterValue);
    }

    private static boolean isEnabledFallback() {
        try {
            if (sIsTagEnabledMethod == null) {
                Field traceTagAppField = android.os.Trace.class.getField("TRACE_TAG_APP");
                sTraceTagApp = traceTagAppField.getLong(null);
                sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception exception) {
            handleException("isTagEnabled", exception);
            return false;
        }
    }

    private static void beginAsyncSectionFallback(String methodName, int cookie) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), methodName, Integer.valueOf(cookie));
        } catch (Exception exception) {
            handleException("asyncTraceBegin", exception);
        }
    }

    private static void endAsyncSectionFallback(String methodName, int cookie) {
        try {
            if (sAsyncTraceEndMethod == null) {
                sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), methodName, Integer.valueOf(cookie));
        } catch (Exception exception) {
            handleException("asyncTraceEnd", exception);
        }
    }

    private static void setCounterFallback(String counterName, int counterValue) {
        try {
            if (sTraceCounterMethod == null) {
                sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), counterName, Integer.valueOf(counterValue));
        } catch (Exception exception) {
            handleException("traceCounter", exception);
        }
    }

    private static void handleException(String methodName, Exception exception) {
        if (exception instanceof InvocationTargetException) {
            Throwable cause = exception.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
        Log.v(TAG, "Unable to call " + methodName + " via reflection", exception);
    }

    private Trace() {
    }
}
