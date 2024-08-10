package kotlinx.coroutines.selects;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionHandlerBase;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecovery;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.Undispatched;
import kotlinx.coroutines.selects.SelectBuilder;

/* compiled from: Select.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0004QRSTB\u0013\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\fH\u0016J\b\u0010&\u001a\u00020$H\u0002J'\u0010'\u001a\u00020$2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020$0(H\u0082\bJ\n\u0010*\u001a\u0004\u0018\u00010\u000eH\u0001J\u0010\u0010+\u001a\n\u0018\u00010,j\u0004\u0018\u0001`-H\u0016J\u0010\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u000200H\u0001J\b\u00101\u001a\u00020$H\u0002J6\u00102\u001a\u00020$2\u0006\u00103\u001a\u0002042\u001c\u0010)\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000e05H\u0016ø\u0001\u0000¢\u0006\u0002\u00106J\u0012\u00107\u001a\u0004\u0018\u00010\u000e2\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u000200H\u0016J\u001e\u0010<\u001a\u00020$2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>H\u0016ø\u0001\u0000¢\u0006\u0002\u0010?J\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020\u001bH\u0016J\u0014\u0010C\u001a\u0004\u0018\u00010\u000e2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J3\u0010F\u001a\u00020$*\u00020G2\u001c\u0010)\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000e05H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010HJE\u0010F\u001a\u00020$\"\u0004\b\u0001\u0010I*\b\u0012\u0004\u0012\u0002HI0J2\"\u0010)\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002HI\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000e0KH\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010LJY\u0010F\u001a\u00020$\"\u0004\b\u0001\u0010M\"\u0004\b\u0002\u0010I*\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u0002HI0N2\u0006\u0010O\u001a\u0002HM2\"\u0010)\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002HI\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000e0KH\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010PR\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR(\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\f8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006U"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "uCont", "(Lkotlin/coroutines/Continuation;)V", "_parentHandle", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/DisposableHandle;", "_result", "", "_state", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "completion", "getCompletion", "()Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isSelected", "", "()Z", "value", "parentHandle", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "disposeOnSelect", "", "handle", "doAfterSelect", "doResume", "Lkotlin/Function0;", "block", "getResult", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "handleBuilderException", "e", "", "initCancellability", "onTimeout", "timeMillis", "", "Lkotlin/Function1;", "(JLkotlin/jvm/functions/Function1;)V", "performAtomicTrySelect", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "resumeSelectWithException", "exception", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "trySelect", "trySelectOther", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "AtomicSelectOp", "DisposeNode", "PairSelectOp", "SelectOnCancelling", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* renamed from: kotlinx.coroutines.selects.SelectBuilderImpl, reason: from toString */
/* loaded from: classes4.dex */
public final class SelectInstance<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, kotlinx.coroutines.selects.SelectInstance<R>, Continuation<R>, CoroutineStackFrame {
    private final AtomicRef<DisposableHandle> _parentHandle;
    private final AtomicRef<Object> _result;
    private final AtomicRef<Object> _state;
    private final Continuation<R> uCont;

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectInstance(Continuation<? super R> uCont) {
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        this.uCont = uCont;
        this._state = AtomicFU.atomic(SelectKt.getNOT_SELECTED());
        this._result = AtomicFU.atomic(SelectKt.access$getUNDECIDED$p());
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    private final DisposableHandle getParentHandle() {
        return this._parentHandle.getValue();
    }

    private final void setParentHandle(DisposableHandle value) {
        this._parentHandle.setValue(value);
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Continuation<R> getCompletion() {
        return this;
    }

    private final void doResume(Function0<? extends Object> value, Function0<Unit> block) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        AtomicRef $this$loop$iv = this._result;
        while (true) {
            Object result = $this$loop$iv.getValue();
            if (result == SelectKt.access$getUNDECIDED$p()) {
                Object update = value.invoke();
                if (this._result.compareAndSet(SelectKt.access$getUNDECIDED$p(), update)) {
                    return;
                }
            } else {
                Object update2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result != update2) {
                    throw new IllegalStateException("Already resumed");
                }
                if (this._result.compareAndSet(IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                    block.invoke();
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        Throwable th;
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        AtomicRef $this$loop$iv$iv = this._result;
        while (true) {
            Object result$iv = $this$loop$iv$iv.getValue();
            if (result$iv == SelectKt.access$getUNDECIDED$p()) {
                Object update$iv = CompletionStateKt.toState$default(result, null, 1, null);
                if (this._result.compareAndSet(SelectKt.access$getUNDECIDED$p(), update$iv)) {
                    return;
                }
            } else {
                Object update$iv2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result$iv == update$iv2) {
                    if (this._result.compareAndSet(IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                        if (Result.m862isFailureimpl(result)) {
                            Continuation $this$resumeWithStackTrace$iv = this.uCont;
                            Throwable exception$iv = Result.m859exceptionOrNullimpl(result);
                            Intrinsics.checkNotNull(exception$iv);
                            Result.Companion companion = Result.INSTANCE;
                            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv instanceof CoroutineStackFrame)) {
                                th = StackTraceRecovery.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) $this$resumeWithStackTrace$iv);
                            } else {
                                th = exception$iv;
                            }
                            $this$resumeWithStackTrace$iv.resumeWith(Result.m856constructorimpl(ResultKt.createFailure(th)));
                            return;
                        }
                        this.uCont.resumeWith(result);
                        return;
                    }
                } else {
                    throw new IllegalStateException("Already resumed");
                }
            }
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void resumeSelectWithException(Throwable exception) {
        Throwable th;
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        AtomicRef $this$loop$iv$iv = this._result;
        while (true) {
            Object result$iv = $this$loop$iv$iv.getValue();
            if (result$iv == SelectKt.access$getUNDECIDED$p()) {
                Continuation continuation$iv = this.uCont;
                if (DebugKt.getRECOVER_STACK_TRACES() && (continuation$iv instanceof CoroutineStackFrame)) {
                    th = StackTraceRecovery.recoverFromStackFrame(exception, (CoroutineStackFrame) continuation$iv);
                } else {
                    th = exception;
                }
                Object update$iv = new CompletedExceptionally(th, false, 2, null);
                if (this._result.compareAndSet(SelectKt.access$getUNDECIDED$p(), update$iv)) {
                    return;
                }
            } else {
                Object update$iv2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result$iv == update$iv2) {
                    if (this._result.compareAndSet(IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                        Continuation intercepted = IntrinsicsKt.intercepted(this.uCont);
                        Result.Companion companion = Result.INSTANCE;
                        intercepted.resumeWith(Result.m856constructorimpl(ResultKt.createFailure(exception)));
                        return;
                    }
                } else {
                    throw new IllegalStateException("Already resumed");
                }
            }
        }
    }

    public final Object getResult() {
        if (!isSelected()) {
            initCancellability();
        }
        Object result = this._result.getValue();
        if (result == SelectKt.access$getUNDECIDED$p()) {
            if (this._result.compareAndSet(SelectKt.access$getUNDECIDED$p(), IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            result = this._result.getValue();
        }
        if (result == SelectKt.access$getRESUMED$p()) {
            throw new IllegalStateException("Already resumed");
        }
        if (result instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) result).cause;
        }
        return result;
    }

    private final void initCancellability() {
        Job parent = (Job) getContext().get(Job.INSTANCE);
        if (parent == null) {
            return;
        }
        CompletionHandlerBase $this$asHandler$iv = new SelectOnCancelling();
        DisposableHandle newRegistration = Job.DefaultImpls.invokeOnCompletion$default(parent, true, false, $this$asHandler$iv, 2, null);
        setParentHandle(newRegistration);
        if (isSelected()) {
            newRegistration.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Select.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$SelectOnCancelling;", "Lkotlinx/coroutines/JobCancellingNode;", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;)V", "invoke", "", "cause", "", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.selects.SelectBuilderImpl$SelectOnCancelling */
    /* loaded from: classes4.dex */
    public final class SelectOnCancelling extends JobCancellingNode {
        public SelectOnCancelling() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            if (SelectInstance.this.trySelect()) {
                SelectInstance.this.resumeSelectWithException(getJob().getCancellationException());
            }
        }
    }

    public final void handleBuilderException(Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        if (trySelect()) {
            Result.Companion companion = Result.INSTANCE;
            resumeWith(Result.m856constructorimpl(ResultKt.createFailure(e)));
        } else if (!(e instanceof CancellationException)) {
            Object result = getResult();
            if (result instanceof CompletedExceptionally) {
                Throwable exception$iv = ((CompletedExceptionally) result).cause;
                if (DebugKt.getRECOVER_STACK_TRACES()) {
                    exception$iv = StackTraceRecovery.unwrapImpl(exception$iv);
                }
                if (exception$iv == (!DebugKt.getRECOVER_STACK_TRACES() ? e : StackTraceRecovery.unwrapImpl(e))) {
                    return;
                }
            }
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), e);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean isSelected() {
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state == SelectKt.getNOT_SELECTED()) {
                return false;
            }
            if (!(state instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) state).perform(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnSelect(DisposableHandle handle) {
        Intrinsics.checkNotNullParameter(handle, "handle");
        DisposeNode node = new DisposeNode(handle);
        if (!isSelected()) {
            addLast(node);
            if (!isSelected()) {
                return;
            }
        }
        handle.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doAfterSelect() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
        }
        SelectInstance<R> this_$iv = this;
        Object next = this_$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
            if (cur$iv instanceof DisposeNode) {
                DisposeNode it = (DisposeNode) cur$iv;
                it.handle.dispose();
            }
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect() {
        Object result = trySelectOther(null);
        if (result == CancellableContinuationImplKt.RESUME_TOKEN) {
            return true;
        }
        if (result == null) {
            return false;
        }
        throw new IllegalStateException(("Unexpected trySelectIdempotent result " + result).toString());
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Object trySelectOther(LockFreeLinkedListNode.PrepareOp otherOp) {
        AtomicRef $this$loop$iv = this._state;
        while (true) {
            Object state = $this$loop$iv.getValue();
            if (state == SelectKt.getNOT_SELECTED()) {
                if (otherOp == null) {
                    if (this._state.compareAndSet(SelectKt.getNOT_SELECTED(), null)) {
                        break;
                    }
                } else {
                    PairSelectOp pairSelectOp = new PairSelectOp(otherOp);
                    if (this._state.compareAndSet(SelectKt.getNOT_SELECTED(), pairSelectOp)) {
                        Object decision = pairSelectOp.perform(this);
                        if (decision != null) {
                            return decision;
                        }
                    }
                }
            } else if (state instanceof OpDescriptor) {
                if (otherOp != null) {
                    AtomicOp otherAtomicOp = otherOp.getAtomicOp();
                    if ((otherAtomicOp instanceof AtomicSelectOp) && ((AtomicSelectOp) otherAtomicOp).impl == this) {
                        throw new IllegalStateException("Cannot use matching select clauses on the same object".toString());
                    }
                    if (otherAtomicOp.isEarlierThan((OpDescriptor) state)) {
                        return AtomicKt.RETRY_ATOMIC;
                    }
                }
                ((OpDescriptor) state).perform(this);
            } else {
                if (otherOp != null && state == otherOp.desc) {
                    return CancellableContinuationImplKt.RESUME_TOKEN;
                }
                return null;
            }
        }
        doAfterSelect();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Select.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$PairSelectOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "perform", "", "affected", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp */
    /* loaded from: classes4.dex */
    public static final class PairSelectOp extends OpDescriptor {
        public final LockFreeLinkedListNode.PrepareOp otherOp;

        public PairSelectOp(LockFreeLinkedListNode.PrepareOp otherOp) {
            Intrinsics.checkNotNullParameter(otherOp, "otherOp");
            this.otherOp = otherOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object perform(Object affected) {
            Intrinsics.checkNotNull(affected, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            SelectInstance impl = (SelectInstance) affected;
            this.otherOp.finishPrepare();
            Object decision = this.otherOp.getAtomicOp().decide(null);
            Object update = decision == null ? this.otherOp.desc : SelectKt.getNOT_SELECTED();
            impl._state.compareAndSet(this, update);
            return decision;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp<?> getAtomicOp() {
            return this.otherOp.getAtomicOp();
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Object performAtomicTrySelect(AtomicDesc desc) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        return new AtomicSelectOp(this, desc).perform(null);
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return "SelectInstance(state=" + this._state.getValue() + ", result=" + this._result.getValue() + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Select.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0019\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0002R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "", "impl", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "desc", "Lkotlinx/coroutines/internal/AtomicDesc;", "(Lkotlinx/coroutines/selects/SelectBuilderImpl;Lkotlinx/coroutines/internal/AtomicDesc;)V", "opSequence", "", "getOpSequence", "()J", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareSelectOp", "toString", "", "undoPrepare", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.selects.SelectBuilderImpl$AtomicSelectOp, reason: from toString */
    /* loaded from: classes4.dex */
    public static final class AtomicSelectOp extends AtomicOp<Object> {
        public final AtomicDesc desc;
        public final SelectInstance<?> impl;
        private final long opSequence;

        public AtomicSelectOp(SelectInstance<?> impl, AtomicDesc desc) {
            Intrinsics.checkNotNullParameter(impl, "impl");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.impl = impl;
            this.desc = desc;
            this.opSequence = SelectKt.access$getSelectOpSequenceNumber$p().next();
            desc.setAtomicOp(this);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long getOpSequence() {
            return this.opSequence;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object prepare(Object affected) {
            Object it;
            if (affected == null && (it = prepareSelectOp()) != null) {
                return it;
            }
            try {
                return this.desc.prepare(this);
            } catch (Throwable e) {
                if (affected == null) {
                    undoPrepare();
                }
                throw e;
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(Object affected, Object failure) {
            completeSelect(failure);
            this.desc.complete(this, failure);
        }

        private final Object prepareSelectOp() {
            AtomicRef $this$loop$iv = ((SelectInstance) this.impl)._state;
            while (true) {
                Object state = $this$loop$iv.getValue();
                if (state == this) {
                    return null;
                }
                if (state instanceof OpDescriptor) {
                    ((OpDescriptor) state).perform(this.impl);
                } else if (state == SelectKt.getNOT_SELECTED()) {
                    if (((SelectInstance) this.impl)._state.compareAndSet(SelectKt.getNOT_SELECTED(), this)) {
                        return null;
                    }
                } else {
                    return SelectKt.getALREADY_SELECTED();
                }
            }
        }

        private final void undoPrepare() {
            ((SelectInstance) this.impl)._state.compareAndSet(this, SelectKt.getNOT_SELECTED());
        }

        private final void completeSelect(Object failure) {
            boolean selectSuccess = failure == null;
            Object update = selectSuccess ? null : SelectKt.getNOT_SELECTED();
            if (((SelectInstance) this.impl)._state.compareAndSet(this, update) && selectSuccess) {
                this.impl.doAfterSelect();
            }
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            return "AtomicSelectOp(sequence=" + getOpSequence() + ")";
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause0 $this$invoke, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter($this$invoke, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        $this$invoke.registerSelectClause0(this, block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(SelectClause1<? extends Q> selectClause1, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(selectClause1, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        selectClause1.registerSelectClause1(this, block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, P p, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(selectClause2, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        selectClause2.registerSelectClause2(this, p, block);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long timeMillis, final Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (timeMillis <= 0) {
            if (trySelect()) {
                Undispatched.startCoroutineUnintercepted(block, getCompletion());
            }
        } else {
            Runnable action = new Runnable() { // from class: kotlinx.coroutines.selects.SelectBuilderImpl$onTimeout$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (!SelectInstance.this.trySelect()) {
                        return;
                    }
                    CancellableKt.startCoroutineCancellable(block, SelectInstance.this.getCompletion());
                }
            };
            disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(timeMillis, action, getContext()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Select.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/DisposableHandle;)V", "external__kotlinx.coroutines__android_common__kotlinx_coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.selects.SelectBuilderImpl$DisposeNode */
    /* loaded from: classes4.dex */
    public static final class DisposeNode extends LockFreeLinkedListNode {
        public final DisposableHandle handle;

        public DisposeNode(DisposableHandle handle) {
            Intrinsics.checkNotNullParameter(handle, "handle");
            this.handle = handle;
        }
    }
}
