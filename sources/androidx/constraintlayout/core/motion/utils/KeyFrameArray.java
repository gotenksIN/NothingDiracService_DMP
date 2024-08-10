package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import java.util.Arrays;

/* loaded from: classes.dex */
public class KeyFrameArray {

    /* loaded from: classes.dex */
    public static class CustomArray {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys = new int[101];
        CustomAttribute[] mValues = new CustomAttribute[101];

        public CustomArray() {
            clear();
        }

        public void clear() {
            Arrays.fill(this.mKeys, EMPTY);
            Arrays.fill(this.mValues, (Object) null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            int i = 0;
            while (i < this.mCount) {
                System.out.print((i == 0 ? "" : ", ") + valueAt(i));
                i++;
            }
            System.out.println("]");
        }

        public int size() {
            return this.mCount;
        }

        public CustomAttribute valueAt(int i) {
            return this.mValues[this.mKeys[i]];
        }

        public int keyAt(int i) {
            return this.mKeys[i];
        }

        public void append(int position, CustomAttribute value) {
            if (this.mValues[position] != null) {
                remove(position);
            }
            this.mValues[position] = value;
            int[] iArr = this.mKeys;
            int i = this.mCount;
            this.mCount = i + 1;
            iArr[i] = position;
            Arrays.sort(iArr);
        }

        public void remove(int position) {
            this.mValues[position] = null;
            int j = 0;
            int i = 0;
            while (true) {
                int i2 = this.mCount;
                if (i < i2) {
                    int[] iArr = this.mKeys;
                    if (position == iArr[i]) {
                        iArr[i] = EMPTY;
                        j++;
                    }
                    if (i != j) {
                        iArr[i] = iArr[j];
                    }
                    j++;
                    i++;
                } else {
                    this.mCount = i2 - 1;
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class CustomVar {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys = new int[101];
        CustomVariable[] mValues = new CustomVariable[101];

        public CustomVar() {
            clear();
        }

        public void clear() {
            Arrays.fill(this.mKeys, EMPTY);
            Arrays.fill(this.mValues, (Object) null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            int i = 0;
            while (i < this.mCount) {
                System.out.print((i == 0 ? "" : ", ") + valueAt(i));
                i++;
            }
            System.out.println("]");
        }

        public int size() {
            return this.mCount;
        }

        public CustomVariable valueAt(int i) {
            return this.mValues[this.mKeys[i]];
        }

        public int keyAt(int i) {
            return this.mKeys[i];
        }

        public void append(int position, CustomVariable value) {
            if (this.mValues[position] != null) {
                remove(position);
            }
            this.mValues[position] = value;
            int[] iArr = this.mKeys;
            int i = this.mCount;
            this.mCount = i + 1;
            iArr[i] = position;
            Arrays.sort(iArr);
        }

        public void remove(int position) {
            this.mValues[position] = null;
            int j = 0;
            int i = 0;
            while (true) {
                int i2 = this.mCount;
                if (i < i2) {
                    int[] iArr = this.mKeys;
                    if (position == iArr[i]) {
                        iArr[i] = EMPTY;
                        j++;
                    }
                    if (i != j) {
                        iArr[i] = iArr[j];
                    }
                    j++;
                    i++;
                } else {
                    this.mCount = i2 - 1;
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class FloatArray {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys = new int[101];
        float[][] mValues = new float[101];

        /* JADX INFO: Access modifiers changed from: package-private */
        public FloatArray() {
            clear();
        }

        public void clear() {
            Arrays.fill(this.mKeys, EMPTY);
            Arrays.fill(this.mValues, (Object) null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            int i = 0;
            while (i < this.mCount) {
                System.out.print((i == 0 ? "" : ", ") + Arrays.toString(valueAt(i)));
                i++;
            }
            System.out.println("]");
        }

        public int size() {
            return this.mCount;
        }

        public float[] valueAt(int i) {
            return this.mValues[this.mKeys[i]];
        }

        public int keyAt(int i) {
            return this.mKeys[i];
        }

        public void append(int position, float[] value) {
            if (this.mValues[position] != null) {
                remove(position);
            }
            this.mValues[position] = value;
            int[] iArr = this.mKeys;
            int i = this.mCount;
            this.mCount = i + 1;
            iArr[i] = position;
            Arrays.sort(iArr);
        }

        public void remove(int position) {
            this.mValues[position] = null;
            int j = 0;
            int i = 0;
            while (true) {
                int i2 = this.mCount;
                if (i < i2) {
                    int[] iArr = this.mKeys;
                    if (position == iArr[i]) {
                        iArr[i] = EMPTY;
                        j++;
                    }
                    if (i != j) {
                        iArr[i] = iArr[j];
                    }
                    j++;
                    i++;
                } else {
                    this.mCount = i2 - 1;
                    return;
                }
            }
        }
    }
}
