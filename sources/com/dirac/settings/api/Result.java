package com.dirac.settings.api;

/* loaded from: classes4.dex */
public interface Result<T> {
    void onFailure(RuntimeException runtimeException);

    void onResult(T t);
}
