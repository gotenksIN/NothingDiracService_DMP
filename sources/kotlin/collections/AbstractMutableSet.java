package kotlin.collections;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableSet;

/* compiled from: AbstractMutableSet.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/collections/AbstractMutableSet;", "E", "", "Ljava/util/AbstractSet;", "()V", "add", "", "element", "(Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class AbstractMutableSet<E> extends java.util.AbstractSet<E> implements Set<E>, KMutableSet {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public abstract boolean add(E element);

    public abstract int getSize();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }
}
