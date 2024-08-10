package com.dirac.settings.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class ParameterInfo implements Parcelable {
    public static final Parcelable.Creator<ParameterInfo> CREATOR = new Parcelable.Creator<ParameterInfo>() { // from class: com.dirac.settings.api.ParameterInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterInfo createFromParcel(Parcel in) {
            return new ParameterInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterInfo[] newArray(int size) {
            return new ParameterInfo[size];
        }
    };
    private float defaultValue;
    private String description;
    private ParameterEnumValue[] enumValues;
    private String formatting;
    private boolean hdSoundAvailable;
    private int id;
    private float max;
    private float min;
    private String name;
    private float stepSize;
    private ParameterType type;
    private String unit;
    private float value;

    public ParameterInfo() {
    }

    protected ParameterInfo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.min = in.readFloat();
        this.max = in.readFloat();
        this.defaultValue = in.readFloat();
        this.stepSize = in.readFloat();
        this.hdSoundAvailable = in.readByte() != 0;
        this.value = in.readFloat();
        this.type = ParameterType.valueOf(in.readString());
        this.enumValues = (ParameterEnumValue[]) in.createTypedArray(ParameterEnumValue.CREATOR);
        this.unit = in.readString();
        this.formatting = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeFloat(this.min);
        parcel.writeFloat(this.max);
        parcel.writeFloat(this.defaultValue);
        parcel.writeFloat(this.stepSize);
        parcel.writeByte(this.hdSoundAvailable ? (byte) 1 : (byte) 0);
        parcel.writeFloat(this.value);
        parcel.writeString(this.type.name());
        parcel.writeTypedArray(this.enumValues, i);
        parcel.writeString(this.unit);
        parcel.writeString(this.formatting);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMin() {
        return this.min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return this.max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public void setStepSize(float stepSize) {
        this.stepSize = stepSize;
    }

    public boolean isHdSoundAvailable() {
        return this.hdSoundAvailable;
    }

    public void setHdSoundAvailable(boolean hdSoundAvailable) {
        this.hdSoundAvailable = hdSoundAvailable;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public ParameterType getType() {
        return this.type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParameterEnumValue[] getParameterEnumValues() {
        return this.enumValues;
    }

    public void setParameterEnumValues(ParameterEnumValue[] enumValues) {
        this.enumValues = enumValues;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFormatting() {
        return this.formatting;
    }

    public void setFormatting(String formatting) {
        this.formatting = formatting;
    }
}
