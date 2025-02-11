package androidx.core.view;

import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.collection.SimpleArrayMap;
import androidx.core.R;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.view.contentcapture.ContentCaptureSessionCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_AUTO = 0;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO = 2;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS = 8;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES = 1;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS = 4;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    private static Field sAccessibilityDelegateField;
    private static Method sChildrenDrawingOrderMethod;
    private static Method sDispatchFinishTemporaryDetach;
    private static Method sDispatchStartTemporaryDetach;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    private static boolean sTempDetachBound;
    private static ThreadLocal<Rect> sThreadLocalRect;
    private static WeakHashMap<View, String> sTransitionNameMap;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    private static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new OnReceiveContentViewBehavior() { // from class: androidx.core.view.ViewCompat$$ExternalSyntheticLambda0
        @Override // androidx.core.view.OnReceiveContentViewBehavior
        public final ContentInfoCompat onReceiveContent(ContentInfoCompat contentInfoCompat) {
            return ViewCompat.lambda$static$0(contentInfoCompat);
        }
    };
    private static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FocusDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FocusRealDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface NestedScrollType {
    }

    /* loaded from: classes.dex */
    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ScrollAxis {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ScrollIndicators {
    }

    private static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal<>();
        }
        Rect rect = sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void saveAttributeDataForStyleable(View view, Context context, int[] styleable, AttributeSet attrs, TypedArray t, int defStyleAttr, int defStyleRes) {
        Api29Impl.saveAttributeDataForStyleable(view, context, styleable, attrs, t, defStyleAttr, defStyleRes);
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int direction) {
        return view.canScrollHorizontally(direction);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int direction) {
        return view.canScrollVertically(direction);
    }

    @Deprecated
    public static int getOverScrollMode(View v) {
        return v.getOverScrollMode();
    }

    @Deprecated
    public static void setOverScrollMode(View v, int overScrollMode) {
        v.setOverScrollMode(overScrollMode);
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onPopulateAccessibilityEvent(event);
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onInitializeAccessibilityEvent(event);
    }

    public static void onInitializeAccessibilityNodeInfo(View v, AccessibilityNodeInfoCompat info) {
        v.onInitializeAccessibilityNodeInfo(info.unwrap());
    }

    public static void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
        if (delegate == null && (getAccessibilityDelegateInternal(v) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            delegate = new AccessibilityDelegateCompat();
        }
        v.setAccessibilityDelegate(delegate == null ? null : delegate.getBridge());
    }

    public static void setAutofillHints(View v, String... autofillHints) {
        Api26Impl.setAutofillHints(v, autofillHints);
    }

    public static int getImportantForAutofill(View v) {
        return Api26Impl.getImportantForAutofill(v);
    }

    public static void setImportantForAutofill(View v, int mode) {
        Api26Impl.setImportantForAutofill(v, mode);
    }

    public static boolean isImportantForAutofill(View v) {
        return Api26Impl.isImportantForAutofill(v);
    }

    public static AutofillIdCompat getAutofillId(View v) {
        return AutofillIdCompat.toAutofillIdCompat(Api26Impl.getAutofillId(v));
    }

    public static void setAutofillId(View v, AutofillIdCompat id) {
        Api28Impl.setAutofillId(v, id.toAutofillId());
    }

    public static void setImportantForContentCapture(View v, int mode) {
        Api30Impl.setImportantForContentCapture(v, mode);
    }

    public static int getImportantForContentCapture(View v) {
        return Api30Impl.getImportantForContentCapture(v);
    }

    public static boolean isImportantForContentCapture(View v) {
        return Api30Impl.isImportantForContentCapture(v);
    }

    public static ContentCaptureSessionCompat getContentCaptureSession(View v) {
        ContentCaptureSession session = Api29Impl.getContentCaptureSession(v);
        if (session == null) {
            return null;
        }
        return ContentCaptureSessionCompat.toContentCaptureSessionCompat(session, v);
    }

    public static void setContentCaptureSession(View v, ContentCaptureSessionCompat contentCaptureSession) {
        Api29Impl.setContentCaptureSession(v, contentCaptureSession.toContentCaptureSession());
    }

    public static boolean hasAccessibilityDelegate(View view) {
        return getAccessibilityDelegateInternal(view) != null;
    }

    public static AccessibilityDelegateCompat getAccessibilityDelegate(View view) {
        View.AccessibilityDelegate delegate = getAccessibilityDelegateInternal(view);
        if (delegate == null) {
            return null;
        }
        if (delegate instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) {
            return ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) delegate).mCompat;
        }
        return new AccessibilityDelegateCompat(delegate);
    }

    static void ensureAccessibilityDelegateCompat(View v) {
        AccessibilityDelegateCompat delegateCompat = getAccessibilityDelegate(v);
        if (delegateCompat == null) {
            delegateCompat = new AccessibilityDelegateCompat();
        }
        setAccessibilityDelegate(v, delegateCompat);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateInternal(View v) {
        return Api29Impl.getAccessibilityDelegate(v);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateThroughReflection(View v) {
        if (sAccessibilityDelegateCheckFailed) {
            return null;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable th) {
                sAccessibilityDelegateCheckFailed = true;
                return null;
            }
        }
        try {
            Object o = sAccessibilityDelegateField.get(v);
            if (o instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) o;
            }
            return null;
        } catch (Throwable th2) {
            sAccessibilityDelegateCheckFailed = true;
            return null;
        }
    }

    public static boolean hasTransientState(View view) {
        return Api16Impl.hasTransientState(view);
    }

    public static void setHasTransientState(View view, boolean hasTransientState) {
        Api16Impl.setHasTransientState(view, hasTransientState);
    }

    public static void postInvalidateOnAnimation(View view) {
        Api16Impl.postInvalidateOnAnimation(view);
    }

    public static void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
        Api16Impl.postInvalidateOnAnimation(view, left, top, right, bottom);
    }

    public static void postOnAnimation(View view, Runnable action) {
        Api16Impl.postOnAnimation(view, action);
    }

    public static void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
        Api16Impl.postOnAnimationDelayed(view, action, delayMillis);
    }

    public static int getImportantForAccessibility(View view) {
        return Api16Impl.getImportantForAccessibility(view);
    }

    public static void setImportantForAccessibility(View view, int mode) {
        Api16Impl.setImportantForAccessibility(view, mode);
    }

    public static boolean isImportantForAccessibility(View view) {
        return Api21Impl.isImportantForAccessibility(view);
    }

    public static boolean performAccessibilityAction(View view, int action, Bundle arguments) {
        return Api16Impl.performAccessibilityAction(view, action, arguments);
    }

    public static int addAccessibilityAction(View view, CharSequence label, AccessibilityViewCommand command) {
        int actionId = getAvailableActionIdFromResources(view, label);
        if (actionId != -1) {
            AccessibilityNodeInfoCompat.AccessibilityActionCompat action = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId, label, command);
            addAccessibilityAction(view, action);
        }
        return actionId;
    }

    private static int getAvailableActionIdFromResources(View view, CharSequence label) {
        int result = -1;
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actions = getActionList(view);
        for (int i = 0; i < actions.size(); i++) {
            if (TextUtils.equals(label, actions.get(i).getLabel())) {
                return actions.get(i).getId();
            }
        }
        int i2 = 0;
        while (true) {
            int[] iArr = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            if (i2 >= iArr.length || result != -1) {
                break;
            }
            int id = iArr[i2];
            boolean idAvailable = true;
            for (int j = 0; j < actions.size(); j++) {
                idAvailable &= actions.get(j).getId() != id;
            }
            if (idAvailable) {
                result = id;
            }
            i2++;
        }
        return result;
    }

    public static void replaceAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat replacedAction, CharSequence label, AccessibilityViewCommand command) {
        if (command == null && label == null) {
            removeAccessibilityAction(view, replacedAction.getId());
        } else {
            addAccessibilityAction(view, replacedAction.createReplacementAction(label, command));
        }
    }

    private static void addAccessibilityAction(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat action) {
        ensureAccessibilityDelegateCompat(view);
        removeActionWithId(action.getId(), view);
        getActionList(view).add(action);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    public static void removeAccessibilityAction(View view, int actionId) {
        removeActionWithId(actionId, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    private static void removeActionWithId(int actionId, View view) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actions = getActionList(view);
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getId() == actionId) {
                actions.remove(i);
                return;
            }
        }
    }

    private static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View view) {
        ArrayList<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actions = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (actions == null) {
            ArrayList<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actions2 = new ArrayList<>();
            view.setTag(R.id.tag_accessibility_actions, actions2);
            return actions2;
        }
        return actions;
    }

    public static void setStateDescription(View view, CharSequence stateDescription) {
        stateDescriptionProperty().set(view, stateDescription);
    }

    public static CharSequence getStateDescription(View view) {
        return stateDescriptionProperty().get(view);
    }

    public static void enableAccessibleClickableSpanSupport(View view) {
        ensureAccessibilityDelegateCompat(view);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProvider provider = Api16Impl.getAccessibilityNodeProvider(view);
        if (provider != null) {
            return new AccessibilityNodeProviderCompat(provider);
        }
        return null;
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    @Deprecated
    public static void setLayerType(View view, int layerType, Paint paint) {
        view.setLayerType(layerType, paint);
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLabelFor(View view) {
        return Api17Impl.getLabelFor(view);
    }

    public static void setLabelFor(View view, int labeledId) {
        Api17Impl.setLabelFor(view, labeledId);
    }

    public static void setLayerPaint(View view, Paint paint) {
        Api17Impl.setLayerPaint(view, paint);
    }

    public static int getLayoutDirection(View view) {
        return Api17Impl.getLayoutDirection(view);
    }

    public static void setLayoutDirection(View view, int layoutDirection) {
        Api17Impl.setLayoutDirection(view, layoutDirection);
    }

    public static ViewParent getParentForAccessibility(View view) {
        return Api16Impl.getParentForAccessibility(view);
    }

    public static <T extends View> T requireViewById(View view, int id) {
        return (T) Api28Impl.requireViewById(view, id);
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    @Deprecated
    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int combineMeasuredStates(int curState, int newState) {
        return View.combineMeasuredStates(curState, newState);
    }

    public static int getAccessibilityLiveRegion(View view) {
        return Api19Impl.getAccessibilityLiveRegion(view);
    }

    public static void setAccessibilityLiveRegion(View view, int mode) {
        Api19Impl.setAccessibilityLiveRegion(view, mode);
    }

    public static int getPaddingStart(View view) {
        return Api17Impl.getPaddingStart(view);
    }

    public static int getPaddingEnd(View view) {
        return Api17Impl.getPaddingEnd(view);
    }

    public static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
        Api17Impl.setPaddingRelative(view, start, top, end, bottom);
    }

    private static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "Couldn't find method", e);
        }
        sTempDetachBound = true;
    }

    public static void dispatchStartTemporaryDetach(View view) {
        Api24Impl.dispatchStartTemporaryDetach(view);
    }

    public static void dispatchFinishTemporaryDetach(View view) {
        Api24Impl.dispatchFinishTemporaryDetach(view);
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    public static int getMinimumWidth(View view) {
        return Api16Impl.getMinimumWidth(view);
    }

    public static int getMinimumHeight(View view) {
        return Api16Impl.getMinimumHeight(view);
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap<>();
        }
        ViewPropertyAnimatorCompat vpa = sViewPropertyAnimatorMap.get(view);
        if (vpa == null) {
            ViewPropertyAnimatorCompat vpa2 = new ViewPropertyAnimatorCompat(view);
            sViewPropertyAnimatorMap.put(view, vpa2);
            return vpa2;
        }
        return vpa;
    }

    @Deprecated
    public static void setTranslationX(View view, float value) {
        view.setTranslationX(value);
    }

    @Deprecated
    public static void setTranslationY(View view, float value) {
        view.setTranslationY(value);
    }

    @Deprecated
    public static void setAlpha(View view, float value) {
        view.setAlpha(value);
    }

    @Deprecated
    public static void setX(View view, float value) {
        view.setX(value);
    }

    @Deprecated
    public static void setY(View view, float value) {
        view.setY(value);
    }

    @Deprecated
    public static void setRotation(View view, float value) {
        view.setRotation(value);
    }

    @Deprecated
    public static void setRotationX(View view, float value) {
        view.setRotationX(value);
    }

    @Deprecated
    public static void setRotationY(View view, float value) {
        view.setRotationY(value);
    }

    @Deprecated
    public static void setScaleX(View view, float value) {
        view.setScaleX(value);
    }

    @Deprecated
    public static void setScaleY(View view, float value) {
        view.setScaleY(value);
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static void setPivotX(View view, float value) {
        view.setPivotX(value);
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static void setPivotY(View view, float value) {
        view.setPivotY(value);
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static void setElevation(View view, float elevation) {
        Api21Impl.setElevation(view, elevation);
    }

    public static float getElevation(View view) {
        return Api21Impl.getElevation(view);
    }

    public static void setTranslationZ(View view, float translationZ) {
        Api21Impl.setTranslationZ(view, translationZ);
    }

    public static float getTranslationZ(View view) {
        return Api21Impl.getTranslationZ(view);
    }

    public static void setTransitionName(View view, String transitionName) {
        Api21Impl.setTransitionName(view, transitionName);
    }

    public static String getTransitionName(View view) {
        return Api21Impl.getTransitionName(view);
    }

    @Deprecated
    public static int getWindowSystemUiVisibility(View view) {
        return Api16Impl.getWindowSystemUiVisibility(view);
    }

    public static void requestApplyInsets(View view) {
        Api20Impl.requestApplyInsets(view);
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean enabled) {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "Unable to find childrenDrawingOrderEnabled", e);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke(viewGroup, Boolean.valueOf(enabled));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e4);
        }
    }

    public static boolean getFitsSystemWindows(View v) {
        return Api16Impl.getFitsSystemWindows(v);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean fitSystemWindows) {
        view.setFitsSystemWindows(fitSystemWindows);
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View v) {
        v.jumpDrawablesToCurrentState();
    }

    public static void setOnApplyWindowInsetsListener(View v, OnApplyWindowInsetsListener listener) {
        Api21Impl.setOnApplyWindowInsetsListener(v, listener);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
        WindowInsets unwrapped = insets.toWindowInsets();
        if (unwrapped != null) {
            WindowInsets result = Api20Impl.onApplyWindowInsets(view, unwrapped);
            if (!result.equals(unwrapped)) {
                return WindowInsetsCompat.toWindowInsetsCompat(result, view);
            }
        }
        return insets;
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat insets) {
        WindowInsets unwrapped = insets.toWindowInsets();
        if (unwrapped != null) {
            WindowInsets result = Api20Impl.dispatchApplyWindowInsets(view, unwrapped);
            if (!result.equals(unwrapped)) {
                return WindowInsetsCompat.toWindowInsetsCompat(result, view);
            }
        }
        return insets;
    }

    public static void setSystemGestureExclusionRects(View view, List<Rect> rects) {
        Api29Impl.setSystemGestureExclusionRects(view, rects);
    }

    public static List<Rect> getSystemGestureExclusionRects(View view) {
        return Api29Impl.getSystemGestureExclusionRects(view);
    }

    public static WindowInsetsCompat getRootWindowInsets(View view) {
        return Api23Impl.getRootWindowInsets(view);
    }

    public static WindowInsetsCompat computeSystemWindowInsets(View view, WindowInsetsCompat insets, Rect outLocalInsets) {
        return Api21Impl.computeSystemWindowInsets(view, insets, outLocalInsets);
    }

    @Deprecated
    public static WindowInsetsControllerCompat getWindowInsetsController(View view) {
        return Api30Impl.getWindowInsetsController(view);
    }

    public static void setWindowInsetsAnimationCallback(View view, WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.setCallback(view, callback);
    }

    public static void setOnReceiveContentListener(View view, String[] mimeTypes, OnReceiveContentListener listener) {
        Api31Impl.setOnReceiveContentListener(view, mimeTypes, listener);
    }

    public static String[] getOnReceiveContentMimeTypes(View view) {
        return Api31Impl.getReceiveContentMimeTypes(view);
    }

    public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat payload) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "performReceiveContent: " + payload + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        return Api31Impl.performReceiveContent(view, payload);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static OnReceiveContentViewBehavior getFallback(View view) {
        if (view instanceof OnReceiveContentViewBehavior) {
            return (OnReceiveContentViewBehavior) view;
        }
        return NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ContentInfoCompat lambda$static$0(ContentInfoCompat payload) {
        return payload;
    }

    /* loaded from: classes.dex */
    private static final class Api31Impl {
        private Api31Impl() {
        }

        public static void setOnReceiveContentListener(View view, String[] mimeTypes, OnReceiveContentListener listener) {
            if (listener == null) {
                view.setOnReceiveContentListener(mimeTypes, null);
            } else {
                view.setOnReceiveContentListener(mimeTypes, new OnReceiveContentListenerAdapter(listener));
            }
        }

        public static String[] getReceiveContentMimeTypes(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static ContentInfoCompat performReceiveContent(View view, ContentInfoCompat payload) {
            ContentInfo platPayload = payload.toContentInfo();
            ContentInfo platResult = view.performReceiveContent(platPayload);
            if (platResult == null) {
                return null;
            }
            if (platResult == platPayload) {
                return payload;
            }
            return ContentInfoCompat.toContentInfoCompat(platResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class OnReceiveContentListenerAdapter implements android.view.OnReceiveContentListener {
        private final OnReceiveContentListener mJetpackListener;

        OnReceiveContentListenerAdapter(OnReceiveContentListener jetpackListener) {
            this.mJetpackListener = jetpackListener;
        }

        @Override // android.view.OnReceiveContentListener
        public ContentInfo onReceiveContent(View view, ContentInfo platPayload) {
            ContentInfoCompat payload = ContentInfoCompat.toContentInfoCompat(platPayload);
            ContentInfoCompat result = this.mJetpackListener.onReceiveContent(view, payload);
            if (result == null) {
                return null;
            }
            if (result == payload) {
                return platPayload;
            }
            return result.toContentInfo();
        }
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View v, boolean enabled) {
        v.setSaveFromParentEnabled(enabled);
    }

    @Deprecated
    public static void setActivated(View view, boolean activated) {
        view.setActivated(activated);
    }

    public static boolean hasOverlappingRendering(View view) {
        return Api16Impl.hasOverlappingRendering(view);
    }

    public static boolean isPaddingRelative(View view) {
        return Api17Impl.isPaddingRelative(view);
    }

    public static void setBackground(View view, Drawable background) {
        Api16Impl.setBackground(view, background);
    }

    public static ColorStateList getBackgroundTintList(View view) {
        return Api21Impl.getBackgroundTintList(view);
    }

    public static void setBackgroundTintList(View view, ColorStateList tintList) {
        Api21Impl.setBackgroundTintList(view, tintList);
    }

    public static PorterDuff.Mode getBackgroundTintMode(View view) {
        return Api21Impl.getBackgroundTintMode(view);
    }

    public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        Api21Impl.setBackgroundTintMode(view, mode);
    }

    public static void setNestedScrollingEnabled(View view, boolean enabled) {
        Api21Impl.setNestedScrollingEnabled(view, enabled);
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return Api21Impl.isNestedScrollingEnabled(view);
    }

    public static boolean startNestedScroll(View view, int axes) {
        return Api21Impl.startNestedScroll(view, axes);
    }

    public static void stopNestedScroll(View view) {
        Api21Impl.stopNestedScroll(view);
    }

    public static boolean hasNestedScrollingParent(View view) {
        return Api21Impl.hasNestedScrollingParent(view);
    }

    public static boolean dispatchNestedScroll(View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return Api21Impl.dispatchNestedScroll(view, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public static boolean dispatchNestedPreScroll(View view, int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return Api21Impl.dispatchNestedPreScroll(view, dx, dy, consumed, offsetInWindow);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean startNestedScroll(View view, int axes, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(axes, type);
        }
        if (type == 0) {
            return startNestedScroll(view, axes);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void stopNestedScroll(View view, int type) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(type);
        } else if (type == 0) {
            stopNestedScroll(view);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasNestedScrollingParent(View view, int type) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(type);
            return false;
        }
        if (type == 0) {
            return hasNestedScrollingParent(view);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void dispatchNestedScroll(View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type, int[] consumed) {
        if (view instanceof NestedScrollingChild3) {
            ((NestedScrollingChild3) view).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type, consumed);
        } else {
            dispatchNestedScroll(view, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedScroll(View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedScroll(view, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedPreScroll(View view, int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedPreScroll(view, dx, dy, consumed, offsetInWindow);
        }
        return false;
    }

    public static boolean dispatchNestedFling(View view, float velocityX, float velocityY, boolean consumed) {
        return Api21Impl.dispatchNestedFling(view, velocityX, velocityY, consumed);
    }

    public static boolean dispatchNestedPreFling(View view, float velocityX, float velocityY) {
        return Api21Impl.dispatchNestedPreFling(view, velocityX, velocityY);
    }

    public static boolean isInLayout(View view) {
        return Api18Impl.isInLayout(view);
    }

    public static boolean isLaidOut(View view) {
        return Api19Impl.isLaidOut(view);
    }

    public static boolean isLayoutDirectionResolved(View view) {
        return Api19Impl.isLayoutDirectionResolved(view);
    }

    public static float getZ(View view) {
        return Api21Impl.getZ(view);
    }

    public static void setZ(View view, float z) {
        Api21Impl.setZ(view, z);
    }

    public static void offsetTopAndBottom(View view, int offset) {
        view.offsetTopAndBottom(offset);
    }

    private static void compatOffsetTopAndBottom(View view, int offset) {
        view.offsetTopAndBottom(offset);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    public static void offsetLeftAndRight(View view, int offset) {
        view.offsetLeftAndRight(offset);
    }

    private static void compatOffsetLeftAndRight(View view, int offset) {
        view.offsetLeftAndRight(offset);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        float y = view.getTranslationY();
        view.setTranslationY(1.0f + y);
        view.setTranslationY(y);
    }

    public static void setClipBounds(View view, Rect clipBounds) {
        Api18Impl.setClipBounds(view, clipBounds);
    }

    public static Rect getClipBounds(View view) {
        return Api18Impl.getClipBounds(view);
    }

    public static boolean isAttachedToWindow(View view) {
        return Api19Impl.isAttachedToWindow(view);
    }

    public static boolean hasOnClickListeners(View view) {
        return Api15Impl.hasOnClickListeners(view);
    }

    public static void setScrollIndicators(View view, int indicators) {
        Api23Impl.setScrollIndicators(view, indicators);
    }

    public static void setScrollIndicators(View view, int indicators, int mask) {
        Api23Impl.setScrollIndicators(view, indicators, mask);
    }

    public static int getScrollIndicators(View view) {
        return Api23Impl.getScrollIndicators(view);
    }

    public static void setPointerIcon(View view, PointerIconCompat pointerIcon) {
        Api24Impl.setPointerIcon(view, (PointerIcon) (pointerIcon != null ? pointerIcon.getPointerIcon() : null));
    }

    public static Display getDisplay(View view) {
        return Api17Impl.getDisplay(view);
    }

    public static void setTooltipText(View view, CharSequence tooltipText) {
        Api26Impl.setTooltipText(view, tooltipText);
    }

    public static boolean startDragAndDrop(View v, ClipData data, View.DragShadowBuilder shadowBuilder, Object myLocalState, int flags) {
        return Api24Impl.startDragAndDrop(v, data, shadowBuilder, myLocalState, flags);
    }

    public static void cancelDragAndDrop(View v) {
        Api24Impl.cancelDragAndDrop(v);
    }

    public static void updateDragShadow(View v, View.DragShadowBuilder shadowBuilder) {
        Api24Impl.updateDragShadow(v, shadowBuilder);
    }

    public static int getNextClusterForwardId(View view) {
        return Api26Impl.getNextClusterForwardId(view);
    }

    public static void setNextClusterForwardId(View view, int nextClusterForwardId) {
        Api26Impl.setNextClusterForwardId(view, nextClusterForwardId);
    }

    public static boolean isKeyboardNavigationCluster(View view) {
        return Api26Impl.isKeyboardNavigationCluster(view);
    }

    public static void setKeyboardNavigationCluster(View view, boolean isCluster) {
        Api26Impl.setKeyboardNavigationCluster(view, isCluster);
    }

    public static boolean isFocusedByDefault(View view) {
        return Api26Impl.isFocusedByDefault(view);
    }

    public static void setFocusedByDefault(View view, boolean isFocusedByDefault) {
        Api26Impl.setFocusedByDefault(view, isFocusedByDefault);
    }

    public static View keyboardNavigationClusterSearch(View view, View currentCluster, int direction) {
        return Api26Impl.keyboardNavigationClusterSearch(view, currentCluster, direction);
    }

    public static void addKeyboardNavigationClusters(View view, Collection<View> views, int direction) {
        Api26Impl.addKeyboardNavigationClusters(view, views, direction);
    }

    public static boolean restoreDefaultFocus(View view) {
        return Api26Impl.restoreDefaultFocus(view);
    }

    public static boolean hasExplicitFocusable(View view) {
        return Api26Impl.hasExplicitFocusable(view);
    }

    public static int generateViewId() {
        return Api17Impl.generateViewId();
    }

    public static void addOnUnhandledKeyEventListener(View v, OnUnhandledKeyEventListenerCompat listener) {
        Api28Impl.addOnUnhandledKeyEventListener(v, listener);
    }

    public static void removeOnUnhandledKeyEventListener(View v, OnUnhandledKeyEventListenerCompat listener) {
        Api28Impl.removeOnUnhandledKeyEventListener(v, listener);
    }

    @Deprecated
    protected ViewCompat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View root, KeyEvent evt) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean dispatchUnhandledKeyEventBeforeCallback(View root, KeyEvent evt) {
        return false;
    }

    public static void setScreenReaderFocusable(View view, boolean screenReaderFocusable) {
        screenReaderFocusableProperty().set(view, Boolean.valueOf(screenReaderFocusable));
    }

    public static boolean isScreenReaderFocusable(View view) {
        Boolean result = screenReaderFocusableProperty().get(view);
        return result != null && result.booleanValue();
    }

    private static AccessibilityViewProperty<Boolean> screenReaderFocusableProperty() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_screen_reader_focusable, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(view));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public void frameworkSet(View view, Boolean value) {
                Api28Impl.setScreenReaderFocusable(view, value.booleanValue());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(Boolean oldValue, Boolean newValue) {
                return !booleanNullToFalseEquals(oldValue, newValue);
            }
        };
    }

    public static void setAccessibilityPaneTitle(View view, CharSequence accessibilityPaneTitle) {
        paneTitleProperty().set(view, accessibilityPaneTitle);
        if (accessibilityPaneTitle != null) {
            sAccessibilityPaneVisibilityManager.addAccessibilityPane(view);
        } else {
            sAccessibilityPaneVisibilityManager.removeAccessibilityPane(view);
        }
    }

    public static CharSequence getAccessibilityPaneTitle(View view) {
        return paneTitleProperty().get(view);
    }

    private static AccessibilityViewProperty<CharSequence> paneTitleProperty() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28) { // from class: androidx.core.view.ViewCompat.2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public CharSequence frameworkGet(View view) {
                return Api28Impl.getAccessibilityPaneTitle(view);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public void frameworkSet(View view, CharSequence value) {
                Api28Impl.setAccessibilityPaneTitle(view, value);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(CharSequence oldValue, CharSequence newValue) {
                return !TextUtils.equals(oldValue, newValue);
            }
        };
    }

    private static AccessibilityViewProperty<CharSequence> stateDescriptionProperty() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_state_description, CharSequence.class, 64, 30) { // from class: androidx.core.view.ViewCompat.3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public CharSequence frameworkGet(View view) {
                return Api30Impl.getStateDescription(view);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public void frameworkSet(View view, CharSequence value) {
                Api30Impl.setStateDescription(view, value);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(CharSequence oldValue, CharSequence newValue) {
                return !TextUtils.equals(oldValue, newValue);
            }
        };
    }

    public static boolean isAccessibilityHeading(View view) {
        Boolean result = accessibilityHeadingProperty().get(view);
        return result != null && result.booleanValue();
    }

    public static void setAccessibilityHeading(View view, boolean isHeading) {
        accessibilityHeadingProperty().set(view, Boolean.valueOf(isHeading));
    }

    private static AccessibilityViewProperty<Boolean> accessibilityHeadingProperty() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_accessibility_heading, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(Api28Impl.isAccessibilityHeading(view));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public void frameworkSet(View view, Boolean value) {
                Api28Impl.setAccessibilityHeading(view, value.booleanValue());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(Boolean oldValue, Boolean newValue) {
                return !booleanNullToFalseEquals(oldValue, newValue);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class AccessibilityViewProperty<T> {
        private final int mContentChangeType;
        private final int mFrameworkMinimumSdk;
        private final int mTagKey;
        private final Class<T> mType;

        abstract T frameworkGet(View view);

        abstract void frameworkSet(View view, T t);

        AccessibilityViewProperty(int tagKey, Class<T> type, int frameworkMinimumSdk) {
            this(tagKey, type, 0, frameworkMinimumSdk);
        }

        AccessibilityViewProperty(int tagKey, Class<T> type, int contentChangeType, int frameworkMinimumSdk) {
            this.mTagKey = tagKey;
            this.mType = type;
            this.mContentChangeType = contentChangeType;
            this.mFrameworkMinimumSdk = frameworkMinimumSdk;
        }

        void set(View view, T value) {
            if (frameworkAvailable()) {
                frameworkSet(view, value);
            } else if (extrasAvailable() && shouldUpdate(get(view), value)) {
                ViewCompat.ensureAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, value);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        T get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            if (extrasAvailable()) {
                T t = (T) view.getTag(this.mTagKey);
                if (this.mType.isInstance(t)) {
                    return t;
                }
                return null;
            }
            return null;
        }

        private boolean frameworkAvailable() {
            return Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk;
        }

        private boolean extrasAvailable() {
            return true;
        }

        boolean shouldUpdate(T oldValue, T newValue) {
            return !newValue.equals(oldValue);
        }

        boolean booleanNullToFalseEquals(Boolean a, Boolean b) {
            boolean aBool = a != null && a.booleanValue();
            boolean bBool = b != null && b.booleanValue();
            return aBool == bBool;
        }
    }

    static void notifyViewAccessibilityStateChangedIfNeeded(View view, int changeType) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (!accessibilityManager.isEnabled()) {
            return;
        }
        boolean isVisibleAccessibilityPane = getAccessibilityPaneTitle(view) != null && view.isShown() && view.getWindowVisibility() == 0;
        if (getAccessibilityLiveRegion(view) != 0 || isVisibleAccessibilityPane) {
            AccessibilityEvent event = AccessibilityEvent.obtain();
            event.setEventType(isVisibleAccessibilityPane ? 32 : 2048);
            Api19Impl.setContentChangeTypes(event, changeType);
            if (isVisibleAccessibilityPane) {
                event.getText().add(getAccessibilityPaneTitle(view));
                setViewImportanceForAccessibilityIfNeeded(view);
            }
            view.sendAccessibilityEventUnchecked(event);
            return;
        }
        if (changeType == 32) {
            AccessibilityEvent event2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(event2);
            event2.setEventType(32);
            Api19Impl.setContentChangeTypes(event2, changeType);
            event2.setSource(view);
            view.onPopulateAccessibilityEvent(event2);
            event2.getText().add(getAccessibilityPaneTitle(view));
            accessibilityManager.sendAccessibilityEvent(event2);
            return;
        }
        if (view.getParent() != null) {
            ViewParent parent = view.getParent();
            try {
                Api19Impl.notifySubtreeAccessibilityStateChanged(parent, view, view, changeType);
            } catch (AbstractMethodError e) {
                Log.e(TAG, view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e);
            }
        }
    }

    private static void setViewImportanceForAccessibilityIfNeeded(View view) {
        if (getImportantForAccessibility(view) == 0) {
            setImportantForAccessibility(view, 1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (getImportantForAccessibility((View) parent) == 4) {
                setImportantForAccessibility(view, 2);
                return;
            }
        }
    }

    /* loaded from: classes.dex */
    static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        private final WeakHashMap<View, Boolean> mPanesToVisible = new WeakHashMap<>();

        AccessibilityPaneVisibilityManager() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        void addAccessibilityPane(View pane) {
            this.mPanesToVisible.put(pane, Boolean.valueOf(pane.isShown() && pane.getWindowVisibility() == 0));
            pane.addOnAttachStateChangeListener(this);
            if (Api19Impl.isAttachedToWindow(pane)) {
                registerForLayoutCallback(pane);
            }
        }

        void removeAccessibilityPane(View pane) {
            this.mPanesToVisible.remove(pane);
            pane.removeOnAttachStateChangeListener(this);
            unregisterForLayoutCallback(pane);
        }

        private void checkPaneVisibility(View pane, boolean oldVisibility) {
            int contentChangeType;
            boolean newVisibility = pane.isShown() && pane.getWindowVisibility() == 0;
            if (oldVisibility != newVisibility) {
                if (newVisibility) {
                    contentChangeType = 16;
                } else {
                    contentChangeType = 32;
                }
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(pane, contentChangeType);
                this.mPanesToVisible.put(pane, Boolean.valueOf(newVisibility));
            }
        }

        private void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void unregisterForLayoutCallback(View view) {
            ViewTreeObserver observer = view.getViewTreeObserver();
            Api16Impl.removeOnGlobalLayoutListener(observer, this);
        }
    }

    /* loaded from: classes.dex */
    static class UnhandledKeyEventManager {
        private static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList<>();
        private WeakHashMap<View, Boolean> mViewsContainingListeners = null;
        private SparseArray<WeakReference<View>> mCapturedKeys = null;
        private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;

        UnhandledKeyEventManager() {
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray<>();
            }
            return this.mCapturedKeys;
        }

        static UnhandledKeyEventManager at(View root) {
            UnhandledKeyEventManager manager = (UnhandledKeyEventManager) root.getTag(R.id.tag_unhandled_key_event_manager);
            if (manager == null) {
                UnhandledKeyEventManager manager2 = new UnhandledKeyEventManager();
                root.setTag(R.id.tag_unhandled_key_event_manager, manager2);
                return manager2;
            }
            return manager;
        }

        boolean dispatch(View root, KeyEvent event) {
            if (event.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View consumer = dispatchInOrder(root, event);
            if (event.getAction() == 0) {
                int keycode = event.getKeyCode();
                if (consumer != null && !KeyEvent.isModifierKey(keycode)) {
                    getCapturedKeys().put(keycode, new WeakReference<>(consumer));
                }
            }
            return consumer != null;
        }

        private View dispatchInOrder(View view, KeyEvent event) {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap == null || !weakHashMap.containsKey(view)) {
                return null;
            }
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                for (int i = vg.getChildCount() - 1; i >= 0; i--) {
                    View v = vg.getChildAt(i);
                    View consumer = dispatchInOrder(v, event);
                    if (consumer != null) {
                        return consumer;
                    }
                }
            }
            if (onUnhandledKeyEvent(view, event)) {
                return view;
            }
            return null;
        }

        boolean preDispatch(KeyEvent event) {
            int idx;
            WeakReference<KeyEvent> weakReference = this.mLastDispatchedPreViewKeyEvent;
            if (weakReference != null && weakReference.get() == event) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference<>(event);
            WeakReference<View> currentReceiver = null;
            SparseArray<WeakReference<View>> capturedKeys = getCapturedKeys();
            if (event.getAction() == 1 && (idx = capturedKeys.indexOfKey(event.getKeyCode())) >= 0) {
                currentReceiver = capturedKeys.valueAt(idx);
                capturedKeys.removeAt(idx);
            }
            if (currentReceiver == null) {
                currentReceiver = capturedKeys.get(event.getKeyCode());
            }
            if (currentReceiver == null) {
                return false;
            }
            View target = currentReceiver.get();
            if (target != null && ViewCompat.isAttachedToWindow(target)) {
                onUnhandledKeyEvent(target, event);
            }
            return true;
        }

        private boolean onUnhandledKeyEvent(View v, KeyEvent event) {
            ArrayList<OnUnhandledKeyEventListenerCompat> viewListeners = (ArrayList) v.getTag(R.id.tag_unhandled_key_listeners);
            if (viewListeners != null) {
                for (int i = viewListeners.size() - 1; i >= 0; i--) {
                    if (viewListeners.get(i).onUnhandledKeyEvent(v, event)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        static void registerListeningView(View v) {
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            synchronized (arrayList) {
                Iterator<WeakReference<View>> it = arrayList.iterator();
                while (it.hasNext()) {
                    WeakReference<View> wv = it.next();
                    if (wv.get() == v) {
                        return;
                    }
                }
                sViewsWithListeners.add(new WeakReference<>(v));
            }
        }

        static void unregisterListeningView(View v) {
            synchronized (sViewsWithListeners) {
                int i = 0;
                while (true) {
                    ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
                    if (i < arrayList.size()) {
                        if (arrayList.get(i).get() != v) {
                            i++;
                        } else {
                            arrayList.remove(i);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.mViewsContainingListeners == null) {
                    this.mViewsContainingListeners = new WeakHashMap<>();
                }
                for (int i = arrayList.size() - 1; i >= 0; i--) {
                    ArrayList<WeakReference<View>> arrayList2 = sViewsWithListeners;
                    WeakReference<View> vw = arrayList2.get(i);
                    View v = vw.get();
                    if (v == null) {
                        arrayList2.remove(i);
                    } else {
                        this.mViewsContainingListeners.put(v, Boolean.TRUE);
                        for (ViewParent nxt = v.getParent(); nxt instanceof View; nxt = nxt.getParent()) {
                            this.mViewsContainingListeners.put((View) nxt, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static WindowInsetsCompat getRootWindowInsets(View v) {
            return WindowInsetsCompat.Api21ReflectionHolder.getRootWindowInsets(v);
        }

        static WindowInsetsCompat computeSystemWindowInsets(View v, WindowInsetsCompat insets, Rect outLocalInsets) {
            WindowInsets platformInsets = insets.toWindowInsets();
            if (platformInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(v.computeSystemWindowInsets(platformInsets, outLocalInsets), v);
            }
            outLocalInsets.setEmpty();
            return insets;
        }

        static void setOnApplyWindowInsetsListener(final View v, final OnApplyWindowInsetsListener listener) {
            if (listener == null) {
                View.OnApplyWindowInsetsListener compatInsetsAnimationCallback = (View.OnApplyWindowInsetsListener) v.getTag(R.id.tag_window_insets_animation_callback);
                v.setOnApplyWindowInsetsListener(compatInsetsAnimationCallback);
            } else {
                v.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.core.view.ViewCompat.Api21Impl.1
                    WindowInsetsCompat mLastInsets = null;

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        WindowInsetsCompat compatInsets = WindowInsetsCompat.toWindowInsetsCompat(insets, view);
                        this.mLastInsets = compatInsets;
                        return listener.onApplyWindowInsets(view, compatInsets).toWindowInsets();
                    }
                });
            }
        }

        static void callCompatInsetAnimationCallback(WindowInsets insets, View v) {
            View.OnApplyWindowInsetsListener insetsAnimationCallback = (View.OnApplyWindowInsetsListener) v.getTag(R.id.tag_window_insets_animation_callback);
            if (insetsAnimationCallback != null) {
                insetsAnimationCallback.onApplyWindowInsets(v, insets);
            }
        }

        static boolean dispatchNestedFling(View view, float velocityX, float velocityY, boolean consumed) {
            return view.dispatchNestedFling(velocityX, velocityY, consumed);
        }

        static boolean dispatchNestedPreFling(View view, float velocityX, float velocityY) {
            return view.dispatchNestedPreFling(velocityX, velocityY);
        }

        static float getZ(View view) {
            return view.getZ();
        }

        static void setZ(View view, float z) {
            view.setZ(z);
        }

        static void setElevation(View view, float elevation) {
            view.setElevation(elevation);
        }

        static void setTranslationZ(View view, float translationZ) {
            view.setTranslationZ(translationZ);
        }

        static float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        static void setTransitionName(View view, String transitionName) {
            view.setTransitionName(transitionName);
        }

        static boolean isImportantForAccessibility(View view) {
            return view.isImportantForAccessibility();
        }

        static float getElevation(View view) {
            return view.getElevation();
        }

        static String getTransitionName(View view) {
            return view.getTransitionName();
        }

        static void setBackgroundTintList(View view, ColorStateList tint) {
            view.setBackgroundTintList(tint);
        }

        static ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        static PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        static void setBackgroundTintMode(View view, PorterDuff.Mode tintMode) {
            view.setBackgroundTintMode(tintMode);
        }

        static void setNestedScrollingEnabled(View view, boolean enabled) {
            view.setNestedScrollingEnabled(enabled);
        }

        static boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        static boolean startNestedScroll(View view, int axes) {
            return view.startNestedScroll(axes);
        }

        static void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }

        static boolean hasNestedScrollingParent(View view) {
            return view.hasNestedScrollingParent();
        }

        static boolean dispatchNestedScroll(View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
            return view.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }

        static boolean dispatchNestedPreScroll(View view, int dx, int dy, int[] consumed, int[] offsetInWindow) {
            return view.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        }
    }

    /* loaded from: classes.dex */
    private static class Api23Impl {
        private Api23Impl() {
        }

        public static WindowInsetsCompat getRootWindowInsets(View v) {
            WindowInsets wi = v.getRootWindowInsets();
            if (wi == null) {
                return null;
            }
            WindowInsetsCompat insets = WindowInsetsCompat.toWindowInsetsCompat(wi);
            insets.setRootWindowInsets(insets);
            insets.copyRootViewBounds(v.getRootView());
            return insets;
        }

        static void setScrollIndicators(View view, int indicators) {
            view.setScrollIndicators(indicators);
        }

        static void setScrollIndicators(View view, int indicators, int mask) {
            view.setScrollIndicators(indicators, mask);
        }

        static int getScrollIndicators(View view) {
            return view.getScrollIndicators();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api29Impl {
        private Api29Impl() {
        }

        static void saveAttributeDataForStyleable(View view, Context context, int[] styleable, AttributeSet attrs, TypedArray t, int defStyleAttr, int defStyleRes) {
            view.saveAttributeDataForStyleable(context, styleable, attrs, t, defStyleAttr, defStyleRes);
        }

        static View.AccessibilityDelegate getAccessibilityDelegate(View view) {
            return view.getAccessibilityDelegate();
        }

        static void setSystemGestureExclusionRects(View view, List<Rect> rects) {
            view.setSystemGestureExclusionRects(rects);
        }

        static List<Rect> getSystemGestureExclusionRects(View view) {
            return view.getSystemGestureExclusionRects();
        }

        static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }

        static void setContentCaptureSession(View view, ContentCaptureSession contentCaptureSession) {
            view.setContentCaptureSession(contentCaptureSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static WindowInsetsControllerCompat getWindowInsetsController(View view) {
            WindowInsetsController windowInsetsController = view.getWindowInsetsController();
            if (windowInsetsController != null) {
                return WindowInsetsControllerCompat.toWindowInsetsControllerCompat(windowInsetsController);
            }
            return null;
        }

        static void setStateDescription(View view, CharSequence stateDescription) {
            view.setStateDescription(stateDescription);
        }

        static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        static void setImportantForContentCapture(View view, int mode) {
            view.setImportantForContentCapture(mode);
        }

        static boolean isImportantForContentCapture(View view) {
            return view.isImportantForContentCapture();
        }

        static int getImportantForContentCapture(View view) {
            return view.getImportantForContentCapture();
        }
    }

    /* loaded from: classes.dex */
    static class Api26Impl {
        private Api26Impl() {
        }

        static void setAutofillHints(View view, String... autofillHints) {
            view.setAutofillHints(autofillHints);
        }

        static void setTooltipText(View view, CharSequence tooltipText) {
            view.setTooltipText(tooltipText);
        }

        static int getNextClusterForwardId(View view) {
            return view.getNextClusterForwardId();
        }

        static void setNextClusterForwardId(View view, int nextClusterForwardId) {
            view.setNextClusterForwardId(nextClusterForwardId);
        }

        static boolean isKeyboardNavigationCluster(View view) {
            return view.isKeyboardNavigationCluster();
        }

        static void setKeyboardNavigationCluster(View view, boolean isCluster) {
            view.setKeyboardNavigationCluster(isCluster);
        }

        static boolean isFocusedByDefault(View view) {
            return view.isFocusedByDefault();
        }

        static void setFocusedByDefault(View view, boolean isFocusedByDefault) {
            view.setFocusedByDefault(isFocusedByDefault);
        }

        static View keyboardNavigationClusterSearch(View view, View currentCluster, int direction) {
            return view.keyboardNavigationClusterSearch(currentCluster, direction);
        }

        static void addKeyboardNavigationClusters(View view, Collection<View> views, int direction) {
            view.addKeyboardNavigationClusters(views, direction);
        }

        static boolean restoreDefaultFocus(View view) {
            return view.restoreDefaultFocus();
        }

        static boolean hasExplicitFocusable(View view) {
            return view.hasExplicitFocusable();
        }

        static int getImportantForAutofill(View view) {
            return view.getImportantForAutofill();
        }

        static void setImportantForAutofill(View view, int mode) {
            view.setImportantForAutofill(mode);
        }

        static boolean isImportantForAutofill(View view) {
            return view.isImportantForAutofill();
        }

        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }
    }

    /* loaded from: classes.dex */
    static class Api18Impl {
        private Api18Impl() {
        }

        static boolean isInLayout(View view) {
            return view.isInLayout();
        }

        static void setClipBounds(View view, Rect clipBounds) {
            view.setClipBounds(clipBounds);
        }

        static Rect getClipBounds(View view) {
            return view.getClipBounds();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api19Impl {
        private Api19Impl() {
        }

        static boolean isLaidOut(View view) {
            return view.isLaidOut();
        }

        static boolean isAttachedToWindow(View view) {
            return view.isAttachedToWindow();
        }

        static boolean isLayoutDirectionResolved(View view) {
            return view.isLayoutDirectionResolved();
        }

        static int getAccessibilityLiveRegion(View view) {
            return view.getAccessibilityLiveRegion();
        }

        static void setAccessibilityLiveRegion(View view, int mode) {
            view.setAccessibilityLiveRegion(mode);
        }

        static void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int changeTypes) {
            accessibilityEvent.setContentChangeTypes(changeTypes);
        }

        static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View child, View source, int changeType) {
            viewParent.notifySubtreeAccessibilityStateChanged(child, source, changeType);
        }
    }

    /* loaded from: classes.dex */
    static class Api15Impl {
        private Api15Impl() {
        }

        static boolean hasOnClickListeners(View view) {
            return view.hasOnClickListeners();
        }
    }

    /* loaded from: classes.dex */
    static class Api24Impl {
        private Api24Impl() {
        }

        static void setPointerIcon(View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }

        static boolean startDragAndDrop(View view, ClipData data, View.DragShadowBuilder shadowBuilder, Object myLocalState, int flags) {
            return view.startDragAndDrop(data, shadowBuilder, myLocalState, flags);
        }

        static void cancelDragAndDrop(View view) {
            view.cancelDragAndDrop();
        }

        static void updateDragShadow(View view, View.DragShadowBuilder shadowBuilder) {
            view.updateDragShadow(shadowBuilder);
        }

        static void dispatchStartTemporaryDetach(View view) {
            view.dispatchStartTemporaryDetach();
        }

        static void dispatchFinishTemporaryDetach(View view) {
            view.dispatchFinishTemporaryDetach();
        }
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static Display getDisplay(View view) {
            return view.getDisplay();
        }

        static int generateViewId() {
            return View.generateViewId();
        }

        static int getLabelFor(View view) {
            return view.getLabelFor();
        }

        static void setLabelFor(View view, int id) {
            view.setLabelFor(id);
        }

        static void setLayerPaint(View view, Paint paint) {
            view.setLayerPaint(paint);
        }

        static int getLayoutDirection(View view) {
            return view.getLayoutDirection();
        }

        static void setLayoutDirection(View view, int layoutDirection) {
            view.setLayoutDirection(layoutDirection);
        }

        static int getPaddingStart(View view) {
            return view.getPaddingStart();
        }

        static int getPaddingEnd(View view) {
            return view.getPaddingEnd();
        }

        static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
            view.setPaddingRelative(start, top, end, bottom);
        }

        static boolean isPaddingRelative(View view) {
            return view.isPaddingRelative();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api16Impl {
        private Api16Impl() {
        }

        static boolean hasTransientState(View view) {
            return view.hasTransientState();
        }

        static void setHasTransientState(View view, boolean hasTransientState) {
            view.setHasTransientState(hasTransientState);
        }

        static void postInvalidateOnAnimation(View view) {
            view.postInvalidateOnAnimation();
        }

        static void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
            view.postInvalidateOnAnimation(left, top, right, bottom);
        }

        static void postOnAnimation(View view, Runnable action) {
            view.postOnAnimation(action);
        }

        static void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
            view.postOnAnimationDelayed(action, delayMillis);
        }

        static int getImportantForAccessibility(View view) {
            return view.getImportantForAccessibility();
        }

        static void setImportantForAccessibility(View view, int mode) {
            view.setImportantForAccessibility(mode);
        }

        static AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            return view.getAccessibilityNodeProvider();
        }

        static ViewParent getParentForAccessibility(View view) {
            return view.getParentForAccessibility();
        }

        static int getMinimumWidth(View view) {
            return view.getMinimumWidth();
        }

        static int getMinimumHeight(View view) {
            return view.getMinimumHeight();
        }

        static int getWindowSystemUiVisibility(View view) {
            return view.getWindowSystemUiVisibility();
        }

        static void requestFitSystemWindows(View view) {
            view.requestFitSystemWindows();
        }

        static boolean getFitsSystemWindows(View view) {
            return view.getFitsSystemWindows();
        }

        static boolean performAccessibilityAction(View view, int action, Bundle arguments) {
            return view.performAccessibilityAction(action, arguments);
        }

        static boolean hasOverlappingRendering(View view) {
            return view.hasOverlappingRendering();
        }

        static void setBackground(View view, Drawable background) {
            view.setBackground(background);
        }

        static void removeOnGlobalLayoutListener(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener victim) {
            viewTreeObserver.removeOnGlobalLayoutListener(victim);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api28Impl {
        private Api28Impl() {
        }

        static <T> T requireViewById(View view, int i) {
            return (T) view.requireViewById(i);
        }

        static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        static void setAccessibilityPaneTitle(View view, CharSequence accessibilityPaneTitle) {
            view.setAccessibilityPaneTitle(accessibilityPaneTitle);
        }

        static void setAccessibilityHeading(View view, boolean isHeading) {
            view.setAccessibilityHeading(isHeading);
        }

        static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        static void setScreenReaderFocusable(View view, boolean screenReaderFocusable) {
            view.setScreenReaderFocusable(screenReaderFocusable);
        }

        static void addOnUnhandledKeyEventListener(View v, final OnUnhandledKeyEventListenerCompat listener) {
            SimpleArrayMap<OnUnhandledKeyEventListenerCompat, View.OnUnhandledKeyEventListener> viewListeners = (SimpleArrayMap) v.getTag(R.id.tag_unhandled_key_listeners);
            if (viewListeners == null) {
                viewListeners = new SimpleArrayMap<>();
                v.setTag(R.id.tag_unhandled_key_listeners, viewListeners);
            }
            Objects.requireNonNull(listener);
            View.OnUnhandledKeyEventListener fwListener = new View.OnUnhandledKeyEventListener() { // from class: androidx.core.view.ViewCompat$Api28Impl$$ExternalSyntheticLambda0
                @Override // android.view.View.OnUnhandledKeyEventListener
                public final boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
                    return ViewCompat.OnUnhandledKeyEventListenerCompat.this.onUnhandledKeyEvent(view, keyEvent);
                }
            };
            viewListeners.put(listener, fwListener);
            v.addOnUnhandledKeyEventListener(fwListener);
        }

        static void removeOnUnhandledKeyEventListener(View v, OnUnhandledKeyEventListenerCompat listener) {
            View.OnUnhandledKeyEventListener fwListener;
            SimpleArrayMap<OnUnhandledKeyEventListenerCompat, View.OnUnhandledKeyEventListener> viewListeners = (SimpleArrayMap) v.getTag(R.id.tag_unhandled_key_listeners);
            if (viewListeners != null && (fwListener = viewListeners.get(listener)) != null) {
                v.removeOnUnhandledKeyEventListener(fwListener);
            }
        }

        public static void setAutofillId(View view, AutofillId id) {
            view.setAutofillId(id);
        }
    }

    /* loaded from: classes.dex */
    static class Api20Impl {
        private Api20Impl() {
        }

        static void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }

        static WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
            return view.onApplyWindowInsets(insets);
        }

        static WindowInsets dispatchApplyWindowInsets(View view, WindowInsets insets) {
            return view.dispatchApplyWindowInsets(insets);
        }
    }
}
