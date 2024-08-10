package com.dirac.settings.api;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class Configuration implements Parcelable {
    public static final Parcelable.Creator<Configuration> CREATOR = new Parcelable.Creator<Configuration>() { // from class: com.dirac.settings.api.Configuration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Configuration createFromParcel(Parcel in) {
            return new Configuration(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Configuration[] newArray(int size) {
            return new Configuration[size];
        }
    };
    private boolean active;
    private String name;
    private OutputType outputType;
    private StreamType streamType;
    private ParcelUuid uuid;

    public Configuration() {
    }

    protected Configuration(Parcel in) {
        this.name = in.readString();
        this.uuid = (ParcelUuid) in.readParcelable(ParcelUuid.class.getClassLoader());
        this.outputType = OutputType.values()[in.readInt()];
        this.streamType = StreamType.values()[in.readInt()];
        this.active = in.readInt() == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeParcelable(this.uuid, 0);
        parcel.writeInt(this.outputType.ordinal());
        parcel.writeInt(this.streamType.ordinal());
        parcel.writeInt(this.active ? 1 : 0);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParcelUuid getUuid() {
        return this.uuid;
    }

    public void setUuid(ParcelUuid uuid) {
        this.uuid = uuid;
    }

    public OutputType getOutputType() {
        return this.outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public StreamType getStreamType() {
        return this.streamType;
    }

    public void setStreamType(StreamType streamType) {
        this.streamType = streamType;
    }
}
