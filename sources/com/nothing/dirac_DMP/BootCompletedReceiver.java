package com.nothing.dirac_DMP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes4.dex */
public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = BootCompletedReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        Log.d(str, "[onReceive] action is " + action);
        if (action != null) {
            if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
                Intent mIntent = new Intent(context, (Class<?>) AADiracService.class);
                context.startService(mIntent);
                Log.d(str, "[onReceive] start AADiracService successfully");
            }
        }
    }
}
