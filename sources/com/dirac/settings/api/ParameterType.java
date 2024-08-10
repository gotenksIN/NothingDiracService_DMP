package com.dirac.settings.api;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum ParameterType {
    ACS_PARAMETER_TYPE_LINEAR,
    ACS_PARAMETER_TYPE_TOGGLE,
    ACS_PARAMETER_TYPE_LOGARITHMIC,
    ACS_PARAMETER_TYPE_ENUMERATION;

    public static final Parcelable.Creator<ParameterType> CREATOR = new Parcelable.Creator<ParameterType>() { // from class: com.dirac.settings.api.ParameterType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterType createFromParcel(Parcel in) {
            try {
                return ParameterType.values()[in.readInt()];
            } catch (Exception e) {
                throw new BadParcelableException(e);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterType[] newArray(int size) {
            return new ParameterType[size];
        }
    };

    public void writeToParcel(Parcel out) {
        ParameterType[] a = values();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == this) {
                out.writeInt(i);
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
