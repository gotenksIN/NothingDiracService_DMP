package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.core.motion.utils.ViewState;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Motion implements TypedValues {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    String[] mAttributeTable;
    private HashMap<String, SplineSet> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    public String mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private MotionKeyTrigger[] mKeyTriggers;
    Motion mRelativeMotion;
    private CurveFit[] mSpline;
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    MotionWidget mView;
    Rect mTempRect = new Rect();
    private int mCurveFitType = 0;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    float mMotionStagger = Float.NaN;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private int mMaxDimension = 4;
    private float[] mValuesBuff = new float[4];
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    private float[] mVelocity = new float[1];
    private ArrayList<MotionKey> mKeyList = new ArrayList<>();
    private int mPathMotionArc = -1;
    private int mTransformPivotTarget = -1;
    private MotionWidget mTransformPivotView = null;
    private int mQuantizeMotionSteps = -1;
    private float mQuantizeMotionPhase = Float.NaN;
    private DifferentialInterpolator mQuantizeMotionInterpolator = null;
    private boolean mNoMovement = false;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int transformPivotTarget) {
        this.mTransformPivotTarget = transformPivotTarget;
        this.mTransformPivotView = null;
    }

    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    public Motion(MotionWidget view) {
        setView(view);
    }

    public float getStartX() {
        return this.mStartMotionPath.mX;
    }

    public float getStartY() {
        return this.mStartMotionPath.mY;
    }

    public float getFinalX() {
        return this.mEndMotionPath.mX;
    }

    public float getFinalY() {
        return this.mEndMotionPath.mY;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.mWidth;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.mHeight;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.mWidth;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.mHeight;
    }

    public String getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(Motion motionController) {
        this.mRelativeMotion = motionController;
    }

    private void setupRelative() {
        Motion motion = this.mRelativeMotion;
        if (motion == null) {
            return;
        }
        this.mStartMotionPath.setupRelative(motion, motion.mStartMotionPath);
        MotionPaths motionPaths = this.mEndMotionPath;
        Motion motion2 = this.mRelativeMotion;
        motionPaths.setupRelative(motion2, motion2.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double p, float[] pos, float[] vel) {
        double[] position = new double[4];
        double[] velocity = new double[4];
        this.mSpline[0].getPos(p, position);
        this.mSpline[0].getSlope(p, velocity);
        Arrays.fill(vel, 0.0f);
        this.mStartMotionPath.getCenter(p, this.mInterpolateVariables, position, pos, velocity, vel);
    }

    public void buildPath(float[] points, int pointCount) {
        float position;
        double p;
        Motion motion = this;
        float f = 1.0f;
        float mils = 1.0f / (pointCount - 1);
        HashMap<String, SplineSet> hashMap = motion.mAttributesMap;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = motion.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap3 = motion.mCycleMap;
        KeyCycleOscillator osc_x = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap4 = motion.mCycleMap;
        KeyCycleOscillator osc_y = hashMap4 != null ? hashMap4.get("translationY") : null;
        int i = 0;
        while (i < pointCount) {
            float position2 = i * mils;
            float f2 = motion.mStaggerScale;
            if (f2 == f) {
                position = position2;
            } else {
                float f3 = motion.mStaggerOffset;
                if (position2 < f3) {
                    position2 = 0.0f;
                }
                if (position2 > f3 && position2 < 1.0d) {
                    position = Math.min((position2 - f3) * f2, f);
                } else {
                    position = position2;
                }
            }
            double p2 = position;
            Easing easing = motion.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = motion.mMotionPaths.iterator();
            float start = 0.0f;
            Easing easing2 = easing;
            float end = Float.NaN;
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                if (frame.mKeyFrameEasing != null) {
                    if (frame.mTime < position) {
                        easing2 = frame.mKeyFrameEasing;
                        start = frame.mTime;
                    } else if (Float.isNaN(end)) {
                        end = frame.mTime;
                    }
                }
            }
            if (easing2 == null) {
                p = p2;
            } else {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                float offset = (position - start) / (end - start);
                double p3 = offset;
                float offset2 = (float) easing2.get(p3);
                p = ((end - start) * offset2) + start;
            }
            motion.mSpline[0].getPos(p, motion.mInterpolateData);
            CurveFit curveFit = motion.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motion.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            float position3 = position;
            motion.mStartMotionPath.getCenter(p, motion.mInterpolateVariables, motion.mInterpolateData, points, i * 2);
            if (osc_x != null) {
                int i2 = i * 2;
                points[i2] = points[i2] + osc_x.get(position3);
            } else if (trans_x != null) {
                int i3 = i * 2;
                points[i3] = points[i3] + trans_x.get(position3);
            }
            if (osc_y != null) {
                int i4 = (i * 2) + 1;
                points[i4] = points[i4] + osc_y.get(position3);
            } else if (trans_y != null) {
                int i5 = (i * 2) + 1;
                points[i5] = points[i5] + trans_y.get(position3);
            }
            i++;
            f = 1.0f;
            motion = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double[] getPos(double position) {
        this.mSpline[0].getPos(position, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(position, dArr);
            }
        }
        return this.mInterpolateData;
    }

    void buildBounds(float[] bounds, int pointCount) {
        float mils;
        Motion motion = this;
        int i = pointCount;
        float f = 1.0f;
        float mils2 = 1.0f / (i - 1);
        HashMap<String, SplineSet> hashMap = motion.mAttributesMap;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = motion.mAttributesMap;
        if (hashMap2 != null) {
            hashMap2.get("translationY");
        }
        HashMap<String, KeyCycleOscillator> hashMap3 = motion.mCycleMap;
        if (hashMap3 != null) {
            hashMap3.get("translationX");
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = motion.mCycleMap;
        if (hashMap4 != null) {
            hashMap4.get("translationY");
        }
        int i2 = 0;
        while (i2 < i) {
            float position = i2 * mils2;
            float f2 = motion.mStaggerScale;
            if (f2 != f) {
                float f3 = motion.mStaggerOffset;
                if (position < f3) {
                    position = 0.0f;
                }
                if (position > f3 && position < 1.0d) {
                    position = Math.min((position - f3) * f2, f);
                }
            }
            double p = position;
            Easing easing = motion.mStartMotionPath.mKeyFrameEasing;
            float start = 0.0f;
            float end = Float.NaN;
            Iterator<MotionPaths> it = motion.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                if (frame.mKeyFrameEasing != null) {
                    if (frame.mTime < position) {
                        Easing easing2 = frame.mKeyFrameEasing;
                        start = frame.mTime;
                        easing = easing2;
                    } else if (Float.isNaN(end)) {
                        end = frame.mTime;
                    }
                }
            }
            if (easing == null) {
                mils = mils2;
            } else {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                float offset = (position - start) / (end - start);
                mils = mils2;
                p = ((end - start) * ((float) easing.get(offset))) + start;
            }
            motion.mSpline[0].getPos(p, motion.mInterpolateData);
            CurveFit curveFit = motion.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motion.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            motion.mStartMotionPath.getBounds(motion.mInterpolateVariables, motion.mInterpolateData, bounds, i2 * 2);
            i2++;
            motion = this;
            i = pointCount;
            mils2 = mils;
            trans_x = trans_x;
            f = 1.0f;
        }
    }

    private float getPreCycleDistance() {
        float offset;
        double p;
        int pointCount = 100;
        float[] points = new float[2];
        float mils = 1.0f / (100 - 1);
        float sum = 0.0f;
        double x = 0.0d;
        double y = 0.0d;
        int i = 0;
        while (i < pointCount) {
            float position = i * mils;
            double p2 = position;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            int pointCount2 = pointCount;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float start = 0.0f;
            Easing easing2 = easing;
            float end = Float.NaN;
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                Iterator<MotionPaths> it2 = it;
                if (frame.mKeyFrameEasing != null) {
                    if (frame.mTime < position) {
                        Easing easing3 = frame.mKeyFrameEasing;
                        start = frame.mTime;
                        easing2 = easing3;
                    } else if (Float.isNaN(end)) {
                        end = frame.mTime;
                    }
                }
                it = it2;
            }
            if (easing2 == null) {
                offset = end;
                p = p2;
            } else {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                float offset2 = (position - start) / (end - start);
                double p3 = offset2;
                float offset3 = (float) easing2.get(p3);
                double d = ((end - start) * offset3) + start;
                offset = end;
                p = d;
            }
            this.mSpline[0].getPos(p, this.mInterpolateData);
            int i2 = i;
            this.mStartMotionPath.getCenter(p, this.mInterpolateVariables, this.mInterpolateData, points, 0);
            if (i2 > 0) {
                sum += (float) Math.hypot(y - points[1], x - points[0]);
            }
            x = points[0];
            y = points[1];
            i = i2 + 1;
            pointCount = pointCount2;
        }
        return sum;
    }

    MotionKeyPosition getPositionKeyframe(int layoutWidth, int layoutHeight, float x, float y) {
        FloatRect start = new FloatRect();
        start.left = this.mStartMotionPath.mX;
        start.top = this.mStartMotionPath.mY;
        start.right = start.left + this.mStartMotionPath.mWidth;
        start.bottom = start.top + this.mStartMotionPath.mHeight;
        FloatRect end = new FloatRect();
        end.left = this.mEndMotionPath.mX;
        end.top = this.mEndMotionPath.mY;
        end.right = end.left + this.mEndMotionPath.mWidth;
        end.bottom = end.top + this.mEndMotionPath.mHeight;
        Iterator<MotionKey> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            MotionKey key = it.next();
            if ((key instanceof MotionKeyPosition) && ((MotionKeyPosition) key).intersects(layoutWidth, layoutHeight, start, end, x, y)) {
                return (MotionKeyPosition) key;
            }
        }
        return null;
    }

    public int buildKeyFrames(float[] keyFrames, int[] mode, int[] pos) {
        if (keyFrames == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths keyFrame = it.next();
                mode[count] = keyFrame.mMode;
                count++;
            }
            count = 0;
        }
        if (pos != null) {
            Iterator<MotionPaths> it2 = this.mMotionPaths.iterator();
            while (it2.hasNext()) {
                MotionPaths keyFrame2 = it2.next();
                pos[count] = (int) (keyFrame2.mPosition * 100.0f);
                count++;
            }
            count = 0;
        }
        for (int i = 0; i < time.length; i++) {
            this.mSpline[0].getPos(time[i], this.mInterpolateData);
            this.mStartMotionPath.getCenter(time[i], this.mInterpolateVariables, this.mInterpolateData, keyFrames, count);
            count += 2;
        }
        return count / 2;
    }

    int buildKeyBounds(float[] keyBounds, int[] mode) {
        if (keyBounds == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths keyFrame = it.next();
                mode[count] = keyFrame.mMode;
                count++;
            }
            count = 0;
        }
        for (double d : time) {
            this.mSpline[0].getPos(d, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, keyBounds, count);
            count += 2;
        }
        return count / 2;
    }

    int getAttributeValues(String attributeType, float[] points, int pointCount) {
        float f = 1.0f / (pointCount - 1);
        SplineSet spline = this.mAttributesMap.get(attributeType);
        if (spline == null) {
            return -1;
        }
        for (int j = 0; j < points.length; j++) {
            points[j] = spline.get(j / (points.length - 1));
        }
        int j2 = points.length;
        return j2;
    }

    public void buildRect(float p, float[] path, int offset) {
        this.mSpline[0].getPos(getAdjustedPosition(p, null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, offset);
    }

    void buildRectangles(float[] path, int pointCount) {
        float mils = 1.0f / (pointCount - 1);
        for (int i = 0; i < pointCount; i++) {
            float position = i * mils;
            this.mSpline[0].getPos(getAdjustedPosition(position, null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, i * 8);
        }
    }

    float getKeyFrameParameter(int type, float x, float y) {
        float dx = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
        float dy = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
        float startCenterX = this.mStartMotionPath.mX + (this.mStartMotionPath.mWidth / 2.0f);
        float startCenterY = this.mStartMotionPath.mY + (this.mStartMotionPath.mHeight / 2.0f);
        float hypotenuse = (float) Math.hypot(dx, dy);
        if (hypotenuse < 1.0E-7d) {
            return Float.NaN;
        }
        float vx = x - startCenterX;
        float vy = y - startCenterY;
        float distFromStart = (float) Math.hypot(vx, vy);
        if (distFromStart == 0.0f) {
            return 0.0f;
        }
        float pathDistance = (vx * dx) + (vy * dy);
        switch (type) {
            case 0:
                return pathDistance / hypotenuse;
            case 1:
                return (float) Math.sqrt((hypotenuse * hypotenuse) - (pathDistance * pathDistance));
            case 2:
                return vx / dx;
            case 3:
                return vy / dx;
            case 4:
                return vx / dy;
            case 5:
                return vy / dy;
            default:
                return 0.0f;
        }
    }

    private void insertKey(MotionPaths point) {
        MotionPaths redundant = null;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths p = it.next();
            if (point.mPosition == p.mPosition) {
                redundant = p;
            }
        }
        if (redundant != null) {
            this.mMotionPaths.remove(redundant);
        }
        int pos = Collections.binarySearch(this.mMotionPaths, point);
        if (pos == 0) {
            Utils.loge(TAG, " KeyPath position \"" + point.mPosition + "\" outside of range");
        }
        this.mMotionPaths.add((-pos) - 1, point);
    }

    void addKeys(ArrayList<MotionKey> list) {
        this.mKeyList.addAll(list);
    }

    public void addKey(MotionKey key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int arc) {
        this.mPathMotionArc = arc;
    }

    public void setup(int parentWidth, int parentHeight, float transitionDuration, long currentTime) {
        HashSet<String> splineAttributes;
        HashMap<String, Integer> interpolation;
        boolean arcMode;
        boolean[] mask;
        CustomVariable attribute;
        Iterator<String> it;
        HashSet<String> timeCycleAttributes;
        SplineSet splineSets;
        HashSet<String> timeCycleAttributes2;
        Integer boxedCurve;
        HashSet<String> springAttributes;
        ArrayList<MotionKeyTrigger> triggerList;
        SplineSet splineSets2;
        String customAttributeName;
        HashSet<String> springAttributes2 = new HashSet<>();
        HashSet<String> timeCycleAttributes3 = new HashSet<>();
        HashSet<String> splineAttributes2 = new HashSet<>();
        HashSet<String> cycleAttributes = new HashSet<>();
        HashMap<String, Integer> interpolation2 = new HashMap<>();
        ArrayList<MotionKeyTrigger> triggerList2 = null;
        setupRelative();
        if (this.mPathMotionArc != -1 && this.mStartMotionPath.mPathMotionArc == -1) {
            this.mStartMotionPath.mPathMotionArc = this.mPathMotionArc;
        }
        this.mStartPoint.different(this.mEndPoint, splineAttributes2);
        ArrayList<MotionKey> arrayList = this.mKeyList;
        if (arrayList != null) {
            Iterator<MotionKey> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                MotionKey key = it2.next();
                if (key instanceof MotionKeyPosition) {
                    MotionKeyPosition keyPath = (MotionKeyPosition) key;
                    insertKey(new MotionPaths(parentWidth, parentHeight, keyPath, this.mStartMotionPath, this.mEndMotionPath));
                    if (keyPath.mCurveFit != -1) {
                        this.mCurveFitType = keyPath.mCurveFit;
                    }
                } else if (key instanceof MotionKeyCycle) {
                    key.getAttributeNames(cycleAttributes);
                } else if (key instanceof MotionKeyTimeCycle) {
                    key.getAttributeNames(timeCycleAttributes3);
                } else if (key instanceof MotionKeyTrigger) {
                    if (triggerList2 == null) {
                        triggerList2 = new ArrayList<>();
                    }
                    triggerList2.add((MotionKeyTrigger) key);
                } else {
                    key.setInterpolation(interpolation2);
                    key.getAttributeNames(splineAttributes2);
                }
            }
        }
        if (triggerList2 != null) {
            this.mKeyTriggers = (MotionKeyTrigger[]) triggerList2.toArray(new MotionKeyTrigger[0]);
        }
        char c = 1;
        if (!splineAttributes2.isEmpty()) {
            this.mAttributesMap = new HashMap<>();
            Iterator<String> it3 = splineAttributes2.iterator();
            while (it3.hasNext()) {
                String attribute2 = it3.next();
                if (attribute2.startsWith("CUSTOM,")) {
                    KeyFrameArray.CustomVar attrList = new KeyFrameArray.CustomVar();
                    String customAttributeName2 = attribute2.split(",")[c];
                    Iterator<MotionKey> it4 = this.mKeyList.iterator();
                    while (it4.hasNext()) {
                        HashSet<String> springAttributes3 = springAttributes2;
                        MotionKey key2 = it4.next();
                        ArrayList<MotionKeyTrigger> triggerList3 = triggerList2;
                        if (key2.mCustom == null) {
                            triggerList2 = triggerList3;
                            springAttributes2 = springAttributes3;
                        } else {
                            CustomVariable customAttribute = key2.mCustom.get(customAttributeName2);
                            if (customAttribute == null) {
                                customAttributeName = customAttributeName2;
                            } else {
                                customAttributeName = customAttributeName2;
                                attrList.append(key2.mFramePosition, customAttribute);
                            }
                            triggerList2 = triggerList3;
                            springAttributes2 = springAttributes3;
                            customAttributeName2 = customAttributeName;
                        }
                    }
                    springAttributes = springAttributes2;
                    triggerList = triggerList2;
                    splineSets2 = SplineSet.makeCustomSplineSet(attribute2, attrList);
                } else {
                    springAttributes = springAttributes2;
                    triggerList = triggerList2;
                    splineSets2 = SplineSet.makeSpline(attribute2, currentTime);
                }
                if (splineSets2 == null) {
                    triggerList2 = triggerList;
                    springAttributes2 = springAttributes;
                    c = 1;
                } else {
                    splineSets2.setType(attribute2);
                    this.mAttributesMap.put(attribute2, splineSets2);
                    triggerList2 = triggerList;
                    springAttributes2 = springAttributes;
                    c = 1;
                }
            }
            ArrayList<MotionKey> arrayList2 = this.mKeyList;
            if (arrayList2 != null) {
                Iterator<MotionKey> it5 = arrayList2.iterator();
                while (it5.hasNext()) {
                    MotionKey key3 = it5.next();
                    if (key3 instanceof MotionKeyAttributes) {
                        key3.addValues(this.mAttributesMap);
                    }
                }
            }
            this.mStartPoint.addValues(this.mAttributesMap, 0);
            this.mEndPoint.addValues(this.mAttributesMap, 100);
            for (String spline : this.mAttributesMap.keySet()) {
                int curve = 0;
                if (interpolation2.containsKey(spline) && (boxedCurve = interpolation2.get(spline)) != null) {
                    curve = boxedCurve.intValue();
                }
                SplineSet splineSet = this.mAttributesMap.get(spline);
                if (splineSet != null) {
                    splineSet.setup(curve);
                }
            }
        }
        if (!timeCycleAttributes3.isEmpty()) {
            if (this.mTimeCycleAttributesMap == null) {
                this.mTimeCycleAttributesMap = new HashMap<>();
            }
            Iterator<String> it6 = timeCycleAttributes3.iterator();
            while (it6.hasNext()) {
                String attribute3 = it6.next();
                if (!this.mTimeCycleAttributesMap.containsKey(attribute3)) {
                    if (attribute3.startsWith("CUSTOM,")) {
                        KeyFrameArray.CustomVar attrList2 = new KeyFrameArray.CustomVar();
                        String customAttributeName3 = attribute3.split(",")[1];
                        Iterator<MotionKey> it7 = this.mKeyList.iterator();
                        while (it7.hasNext()) {
                            MotionKey key4 = it7.next();
                            Iterator<String> it8 = it6;
                            if (key4.mCustom == null) {
                                it6 = it8;
                            } else {
                                CustomVariable customAttribute2 = key4.mCustom.get(customAttributeName3);
                                if (customAttribute2 == null) {
                                    timeCycleAttributes2 = timeCycleAttributes3;
                                } else {
                                    timeCycleAttributes2 = timeCycleAttributes3;
                                    attrList2.append(key4.mFramePosition, customAttribute2);
                                }
                                it6 = it8;
                                timeCycleAttributes3 = timeCycleAttributes2;
                            }
                        }
                        it = it6;
                        timeCycleAttributes = timeCycleAttributes3;
                        splineSets = SplineSet.makeCustomSplineSet(attribute3, attrList2);
                    } else {
                        it = it6;
                        timeCycleAttributes = timeCycleAttributes3;
                        splineSets = SplineSet.makeSpline(attribute3, currentTime);
                    }
                    if (splineSets == null) {
                        it6 = it;
                        timeCycleAttributes3 = timeCycleAttributes;
                    } else {
                        splineSets.setType(attribute3);
                        it6 = it;
                        timeCycleAttributes3 = timeCycleAttributes;
                    }
                }
            }
            ArrayList<MotionKey> arrayList3 = this.mKeyList;
            if (arrayList3 != null) {
                Iterator<MotionKey> it9 = arrayList3.iterator();
                while (it9.hasNext()) {
                    MotionKey key5 = it9.next();
                    if (key5 instanceof MotionKeyTimeCycle) {
                        ((MotionKeyTimeCycle) key5).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for (String spline2 : this.mTimeCycleAttributesMap.keySet()) {
                int curve2 = 0;
                if (interpolation2.containsKey(spline2)) {
                    curve2 = interpolation2.get(spline2).intValue();
                }
                this.mTimeCycleAttributesMap.get(spline2).setup(curve2);
            }
        }
        MotionPaths[] points = new MotionPaths[this.mMotionPaths.size() + 2];
        int count = 1;
        points[0] = this.mStartMotionPath;
        points[points.length - 1] = this.mEndMotionPath;
        if (this.mMotionPaths.size() > 0 && this.mCurveFitType == MotionKey.UNSET) {
            this.mCurveFitType = 0;
        }
        Iterator<MotionPaths> it10 = this.mMotionPaths.iterator();
        while (it10.hasNext()) {
            MotionPaths point = it10.next();
            points[count] = point;
            count++;
        }
        int variables = 18;
        HashSet<String> attributeNameSet = new HashSet<>();
        for (String s : this.mEndMotionPath.mCustomAttributes.keySet()) {
            if (this.mStartMotionPath.mCustomAttributes.containsKey(s) && !splineAttributes2.contains("CUSTOM," + s)) {
                attributeNameSet.add(s);
            }
        }
        String[] strArr = (String[]) attributeNameSet.toArray(new String[0]);
        this.mAttributeNames = strArr;
        this.mAttributeInterpolatorCount = new int[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.mAttributeNames;
            if (i >= strArr2.length) {
                break;
            }
            String attributeName = strArr2[i];
            this.mAttributeInterpolatorCount[i] = 0;
            int j = 0;
            while (true) {
                if (j >= points.length) {
                    break;
                }
                if (!points[j].mCustomAttributes.containsKey(attributeName) || (attribute = points[j].mCustomAttributes.get(attributeName)) == null) {
                    j++;
                } else {
                    int[] iArr = this.mAttributeInterpolatorCount;
                    iArr[i] = iArr[i] + attribute.numberOfInterpolatedValues();
                    break;
                }
            }
            i++;
        }
        boolean arcMode2 = points[0].mPathMotionArc != -1;
        boolean[] mask2 = new boolean[this.mAttributeNames.length + 18];
        for (int i2 = 1; i2 < points.length; i2++) {
            points[i2].different(points[i2 - 1], mask2, this.mAttributeNames, arcMode2);
        }
        int count2 = 0;
        for (int i3 = 1; i3 < mask2.length; i3++) {
            if (mask2[i3]) {
                count2++;
            }
        }
        this.mInterpolateVariables = new int[count2];
        int varLen = Math.max(2, count2);
        this.mInterpolateData = new double[varLen];
        this.mInterpolateVelocity = new double[varLen];
        int count3 = 0;
        for (int i4 = 1; i4 < mask2.length; i4++) {
            if (mask2[i4]) {
                this.mInterpolateVariables[count3] = i4;
                count3++;
            }
        }
        int i5 = points.length;
        double[][] splineData = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i5, this.mInterpolateVariables.length);
        double[] timePoint = new double[points.length];
        int i6 = 0;
        while (i6 < points.length) {
            points[i6].fillStandard(splineData[i6], this.mInterpolateVariables);
            timePoint[i6] = points[i6].mTime;
            i6++;
            count3 = count3;
        }
        int j2 = 0;
        while (true) {
            int[] iArr2 = this.mInterpolateVariables;
            if (j2 >= iArr2.length) {
                break;
            }
            int interpolateVariable = iArr2[j2];
            if (interpolateVariable < MotionPaths.sNames.length) {
                String s2 = MotionPaths.sNames[this.mInterpolateVariables[j2]] + " [";
                int i7 = 0;
                while (i7 < points.length) {
                    s2 = s2 + splineData[i7][j2];
                    i7++;
                    variables = variables;
                    attributeNameSet = attributeNameSet;
                }
            }
            j2++;
            variables = variables;
            attributeNameSet = attributeNameSet;
        }
        this.mSpline = new CurveFit[this.mAttributeNames.length + 1];
        int i8 = 0;
        while (true) {
            String[] strArr3 = this.mAttributeNames;
            if (i8 >= strArr3.length) {
                break;
            }
            int pointCount = 0;
            double[][] splinePoints = null;
            double[] timePoints = null;
            String name = strArr3[i8];
            int j3 = 0;
            while (true) {
                splineAttributes = splineAttributes2;
                if (j3 < points.length) {
                    if (!points[j3].hasCustomData(name)) {
                        interpolation = interpolation2;
                        arcMode = arcMode2;
                        mask = mask2;
                    } else {
                        if (splinePoints != null) {
                            interpolation = interpolation2;
                        } else {
                            timePoints = new double[points.length];
                            interpolation = interpolation2;
                            splinePoints = (double[][]) Array.newInstance((Class<?>) Double.TYPE, points.length, points[j3].getCustomDataCount(name));
                        }
                        arcMode = arcMode2;
                        mask = mask2;
                        timePoints[pointCount] = points[j3].mTime;
                        points[j3].getCustomData(name, splinePoints[pointCount], 0);
                        pointCount++;
                    }
                    j3++;
                    arcMode2 = arcMode;
                    splineAttributes2 = splineAttributes;
                    interpolation2 = interpolation;
                    mask2 = mask;
                }
            }
            this.mSpline[i8 + 1] = CurveFit.get(this.mCurveFitType, Arrays.copyOf(timePoints, pointCount), (double[][]) Arrays.copyOf(splinePoints, pointCount));
            i8++;
            arcMode2 = arcMode2;
            splineAttributes2 = splineAttributes;
            interpolation2 = interpolation2;
            mask2 = mask2;
        }
        this.mSpline[0] = CurveFit.get(this.mCurveFitType, timePoint, splineData);
        if (points[0].mPathMotionArc != -1) {
            int size = points.length;
            int[] mode = new int[size];
            double[] time = new double[size];
            double[][] values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 2);
            for (int i9 = 0; i9 < size; i9++) {
                mode[i9] = points[i9].mPathMotionArc;
                time[i9] = points[i9].mTime;
                values[i9][0] = points[i9].mX;
                values[i9][1] = points[i9].mY;
            }
            this.mArcSpline = CurveFit.getArc(mode, time, values);
        }
        float distance = Float.NaN;
        this.mCycleMap = new HashMap<>();
        if (this.mKeyList != null) {
            Iterator<String> it11 = cycleAttributes.iterator();
            while (it11.hasNext()) {
                String attribute4 = it11.next();
                KeyCycleOscillator cycle = KeyCycleOscillator.makeWidgetCycle(attribute4);
                if (cycle != null) {
                    if (cycle.variesByPath() && Float.isNaN(distance)) {
                        distance = getPreCycleDistance();
                    }
                    cycle.setType(attribute4);
                    this.mCycleMap.put(attribute4, cycle);
                }
            }
            Iterator<MotionKey> it12 = this.mKeyList.iterator();
            while (it12.hasNext()) {
                MotionKey key6 = it12.next();
                if (key6 instanceof MotionKeyCycle) {
                    ((MotionKeyCycle) key6).addCycleValues(this.mCycleMap);
                }
            }
            Iterator<KeyCycleOscillator> it13 = this.mCycleMap.values().iterator();
            while (it13.hasNext()) {
                it13.next().setup(distance);
            }
        }
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.mX + " y: " + this.mStartMotionPath.mY + " end: x: " + this.mEndMotionPath.mX + " y: " + this.mEndMotionPath.mY;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds(this.mView.getX(), this.mView.getY(), this.mView.getWidth(), this.mView.getHeight());
    }

    public void setView(MotionWidget view) {
        this.mView = view;
    }

    public MotionWidget getView() {
        return this.mView;
    }

    public void setStart(MotionWidget mw) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mStartMotionPath.setBounds(mw.getX(), mw.getY(), mw.getWidth(), mw.getHeight());
        this.mStartMotionPath.applyParameters(mw);
        this.mStartPoint.setState(mw);
        TypedBundle p = mw.getWidgetFrame().getMotionProperties();
        if (p != null) {
            p.applyDelta(this);
        }
    }

    public void setEnd(MotionWidget mw) {
        this.mEndMotionPath.mTime = 1.0f;
        this.mEndMotionPath.mPosition = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds(mw.getLeft(), mw.getTop(), mw.getWidth(), mw.getHeight());
        this.mEndMotionPath.applyParameters(mw);
        this.mEndPoint.setState(mw);
    }

    public void setStartState(ViewState rect, MotionWidget v, int rotation, int preWidth, int preHeight) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        Rect r = new Rect();
        switch (rotation) {
            case 1:
                int cx = rect.left;
                int cx2 = cx + rect.right;
                int cy = rect.top + rect.bottom;
                r.left = (cy - rect.width()) / 2;
                r.top = preWidth - ((rect.height() + cx2) / 2);
                r.right = r.left + rect.width();
                r.bottom = r.top + rect.height();
                break;
            case 2:
                int cx3 = rect.left + rect.right;
                int cy2 = rect.top + rect.bottom;
                r.left = preHeight - ((rect.width() + cy2) / 2);
                r.top = (cx3 - rect.height()) / 2;
                r.right = r.left + rect.width();
                r.bottom = r.top + rect.height();
                break;
        }
        this.mStartMotionPath.setBounds(r.left, r.top, r.width(), r.height());
        this.mStartPoint.setState(r, v, rotation, rect.rotation);
    }

    void rotate(Rect rect, Rect out, int rotation, int preHeight, int preWidth) {
        switch (rotation) {
            case 1:
                int cx = rect.left;
                int cx2 = cx + rect.right;
                int cy = rect.top + rect.bottom;
                out.left = (cy - rect.width()) / 2;
                out.top = preWidth - ((rect.height() + cx2) / 2);
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 2:
                int cx3 = rect.left;
                int cx4 = cx3 + rect.right;
                int cy2 = rect.top + rect.bottom;
                out.left = preHeight - ((rect.width() + cy2) / 2);
                out.top = (cx4 - rect.height()) / 2;
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 3:
                int cx5 = rect.left;
                int cx6 = cx5 + rect.right;
                int i = rect.top + rect.bottom;
                out.left = ((rect.height() / 2) + rect.top) - (cx6 / 2);
                out.top = preWidth - ((rect.height() + cx6) / 2);
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            case 4:
                int cx7 = rect.left + rect.right;
                int cy3 = rect.bottom + rect.top;
                out.left = preHeight - ((rect.width() + cy3) / 2);
                out.top = (cx7 - rect.height()) / 2;
                out.right = out.left + rect.width();
                out.bottom = out.top + rect.height();
                return;
            default:
                return;
        }
    }

    private static DifferentialInterpolator getInterpolator(int type, String interpolatorString, int id) {
        switch (type) {
            case -1:
                final Easing easing = Easing.getInterpolator(interpolatorString);
                return new DifferentialInterpolator() { // from class: androidx.constraintlayout.core.motion.Motion.1
                    float mX;

                    @Override // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
                    public float getInterpolation(float x) {
                        this.mX = x;
                        return (float) Easing.this.get(x);
                    }

                    @Override // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
                    public float getVelocity() {
                        return (float) Easing.this.getDiff(this.mX);
                    }
                };
            default:
                return null;
        }
    }

    void setBothStates(MotionWidget v) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(v.getX(), v.getY(), v.getWidth(), v.getHeight());
        this.mEndMotionPath.setBounds(v.getX(), v.getY(), v.getWidth(), v.getHeight());
        this.mStartPoint.setState(v);
        this.mEndPoint.setState(v);
    }

    private float getAdjustedPosition(float position, float[] velocity) {
        if (velocity != null) {
            velocity[0] = 1.0f;
        } else {
            float f = this.mStaggerScale;
            if (f != 1.0d) {
                float f2 = this.mStaggerOffset;
                if (position < f2) {
                    position = 0.0f;
                }
                if (position > f2 && position < 1.0d) {
                    position = Math.min((position - f2) * f, 1.0f);
                }
            }
        }
        float adjusted = position;
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float start = 0.0f;
        float end = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths frame = it.next();
            if (frame.mKeyFrameEasing != null) {
                if (frame.mTime < position) {
                    easing = frame.mKeyFrameEasing;
                    start = frame.mTime;
                } else if (Float.isNaN(end)) {
                    end = frame.mTime;
                }
            }
        }
        if (easing != null) {
            if (Float.isNaN(end)) {
                end = 1.0f;
            }
            float offset = (position - start) / (end - start);
            float new_offset = (float) easing.get(offset);
            adjusted = ((end - start) * new_offset) + start;
            if (velocity != null) {
                velocity[0] = (float) easing.getDiff(offset);
            }
        }
        return adjusted;
    }

    void endTrigger(boolean start) {
    }

    public boolean interpolate(MotionWidget child, float globalPosition, long time, KeyCache keyCache) {
        float position;
        float section;
        float position2 = getAdjustedPosition(globalPosition, null);
        int i = this.mQuantizeMotionSteps;
        if (i == -1) {
            position = position2;
        } else {
            float steps = 1.0f / i;
            float jump = ((float) Math.floor(position2 / steps)) * steps;
            float section2 = (position2 % steps) / steps;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                section2 = (this.mQuantizeMotionPhase + section2) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator = this.mQuantizeMotionInterpolator;
            if (differentialInterpolator != null) {
                section = differentialInterpolator.getInterpolation(section2);
            } else {
                section = ((double) section2) > 0.5d ? 1.0f : 0.0f;
            }
            position = (section * steps) + jump;
        }
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet aSpline : hashMap.values()) {
                aSpline.setProperty(child, position);
            }
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getPos(position, this.mInterpolateData);
            this.mSpline[0].getSlope(position, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(position, dArr);
                    this.mArcSpline.getSlope(position, this.mInterpolateVelocity);
                }
            }
            if (!this.mNoMovement) {
                this.mStartMotionPath.setView(position, child, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null);
            }
            if (this.mTransformPivotTarget != -1) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = child.getParent().findViewById(this.mTransformPivotTarget);
                }
                MotionWidget layout = this.mTransformPivotView;
                if (layout != null) {
                    float cy = (layout.getTop() + this.mTransformPivotView.getBottom()) / 2.0f;
                    float cx = (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight()) / 2.0f;
                    if (child.getRight() - child.getLeft() > 0 && child.getBottom() - child.getTop() > 0) {
                        float px = cx - child.getLeft();
                        float py = cy - child.getTop();
                        child.setPivotX(px);
                        child.setPivotY(py);
                    }
                }
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                CurveFit spline = curveFitArr2[i2];
                spline.getPos(position, this.mValuesBuff);
                this.mStartMotionPath.mCustomAttributes.get(this.mAttributeNames[i2 - 1]).setInterpolatedValue(child, this.mValuesBuff);
                i2++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (position <= 0.0f) {
                    child.setVisibility(this.mStartPoint.mVisibility);
                } else if (position < 1.0f) {
                    if (this.mEndPoint.mVisibility != this.mStartPoint.mVisibility) {
                        child.setVisibility(4);
                    }
                } else {
                    child.setVisibility(this.mEndPoint.mVisibility);
                }
            }
            if (this.mKeyTriggers != null) {
                int i3 = 0;
                while (true) {
                    MotionKeyTrigger[] motionKeyTriggerArr = this.mKeyTriggers;
                    if (i3 >= motionKeyTriggerArr.length) {
                        break;
                    }
                    motionKeyTriggerArr[i3].conditionallyFire(position, child);
                    i3++;
                }
            }
        } else {
            float float_l = this.mStartMotionPath.mX + ((this.mEndMotionPath.mX - this.mStartMotionPath.mX) * position);
            float float_t = this.mStartMotionPath.mY + ((this.mEndMotionPath.mY - this.mStartMotionPath.mY) * position);
            float float_width = this.mStartMotionPath.mWidth + ((this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth) * position);
            float float_height = this.mStartMotionPath.mHeight + ((this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight) * position);
            int l = (int) (float_l + 0.5f);
            int t = (int) (float_t + 0.5f);
            int r = (int) (float_l + 0.5f + float_width);
            int b = (int) (0.5f + float_t + float_height);
            int i4 = r - l;
            int i5 = b - t;
            child.layout(l, t, r, b);
        }
        HashMap<String, KeyCycleOscillator> hashMap2 = this.mCycleMap;
        if (hashMap2 != null) {
            for (KeyCycleOscillator osc : hashMap2.values()) {
                if (osc instanceof KeyCycleOscillator.PathRotateSet) {
                    double[] dArr2 = this.mInterpolateVelocity;
                    ((KeyCycleOscillator.PathRotateSet) osc).setPathRotate(child, position, dArr2[0], dArr2[1]);
                } else {
                    osc.setProperty(child, position);
                }
            }
        }
        return false;
    }

    public void getDpDt(float position, float locationX, float locationY, float[] mAnchorDpDt) {
        double[] dArr;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getSlope(position2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(position2, this.mInterpolateData);
            float v = this.mVelocity[0];
            int i = 0;
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * v;
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit == null) {
                this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, dArr, this.mInterpolateData);
                return;
            }
            double[] dArr2 = this.mInterpolateData;
            if (dArr2.length > 0) {
                curveFit.getPos(position2, dArr2);
                this.mArcSpline.getSlope(position2, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                return;
            }
            return;
        }
        float dleft = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
        float dTop = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
        float dWidth = this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth;
        float dHeight = this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight;
        float dRight = dleft + dWidth;
        float dBottom = dTop + dHeight;
        mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + (dRight * locationX);
        mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + (dBottom * locationY);
    }

    void getPostLayoutDvDp(float position, int width, int height, float locationX, float locationY, float[] mAnchorDpDt) {
        VelocityMatrix vmat;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
        SplineSet rotation = hashMap3 == null ? null : hashMap3.get("rotationZ");
        HashMap<String, SplineSet> hashMap4 = this.mAttributesMap;
        SplineSet scale_x = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, SplineSet> hashMap5 = this.mAttributesMap;
        SplineSet scale_y = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, KeyCycleOscillator> hashMap6 = this.mCycleMap;
        KeyCycleOscillator osc_x = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap7 = this.mCycleMap;
        KeyCycleOscillator osc_y = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap8 = this.mCycleMap;
        KeyCycleOscillator osc_r = hashMap8 == null ? null : hashMap8.get("rotationZ");
        HashMap<String, KeyCycleOscillator> hashMap9 = this.mCycleMap;
        KeyCycleOscillator osc_sx = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, KeyCycleOscillator> hashMap10 = this.mCycleMap;
        KeyCycleOscillator osc_sy = hashMap10 != null ? hashMap10.get("scaleY") : null;
        VelocityMatrix vmat2 = new VelocityMatrix();
        vmat2.clear();
        vmat2.setRotationVelocity(rotation, position2);
        vmat2.setTranslationVelocity(trans_x, trans_y, position2);
        vmat2.setScaleVelocity(scale_x, scale_y, position2);
        vmat2.setRotationVelocity(osc_r, position2);
        vmat2.setTranslationVelocity(osc_x, osc_y, position2);
        vmat2.setScaleVelocity(osc_sx, osc_sy, position2);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit == null) {
            if (this.mSpline != null) {
                float position3 = getAdjustedPosition(position2, this.mVelocity);
                this.mSpline[0].getSlope(position3, this.mInterpolateVelocity);
                this.mSpline[0].getPos(position3, this.mInterpolateData);
                float v = this.mVelocity[0];
                int i = 0;
                while (true) {
                    double[] dArr = this.mInterpolateVelocity;
                    if (i >= dArr.length) {
                        this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, dArr, this.mInterpolateData);
                        vmat2.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
                        return;
                    } else {
                        dArr[i] = dArr[i] * v;
                        i++;
                    }
                }
            } else {
                float dleft = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
                float dTop = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
                float dWidth = this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth;
                float dHeight = this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight;
                float dRight = dleft + dWidth;
                float dBottom = dTop + dHeight;
                mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + (dRight * locationX);
                mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + (dBottom * locationY);
                vmat2.clear();
                vmat2.setRotationVelocity(rotation, position2);
                vmat2.setTranslationVelocity(trans_x, trans_y, position2);
                vmat2.setScaleVelocity(scale_x, scale_y, position2);
                vmat2.setRotationVelocity(osc_r, position2);
                vmat2.setTranslationVelocity(osc_x, osc_y, position2);
                vmat2.setScaleVelocity(osc_sx, osc_sy, position2);
                vmat2.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
            }
        } else {
            double[] dArr2 = this.mInterpolateData;
            if (dArr2.length > 0) {
                curveFit.getPos(position2, dArr2);
                this.mArcSpline.getSlope(position2, this.mInterpolateVelocity);
                vmat = vmat2;
                this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            } else {
                vmat = vmat2;
            }
            vmat.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
        }
    }

    public int getDrawPath() {
        int mode = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths keyFrame = it.next();
            mode = Math.max(mode, keyFrame.mDrawPath);
        }
        return Math.max(mode, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int debugMode) {
        this.mStartMotionPath.mDrawPath = debugMode;
    }

    String name() {
        return this.mView.getName();
    }

    void positionKeyframe(MotionWidget view, MotionKeyPosition key, float x, float y, String[] attribute, float[] value) {
        FloatRect start = new FloatRect();
        start.left = this.mStartMotionPath.mX;
        start.top = this.mStartMotionPath.mY;
        start.right = start.left + this.mStartMotionPath.mWidth;
        start.bottom = start.top + this.mStartMotionPath.mHeight;
        FloatRect end = new FloatRect();
        end.left = this.mEndMotionPath.mX;
        end.top = this.mEndMotionPath.mY;
        end.right = end.left + this.mEndMotionPath.mWidth;
        end.bottom = end.top + this.mEndMotionPath.mHeight;
        key.positionAttributes(view, start, end, x, y, attribute, value);
    }

    public int getKeyFramePositions(int[] type, float[] pos) {
        int i = 0;
        int count = 0;
        Iterator<MotionKey> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            MotionKey key = it.next();
            int i2 = i + 1;
            type[i] = key.mFramePosition + (key.mType * 1000);
            float time = key.mFramePosition / 100.0f;
            this.mSpline[0].getPos(time, this.mInterpolateData);
            this.mStartMotionPath.getCenter(time, this.mInterpolateVariables, this.mInterpolateData, pos, count);
            count += 2;
            i = i2;
        }
        return i;
    }

    public int getKeyFrameInfo(int type, int[] info) {
        int count = 0;
        int cursor = 0;
        float[] pos = new float[2];
        Iterator<MotionKey> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            MotionKey key = it.next();
            if (key.mType == type || type != -1) {
                int len = cursor;
                info[cursor] = 0;
                int cursor2 = cursor + 1;
                info[cursor2] = key.mType;
                int cursor3 = cursor2 + 1;
                info[cursor3] = key.mFramePosition;
                float time = key.mFramePosition / 100.0f;
                this.mSpline[0].getPos(time, this.mInterpolateData);
                this.mStartMotionPath.getCenter(time, this.mInterpolateVariables, this.mInterpolateData, pos, 0);
                int cursor4 = cursor3 + 1;
                info[cursor4] = Float.floatToIntBits(pos[0]);
                int cursor5 = cursor4 + 1;
                info[cursor5] = Float.floatToIntBits(pos[1]);
                if (key instanceof MotionKeyPosition) {
                    MotionKeyPosition kp = (MotionKeyPosition) key;
                    int cursor6 = cursor5 + 1;
                    info[cursor6] = kp.mPositionType;
                    int cursor7 = cursor6 + 1;
                    info[cursor7] = Float.floatToIntBits(kp.mPercentX);
                    cursor5 = cursor7 + 1;
                    info[cursor5] = Float.floatToIntBits(kp.mPercentY);
                }
                cursor = cursor5 + 1;
                info[len] = cursor - len;
                count++;
            }
        }
        return count;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int id, int value) {
        switch (id) {
            case 509:
                setPathMotionArc(value);
                return true;
            case TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS /* 610 */:
                this.mQuantizeMotionSteps = value;
                return true;
            case TypedValues.TransitionType.TYPE_AUTO_TRANSITION /* 704 */:
                return true;
            default:
                return false;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int id, float value) {
        if (602 == id) {
            this.mQuantizeMotionPhase = value;
            return true;
        }
        if (600 == id) {
            this.mMotionStagger = value;
            return true;
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int id, String value) {
        if (705 == id || 611 == id) {
            this.mQuantizeMotionInterpolator = getInterpolator(-1, value, 0);
            return true;
        }
        if (605 != id) {
            return false;
        }
        this.mStartMotionPath.mAnimateRelativeTo = value;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int id, boolean value) {
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String name) {
        return 0;
    }

    public void setStaggerScale(float staggerScale) {
        this.mStaggerScale = staggerScale;
    }

    public void setStaggerOffset(float staggerOffset) {
        this.mStaggerOffset = staggerOffset;
    }

    public float getMotionStagger() {
        return this.mMotionStagger;
    }

    public void setIdString(String stringId) {
        this.mId = stringId;
        this.mStartMotionPath.mId = stringId;
    }
}
