package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintSet {
    ArrayList<Constraint> mConstraints = new ArrayList<>();
    ArrayList<Helper> mHelpers = new ArrayList<>();
    private final String mName;

    public ConstraintSet(String name) {
        this.mName = name;
    }

    public void add(Constraint c) {
        this.mConstraints.add(c);
    }

    public void add(Helper h) {
        this.mHelpers.add(h);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder(this.mName + ":{\n");
        if (!this.mConstraints.isEmpty()) {
            Iterator<Constraint> it = this.mConstraints.iterator();
            while (it.hasNext()) {
                Constraint cs = it.next();
                ret.append(cs.toString());
            }
        }
        if (!this.mHelpers.isEmpty()) {
            Iterator<Helper> it2 = this.mHelpers.iterator();
            while (it2.hasNext()) {
                Helper h = it2.next();
                ret.append(h.toString());
            }
        }
        ret.append("},\n");
        return ret.toString();
    }
}
