package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StableIdStorage;
import androidx.recyclerview.widget.ViewTypeStorage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NestedAdapterWrapper {
    public final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    private RecyclerView.AdapterDataObserver mAdapterObserver = new RecyclerView.AdapterDataObserver() { // from class: androidx.recyclerview.widget.NestedAdapterWrapper.1
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            NestedAdapterWrapper nestedAdapterWrapper = NestedAdapterWrapper.this;
            nestedAdapterWrapper.mCachedItemCount = nestedAdapterWrapper.adapter.getItemCount();
            NestedAdapterWrapper.this.mCallback.onChanged(NestedAdapterWrapper.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, positionStart, itemCount, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, positionStart, itemCount, payload);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int positionStart, int itemCount) {
            NestedAdapterWrapper.this.mCachedItemCount += itemCount;
            NestedAdapterWrapper.this.mCallback.onItemRangeInserted(NestedAdapterWrapper.this, positionStart, itemCount);
            if (NestedAdapterWrapper.this.mCachedItemCount > 0 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            NestedAdapterWrapper.this.mCachedItemCount -= itemCount;
            NestedAdapterWrapper.this.mCallback.onItemRangeRemoved(NestedAdapterWrapper.this, positionStart, itemCount);
            if (NestedAdapterWrapper.this.mCachedItemCount < 1 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            Preconditions.checkArgument(itemCount == 1, "moving more than 1 item is not supported in RecyclerView");
            NestedAdapterWrapper.this.mCallback.onItemRangeMoved(NestedAdapterWrapper.this, fromPosition, toPosition);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
        }
    };
    int mCachedItemCount;
    final Callback mCallback;
    private final StableIdStorage.StableIdLookup mStableIdLookup;
    private final ViewTypeStorage.ViewTypeLookup mViewTypeLookup;

    /* loaded from: classes3.dex */
    interface Callback {
        void onChanged(NestedAdapterWrapper nestedAdapterWrapper);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2, Object obj);

        void onItemRangeInserted(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeMoved(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeRemoved(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onStateRestorationPolicyChanged(NestedAdapterWrapper nestedAdapterWrapper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NestedAdapterWrapper(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, Callback callback, ViewTypeStorage viewTypeStorage, StableIdStorage.StableIdLookup stableIdLookup) {
        this.adapter = adapter;
        this.mCallback = callback;
        this.mViewTypeLookup = viewTypeStorage.createViewTypeWrapper(this);
        this.mStableIdLookup = stableIdLookup;
        this.mCachedItemCount = adapter.getItemCount();
        adapter.registerAdapterDataObserver(this.mAdapterObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        this.adapter.unregisterAdapterDataObserver(this.mAdapterObserver);
        this.mViewTypeLookup.dispose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCachedItemCount() {
        return this.mCachedItemCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getItemViewType(int localPosition) {
        return this.mViewTypeLookup.localToGlobal(this.adapter.getItemViewType(localPosition));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int globalViewType) {
        int localType = this.mViewTypeLookup.globalToLocal(globalViewType);
        return this.adapter.onCreateViewHolder(parent, localType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int localPosition) {
        this.adapter.bindViewHolder(viewHolder, localPosition);
    }

    public long getItemId(int localPosition) {
        long localItemId = this.adapter.getItemId(localPosition);
        return this.mStableIdLookup.localToGlobal(localItemId);
    }
}
