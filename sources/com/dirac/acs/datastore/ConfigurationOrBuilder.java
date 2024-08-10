package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface ConfigurationOrBuilder extends MessageOrBuilder {
    boolean getActive();

    Timestamp getActiveTime();

    TimestampOrBuilder getActiveTimeOrBuilder();

    String getName();

    ByteString getNameBytes();

    String getOutputType();

    ByteString getOutputTypeBytes();

    String getStreamType();

    ByteString getStreamTypeBytes();

    ParamValue getValues(int i);

    int getValuesCount();

    List<ParamValue> getValuesList();

    ParamValueOrBuilder getValuesOrBuilder(int i);

    List<? extends ParamValueOrBuilder> getValuesOrBuilderList();

    boolean hasActiveTime();
}
