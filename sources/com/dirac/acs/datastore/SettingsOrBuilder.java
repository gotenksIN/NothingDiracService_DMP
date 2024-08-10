package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface SettingsOrBuilder extends MessageOrBuilder {
    Configuration getConfigurations(int i);

    int getConfigurationsCount();

    List<Configuration> getConfigurationsList();

    ConfigurationOrBuilder getConfigurationsOrBuilder(int i);

    List<? extends ConfigurationOrBuilder> getConfigurationsOrBuilderList();

    Timestamp getCreationTime();

    TimestampOrBuilder getCreationTimeOrBuilder();

    boolean getIsDiracEnabled();

    boolean getIsEnabled(int i);

    int getIsEnabledCount();

    List<Boolean> getIsEnabledList();

    boolean getIsGEF();

    int getOutputType(int i);

    int getOutputTypeCount();

    List<Integer> getOutputTypeList();

    String getUuid();

    ByteString getUuidBytes();

    boolean hasCreationTime();
}
