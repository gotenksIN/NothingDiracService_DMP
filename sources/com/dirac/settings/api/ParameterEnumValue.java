package com.dirac.settings.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class ParameterEnumValue implements Parcelable {
    public static final Parcelable.Creator<ParameterEnumValue> CREATOR = new Parcelable.Creator<ParameterEnumValue>() { // from class: com.dirac.settings.api.ParameterEnumValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterEnumValue createFromParcel(Parcel in) {
            return new ParameterEnumValue(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterEnumValue[] newArray(int size) {
            return new ParameterEnumValue[size];
        }
    };
    String name;
    double value;

    public ParameterEnumValue(String n, double v) {
        this.name = n;
        this.value = v;
    }

    protected ParameterEnumValue(Parcel in) {
        this.name = in.readString();
        this.value = in.readDouble();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.value);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
