package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;

/* loaded from: classes4.dex */
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF bounds, int alpha) {
        return canvas.saveLayerAlpha(bounds, alpha);
    }

    public static int saveLayerAlpha(Canvas canvas, float left, float top, float right, float bottom, int alpha) {
        return canvas.saveLayerAlpha(left, top, right, bottom, alpha);
    }
}
