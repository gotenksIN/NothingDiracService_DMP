package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.core.os.BuildCompat;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class IntentCompat {
    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    private IntentCompat() {
    }

    public static Intent makeMainSelectorActivity(String selectorAction, String selectorCategory) {
        return Api15Impl.makeMainSelectorActivity(selectorAction, selectorCategory);
    }

    public static Intent createManageUnusedAppRestrictionsIntent(Context context, String packageName) {
        if (!PackageManagerCompat.areUnusedAppRestrictionsAvailable(context.getPackageManager())) {
            throw new UnsupportedOperationException("Unused App Restriction features are not available on this device");
        }
        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", packageName, null));
    }

    public static <T> T getParcelableExtra(Intent intent, String str, Class<T> cls) {
        if (BuildCompat.isAtLeastU()) {
            return (T) Api33Impl.getParcelableExtra(intent, str, cls);
        }
        T t = (T) intent.getParcelableExtra(str);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    public static Parcelable[] getParcelableArrayExtra(Intent in, String name, Class<? extends Parcelable> clazz) {
        if (BuildCompat.isAtLeastU()) {
            return (Parcelable[]) Api33Impl.getParcelableArrayExtra(in, name, clazz);
        }
        return in.getParcelableArrayExtra(name);
    }

    public static <T> ArrayList<T> getParcelableArrayListExtra(Intent in, String name, Class<? extends T> clazz) {
        if (BuildCompat.isAtLeastU()) {
            return Api33Impl.getParcelableArrayListExtra(in, name, clazz);
        }
        return in.getParcelableArrayListExtra(name);
    }

    /* loaded from: classes.dex */
    static class Api15Impl {
        private Api15Impl() {
        }

        static Intent makeMainSelectorActivity(String selectorAction, String selectorCategory) {
            return Intent.makeMainSelectorActivity(selectorAction, selectorCategory);
        }
    }

    /* loaded from: classes.dex */
    static class Api33Impl {
        private Api33Impl() {
        }

        static <T> T getParcelableExtra(Intent intent, String str, Class<T> cls) {
            return (T) intent.getParcelableExtra(str, cls);
        }

        static <T> T[] getParcelableArrayExtra(Intent intent, String str, Class<T> cls) {
            return (T[]) intent.getParcelableArrayExtra(str, cls);
        }

        static <T> ArrayList<T> getParcelableArrayListExtra(Intent in, String name, Class<? extends T> clazz) {
            return in.getParcelableArrayListExtra(name, clazz);
        }
    }
}
