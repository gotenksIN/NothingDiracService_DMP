package com.dirac;

/* loaded from: classes4.dex */
public class Settings {
    private final Backend backend;
    private final String section;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public interface Backend {
        String getSectionDivider();

        String getStr(String str, String str2, String str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Settings(String section, Backend backend) {
        this.section = section;
        this.backend = backend;
    }

    public final int getInt(String key, int def) {
        String s = getStr(key);
        if (s == null) {
            return def;
        }
        return Integer.parseInt(s);
    }

    public final Integer getInt(String key) {
        String s = getStr(key);
        if (s == null) {
            return null;
        }
        return Integer.valueOf(s);
    }

    public final boolean getBool(String key, boolean def) {
        String s = getStr(key);
        if (s == null) {
            return def;
        }
        return Boolean.parseBoolean(s);
    }

    public final Boolean getBool(String key) {
        String s = getStr(key);
        if (s == null) {
            return false;
        }
        return Boolean.valueOf(s);
    }

    public final String getStr(String key) {
        return getStr(key, null);
    }

    public final String getStr(String key, String def) {
        return this.backend.getStr(this.section, key, def);
    }

    public final Settings getSubSection(String subSection) {
        String newSection = this.section + this.backend.getSectionDivider() + subSection;
        return new Settings(newSection, this.backend);
    }

    public final String getSection() {
        return this.section;
    }
}
