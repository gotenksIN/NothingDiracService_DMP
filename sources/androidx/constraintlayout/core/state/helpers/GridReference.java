package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.utils.GridCore;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GridReference extends HelperReference {
    private static final String SPANS_RESPECT_WIDGET_ORDER = "spansrespectwidgetorder";
    private static final String SUB_GRID_BY_COL_ROW = "subgridbycolrow";
    private String mColumnWeights;
    private int mColumnsSet;
    private int[] mFlags;
    private GridCore mGrid;
    private float mHorizontalGaps;
    private int mOrientation;
    private int mPaddingBottom;
    private int mPaddingEnd;
    private int mPaddingStart;
    private int mPaddingTop;
    private String mRowWeights;
    private int mRowsSet;
    private String mSkips;
    private String mSpans;
    private float mVerticalGaps;

    public GridReference(State state, State.Helper type) {
        super(state, type);
        this.mPaddingStart = 0;
        this.mPaddingEnd = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        if (type == State.Helper.ROW) {
            this.mRowsSet = 1;
        } else if (type == State.Helper.COLUMN) {
            this.mColumnsSet = 1;
        }
    }

    public int getPaddingStart() {
        return this.mPaddingStart;
    }

    public void setPaddingStart(int paddingStart) {
        this.mPaddingStart = paddingStart;
    }

    public int getPaddingEnd() {
        return this.mPaddingEnd;
    }

    public void setPaddingEnd(int paddingEnd) {
        this.mPaddingEnd = paddingEnd;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.mPaddingTop = paddingTop;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.mPaddingBottom = paddingBottom;
    }

    public int[] getFlags() {
        return this.mFlags;
    }

    public void setFlags(int[] flags) {
        this.mFlags = flags;
    }

    public void setFlags(String flags) {
        char c;
        if (flags.length() == 0) {
            return;
        }
        String[] strArr = flags.split("\\|");
        ArrayList<Integer> flagList = new ArrayList<>();
        for (String flag : strArr) {
            String lowerCase = flag.toLowerCase();
            switch (lowerCase.hashCode()) {
                case -578405641:
                    if (lowerCase.equals(SUB_GRID_BY_COL_ROW)) {
                        c = 0;
                        break;
                    }
                    break;
                case 2144989229:
                    if (lowerCase.equals(SPANS_RESPECT_WIDGET_ORDER)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                    flagList.add(0);
                    break;
                case 1:
                    flagList.add(1);
                    break;
            }
        }
        int[] flagArr = new int[flagList.size()];
        int i = 0;
        Iterator<Integer> it = flagList.iterator();
        while (it.hasNext()) {
            int flag2 = it.next().intValue();
            flagArr[i] = flag2;
            i++;
        }
        this.mFlags = flagArr;
    }

    public int getRowsSet() {
        return this.mRowsSet;
    }

    public void setRowsSet(int rowsSet) {
        if (super.getType() == State.Helper.COLUMN) {
            return;
        }
        this.mRowsSet = rowsSet;
    }

    public int getColumnsSet() {
        return this.mColumnsSet;
    }

    public void setColumnsSet(int columnsSet) {
        if (super.getType() == State.Helper.ROW) {
            return;
        }
        this.mColumnsSet = columnsSet;
    }

    public float getHorizontalGaps() {
        return this.mHorizontalGaps;
    }

    public void setHorizontalGaps(float horizontalGaps) {
        this.mHorizontalGaps = horizontalGaps;
    }

    public float getVerticalGaps() {
        return this.mVerticalGaps;
    }

    public void setVerticalGaps(float verticalGaps) {
        this.mVerticalGaps = verticalGaps;
    }

    public String getRowWeights() {
        return this.mRowWeights;
    }

    public void setRowWeights(String rowWeights) {
        this.mRowWeights = rowWeights;
    }

    public String getColumnWeights() {
        return this.mColumnWeights;
    }

    public void setColumnWeights(String columnWeights) {
        this.mColumnWeights = columnWeights;
    }

    public String getSpans() {
        return this.mSpans;
    }

    public void setSpans(String spans) {
        this.mSpans = spans;
    }

    public String getSkips() {
        return this.mSkips;
    }

    public void setSkips(String skips) {
        this.mSkips = skips;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mGrid == null) {
            this.mGrid = new GridCore();
        }
        return this.mGrid;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference
    public void setHelperWidget(HelperWidget widget) {
        if (widget instanceof GridCore) {
            this.mGrid = (GridCore) widget;
        } else {
            this.mGrid = null;
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        getHelperWidget();
        this.mGrid.setOrientation(this.mOrientation);
        int i = this.mRowsSet;
        if (i != 0) {
            this.mGrid.setRows(i);
        }
        int i2 = this.mColumnsSet;
        if (i2 != 0) {
            this.mGrid.setColumns(i2);
        }
        float f = this.mHorizontalGaps;
        if (f != 0.0f) {
            this.mGrid.setHorizontalGaps(f);
        }
        float f2 = this.mVerticalGaps;
        if (f2 != 0.0f) {
            this.mGrid.setVerticalGaps(f2);
        }
        String str = this.mRowWeights;
        if (str != null && !str.equals("")) {
            this.mGrid.setRowWeights(this.mRowWeights);
        }
        String str2 = this.mColumnWeights;
        if (str2 != null && !str2.equals("")) {
            this.mGrid.setColumnWeights(this.mColumnWeights);
        }
        String str3 = this.mSpans;
        if (str3 != null && !str3.equals("")) {
            this.mGrid.setSpans(this.mSpans);
        }
        String str4 = this.mSkips;
        if (str4 != null && !str4.equals("")) {
            this.mGrid.setSkips(this.mSkips);
        }
        int[] iArr = this.mFlags;
        if (iArr != null && iArr.length > 0) {
            this.mGrid.setFlags(iArr);
        }
        applyBase();
    }
}
