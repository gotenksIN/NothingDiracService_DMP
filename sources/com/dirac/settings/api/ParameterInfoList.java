package com.dirac.settings.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ParameterInfoList implements Parcelable {
    public static final Parcelable.Creator<ParameterInfoList> CREATOR = new Parcelable.Creator<ParameterInfoList>() { // from class: com.dirac.settings.api.ParameterInfoList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterInfoList createFromParcel(Parcel in) {
            return new ParameterInfoList(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParameterInfoList[] newArray(int size) {
            return new ParameterInfoList[size];
        }
    };
    private final List<ParameterInfo> mParameterInfoList;

    public ParameterInfoList(List<ParameterInfo> configurations) {
        this.mParameterInfoList = configurations;
    }

    protected ParameterInfoList(Parcel in) {
        ArrayList arrayList = new ArrayList();
        this.mParameterInfoList = arrayList;
        in.readTypedList(arrayList, ParameterInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<ParameterInfo> getListOfParameterInfo() {
        return this.mParameterInfoList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mParameterInfoList);
    }
}
