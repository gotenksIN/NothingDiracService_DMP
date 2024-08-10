package com.nothing.dirac_DMP;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.util.Log;
import android.util.Xml;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes4.dex */
public class DetectSceneService extends Thread {
    private static final String TAG = DetectSceneService.class.getSimpleName();
    private AADiracService mAADiracControlService;
    private ActivityManager mActivityManger;
    private Context mContext;
    private int mLocalAppListVer;
    private ArrayList<String> mVideoListApk = new ArrayList<>();
    private ArrayList<String> mMusicListApk = new ArrayList<>();
    private ArrayList<String> mShortVideoListApk = new ArrayList<>();
    private ArrayList<String> mGameListApk = new ArrayList<>();
    private ArrayList<String> mNavigateListApk = new ArrayList<>();
    private ArrayList<String> mBlackListApk = new ArrayList<>();
    private String activeAuidoPids = null;
    private String openString = "open";
    private String cmdString = null;

    public DetectSceneService(AADiracService diracEffectControlService) {
        this.mLocalAppListVer = 20160101;
        Log.d(TAG, "[DetectSceneService] Begin execute!!!");
        this.mAADiracControlService = diracEffectControlService;
        Context applicationContext = diracEffectControlService.getApplicationContext();
        this.mContext = applicationContext;
        this.mActivityManger = (ActivityManager) applicationContext.getSystemService("activity");
        try {
            this.mLocalAppListVer = Integer.valueOf(this.mContext.getResources().getString(R.string.applist_version)).intValue();
        } catch (NumberFormatException e) {
            this.mLocalAppListVer = 20160101;
        }
        IntentFilter filter = new IntentFilter(AADiracService.SET_AUTO_MODE);
        this.mContext.registerReceiver(new receiveSetAutomode(), filter);
        Log.d(TAG, "[DetectSceneService] Finfish execute!!!");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.d(TAG, "[run] DetectSceneService thread run!!!");
        getMediaList();
        try {
            LocalServerSocket localServerSocket = new LocalServerSocket("AudioEffect");
            while (true) {
                String str = TAG;
                Log.i(str, "[run] begin!!!");
                LocalSocket connect = localServerSocket.accept();
                Log.i(str, "[run] Acquire localSocket success!!!");
                ThreadPool.getInstance().request(new Task(connect));
                Log.i(str, "[run] Finish ThreadPool.getInstance().request!!!");
            }
        } catch (IOException e) {
            Log.i(TAG, "[run][dirac] failed!");
            e.printStackTrace();
        }
    }

    /* loaded from: classes4.dex */
    private class receiveSetAutomode extends BroadcastReceiver {
        private receiveSetAutomode() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(AADiracService.SET_AUTO_MODE)) {
                Log.d(DetectSceneService.TAG, "[onReceive] receiveSetAutomode");
                DetectSceneService detectSceneService = DetectSceneService.this;
                detectSceneService.setAutoModeEffect(detectSceneService.activeAuidoPids);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0065. Please report as an issue. */
    private void getMediaList() {
        Log.d(TAG, "[getMediaList]");
        FileReader xmlReader = null;
        if (!this.mVideoListApk.isEmpty()) {
            this.mVideoListApk.clear();
        }
        if (!this.mMusicListApk.isEmpty()) {
            this.mMusicListApk.clear();
        }
        if (!this.mNavigateListApk.isEmpty()) {
            this.mNavigateListApk.clear();
        }
        if (!this.mGameListApk.isEmpty()) {
            this.mGameListApk.clear();
        }
        if (!this.mBlackListApk.isEmpty()) {
            this.mBlackListApk.clear();
        }
        try {
            try {
                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(this.mContext.getAssets().open("dirac_support_apps.xml"), "UTF-8");
                    for (int event = parser.getEventType(); event != 1; event = parser.next()) {
                        switch (event) {
                            case 0:
                            case 1:
                            default:
                            case 2:
                                if (parser.getName().equals("mVideoListApk")) {
                                    parser.next();
                                    this.mVideoListApk.add(parser.getText());
                                } else if (parser.getName().equals("mMusicListApk")) {
                                    parser.next();
                                    this.mMusicListApk.add(parser.getText());
                                } else if (parser.getName().equals("mNavigateListApk")) {
                                    parser.next();
                                    this.mNavigateListApk.add(parser.getText());
                                } else if (parser.getName().equals("mGameListApk")) {
                                    parser.next();
                                    this.mGameListApk.add(parser.getText());
                                } else if (parser.getName().equals("mBlackListApk")) {
                                    parser.next();
                                    this.mBlackListApk.add(parser.getText());
                                }
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "[getMediaList] err when parse xml");
                    e.printStackTrace();
                    if (0 != 0) {
                        xmlReader.close();
                    } else {
                        return;
                    }
                }
                if (0 != 0) {
                    xmlReader.close();
                }
            } catch (IOException e2) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    xmlReader.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }

    private boolean checkMusicListPackagename(String appName) {
        for (int i = 0; i < this.mMusicListApk.size(); i++) {
            if (appName.contains(this.mMusicListApk.get(i)) || appName.contains("music") || appName.contains("mp3")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVideoListPackagename(String appName) {
        for (int i = 0; i < this.mVideoListApk.size(); i++) {
            if (appName.contains(this.mVideoListApk.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNavigateListPackagename(String appName) {
        for (int i = 0; i < this.mNavigateListApk.size(); i++) {
            if (appName.contains(this.mNavigateListApk.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGameListPackagename(String appName) {
        for (int i = 0; i < this.mGameListApk.size(); i++) {
            if (appName.contains(this.mGameListApk.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBlackListPackagename(String appName) {
        for (int i = 0; i < this.mBlackListApk.size(); i++) {
            if (appName.contains(this.mBlackListApk.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getActiveAudioPids() {
        AudioManager localAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        String pids = localAudioManager.getParameters("get_pid");
        if (pids == null || pids.length() == 0) {
            return null;
        }
        return pids;
    }

    private String getPidName(String pID) {
        String processingName = "";
        List<ActivityManager.RunningAppProcessInfo> appProcessList = this.mActivityManger.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {
            int pid = appProcess.pid;
            String processName = appProcess.processName;
            Log.d(TAG, "pid = " + pid + " processName = " + processName);
            if (pID != null) {
                try {
                    if (pID.contains(Integer.toString(pid))) {
                        processingName = (processingName + processName) + ":";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return processingName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class Task implements Runnable {
        private LocalSocket socket;

        public Task(LocalSocket connect) {
            this.socket = null;
            this.socket = connect;
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x000c -> B:7:0x0020). Please report as a decompilation issue!!! */
        @Override // java.lang.Runnable
        public void run() {
            try {
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    handlesocket();
                    LocalSocket localSocket = this.socket;
                    if (localSocket != null) {
                        localSocket.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (this.socket != null) {
                        this.socket.close();
                    }
                }
            } catch (Throwable th) {
                try {
                    LocalSocket localSocket2 = this.socket;
                    if (localSocket2 != null) {
                        localSocket2.close();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }

        private void handlesocket() {
            Log.d(DetectSceneService.TAG, "[handlesocket]");
            try {
                InputStream input = this.socket.getInputStream();
                byte[] bytes = new byte[2];
                input.read(bytes, 0, 2);
                int cmd_size = (bytes[0] & 255) | ((bytes[1] & 255) << 8);
                if (cmd_size > 0) {
                    byte[] StringOfcmd = new byte[cmd_size + 1];
                    input.read(StringOfcmd, 0, cmd_size);
                    StringOfcmd[cmd_size] = 0;
                    DetectSceneService.this.cmdString = new String(StringOfcmd, 0, cmd_size);
                }
                Thread.sleep(100L);
                DetectSceneService detectSceneService = DetectSceneService.this;
                detectSceneService.activeAuidoPids = detectSceneService.getActiveAudioPids();
                DetectSceneService detectSceneService2 = DetectSceneService.this;
                detectSceneService2.setAutoModeEffect(detectSceneService2.activeAuidoPids);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAutoModeEffect(String activeAuidoPids) {
        AADiracService aADiracService;
        Log.d(TAG, "activeAuidoPids = " + activeAuidoPids);
        if (activeAuidoPids != null && this.cmdString.equals(this.openString) && this.mAADiracControlService.getAutoMode() && this.mAADiracControlService.getDiracEnableStatus()) {
            boolean found = false;
            String[] AllActivityAppName = getPidName(activeAuidoPids).split(":");
            int length = AllActivityAppName.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String activyAppName = AllActivityAppName[i2];
                if (!checkMusicListPackagename(activyAppName)) {
                    i2++;
                } else {
                    Log.d(TAG, "[setAutoModeEffect] find Music App: " + activyAppName);
                    AADiracService aADiracService2 = this.mAADiracControlService;
                    if (aADiracService2 != null) {
                        aADiracService2.setMusicMode();
                    }
                    found = true;
                }
            }
            if (!found) {
                int length2 = AllActivityAppName.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        break;
                    }
                    String appName = AllActivityAppName[i3];
                    if (!checkVideoListPackagename(appName)) {
                        i3++;
                    } else {
                        Log.d(TAG, "[setAutoModeEffect] find Movie App: " + appName);
                        AADiracService aADiracService3 = this.mAADiracControlService;
                        if (aADiracService3 != null) {
                            aADiracService3.setMovieMode();
                        }
                        found = true;
                    }
                }
            }
            if (!found) {
                int length3 = AllActivityAppName.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length3) {
                        break;
                    }
                    String appName2 = AllActivityAppName[i4];
                    if (!checkNavigateListPackagename(appName2)) {
                        i4++;
                    } else {
                        Log.d(TAG, "[setAutoModeEffect] find Navigate App: " + appName2);
                        AADiracService aADiracService4 = this.mAADiracControlService;
                        if (aADiracService4 != null) {
                            aADiracService4.setNavigateMode();
                        }
                        found = true;
                    }
                }
            }
            if (!found) {
                int length4 = AllActivityAppName.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length4) {
                        break;
                    }
                    String appName3 = AllActivityAppName[i5];
                    if (!checkGameListPackagename(appName3)) {
                        i5++;
                    } else {
                        Log.d(TAG, "[setAutoModeEffect] find Game App: " + appName3);
                        AADiracService aADiracService5 = this.mAADiracControlService;
                        if (aADiracService5 != null) {
                            aADiracService5.setGameMode();
                        }
                        found = true;
                    }
                }
            }
            if (!found) {
                int length5 = AllActivityAppName.length;
                while (true) {
                    if (i >= length5) {
                        break;
                    }
                    String appName4 = AllActivityAppName[i];
                    if (!checkBlackListPackagename(appName4)) {
                        i++;
                    } else {
                        String str = TAG;
                        Log.d(str, "[setAutoModeEffect] find BlackList App: " + appName4);
                        AADiracService aADiracService6 = this.mAADiracControlService;
                        if (aADiracService6 != null) {
                            if (aADiracService6.getHeadsetStatus() == 1) {
                                this.mAADiracControlService.disableExternal();
                            } else if (this.mAADiracControlService.getHeadsetStatus() == 2) {
                                Log.i(str, "[setAutoModeEffect] find BlackList App：cureent device is bluetooth, do nothing here,will not process data in FW");
                            } else {
                                this.mAADiracControlService.disableInternal();
                            }
                        }
                        found = true;
                    }
                }
            }
            if (!found && (aADiracService = this.mAADiracControlService) != null) {
                aADiracService.setMusicMode();
            }
        }
    }
}
