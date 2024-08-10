package com.dirac.settings.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class DiracResult<T> implements Parcelable {
    public static final Parcelable.Creator<DiracResult> CREATOR = new Parcelable.Creator<DiracResult>() { // from class: com.dirac.settings.api.DiracResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiracResult createFromParcel(Parcel in) {
            return new DiracResult(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiracResult[] newArray(int size) {
            return new DiracResult[size];
        }
    };
    private Class classType;
    private T data;

    public DiracResult(T data) {
        this.classType = data.getClass();
        this.data = data;
    }

    protected DiracResult(Parcel in) {
        readFromParcel(in);
    }

    public T getData() {
        return this.data;
    }

    public Class getClassType() {
        return this.classType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.classType);
        parcel.writeValue(this.data);
    }

    public void readFromParcel(Parcel parcel) {
        Class cls = (Class) parcel.readSerializable();
        this.classType = cls;
        this.data = (T) parcel.readValue(cls.getClassLoader());
    }
}
