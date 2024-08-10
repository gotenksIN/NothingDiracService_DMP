package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
import kotlin.time.DurationKt;

/* loaded from: classes.dex */
public class BuildCompat {
    public static final int R_EXTENSION_INT = Extensions30Impl.R;
    public static final int S_EXTENSION_INT = Extensions30Impl.S;
    public static final int T_EXTENSION_INT = Extensions30Impl.TIRAMISU;
    public static final int AD_SERVICES_EXTENSION_INT = Extensions30Impl.AD_SERVICES;

    /* loaded from: classes.dex */
    public @interface PrereleaseSdkCheck {
    }

    private BuildCompat() {
    }

    protected static boolean isAtLeastPreReleaseCodename(String codename, String buildCodename) {
        if ("REL".equals(buildCodename)) {
            return false;
        }
        String buildCodenameUpper = buildCodename.toUpperCase(Locale.ROOT);
        String codenameUpper = codename.toUpperCase(Locale.ROOT);
        return buildCodenameUpper.compareTo(codenameUpper) >= 0;
    }

    @Deprecated
    public static boolean isAtLeastN() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastR() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastS() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastSv2() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastT() {
        return true;
    }

    public static boolean isAtLeastU() {
        return true;
    }

    public static boolean isAtLeastV() {
        return isAtLeastPreReleaseCodename("VanillaIceCream", Build.VERSION.CODENAME);
    }

    /* loaded from: classes.dex */
    private static final class Extensions30Impl {
        static final int R = SdkExtensions.getExtensionVersion(30);
        static final int S = SdkExtensions.getExtensionVersion(31);
        static final int TIRAMISU = SdkExtensions.getExtensionVersion(33);
        static final int AD_SERVICES = SdkExtensions.getExtensionVersion(DurationKt.NANOS_IN_MILLIS);

        private Extensions30Impl() {
        }
    }
}
