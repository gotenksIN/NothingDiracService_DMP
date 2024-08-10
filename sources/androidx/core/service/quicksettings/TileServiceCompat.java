package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.quicksettings.TileService;

/* loaded from: classes.dex */
public class TileServiceCompat {
    private static TileServiceWrapper sTileServiceWrapper;

    /* loaded from: classes.dex */
    interface TileServiceWrapper {
        void startActivityAndCollapse(PendingIntent pendingIntent);

        void startActivityAndCollapse(Intent intent);
    }

    public static void startActivityAndCollapse(TileService tileService, PendingIntentActivityWrapper wrapper) {
        TileServiceWrapper tileServiceWrapper = sTileServiceWrapper;
        if (tileServiceWrapper != null) {
            tileServiceWrapper.startActivityAndCollapse(wrapper.getPendingIntent());
        } else {
            Api34Impl.startActivityAndCollapse(tileService, wrapper.getPendingIntent());
        }
    }

    public static void setTileServiceWrapper(TileServiceWrapper serviceWrapper) {
        sTileServiceWrapper = serviceWrapper;
    }

    public static void clearTileServiceWrapper() {
        sTileServiceWrapper = null;
    }

    /* loaded from: classes.dex */
    private static class Api34Impl {
        private Api34Impl() {
        }

        static void startActivityAndCollapse(TileService service, PendingIntent pendingIntent) {
            service.startActivityAndCollapse(pendingIntent);
        }
    }

    /* loaded from: classes.dex */
    private static class Api24Impl {
        private Api24Impl() {
        }

        static void startActivityAndCollapse(TileService service, Intent intent) {
            service.startActivityAndCollapse(intent);
        }
    }

    private TileServiceCompat() {
    }
}
