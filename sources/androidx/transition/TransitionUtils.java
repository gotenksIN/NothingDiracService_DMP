package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;

/* loaded from: classes3.dex */
class TransitionUtils {
    private static final boolean HAS_IS_ATTACHED_TO_WINDOW = true;
    private static final boolean HAS_OVERLAY = true;
    private static final boolean HAS_PICTURE_BITMAP = true;
    private static final int MAX_IMAGE_SIZE = 1048576;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View copyViewImage(ViewGroup sceneRoot, View view, View parent) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-parent.getScrollX(), -parent.getScrollY());
        ViewUtils.transformMatrixToGlobal(view, matrix);
        ViewUtils.transformMatrixToLocal(sceneRoot, matrix);
        RectF bounds = new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        matrix.mapRect(bounds);
        int left = Math.round(bounds.left);
        int top = Math.round(bounds.top);
        int right = Math.round(bounds.right);
        int bottom = Math.round(bounds.bottom);
        ImageView copy = new ImageView(view.getContext());
        copy.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bitmap = createViewBitmap(view, matrix, bounds, sceneRoot);
        if (bitmap != null) {
            copy.setImageBitmap(bitmap);
        }
        int widthSpec = View.MeasureSpec.makeMeasureSpec(right - left, BasicMeasure.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(bottom - top, BasicMeasure.EXACTLY);
        copy.measure(widthSpec, heightSpec);
        copy.layout(left, top, right, bottom);
        return copy;
    }

    private static Bitmap createViewBitmap(View view, Matrix matrix, RectF bounds, ViewGroup sceneRoot) {
        boolean addToOverlay;
        boolean sceneRootIsAttached;
        if (HAS_IS_ATTACHED_TO_WINDOW) {
            sceneRootIsAttached = true;
            addToOverlay = !Api19Impl.isAttachedToWindow(view);
            if (sceneRoot == null || !Api19Impl.isAttachedToWindow(sceneRoot)) {
                sceneRootIsAttached = false;
            }
        } else {
            addToOverlay = false;
            sceneRootIsAttached = false;
        }
        ViewGroup parent = null;
        int indexInParent = 0;
        boolean z = HAS_OVERLAY;
        if (z && addToOverlay) {
            if (!sceneRootIsAttached) {
                return null;
            }
            parent = (ViewGroup) view.getParent();
            indexInParent = parent.indexOfChild(view);
            Api18Impl.getOverlayAndAdd(sceneRoot, view);
        }
        Bitmap bitmap = null;
        int bitmapWidth = Math.round(bounds.width());
        int bitmapHeight = Math.round(bounds.height());
        if (bitmapWidth > 0 && bitmapHeight > 0) {
            float scale = Math.min(1.0f, 1048576.0f / (bitmapWidth * bitmapHeight));
            int bitmapWidth2 = Math.round(bitmapWidth * scale);
            int bitmapHeight2 = Math.round(bitmapHeight * scale);
            matrix.postTranslate(-bounds.left, -bounds.top);
            matrix.postScale(scale, scale);
            if (HAS_PICTURE_BITMAP) {
                Picture picture = new Picture();
                Canvas canvas = picture.beginRecording(bitmapWidth2, bitmapHeight2);
                canvas.concat(matrix);
                view.draw(canvas);
                picture.endRecording();
                bitmap = Api28Impl.createBitmap(picture);
            } else {
                bitmap = Bitmap.createBitmap(bitmapWidth2, bitmapHeight2, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmap);
                canvas2.concat(matrix);
                view.draw(canvas2);
            }
        }
        if (z && addToOverlay) {
            Api18Impl.getOverlayAndRemove(sceneRoot, view);
            parent.addView(view, indexInParent);
        }
        return bitmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Animator mergeAnimators(Animator animator1, Animator animator2) {
        if (animator1 == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator1;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        return animatorSet;
    }

    /* loaded from: classes3.dex */
    static class MatrixEvaluator implements TypeEvaluator<Matrix> {
        final float[] mTempStartValues = new float[9];
        final float[] mTempEndValues = new float[9];
        final Matrix mTempMatrix = new Matrix();

        @Override // android.animation.TypeEvaluator
        public Matrix evaluate(float fraction, Matrix startValue, Matrix endValue) {
            startValue.getValues(this.mTempStartValues);
            endValue.getValues(this.mTempEndValues);
            for (int i = 0; i < 9; i++) {
                float[] fArr = this.mTempEndValues;
                float f = fArr[i];
                float f2 = this.mTempStartValues[i];
                float diff = f - f2;
                fArr[i] = f2 + (fraction * diff);
            }
            this.mTempMatrix.setValues(this.mTempEndValues);
            return this.mTempMatrix;
        }
    }

    private TransitionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Api18Impl {
        private Api18Impl() {
        }

        static ViewGroupOverlay getOverlayAndAdd(ViewGroup viewGroup, View toAdd) {
            ViewGroupOverlay result = viewGroup.getOverlay();
            result.add(toAdd);
            return result;
        }

        static ViewGroupOverlay getOverlayAndRemove(ViewGroup viewGroup, View toRemove) {
            ViewGroupOverlay result = viewGroup.getOverlay();
            result.remove(toRemove);
            return result;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Api28Impl {
        private Api28Impl() {
        }

        static Bitmap createBitmap(Picture source) {
            return Bitmap.createBitmap(source);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Api19Impl {
        private Api19Impl() {
        }

        static boolean isAttachedToWindow(View view) {
            return view.isAttachedToWindow();
        }
    }
}
