package com.dirac.acs.datastore;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface IdsOrBuilder extends MessageOrBuilder {
    Id getIds(int i);

    int getIdsCount();

    List<Id> getIdsList();

    IdOrBuilder getIdsOrBuilder(int i);

    List<? extends IdOrBuilder> getIdsOrBuilderList();
}
