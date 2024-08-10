package com.dirac.settings.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.dirac.settings.api.AudioControlService;
import com.dirac.settings.api.IAudioControlService;
import com.dirac.settings.api.IDiracResult;
import com.dirac.settings.api.exceptions.CouldNotHandleConfigurationException;
import com.dirac.settings.api.exceptions.CouldNotHandleEnableException;
import com.dirac.settings.api.exceptions.CouldNotHandleParameterException;
import com.dirac.settings.api.exceptions.CouldNotHandleSafeModeException;
import java.util.List;

/* loaded from: classes4.dex */
public class AudioControlService {
    private static final String TAG = "Acs-api";
    private Result<Boolean> isAcsReady;
    private Result<Boolean> mOnDiracEnabledChangedCallbackExternal;
    private Result<Boolean> mOnDiracEnabledChangedCallbackInternal;
    private IAudioControlService mService;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final ServiceConnection mAcsConnection = new AnonymousClass1();

    public AudioControlService(Context context) {
        bind(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            AudioControlService.this.mService = IAudioControlService.Stub.asInterface(boundService);
            waitForAcsToBecomeReady(AudioControlService.this.mService);
            AudioControlService audioControlService = AudioControlService.this;
            audioControlService.handleInitialDiracEnabledCallbackForOutputType(audioControlService.mService, OutputType.INTERNAL_SPEAKER);
            AudioControlService audioControlService2 = AudioControlService.this;
            audioControlService2.handleInitialDiracEnabledCallbackForOutputType(audioControlService2.mService, OutputType.HEADSET);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            if (AudioControlService.this.isAcsReady != null) {
                AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.AnonymousClass1.this.lambda$onServiceDisconnected$0();
                    }
                });
                AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.AnonymousClass1.this.lambda$onServiceDisconnected$1();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onServiceDisconnected$0() {
            AudioControlService.this.isAcsReady.onResult(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onServiceDisconnected$1() {
            AudioControlService.this.isAcsReady.onFailure(new RuntimeException("Service disconnected"));
        }

        private void waitForAcsToBecomeReady(IAudioControlService service) {
            if (service != null && AudioControlService.this.isAcsReady != null) {
                AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.AnonymousClass1.this.lambda$waitForAcsToBecomeReady$2();
                    }
                });
                try {
                    service.onAcsReady(new BinderC00041());
                } catch (Exception exception) {
                    AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            AudioControlService.AnonymousClass1.this.lambda$waitForAcsToBecomeReady$3(exception);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$waitForAcsToBecomeReady$2() {
            AudioControlService.this.isAcsReady.onResult(false);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.dirac.settings.api.AudioControlService$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes4.dex */
        public class BinderC00041 extends IDiracResult.Stub {
            BinderC00041() {
            }

            @Override // com.dirac.settings.api.IDiracResult
            public void onResult(DiracResult result) {
                final boolean isReady = ((Boolean) result.getData()).booleanValue();
                AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.AnonymousClass1.BinderC00041.this.lambda$onResult$0(isReady);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$onResult$0(boolean isReady) {
                AudioControlService.this.isAcsReady.onResult(Boolean.valueOf(isReady));
            }

            @Override // com.dirac.settings.api.IDiracResult
            public void onFailure(Bundle exception) {
                final SafeModeException e = AudioControlService.this.bundleAsSafeModeException(exception);
                AudioControlService.this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$1$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.AnonymousClass1.BinderC00041.this.lambda$onFailure$1(e);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$onFailure$1(SafeModeException e) {
                AudioControlService.this.isAcsReady.onFailure(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$waitForAcsToBecomeReady$3(Exception exception) {
            AudioControlService.this.isAcsReady.onFailure(new RuntimeException(exception));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInitialDiracEnabledCallbackForOutputType(IAudioControlService service, OutputType outputType) {
        final Result<Boolean> onDiracEnabledChangedCallback;
        if (service == null) {
            return;
        }
        if (outputType.equals(OutputType.INTERNAL_SPEAKER) && this.mOnDiracEnabledChangedCallbackInternal != null) {
            onDiracEnabledChangedCallback = this.mOnDiracEnabledChangedCallbackInternal;
        } else if (outputType.equals(OutputType.HEADSET) && this.mOnDiracEnabledChangedCallbackExternal != null) {
            onDiracEnabledChangedCallback = this.mOnDiracEnabledChangedCallbackExternal;
        } else {
            return;
        }
        try {
            final boolean isDiracEnabled = service.isDiracEnabledForOutput(outputType);
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult(Boolean.valueOf(isDiracEnabled));
                }
            });
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e.getMessage()));
                }
            });
        }
    }

    public void acsReadyListener(Result<Boolean> isAcsReady) {
        this.isAcsReady = isAcsReady;
    }

    public void close(Context context) {
        if (context == null) {
            Log.w(TAG, "Could not unbind, context is null");
        } else {
            context.unbindService(this.mAcsConnection);
        }
    }

    @Deprecated
    public boolean isDiracEnabled() {
        try {
            return this.mService.isDiracEnabled();
        } catch (Exception e) {
            throw new CouldNotHandleEnableException(e.toString());
        }
    }

    @Deprecated
    public boolean isDiracEnabledForOutput(OutputType outputType) throws RemoteException {
        try {
            return this.mService.isDiracEnabledForOutput(outputType);
        } catch (Exception e) {
            throw new CouldNotHandleEnableException(e.getMessage());
        }
    }

    public boolean isInSafeMode() {
        try {
            return this.mService.isInSafeMode();
        } catch (Exception e) {
            throw new CouldNotHandleSafeModeException(e.toString());
        }
    }

    public void setEnabled(OutputType outputType, final boolean state) {
        try {
            Bundle out = new Bundle();
            this.mService.setEnabled(outputType, state, out);
            if (outputType.equals(OutputType.INTERNAL_SPEAKER)) {
                if (this.mOnDiracEnabledChangedCallbackInternal != null) {
                    this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            AudioControlService.this.lambda$setEnabled$2(state);
                        }
                    });
                }
            } else if (this.mOnDiracEnabledChangedCallbackExternal != null) {
                this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioControlService.this.lambda$setEnabled$3(state);
                    }
                });
            }
            handleSafeMode(out);
        } catch (Exception e) {
            throw new CouldNotHandleEnableException(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$2(boolean state) {
        this.mOnDiracEnabledChangedCallbackInternal.onResult(Boolean.valueOf(state));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$3(boolean state) {
        this.mOnDiracEnabledChangedCallbackExternal.onResult(Boolean.valueOf(state));
    }

    public void onConfigurationChanged(final Result<Configuration> result) {
        if (result == null) {
            return;
        }
        try {
            this.mService.onConfigurationChanged(new AnonymousClass2(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass2(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(DiracResult diracResult) {
            final Configuration configuration = (Configuration) diracResult.getData();
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult(configuration);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass2.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    public void onDiracEnabledChangedForOutputTypeInternal(Result<Boolean> result) {
        if (result == null) {
            return;
        }
        this.mOnDiracEnabledChangedCallbackInternal = result;
        IAudioControlService iAudioControlService = this.mService;
        if (iAudioControlService == null) {
            return;
        }
        handleInitialDiracEnabledCallbackForOutputType(iAudioControlService, OutputType.INTERNAL_SPEAKER);
        sendResultToListener(OutputType.INTERNAL_SPEAKER, result);
    }

    public void onDiracEnabledChangedForOutputTypeExternal(Result<Boolean> result) {
        IAudioControlService iAudioControlService;
        if (result == null || (iAudioControlService = this.mService) == null) {
            return;
        }
        this.mOnDiracEnabledChangedCallbackExternal = result;
        handleInitialDiracEnabledCallbackForOutputType(iAudioControlService, OutputType.HEADSET);
        sendResultToListener(OutputType.HEADSET, result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass3(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(DiracResult diracResult) {
            final Boolean state = (Boolean) diracResult.getData();
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult(state);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass3.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    private void sendResultToListener(OutputType outputType, final Result<Boolean> result) {
        try {
            this.mService.onDiracEnabledChangedForOutput(outputType, new AnonymousClass3(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    public List<Configuration> listConfigurations() {
        try {
            Bundle out = new Bundle();
            List<Configuration> confs = this.mService.listConfigurations(out);
            handleSafeMode(out);
            return confs;
        } catch (Exception e) {
            throw new CouldNotHandleConfigurationException(e.toString());
        }
    }

    public Configuration getLatestConfigurationForStreamTypeAndOutputType(OutputType outputType, StreamType streamType) throws RemoteException {
        try {
            Bundle out = new Bundle();
            Configuration configuration = this.mService.getLatestConfigurationForStreamTypeAndOutputType(outputType, streamType, out);
            handleSafeMode(out);
            return configuration;
        } catch (Exception e) {
            throw new SafeModeException("Exception thrown in getLatestConfigurationForStreamTypeAndOutputType", e);
        }
    }

    @Deprecated
    public List<Configuration> getLatestConfigurationsListForOutputType(OutputType outputType) {
        try {
            Bundle out = new Bundle();
            List<Configuration> confs = this.mService.getLatestConfigurationsListForOutputType(outputType, out);
            handleSafeMode(out);
            return confs;
        } catch (Exception e) {
            throw new SafeModeException("Exception thrown in getLatestConfigurationsFromDapForOutputType", e);
        }
    }

    public List<Configuration> getLatestConfigurations() {
        try {
            Bundle out = new Bundle();
            List<Configuration> confs = this.mService.getLatestConfigurationsList(out);
            handleSafeMode(out);
            return confs;
        } catch (Exception e) {
            throw new SafeModeException("Exception thrown in getLatestConfigurationsFromDap", e);
        }
    }

    public List<ParameterInfo> getParameterInfoList(String configurationName) {
        try {
            Bundle out = new Bundle();
            List<ParameterInfo> params = this.mService.getParameterInfoList(configurationName, out);
            handleSafeMode(out);
            return params;
        } catch (Exception e) {
            throw new SafeModeException("Exception thrown in listParameters", e);
        }
    }

    public void setParameter(String configurationName, int id, float value) {
        try {
            Bundle out = new Bundle();
            this.mService.setParameter(configurationName, id, value, out);
            handleSafeMode(out);
        } catch (Exception e) {
            throw new CouldNotHandleParameterException(e.toString());
        }
    }

    public float getParameterValue(String configurationName, int id) {
        try {
            Bundle out = new Bundle();
            return this.mService.getParameterValue(configurationName, id, out);
        } catch (Exception e) {
            throw new CouldNotHandleParameterException(e.toString());
        }
    }

    @Deprecated
    public void setConfiguration(String name) {
        try {
            Bundle out = new Bundle();
            this.mService.setConfiguration(name, out);
            handleSafeMode(out);
        } catch (Exception e) {
            throw new CouldNotHandleConfigurationException(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass4(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(final DiracResult diracResult) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult((Configuration) diracResult.getData());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass4.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    public void setConfigurationAsync(String name, final Result<Configuration> result) {
        try {
            Log.i(TAG, "setConfigurationAsync " + name);
            this.mService.setConfigurationAsync(name, new AnonymousClass4(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    public void listConfigurationsAsync(final Result<List<Configuration>> result) {
        if (result == null) {
            return;
        }
        try {
            this.mService.listConfigurationsAsync(new AnonymousClass5(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass5 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass5(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(DiracResult diracResult) {
            final ConfigurationList configurationList = (ConfigurationList) diracResult.getData();
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$5$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult(configurationList.getConfigurations());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass5.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    public void setParameterAsync(String configurationName, int id, float value, final Result<Boolean> result) {
        if (result == null) {
            return;
        }
        try {
            this.mService.setParameterAsync(configurationName, id, value, new AnonymousClass6(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$6, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass6 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass6(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(final DiracResult diracResult) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$6$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult((Boolean) diracResult.getData());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$6$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass6.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.settings.api.AudioControlService$7, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass7 extends IDiracResult.Stub {
        final /* synthetic */ Result val$result;

        AnonymousClass7(Result result) {
            this.val$result = result;
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onResult(DiracResult diracResult) {
            final ParameterInfoList parameterInfoList = (ParameterInfoList) diracResult.getData();
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$7$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onResult(parameterInfoList.getListOfParameterInfo());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(Result result, Bundle exception) {
            result.onFailure(AudioControlService.this.bundleAsSafeModeException(exception));
        }

        @Override // com.dirac.settings.api.IDiracResult
        public void onFailure(final Bundle exception) {
            Handler handler = AudioControlService.this.mainThreadHandler;
            final Result result = this.val$result;
            handler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$7$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlService.AnonymousClass7.this.lambda$onFailure$1(result, exception);
                }
            });
        }
    }

    public void getParameterInfoListAsync(String configurationName, final Result<List<ParameterInfo>> result) {
        try {
            this.mService.getParameterInfoListAsync(configurationName, new AnonymousClass7(result));
        } catch (Exception e) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.dirac.settings.api.AudioControlService$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    Result.this.onFailure(new RuntimeException(e));
                }
            });
        }
    }

    private void bind(Context context) {
        Intent i = getIntent();
        context.startService(i);
        context.bindService(i, this.mAcsConnection, 0);
    }

    private void handleSafeMode(Bundle out) {
        if (out.getChar("ExceptionOccured", 'N') == 'Y') {
            StackTraceElement[] trace = (StackTraceElement[]) out.getSerializable("CauseTrace");
            String causeClass = out.getString("CauseClassName");
            String message = out.getString("CauseMessage");
            InternalServiceException ise = new InternalServiceException(causeClass + ": " + message);
            ise.setStackTrace(trace);
            throw new SafeModeException("AudioControlService is in safe mode.", ise);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SafeModeException bundleAsSafeModeException(Bundle out) {
        if (out.getChar("ExceptionOccured", 'N') == 'Y') {
            StackTraceElement[] trace = (StackTraceElement[]) out.getSerializable("CauseTrace");
            String causeClass = out.getString("CauseClassName");
            String message = out.getString("CauseMessage");
            InternalServiceException ise = new InternalServiceException(causeClass + ": " + message);
            ise.setStackTrace(trace);
            return new SafeModeException("AudioControlService is in safe mode.", ise);
        }
        return null;
    }

    private Intent getIntent() {
        return new Intent().setClassName("com.dirac.acs", "com.dirac.acs.AudioControlService");
    }

    public void killAudioControlService(Context context) {
        IAudioControlService iAudioControlService = this.mService;
        if (iAudioControlService != null) {
            try {
                iAudioControlService.killAudioControlService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        context.stopService(getIntent());
    }
}
