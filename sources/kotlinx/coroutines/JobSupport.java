package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecovery;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.Undispatched;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;

/* compiled from: JobSupport.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006¯\u0001°\u0001±\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002J\u001e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00122\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00120AH\u0002J\u0012\u0010B\u001a\u00020>2\b\u0010.\u001a\u0004\u0018\u00010\fH\u0014J\u000e\u0010C\u001a\u00020\n2\u0006\u0010D\u001a\u00020\u0002J\u0015\u0010E\u001a\u0004\u0018\u00010\fH\u0080@ø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u0013\u0010H\u001a\u0004\u0018\u00010\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0012\u0010I\u001a\u00020\u00062\b\u0010J\u001a\u0004\u0018\u00010\u0012H\u0017J\u0018\u0010I\u001a\u00020>2\u000e\u0010J\u001a\n\u0018\u00010Kj\u0004\u0018\u0001`LH\u0016J\u0010\u0010M\u001a\u00020\u00062\b\u0010J\u001a\u0004\u0018\u00010\u0012J\u0017\u0010N\u001a\u00020\u00062\b\u0010J\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\bOJ\u0010\u0010P\u001a\u00020>2\u0006\u0010J\u001a\u00020\u0012H\u0016J\u0014\u0010Q\u001a\u0004\u0018\u00010\f2\b\u0010J\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010R\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u0012H\u0002J\b\u0010S\u001a\u00020TH\u0014J\u0010\u0010U\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u0012H\u0016J\u001a\u0010V\u001a\u00020>2\u0006\u0010.\u001a\u0002052\b\u0010W\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010X\u001a\u00020>2\u0006\u0010.\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[2\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010]\u001a\u00020\u00122\b\u0010J\u001a\u0004\u0018\u00010\fH\u0002J&\u0010^\u001a\u00020_2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010T2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u0012H\u0080\b¢\u0006\u0002\baJ\u001c\u0010b\u001a\u0004\u0018\u00010\f2\u0006\u0010.\u001a\u00020Y2\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010c\u001a\u0004\u0018\u00010[2\u0006\u0010.\u001a\u000205H\u0002J\n\u0010d\u001a\u00060Kj\u0002`LJ\f\u0010e\u001a\u00060Kj\u0002`LH\u0016J\u000f\u0010f\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\bgJ\b\u0010h\u001a\u0004\u0018\u00010\u0012J \u0010i\u001a\u0004\u0018\u00010\u00122\u0006\u0010.\u001a\u00020Y2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00120AH\u0002J\u0012\u0010j\u001a\u0004\u0018\u00010:2\u0006\u0010.\u001a\u000205H\u0002J\u0010\u0010k\u001a\u00020\u00062\u0006\u0010l\u001a\u00020\u0012H\u0014J\u0015\u0010m\u001a\u00020>2\u0006\u0010l\u001a\u00020\u0012H\u0010¢\u0006\u0002\bnJ\u0012\u0010o\u001a\u00020>2\b\u0010p\u001a\u0004\u0018\u00010\u0001H\u0004J?\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020\u00062\u0006\u0010t\u001a\u00020\u00062'\u0010u\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\bw\u0012\b\bx\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020>0vj\u0002`yJ/\u0010q\u001a\u00020r2'\u0010u\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\bw\u0012\b\bx\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020>0vj\u0002`yJ\u0011\u0010z\u001a\u00020>H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010GJ\b\u0010{\u001a\u00020\u0006H\u0002J\u0011\u0010|\u001a\u00020>H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u001f\u0010}\u001a\u00020~2\u0014\u0010\u007f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020>0vH\u0082\bJ\u0015\u0010\u0080\u0001\u001a\u0004\u0018\u00010\f2\b\u0010J\u001a\u0004\u0018\u00010\fH\u0002J\u0019\u0010\u0081\u0001\u001a\u00020\u00062\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0003\b\u0082\u0001J\u001b\u0010\u0083\u0001\u001a\u0004\u0018\u00010\f2\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0003\b\u0084\u0001J:\u0010\u0085\u0001\u001a\u00020<2'\u0010u\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\bw\u0012\b\bx\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020>0vj\u0002`y2\u0006\u0010s\u001a\u00020\u0006H\u0002J\u000f\u0010\u0086\u0001\u001a\u00020TH\u0010¢\u0006\u0003\b\u0087\u0001J\u0019\u0010\u0088\u0001\u001a\u00020>2\u0006\u00109\u001a\u00020:2\u0006\u0010J\u001a\u00020\u0012H\u0002J)\u0010\u0089\u0001\u001a\u00020>\"\u000b\b\u0000\u0010\u008a\u0001\u0018\u0001*\u00020<2\u0006\u00109\u001a\u00020:2\b\u0010J\u001a\u0004\u0018\u00010\u0012H\u0082\bJ\u0012\u0010s\u001a\u00020>2\b\u0010J\u001a\u0004\u0018\u00010\u0012H\u0014J\u0013\u0010\u008b\u0001\u001a\u00020>2\b\u0010.\u001a\u0004\u0018\u00010\fH\u0014J\t\u0010\u008c\u0001\u001a\u00020>H\u0014J\u0010\u0010\u008d\u0001\u001a\u00020>2\u0007\u0010\u008e\u0001\u001a\u00020\u0003J\u0012\u0010\u008f\u0001\u001a\u00020>2\u0007\u0010.\u001a\u00030\u0090\u0001H\u0002J\u0011\u0010\u0091\u0001\u001a\u00020>2\u0006\u0010.\u001a\u00020<H\u0002JH\u0010\u0092\u0001\u001a\u00020>\"\u0005\b\u0000\u0010\u0093\u00012\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0095\u00012\u001e\u0010\u007f\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0096\u0001\u0012\u0006\u0012\u0004\u0018\u00010\f0vø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\\\u0010\u0098\u0001\u001a\u00020>\"\u0005\b\u0000\u0010\u008a\u0001\"\u0005\b\u0001\u0010\u0093\u00012\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0095\u00012&\u0010\u007f\u001a\"\b\u0001\u0012\u0005\u0012\u0003H\u008a\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0096\u0001\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0099\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0017\u0010\u009c\u0001\u001a\u00020>2\u0006\u0010;\u001a\u00020<H\u0000¢\u0006\u0003\b\u009d\u0001J\\\u0010\u009e\u0001\u001a\u00020>\"\u0005\b\u0000\u0010\u008a\u0001\"\u0005\b\u0001\u0010\u0093\u00012\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0095\u00012&\u0010\u007f\u001a\"\b\u0001\u0012\u0005\u0012\u0003H\u008a\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0096\u0001\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0099\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u009f\u0001\u0010\u009b\u0001J\u0007\u0010 \u0001\u001a\u00020\u0006J\u0014\u0010¡\u0001\u001a\u00030¢\u00012\b\u0010.\u001a\u0004\u0018\u00010\fH\u0002J\u0013\u0010£\u0001\u001a\u00020T2\b\u0010.\u001a\u0004\u0018\u00010\fH\u0002J\t\u0010¤\u0001\u001a\u00020TH\u0007J\t\u0010¥\u0001\u001a\u00020TH\u0016J\u001b\u0010¦\u0001\u001a\u00020\u00062\u0006\u0010.\u001a\u0002052\b\u0010W\u001a\u0004\u0018\u00010\fH\u0002J\u0019\u0010§\u0001\u001a\u00020\u00062\u0006\u0010.\u001a\u0002052\u0006\u0010?\u001a\u00020\u0012H\u0002J\u001f\u0010¨\u0001\u001a\u0004\u0018\u00010\f2\b\u0010.\u001a\u0004\u0018\u00010\f2\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0002J\u001d\u0010©\u0001\u001a\u0004\u0018\u00010\f2\u0006\u0010.\u001a\u0002052\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0002J$\u0010ª\u0001\u001a\u00020\u00062\u0006\u0010.\u001a\u00020Y2\u0006\u0010D\u001a\u00020[2\b\u0010\\\u001a\u0004\u0018\u00010\fH\u0082\u0010J\u0010\u0010«\u0001\u001a\u0004\u0018\u00010[*\u00030¬\u0001H\u0002J\u0017\u0010\u00ad\u0001\u001a\u00020>*\u00020:2\b\u0010J\u001a\u0004\u0018\u00010\u0012H\u0002J\u001d\u0010®\u0001\u001a\u00060Kj\u0002`L*\u00020\u00122\n\b\u0002\u0010`\u001a\u0004\u0018\u00010TH\u0004R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00068DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u001b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u001d\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0015\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0017R\u0011\u0010%\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b&\u0010'R(\u0010)\u001a\u0004\u0018\u00010\n2\b\u0010(\u001a\u0004\u0018\u00010\n8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0016\u0010.\u001a\u0004\u0018\u00010\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u00010\u0012*\u0004\u0018\u00010\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0018\u00104\u001a\u00020\u0006*\u0002058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006²\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/selects/SelectClause0;", "active", "", "(Z)V", "_parentHandle", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/ChildHandle;", "_state", "", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "completionCause", "", "getCompletionCause", "()Ljava/lang/Throwable;", "completionCauseHandled", "getCompletionCauseHandled", "()Z", "handlesException", "getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "onCancelComplete", "getOnCancelComplete$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "onJoin", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "value", "parentHandle", "getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "(Lkotlinx/coroutines/ChildHandle;)V", "state", "getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "()Ljava/lang/Object;", "exceptionOrNull", "getExceptionOrNull", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "isCancelling", "Lkotlinx/coroutines/Incomplete;", "(Lkotlinx/coroutines/Incomplete;)Z", "addLastAtomic", "expect", "list", "Lkotlinx/coroutines/NodeList;", "node", "Lkotlinx/coroutines/JobNode;", "addSuppressedExceptions", "", "rootCause", "exceptions", "", "afterCompletion", "attachChild", "child", "awaitInternal", "awaitInternal$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSuspend", "cancel", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelCoroutine", "cancelImpl", "cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "cancelInternal", "cancelMakeCompleting", "cancelParent", "cancellationExceptionMessage", "", "childCancelled", "completeStateFinalization", "update", "continueCompleting", "Lkotlinx/coroutines/JobSupport$Finishing;", "lastChild", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "createCauseException", "defaultCancellationException", "Lkotlinx/coroutines/JobCancellationException;", "message", "defaultCancellationException$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "finalizeFinishingState", "firstChild", "getCancellationException", "getChildJobCancellationCause", "getCompletedInternal", "getCompletedInternal$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "getCompletionExceptionOrNull", "getFinalRootCause", "getOrPromoteCancellingList", "handleJobException", "exception", "handleOnCompletionException", "handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "initParentJob", "parent", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "joinInternal", "joinSuspend", "loopOnState", "", "block", "makeCancelling", "makeCompleting", "makeCompleting$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "makeCompletingOnce", "makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "makeNode", "nameString", "nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "notifyCancelling", "notifyHandlers", "T", "onCompletionInternal", "onStart", "parentCancelled", "parentJob", "promoteEmptyToNodeList", "Lkotlinx/coroutines/Empty;", "promoteSingleToNodeList", "registerSelectClause0", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "registerSelectClause1Internal", "Lkotlin/Function2;", "registerSelectClause1Internal$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "removeNode", "removeNode$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "selectAwaitCompletion", "selectAwaitCompletion$external__kotlinx_coroutines__android_common__kotlinx_coroutines", "start", "startInternal", "", "stateString", "toDebugString", "toString", "tryFinalizeSimpleState", "tryMakeCancelling", "tryMakeCompleting", "tryMakeCompletingSlowPath", "tryWaitForChild", "nextChild", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "notifyCompletion", "toCancellationException", "AwaitContinuation", "ChildCompletion", "Finishing", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    private final AtomicRef<ChildHandle> _parentHandle;
    private final AtomicRef<Object> _state;

    public JobSupport(boolean active) {
        this._state = AtomicFU.atomic(active ? JobSupportKt.access$getEMPTY_ACTIVE$p() : JobSupportKt.access$getEMPTY_NEW$p());
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        Job.DefaultImpls.cancel(this);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) Job.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext context) {
        return Job.DefaultImpls.plus(this, context);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job other) {
        return Job.DefaultImpls.plus((Job) this, other);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.INSTANCE;
    }

    public final ChildHandle getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this._parentHandle.getValue();
    }

    public final void setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines(ChildHandle value) {
        this._parentHandle.setValue(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initParentJob(Job parent) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == null)) {
                throw new AssertionError();
            }
        }
        if (parent == null) {
            setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines(NonDisposableHandle.INSTANCE);
            return;
        }
        parent.start();
        ChildHandle handle = parent.attachChild(this);
        setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines(handle);
        if (isCompleted()) {
            handle.dispose();
            setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines(NonDisposableHandle.INSTANCE);
        }
    }

    public final Object getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (!(state instanceof OpDescriptor)) {
                return state;
            }
            ((OpDescriptor) state).perform(this);
        }
    }

    private final Void loopOnState(Function1<Object, Unit> block) {
        while (true) {
            block.invoke(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines());
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        return (state instanceof Incomplete) && ((Incomplete) state).getIsActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof Incomplete);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        return (state instanceof CompletedExceptionally) || ((state instanceof Finishing) && ((Finishing) state).isCancelling());
    }

    private final Object finalizeFinishingState(Finishing state, Object proposedUpdate) {
        boolean wasCancelling;
        Throwable finalCause;
        boolean handled = true;
        boolean z = false;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == state ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!state.isSealed())) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !state.isCompleting()) {
            throw new AssertionError();
        }
        DefaultConstructorMarker defaultConstructorMarker = null;
        CompletedExceptionally completedExceptionally = proposedUpdate instanceof CompletedExceptionally ? (CompletedExceptionally) proposedUpdate : null;
        Throwable proposedException = completedExceptionally != null ? completedExceptionally.cause : null;
        synchronized (state) {
            wasCancelling = state.isCancelling();
            List exceptions = state.sealLocked(proposedException);
            finalCause = getFinalRootCause(state, exceptions);
            if (finalCause != null) {
                addSuppressedExceptions(finalCause, exceptions);
            }
        }
        Object finalState = (finalCause == null || finalCause == proposedException) ? proposedUpdate : new CompletedExceptionally(finalCause, z, 2, defaultConstructorMarker);
        if (finalCause != null) {
            if (!cancelParent(finalCause) && !handleJobException(finalCause)) {
                handled = false;
            }
            if (handled) {
                Intrinsics.checkNotNull(finalState, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((CompletedExceptionally) finalState).makeHandled();
            }
        }
        if (!wasCancelling) {
            onCancelling(finalCause);
        }
        onCompletionInternal(finalState);
        boolean casSuccess = this._state.compareAndSet(state, JobSupportKt.boxIncomplete(finalState));
        if (DebugKt.getASSERTIONS_ENABLED() && !casSuccess) {
            throw new AssertionError();
        }
        completeStateFinalization(state, finalState);
        return finalState;
    }

    private final Throwable getFinalRootCause(Finishing state, List<? extends Throwable> exceptions) {
        Object element$iv;
        Object obj = null;
        if (exceptions.isEmpty()) {
            if (state.isCancelling()) {
                return new JobCancellationException(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        List<? extends Throwable> $this$firstOrNull$iv = exceptions;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                if (!(((Throwable) element$iv) instanceof CancellationException)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        Throwable firstNonCancellation = (Throwable) element$iv;
        if (firstNonCancellation != null) {
            return firstNonCancellation;
        }
        Throwable first = exceptions.get(0);
        if (first instanceof TimeoutCancellationException) {
            List<? extends Throwable> $this$firstOrNull$iv2 = exceptions;
            Iterator it2 = $this$firstOrNull$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object element$iv2 = it2.next();
                Throwable it3 = (Throwable) element$iv2;
                if (((it3 == first || !(it3 instanceof TimeoutCancellationException)) ? null : 1) != null) {
                    obj = element$iv2;
                    break;
                }
            }
            Throwable detailedTimeoutException = (Throwable) obj;
            if (detailedTimeoutException != null) {
                return detailedTimeoutException;
            }
        }
        return first;
    }

    private final void addSuppressedExceptions(Throwable rootCause, List<? extends Throwable> exceptions) {
        if (exceptions.size() <= 1) {
            return;
        }
        int expectedSize$iv = exceptions.size();
        Set seenExceptions = Collections.newSetFromMap(new IdentityHashMap(expectedSize$iv));
        Intrinsics.checkNotNullExpressionValue(seenExceptions, "newSetFromMap(IdentityHashMap(expectedSize))");
        Throwable unwrappedCause = !DebugKt.getRECOVER_STACK_TRACES() ? rootCause : StackTraceRecovery.unwrapImpl(rootCause);
        for (Throwable exception : exceptions) {
            Throwable unwrapped = !DebugKt.getRECOVER_STACK_TRACES() ? exception : StackTraceRecovery.unwrapImpl(exception);
            if (unwrapped != rootCause && unwrapped != unwrappedCause && !(unwrapped instanceof CancellationException) && seenExceptions.add(unwrapped)) {
                kotlin.ExceptionsKt.addSuppressed(rootCause, unwrapped);
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete state, Object update) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((((state instanceof Empty) || (state instanceof JobNode)) ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(update instanceof CompletedExceptionally))) {
            throw new AssertionError();
        }
        if (!this._state.compareAndSet(state, JobSupportKt.boxIncomplete(update))) {
            return false;
        }
        onCancelling(null);
        onCompletionInternal(update);
        completeStateFinalization(state, update);
        return true;
    }

    private final void completeStateFinalization(Incomplete state, Object update) {
        ChildHandle it = getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (it != null) {
            it.dispose();
            setParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines(NonDisposableHandle.INSTANCE);
        }
        CompletedExceptionally completedExceptionally = update instanceof CompletedExceptionally ? (CompletedExceptionally) update : null;
        Throwable cause = completedExceptionally != null ? completedExceptionally.cause : null;
        if (state instanceof JobNode) {
            try {
                ((JobNode) state).invoke(cause);
                return;
            } catch (Throwable ex) {
                handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(new CompletionHandlerException("Exception in completion handler " + state + " for " + this, ex));
                return;
            }
        }
        NodeList list = state.getList();
        if (list != null) {
            notifyCompletion(list, cause);
        }
    }

    private final void notifyCancelling(NodeList list, Throwable cause) {
        onCancelling(cause);
        NodeList this_$iv$iv = list;
        Object next = this_$iv$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        Object exception$iv = null;
        for (LockFreeLinkedListNode cur$iv$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv$iv, this_$iv$iv); cur$iv$iv = cur$iv$iv.getNextNode()) {
            if (cur$iv$iv instanceof JobCancellingNode) {
                JobNode node$iv = (JobNode) cur$iv$iv;
                try {
                    node$iv.invoke(cause);
                } catch (Throwable ex$iv) {
                    Throwable $this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv = (Throwable) exception$iv;
                    if ($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv != null) {
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv, ex$iv);
                        if ($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv != null) {
                        }
                    }
                    exception$iv = new CompletionHandlerException("Exception in completion handler " + node$iv + " for " + this, ex$iv);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable it$iv = (Throwable) exception$iv;
        if (it$iv != null) {
            handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(it$iv);
        }
        cancelParent(cause);
    }

    private final boolean cancelParent(Throwable cause) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean isCancellation = cause instanceof CancellationException;
        ChildHandle parent = getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (parent == null || parent == NonDisposableHandle.INSTANCE) {
            return isCancellation;
        }
        return parent.childCancelled(cause) || isCancellation;
    }

    private final void notifyCompletion(NodeList $this$notifyCompletion, Throwable cause) {
        NodeList this_$iv$iv = $this$notifyCompletion;
        Object next = this_$iv$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        Object exception$iv = null;
        for (LockFreeLinkedListNode cur$iv$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv$iv, this_$iv$iv); cur$iv$iv = cur$iv$iv.getNextNode()) {
            if (cur$iv$iv instanceof JobNode) {
                JobNode node$iv = (JobNode) cur$iv$iv;
                try {
                    node$iv.invoke(cause);
                } catch (Throwable ex$iv) {
                    Throwable $this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv = (Throwable) exception$iv;
                    if ($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv != null) {
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv, ex$iv);
                        if ($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412$iv != null) {
                        }
                    }
                    exception$iv = new CompletionHandlerException("Exception in completion handler " + node$iv + " for " + this, ex$iv);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable it$iv = (Throwable) exception$iv;
        if (it$iv == null) {
            return;
        }
        handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(it$iv);
    }

    private final /* synthetic */ <T extends JobNode> void notifyHandlers(NodeList list, Throwable cause) {
        Object exception = null;
        NodeList this_$iv = list;
        Object next = this_$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (cur$iv instanceof LockFreeLinkedListNode) {
                JobNode node = (JobNode) cur$iv;
                try {
                    node.invoke(cause);
                } catch (Throwable ex) {
                    Throwable th = (Throwable) exception;
                    if (th != null) {
                        Throwable $this$notifyHandlers_u24lambda_u2414_u24lambda_u2412 = th;
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2414_u24lambda_u2412, ex);
                        if (th != null) {
                        }
                    }
                    JobSupport $this$notifyHandlers_u24lambda_u2414_u24lambda_u2413 = this;
                    exception = new CompletionHandlerException("Exception in completion handler " + node + " for " + $this$notifyHandlers_u24lambda_u2414_u24lambda_u2413, ex);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        Throwable th2 = (Throwable) exception;
        if (th2 != null) {
            Throwable it = th2;
            handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(it);
        }
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        while (true) {
            Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            switch (startInternal(state)) {
                case 0:
                    return false;
                case 1:
                    return true;
            }
        }
    }

    private final int startInternal(Object state) {
        if (state instanceof Empty) {
            if (((Empty) state).getIsActive()) {
                return 0;
            }
            if (!this._state.compareAndSet(state, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
                return -1;
            }
            onStart();
            return 1;
        }
        if (!(state instanceof InactiveNodeList)) {
            return 0;
        }
        if (!this._state.compareAndSet(state, ((InactiveNodeList) state).getList())) {
            return -1;
        }
        onStart();
        return 1;
    }

    protected void onStart() {
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (state instanceof Finishing) {
            Throwable rootCause = ((Finishing) state).getRootCause();
            if (rootCause != null) {
                CancellationException cancellationException = toCancellationException(rootCause, DebugStrings.getClassSimpleName(this) + " is cancelling");
                if (cancellationException != null) {
                    return cancellationException;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof CompletedExceptionally) {
            return toCancellationException$default(this, ((CompletedExceptionally) state).cause, null, 1, null);
        }
        return new JobCancellationException(DebugStrings.getClassSimpleName(this) + " has completed normally", null, this);
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        return jobSupport.toCancellationException(th, str);
    }

    protected final CancellationException toCancellationException(Throwable $this$toCancellationException, String message) {
        Intrinsics.checkNotNullParameter($this$toCancellationException, "<this>");
        CancellationException cancellationException = $this$toCancellationException instanceof CancellationException ? (CancellationException) $this$toCancellationException : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        return new JobCancellationException(message == null ? cancellationExceptionMessage() : message, $this$toCancellationException, this);
    }

    protected final Throwable getCompletionCause() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (state instanceof Finishing) {
            Throwable rootCause = ((Finishing) state).getRootCause();
            if (rootCause == null) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            return rootCause;
        }
        if (state instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof CompletedExceptionally) {
            return ((CompletedExceptionally) state).cause;
        }
        return null;
    }

    protected final boolean getCompletionCauseHandled() {
        Object it = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        return (it instanceof CompletedExceptionally) && ((CompletedExceptionally) it).getHandled();
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        return invokeOnCompletion(false, true, handler);
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        JobNode node = makeNode(handler, onCancelling);
        while (true) {
            Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (state instanceof Empty) {
                if (((Empty) state).getIsActive()) {
                    if (this._state.compareAndSet(state, node)) {
                        return node;
                    }
                } else {
                    promoteEmptyToNodeList((Empty) state);
                }
            } else if (state instanceof Incomplete) {
                NodeList list = ((Incomplete) state).getList();
                if (list == null) {
                    Intrinsics.checkNotNull(state, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    promoteSingleToNodeList((JobNode) state);
                } else {
                    Object rootCause = null;
                    Object handle = NonDisposableHandle.INSTANCE;
                    if (onCancelling && (state instanceof Finishing)) {
                        synchronized (state) {
                            rootCause = ((Finishing) state).getRootCause();
                            if (rootCause == null || ((handler instanceof ChildHandleNode) && !((Finishing) state).isCompleting())) {
                                if (addLastAtomic(state, list, node)) {
                                    if (rootCause == null) {
                                        return node;
                                    }
                                    handle = node;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    if (rootCause != null) {
                        if (invokeImmediately) {
                            Object cause$iv = rootCause;
                            handler.invoke(cause$iv);
                        }
                        return (DisposableHandle) handle;
                    }
                    if (addLastAtomic(state, list, node)) {
                        return node;
                    }
                }
            } else {
                if (invokeImmediately) {
                    CompletedExceptionally completedExceptionally = state instanceof CompletedExceptionally ? (CompletedExceptionally) state : null;
                    Throwable cause$iv2 = completedExceptionally != null ? completedExceptionally.cause : null;
                    handler.invoke(cause$iv2);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    private final JobNode makeNode(Function1<? super Throwable, Unit> handler, boolean onCancelling) {
        InvokeOnCompletion node;
        if (onCancelling) {
            node = handler instanceof JobCancellingNode ? (JobCancellingNode) handler : null;
            if (node == null) {
                node = new InvokeOnCancelling(handler);
            }
            node = node;
        } else {
            node = handler instanceof JobNode ? (JobNode) handler : null;
            if (node != null) {
                JobNode it = node;
                if (DebugKt.getASSERTIONS_ENABLED() && !(!(it instanceof JobCancellingNode))) {
                    throw new AssertionError();
                }
            } else {
                node = new InvokeOnCompletion(handler);
            }
        }
        node.setJob(this);
        return node;
    }

    private final boolean addLastAtomic(final Object expect, NodeList list, JobNode node) {
        NodeList this_$iv = list;
        final JobNode jobNode = node;
        LockFreeLinkedListNode.CondAddOp condAdd$iv = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                Intrinsics.checkNotNullParameter(affected, "affected");
                if (this.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == expect) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            LockFreeLinkedListNode prev$iv = this_$iv.getPrevNode();
            switch (prev$iv.tryCondAddNext(node, this_$iv, condAdd$iv)) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }
    }

    private final void promoteEmptyToNodeList(Empty state) {
        NodeList list = new NodeList();
        Incomplete update = state.getIsActive() ? list : new InactiveNodeList(list);
        this._state.compareAndSet(state, update);
    }

    private final void promoteSingleToNodeList(JobNode state) {
        state.addOneIfEmpty(new NodeList());
        LockFreeLinkedListNode list = state.getNextNode();
        this._state.compareAndSet(state, list);
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation<? super Unit> continuation) {
        if (!joinInternal()) {
            JobKt.ensureActive(continuation.get$context());
            return Unit.INSTANCE;
        }
        Object joinSuspend = joinSuspend(continuation);
        return joinSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinSuspend : Unit.INSTANCE;
    }

    private final boolean joinInternal() {
        Object state;
        do {
            state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (!(state instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state) < 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object joinSuspend(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        CompletionHandlerBase $this$asHandler$iv = new ResumeOnCompletion(cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    public final SelectClause0 getOnJoin() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final <R> void registerSelectClause0(SelectInstance<? super R> select, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Object state;
        Intrinsics.checkNotNullParameter(select, "select");
        Intrinsics.checkNotNullParameter(block, "block");
        do {
            state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (select.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (select.trySelect()) {
                    Undispatched.startCoroutineUnintercepted(block, select.getCompletion());
                    return;
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectJoinOnCompletion(select, block);
        select.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final void removeNode$external__kotlinx_coroutines__android_common__kotlinx_coroutines(JobNode node) {
        Object state;
        Intrinsics.checkNotNullParameter(node, "node");
        do {
            state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (state instanceof JobNode) {
                if (state != node) {
                    return;
                }
            } else {
                if (!(state instanceof Incomplete) || ((Incomplete) state).getList() == null) {
                    return;
                }
                node.mo2402remove();
                return;
            }
        } while (!this._state.compareAndSet(state, JobSupportKt.access$getEMPTY_ACTIVE$p()));
    }

    public boolean getOnCancelComplete$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cause) {
        JobCancellationException jobCancellationException;
        if (cause != null) {
            jobCancellationException = cause;
        } else {
            jobCancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(jobCancellationException);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable cause) {
        JobCancellationException jobCancellationException;
        if (cause == null || (jobCancellationException = toCancellationException$default(this, cause, null, 1, null)) == null) {
            jobCancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(jobCancellationException);
        return true;
    }

    public void cancelInternal(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(cause);
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob) {
        Intrinsics.checkNotNullParameter(parentJob, "parentJob");
        cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(parentJob);
    }

    public boolean childCancelled(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(cause) && getHandlesException();
    }

    public final boolean cancelCoroutine(Throwable cause) {
        return cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(cause);
    }

    public final boolean cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object cause) {
        Object finalState = JobSupportKt.access$getCOMPLETING_ALREADY$p();
        if (getOnCancelComplete$external__kotlinx_coroutines__android_common__kotlinx_coroutines() && (finalState = cancelMakeCompleting(cause)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (finalState == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            finalState = makeCancelling(cause);
        }
        if (finalState == JobSupportKt.access$getCOMPLETING_ALREADY$p() || finalState == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (finalState == JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) {
            return false;
        }
        afterCompletion(finalState);
        return true;
    }

    private final Object cancelMakeCompleting(Object cause) {
        Object finalState;
        do {
            Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (!(state instanceof Incomplete) || ((state instanceof Finishing) && ((Finishing) state).isCompleting())) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            CompletedExceptionally proposedUpdate = new CompletedExceptionally(createCauseException(cause), false, 2, null);
            finalState = tryMakeCompleting(state, proposedUpdate);
        } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return finalState;
    }

    public static /* synthetic */ JobCancellationException defaultCancellationException$external__kotlinx_coroutines__android_common__kotlinx_coroutines$default(JobSupport $this, String message, Throwable cause, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
        }
        if ((i & 1) != 0) {
            message = null;
        }
        if ((i & 2) != 0) {
            cause = null;
        }
        return new JobCancellationException(message == null ? $this.cancellationExceptionMessage() : message, cause, $this);
    }

    public final JobCancellationException defaultCancellationException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(String message, Throwable cause) {
        return new JobCancellationException(message == null ? cancellationExceptionMessage() : message, cause, this);
    }

    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Throwable rootCause;
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (state instanceof Finishing) {
            rootCause = ((Finishing) state).getRootCause();
        } else if (state instanceof CompletedExceptionally) {
            rootCause = ((CompletedExceptionally) state).cause;
        } else {
            if (state instanceof Incomplete) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + state).toString());
            }
            rootCause = null;
        }
        CancellationException cancellationException = rootCause instanceof CancellationException ? (CancellationException) rootCause : null;
        if (cancellationException == null) {
            return new JobCancellationException("Parent job is " + stateString(state), rootCause, this);
        }
        return cancellationException;
    }

    private final Throwable createCauseException(Object cause) {
        if (!(cause == null ? true : cause instanceof Throwable)) {
            Intrinsics.checkNotNull(cause, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
            return ((ParentJob) cause).getChildJobCancellationCause();
        }
        Throwable th = (Throwable) cause;
        if (th != null) {
            return th;
        }
        return new JobCancellationException(cancellationExceptionMessage(), null, this);
    }

    private final Object makeCancelling(Object cause) {
        Throwable th;
        Throwable th2;
        Throwable th3 = null;
        while (true) {
            Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            byte b = 0;
            if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Finishing) {
                synchronized (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines) {
                    try {
                        if (((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).isSealed()) {
                            return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                        }
                        boolean isCancelling = ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).isCancelling();
                        if (cause != null || !isCancelling) {
                            if (th3 == null) {
                                Throwable createCauseException = createCauseException(cause);
                                th = createCauseException;
                                th3 = createCauseException;
                            } else {
                                th = th3;
                            }
                            try {
                                ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).addExceptionLocked(th3);
                            } catch (Throwable th4) {
                                th = th4;
                                throw th;
                            }
                        }
                        Throwable rootCause = isCancelling ? false : true ? ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getRootCause() : null;
                        if (rootCause != null) {
                            notifyCancelling(((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getList(), rootCause);
                        }
                        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
            } else {
                if (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete)) {
                    return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                }
                if (th3 == null) {
                    Throwable createCauseException2 = createCauseException(cause);
                    th2 = createCauseException2;
                    th3 = createCauseException2;
                } else {
                    th2 = th3;
                }
                if (!((Incomplete) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getIsActive()) {
                    Object tryMakeCompleting = tryMakeCompleting(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines, new CompletedExceptionally(th3, r7, 2, b == true ? 1 : 0));
                    if (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                        throw new IllegalStateException(("Cannot happen in " + state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).toString());
                    }
                    if (tryMakeCompleting != JobSupportKt.access$getCOMPLETING_RETRY$p()) {
                        return tryMakeCompleting;
                    }
                } else if (tryMakeCancelling((Incomplete) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines, th3)) {
                    return JobSupportKt.access$getCOMPLETING_ALREADY$p();
                }
                th3 = th2;
            }
        }
    }

    private final NodeList getOrPromoteCancellingList(Incomplete state) {
        NodeList list = state.getList();
        if (list == null) {
            if (state instanceof Empty) {
                return new NodeList();
            }
            if (state instanceof JobNode) {
                promoteSingleToNodeList((JobNode) state);
                return null;
            }
            throw new IllegalStateException(("State should have list: " + state).toString());
        }
        return list;
    }

    private final boolean tryMakeCancelling(Incomplete state, Throwable rootCause) {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state instanceof Finishing))) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !state.getIsActive()) {
            throw new AssertionError();
        }
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return false;
        }
        Finishing cancelling = new Finishing(list, false, rootCause);
        if (!this._state.compareAndSet(state, cancelling)) {
            return false;
        }
        notifyCancelling(list, rootCause);
        return true;
    }

    public final boolean makeCompleting$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object proposedUpdate) {
        Object finalState;
        do {
            Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            finalState = tryMakeCompleting(state, proposedUpdate);
            if (finalState == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                return false;
            }
            if (finalState == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());
        afterCompletion(finalState);
        return true;
    }

    public final Object makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object proposedUpdate) {
        Object finalState;
        do {
            Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            finalState = tryMakeCompleting(state, proposedUpdate);
            if (finalState == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + proposedUpdate, getExceptionOrNull(proposedUpdate));
            }
        } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return finalState;
    }

    private final Object tryMakeCompleting(Object state, Object proposedUpdate) {
        if (!(state instanceof Incomplete)) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
        }
        if (((state instanceof Empty) || (state instanceof JobNode)) && !(state instanceof ChildHandleNode) && !(proposedUpdate instanceof CompletedExceptionally)) {
            if (tryFinalizeSimpleState((Incomplete) state, proposedUpdate)) {
                return proposedUpdate;
            }
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        return tryMakeCompletingSlowPath((Incomplete) state, proposedUpdate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object tryMakeCompletingSlowPath(Incomplete state, Object proposedUpdate) {
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        Finishing finishing = state instanceof Finishing ? (Finishing) state : null;
        if (finishing == null) {
            finishing = new Finishing(list, false, null);
        }
        Ref.ObjectRef notifyRootCause = new Ref.ObjectRef();
        synchronized (finishing) {
            if (finishing.isCompleting()) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            finishing.setCompleting(true);
            if (finishing != state && !this._state.compareAndSet(state, finishing)) {
                return JobSupportKt.access$getCOMPLETING_RETRY$p();
            }
            if (DebugKt.getASSERTIONS_ENABLED() && !(!finishing.isSealed())) {
                throw new AssertionError();
            }
            boolean wasCancelling = finishing.isCancelling();
            CompletedExceptionally it = proposedUpdate instanceof CompletedExceptionally ? (CompletedExceptionally) proposedUpdate : null;
            if (it != null) {
                finishing.addExceptionLocked(it.cause);
            }
            notifyRootCause.element = Boolean.valueOf(wasCancelling ? false : true).booleanValue() ? finishing.getRootCause() : 0;
            Unit unit = Unit.INSTANCE;
            Throwable it2 = (Throwable) notifyRootCause.element;
            if (it2 != null) {
                notifyCancelling(list, it2);
            }
            ChildHandleNode child = firstChild(state);
            return (child == null || !tryWaitForChild(finishing, child, proposedUpdate)) ? finalizeFinishingState(finishing, proposedUpdate) : JobSupportKt.COMPLETING_WAITING_CHILDREN;
        }
    }

    private final Throwable getExceptionOrNull(Object $this$exceptionOrNull) {
        CompletedExceptionally completedExceptionally = $this$exceptionOrNull instanceof CompletedExceptionally ? (CompletedExceptionally) $this$exceptionOrNull : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final ChildHandleNode firstChild(Incomplete state) {
        ChildHandleNode childHandleNode = state instanceof ChildHandleNode ? (ChildHandleNode) state : null;
        if (childHandleNode != null) {
            return childHandleNode;
        }
        NodeList list = state.getList();
        if (list != null) {
            return nextChild(list);
        }
        return null;
    }

    private final boolean tryWaitForChild(Finishing state, ChildHandleNode child, Object proposedUpdate) {
        while (true) {
            ChildJob childJob = child.childJob;
            CompletionHandlerBase $this$asHandler$iv = new ChildCompletion(this, state, child, proposedUpdate);
            DisposableHandle handle = Job.DefaultImpls.invokeOnCompletion$default(childJob, false, false, $this$asHandler$iv, 1, null);
            if (handle != NonDisposableHandle.INSTANCE) {
                return true;
            }
            ChildHandleNode nextChild = nextChild(child);
            if (nextChild == null) {
                return false;
            }
            child = nextChild;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(Finishing state, ChildHandleNode lastChild, Object proposedUpdate) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == state)) {
                throw new AssertionError();
            }
        }
        ChildHandleNode waitChild = nextChild(lastChild);
        if (waitChild == null || !tryWaitForChild(state, waitChild, proposedUpdate)) {
            Object finalState = finalizeFinishingState(state, proposedUpdate);
            afterCompletion(finalState);
        }
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode $this$nextChild) {
        LockFreeLinkedListNode cur = $this$nextChild;
        while (cur.isRemoved()) {
            cur = cur.getPrevNode();
        }
        while (true) {
            cur = cur.getNextNode();
            if (!cur.isRemoved()) {
                if (cur instanceof ChildHandleNode) {
                    return (ChildHandleNode) cur;
                }
                if (cur instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, null));
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob child) {
        Intrinsics.checkNotNullParameter(child, "child");
        CompletionHandlerBase $this$asHandler$iv = new ChildHandleNode(child);
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(this, true, false, $this$asHandler$iv, 2, null);
        Intrinsics.checkNotNull(invokeOnCompletion$default, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (ChildHandle) invokeOnCompletion$default;
    }

    public void handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        throw exception;
    }

    protected void onCancelling(Throwable cause) {
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    /* renamed from: getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines */
    public boolean getHandlesException() {
        return true;
    }

    protected boolean handleJobException(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return false;
    }

    protected void onCompletionInternal(Object state) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterCompletion(Object state) {
    }

    public String toString() {
        return toDebugString() + "@" + DebugStrings.getHexAddress(this);
    }

    public final String toDebugString() {
        return nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() + "{" + stateString(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) + "}";
    }

    public String nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return DebugStrings.getClassSimpleName(this);
    }

    private final String stateString(Object state) {
        return state instanceof Finishing ? ((Finishing) state).isCancelling() ? "Cancelling" : ((Finishing) state).isCompleting() ? "Completing" : "Active" : state instanceof Incomplete ? ((Incomplete) state).getIsActive() ? "Active" : "New" : state instanceof CompletedExceptionally ? "Cancelled" : "Completed";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JobSupport.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\tJ\u0018\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\t0&j\b\u0012\u0004\u0012\u00020\t`'H\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0)2\b\u0010*\u001a\u0004\u0018\u00010\tJ\b\u0010+\u001a\u00020,H\u0016R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\fX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017R$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0017\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR(\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006-"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "_exceptionsHolder", "Lkotlinx/atomicfu/AtomicRef;", "_isCompleting", "Lkotlinx/atomicfu/AtomicBoolean;", "_rootCause", "value", "exceptionsHolder", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "isActive", "()Z", "isCancelling", "setCompleting", "(Z)V", "isSealed", "getList", "()Lkotlinx/coroutines/NodeList;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "(Ljava/lang/Throwable;)V", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sealLocked", "", "proposedException", "toString", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Finishing implements Incomplete {
        private final AtomicRef<Object> _exceptionsHolder;
        private final AtomicBoolean _isCompleting;
        private final AtomicRef<Throwable> _rootCause;
        private final NodeList list;

        @Override // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public Finishing(NodeList list, boolean isCompleting, Throwable rootCause) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this._isCompleting = AtomicFU.atomic(isCompleting);
            this._rootCause = AtomicFU.atomic(rootCause);
            this._exceptionsHolder = AtomicFU.atomic((Object) null);
        }

        public final boolean isCompleting() {
            return this._isCompleting.getValue();
        }

        public final void setCompleting(boolean value) {
            this._isCompleting.setValue(value);
        }

        public final Throwable getRootCause() {
            return this._rootCause.getValue();
        }

        public final void setRootCause(Throwable value) {
            this._rootCause.setValue(value);
        }

        private final Object getExceptionsHolder() {
            return this._exceptionsHolder.getValue();
        }

        private final void setExceptionsHolder(Object value) {
            this._exceptionsHolder.setValue(value);
        }

        public final boolean isSealed() {
            return getExceptionsHolder() == JobSupportKt.access$getSEALED$p();
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        /* renamed from: isActive */
        public boolean getIsActive() {
            return getRootCause() == null;
        }

        public final List<Throwable> sealLocked(Throwable proposedException) {
            ArrayList it;
            Object eh = getExceptionsHolder();
            if (eh == null) {
                it = allocateList();
            } else if (eh instanceof Throwable) {
                it = allocateList();
                it.add(eh);
            } else {
                if (!(eh instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + eh).toString());
                }
                it = (ArrayList) eh;
            }
            ArrayList list = it;
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                list.add(0, rootCause);
            }
            if (proposedException != null && !Intrinsics.areEqual(proposedException, rootCause)) {
                list.add(proposedException);
            }
            setExceptionsHolder(JobSupportKt.access$getSEALED$p());
            return list;
        }

        public final void addExceptionLocked(Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                setRootCause(exception);
                return;
            }
            if (exception == rootCause) {
                return;
            }
            Object eh = getExceptionsHolder();
            if (eh != null) {
                if (eh instanceof Throwable) {
                    if (exception == eh) {
                        return;
                    }
                    ArrayList $this$addExceptionLocked_u24lambda_u242 = allocateList();
                    $this$addExceptionLocked_u24lambda_u242.add(eh);
                    $this$addExceptionLocked_u24lambda_u242.add(exception);
                    setExceptionsHolder($this$addExceptionLocked_u24lambda_u242);
                    return;
                }
                if (!(eh instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + eh).toString());
                }
                ((ArrayList) eh).add(exception);
                return;
            }
            setExceptionsHolder(exception);
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + getExceptionsHolder() + ", list=" + getList() + "]";
        }
    }

    private final boolean isCancelling(Incomplete $this$isCancelling) {
        return ($this$isCancelling instanceof Finishing) && ((Finishing) $this$isCancelling).isCancelling();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JobSupport.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public ChildCompletion(JobSupport parent, Finishing state, ChildHandleNode child, Object proposedUpdate) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(child, "child");
            this.parent = parent;
            this.state = state;
            this.child = child;
            this.proposedUpdate = proposedUpdate;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JobSupport.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AwaitContinuation(Continuation<? super T> delegate, JobSupport job) {
            super(delegate, 1);
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            Intrinsics.checkNotNullParameter(job, "job");
            this.job = job;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable getContinuationCancellationCause(Job parent) {
            Throwable it;
            Intrinsics.checkNotNullParameter(parent, "parent");
            Object state = this.job.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (!(state instanceof Finishing) || (it = ((Finishing) state).getRootCause()) == null) {
                return state instanceof CompletedExceptionally ? ((CompletedExceptionally) state).cause : parent.getCancellationException();
            }
            return it;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        protected String nameString() {
            return "AwaitContinuation";
        }
    }

    public final boolean isCompletedExceptionally() {
        return getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof CompletedExceptionally;
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (!(state instanceof Incomplete)) {
            return getExceptionOrNull(state);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    public final Object getCompletedInternal$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (!(!(state instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (state instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) state).cause;
        }
        return JobSupportKt.unboxState(state);
    }

    public final Object awaitInternal$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Continuation<Object> continuation) {
        Object state;
        do {
            state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (!(state instanceof Incomplete)) {
                if (state instanceof CompletedExceptionally) {
                    Throwable exception$iv = ((CompletedExceptionally) state).cause;
                    if (!DebugKt.getRECOVER_STACK_TRACES()) {
                        throw exception$iv;
                    }
                    if (continuation instanceof CoroutineStackFrame) {
                        throw StackTraceRecovery.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) continuation);
                    }
                    throw exception$iv;
                }
                return JobSupportKt.unboxState(state);
            }
        } while (startInternal(state) < 0);
        return awaitSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitSuspend(Continuation<Object> continuation) {
        AwaitContinuation cont = new AwaitContinuation(IntrinsicsKt.intercepted(continuation), this);
        cont.initCancellability();
        CompletionHandlerBase $this$asHandler$iv = new ResumeAwaitOnCompletion(cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cont.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <T, R> void registerSelectClause1Internal$external__kotlinx_coroutines__android_common__kotlinx_coroutines(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Object state;
        Intrinsics.checkNotNullParameter(select, "select");
        Intrinsics.checkNotNullParameter(block, "block");
        do {
            state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (select.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (select.trySelect()) {
                    if (state instanceof CompletedExceptionally) {
                        select.resumeSelectWithException(((CompletedExceptionally) state).cause);
                        return;
                    } else {
                        Undispatched.startCoroutineUnintercepted(block, JobSupportKt.unboxState(state), select.getCompletion());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectAwaitOnCompletion(select, block);
        select.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final <T, R> void selectAwaitCompletion$external__kotlinx_coroutines__android_common__kotlinx_coroutines(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(select, "select");
        Intrinsics.checkNotNullParameter(block, "block");
        Object state = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (state instanceof CompletedExceptionally) {
            select.resumeSelectWithException(((CompletedExceptionally) state).cause);
        } else {
            CancellableKt.startCoroutineCancellable$default(block, JobSupportKt.unboxState(state), select.getCompletion(), null, 4, null);
        }
    }
}
