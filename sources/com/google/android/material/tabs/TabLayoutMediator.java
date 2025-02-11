package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public final class TabLayoutMediator {
    private RecyclerView.Adapter<?> adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private TabLayout.OnTabSelectedListener onTabSelectedListener;
    private RecyclerView.AdapterDataObserver pagerAdapterObserver;
    private final boolean smoothScroll;
    private final TabConfigurationStrategy tabConfigurationStrategy;
    private final TabLayout tabLayout;
    private final ViewPager2 viewPager;

    /* loaded from: classes4.dex */
    public interface TabConfigurationStrategy {
        void onConfigureTab(TabLayout.Tab tab, int i);
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager, TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager, boolean autoRefresh, TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager, autoRefresh, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager, boolean autoRefresh, boolean smoothScroll, TabConfigurationStrategy tabConfigurationStrategy) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        this.autoRefresh = autoRefresh;
        this.smoothScroll = smoothScroll;
        this.tabConfigurationStrategy = tabConfigurationStrategy;
    }

    public void attach() {
        if (this.attached) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        RecyclerView.Adapter<?> adapter = this.viewPager.getAdapter();
        this.adapter = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.attached = true;
        TabLayoutOnPageChangeCallback tabLayoutOnPageChangeCallback = new TabLayoutOnPageChangeCallback(this.tabLayout);
        this.onPageChangeCallback = tabLayoutOnPageChangeCallback;
        this.viewPager.registerOnPageChangeCallback(tabLayoutOnPageChangeCallback);
        ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(this.viewPager, this.smoothScroll);
        this.onTabSelectedListener = viewPagerOnTabSelectedListener;
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) viewPagerOnTabSelectedListener);
        if (this.autoRefresh) {
            PagerAdapterObserver pagerAdapterObserver = new PagerAdapterObserver();
            this.pagerAdapterObserver = pagerAdapterObserver;
            this.adapter.registerAdapterDataObserver(pagerAdapterObserver);
        }
        populateTabsFromPagerAdapter();
        this.tabLayout.setScrollPosition(this.viewPager.getCurrentItem(), 0.0f, true);
    }

    public void detach() {
        RecyclerView.Adapter<?> adapter;
        if (this.autoRefresh && (adapter = this.adapter) != null) {
            adapter.unregisterAdapterDataObserver(this.pagerAdapterObserver);
            this.pagerAdapterObserver = null;
        }
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        this.viewPager.unregisterOnPageChangeCallback(this.onPageChangeCallback);
        this.onTabSelectedListener = null;
        this.onPageChangeCallback = null;
        this.adapter = null;
        this.attached = false;
    }

    public boolean isAttached() {
        return this.attached;
    }

    void populateTabsFromPagerAdapter() {
        RecyclerView.Adapter<?> adapter = this.adapter;
        if (adapter == null) {
            return;
        }
        updateTabsFromPagerAdapter(0, Math.max(adapter.getItemCount(), this.tabLayout.getTabCount()));
    }

    void insertTabsFromPagerAdapter(int start, int itemCount) {
        updateTabsFromPagerAdapter(start, itemCount, true, false);
    }

    void removeTabsFromPagerAdapter(int start, int itemCount) {
        updateTabsFromPagerAdapter(start, itemCount, false, true);
    }

    void updateTabsFromPagerAdapter(int start, int itemCount) {
        updateTabsFromPagerAdapter(start, itemCount, true, true);
    }

    private void updateTabsFromPagerAdapter(int start, int itemCount, boolean insert, boolean remove) {
        if (this.adapter == null) {
            return;
        }
        if (remove) {
            int tabViewCount = this.tabLayout.getTabCount();
            int removeItemCount = Math.min(itemCount, tabViewCount - start);
            if (removeItemCount == tabViewCount) {
                this.tabLayout.removeAllTabs();
            } else {
                for (int i = 0; i < removeItemCount; i++) {
                    this.tabLayout.removeTabAt(start);
                }
            }
        }
        int adapterCount = this.adapter.getItemCount();
        if (insert) {
            int insertItemCount = Math.min(itemCount, adapterCount - start);
            for (int i2 = start; i2 < start + insertItemCount; i2++) {
                TabLayout.Tab tab = this.tabLayout.newTab();
                this.tabConfigurationStrategy.onConfigureTab(tab, i2);
                this.tabLayout.addTab(tab, i2, false);
            }
        }
        if (adapterCount == 0) {
            return;
        }
        int lastItem = this.tabLayout.getTabCount() - 1;
        int currItem = Math.min(this.viewPager.getCurrentItem(), lastItem);
        if (currItem != this.tabLayout.getSelectedTabPosition()) {
            TabLayout tabLayout = this.tabLayout;
            tabLayout.selectTab(tabLayout.getTabAt(currItem));
        }
    }

    /* loaded from: classes4.dex */
    private static class TabLayoutOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        TabLayoutOnPageChangeCallback(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
            reset();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int state) {
            this.previousScrollState = this.scrollState;
            this.scrollState = state;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i = this.scrollState;
                boolean updateText = i != 2 || this.previousScrollState == 1;
                boolean updateIndicator = (i == 2 && this.previousScrollState == 0) ? false : true;
                tabLayout.setScrollPosition(position, positionOffset, updateText, updateIndicator);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int position) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != position && position < tabLayout.getTabCount()) {
                int i = this.scrollState;
                boolean updateIndicator = i == 0 || (i == 2 && this.previousScrollState == 0);
                tabLayout.selectTab(tabLayout.getTabAt(position), updateIndicator);
            }
        }

        void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    /* loaded from: classes4.dex */
    private static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        private final boolean smoothScroll;
        private final ViewPager2 viewPager;

        ViewPagerOnTabSelectedListener(ViewPager2 viewPager, boolean smoothScroll) {
            this.viewPager = viewPager;
            this.smoothScroll = smoothScroll;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition(), this.smoothScroll);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }

    /* loaded from: classes4.dex */
    private class PagerAdapterObserver extends RecyclerView.AdapterDataObserver {
        PagerAdapterObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount) {
            TabLayoutMediator.this.updateTabsFromPagerAdapter(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            TabLayoutMediator.this.updateTabsFromPagerAdapter(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int positionStart, int itemCount) {
            TabLayoutMediator.this.insertTabsFromPagerAdapter(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            TabLayoutMediator.this.removeTabsFromPagerAdapter(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            TabLayoutMediator.this.removeTabsFromPagerAdapter(fromPosition, itemCount);
            TabLayoutMediator.this.insertTabsFromPagerAdapter(toPosition, itemCount);
        }
    }
}
