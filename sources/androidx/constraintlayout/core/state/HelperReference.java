package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
public class HelperReference extends ConstraintReference implements Facade {
    protected final State mHelperState;
    private HelperWidget mHelperWidget;
    protected ArrayList<Object> mReferences;
    final State.Helper mType;

    public HelperReference(State state, State.Helper type) {
        super(state);
        this.mReferences = new ArrayList<>();
        this.mHelperState = state;
        this.mType = type;
    }

    public State.Helper getType() {
        return this.mType;
    }

    public HelperReference add(Object... objects) {
        Collections.addAll(this.mReferences, objects);
        return this;
    }

    public void setHelperWidget(HelperWidget helperWidget) {
        this.mHelperWidget = helperWidget;
    }

    public HelperWidget getHelperWidget() {
        return this.mHelperWidget;
    }

    @Override // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        return getHelperWidget();
    }

    @Override // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
    }

    public void applyBase() {
        super.apply();
    }
}
