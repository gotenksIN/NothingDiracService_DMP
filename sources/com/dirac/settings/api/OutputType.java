package com.dirac.settings.api;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum OutputType {
    INTERNAL_SPEAKER,
    HEADSET;

    public static final Parcelable.Creator<OutputType> CREATOR = new Parcelable.Creator<OutputType>() { // from class: com.dirac.settings.api.OutputType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OutputType createFromParcel(Parcel in) {
            try {
                return OutputType.values()[in.readInt()];
            } catch (Exception e) {
                throw new BadParcelableException(e);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OutputType[] newArray(int size) {
            return new OutputType[size];
        }
    };

    public void writeToParcel(Parcel out, int unknown) {
        OutputType[] a = values();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == this) {
                out.writeInt(i);
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
