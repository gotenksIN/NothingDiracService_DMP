package com.google.android.material.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes4.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    private boolean centerIfNoTextEnabled;
    private CharSequence errorAccessibilityLabel;
    private boolean errorShown;
    private ColorStateList materialThemeColorsTintList;
    private final LinkedHashSet<OnErrorChangedListener> onErrorChangedListeners;
    private boolean useMaterialThemeColors;
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_CompoundButton_CheckBox;
    private static final int[] ERROR_STATE_SET = {R.attr.state_error};
    private static final int[][] CHECKBOX_STATES = {new int[]{android.R.attr.state_enabled, R.attr.state_error}, new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* loaded from: classes4.dex */
    public interface OnErrorChangedListener {
        void onErrorChanged(MaterialCheckBox materialCheckBox, boolean z);
    }

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    public MaterialCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialCheckBox(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.checkbox.MaterialCheckBox.DEF_STYLE_RES
            android.content.Context r0 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r8, r9, r10, r4)
            r7.<init>(r0, r9, r10)
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            r7.onErrorChangedListeners = r0
            android.content.Context r8 = r7.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialCheckBox
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r8
            r1 = r9
            r3 = r10
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_buttonTint
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L31
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_buttonTint
            android.content.res.ColorStateList r1 = com.google.android.material.resources.MaterialResources.getColorStateList(r8, r0, r1)
            androidx.core.widget.CompoundButtonCompat.setButtonTintList(r7, r1)
        L31:
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_useMaterialThemeColors
            boolean r1 = r0.getBoolean(r1, r6)
            r7.useMaterialThemeColors = r1
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_centerIfNoTextEnabled
            r2 = 1
            boolean r1 = r0.getBoolean(r1, r2)
            r7.centerIfNoTextEnabled = r1
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_errorShown
            boolean r1 = r0.getBoolean(r1, r6)
            r7.errorShown = r1
            int r1 = com.google.android.material.R.styleable.MaterialCheckBox_errorAccessibilityLabel
            java.lang.CharSequence r1 = r0.getText(r1)
            r7.errorAccessibilityLabel = r1
            r0.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.checkbox.MaterialCheckBox.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable;
        if (this.centerIfNoTextEnabled && TextUtils.isEmpty(getText()) && (drawable = CompoundButtonCompat.getButtonDrawable(this)) != null) {
            int direction = ViewUtils.isLayoutRtl(this) ? -1 : 1;
            int dx = ((getWidth() - drawable.getIntrinsicWidth()) / 2) * direction;
            int saveCount = canvas.save();
            canvas.translate(dx, 0.0f);
            super.onDraw(canvas);
            canvas.restoreToCount(saveCount);
            if (getBackground() != null) {
                Rect bounds = drawable.getBounds();
                DrawableCompat.setHotspotBounds(getBackground(), bounds.left + dx, bounds.top, bounds.right + dx, bounds.bottom);
                return;
            }
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && CompoundButtonCompat.getButtonTintList(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableStates = super.onCreateDrawableState(extraSpace + 1);
        if (isErrorShown()) {
            mergeDrawableStates(drawableStates, ERROR_STATE_SET);
        }
        return drawableStates;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        if (info != null && isErrorShown()) {
            info.setText(((Object) info.getText()) + ", " + ((Object) this.errorAccessibilityLabel));
        }
    }

    public void setErrorShown(boolean errorShown) {
        if (this.errorShown == errorShown) {
            return;
        }
        this.errorShown = errorShown;
        refreshDrawableState();
        Iterator<OnErrorChangedListener> it = this.onErrorChangedListeners.iterator();
        while (it.hasNext()) {
            OnErrorChangedListener listener = it.next();
            listener.onErrorChanged(this, this.errorShown);
        }
    }

    public boolean isErrorShown() {
        return this.errorShown;
    }

    public void setErrorAccessibilityLabelResource(int resId) {
        setErrorAccessibilityLabel(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setErrorAccessibilityLabel(CharSequence errorAccessibilityLabel) {
        this.errorAccessibilityLabel = errorAccessibilityLabel;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.errorAccessibilityLabel;
    }

    public void addOnErrorChangedListener(OnErrorChangedListener listener) {
        this.onErrorChangedListeners.add(listener);
    }

    public void removeOnErrorChangedListener(OnErrorChangedListener listener) {
        this.onErrorChangedListeners.remove(listener);
    }

    public void clearOnErrorChangedListeners() {
        this.onErrorChangedListeners.clear();
    }

    public void setUseMaterialThemeColors(boolean useMaterialThemeColors) {
        this.useMaterialThemeColors = useMaterialThemeColors;
        if (useMaterialThemeColors) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, null);
        }
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    public void setCenterIfNoTextEnabled(boolean centerIfNoTextEnabled) {
        this.centerIfNoTextEnabled = centerIfNoTextEnabled;
    }

    public boolean isCenterIfNoTextEnabled() {
        return this.centerIfNoTextEnabled;
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.materialThemeColorsTintList == null) {
            int[][] iArr = CHECKBOX_STATES;
            int[] checkBoxColorsList = new int[iArr.length];
            int colorControlActivated = MaterialColors.getColor(this, R.attr.colorControlActivated);
            int colorError = MaterialColors.getColor(this, R.attr.colorError);
            int colorSurface = MaterialColors.getColor(this, R.attr.colorSurface);
            int colorOnSurface = MaterialColors.getColor(this, R.attr.colorOnSurface);
            checkBoxColorsList[0] = MaterialColors.layer(colorSurface, colorError, 1.0f);
            checkBoxColorsList[1] = MaterialColors.layer(colorSurface, colorControlActivated, 1.0f);
            checkBoxColorsList[2] = MaterialColors.layer(colorSurface, colorOnSurface, 0.54f);
            checkBoxColorsList[3] = MaterialColors.layer(colorSurface, colorOnSurface, 0.38f);
            checkBoxColorsList[4] = MaterialColors.layer(colorSurface, colorOnSurface, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(iArr, checkBoxColorsList);
        }
        return this.materialThemeColorsTintList;
    }
}
