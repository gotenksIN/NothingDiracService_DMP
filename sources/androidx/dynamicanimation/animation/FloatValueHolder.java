package androidx.dynamicanimation.animation;

/* loaded from: classes2.dex */
public class FloatValueHolder {
    private float mValue = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float value) {
        setValue(value);
    }

    public void setValue(float value) {
        this.mValue = value;
    }

    public float getValue() {
        return this.mValue;
    }
}
