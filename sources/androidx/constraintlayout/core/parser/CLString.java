package androidx.constraintlayout.core.parser;

/* loaded from: classes.dex */
public class CLString extends CLElement {
    public CLString(char[] content) {
        super(content);
    }

    public static CLElement allocate(char[] content) {
        return new CLString(content);
    }

    public static CLString from(String content) {
        CLString stringElement = new CLString(content.toCharArray());
        stringElement.setStart(0L);
        stringElement.setEnd(content.length() - 1);
        return stringElement;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        return "'" + content() + "'";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int indent, int forceIndent) {
        StringBuilder json = new StringBuilder();
        addIndent(json, indent);
        json.append("'");
        json.append(content());
        json.append("'");
        return json.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof CLString) && content().equals(((CLString) obj).content())) {
            return true;
        }
        return super.equals(obj);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return super.hashCode();
    }
}
