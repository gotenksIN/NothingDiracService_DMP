package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Constraint;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Helper {
    protected static final Map<Constraint.Side, String> sideMap;
    protected static final Map<Type, String> typeMap;
    protected String config;
    protected Map<String, String> configMap;
    protected final String name;
    protected HelperType type;

    /* loaded from: classes.dex */
    public enum Type {
        VERTICAL_GUIDELINE,
        HORIZONTAL_GUIDELINE,
        VERTICAL_CHAIN,
        HORIZONTAL_CHAIN,
        BARRIER
    }

    static {
        HashMap hashMap = new HashMap();
        sideMap = hashMap;
        hashMap.put(Constraint.Side.LEFT, "'left'");
        hashMap.put(Constraint.Side.RIGHT, "'right'");
        hashMap.put(Constraint.Side.TOP, "'top'");
        hashMap.put(Constraint.Side.BOTTOM, "'bottom'");
        hashMap.put(Constraint.Side.START, "'start'");
        hashMap.put(Constraint.Side.END, "'end'");
        hashMap.put(Constraint.Side.BASELINE, "'baseline'");
        HashMap hashMap2 = new HashMap();
        typeMap = hashMap2;
        hashMap2.put(Type.VERTICAL_GUIDELINE, "vGuideline");
        hashMap2.put(Type.HORIZONTAL_GUIDELINE, "hGuideline");
        hashMap2.put(Type.VERTICAL_CHAIN, "vChain");
        hashMap2.put(Type.HORIZONTAL_CHAIN, "hChain");
        hashMap2.put(Type.BARRIER, "barrier");
    }

    public Helper(String name, HelperType type) {
        this.type = null;
        this.configMap = new HashMap();
        this.name = name;
        this.type = type;
    }

    public Helper(String name, HelperType type, String config) {
        this.type = null;
        this.configMap = new HashMap();
        this.name = name;
        this.type = type;
        this.config = config;
        this.configMap = convertConfigToMap();
    }

    public String getId() {
        return this.name;
    }

    public HelperType getType() {
        return this.type;
    }

    public String getConfig() {
        return this.config;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:            r1.append(r7);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.String, java.lang.String> convertConfigToMap() {
        /*
            r10 = this;
            java.lang.String r0 = r10.config
            if (r0 == 0) goto L71
            int r0 = r0.length()
            if (r0 != 0) goto Lc
            goto L71
        Lc:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            r4 = 0
            r5 = 0
            r6 = 0
        L1d:
            java.lang.String r7 = r10.config
            int r7 = r7.length()
            if (r6 >= r7) goto L69
            java.lang.String r7 = r10.config
            char r7 = r7.charAt(r6)
            r8 = 58
            r9 = 0
            if (r7 != r8) goto L38
            java.lang.String r2 = r1.toString()
            r1.setLength(r9)
            goto L66
        L38:
            r8 = 44
            if (r7 != r8) goto L4f
            if (r4 != 0) goto L4f
            if (r5 != 0) goto L4f
            java.lang.String r3 = r1.toString()
            r0.put(r2, r3)
            java.lang.String r8 = ""
            r3 = r8
            r2 = r8
            r1.setLength(r9)
            goto L66
        L4f:
            r8 = 32
            if (r7 == r8) goto L66
            switch(r7) {
                case 91: goto L60;
                case 93: goto L5d;
                case 123: goto L5a;
                case 125: goto L57;
                default: goto L56;
            }
        L56:
            goto L63
        L57:
            int r5 = r5 + (-1)
            goto L63
        L5a:
            int r5 = r5 + 1
            goto L63
        L5d:
            int r4 = r4 + (-1)
            goto L63
        L60:
            int r4 = r4 + 1
        L63:
            r1.append(r7)
        L66:
            int r6 = r6 + 1
            goto L1d
        L69:
            java.lang.String r6 = r1.toString()
            r0.put(r2, r6)
            return r0
        L71:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.dsl.Helper.convertConfigToMap():java.util.Map");
    }

    public void append(Map<String, String> map, StringBuilder ret) {
        if (map.isEmpty()) {
            return;
        }
        for (String key : map.keySet()) {
            ret.append(key).append(":").append(map.get(key)).append(",\n");
        }
    }

    public String toString() {
        StringBuilder ret = new StringBuilder(this.name + ":{\n");
        if (this.type != null) {
            ret.append("type:'").append(this.type.toString()).append("',\n");
        }
        Map<String, String> map = this.configMap;
        if (map != null) {
            append(map, ret);
        }
        ret.append("},\n");
        return ret.toString();
    }

    public static void main(String[] args) {
        Barrier b = new Barrier("abc", "['a1', 'b2']");
        System.out.println(b.toString());
    }

    /* loaded from: classes.dex */
    public static final class HelperType {
        final String mName;

        public HelperType(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }
}
