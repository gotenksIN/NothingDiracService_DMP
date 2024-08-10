package com.dirac.acs.datastore;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface ParamValuesOrBuilder extends MessageOrBuilder {
    ParamValue getValues(int i);

    int getValuesCount();

    List<ParamValue> getValuesList();

    ParamValueOrBuilder getValuesOrBuilder(int i);

    List<? extends ParamValueOrBuilder> getValuesOrBuilderList();
}
