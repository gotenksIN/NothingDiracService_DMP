package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface ArchiveOrBuilder extends MessageOrBuilder {
    boolean containsArchiveMetaData(String str);

    @Deprecated
    Map<String, Value> getArchiveMetaData();

    int getArchiveMetaDataCount();

    Map<String, Value> getArchiveMetaDataMap();

    Value getArchiveMetaDataOrDefault(String str, Value value);

    Value getArchiveMetaDataOrThrow(String str);

    String getAuthor();

    ByteString getAuthorBytes();

    SignalChain getConfigurations(int i);

    int getConfigurationsCount();

    List<SignalChain> getConfigurationsList();

    SignalChainOrBuilder getConfigurationsOrBuilder(int i);

    List<? extends SignalChainOrBuilder> getConfigurationsOrBuilderList();

    Timestamp getCreationTime();

    TimestampOrBuilder getCreationTimeOrBuilder();

    String getCreator();

    ByteString getCreatorBytes();

    Version getCreatorVersion();

    VersionOrBuilder getCreatorVersionOrBuilder();

    String getDescription();

    ByteString getDescriptionBytes();

    Version getInterfaceVersion();

    VersionOrBuilder getInterfaceVersionOrBuilder();

    String getManufacturer();

    ByteString getManufacturerBytes();

    String getProject();

    ByteString getProjectBytes();

    String getUuid();

    ByteString getUuidBytes();

    boolean hasCreationTime();

    boolean hasCreatorVersion();

    boolean hasInterfaceVersion();
}
