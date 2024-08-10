package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.R;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class MaterialSwitch extends SwitchCompat {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_CompoundButton_MaterialSwitch;
    private static final int[] STATE_SET_WITH_ICON = {R.attr.state_with_icon};
    private int[] currentStateChecked;
    private int[] currentStateUnchecked;
    private Drawable thumbDrawable;
    private Drawable thumbIconDrawable;
    private ColorStateList thumbIconTintList;
    private PorterDuff.Mode thumbIconTintMode;
    private ColorStateList thumbTintList;
    private Drawable trackDecorationDrawable;
    private ColorStateList trackDecorationTintList;
    private PorterDuff.Mode trackDecorationTintMode;
    private Drawable trackDrawable;
    private ColorStateList trackTintList;

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public MaterialSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.materialSwitchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialSwitch(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.materialswitch.MaterialSwitch.DEF_STYLE_RES
            android.content.Context r0 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r8, r9, r10, r4)
            r7.<init>(r0, r9, r10)
            android.content.Context r8 = r7.getContext()
            android.graphics.drawable.Drawable r0 = super.getThumbDrawable()
            r7.thumbDrawable = r0
            android.content.res.ColorStateList r0 = super.getThumbTintList()
            r7.thumbTintList = r0
            r0 = 0
            super.setThumbTintList(r0)
            android.graphics.drawable.Drawable r1 = super.getTrackDrawable()
            r7.trackDrawable = r1
            android.content.res.ColorStateList r1 = super.getTrackTintList()
            r7.trackTintList = r1
            super.setTrackTintList(r0)
            int[] r2 = com.google.android.material.R.styleable.MaterialSwitch
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r8
            r1 = r9
            r3 = r10
            androidx.appcompat.widget.TintTypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_thumbIcon
            android.graphics.drawable.Drawable r1 = r0.getDrawable(r1)
            r7.thumbIconDrawable = r1
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_thumbIconTint
            android.content.res.ColorStateList r1 = r0.getColorStateList(r1)
            r7.thumbIconTintList = r1
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_thumbIconTintMode
            r2 = -1
            int r1 = r0.getInt(r1, r2)
            android.graphics.PorterDuff$Mode r3 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r1 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r1, r3)
            r7.thumbIconTintMode = r1
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_trackDecoration
            android.graphics.drawable.Drawable r1 = r0.getDrawable(r1)
            r7.trackDecorationDrawable = r1
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_trackDecorationTint
            android.content.res.ColorStateList r1 = r0.getColorStateList(r1)
            r7.trackDecorationTintList = r1
            int r1 = com.google.android.material.R.styleable.MaterialSwitch_trackDecorationTintMode
            int r1 = r0.getInt(r1, r2)
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r1 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r1, r2)
            r7.trackDecorationTintMode = r1
            r0.recycle()
            r7.setEnforceSwitchWidth(r6)
            r7.refreshThumbDrawable()
            r7.refreshTrackDrawable()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.materialswitch.MaterialSwitch.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.view.View
    public void invalidate() {
        updateDrawableTints();
        super.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.thumbIconDrawable != null) {
            mergeDrawableStates(drawableState, STATE_SET_WITH_ICON);
        }
        this.currentStateUnchecked = getUncheckedState(drawableState);
        this.currentStateChecked = getCheckedState(drawableState);
        return drawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable) {
        this.thumbDrawable = drawable;
        refreshThumbDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.thumbDrawable;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList tintList) {
        this.thumbTintList = tintList;
        refreshThumbDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.thumbTintList;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode tintMode) {
        super.setThumbTintMode(tintMode);
        refreshThumbDrawable();
    }

    public void setThumbIconResource(int resId) {
        setThumbIconDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public void setThumbIconDrawable(Drawable icon) {
        this.thumbIconDrawable = icon;
        refreshThumbDrawable();
    }

    public Drawable getThumbIconDrawable() {
        return this.thumbIconDrawable;
    }

    public void setThumbIconTintList(ColorStateList tintList) {
        this.thumbIconTintList = tintList;
        refreshThumbDrawable();
    }

    public ColorStateList getThumbIconTintList() {
        return this.thumbIconTintList;
    }

    public void setThumbIconTintMode(PorterDuff.Mode tintMode) {
        this.thumbIconTintMode = tintMode;
        refreshThumbDrawable();
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.thumbIconTintMode;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable track) {
        this.trackDrawable = track;
        refreshTrackDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.trackDrawable;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList tintList) {
        this.trackTintList = tintList;
        refreshTrackDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.trackTintList;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode tintMode) {
        super.setTrackTintMode(tintMode);
        refreshTrackDrawable();
    }

    public void setTrackDecorationResource(int resId) {
        setTrackDecorationDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public void setTrackDecorationDrawable(Drawable trackDecoration) {
        this.trackDecorationDrawable = trackDecoration;
        refreshTrackDrawable();
    }

    public Drawable getTrackDecorationDrawable() {
        return this.trackDecorationDrawable;
    }

    public void setTrackDecorationTintList(ColorStateList tintList) {
        this.trackDecorationTintList = tintList;
        refreshTrackDrawable();
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.trackDecorationTintList;
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode tintMode) {
        this.trackDecorationTintMode = tintMode;
        refreshTrackDrawable();
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.trackDecorationTintMode;
    }

    private void refreshThumbDrawable() {
        this.thumbDrawable = createTintableDrawableIfNeeded(this.thumbDrawable, this.thumbTintList, getThumbTintMode());
        this.thumbIconDrawable = createTintableDrawableIfNeeded(this.thumbIconDrawable, this.thumbIconTintList, this.thumbIconTintMode);
        updateDrawableTints();
        super.setThumbDrawable(compositeThumbAndIconDrawable(this.thumbDrawable, this.thumbIconDrawable));
        refreshDrawableState();
    }

    private static Drawable compositeThumbAndIconDrawable(Drawable thumbDrawable, Drawable thumbIconDrawable) {
        int iconNewHeight;
        int iconNewWidth;
        if (thumbDrawable == null) {
            return thumbIconDrawable;
        }
        if (thumbIconDrawable == null) {
            return thumbDrawable;
        }
        LayerDrawable drawable = new LayerDrawable(new Drawable[]{thumbDrawable, thumbIconDrawable});
        if (thumbIconDrawable.getIntrinsicWidth() <= thumbDrawable.getIntrinsicWidth() && thumbIconDrawable.getIntrinsicHeight() <= thumbDrawable.getIntrinsicHeight()) {
            iconNewWidth = thumbIconDrawable.getIntrinsicWidth();
            iconNewHeight = thumbIconDrawable.getIntrinsicHeight();
        } else {
            float thumbIconRatio = thumbIconDrawable.getIntrinsicWidth() / thumbIconDrawable.getIntrinsicHeight();
            float thumbRatio = thumbDrawable.getIntrinsicWidth() / thumbDrawable.getIntrinsicHeight();
            if (thumbIconRatio >= thumbRatio) {
                int iconNewWidth2 = thumbDrawable.getIntrinsicWidth();
                int i = (int) (iconNewWidth2 / thumbIconRatio);
                iconNewWidth = iconNewWidth2;
                iconNewHeight = i;
            } else {
                int iconNewHeight2 = thumbDrawable.getIntrinsicHeight();
                iconNewHeight = iconNewHeight2;
                iconNewWidth = (int) (iconNewHeight2 * thumbIconRatio);
            }
        }
        drawable.setLayerSize(1, iconNewWidth, iconNewHeight);
        drawable.setLayerGravity(1, 17);
        return drawable;
    }

    private void refreshTrackDrawable() {
        Drawable finalTrackDrawable;
        this.trackDrawable = createTintableDrawableIfNeeded(this.trackDrawable, this.trackTintList, getTrackTintMode());
        this.trackDecorationDrawable = createTintableDrawableIfNeeded(this.trackDecorationDrawable, this.trackDecorationTintList, this.trackDecorationTintMode);
        updateDrawableTints();
        Drawable finalTrackDrawable2 = this.trackDrawable;
        if (finalTrackDrawable2 != null && this.trackDecorationDrawable != null) {
            finalTrackDrawable = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        } else if (finalTrackDrawable2 != null) {
            finalTrackDrawable = this.trackDrawable;
        } else {
            finalTrackDrawable = this.trackDecorationDrawable;
        }
        if (finalTrackDrawable != null) {
            setSwitchMinWidth(finalTrackDrawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(finalTrackDrawable);
    }

    private void updateDrawableTints() {
        if (this.thumbTintList == null && this.thumbIconTintList == null && this.trackTintList == null && this.trackDecorationTintList == null) {
            return;
        }
        float thumbPosition = getThumbPosition();
        ColorStateList colorStateList = this.thumbTintList;
        if (colorStateList != null) {
            setInterpolatedDrawableTintIfPossible(this.thumbDrawable, colorStateList, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList2 = this.thumbIconTintList;
        if (colorStateList2 != null) {
            setInterpolatedDrawableTintIfPossible(this.thumbIconDrawable, colorStateList2, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList3 = this.trackTintList;
        if (colorStateList3 != null) {
            setInterpolatedDrawableTintIfPossible(this.trackDrawable, colorStateList3, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
        ColorStateList colorStateList4 = this.trackDecorationTintList;
        if (colorStateList4 != null) {
            setInterpolatedDrawableTintIfPossible(this.trackDecorationDrawable, colorStateList4, this.currentStateUnchecked, this.currentStateChecked, thumbPosition);
        }
    }

    private static int[] getUncheckedState(int[] state) {
        int[] newState = new int[state.length];
        int i = 0;
        for (int subState : state) {
            if (subState != 16842912) {
                newState[i] = subState;
                i++;
            }
        }
        return newState;
    }

    private static int[] getCheckedState(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 16842912) {
                return state;
            }
            if (state[i] == 0) {
                int[] newState = (int[]) state.clone();
                newState[i] = 16842912;
                return newState;
            }
        }
        int i2 = state.length;
        int[] newState2 = Arrays.copyOf(state, i2 + 1);
        newState2[state.length] = 16842912;
        return newState2;
    }

    private static void setInterpolatedDrawableTintIfPossible(Drawable drawable, ColorStateList tint, int[] stateUnchecked, int[] stateChecked, float thumbPosition) {
        if (drawable == null || tint == null) {
            return;
        }
        DrawableCompat.setTint(drawable, ColorUtils.blendARGB(tint.getColorForState(stateUnchecked, 0), tint.getColorForState(stateChecked, 0), thumbPosition));
    }

    private static Drawable createTintableDrawableIfNeeded(Drawable drawable, ColorStateList tintList, PorterDuff.Mode tintMode) {
        if (drawable == null) {
            return null;
        }
        if (tintList != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (tintMode != null) {
                DrawableCompat.setTintMode(drawable, tintMode);
            }
        }
        return drawable;
    }
}
