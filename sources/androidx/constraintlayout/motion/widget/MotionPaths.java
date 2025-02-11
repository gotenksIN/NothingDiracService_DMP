package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] sNames = {"position", "x", "y", "width", "height", "pathRotate"};
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    LinkedHashMap<String, ConstraintAttribute> mAttributes;
    int mDrawPath;
    float mHeight;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mPosition;
    float mProgress;
    float mRelativeAngle;
    MotionController mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float mTime;
    float mWidth;
    float mX;
    float mY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    void initCartesian(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float dxdx;
        float dydy;
        float position = c.mFramePosition / 100.0f;
        this.mTime = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float f = endTimePoint.mWidth;
        float f2 = startTimePoint.mWidth;
        float scaleX = f - f2;
        float f3 = endTimePoint.mHeight;
        float f4 = startTimePoint.mHeight;
        float scaleY = f3 - f4;
        this.mPosition = this.mTime;
        float startCenterX = startTimePoint.mX + (f2 / 2.0f);
        float startCenterY = startTimePoint.mY + (f4 / 2.0f);
        float endCenterX = endTimePoint.mX + (f / 2.0f);
        float endCenterY = endTimePoint.mY + (f3 / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.mX = (int) ((r15 + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) ((r3 + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.mWidth = (int) (f2 + (scaleX * scaleWidth));
        this.mHeight = (int) (f4 + (scaleY * scaleHeight));
        if (!Float.isNaN(c.mPercentX)) {
            dxdx = c.mPercentX;
        } else {
            dxdx = position;
        }
        float dydx = Float.isNaN(c.mAltPercentY) ? 0.0f : c.mAltPercentY;
        if (!Float.isNaN(c.mPercentY)) {
            dydy = c.mPercentY;
        } else {
            dydy = position;
        }
        float dxdy = Float.isNaN(c.mAltPercentX) ? 0.0f : c.mAltPercentX;
        this.mMode = 0;
        this.mX = (int) (((startTimePoint.mX + (pathVectorX * dxdx)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) (((startTimePoint.mY + (pathVectorX * dydx)) + (pathVectorY * dydy)) - ((scaleY * scaleHeight) / 2.0f));
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    void initAxis(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float dxdx;
        float dydy;
        float position = c.mFramePosition / 100.0f;
        this.mTime = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float f = endTimePoint.mWidth;
        float f2 = startTimePoint.mWidth;
        float scaleX = f - f2;
        float f3 = endTimePoint.mHeight;
        float f4 = startTimePoint.mHeight;
        float scaleY = f3 - f4;
        this.mPosition = this.mTime;
        float startCenterX = startTimePoint.mX + (f2 / 2.0f);
        float startCenterY = startTimePoint.mY + (f4 / 2.0f);
        float endCenterX = endTimePoint.mX + (f / 2.0f);
        float endCenterY = endTimePoint.mY + (f3 / 2.0f);
        if (startCenterX > endCenterX) {
            startCenterX = endCenterX;
            endCenterX = startCenterX;
        }
        if (startCenterY > endCenterY) {
            startCenterY = endCenterY;
            endCenterY = startCenterY;
        }
        float tmp = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.mX = (int) ((r15 + (tmp * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) ((r3 + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.mWidth = (int) (f2 + (scaleX * scaleWidth));
        this.mHeight = (int) (f4 + (scaleY * scaleHeight));
        if (!Float.isNaN(c.mPercentX)) {
            dxdx = c.mPercentX;
        } else {
            dxdx = position;
        }
        float dydx = Float.isNaN(c.mAltPercentY) ? 0.0f : c.mAltPercentY;
        if (!Float.isNaN(c.mPercentY)) {
            dydy = c.mPercentY;
        } else {
            dydy = position;
        }
        float dxdy = Float.isNaN(c.mAltPercentX) ? 0.0f : c.mAltPercentX;
        this.mMode = 0;
        this.mX = (int) (((startTimePoint.mX + (tmp * dxdx)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) (((startTimePoint.mY + (tmp * dydx)) + (pathVectorY * dydy)) - ((scaleY * scaleHeight) / 2.0f));
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if (startTimePoint.mAnimateRelativeTo != Key.UNSET) {
            initPolar(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
            return;
        }
        switch (c.mPositionType) {
            case 1:
                initPath(c, startTimePoint, endTimePoint);
                return;
            case 2:
                initScreen(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
                return;
            case 3:
                initAxis(c, startTimePoint, endTimePoint);
                return;
            default:
                initCartesian(c, startTimePoint, endTimePoint);
                return;
        }
    }

    void initPolar(int parentWidth, int parentHeight, KeyPosition c, MotionPaths s, MotionPaths e) {
        float min;
        float f;
        float position = c.mFramePosition / 100.0f;
        this.mTime = position;
        this.mDrawPath = c.mDrawPath;
        this.mMode = c.mPositionType;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float scaleX = e.mWidth - s.mWidth;
        float scaleY = e.mHeight - s.mHeight;
        this.mPosition = this.mTime;
        this.mWidth = (int) (r4 + (scaleX * scaleWidth));
        this.mHeight = (int) (r6 + (scaleY * scaleHeight));
        switch (c.mPositionType) {
            case 2:
                if (Float.isNaN(c.mPercentX)) {
                    float f2 = e.mX;
                    float f3 = s.mX;
                    min = ((f2 - f3) * position) + f3;
                } else {
                    min = c.mPercentX * Math.min(scaleHeight, scaleWidth);
                }
                this.mX = min;
                if (Float.isNaN(c.mPercentY)) {
                    float f4 = e.mY;
                    float f5 = s.mY;
                    f = ((f4 - f5) * position) + f5;
                } else {
                    f = c.mPercentY;
                }
                this.mY = f;
                break;
            default:
                float f6 = Float.isNaN(c.mPercentX) ? position : c.mPercentX;
                float f7 = e.mX;
                float f8 = s.mX;
                this.mX = (f6 * (f7 - f8)) + f8;
                float f9 = Float.isNaN(c.mPercentY) ? position : c.mPercentY;
                float f10 = e.mY;
                float f11 = s.mY;
                this.mY = (f9 * (f10 - f11)) + f11;
                break;
        }
        this.mAnimateRelativeTo = s.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public void setupRelative(MotionController mc, MotionPaths relative) {
        double dx = ((this.mX + (this.mWidth / 2.0f)) - relative.mX) - (relative.mWidth / 2.0f);
        double dy = ((this.mY + (this.mHeight / 2.0f)) - relative.mY) - (relative.mHeight / 2.0f);
        this.mRelativeToController = mc;
        this.mX = (float) Math.hypot(dy, dx);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.mY = (float) (Math.atan2(dy, dx) + 1.5707963267948966d);
        } else {
            this.mY = (float) Math.toRadians(this.mRelativeAngle);
        }
    }

    void initScreen(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position = c.mFramePosition / 100.0f;
        this.mTime = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float f = endTimePoint.mWidth;
        float f2 = startTimePoint.mWidth;
        float scaleX = f - f2;
        float f3 = endTimePoint.mHeight;
        float f4 = startTimePoint.mHeight;
        float scaleY = f3 - f4;
        this.mPosition = this.mTime;
        float startCenterX = startTimePoint.mX + (f2 / 2.0f);
        float startCenterY = startTimePoint.mY + (f4 / 2.0f);
        float endCenterX = endTimePoint.mX + (f / 2.0f);
        float endCenterY = endTimePoint.mY + (f3 / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.mX = (int) ((r15 + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) ((r4 + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.mWidth = (int) (f2 + (scaleX * scaleWidth));
        this.mHeight = (int) (f4 + (scaleY * scaleHeight));
        this.mMode = 2;
        if (!Float.isNaN(c.mPercentX)) {
            this.mX = (int) (c.mPercentX * (parentWidth - ((int) this.mWidth)));
        }
        if (!Float.isNaN(c.mPercentY)) {
            this.mY = (int) (c.mPercentY * (parentHeight - ((int) this.mHeight)));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    void initPath(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position = c.mFramePosition / 100.0f;
        this.mTime = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float scaleX = endTimePoint.mWidth - startTimePoint.mWidth;
        float scaleY = endTimePoint.mHeight - startTimePoint.mHeight;
        this.mPosition = this.mTime;
        float path = Float.isNaN(c.mPercentX) ? position : c.mPercentX;
        float startCenterX = (startTimePoint.mWidth / 2.0f) + startTimePoint.mX;
        float startCenterY = startTimePoint.mY + (startTimePoint.mHeight / 2.0f);
        float endCenterX = endTimePoint.mX + (endTimePoint.mWidth / 2.0f);
        float endCenterY = endTimePoint.mY + (endTimePoint.mHeight / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.mX = (int) ((r11 + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.mY = (int) ((r15 + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.mWidth = (int) (r12 + (scaleX * scaleWidth));
        this.mHeight = (int) (r4 + (scaleY * scaleHeight));
        float perpendicular = Float.isNaN(c.mPercentY) ? 0.0f : c.mPercentY;
        float perpendicularX = -pathVectorY;
        float normalX = perpendicularX * perpendicular;
        float normalY = pathVectorX * perpendicular;
        this.mMode = 1;
        float endCenterY2 = startTimePoint.mX;
        float f = (int) ((endCenterY2 + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.mX = f;
        float f2 = (int) ((startTimePoint.mY + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.mY = f2;
        this.mX = f + normalX;
        this.mY = f2 + normalY;
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    private static float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    private boolean diff(float a, float b) {
        return (Float.isNaN(a) || Float.isNaN(b)) ? Float.isNaN(a) != Float.isNaN(b) : Math.abs(a - b) > 1.0E-6f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        boolean diffx = diff(this.mX, points.mX);
        boolean diffy = diff(this.mY, points.mY);
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.mPosition, points.mPosition);
        int c2 = c + 1;
        mask[c] = mask[c] | diffx | diffy | arcMode;
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diffx | diffy | arcMode;
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.mWidth, points.mWidth);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.mHeight, points.mHeight);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        float v_x = this.mX;
        float v_y = this.mY;
        float v_width = this.mWidth;
        float v_height = this.mHeight;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float angle = v_y;
            float v_x2 = (float) ((rx + (radius * Math.sin(angle))) - (v_width / 2.0f));
            f = 2.0f;
            v_y = (float) ((ry - (radius * Math.cos(angle))) - (v_height / 2.0f));
            v_x = v_x2;
        } else {
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, double[] vdata, float[] velocity) {
        float v_x = this.mX;
        float v_y = this.mY;
        float v_width = this.mWidth;
        float v_height = this.mHeight;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            float dvalue = (float) vdata[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    dv_x = dvalue;
                    break;
                case 2:
                    v_y = value;
                    dv_y = dvalue;
                    break;
                case 3:
                    v_width = value;
                    dv_width = dvalue;
                    break;
                case 4:
                    v_height = value;
                    dv_height = dvalue;
                    break;
            }
        }
        float dangle = (dv_width / 2.0f) + dv_x;
        float dpos_y = (dv_height / 2.0f) + dv_y;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float angle = v_y;
            float dradius = dv_x;
            float dangle2 = dv_y;
            float drx = vel[0];
            float v_x2 = vel[1];
            float v_x3 = (float) ((rx + (radius * Math.sin(angle))) - (v_width / 2.0f));
            float v_y2 = (float) ((ry - (radius * Math.cos(angle))) - (v_height / 2.0f));
            float dpos_x = (float) (drx + (dradius * Math.sin(angle)) + (Math.cos(angle) * dangle2));
            dpos_y = (float) ((v_x2 - (dradius * Math.cos(angle))) + (Math.sin(angle) * dangle2));
            v_y = v_y2;
            dangle = dpos_x;
            v_x = v_x3;
        }
        point[0] = (v_width / 2.0f) + v_x + 0.0f;
        point[1] = (v_height / 2.0f) + v_y + 0.0f;
        velocity[0] = dangle;
        velocity[1] = dpos_y;
    }

    void getCenterVelocity(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        float v_x = this.mX;
        float v_y = this.mY;
        float v_width = this.mWidth;
        float v_height = this.mHeight;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float radius = v_x;
            float angle = v_y;
            float v_x2 = (float) ((rx + (radius * Math.sin(angle))) - (v_width / 2.0f));
            f = 2.0f;
            v_y = (float) ((ry - (radius * Math.cos(angle))) - (v_height / 2.0f));
            v_x = v_x2;
        } else {
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.mX;
        float f2 = this.mY;
        float v_width = this.mWidth;
        float v_height = this.mHeight;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = v_width;
        point[offset + 1] = v_height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setView(float position, View view, int[] toUse, double[] data, double[] slope, double[] cycle, boolean mForceMeasure) {
        float v_x;
        float v_width;
        float v_height;
        boolean z;
        boolean z2;
        float v_y;
        View view2 = view;
        float v_x2 = this.mX;
        float v_y2 = this.mY;
        float v_width2 = this.mWidth;
        float v_height2 = this.mHeight;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        float delta_path = 0.0f;
        float path_rotate = Float.NaN;
        if (toUse.length != 0) {
            v_x = v_x2;
            if (this.mTempValue.length <= toUse[toUse.length - 1]) {
                int scratch_data_length = toUse[toUse.length - 1] + 1;
                this.mTempValue = new double[scratch_data_length];
                this.mTempDelta = new double[scratch_data_length];
            }
        } else {
            v_x = v_x2;
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i = 0; i < toUse.length; i++) {
            this.mTempValue[toUse[i]] = data[i];
            this.mTempDelta[toUse[i]] = slope[i];
        }
        int i2 = 0;
        float v_y3 = v_y2;
        float v_width3 = v_width2;
        while (true) {
            double[] dArr = this.mTempValue;
            if (i2 < dArr.length) {
                if (!Double.isNaN(dArr[i2]) || (cycle != null && cycle[i2] != 0.0d)) {
                    double deltaCycle = cycle != null ? cycle[i2] : 0.0d;
                    if (!Double.isNaN(this.mTempValue[i2])) {
                        deltaCycle = this.mTempValue[i2] + deltaCycle;
                    }
                    float value = (float) deltaCycle;
                    float dvalue = (float) this.mTempDelta[i2];
                    switch (i2) {
                        case 0:
                            delta_path = value;
                            break;
                        case 1:
                            dv_x = dvalue;
                            v_x = value;
                            break;
                        case 2:
                            v_y3 = value;
                            dv_y = dvalue;
                            break;
                        case 3:
                            v_width3 = value;
                            dv_width = dvalue;
                            break;
                        case 4:
                            v_height2 = value;
                            dv_height = dvalue;
                            break;
                        case 5:
                            path_rotate = value;
                            break;
                    }
                }
                i2++;
            } else {
                MotionController motionController = this.mRelativeToController;
                if (motionController != null) {
                    float[] pos = new float[2];
                    float[] vel = new float[2];
                    motionController.getCenter(position, pos, vel);
                    float rx = pos[0];
                    float ry = pos[1];
                    float radius = v_x;
                    float dradius = dv_x;
                    float dangle = dv_y;
                    float drx = vel[0];
                    float dry = vel[1];
                    float pos_x = (float) ((rx + (radius * Math.sin(v_y3))) - (v_width3 / 2.0f));
                    float pos_y = (float) ((ry - (radius * Math.cos(v_y3))) - (v_height2 / 2.0f));
                    double d = dradius;
                    v_width = v_width3;
                    v_height = v_height2;
                    float dpos_x = (float) (drx + (d * Math.sin(v_y3)) + (radius * Math.cos(v_y3) * dangle));
                    float dpos_y = (float) ((dry - (dradius * Math.cos(v_y3))) + (radius * Math.sin(v_y3) * dangle));
                    v_x = pos_x;
                    if (slope.length < 2) {
                        z = true;
                        z2 = false;
                    } else {
                        z2 = false;
                        slope[0] = dpos_x;
                        z = true;
                        slope[1] = dpos_y;
                    }
                    if (Float.isNaN(path_rotate)) {
                        view2 = view;
                    } else {
                        float rot = (float) (path_rotate + Math.toDegrees(Math.atan2(dpos_y, dpos_x)));
                        view2 = view;
                        view2.setRotation(rot);
                    }
                    v_y = pos_y;
                } else {
                    float v_y4 = v_y3;
                    v_width = v_width3;
                    v_height = v_height2;
                    float dv_x2 = dv_x;
                    float dv_y2 = dv_y;
                    float dv_width2 = dv_width;
                    float dv_height2 = dv_height;
                    z = true;
                    z2 = false;
                    if (!Float.isNaN(path_rotate)) {
                        float dx = dv_x2 + (dv_width2 / 2.0f);
                        float dy = dv_y2 + (dv_height2 / 2.0f);
                        float rot2 = 0.0f + ((float) Math.toDegrees(Math.atan2(dy, dx))) + path_rotate;
                        view2.setRotation(rot2);
                    }
                    v_y = v_y4;
                }
                if (view2 instanceof FloatLayout) {
                    ((FloatLayout) view2).layout(v_x, v_y, v_x + v_width, v_y + v_height);
                    return;
                }
                int l = (int) (v_x + 0.5f);
                int t = (int) (v_y + 0.5f);
                int r = (int) (v_x + 0.5f + v_width);
                int b = (int) (0.5f + v_y + v_height);
                int i_width = r - l;
                int i_height = b - t;
                boolean remeasure = (i_width == view.getMeasuredWidth() && i_height == view.getMeasuredHeight()) ? z2 : z;
                if (remeasure || mForceMeasure) {
                    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_width, BasicMeasure.EXACTLY);
                    int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_height, BasicMeasure.EXACTLY);
                    view2.measure(widthMeasureSpec, heightMeasureSpec);
                }
                view2.layout(l, t, r, b);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float angle;
        float v_x = this.mX;
        float v_y = this.mY;
        float v_width = this.mWidth;
        float v_height = this.mHeight;
        float alpha = 0.0f;
        float rotationX = 0.0f;
        int i = 0;
        while (true) {
            float alpha2 = alpha;
            if (i < toUse.length) {
                float rotationX2 = rotationX;
                float value = (float) data[i];
                switch (toUse[i]) {
                    case 1:
                        v_x = value;
                        break;
                    case 2:
                        v_y = value;
                        break;
                    case 3:
                        v_width = value;
                        break;
                    case 4:
                        v_height = value;
                        break;
                }
                i++;
                alpha = alpha2;
                rotationX = rotationX2;
            } else {
                MotionController motionController = this.mRelativeToController;
                if (motionController == null) {
                    angle = 0.0f;
                } else {
                    float rx = motionController.getCenterX();
                    float ry = this.mRelativeToController.getCenterY();
                    float radius = v_x;
                    angle = 0.0f;
                    float v_x2 = (float) ((rx + (radius * Math.sin(v_y))) - (v_width / 2.0f));
                    v_y = (float) ((ry - (radius * Math.cos(v_y))) - (v_height / 2.0f));
                    v_x = v_x2;
                }
                float x1 = v_x;
                float y1 = v_y;
                float x2 = v_x + v_width;
                float y2 = y1;
                float x3 = x2;
                float y3 = v_y + v_height;
                float x4 = x1;
                float y4 = y3;
                float cx = x1 + (v_width / 2.0f);
                float cy = y1 + (v_height / 2.0f);
                if (!Float.isNaN(Float.NaN)) {
                    cx = x1 + ((x2 - x1) * Float.NaN);
                }
                if (!Float.isNaN(Float.NaN)) {
                    cy = y1 + ((y3 - y1) * Float.NaN);
                }
                if (1.0f != 1.0f) {
                    float midx = (x1 + x2) / 2.0f;
                    x1 = ((x1 - midx) * 1.0f) + midx;
                    x2 = ((x2 - midx) * 1.0f) + midx;
                    x3 = ((x3 - midx) * 1.0f) + midx;
                    x4 = ((x4 - midx) * 1.0f) + midx;
                }
                if (1.0f != 1.0f) {
                    float midy = (y1 + y3) / 2.0f;
                    y1 = ((y1 - midy) * 1.0f) + midy;
                    y2 = ((y2 - midy) * 1.0f) + midy;
                    y3 = ((y3 - midy) * 1.0f) + midy;
                    y4 = ((y4 - midy) * 1.0f) + midy;
                }
                if (angle != 0.0f) {
                    float v_x3 = angle;
                    float sin = (float) Math.sin(Math.toRadians(v_x3));
                    float cos = (float) Math.cos(Math.toRadians(v_x3));
                    float f = cx;
                    float f2 = cy;
                    float f3 = x1;
                    float f4 = y1;
                    float tx1 = xRotate(sin, cos, f, f2, f3, f4);
                    float ty1 = yRotate(sin, cos, f, f2, f3, f4);
                    float f5 = x2;
                    float f6 = y2;
                    float tx2 = xRotate(sin, cos, f, f2, f5, f6);
                    float ty2 = yRotate(sin, cos, f, f2, f5, f6);
                    float f7 = x3;
                    float f8 = y3;
                    float tx3 = xRotate(sin, cos, f, f2, f7, f8);
                    float ty3 = yRotate(sin, cos, f, f2, f7, f8);
                    float f9 = x4;
                    float f10 = y4;
                    float tx4 = xRotate(sin, cos, f, f2, f9, f10);
                    float ty4 = yRotate(sin, cos, f, f2, f9, f10);
                    x1 = tx1;
                    y1 = ty1;
                    x2 = tx2;
                    y2 = ty2;
                    x3 = tx3;
                    y3 = ty3;
                    x4 = tx4;
                    y4 = ty4;
                }
                int offset2 = offset + 1;
                path[offset] = x1 + 0.0f;
                int offset3 = offset2 + 1;
                path[offset2] = y1 + 0.0f;
                int offset4 = offset3 + 1;
                path[offset3] = x2 + 0.0f;
                int offset5 = offset4 + 1;
                path[offset4] = y2 + 0.0f;
                int offset6 = offset5 + 1;
                path[offset5] = x3 + 0.0f;
                int offset7 = offset6 + 1;
                path[offset6] = y3 + 0.0f;
                int offset8 = offset7 + 1;
                path[offset7] = x4 + 0.0f;
                int i2 = offset8 + 1;
                path[offset8] = y4 + 0.0f;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        float d_x = 0.0f;
        float d_y = 0.0f;
        float d_width = 0.0f;
        float d_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float deltaV = (float) deltaData[i];
            switch (toUse[i]) {
                case 1:
                    d_x = deltaV;
                    break;
                case 2:
                    d_y = deltaV;
                    break;
                case 3:
                    d_width = deltaV;
                    break;
                case 4:
                    d_height = deltaV;
                    break;
            }
        }
        float deltaX = d_x - ((0.0f * d_width) / 2.0f);
        float deltaY = d_y - ((0.0f * d_height) / 2.0f);
        float deltaWidth = (0.0f + 1.0f) * d_width;
        float deltaHeight = (0.0f + 1.0f) * d_height;
        float deltaRight = deltaX + deltaWidth;
        float deltaBottom = deltaY + deltaHeight;
        mAnchorDpDt[0] = ((1.0f - locationX) * deltaX) + (deltaRight * locationX) + 0.0f;
        mAnchorDpDt[1] = ((1.0f - locationY) * deltaY) + (deltaBottom * locationY) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.mPosition, this.mX, this.mY, this.mWidth, this.mHeight, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = set[toUse[i]];
                c++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCustomData(String name) {
        return this.mAttributes.containsKey(name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCustomDataCount(String name) {
        ConstraintAttribute a = this.mAttributes.get(name);
        if (a == null) {
            return 0;
        }
        return a.numberOfInterpolatedValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.mAttributes.get(name);
        if (a == null) {
            return 0;
        }
        if (a.numberOfInterpolatedValues() == 1) {
            value[offset] = a.getValueToInterpolate();
            return 1;
        }
        int count = a.numberOfInterpolatedValues();
        float[] f = new float[count];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < count) {
            value[offset] = f[i];
            i++;
            offset++;
        }
        return count;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBounds(float x, float y, float w, float h) {
        this.mX = x;
        this.mY = y;
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths o) {
        return Float.compare(this.mPosition, o.mPosition);
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathMotionArc = c.motion.mPathMotionArc;
        this.mAnimateRelativeTo = c.motion.mAnimateRelativeTo;
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mAnimateCircleAngleTo = c.motion.mAnimateCircleAngleTo;
        this.mProgress = c.propertySet.mProgress;
        this.mRelativeAngle = c.layout.circleAngle;
        Set<String> at = c.mCustomConstraints.keySet();
        for (String s : at) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr != null && attr.isContinuous()) {
                this.mAttributes.put(s, attr);
            }
        }
    }

    public void configureRelativeTo(MotionController toOrbit) {
        toOrbit.getPos(this.mProgress);
    }
}
