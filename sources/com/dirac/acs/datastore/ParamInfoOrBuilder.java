package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface ParamInfoOrBuilder extends MessageOrBuilder {
    double getDefault();

    String getDescription();

    ByteString getDescriptionBytes();

    ParamEnumValue getEnumValues(int i);

    int getEnumValuesCount();

    List<ParamEnumValue> getEnumValuesList();

    ParamEnumValueOrBuilder getEnumValuesOrBuilder(int i);

    List<? extends ParamEnumValueOrBuilder> getEnumValuesOrBuilderList();

    String getFormatting();

    ByteString getFormattingBytes();

    Id getId();

    IdOrBuilder getIdOrBuilder();

    double getMax();

    double getMin();

    String getName();

    ByteString getNameBytes();

    double getStepSize();

    ParamType getType();

    int getTypeValue();

    String getUnit();

    ByteString getUnitBytes();

    boolean hasId();
}
