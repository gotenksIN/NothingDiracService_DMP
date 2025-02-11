package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class KeyAttribute extends Keys {
    private int mFrame;
    private String mTarget;
    private String mTransitionEasing;
    protected String TYPE = TypedValues.AttributesType.NAME;
    private Fit mCurveFit = null;
    private Visibility mVisibility = null;
    private float mAlpha = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;

    /* loaded from: classes.dex */
    public enum Fit {
        SPLINE,
        LINEAR
    }

    /* loaded from: classes.dex */
    public enum Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    public KeyAttribute(int frame, String target) {
        this.mTarget = null;
        this.mFrame = 0;
        this.mTarget = target;
        this.mFrame = frame;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public void setTarget(String target) {
        this.mTarget = target;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public void setTransitionEasing(String transitionEasing) {
        this.mTransitionEasing = transitionEasing;
    }

    public Fit getCurveFit() {
        return this.mCurveFit;
    }

    public void setCurveFit(Fit curveFit) {
        this.mCurveFit = curveFit;
    }

    public Visibility getVisibility() {
        return this.mVisibility;
    }

    public void setVisibility(Visibility visibility) {
        this.mVisibility = visibility;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(float alpha) {
        this.mAlpha = alpha;
    }

    public float getRotation() {
        return this.mRotation;
    }

    public void setRotation(float rotation) {
        this.mRotation = rotation;
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public void setRotationX(float rotationX) {
        this.mRotationX = rotationX;
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public void setRotationY(float rotationY) {
        this.mRotationY = rotationY;
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public void setPivotX(float pivotX) {
        this.mPivotX = pivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public void setPivotY(float pivotY) {
        this.mPivotY = pivotY;
    }

    public float getTransitionPathRotate() {
        return this.mTransitionPathRotate;
    }

    public void setTransitionPathRotate(float transitionPathRotate) {
        this.mTransitionPathRotate = transitionPathRotate;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public void setScaleX(float scaleX) {
        this.mScaleX = scaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public void setScaleY(float scaleY) {
        this.mScaleY = scaleY;
    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public void setTranslationX(float translationX) {
        this.mTranslationX = translationX;
    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public void setTranslationY(float translationY) {
        this.mTranslationY = translationY;
    }

    public float getTranslationZ() {
        return this.mTranslationZ;
    }

    public void setTranslationZ(float translationZ) {
        this.mTranslationZ = translationZ;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(this.TYPE);
        ret.append(":{\n");
        attributesToString(ret);
        ret.append("},\n");
        return ret.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attributesToString(StringBuilder builder) {
        append(builder, TypedValues.AttributesType.S_TARGET, this.mTarget);
        builder.append("frame:").append(this.mFrame).append(",\n");
        append(builder, "easing", this.mTransitionEasing);
        if (this.mCurveFit != null) {
            builder.append("fit:'").append(this.mCurveFit).append("',\n");
        }
        if (this.mVisibility != null) {
            builder.append("visibility:'").append(this.mVisibility).append("',\n");
        }
        append(builder, "alpha", this.mAlpha);
        append(builder, "rotationX", this.mRotationX);
        append(builder, "rotationY", this.mRotationY);
        append(builder, "rotationZ", this.mRotation);
        append(builder, "pivotX", this.mPivotX);
        append(builder, "pivotY", this.mPivotY);
        append(builder, "pathRotate", this.mTransitionPathRotate);
        append(builder, "scaleX", this.mScaleX);
        append(builder, "scaleY", this.mScaleY);
        append(builder, "translationX", this.mTranslationX);
        append(builder, "translationY", this.mTranslationY);
        append(builder, "translationZ", this.mTranslationZ);
    }
}
