package com.dirac.acs.datastore;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface ParamNotifyOrBuilder extends MessageOrBuilder {
    String getChangeId();

    ByteString getChangeIdBytes();

    Ids getIds();

    IdsOrBuilder getIdsOrBuilder();

    boolean hasIds();
}
