package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo info) {
        return Api28Impl.getLongVersionCode(info);
    }

    public static List<Signature> getSignatures(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        Signature[] array;
        PackageInfo pkgInfo = packageManager.getPackageInfo(packageName, 134217728);
        SigningInfo signingInfo = pkgInfo.signingInfo;
        if (Api28Impl.hasMultipleSigners(signingInfo)) {
            array = Api28Impl.getApkContentsSigners(signingInfo);
        } else {
            array = Api28Impl.getSigningCertificateHistory(signingInfo);
        }
        if (array == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(array);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x013d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean hasSignatures(android.content.pm.PackageManager r10, java.lang.String r11, java.util.Map<byte[], java.lang.Integer> r12, boolean r13) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.PackageInfoCompat.hasSignatures(android.content.pm.PackageManager, java.lang.String, java.util.Map, boolean):boolean");
    }

    private static boolean byteArrayContains(byte[][] array, byte[] expected) {
        for (byte[] item : array) {
            if (Arrays.equals(expected, item)) {
                return true;
            }
        }
        return false;
    }

    private static byte[] computeSHA256Digest(byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA256").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Device doesn't support SHA256 cert checking", e);
        }
    }

    private PackageInfoCompat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api28Impl {
        private Api28Impl() {
        }

        static boolean hasSigningCertificate(PackageManager packageManager, String packageName, byte[] bytes, int type) {
            return packageManager.hasSigningCertificate(packageName, bytes, type);
        }

        static boolean hasMultipleSigners(SigningInfo signingInfo) {
            return signingInfo.hasMultipleSigners();
        }

        static Signature[] getApkContentsSigners(SigningInfo signingInfo) {
            return signingInfo.getApkContentsSigners();
        }

        static Signature[] getSigningCertificateHistory(SigningInfo signingInfo) {
            return signingInfo.getSigningCertificateHistory();
        }

        static long getLongVersionCode(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }
    }
}
