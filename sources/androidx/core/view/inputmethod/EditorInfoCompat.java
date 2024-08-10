package androidx.core.view.inputmethod;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_SELECTION_END_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END";
    private static final String CONTENT_SELECTION_HEAD_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD";
    private static final String CONTENT_SURROUNDING_TEXT_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
    static final int MAX_INITIAL_SELECTION_LENGTH = 1024;
    static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;

    public static void setContentMimeTypes(EditorInfo editorInfo, String[] contentMimeTypes) {
        editorInfo.contentMimeTypes = contentMimeTypes;
    }

    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        String[] result = editorInfo.contentMimeTypes;
        return result != null ? result : EMPTY_STRING_ARRAY;
    }

    public static void setInitialSurroundingText(EditorInfo editorInfo, CharSequence sourceText) {
        Api30Impl.setInitialSurroundingSubText(editorInfo, sourceText, 0);
    }

    public static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence subText, int subTextStart) {
        Preconditions.checkNotNull(subText);
        Api30Impl.setInitialSurroundingSubText(editorInfo, subText, subTextStart);
    }

    private static void trimLongSurroundingText(EditorInfo editorInfo, CharSequence subText, int selStart, int selEnd) {
        CharSequence beforeCursor;
        int sourceSelLength = selEnd - selStart;
        int newSelLength = sourceSelLength > 1024 ? 0 : sourceSelLength;
        int subTextAfterCursorLength = subText.length() - selEnd;
        int maxLengthMinusSelection = 2048 - newSelLength;
        int possibleMaxBeforeCursorLength = Math.min(selStart, (int) (maxLengthMinusSelection * 0.8d));
        int newAfterCursorLength = Math.min(subTextAfterCursorLength, maxLengthMinusSelection - possibleMaxBeforeCursorLength);
        int newBeforeCursorLength = Math.min(selStart, maxLengthMinusSelection - newAfterCursorLength);
        int newBeforeCursorHead = selStart - newBeforeCursorLength;
        if (isCutOnSurrogate(subText, selStart - newBeforeCursorLength, 0)) {
            newBeforeCursorHead++;
            newBeforeCursorLength--;
        }
        if (isCutOnSurrogate(subText, (selEnd + newAfterCursorLength) - 1, 1)) {
            newAfterCursorLength--;
        }
        int newTextLength = newBeforeCursorLength + newSelLength + newAfterCursorLength;
        if (newSelLength != sourceSelLength) {
            CharSequence beforeCursor2 = subText.subSequence(newBeforeCursorHead, newBeforeCursorHead + newBeforeCursorLength);
            CharSequence afterCursor = subText.subSequence(selEnd, selEnd + newAfterCursorLength);
            beforeCursor = TextUtils.concat(beforeCursor2, afterCursor);
        } else {
            beforeCursor = subText.subSequence(newBeforeCursorHead, newBeforeCursorHead + newTextLength);
        }
        int newSelHead = 0 + newBeforeCursorLength;
        setSurroundingText(editorInfo, beforeCursor, newSelHead, newSelHead + newSelLength);
    }

    public static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int length, int flags) {
        return Api30Impl.getInitialTextBeforeCursor(editorInfo, length, flags);
    }

    public static CharSequence getInitialSelectedText(EditorInfo editorInfo, int flags) {
        return Api30Impl.getInitialSelectedText(editorInfo, flags);
    }

    public static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int length, int flags) {
        return Api30Impl.getInitialTextAfterCursor(editorInfo, length, flags);
    }

    private static boolean isCutOnSurrogate(CharSequence sourceText, int cutPosition, int policy) {
        switch (policy) {
            case 0:
                return Character.isLowSurrogate(sourceText.charAt(cutPosition));
            case 1:
                return Character.isHighSurrogate(sourceText.charAt(cutPosition));
            default:
                return false;
        }
    }

    private static boolean isPasswordInputType(int inputType) {
        int variation = inputType & 4095;
        return variation == 129 || variation == 225 || variation == 18;
    }

    private static void setSurroundingText(EditorInfo editorInfo, CharSequence surroundingText, int selectionHead, int selectionEnd) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        CharSequence surroundingTextCopy = surroundingText != null ? new SpannableStringBuilder(surroundingText) : null;
        editorInfo.extras.putCharSequence(CONTENT_SURROUNDING_TEXT_KEY, surroundingTextCopy);
        editorInfo.extras.putInt(CONTENT_SELECTION_HEAD_KEY, selectionHead);
        editorInfo.extras.putInt(CONTENT_SELECTION_END_KEY, selectionEnd);
    }

    static int getProtocol(EditorInfo editorInfo) {
        return 1;
    }

    @Deprecated
    public EditorInfoCompat() {
    }

    /* loaded from: classes.dex */
    private static class Api30Impl {
        private Api30Impl() {
        }

        static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence sourceText, int subTextStart) {
            editorInfo.setInitialSurroundingSubText(sourceText, subTextStart);
        }

        static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int length, int flags) {
            return editorInfo.getInitialTextBeforeCursor(length, flags);
        }

        static CharSequence getInitialSelectedText(EditorInfo editorInfo, int flags) {
            return editorInfo.getInitialSelectedText(flags);
        }

        static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int length, int flags) {
            return editorInfo.getInitialTextAfterCursor(length, flags);
        }
    }
}
