package com.dirac.android;

import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public final class Utils {

    /* loaded from: classes4.dex */
    public static final class Debug {
        private Debug() {
            throw new IllegalStateException("Utility class");
        }

        public static String getCurrentMethodName() {
            return Thread.currentThread().getStackTrace()[3].getMethodName();
        }
    }

    public static String join(String delimiter, float[] array) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < array.length - 1) {
            builder.append(array[i]);
            builder.append(delimiter);
            i++;
        }
        return builder.append(array[i]).toString();
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public static String join(String delimiter, int[] array) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < array.length - 1) {
            builder.append(array[i]);
            builder.append(delimiter);
            i++;
        }
        return builder.append(array[i]).toString();
    }

    public static int parseInt(byte[] bytes, int offset) {
        return Integer.parseInt(new String(bytes, offset, bytes.length - offset, StandardCharsets.UTF_8), 10);
    }

    private Utils() {
    }
}
