package androidx.collection;

import androidx.collection.internal.ContainerHelpers;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableSet;

/* compiled from: ArraySet.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u0000 >*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002>?B\u0019\b\u0016\u0012\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u0019\b\u0016\u0012\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0011\b\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000J\u0016\u0010\u001a\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0002J\b\u0010 \u001a\u00020\u001bH\u0016J\u0016\u0010!\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0019J\u0016\u0010\"\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\fJ\u0013\u0010%\u001a\u00020\u00172\b\u0010&\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010'\u001a\u00020\fH\u0016J\u0010\u0010(\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010(\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001f\u001a\u00020\fH\u0002J\b\u0010*\u001a\u00020\fH\u0002J\b\u0010+\u001a\u00020\u0017H\u0016J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0096\u0002J\u0017\u0010.\u001a\u00020\u001b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0082\bJ\u0015\u00102\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u0016\u00103\u001a\u00020\u00172\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000J\u0016\u00103\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0013\u00104\u001a\u00028\u00002\u0006\u00105\u001a\u00020\f¢\u0006\u0002\u00106J\u0016\u00107\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0013\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\t¢\u0006\u0002\u00109J%\u00108\u001a\b\u0012\u0004\u0012\u0002H:0\t\"\u0004\b\u0001\u0010:2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H:0\t¢\u0006\u0002\u0010;J\b\u0010<\u001a\u000201H\u0016J\u0013\u0010=\u001a\u00028\u00002\u0006\u00105\u001a\u00020\f¢\u0006\u0002\u00106R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006@"}, d2 = {"Landroidx/collection/ArraySet;", "E", "", "", "set", "(Landroidx/collection/ArraySet;)V", "", "(Ljava/util/Collection;)V", "array", "", "([Ljava/lang/Object;)V", "capacity", "", "(I)V", "_size", "", "[Ljava/lang/Object;", "hashes", "", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "", "elements", "allocArrays", "binarySearchInternal", "hash", "clear", "contains", "containsAll", "ensureCapacity", "minimumCapacity", "equals", "other", "hashCode", "indexOf", "key", "indexOfNull", "isEmpty", "iterator", "", "printlnIfDebug", "message", "Lkotlin/Function0;", "", "remove", "removeAll", "removeAt", "index", "(I)Ljava/lang/Object;", "retainAll", "toArray", "()[Ljava/lang/Object;", "T", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "valueAt", "Companion", "ElementIterator", "collection"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E>, KMutableCollection, KMutableSet {

    @Deprecated
    private static final int BASE_SIZE = 4;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final boolean DEBUG = false;

    @Deprecated
    private static final String TAG = "ArraySet";
    private int _size;
    private Object[] array;
    private int[] hashes;

    public ArraySet() {
        this(0, 1, null);
    }

    public ArraySet(int capacity) {
        this.hashes = ContainerHelpers.EMPTY_INTS;
        this.array = ContainerHelpers.EMPTY_OBJECTS;
        if (capacity <= 0) {
            return;
        }
        allocArrays(capacity);
    }

    public /* synthetic */ ArraySet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    @Override // java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return get_size();
    }

    /* renamed from: getSize, reason: from getter */
    public int get_size() {
        return this._size;
    }

    public ArraySet(ArraySet<? extends E> arraySet) {
        this(0);
        if (arraySet != null) {
            addAll((ArraySet) arraySet);
        }
    }

    public ArraySet(Collection<? extends E> collection) {
        this(0);
        if (collection != null) {
            addAll(collection);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(E[] eArr) {
        this(0);
        if (eArr != null) {
            Iterator it = ArrayIteratorKt.iterator(eArr);
            while (it.hasNext()) {
                Object value = it.next();
                add(value);
            }
        }
    }

    private final int binarySearchInternal(int hash) {
        try {
            return ContainerHelpers.binarySearch(this.hashes, this._size, hash);
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    private final int indexOf(Object key, int hash) {
        int n = this._size;
        if (n == 0) {
            return -1;
        }
        int index = binarySearchInternal(hash);
        if (index < 0) {
            return index;
        }
        if (Intrinsics.areEqual(key, this.array[index])) {
            return index;
        }
        int end = index + 1;
        while (end < n && this.hashes[end] == hash) {
            if (Intrinsics.areEqual(key, this.array[end])) {
                return end;
            }
            end++;
        }
        for (int i = index - 1; i >= 0 && this.hashes[i] == hash; i--) {
            if (Intrinsics.areEqual(key, this.array[i])) {
                return i;
            }
        }
        return ~end;
    }

    private final int indexOfNull() {
        return indexOf(null, 0);
    }

    private final void allocArrays(int size) {
        this.hashes = new int[size];
        this.array = new Object[size];
    }

    private final void printlnIfDebug(Function0<String> message) {
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        if (this._size != 0) {
            this.hashes = ContainerHelpers.EMPTY_INTS;
            this.array = ContainerHelpers.EMPTY_OBJECTS;
            this._size = 0;
        }
        if (this._size != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final void ensureCapacity(int minimumCapacity) {
        int oSize = this._size;
        if (this.hashes.length < minimumCapacity) {
            int[] ohashes = this.hashes;
            Object[] oarray = this.array;
            allocArrays(minimumCapacity);
            int i = this._size;
            if (i > 0) {
                ArraysKt.copyInto$default(ohashes, this.hashes, 0, 0, i, 6, (Object) null);
                ArraysKt.copyInto$default(oarray, this.array, 0, 0, this._size, 6, (Object) null);
            }
        }
        if (this._size != oSize) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    public final int indexOf(Object key) {
        return key == null ? indexOfNull() : indexOf(key, key.hashCode());
    }

    public final E valueAt(int index) {
        return (E) this.array[index];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this._size <= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E element) {
        int hash;
        int index;
        int oSize = this._size;
        if (element == null) {
            hash = 0;
            index = indexOfNull();
        } else {
            hash = element.hashCode();
            index = indexOf(element, hash);
        }
        if (index >= 0) {
            return false;
        }
        int index2 = ~index;
        if (oSize >= this.hashes.length) {
            int n = 8;
            if (oSize >= 8) {
                n = (oSize >> 1) + oSize;
            } else if (oSize < 4) {
                n = 4;
            }
            int[] ohashes = this.hashes;
            Object[] oarray = this.array;
            allocArrays(n);
            if (oSize != this._size) {
                throw new ConcurrentModificationException();
            }
            int[] iArr = this.hashes;
            if (!(iArr.length == 0)) {
                ArraysKt.copyInto$default(ohashes, iArr, 0, 0, ohashes.length, 6, (Object) null);
                ArraysKt.copyInto$default(oarray, this.array, 0, 0, oarray.length, 6, (Object) null);
            }
        }
        if (index2 < oSize) {
            int[] iArr2 = this.hashes;
            ArraysKt.copyInto(iArr2, iArr2, index2 + 1, index2, oSize);
            Object[] objArr = this.array;
            ArraysKt.copyInto(objArr, objArr, index2 + 1, index2, oSize);
        }
        int i = this._size;
        if (oSize == i) {
            int[] iArr3 = this.hashes;
            if (index2 < iArr3.length) {
                iArr3[index2] = hash;
                this.array[index2] = element;
                this._size = i + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final void addAll(ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int n = array._size;
        ensureCapacity(this._size + n);
        if (this._size == 0) {
            if (n > 0) {
                ArraysKt.copyInto$default(array.hashes, this.hashes, 0, 0, n, 6, (Object) null);
                ArraysKt.copyInto$default(array.array, this.array, 0, 0, n, 6, (Object) null);
                if (this._size != 0) {
                    throw new ConcurrentModificationException();
                }
                this._size = n;
                return;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            add(array.valueAt(i));
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index >= 0) {
            removeAt(index);
            return true;
        }
        return false;
    }

    public final E removeAt(int index) {
        int i;
        int i2 = this._size;
        E e = (E) this.array[index];
        if (i2 <= 1) {
            clear();
        } else {
            int i3 = i2 - 1;
            int[] iArr = this.hashes;
            if (iArr.length > 8 && (i = this._size) < iArr.length / 3) {
                int i4 = i > 8 ? i + (i >> 1) : 8;
                int[] iArr2 = this.hashes;
                Object[] objArr = this.array;
                allocArrays(i4);
                if (index > 0) {
                    ArraysKt.copyInto$default(iArr2, this.hashes, 0, 0, index, 6, (Object) null);
                    ArraysKt.copyInto$default(objArr, this.array, 0, 0, index, 6, (Object) null);
                }
                if (index < i3) {
                    ArraysKt.copyInto(iArr2, this.hashes, index, index + 1, i3 + 1);
                    ArraysKt.copyInto(objArr, this.array, index, index + 1, i3 + 1);
                }
            } else {
                if (index < i3) {
                    ArraysKt.copyInto(iArr, iArr, index, index + 1, i3 + 1);
                    Object[] objArr2 = this.array;
                    ArraysKt.copyInto(objArr2, objArr2, index, index + 1, i3 + 1);
                }
                this.array[i3] = null;
            }
            if (i2 != this._size) {
                throw new ConcurrentModificationException();
            }
            this._size = i3;
        }
        return e;
    }

    public final boolean removeAll(ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int n = array._size;
        int originalSize = this._size;
        for (int i = 0; i < n; i++) {
            remove(array.valueAt(i));
        }
        int i2 = this._size;
        return originalSize != i2;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return ArraysKt.copyOfRange(this.array, 0, this._size);
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int length = array.length;
        int i = this._size;
        if (length < i) {
            return (T[]) ArraysKt.copyOfRange(this.array, 0, i);
        }
        ArraysKt.copyInto(this.array, array, 0, 0, i);
        int length2 = array.length;
        int i2 = this._size;
        if (length2 > i2) {
            array[i2] = null;
        }
        return array;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Set) || size() != ((Set) other).size()) {
            return false;
        }
        try {
            int i = this._size;
            for (int i2 = 0; i2 < i; i2++) {
                Object mine = valueAt(i2);
                if (!((Set) other).contains(mine)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] hashes = this.hashes;
        int s = this._size;
        int result = 0;
        for (int i = 0; i < s; i++) {
            result += hashes[i];
        }
        return result;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder $this$toString_u24lambda_u248 = new StringBuilder(this._size * 14);
        $this$toString_u24lambda_u248.append('{');
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                $this$toString_u24lambda_u248.append(", ");
            }
            Object value = valueAt(i2);
            if (value != this) {
                $this$toString_u24lambda_u248.append(value);
            } else {
                $this$toString_u24lambda_u248.append("(this Set)");
            }
        }
        $this$toString_u24lambda_u248.append('}');
        String sb = $this$toString_u24lambda_u248.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /* compiled from: ArraySet.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\t"}, d2 = {"Landroidx/collection/ArraySet$ElementIterator;", "Landroidx/collection/IndexBasedArrayIterator;", "(Landroidx/collection/ArraySet;)V", "elementAt", "index", "", "(I)Ljava/lang/Object;", "removeAt", "", "collection"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes.dex */
    private final class ElementIterator extends IndexBasedArrayIterator<E> {
        public ElementIterator() {
            super(ArraySet.this._size);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected E elementAt(int index) {
            return ArraySet.this.valueAt(index);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected void removeAt(int index) {
            ArraySet.this.removeAt(index);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (Object item : elements) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        ensureCapacity(this._size + elements.size());
        boolean added = false;
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            added |= add(it.next());
        }
        return added;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean removed = false;
        for (Object value : elements) {
            removed |= remove(value);
        }
        return removed;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean removed = false;
        int i = this._size;
        while (true) {
            i--;
            if (-1 < i) {
                if (!CollectionsKt.contains(elements, this.array[i])) {
                    removeAt(i);
                    removed = true;
                }
            } else {
                return removed;
            }
        }
    }

    /* compiled from: ArraySet.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/collection/ArraySet$Companion;", "", "()V", "BASE_SIZE", "", "DEBUG", "", "TAG", "", "collection"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
