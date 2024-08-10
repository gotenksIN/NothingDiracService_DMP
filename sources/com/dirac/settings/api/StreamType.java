package com.dirac.settings.api;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum StreamType {
    GENERAL,
    SYSTEM_SOUND;

    public static final Parcelable.Creator<StreamType> CREATOR = new Parcelable.Creator<StreamType>() { // from class: com.dirac.settings.api.StreamType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamType createFromParcel(Parcel in) {
            try {
                return StreamType.values()[in.readInt()];
            } catch (Exception e) {
                throw new BadParcelableException(e);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamType[] newArray(int size) {
            return new StreamType[size];
        }
    };

    public void writeToParcel(Parcel out, int parcelableWriteReturnValue) {
        StreamType[] a = values();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == this) {
                out.writeInt(i);
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void readFromParcel(Parcel reply) {
        try {
            reply.readInt();
        } catch (Exception e) {
            throw new BadParcelableException(e);
        }
    }
}
