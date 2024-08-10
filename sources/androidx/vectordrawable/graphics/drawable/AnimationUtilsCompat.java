package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes4.dex */
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int id) throws Resources.NotFoundException {
        Interpolator interp = AnimationUtils.loadInterpolator(context, id);
        ObjectsCompat.requireNonNull(interp, "Failed to parse interpolator, no start tag found");
        return interp;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x003b, code lost:            if (r7.equals("accelerateDecelerateInterpolator") != false) goto L46;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.Interpolator createInterpolatorFromXml(android.content.Context r9, org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat.createInterpolatorFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser):android.view.animation.Interpolator");
    }

    private AnimationUtilsCompat() {
    }
}
