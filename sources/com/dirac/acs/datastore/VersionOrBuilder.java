package com.dirac.acs.datastore;

import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface VersionOrBuilder extends MessageOrBuilder {
    int getMajorVersion();

    int getMinorVersion();

    int getPatchVersion();
}
