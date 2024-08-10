package com.dirac.android;

import com.dirac.Settings;
import com.dirac.wrapper.SystemProperties;

/* loaded from: classes4.dex */
public class SystemSettings extends Settings {
    private static final String[] prefixes = {"", "persist", "ro"};

    public SystemSettings(String section) {
        super(section, new Settings.Backend() { // from class: com.dirac.android.SystemSettings.1
            @Override // com.dirac.Settings.Backend
            public String getStr(String section2, String key, String def) {
                if (def == null || !def.isEmpty()) {
                    for (String prefix : SystemSettings.prefixes) {
                        StringBuilder sb = new StringBuilder();
                        if (!prefix.isEmpty()) {
                            sb.append(prefix).append(".");
                        }
                        String fullKey = sb.append(section2).append(".").append(key).toString();
                        String s = SystemProperties.get(fullKey);
                        if (!s.isEmpty()) {
                            return s;
                        }
                    }
                    return def;
                }
                throw new IllegalArgumentException("invalid default value: the backing settings storage makes no difference between non-set and empty value");
            }

            @Override // com.dirac.Settings.Backend
            public String getSectionDivider() {
                return ".";
            }
        });
    }
}
