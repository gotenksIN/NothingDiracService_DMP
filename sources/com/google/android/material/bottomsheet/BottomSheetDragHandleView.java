package com.google.android.material.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes4.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_BottomSheet_DragHandle;
    private final AccessibilityManager accessibilityManager;
    private boolean accessibilityServiceEnabled;
    private BottomSheetBehavior<?> bottomSheetBehavior;
    private final BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    private final String clickFeedback;
    private final String clickToCollapseActionLabel;
    private final String clickToExpandActionLabel;
    private boolean interactable;

    public BottomSheetDragHandleView(Context context) {
        this(context, null);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.bottomSheetDragHandleStyle);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.clickToExpandActionLabel = getResources().getString(R.string.bottomsheet_action_expand);
        this.clickToCollapseActionLabel = getResources().getString(R.string.bottomsheet_action_collapse);
        this.clickFeedback = getResources().getString(R.string.bottomsheet_drag_handle_clicked);
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                BottomSheetDragHandleView.this.onBottomSheetStateChanged(newState);
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        };
        Context context2 = getContext();
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        updateInteractableState();
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                if (event.getEventType() == 1) {
                    BottomSheetDragHandleView.this.toggleBottomSheetIfPossible();
                }
            }
        });
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setBottomSheetBehavior(findParentBottomSheetBehavior());
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.addAccessibilityStateChangeListener(this);
            onAccessibilityStateChanged(this.accessibilityManager.isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(this);
        }
        setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        this.accessibilityServiceEnabled = enabled;
        updateInteractableState();
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> behavior) {
        BottomSheetBehavior<?> bottomSheetBehavior = this.bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.removeBottomSheetCallback(this.bottomSheetCallback);
        }
        this.bottomSheetBehavior = behavior;
        if (behavior != null) {
            onBottomSheetStateChanged(behavior.getState());
            this.bottomSheetBehavior.addBottomSheetCallback(this.bottomSheetCallback);
        }
        updateInteractableState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBottomSheetStateChanged(int state) {
        String label;
        if (state == 4) {
            label = this.clickToExpandActionLabel;
        } else {
            label = this.clickToCollapseActionLabel;
        }
        ViewCompat.replaceAccessibilityAction(this, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, label, new AccessibilityViewCommand() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView$$ExternalSyntheticLambda0
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return BottomSheetDragHandleView.this.m828xa7b4c95f(view, commandArguments);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onBottomSheetStateChanged$0$com-google-android-material-bottomsheet-BottomSheetDragHandleView, reason: not valid java name */
    public /* synthetic */ boolean m828xa7b4c95f(View v, AccessibilityViewCommand.CommandArguments args) {
        return toggleBottomSheetIfPossible();
    }

    private void updateInteractableState() {
        this.interactable = this.accessibilityServiceEnabled && this.bottomSheetBehavior != null;
        ViewCompat.setImportantForAccessibility(this, this.bottomSheetBehavior == null ? 2 : 1);
        setClickable(this.interactable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean toggleBottomSheetIfPossible() {
        if (!this.interactable) {
            return false;
        }
        announceAccessibilityEvent(this.clickFeedback);
        boolean collapsed = this.bottomSheetBehavior.getState() == 4;
        this.bottomSheetBehavior.setState(collapsed ? 3 : 4);
        return true;
    }

    private void announceAccessibilityEvent(String announcement) {
        if (this.accessibilityManager == null) {
            return;
        }
        AccessibilityEvent announce = AccessibilityEvent.obtain(16384);
        announce.getText().add(announcement);
        this.accessibilityManager.sendAccessibilityEvent(announce);
    }

    private BottomSheetBehavior<?> findParentBottomSheetBehavior() {
        View parent = this;
        while (true) {
            View parentView = getParentView(parent);
            parent = parentView;
            if (parentView != null) {
                ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.Behavior<?> behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
                    if (behavior instanceof BottomSheetBehavior) {
                        return (BottomSheetBehavior) behavior;
                    }
                }
            } else {
                return null;
            }
        }
    }

    private static View getParentView(View view) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
