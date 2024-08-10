package com.dirac.settings.api.exceptions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CouldNotHandleEnableException.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/dirac/settings/api/exceptions/CouldNotHandleEnableException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "exceptionMessage", "", "(Ljava/lang/String;)V", "acs-api_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CouldNotHandleEnableException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CouldNotHandleEnableException(String exceptionMessage) {
        super("Could not handle enable: " + exceptionMessage);
        Intrinsics.checkNotNullParameter(exceptionMessage, "exceptionMessage");
    }
}
