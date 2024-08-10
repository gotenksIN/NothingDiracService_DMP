package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface SignalChainOrBuilder extends MessageOrBuilder {
    boolean containsSignalChainMetaData(String str);

    String getName();

    ByteString getNameBytes();

    ParamInfo getParameters(int i);

    int getParametersCount();

    List<ParamInfo> getParametersList();

    ParamInfoOrBuilder getParametersOrBuilder(int i);

    List<? extends ParamInfoOrBuilder> getParametersOrBuilderList();

    Version getRtCoreVersion();

    VersionOrBuilder getRtCoreVersionOrBuilder();

    @Deprecated
    Map<String, Value> getSignalChainMetaData();

    int getSignalChainMetaDataCount();

    Map<String, Value> getSignalChainMetaDataMap();

    Value getSignalChainMetaDataOrDefault(String str, Value value);

    Value getSignalChainMetaDataOrThrow(String str);

    boolean hasRtCoreVersion();
}
