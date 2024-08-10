package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppLocalesStorageHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final String APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME = "androidx.appcompat.app.AppLocalesMetadataHolderService";
    static final boolean DEBUG = false;
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";

    private AppLocalesStorageHelper() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0040, code lost:            r1 = r3.getAttributeValue(null, androidx.appcompat.app.AppLocalesStorageHelper.LOCALE_RECORD_ATTRIBUTE_TAG);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String readLocales(android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = ""
            java.io.FileInputStream r2 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L6f
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
        L16:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r6 = r5
            r7 = 1
            if (r5 == r7) goto L43
            r5 = 3
            if (r6 != r5) goto L27
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            if (r7 <= r4) goto L43
        L27:
            if (r6 == r5) goto L16
            r5 = 4
            if (r6 != r5) goto L2d
            goto L16
        L2d:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            java.lang.String r7 = "locales"
            boolean r7 = r5.equals(r7)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            if (r7 == 0) goto L42
            java.lang.String r7 = "application_locales"
            r8 = 0
            java.lang.String r7 = r3.getAttributeValue(r8, r7)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r1 = r7
            goto L43
        L42:
            goto L16
        L43:
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.io.IOException -> L49
        L48:
            goto L5c
        L49:
            r3 = move-exception
            goto L48
        L4b:
            r0 = move-exception
            goto L67
        L4d:
            r3 = move-exception
            java.lang.String r4 = "AppLocalesStorageHelper"
            java.lang.String r5 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r4, r5)     // Catch: java.lang.Throwable -> L4b
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.io.IOException -> L49
            goto L48
        L5c:
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L63
            goto L66
        L63:
            r9.deleteFile(r0)
        L66:
            return r1
        L67:
            if (r2 == 0) goto L6e
            r2.close()     // Catch: java.io.IOException -> L6d
            goto L6e
        L6d:
            r3 = move-exception
        L6e:
            throw r0
        L6f:
            r0 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x004d -> B:14:0x004f). Please report as a decompilation issue!!! */
    static void persistLocales(Context context, String locales) {
        if (locales.equals("")) {
            context.deleteFile(APPLICATION_LOCALES_RECORD_FILE);
            return;
        }
        try {
            FileOutputStream fos = context.openFileOutput(APPLICATION_LOCALES_RECORD_FILE, 0);
            XmlSerializer serializer = Xml.newSerializer();
            try {
                try {
                    try {
                        serializer.setOutput(fos, null);
                        serializer.startDocument("UTF-8", true);
                        serializer.startTag(null, LOCALE_RECORD_FILE_TAG);
                        serializer.attribute(null, LOCALE_RECORD_ATTRIBUTE_TAG, locales);
                        serializer.endTag(null, LOCALE_RECORD_FILE_TAG);
                        serializer.endDocument();
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Throwable th) {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                }
            } catch (Exception e3) {
                Log.w(TAG, "Storing App Locales : Failed to persist app-locales in storage ", e3);
                if (fos != null) {
                    fos.close();
                }
            }
        } catch (FileNotFoundException e4) {
            Log.w(TAG, String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", APPLICATION_LOCALES_RECORD_FILE));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void syncLocalesToFramework(Context context) {
        ComponentName app_locales_component = new ComponentName(context, APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME);
        if (context.getPackageManager().getComponentEnabledSetting(app_locales_component) != 1) {
            if (AppCompatDelegate.getApplicationLocales().isEmpty()) {
                String appLocales = readLocales(context);
                Object localeManager = context.getSystemService("locale");
                if (localeManager != null) {
                    AppCompatDelegate.Api33Impl.localeManagerSetApplicationLocales(localeManager, AppCompatDelegate.Api24Impl.localeListForLanguageTags(appLocales));
                }
            }
            context.getPackageManager().setComponentEnabledSetting(app_locales_component, 1, 1);
        }
    }

    /* loaded from: classes.dex */
    static class ThreadPerTaskExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue<Runnable> mTasks = new ArrayDeque();

        /* JADX INFO: Access modifiers changed from: package-private */
        public SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable r) {
            synchronized (this.mLock) {
                this.mTasks.add(new Runnable() { // from class: androidx.appcompat.app.AppLocalesStorageHelper$SerialExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppLocalesStorageHelper.SerialExecutor.this.m6xd188c474(r);
                    }
                });
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$execute$0$androidx-appcompat-app-AppLocalesStorageHelper$SerialExecutor, reason: not valid java name */
        public /* synthetic */ void m6xd188c474(Runnable r) {
            try {
                r.run();
            } finally {
                scheduleNext();
            }
        }

        protected void scheduleNext() {
            synchronized (this.mLock) {
                Runnable poll = this.mTasks.poll();
                this.mActive = poll;
                if (poll != null) {
                    this.mExecutor.execute(poll);
                }
            }
        }
    }
}
