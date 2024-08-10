package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes4.dex */
class CutoutDrawable extends MaterialShapeDrawable {
    private final RectF cutoutBounds;

    CutoutDrawable() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CutoutDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        super(shapeAppearanceModel != null ? shapeAppearanceModel : new ShapeAppearanceModel());
        this.cutoutBounds = new RectF();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCutout() {
        return !this.cutoutBounds.isEmpty();
    }

    void setCutout(float left, float top, float right, float bottom) {
        if (left != this.cutoutBounds.left || top != this.cutoutBounds.top || right != this.cutoutBounds.right || bottom != this.cutoutBounds.bottom) {
            this.cutoutBounds.set(left, top, right, bottom);
            invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCutout(RectF bounds) {
        setCutout(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.shape.MaterialShapeDrawable
    public void drawStrokeShape(Canvas canvas) {
        if (this.cutoutBounds.isEmpty()) {
            super.drawStrokeShape(canvas);
            return;
        }
        canvas.save();
        canvas.clipOutRect(this.cutoutBounds);
        super.drawStrokeShape(canvas);
        canvas.restore();
    }
}
