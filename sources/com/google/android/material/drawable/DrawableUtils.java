package com.google.android.material.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes4.dex */
public final class DrawableUtils {
    private DrawableUtils() {
    }

    public static void setTint(Drawable drawable, int color) {
        boolean hasTint = color != 0;
        if (hasTint) {
            DrawableCompat.setTint(drawable, color);
        } else {
            DrawableCompat.setTintList(drawable, null);
        }
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, ColorStateList tint, PorterDuff.Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        int color = tint.getColorForState(drawable.getState(), 0);
        return new PorterDuffColorFilter(color, tintMode);
    }

    public static AttributeSet parseDrawableXml(Context context, int id, CharSequence startTag) {
        int type;
        try {
            XmlPullParser parser = context.getResources().getXml(id);
            do {
                type = parser.next();
                if (type == 2) {
                    break;
                }
            } while (type != 1);
            if (type != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if (!TextUtils.equals(parser.getName(), startTag)) {
                throw new XmlPullParserException("Must have a <" + ((Object) startTag) + "> start tag");
            }
            AttributeSet attrs = Xml.asAttributeSet(parser);
            return attrs;
        } catch (IOException | XmlPullParserException e) {
            Resources.NotFoundException exception = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(id));
            exception.initCause(e);
            throw exception;
        }
    }

    public static void setRippleDrawableRadius(RippleDrawable drawable, int radius) {
        drawable.setRadius(radius);
    }
}
