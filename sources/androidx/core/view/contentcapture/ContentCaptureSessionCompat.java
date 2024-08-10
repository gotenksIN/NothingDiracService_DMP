package androidx.core.view.contentcapture;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewStructureCompat;
import androidx.core.view.autofill.AutofillIdCompat;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class ContentCaptureSessionCompat {
    private static final String KEY_VIEW_TREE_APPEARED = "TREAT_AS_VIEW_TREE_APPEARED";
    private static final String KEY_VIEW_TREE_APPEARING = "TREAT_AS_VIEW_TREE_APPEARING";
    private final View mView;
    private final Object mWrappedObj;

    public static ContentCaptureSessionCompat toContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View host) {
        return new ContentCaptureSessionCompat(contentCaptureSession, host);
    }

    public ContentCaptureSession toContentCaptureSession() {
        return (ContentCaptureSession) this.mWrappedObj;
    }

    private ContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View host) {
        this.mWrappedObj = contentCaptureSession;
        this.mView = host;
    }

    public AutofillId newAutofillId(long virtualChildId) {
        return Api29Impl.newAutofillId((ContentCaptureSession) this.mWrappedObj, ((AutofillIdCompat) Objects.requireNonNull(ViewCompat.getAutofillId(this.mView))).toAutofillId(), virtualChildId);
    }

    public ViewStructureCompat newVirtualViewStructure(AutofillId parentId, long virtualId) {
        return ViewStructureCompat.toViewStructureCompat(Api29Impl.newVirtualViewStructure((ContentCaptureSession) this.mWrappedObj, parentId, virtualId));
    }

    public void notifyViewsAppeared(List<ViewStructure> appearedNodes) {
        Api34Impl.notifyViewsAppeared((ContentCaptureSession) this.mWrappedObj, appearedNodes);
    }

    public void notifyViewsDisappeared(long[] virtualIds) {
        Api29Impl.notifyViewsDisappeared((ContentCaptureSession) this.mWrappedObj, ((AutofillIdCompat) Objects.requireNonNull(ViewCompat.getAutofillId(this.mView))).toAutofillId(), virtualIds);
    }

    public void notifyViewTextChanged(AutofillId id, CharSequence text) {
        Api29Impl.notifyViewTextChanged((ContentCaptureSession) this.mWrappedObj, id, text);
    }

    /* loaded from: classes.dex */
    private static class Api34Impl {
        private Api34Impl() {
        }

        static void notifyViewsAppeared(ContentCaptureSession contentCaptureSession, List<ViewStructure> appearedNodes) {
        }
    }

    /* loaded from: classes.dex */
    private static class Api29Impl {
        private Api29Impl() {
        }

        static void notifyViewsDisappeared(ContentCaptureSession contentCaptureSession, AutofillId hostId, long[] virtualIds) {
            contentCaptureSession.notifyViewsDisappeared(hostId, virtualIds);
        }

        static void notifyViewAppeared(ContentCaptureSession contentCaptureSession, ViewStructure node) {
            contentCaptureSession.notifyViewAppeared(node);
        }

        static ViewStructure newViewStructure(ContentCaptureSession contentCaptureSession, View view) {
            return contentCaptureSession.newViewStructure(view);
        }

        static ViewStructure newVirtualViewStructure(ContentCaptureSession contentCaptureSession, AutofillId parentId, long virtualId) {
            return contentCaptureSession.newVirtualViewStructure(parentId, virtualId);
        }

        static AutofillId newAutofillId(ContentCaptureSession contentCaptureSession, AutofillId hostId, long virtualChildId) {
            return contentCaptureSession.newAutofillId(hostId, virtualChildId);
        }

        public static void notifyViewTextChanged(ContentCaptureSession contentCaptureSession, AutofillId id, CharSequence charSequence) {
            contentCaptureSession.notifyViewTextChanged(id, charSequence);
        }
    }

    /* loaded from: classes.dex */
    private static class Api23Impl {
        private Api23Impl() {
        }

        static Bundle getExtras(ViewStructure viewStructure) {
            return viewStructure.getExtras();
        }
    }
}
