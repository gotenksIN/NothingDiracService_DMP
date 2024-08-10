package com.nothing.dirac_DMP;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.dirac.settings.api.AudioControlService;
import com.dirac.settings.api.Configuration;
import com.dirac.settings.api.OutputType;
import com.dirac.settings.api.ParameterEnumValue;
import com.dirac.settings.api.ParameterInfo;
import com.dirac.settings.api.Result;
import com.dirac.settings.api.SafeModeException;
import com.dirac.settings.api.StreamType;
import com.nothing.dirac_DMP.AADiracService;
import com.nothing.dirac_DMP.IAADiracService;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes4.dex */
public class AADiracService extends Service {
    private static final String AUDIO_EFFECT_ENABLED = "audio_effect_enable";
    private static final int BIND_AGAIN_DIRAC_SERVICE = 1;
    private static final int BIND_RETRY_TIMES = 3;
    private static final String CURRENT_MODE = "current_state";
    private static final int DELAY_HANDLE_UPDATEEFFECT = 2;
    private static final String DIRAC_STATE = "dirac_on_off";
    public static final String EXTERNAL_SYS_SOUND = "External-SysSound";
    private static final String IS_CONNECTED = "is_connected";
    private static final int MIN_STORAGE = 102400;
    private static final int N_BANDS = 7;
    private static final String PRESET_STATUS = "preset.status";
    public static final String PRINT_ALL_STATUS = "com.aa.dirac.aadiracservice.print_all_status";
    public static final String SET_AUTO_MODE = "com.aa.dirac.aadiracservice.setautomode";
    private float[][] eq_preset;
    HeadsetPlugReceiver headsetPlugReceiver;
    BlueToothHeadsetReceiver mBlueToothHeadsetReceiver;
    private ContentResolver mContentResolver;
    private Context mContext;
    NothingBTConnected mNothingBTConnected;
    private AudioControlService mService;
    private SettingsObserver mSettingsObserver;
    private String[] modeNames;
    DetectSceneService myDetectService;
    private static final String TAG = AADiracService.class.getSimpleName();
    private static OutputType currentlySelectedOutputType = OutputType.INTERNAL_SPEAKER;
    public static final String INTERNAL_SYS_SOUND = "Internal-SysSound";
    private static String currentlySelectedSysSoundType = INTERNAL_SYS_SOUND;
    private List<Configuration> availableInternalConfigurations = new ArrayList();
    public MutableLiveData<List<Configuration>> listOfInternalSpeakerConfigs = new MutableLiveData<>();
    private List<Configuration> availableInternalSystemConfigurations = new ArrayList();
    public MutableLiveData<List<Configuration>> listOfInternalSpeakerSystemConfigs = new MutableLiveData<>();
    private List<Configuration> availableExternalConfigurations = new ArrayList();
    public MutableLiveData<List<Configuration>> listOfExternalSpeakerConfigs = new MutableLiveData<>();
    private List<Configuration> availableExternalSystemConfigurations = new ArrayList();
    public MutableLiveData<List<Configuration>> listOfExternalSpeakerSystemConfigs = new MutableLiveData<>();
    private List<String> availableOutputTypesInStrings = new ArrayList();
    private List<OutputType> availableOutputTypes = new ArrayList();
    public MutableLiveData<List<OutputType>> listOfAvailableOutputTypes = new MutableLiveData<>();
    public MutableLiveData<Configuration> activeConfigurationInternal = new MutableLiveData<>();
    public MutableLiveData<Configuration> activeConfigurationInternalSystem = new MutableLiveData<>();
    public MutableLiveData<Configuration> activeConfigurationExternal = new MutableLiveData<>();
    public MutableLiveData<Configuration> activeConfigurationExternalSystem = new MutableLiveData<>();
    public MutableLiveData<Configuration> latestUsedConfiguration = new MutableLiveData<>();
    public MutableLiveData<List<ParameterInfo>> listProxyParameterData = new MutableLiveData<>();
    public MutableLiveData<List<ParameterInfo>> listProxyEQData = new MutableLiveData<>();
    public MutableLiveData<List<ParameterEnumValue>> listParameterEnumValues = new MutableLiveData<>();
    private float activeHDFilterPosition = -1.0f;
    private SharedPreferences preset_preference = null;
    private int currentMode = 0;
    private boolean diracStatus = true;
    private int eqBandCount = 7;
    private int current_bind_times = 0;
    private int current_dirac_case_id = 0;
    int BTACTIVESTATUS = 2;
    private final Result<Configuration> onConfigurationChange = new Result<Configuration>() { // from class: com.nothing.dirac_DMP.AADiracService.2
        @Override // com.dirac.settings.api.Result
        public void onResult(Configuration configuration) {
            if (configuration.getOutputType().equals(OutputType.HEADSET)) {
                if (configuration.getStreamType().equals(StreamType.GENERAL)) {
                    AADiracService.this.activeConfigurationExternal.setValue(configuration);
                } else {
                    AADiracService.this.activeConfigurationExternalSystem.setValue(configuration);
                }
            } else if (configuration.getStreamType().equals(StreamType.GENERAL)) {
                AADiracService.this.activeConfigurationInternal.setValue(configuration);
            } else {
                AADiracService.this.activeConfigurationInternalSystem.setValue(configuration);
            }
            AADiracService aADiracService = AADiracService.this;
            aADiracService.updateCurrentActiveConfiguration(aADiracService, configuration);
        }

        @Override // com.dirac.settings.api.Result
        public void onFailure(RuntimeException exception) {
            AADiracService.this.onAcsInSafeMode();
        }
    };
    public final IAADiracService.Stub mBinder = new IAADiracService.Stub() { // from class: com.nothing.dirac_DMP.AADiracService.4
        @Override // com.nothing.dirac_DMP.IAADiracService
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setEffectEnable(boolean enable) throws RemoteException {
            Log.d(AADiracService.TAG, "[setEffectEnable] Effect is " + enable);
            AADiracService.this.setEffectOnOff(enable);
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public boolean getEffectEnable() throws RemoteException {
            Log.d(AADiracService.TAG, "[getEffectEnable] Effect is " + AADiracService.this.diracStatus);
            return AADiracService.this.diracStatus;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setPreset(int NewPresetID) throws RemoteException {
            Log.d(AADiracService.TAG, "[setPreset] NewPresetID= " + NewPresetID);
            AADiracService.this.updateEffect(NewPresetID, true);
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public int getPreset() throws RemoteException {
            Log.d(AADiracService.TAG, "[getPreset] currentMode= " + AADiracService.this.currentMode);
            return AADiracService.this.currentMode;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public boolean setPresetEq(int preset) throws RemoteException {
            return true;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setCustomerEq(int band, float value) throws RemoteException {
            AADiracService.this.setCustomerHeadsetEq(band, value);
        }
    };
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.nothing.dirac_DMP.AADiracService.7
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x004d, code lost:            return false;     */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean handleMessage(android.os.Message r5) {
            /*
                r4 = this;
                int r0 = r5.what
                r1 = 0
                switch(r0) {
                    case 1: goto L11;
                    case 2: goto L7;
                    default: goto L6;
                }
            L6:
                goto L4d
            L7:
                com.nothing.dirac_DMP.AADiracService r0 = com.nothing.dirac_DMP.AADiracService.this
                int r2 = com.nothing.dirac_DMP.AADiracService.access$1300(r0)
                com.nothing.dirac_DMP.AADiracService.access$700(r0, r2, r1)
                goto L4d
            L11:
                com.nothing.dirac_DMP.AADiracService r0 = com.nothing.dirac_DMP.AADiracService.this
                com.nothing.dirac_DMP.AADiracService.access$1108(r0)
                com.nothing.dirac_DMP.AADiracService r0 = com.nothing.dirac_DMP.AADiracService.this
                int r0 = com.nothing.dirac_DMP.AADiracService.access$1100(r0)
                r2 = 3
                if (r0 > r2) goto L4d
                java.lang.String r0 = com.nothing.dirac_DMP.AADiracService.access$100()
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "BIND DIRAC FAIL ,RETRY AGAIN current_times="
                java.lang.StringBuilder r2 = r2.append(r3)
                com.nothing.dirac_DMP.AADiracService r3 = com.nothing.dirac_DMP.AADiracService.this
                int r3 = com.nothing.dirac_DMP.AADiracService.access$1100(r3)
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                android.util.Log.d(r0, r2)
                com.nothing.dirac_DMP.AADiracService r0 = com.nothing.dirac_DMP.AADiracService.this
                com.dirac.settings.api.AudioControlService r0 = com.nothing.dirac_DMP.AADiracService.access$1200(r0)
                com.nothing.dirac_DMP.AADiracService$7$1 r2 = new com.nothing.dirac_DMP.AADiracService$7$1
                r2.<init>()
                r0.acsReadyListener(r2)
            L4d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nothing.dirac_DMP.AADiracService.AnonymousClass7.handleMessage(android.os.Message):boolean");
        }
    });
    private BroadcastReceiver receivePrintAllStatus = new BroadcastReceiver() { // from class: com.nothing.dirac_DMP.AADiracService.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(AADiracService.PRINT_ALL_STATUS)) {
                AADiracService.this.reportStaus();
            }
        }
    };
    private BroadcastReceiver receiveDiracOn = new BroadcastReceiver() { // from class: com.nothing.dirac_DMP.AADiracService.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(AADiracService.TAG, "onReceivce DiracOn");
            String action = intent.getAction();
            if (action != null && action.equals(Constant.DIRAC_ON)) {
                AADiracService.this.diracOn();
            }
        }
    };
    private BroadcastReceiver receiveDiracOff = new BroadcastReceiver() { // from class: com.nothing.dirac_DMP.AADiracService.10
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(AADiracService.TAG, "onReceivce DiracOff");
            String action = intent.getAction();
            if (action != null && action.equals(Constant.DIRAC_OFF)) {
                AADiracService.this.diracOff();
            }
        }
    };

    static /* synthetic */ int access$1108(AADiracService x0) {
        int i = x0.current_bind_times;
        x0.current_bind_times = i + 1;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.current_bind_times = 0;
        SharedPreferences sharedPreferences = getSharedPreferences(PRESET_STATUS, 0);
        this.preset_preference = sharedPreferences;
        this.currentMode = sharedPreferences.getInt(CURRENT_MODE, 0);
        this.diracStatus = this.preset_preference.getBoolean(DIRAC_STATE, true);
        AudioControlService audioControlService = new AudioControlService(this);
        this.mService = audioControlService;
        audioControlService.acsReadyListener(new Result<Boolean>() { // from class: com.nothing.dirac_DMP.AADiracService.1
            @Override // com.dirac.settings.api.Result
            public void onResult(Boolean value) {
                if (Boolean.TRUE.equals(value)) {
                    AADiracService.this.onAcsReady();
                }
            }

            @Override // com.dirac.settings.api.Result
            public void onFailure(RuntimeException e) {
                Log.d(AADiracService.TAG, "[onFailure]Acs is not Ready");
                AADiracService.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
            }
        });
        registerPrintAllStatus();
        registerBroadcastForDiracOn();
        registerBroadcastForDiracOff();
        registerHeadsetPlugReceiver();
        registerNothingBTReceiver();
        registerBlueToothHeadsetReceiver();
        this.mContext = this;
        this.mContentResolver = getContentResolver();
        this.mSettingsObserver = new SettingsObserver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAcsReady() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long availableBytes = stat.getAvailableBytes();
        String str = TAG;
        Log.d(str, "[onAcsReady] Available storage(KB): " + (availableBytes / 1024));
        if (availableBytes < 102400) {
            Log.d(str, "[onAcsReady]  Not enough storage space");
            this.mService.killAudioControlService(this);
            return;
        }
        Log.d(str, "[onAcsReady] Acs is Ready!!!");
        this.mService.onConfigurationChanged(this.onConfigurationChange);
        Log.d(str, "[onAcsReady] Finish onConfigurationChanged!!!");
        getConfigurationsAsync(this);
        Log.d(str, "[onAcsReady] Finish getConfigurationsAsync!!!");
        Log.d(str, "[onAcsReady] Creat DetectSceneService!!!");
        DetectSceneService detectSceneService = new DetectSceneService(this);
        this.myDetectService = detectSceneService;
        detectSceneService.start();
        Log.d(str, "[onAcsReady] Finish myDetectService.start!!!");
        setEffectOnOff(this.diracStatus);
        this.current_bind_times = 0;
        Log.d(str, "[onAcsReady] Acs is finish Ready!!!");
    }

    public void updateCurrentActiveConfiguration(Context context, Configuration configuration) {
        resetActiveConfigurations(configuration);
        if (configuration != null) {
            if (configuration.getOutputType() == OutputType.INTERNAL_SPEAKER) {
                if (updateGeneralAndSystemStreamType(configuration, this.listOfInternalSpeakerConfigs, this.listOfInternalSpeakerSystemConfigs)) {
                    return;
                }
            } else if (configuration.getOutputType() == OutputType.HEADSET && updateGeneralAndSystemStreamType(configuration, this.listOfExternalSpeakerConfigs, this.listOfExternalSpeakerSystemConfigs)) {
                return;
            }
            getParametersForConfig(context, configuration.getName());
        }
    }

    public void getParametersForConfig(Context context, String configName) {
        Log.d(TAG, new Throwable().getStackTrace()[0].getMethodName());
        this.mService.getParameterInfoListAsync(configName, new Result<List<ParameterInfo>>() { // from class: com.nothing.dirac_DMP.AADiracService.3
            @Override // com.dirac.settings.api.Result
            public void onResult(List<ParameterInfo> fullProxyList) {
                AADiracService.this.listProxyEQData.setValue(new ArrayList());
                AADiracService.this.listProxyParameterData.setValue(new ArrayList());
                AADiracService.this.listParameterEnumValues.setValue(new ArrayList());
                AADiracService.this.setActiveHDFilterPosition(-1.0f);
                List<ParameterInfo> eQProxyList = new ArrayList<>();
                List<ParameterInfo> normalProxyList = new ArrayList<>();
                for (ParameterInfo param : fullProxyList) {
                    if (AADiracService.this.withinEQRange(param.getId())) {
                        eQProxyList.add(param);
                        AADiracService.this.listProxyEQData.setValue(eQProxyList);
                    } else if (param.isHdSoundAvailable()) {
                        AADiracService.this.setActiveHDFilterPosition(param.getValue());
                        List<ParameterEnumValue> list = Arrays.asList(param.getParameterEnumValues());
                        AADiracService.this.listParameterEnumValues.setValue(list);
                    } else {
                        normalProxyList.add(param);
                        AADiracService.this.listProxyParameterData.setValue(normalProxyList);
                        AADiracService.this.listProxyEQData.setValue(eQProxyList);
                    }
                }
            }

            @Override // com.dirac.settings.api.Result
            public void onFailure(RuntimeException exception) {
                AADiracService.this.onAcsInSafeMode();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean withinEQRange(int id) {
        return id >= 256 && id <= 383;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActiveHDFilterPosition(float activeHDFilterPosition) {
        this.activeHDFilterPosition = activeHDFilterPosition;
    }

    private boolean updateGeneralAndSystemStreamType(final Configuration configuration, MutableLiveData<List<Configuration>> listOfSpeakerConfigs, MutableLiveData<List<Configuration>> listOfSpeakerSystemConfigs) {
        if (configuration.getStreamType().equals(StreamType.GENERAL)) {
            if (listOfSpeakerConfigs == null || listOfSpeakerConfigs.getValue() == null) {
                return true;
            }
            listOfSpeakerConfigs.getValue().stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getName().equals(Configuration.this.getName());
                    return equals;
                }
            }).findFirst().ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Configuration) obj).setActive(true);
                }
            });
            return false;
        }
        if (listOfSpeakerSystemConfigs == null || listOfSpeakerSystemConfigs.getValue() == null) {
            return true;
        }
        listOfSpeakerSystemConfigs.getValue().stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((Configuration) obj).getName().equals(Configuration.this.getName());
                return equals;
            }
        }).findFirst().ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Configuration) obj).setActive(true);
            }
        });
        return false;
    }

    private void resetActiveConfigurations(Configuration configurations) {
        if ((!getCurrentlySelectedOutputType().equals(OutputType.INTERNAL_SPEAKER) || !handleGeneralAndSystemSound(configurations, this.listOfInternalSpeakerConfigs, this.listOfInternalSpeakerSystemConfigs)) && getCurrentlySelectedOutputType().equals(OutputType.HEADSET)) {
            handleGeneralAndSystemSound(configurations, this.listOfExternalSpeakerConfigs, this.listOfExternalSpeakerSystemConfigs);
        }
    }

    private boolean handleGeneralAndSystemSound(Configuration configurations, MutableLiveData<List<Configuration>> listOfSpeakerConfigs, MutableLiveData<List<Configuration>> listOfSpeakerSystemConfigs) {
        if (configurations.getStreamType().equals(StreamType.GENERAL)) {
            if (listOfSpeakerConfigs == null || listOfSpeakerConfigs.getValue() == null) {
                return true;
            }
            listOfSpeakerConfigs.getValue().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).findFirst().ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Configuration) obj).setActive(false);
                }
            });
        }
        if (configurations.getStreamType().equals(StreamType.SYSTEM_SOUND)) {
            if (listOfSpeakerSystemConfigs == null || listOfSpeakerSystemConfigs.getValue() == null) {
                return true;
            }
            listOfSpeakerSystemConfigs.getValue().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).findFirst().ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Configuration) obj).setActive(false);
                }
            });
            return false;
        }
        return false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nothing.dirac_DMP.AADiracService$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass5 implements Result<List<Configuration>> {
        final /* synthetic */ Context val$context;

        AnonymousClass5(Context context) {
            this.val$context = context;
        }

        @Override // com.dirac.settings.api.Result
        public void onResult(List<Configuration> configurations) {
            if (configurations == null) {
                return;
            }
            AADiracService.this.availableOutputTypes = (List) ((Map) configurations.stream().collect(Collectors.toMap(new Function() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Configuration) obj).getOutputType();
                }
            }, new Function() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AADiracService.AnonymousClass5.lambda$onResult$0((Configuration) obj);
                }
            }, new BinaryOperator() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda10
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return AADiracService.AnonymousClass5.lambda$onResult$1((Configuration) obj, (Configuration) obj2);
                }
            }))).values().stream().map(new Function() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Configuration) obj).getOutputType();
                }
            }).sorted().collect(Collectors.toList());
            getAllTypesAsStrings();
            AADiracService.this.setAvailableInternalConfigurations((List) configurations.stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda11
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getOutputType().equals(OutputType.INTERNAL_SPEAKER);
                    return equals;
                }
            }).filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda12
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getStreamType().equals(StreamType.GENERAL);
                    return equals;
                }
            }).collect(Collectors.toList()));
            Optional<Configuration> activeCurrentConfigurationInternal = ((List) AADiracService.this.getAvailableInternalConfigurations().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).collect(Collectors.toList())).stream().findFirst();
            activeCurrentConfigurationInternal.ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AADiracService.AnonymousClass5.this.m850lambda$onResult$4$comnothingdirac_DMPAADiracService$5((Configuration) obj);
                }
            });
            ifConfigIsActiveThenSet(activeCurrentConfigurationInternal, AADiracService.this.getAvailableInternalConfigurations(), AADiracService.this.activeConfigurationInternal);
            AADiracService.this.setAvailableInternalSystemConfigurations((List) configurations.stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda14
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getOutputType().equals(OutputType.INTERNAL_SPEAKER);
                    return equals;
                }
            }).filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda15
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getStreamType().equals(StreamType.SYSTEM_SOUND);
                    return equals;
                }
            }).collect(Collectors.toList()));
            Optional<Configuration> activeCurrentConfigurationInternalSystem = ((List) AADiracService.this.getAvailableInternalSystemConfigurations().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).collect(Collectors.toList())).stream().findFirst();
            activeCurrentConfigurationInternalSystem.ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AADiracService.AnonymousClass5.this.m851lambda$onResult$7$comnothingdirac_DMPAADiracService$5((Configuration) obj);
                }
            });
            ifConfigIsActiveThenSet(activeCurrentConfigurationInternalSystem, AADiracService.this.getAvailableInternalSystemConfigurations(), AADiracService.this.activeConfigurationInternalSystem);
            AADiracService.this.setAvailableExternalConfigurations((List) configurations.stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getOutputType().equals(OutputType.HEADSET);
                    return equals;
                }
            }).filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getStreamType().equals(StreamType.GENERAL);
                    return equals;
                }
            }).collect(Collectors.toList()));
            Optional<Configuration> activeCurrentConfigurationExternal = ((List) AADiracService.this.getAvailableExternalConfigurations().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).collect(Collectors.toList())).stream().findFirst();
            activeCurrentConfigurationExternal.ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AADiracService.AnonymousClass5.this.m848lambda$onResult$10$comnothingdirac_DMPAADiracService$5((Configuration) obj);
                }
            });
            ifConfigIsActiveThenSet(activeCurrentConfigurationExternal, AADiracService.this.getAvailableExternalConfigurations(), AADiracService.this.activeConfigurationExternal);
            AADiracService.this.setAvailableExternalSystemConfigurations((List) configurations.stream().filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getOutputType().equals(OutputType.HEADSET);
                    return equals;
                }
            }).filter(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda7
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((Configuration) obj).getStreamType().equals(StreamType.SYSTEM_SOUND);
                    return equals;
                }
            }).collect(Collectors.toList()));
            Optional<Configuration> activeCurrentConfigurationExternalSystem = ((List) AADiracService.this.getAvailableExternalSystemConfigurations().stream().filter(new AADiracService$$ExternalSyntheticLambda0()).collect(Collectors.toList())).stream().findFirst();
            activeCurrentConfigurationExternalSystem.ifPresent(new Consumer() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AADiracService.AnonymousClass5.this.m849lambda$onResult$13$comnothingdirac_DMPAADiracService$5((Configuration) obj);
                }
            });
            ifConfigIsActiveThenSet(activeCurrentConfigurationExternalSystem, AADiracService.this.getAvailableExternalSystemConfigurations(), AADiracService.this.activeConfigurationExternalSystem);
            AADiracService.this.listOfAvailableOutputTypes.setValue(AADiracService.this.availableOutputTypes);
            addOrRemoveFromList(AADiracService.this.getAvailableInternalConfigurations(), AADiracService.this.listOfInternalSpeakerConfigs, "Internal");
            addOrRemoveFromList(AADiracService.this.getAvailableInternalSystemConfigurations(), AADiracService.this.listOfInternalSpeakerSystemConfigs, AADiracService.INTERNAL_SYS_SOUND);
            addOrRemoveFromList(AADiracService.this.getAvailableExternalConfigurations(), AADiracService.this.listOfExternalSpeakerConfigs, "External");
            addOrRemoveFromList(AADiracService.this.getAvailableExternalSystemConfigurations(), AADiracService.this.listOfExternalSpeakerSystemConfigs, AADiracService.EXTERNAL_SYS_SOUND);
            AADiracService.this.getLatestConfiguration(this.val$context);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Configuration lambda$onResult$0(Configuration p) {
            return p;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Configuration lambda$onResult$1(Configuration p, Configuration q) {
            return p;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResult$4$com-nothing-dirac_DMP-AADiracService$5, reason: not valid java name */
        public /* synthetic */ void m850lambda$onResult$4$comnothingdirac_DMPAADiracService$5(Configuration configuration) {
            AADiracService.this.activeConfigurationInternal.setValue(configuration);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResult$7$com-nothing-dirac_DMP-AADiracService$5, reason: not valid java name */
        public /* synthetic */ void m851lambda$onResult$7$comnothingdirac_DMPAADiracService$5(Configuration configuration) {
            AADiracService.this.activeConfigurationInternalSystem.setValue(configuration);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResult$10$com-nothing-dirac_DMP-AADiracService$5, reason: not valid java name */
        public /* synthetic */ void m848lambda$onResult$10$comnothingdirac_DMPAADiracService$5(Configuration configuration) {
            AADiracService.this.activeConfigurationExternal.setValue(configuration);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResult$13$com-nothing-dirac_DMP-AADiracService$5, reason: not valid java name */
        public /* synthetic */ void m849lambda$onResult$13$comnothingdirac_DMPAADiracService$5(Configuration configuration) {
            AADiracService.this.activeConfigurationExternalSystem.setValue(configuration);
        }

        private void addOrRemoveFromList(List<Configuration> availableConfigurations, MutableLiveData<List<Configuration>> listOfSpeakerConfigs, final String generalOrSystem) {
            if (availableConfigurations.isEmpty()) {
                listOfSpeakerConfigs.setValue(null);
                AADiracService.this.getAvailableOutputTypesInStrings().removeIf(new Predicate() { // from class: com.nothing.dirac_DMP.AADiracService$5$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean equals;
                        equals = ((String) obj).equals(generalOrSystem);
                        return equals;
                    }
                });
            } else {
                listOfSpeakerConfigs.setValue(availableConfigurations);
            }
        }

        private void ifConfigIsActiveThenSet(Optional<Configuration> activeCurrentConfigurationInternal, List<Configuration> availableInternalConfigurations, MutableLiveData<Configuration> activeConfigurationInternal) {
            if (!activeCurrentConfigurationInternal.isPresent() && !availableInternalConfigurations.isEmpty()) {
                activeConfigurationInternal.setValue(availableInternalConfigurations.get(0));
            }
        }

        private void getAllTypesAsStrings() {
            for (int i = 0; i < AADiracService.this.availableOutputTypes.size(); i++) {
                AADiracService.this.getAvailableOutputTypesInStrings().add(((OutputType) AADiracService.this.availableOutputTypes.get(i)).toString());
            }
            AADiracService.this.getAvailableOutputTypesInStrings().add(AADiracService.INTERNAL_SYS_SOUND);
            AADiracService.this.getAvailableOutputTypesInStrings().add(AADiracService.EXTERNAL_SYS_SOUND);
        }

        @Override // com.dirac.settings.api.Result
        public void onFailure(RuntimeException exception) {
            AADiracService.this.onAcsInSafeMode();
        }
    }

    public void getConfigurationsAsync(Context context) {
        Log.d(TAG, new Throwable().getStackTrace()[0].getMethodName());
        this.mService.listConfigurationsAsync(new AnonymousClass5(context));
    }

    public void setEnabled(Context context, boolean diracOn) {
        Log.d(TAG, new Throwable().getStackTrace()[0].getMethodName() + ": " + diracOn);
        try {
            this.mService.setEnabled(getCurrentlySelectedOutputType(), diracOn);
        } catch (SafeModeException e) {
            onAcsInSafeMode();
        }
    }

    public void getLatestConfiguration(Context context) {
        Log.d(TAG, new Throwable().getStackTrace()[0].getMethodName());
        try {
            List<Configuration> configurations = this.mService.getLatestConfigurations();
            if (configurations.isEmpty()) {
                return;
            }
            this.latestUsedConfiguration.setValue(configurations.get(0));
        } catch (SafeModeException e) {
            onAcsInSafeMode();
        }
    }

    private static synchronized OutputType getCurrentlySelectedOutputType() {
        OutputType outputType;
        synchronized (AADiracService.class) {
            outputType = currentlySelectedOutputType;
        }
        return outputType;
    }

    private static synchronized String getCurrentlySelectedSysSoundType() {
        String str;
        synchronized (AADiracService.class) {
            str = currentlySelectedSysSoundType;
        }
        return str;
    }

    private synchronized void setCurrentlySelectedOutputType(OutputType currentlySelectedOutputType2) {
        currentlySelectedOutputType = currentlySelectedOutputType2;
    }

    private synchronized void setCurrentlySelectedSysSoundType(String currentlySelectedSysSoundType2) {
        currentlySelectedSysSoundType = currentlySelectedSysSoundType2;
    }

    public List<Configuration> getAvailableInternalConfigurations() {
        return this.availableInternalConfigurations;
    }

    public void setAvailableInternalConfigurations(List<Configuration> availableInternalConfigurations) {
        this.availableInternalConfigurations = availableInternalConfigurations;
    }

    public List<Configuration> getAvailableInternalSystemConfigurations() {
        return this.availableInternalSystemConfigurations;
    }

    public void setAvailableInternalSystemConfigurations(List<Configuration> availableInternalSystemConfigurations) {
        this.availableInternalSystemConfigurations = availableInternalSystemConfigurations;
    }

    public List<Configuration> getAvailableExternalConfigurations() {
        return this.availableExternalConfigurations;
    }

    public void setAvailableExternalConfigurations(List<Configuration> availableExternalConfigurations) {
        this.availableExternalConfigurations = availableExternalConfigurations;
    }

    public List<Configuration> getAvailableExternalSystemConfigurations() {
        return this.availableExternalSystemConfigurations;
    }

    public void setAvailableExternalSystemConfigurations(List<Configuration> availableExternalSystemConfigurations) {
        this.availableExternalSystemConfigurations = availableExternalSystemConfigurations;
    }

    public List<String> getAvailableOutputTypesInStrings() {
        return this.availableOutputTypesInStrings;
    }

    public void setConfigurationAsync(String name, Context context) {
        Log.d(TAG, new Throwable().getStackTrace()[0].getMethodName());
        this.mService.setConfigurationAsync(name, new Result<Configuration>() { // from class: com.nothing.dirac_DMP.AADiracService.6
            @Override // com.dirac.settings.api.Result
            public void onResult(Configuration configuration) {
                if (configuration != null) {
                    ifResultIsNotNull(configuration);
                }
            }

            private void ifResultIsNotNull(Configuration configuration) {
                if (configuration.getOutputType().equals(OutputType.INTERNAL_SPEAKER)) {
                    if (configuration.getStreamType().equals(StreamType.GENERAL)) {
                        AADiracService.this.activeConfigurationInternal.setValue(configuration);
                    } else {
                        AADiracService.this.activeConfigurationInternalSystem.setValue(configuration);
                    }
                } else if (configuration.getOutputType().equals(OutputType.HEADSET)) {
                    if (configuration.getStreamType().equals(StreamType.GENERAL)) {
                        AADiracService.this.activeConfigurationExternal.setValue(configuration);
                    } else {
                        AADiracService.this.activeConfigurationExternalSystem.setValue(configuration);
                    }
                }
                AADiracService.this.latestUsedConfiguration.setValue(configuration);
            }

            @Override // com.dirac.settings.api.Result
            public void onFailure(RuntimeException exception) {
                AADiracService.this.onAcsInSafeMode();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomerHeadsetEq(int band, float value) {
    }

    private boolean checkIsWired() {
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        AudioDeviceInfo[] devices = audioManager.getDevices(2);
        for (AudioDeviceInfo device : devices) {
            int deviceType = device.getType();
            if (deviceType == 3 || deviceType == 4 || deviceType == 8 || deviceType == 7) {
                Log.d(TAG, "deviceType: " + deviceType);
                return true;
            }
        }
        return false;
    }

    public int getHeadsetStatus() {
        AudioManager audoManager = (AudioManager) getSystemService("audio");
        if (audoManager.isWiredHeadsetOn()) {
            return 1;
        }
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (ba == null) {
            return -1;
        }
        if (ba.isEnabled()) {
            int a2dp = ba.getProfileConnectionState(2);
            ba.getProfileConnectionState(1);
            ba.getProfileConnectionState(3);
            int flag = -1;
            if (a2dp == 2) {
                flag = a2dp;
            }
            if (flag != -1) {
                Log.d(TAG, "bluetoothHeadset on");
                return 2;
            }
            return -2;
        }
        return -2;
    }

    private void initEqBand() {
        String[] strPresets = getApplicationContext().getResources().getStringArray(this.eqBandCount <= 7 ? R.array.eq_preset_values : R.array.eq_preset_values_10);
        this.eq_preset = (float[][]) Array.newInstance((Class<?>) Float.TYPE, strPresets.length, this.eqBandCount);
        for (int i = 0; i < strPresets.length; i++) {
            String[] s = strPresets[i].split(";");
            if (this.eqBandCount != s.length) {
                throw new IllegalStateException("wrong amounts of bands in preset " + i);
            }
            for (int band = 0; band < this.eqBandCount; band++) {
                this.eq_preset[i][band] = Float.parseFloat(s[band]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEffectOnOff(boolean isOn) {
        if (isOn) {
            diracOn();
        } else {
            diracOff();
        }
        enableExternalEffect();
        saveDiracEnableStatus(isOn);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    private void registerPrintAllStatus() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PRINT_ALL_STATUS);
        registerReceiver(this.receivePrintAllStatus, intentFilter);
    }

    private void registerBroadcastForDiracOn() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.DIRAC_ON);
        registerReceiver(this.receiveDiracOn, intentFilter);
    }

    private void registerBroadcastForDiracOff() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.DIRAC_OFF);
        registerReceiver(this.receiveDiracOff, intentFilter);
    }

    private void registerHeadsetPlugReceiver() {
        this.headsetPlugReceiver = new HeadsetPlugReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.HEADSET_PLUG");
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        registerReceiver(this.headsetPlugReceiver, filter);
    }

    private void registerNothingBTReceiver() {
        this.mNothingBTConnected = new NothingBTConnected();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.headset.action.NOTHING_HEADSET_CONNECTION");
        registerReceiver(this.mNothingBTConnected, filter);
    }

    private void registerBlueToothHeadsetReceiver() {
        this.mBlueToothHeadsetReceiver = new BlueToothHeadsetReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
        registerReceiver(this.mBlueToothHeadsetReceiver, filter);
    }

    private void updateEffectMode(String mode_name, OutputType type) {
        if (this.mService != null) {
            Log.d(TAG, "updateEffectMode  mode_name:" + mode_name + " type:" + type);
            if (type == OutputType.INTERNAL_SPEAKER) {
                for (Configuration config : this.availableInternalConfigurations) {
                    if (config.getName().equalsIgnoreCase(mode_name)) {
                        setConfigurationAsync(config.getName(), this);
                    }
                }
                enableInternalEffect();
                return;
            }
            if (type == OutputType.HEADSET) {
                for (Configuration config2 : this.availableExternalConfigurations) {
                    if (config2.getName().equalsIgnoreCase(mode_name)) {
                        setConfigurationAsync(config2.getName(), this);
                    }
                }
                enableExternalEffect();
            }
        }
    }

    private void setEffectInAutoMode() {
        Log.d(TAG, "setEffectInAutoMode");
        this.currentMode = 0;
        savePresentValue(0);
        Intent intent = new Intent(SET_AUTO_MODE);
        sendBroadcast(intent);
    }

    public void disableInternal() {
        AudioControlService audioControlService = this.mService;
        if (audioControlService != null) {
            try {
                audioControlService.setEnabled(OutputType.INTERNAL_SPEAKER, false);
                Log.d(TAG, "[disableInternal]");
            } catch (SafeModeException e) {
                onAcsInSafeMode();
            } catch (Exception exception) {
                Log.d(TAG, "[disableInternal] failed due to " + exception);
            }
        }
    }

    public void disableExternal() {
        AudioControlService audioControlService = this.mService;
        if (audioControlService != null) {
            try {
                audioControlService.setEnabled(OutputType.HEADSET, false);
                Log.d(TAG, "[disableExternal]");
            } catch (SafeModeException e) {
                onAcsInSafeMode();
            } catch (Exception exception) {
                Log.d(TAG, "[disableExternal] failed due to " + exception);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableExternalEffect() {
        AudioControlService audioControlService = this.mService;
        if (audioControlService != null) {
            try {
                audioControlService.setEnabled(OutputType.HEADSET, true);
                Log.d(TAG, "[enableExternalEffect]");
            } catch (SafeModeException e) {
                onAcsInSafeMode();
            } catch (Exception exception) {
                Log.d(TAG, "[enableExternalEffect] failed due to " + exception);
            }
        }
    }

    private void enableInternalEffect() {
        AudioControlService audioControlService = this.mService;
        if (audioControlService != null) {
            try {
                audioControlService.setEnabled(OutputType.INTERNAL_SPEAKER, true);
                Log.d(TAG, "[enableInternalEffect]");
            } catch (SafeModeException e) {
                onAcsInSafeMode();
            } catch (Exception exception) {
                Log.d(TAG, "[enableInternalEffect] failed due to " + exception);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void diracOn() {
        if (this.currentMode == 0) {
            setMusicMode();
        }
        Log.i(TAG, "diracOn");
        updateEffect(this.currentMode, false);
    }

    public void diracOff() {
        String str = TAG;
        Log.i(str, "diracOff");
        Log.d(str, "[diracOff] current_dirac_case_id=" + this.current_dirac_case_id);
        if (getHeadsetStatus() == 1) {
            disableExternal();
        } else if (getHeadsetStatus() == 2) {
            Log.i(str, "[diracOff] cureent device is bluetooth, do nothing here,will not process data in FW");
        } else {
            enableInternalEffect();
            Log.d(str, "[diracoff] enableInternalEffect.");
        }
    }

    public void setMusicMode() {
        this.current_dirac_case_id = 1;
        String str = TAG;
        Log.d(str, "[setMusicMode] current_dirac_case_id=" + this.current_dirac_case_id);
        if (getHeadsetStatus() == 1) {
            if (getDiracEnableStatus()) {
                Log.d(str, "headset music mode");
                updateEffectMode("EXT_1", OutputType.HEADSET);
                return;
            } else {
                diracOff();
                return;
            }
        }
        if (getHeadsetStatus() == 2) {
            Log.i(str, "[setMusicMode] cureent device is bluetooth,not call diracOff");
        } else {
            Log.d(str, "speaker music mode");
            updateEffectMode("MUSIC", OutputType.INTERNAL_SPEAKER);
        }
    }

    public void setMovieMode() {
        this.current_dirac_case_id = 2;
        String str = TAG;
        Log.d(str, "[setMovieMode] current_dirac_case_id=" + this.current_dirac_case_id);
        if (getHeadsetStatus() != 1) {
            if (getHeadsetStatus() == 2) {
                Log.i(str, "[setMovieMode] cureent device is bluetooth,not call diracOff");
                return;
            } else {
                Log.d(str, "speaker movie mode");
                updateEffectMode("MOVIE", OutputType.INTERNAL_SPEAKER);
                return;
            }
        }
        if (getDiracEnableStatus()) {
            Log.d(str, "headset movie mode");
            updateEffectMode("EXT_2", OutputType.HEADSET);
        } else {
            diracOff();
        }
    }

    public void setNavigateMode() {
        this.current_dirac_case_id = 3;
        String str = TAG;
        Log.d(str, "[setNavigateMode] current_dirac_case_id=" + this.current_dirac_case_id);
        if (getHeadsetStatus() == 1) {
            if (getDiracEnableStatus()) {
                Log.d(str, "headset navigate mode");
                updateEffectMode("EXT_3", OutputType.HEADSET);
                return;
            } else {
                diracOff();
                return;
            }
        }
        if (getHeadsetStatus() == 2) {
            Log.i(str, "[setNavigateMode] cureent device is bluetooth,not call diracOff");
        } else {
            Log.d(str, "speaker navigate mode");
            updateEffectMode("SPEECH", OutputType.INTERNAL_SPEAKER);
        }
    }

    public void setGameMode() {
        this.current_dirac_case_id = 4;
        String str = TAG;
        Log.d(str, "[setGameMode] current_dirac_case_id=" + this.current_dirac_case_id);
        if (getHeadsetStatus() == 1) {
            if (getDiracEnableStatus()) {
                Log.d(str, "headset game mode");
                updateEffectMode("EXT_4", OutputType.HEADSET);
                return;
            } else {
                diracOff();
                return;
            }
        }
        if (getHeadsetStatus() == 2) {
            Log.i(str, "[setGameMode] cureent device is bluetooth,not call diracOff");
        } else {
            Log.d(str, "speaker game mode");
            updateEffectMode("GAME", OutputType.INTERNAL_SPEAKER);
        }
    }

    public boolean getAutoMode() {
        return this.currentMode == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEffect(int NewPresetID, boolean shouldSave) {
        switch (NewPresetID) {
            case -1:
                diracOff();
                break;
            case 0:
                setEffectInAutoMode();
                break;
            case 1:
                setMusicMode();
                break;
            case 2:
                setMovieMode();
                break;
            case 3:
                setNavigateMode();
                break;
            case 4:
                setGameMode();
                break;
            default:
                Log.i(TAG, "[updateEffect] invalid parameter Num=" + NewPresetID);
                return;
        }
        if (shouldSave) {
            savePresentValue(NewPresetID);
        }
        reportStaus();
    }

    private void savePresentValue(int value) {
        this.currentMode = value;
        SharedPreferences.Editor et = this.preset_preference.edit();
        et.putInt(CURRENT_MODE, value);
        et.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDiracEnableStatus(boolean enable) {
        this.diracStatus = enable;
        SharedPreferences.Editor et = this.preset_preference.edit();
        et.putBoolean(DIRAC_STATE, this.diracStatus);
        et.apply();
    }

    public boolean getDiracEnableStatus() {
        boolean bDiracStatus = this.preset_preference.getBoolean(DIRAC_STATE, true);
        String str = TAG;
        Log.d(str, "getDiracEnableStatus bDiracStatus = " + bDiracStatus + " from preset_preference");
        if (this.diracStatus) {
            Log.d(str, "getDiracEnableStatus  DiracStatus is On ");
        } else {
            Log.d(str, "getDiracEnableStatus  DiracStatus is Off ");
        }
        if (getHeadsetStatus() == 1) {
            Log.d(str, "getDiracEnableStatus  DiracStatus return " + this.diracStatus + " when headset");
            return this.diracStatus;
        }
        if (getHeadsetStatus() == 2) {
            Log.d(str, "getDiracEnableStatus  DiracStatus always return false when bluetooth");
            return false;
        }
        Log.d(str, "getDiracEnableStatus  DiracStatus always return true when Speaker");
        return true;
    }

    protected void onAcsInSafeMode() {
        try {
            AudioControlService audioControlService = this.mService;
            if (audioControlService != null && audioControlService.isInSafeMode()) {
                this.mService.killAudioControlService(this);
            }
        } catch (Exception e) {
            Log.e(TAG, "Restart Audio Control Service due to: " + e);
        }
        Log.d(TAG, "ACS is in safe-mode");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class NothingBTConnected extends BroadcastReceiver {
        NothingBTConnected() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class BlueToothHeadsetReceiver extends BroadcastReceiver {
        BlueToothHeadsetReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int StreamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
            int StreamDevices = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_DEVICES", -1);
            if (StreamType == 3) {
                Log.d(AADiracService.TAG, "StreamDevices=" + StreamDevices + " StreamType=" + StreamType);
                if (StreamDevices == 128 || StreamDevices == 256 || StreamDevices == 512) {
                    AADiracService.this.BTACTIVESTATUS = 1;
                    Log.d(AADiracService.TAG, "diracoff BTACTIVESTATUS=" + AADiracService.this.BTACTIVESTATUS);
                    return;
                }
                if (StreamDevices == 2) {
                    AADiracService.this.BTACTIVESTATUS = 0;
                    Log.d(AADiracService.TAG, "BTACTIVESTATUS=" + AADiracService.this.BTACTIVESTATUS);
                } else if (StreamDevices == 4 || StreamDevices == 8 || StreamDevices == 16384 || StreamDevices == 67108864) {
                    if (AADiracService.this.getDiracEnableStatus()) {
                        AADiracService.this.enableExternalEffect();
                    } else {
                        AADiracService.this.disableExternal();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class HeadsetPlugReceiver extends BroadcastReceiver {
        HeadsetPlugReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.HEADSET_PLUG") || intent.getAction().equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                if (intent.getAction().equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                    int bluetoothHeadsetState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                    Log.d(AADiracService.TAG, "bluetoothHeadsetState==" + bluetoothHeadsetState);
                    if (AADiracService.this.BTACTIVESTATUS == 1 && bluetoothHeadsetState == 2) {
                        AADiracService.this.diracOff();
                        return;
                    } else if (bluetoothHeadsetState == 1) {
                        return;
                    }
                }
                if (intent.hasExtra("state")) {
                    if (intent.getIntExtra("state", 0) == 0) {
                        Log.d(AADiracService.TAG, "headset not connected");
                    } else if (intent.getIntExtra("state", 0) == 1) {
                        Log.d(AADiracService.TAG, "headset connected");
                    }
                }
                Log.d(AADiracService.TAG, intent.getAction());
                AADiracService.this.mHandler.sendEmptyMessageDelayed(2, 100L);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class SettingsObserver extends ContentObserver {
        SettingsObserver() {
            super(new Handler());
            AADiracService.this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(AADiracService.AUDIO_EFFECT_ENABLED), false, this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            boolean diracEffectShouldEnabled = Settings.Secure.getIntForUser(AADiracService.this.mContentResolver, AADiracService.AUDIO_EFFECT_ENABLED, 1, -2) == 1;
            AADiracService.this.saveDiracEnableStatus(diracEffectShouldEnabled);
            if (diracEffectShouldEnabled) {
                Log.d(AADiracService.TAG, "User enable dirac in setting app");
                if (AADiracService.this.getHeadsetStatus() == 1) {
                    AADiracService.this.updateEffect(0, false);
                    return;
                } else if (AADiracService.this.getHeadsetStatus() == 2) {
                    Log.d(AADiracService.TAG, "current device is bluetooth,do nothing");
                    return;
                } else {
                    Log.d(AADiracService.TAG, "current device is speaker,do nothing");
                    return;
                }
            }
            Log.d(AADiracService.TAG, "User disable dirac in setting app");
            if (AADiracService.this.getHeadsetStatus() == 1 || AADiracService.this.getHeadsetStatus() == 2) {
                Log.d(AADiracService.TAG, "current device is headset,so diracoff");
                AADiracService.this.diracOff();
            } else {
                Log.d(AADiracService.TAG, "current device is speaker,do nothing");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportStaus() {
        if (this.diracStatus) {
            Log.d(TAG, "DiracStatus is On currentMode=" + this.currentMode + " current_dirac_case_id=" + this.current_dirac_case_id);
        } else {
            Log.d(TAG, "DiracStatus is Off currentMode=" + this.currentMode + " current_dirac_case_id=" + this.current_dirac_case_id);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        AudioControlService audioControlService = this.mService;
        if (audioControlService != null) {
            audioControlService.close(this);
            this.mService = null;
        }
        unregisterReceiver(this.receivePrintAllStatus);
        unregisterReceiver(this.headsetPlugReceiver);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(1);
        super.onDestroy();
    }
}
