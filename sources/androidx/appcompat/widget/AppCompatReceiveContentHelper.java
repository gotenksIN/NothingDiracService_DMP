package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
final class AppCompatReceiveContentHelper {
    private static final String LOG_TAG = "ReceiveContent";

    private AppCompatReceiveContentHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean maybeHandleMenuActionViaPerformReceiveContent(TextView view, int actionId) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean maybeHandleDragEventViaPerformReceiveContent(View view, DragEvent event) {
        return false;
    }

    /* loaded from: classes.dex */
    private static final class OnDropApi24Impl {
        private OnDropApi24Impl() {
        }

        static boolean onDropForTextView(DragEvent event, TextView view, Activity activity) {
            activity.requestDragAndDropPermissions(event);
            int offset = view.getOffsetForPosition(event.getX(), event.getY());
            view.beginBatchEdit();
            try {
                Selection.setSelection((Spannable) view.getText(), offset);
                ContentInfoCompat payload = new ContentInfoCompat.Builder(event.getClipData(), 3).build();
                ViewCompat.performReceiveContent(view, payload);
                view.endBatchEdit();
                return true;
            } catch (Throwable th) {
                view.endBatchEdit();
                throw th;
            }
        }

        static boolean onDropForView(DragEvent event, View view, Activity activity) {
            activity.requestDragAndDropPermissions(event);
            ContentInfoCompat payload = new ContentInfoCompat.Builder(event.getClipData(), 3).build();
            ViewCompat.performReceiveContent(view, payload);
            return true;
        }
    }

    static Activity tryGetActivity(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }
}
