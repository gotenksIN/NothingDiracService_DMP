package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class LogJson extends ConstraintHelper {
    public static final int LOG_API = 4;
    public static final int LOG_DELAYED = 2;
    public static final int LOG_LAYOUT = 3;
    public static final int LOG_PERIODIC = 1;
    private static final String TAG = "JSON5";
    private int mDelay;
    private boolean mLogConsole;
    private String mLogToFile;
    private int mMode;
    private boolean mPeriodic;

    public LogJson(Context context) {
        super(context);
        this.mDelay = 1000;
        this.mMode = 2;
        this.mLogToFile = null;
        this.mLogConsole = true;
        this.mPeriodic = false;
    }

    public LogJson(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDelay = 1000;
        this.mMode = 2;
        this.mLogToFile = null;
        this.mLogConsole = true;
        this.mPeriodic = false;
        initLogJson(attrs);
    }

    public LogJson(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDelay = 1000;
        this.mMode = 2;
        this.mLogToFile = null;
        this.mLogConsole = true;
        this.mPeriodic = false;
        initLogJson(attrs);
    }

    private void initLogJson(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LogJson);
            int count = a.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.LogJson_logJsonDelay) {
                    this.mDelay = a.getInt(attr, this.mDelay);
                } else if (attr == R.styleable.LogJson_logJsonMode) {
                    this.mMode = a.getInt(attr, this.mMode);
                } else if (attr == R.styleable.LogJson_logJsonTo) {
                    TypedValue v = a.peekValue(attr);
                    if (v.type == 3) {
                        this.mLogToFile = a.getString(attr);
                    } else {
                        int value = a.getInt(attr, 0);
                        this.mLogConsole = value == 2;
                    }
                }
            }
            a.recycle();
        }
        setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        switch (this.mMode) {
            case 1:
                this.mPeriodic = true;
                postDelayed(new LogJson$$ExternalSyntheticLambda0(this), this.mDelay);
                return;
            case 2:
                postDelayed(new Runnable() { // from class: androidx.constraintlayout.helper.widget.LogJson$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        LogJson.this.writeLog();
                    }
                }, this.mDelay);
                return;
            case 3:
                ConstraintLayout cl = (ConstraintLayout) getParent();
                cl.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.constraintlayout.helper.widget.LogJson$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnLayoutChangeListener
                    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        LogJson.this.m14xb6945733(view, i, i2, i3, i4, i5, i6, i7, i8);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* renamed from: lambda$onAttachedToWindow$0$androidx-constraintlayout-helper-widget-LogJson */
    public /* synthetic */ void m14xb6945733(View v, int a, int b, int c, int d, int e, int f, int g, int h) {
        logOnLayout();
    }

    private void logOnLayout() {
        if (this.mMode == 3) {
            writeLog();
        }
    }

    public void setDelay(int duration) {
        this.mDelay = duration;
    }

    public void periodicStart() {
        if (this.mPeriodic) {
            return;
        }
        this.mPeriodic = true;
        postDelayed(new LogJson$$ExternalSyntheticLambda0(this), this.mDelay);
    }

    public void periodicStop() {
        this.mPeriodic = false;
    }

    public void periodic() {
        if (this.mPeriodic) {
            writeLog();
            postDelayed(new LogJson$$ExternalSyntheticLambda0(this), this.mDelay);
        }
    }

    public void writeLog() {
        String str = asString((ConstraintLayout) getParent());
        String str2 = this.mLogToFile;
        if (str2 == null) {
            if (this.mLogConsole) {
                System.out.println(str);
                return;
            } else {
                logBigString(str);
                return;
            }
        }
        String name = toFile(str, str2);
        Log.v("JSON", "\"" + name + "\" written!");
    }

    private static String toFile(String str, String fileName) {
        if (!fileName.endsWith(".json5")) {
            fileName = fileName + ".json5";
        }
        try {
            File down = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(down, fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(str.getBytes());
            outputStream.close();
            return file.getCanonicalPath();
        } catch (IOException e) {
            return e.toString();
        }
    }

    private void logBigString(String str) {
        int len = str.length();
        int i = 0;
        while (i < len) {
            int k = str.indexOf("\n", i);
            if (k == -1) {
                Log.v(TAG, str.substring(i));
                return;
            } else {
                Log.v(TAG, str.substring(i, k));
                i = k + 1;
            }
        }
    }

    private static String asString(ConstraintLayout constraintLayout) {
        JsonWriter c = new JsonWriter();
        return c.constraintLayoutToJson(constraintLayout);
    }

    /* loaded from: classes.dex */
    public static class JsonWriter {
        private static final String INDENT = "    ";
        private static final String SMALL_INDENT = "  ";
        public static final int UNSET = -1;
        Context mContext;
        ConstraintSet mSet;
        Writer mWriter;
        private static final String LOG_JSON = LogJson.class.getSimpleName();
        private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        int mUnknownCount = 0;
        final String mLEFT = "left";
        final String mRIGHT = "right";
        final String mBASELINE = "baseline";
        final String mBOTTOM = "bottom";
        final String mTOP = "top";
        final String mSTART = "start";
        final String mEND = "end";
        HashMap<Integer, String> mIdMap = new HashMap<>();
        HashMap<Integer, String> mNames = new HashMap<>();

        private static int generateViewId() {
            AtomicInteger atomicInteger;
            int result;
            int newValue;
            do {
                atomicInteger = sNextGeneratedId;
                result = atomicInteger.get();
                newValue = result + 1;
                if (newValue > 16777215) {
                    newValue = 1;
                }
            } while (!atomicInteger.compareAndSet(result, newValue));
            return result;
        }

        /* loaded from: classes.dex */
        public static class JellyBean {
            private JellyBean() {
            }

            static int generateViewId() {
                return View.generateViewId();
            }
        }

        String constraintLayoutToJson(ConstraintLayout constraintLayout) {
            StringWriter writer = new StringWriter();
            int count = constraintLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = constraintLayout.getChildAt(i);
                String name = v.getClass().getSimpleName();
                int id = v.getId();
                if (id == -1) {
                    int id2 = JellyBean.generateViewId();
                    v.setId(id2);
                    if (!LOG_JSON.equals(name)) {
                        name = "noid_" + name;
                    }
                    this.mNames.put(Integer.valueOf(id2), name);
                } else if (LOG_JSON.equals(name)) {
                    this.mNames.put(Integer.valueOf(id), name);
                }
            }
            writer.append("{\n");
            writeWidgets(writer, constraintLayout);
            writer.append("  ConstraintSet:{\n");
            ConstraintSet set = new ConstraintSet();
            set.clone(constraintLayout);
            try {
                writer.append((CharSequence) ((constraintLayout.getId() == -1 ? "cset" : Debug.getName(constraintLayout)) + ":"));
                setup(writer, set, constraintLayout);
                writeLayout();
                writer.append("\n");
                writer.append("  }\n");
                writer.append("}\n");
                return writer.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeWidgets(StringWriter writer, ConstraintLayout constraintLayout) {
            String name;
            int count;
            String w;
            String h;
            JsonWriter jsonWriter = this;
            writer.append("Widgets:{\n");
            int count2 = constraintLayout.getChildCount();
            int i = -1;
            while (i < count2) {
                View v = i == -1 ? constraintLayout : constraintLayout.getChildAt(i);
                int id = v.getId();
                if (LOG_JSON.equals(v.getClass().getSimpleName())) {
                    count = count2;
                } else {
                    if (jsonWriter.mNames.containsKey(Integer.valueOf(id))) {
                        name = jsonWriter.mNames.get(Integer.valueOf(id));
                    } else {
                        name = i == -1 ? "parent" : Debug.getName(v);
                    }
                    String cname = v.getClass().getSimpleName();
                    String bounds = ", bounds: [" + v.getLeft() + ", " + v.getTop() + ", " + v.getRight() + ", " + v.getBottom() + "]},\n";
                    writer.append((CharSequence) (SMALL_INDENT + name + ": { "));
                    if (i == -1) {
                        writer.append((CharSequence) ("type: '" + v.getClass().getSimpleName() + "' , "));
                        try {
                            ViewGroup.LayoutParams p = v.getLayoutParams();
                            count = count2;
                            if (p.width == -1) {
                                w = "'MATCH_PARENT'";
                            } else {
                                w = p.width == -2 ? "'WRAP_CONTENT'" : p.width + "";
                            }
                            writer.append((CharSequence) ("width: " + w + ", "));
                            if (p.height == -1) {
                                h = "'MATCH_PARENT'";
                            } else {
                                try {
                                    h = p.height == -2 ? "'WRAP_CONTENT'" : p.height + "";
                                } catch (Exception e) {
                                }
                            }
                            writer.append("height: ").append((CharSequence) h);
                        } catch (Exception e2) {
                            count = count2;
                        }
                    } else {
                        count = count2;
                        if (cname.contains("Text")) {
                            if (v instanceof TextView) {
                                writer.append((CharSequence) ("type: 'Text', label: '" + escape(((TextView) v).getText().toString()) + "'"));
                            } else {
                                writer.append("type: 'Text' },\n");
                            }
                        } else if (cname.contains("Button")) {
                            if (v instanceof Button) {
                                writer.append((CharSequence) ("type: 'Button', label: '" + ((Object) ((Button) v).getText()) + "'"));
                            } else {
                                writer.append("type: 'Button'");
                            }
                        } else if (cname.contains("Image")) {
                            writer.append("type: 'Image'");
                        } else if (!cname.contains("View")) {
                            writer.append((CharSequence) ("type: '" + v.getClass().getSimpleName() + "'"));
                        } else {
                            writer.append("type: 'Box'");
                        }
                    }
                    writer.append((CharSequence) bounds);
                }
                i++;
                jsonWriter = this;
                count2 = count;
            }
            writer.append("},\n");
        }

        private static String escape(String str) {
            return str.replaceAll("'", "\\'");
        }

        JsonWriter() {
        }

        void setup(Writer writer, ConstraintSet set, ConstraintLayout layout) throws IOException {
            this.mWriter = writer;
            this.mContext = layout.getContext();
            this.mSet = set;
            set.getConstraint(2);
        }

        private int[] getIDs() {
            return this.mSet.getKnownIds();
        }

        private ConstraintSet.Constraint getConstraint(int id) {
            return this.mSet.getConstraint(id);
        }

        private void writeLayout() throws IOException {
            this.mWriter.write("{\n");
            for (int i : getIDs()) {
                Integer id = Integer.valueOf(i);
                ConstraintSet.Constraint c = getConstraint(id.intValue());
                String idName = getSimpleName(id.intValue());
                if (!LOG_JSON.equals(idName)) {
                    this.mWriter.write(SMALL_INDENT + idName + ":{\n");
                    ConstraintSet.Layout l = c.layout;
                    String str = "";
                    if (l.mReferenceIds != null) {
                        StringBuilder ref = new StringBuilder("type: '_" + idName + "_' , contains: [");
                        int r = 0;
                        while (r < l.mReferenceIds.length) {
                            int rid = l.mReferenceIds[r];
                            ref.append(r == 0 ? "" : ", ").append(getName(rid));
                            r++;
                        }
                        this.mWriter.write(((Object) ref) + "]\n");
                    }
                    if (l.mReferenceIdString != null) {
                        StringBuilder ref2 = new StringBuilder("  type: '???' , contains: [");
                        String[] rids = l.mReferenceIdString.split(",");
                        int r2 = 0;
                        while (r2 < rids.length) {
                            String rid2 = rids[r2];
                            ref2.append(r2 == 0 ? str : ", ").append("`").append(rid2).append("`");
                            r2++;
                            str = str;
                        }
                        this.mWriter.write(((Object) ref2) + "]\n");
                    }
                    writeDimension("height", l.mHeight, l.heightDefault, l.heightPercent, l.heightMin, l.heightMax, l.constrainedHeight);
                    writeDimension("width", l.mWidth, l.widthDefault, l.widthPercent, l.widthMin, l.widthMax, l.constrainedWidth);
                    writeConstraint("left", l.leftToLeft, "left", l.leftMargin, l.goneLeftMargin);
                    writeConstraint("left", l.leftToRight, "right", l.leftMargin, l.goneLeftMargin);
                    writeConstraint("right", l.rightToLeft, "left", l.rightMargin, l.goneRightMargin);
                    writeConstraint("right", l.rightToRight, "right", l.rightMargin, l.goneRightMargin);
                    writeConstraint("baseline", l.baselineToBaseline, "baseline", -1, l.goneBaselineMargin);
                    writeConstraint("baseline", l.baselineToTop, "top", -1, l.goneBaselineMargin);
                    writeConstraint("baseline", l.baselineToBottom, "bottom", -1, l.goneBaselineMargin);
                    writeConstraint("top", l.topToBottom, "bottom", l.topMargin, l.goneTopMargin);
                    writeConstraint("top", l.topToTop, "top", l.topMargin, l.goneTopMargin);
                    writeConstraint("bottom", l.bottomToBottom, "bottom", l.bottomMargin, l.goneBottomMargin);
                    writeConstraint("bottom", l.bottomToTop, "top", l.bottomMargin, l.goneBottomMargin);
                    writeConstraint("start", l.startToStart, "start", l.startMargin, l.goneStartMargin);
                    writeConstraint("start", l.startToEnd, "end", l.startMargin, l.goneStartMargin);
                    writeConstraint("end", l.endToStart, "start", l.endMargin, l.goneEndMargin);
                    writeConstraint("end", l.endToEnd, "end", l.endMargin, l.goneEndMargin);
                    writeVariable("horizontalBias", l.horizontalBias, 0.5f);
                    writeVariable("verticalBias", l.verticalBias, 0.5f);
                    writeCircle(l.circleConstraint, l.circleAngle, l.circleRadius);
                    writeGuideline(l.orientation, l.guideBegin, l.guideEnd, l.guidePercent);
                    writeVariable("dimensionRatio", l.dimensionRatio);
                    writeVariable("barrierMargin", l.mBarrierMargin);
                    writeVariable("type", l.mHelperType);
                    writeVariable("ReferenceId", l.mReferenceIdString);
                    writeVariable("mBarrierAllowsGoneWidgets", l.mBarrierAllowsGoneWidgets, true);
                    writeVariable("WrapBehavior", l.mWrapBehavior);
                    writeVariable("verticalWeight", l.verticalWeight);
                    writeVariable("horizontalWeight", l.horizontalWeight);
                    writeVariable("horizontalChainStyle", l.horizontalChainStyle);
                    writeVariable("verticalChainStyle", l.verticalChainStyle);
                    writeVariable("barrierDirection", l.mBarrierDirection);
                    if (l.mReferenceIds != null) {
                        writeVariable("ReferenceIds", l.mReferenceIds);
                    }
                    writeTransform(c.transform);
                    writeCustom(c.mCustomConstraints);
                    this.mWriter.write("  },\n");
                }
            }
            this.mWriter.write("},\n");
        }

        private void writeTransform(ConstraintSet.Transform transform) throws IOException {
            if (transform.applyElevation) {
                writeVariable("elevation", transform.elevation);
            }
            writeVariable("rotationX", transform.rotationX, 0.0f);
            writeVariable("rotationY", transform.rotationY, 0.0f);
            writeVariable("rotationZ", transform.rotation, 0.0f);
            writeVariable("scaleX", transform.scaleX, 1.0f);
            writeVariable("scaleY", transform.scaleY, 1.0f);
            writeVariable("translationX", transform.translationX, 0.0f);
            writeVariable("translationY", transform.translationY, 0.0f);
            writeVariable("translationZ", transform.translationZ, 0.0f);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0055. Please report as an issue. */
        private void writeCustom(HashMap<String, ConstraintAttribute> cset) throws IOException {
            if (cset != null && cset.size() > 0) {
                this.mWriter.write("    custom: {\n");
                for (String s : cset.keySet()) {
                    ConstraintAttribute attr = cset.get(s);
                    if (attr != null) {
                        String custom = "      " + attr.getName() + ": ";
                        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[attr.getType().ordinal()]) {
                            case 1:
                                custom = custom + attr.getIntegerValue();
                                break;
                            case 2:
                                custom = custom + colorString(attr.getColorValue());
                                break;
                            case 3:
                                custom = custom + attr.getFloatValue();
                                break;
                            case 4:
                                custom = custom + "'" + attr.getStringValue() + "'";
                                break;
                            case 5:
                                custom = custom + attr.getFloatValue();
                                break;
                            case 6:
                            case 7:
                            case 8:
                                custom = null;
                                break;
                        }
                        if (custom != null) {
                            this.mWriter.write(custom + ",\n");
                        }
                    }
                }
                this.mWriter.write("     } \n");
            }
        }

        private static String colorString(int v) {
            String str = "00000000" + Integer.toHexString(v);
            return "#" + str.substring(str.length() - 8);
        }

        private void writeGuideline(int orientation, int guideBegin, int guideEnd, float guidePercent) throws IOException {
            writeVariable("orientation", orientation);
            writeVariable("guideBegin", guideBegin);
            writeVariable("guideEnd", guideEnd);
            writeVariable("guidePercent", guidePercent);
        }

        private void writeDimension(String dimString, int dim, int dimDefault, float dimPercent, int dimMin, int dimMax, boolean unusedConstrainedDim) throws IOException {
            if (dim == 0) {
                if (dimMax != -1 || dimMin != -1) {
                    String s = "-----";
                    switch (dimDefault) {
                        case 0:
                            s = INDENT + dimString + ": {value:'spread'";
                            break;
                        case 1:
                            s = INDENT + dimString + ": {value:'wrap'";
                            break;
                        case 2:
                            s = INDENT + dimString + ": {value: '" + dimPercent + "%'";
                            break;
                    }
                    if (dimMax != -1) {
                        s = s + ", max: " + dimMax;
                    }
                    if (dimMax != -1) {
                        s = s + ", min: " + dimMin;
                    }
                    this.mWriter.write(s + "},\n");
                    return;
                }
                switch (dimDefault) {
                    case 0:
                    default:
                        return;
                    case 1:
                        this.mWriter.write(INDENT + dimString + ": '???????????',\n");
                        return;
                    case 2:
                        this.mWriter.write(INDENT + dimString + ": '" + dimPercent + "%',\n");
                        return;
                }
            }
            if (dim == -2) {
                this.mWriter.write(INDENT + dimString + ": 'wrap',\n");
            } else if (dim == -1) {
                this.mWriter.write(INDENT + dimString + ": 'parent',\n");
            } else {
                this.mWriter.write(INDENT + dimString + ": " + dim + ",\n");
            }
        }

        private String getSimpleName(int id) {
            if (this.mIdMap.containsKey(Integer.valueOf(id))) {
                return "" + this.mIdMap.get(Integer.valueOf(id));
            }
            if (id == 0) {
                return "parent";
            }
            String name = lookup(id);
            this.mIdMap.put(Integer.valueOf(id), name);
            return "" + name + "";
        }

        private String getName(int id) {
            return "'" + getSimpleName(id) + "'";
        }

        private String lookup(int id) {
            try {
                if (this.mNames.containsKey(Integer.valueOf(id))) {
                    return this.mNames.get(Integer.valueOf(id));
                }
                if (id == -1) {
                    StringBuilder append = new StringBuilder().append(EnvironmentCompat.MEDIA_UNKNOWN);
                    int i = this.mUnknownCount + 1;
                    this.mUnknownCount = i;
                    return append.append(i).toString();
                }
                return this.mContext.getResources().getResourceEntryName(id);
            } catch (Exception e) {
                StringBuilder append2 = new StringBuilder().append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i2 = this.mUnknownCount + 1;
                this.mUnknownCount = i2;
                return append2.append(i2).toString();
            }
        }

        private void writeConstraint(String my, int constraint, String other, int margin, int goneMargin) throws IOException {
            if (constraint == -1) {
                return;
            }
            this.mWriter.write(INDENT + my);
            this.mWriter.write(":[");
            this.mWriter.write(getName(constraint));
            this.mWriter.write(", ");
            this.mWriter.write("'" + other + "'");
            if (margin != 0 || goneMargin != Integer.MIN_VALUE) {
                this.mWriter.write(", " + margin);
                if (goneMargin != Integer.MIN_VALUE) {
                    this.mWriter.write(", " + goneMargin);
                }
            }
            this.mWriter.write("],\n");
        }

        private void writeCircle(int circleConstraint, float circleAngle, int circleRadius) throws IOException {
            if (circleConstraint == -1) {
                return;
            }
            this.mWriter.write("    circle");
            this.mWriter.write(":[");
            this.mWriter.write(getName(circleConstraint));
            this.mWriter.write(", " + circleAngle);
            this.mWriter.write(circleRadius + "],\n");
        }

        private void writeVariable(String name, int value) throws IOException {
            if (value == 0 || value == -1) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": " + value);
            this.mWriter.write(",\n");
        }

        private void writeVariable(String name, float value) throws IOException {
            if (value == -1.0f) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": " + value);
            this.mWriter.write(",\n");
        }

        private void writeVariable(String name, float value, float def) throws IOException {
            if (value == def) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": " + value);
            this.mWriter.write(",\n");
        }

        private void writeVariable(String name, boolean value, boolean def) throws IOException {
            if (value == def) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": " + value);
            this.mWriter.write(",\n");
        }

        private void writeVariable(String name, int[] value) throws IOException {
            if (value == null) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": ");
            int i = 0;
            while (i < value.length) {
                this.mWriter.write((i == 0 ? "[" : ", ") + getName(value[i]));
                i++;
            }
            this.mWriter.write("],\n");
        }

        private void writeVariable(String name, String value) throws IOException {
            if (value == null) {
                return;
            }
            this.mWriter.write(INDENT + name);
            this.mWriter.write(": '" + value);
            this.mWriter.write("',\n");
        }
    }

    /* renamed from: androidx.constraintlayout.helper.widget.LogJson$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType;

        static {
            int[] iArr = new int[ConstraintAttribute.AttributeType.values().length];
            $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType = iArr;
            try {
                iArr[ConstraintAttribute.AttributeType.INT_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.COLOR_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.FLOAT_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.STRING_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.DIMENSION_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.REFERENCE_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.COLOR_DRAWABLE_TYPE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$widget$ConstraintAttribute$AttributeType[ConstraintAttribute.AttributeType.BOOLEAN_TYPE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }
}
