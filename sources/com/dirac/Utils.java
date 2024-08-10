package com.dirac;

import android.util.Log;

/* loaded from: classes4.dex */
public final class Utils {
    public static boolean isMethodOverrriden(Object obj, String name, Class<?>... parameterTypes) {
        try {
            Class<?> clazz = obj.getClass();
            return clazz.getDeclaredMethod(name, parameterTypes).getDeclaringClass().equals(clazz);
        } catch (NoSuchMethodException e) {
            Log.e("DIRAC", e.getMessage());
            return false;
        }
    }

    public static int indexOf(byte[] array, int offset, byte target) {
        for (int i = offset; i < array.length - offset; i++) {
            if (target == array[i]) {
                return i - offset;
            }
        }
        return -1;
    }

    private Utils() {
    }
}
