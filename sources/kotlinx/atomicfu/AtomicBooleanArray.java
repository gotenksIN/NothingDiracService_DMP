package kotlinx.atomicfu;

import kotlin.Metadata;

/* compiled from: AtomicFU.common.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0003H\u0087\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001a\u0010\u0002\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lkotlinx/atomicfu/AtomicBooleanArray;", "", "size", "", "(I)V", "array", "", "Lkotlinx/atomicfu/AtomicBoolean;", "[Lkotlinx/atomicfu/AtomicBoolean;", "getSize$annotations", "()V", "getSize", "()I", "get", "index", "external__kotlinx.atomicfu__android_common__kotlinx_atomicfu"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AtomicBooleanArray {
    private final AtomicBoolean[] array;

    public static /* synthetic */ void getSize$annotations() {
    }

    public AtomicBooleanArray(int size) {
        AtomicBoolean[] atomicBooleanArr = new AtomicBoolean[size];
        for (int i = 0; i < size; i++) {
            atomicBooleanArr[i] = AtomicFU.atomic(false);
        }
        this.array = atomicBooleanArr;
    }

    public final int getSize() {
        return this.array.length;
    }

    public final AtomicBoolean get(int index) {
        return this.array[index];
    }
}
