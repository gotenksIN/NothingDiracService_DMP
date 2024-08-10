package com.dirac.settings.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ConfigurationList implements Parcelable {
    public static final Parcelable.Creator<ConfigurationList> CREATOR = new Parcelable.Creator<ConfigurationList>() { // from class: com.dirac.settings.api.ConfigurationList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConfigurationList createFromParcel(Parcel in) {
            return new ConfigurationList(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConfigurationList[] newArray(int size) {
            return new ConfigurationList[size];
        }
    };
    private final List<Configuration> mConfigurations;

    public ConfigurationList(List<Configuration> configurations) {
        this.mConfigurations = configurations;
    }

    protected ConfigurationList(Parcel in) {
        ArrayList arrayList = new ArrayList();
        this.mConfigurations = arrayList;
        in.readTypedList(arrayList, Configuration.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Configuration> getConfigurations() {
        return this.mConfigurations;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mConfigurations);
    }
}
