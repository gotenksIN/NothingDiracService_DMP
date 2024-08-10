package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface ValueOrBuilder extends MessageOrBuilder {
    ByteString getBinaryValue();

    boolean getBoolValue();

    float getFloatValue();

    int getInt32Value();

    long getInt64Value();

    String getStringValue();

    ByteString getStringValueBytes();

    Value.ValueCase getValueCase();
}
