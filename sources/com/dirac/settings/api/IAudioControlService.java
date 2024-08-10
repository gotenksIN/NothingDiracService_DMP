package com.dirac.settings.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.dirac.settings.api.IDiracResult;
import java.util.List;

/* loaded from: classes4.dex */
public interface IAudioControlService extends IInterface {
    Configuration getLatestConfigurationForStreamTypeAndOutputType(OutputType outputType, StreamType streamType, Bundle bundle) throws RemoteException;

    List<Configuration> getLatestConfigurationsList(Bundle bundle) throws RemoteException;

    List<Configuration> getLatestConfigurationsListForOutputType(OutputType outputType, Bundle bundle) throws RemoteException;

    List<ParameterInfo> getParameterInfoList(String str, Bundle bundle) throws RemoteException;

    void getParameterInfoListAsync(String str, IDiracResult iDiracResult) throws RemoteException;

    float getParameterValue(String str, int i, Bundle bundle) throws RemoteException;

    boolean isDiracEnabled() throws RemoteException;

    boolean isDiracEnabledForOutput(OutputType outputType) throws RemoteException;

    boolean isInSafeMode() throws RemoteException;

    void killAudioControlService() throws RemoteException;

    List<Configuration> listConfigurations(Bundle bundle) throws RemoteException;

    void listConfigurationsAsync(IDiracResult iDiracResult) throws RemoteException;

    void onAcsReady(IDiracResult iDiracResult) throws RemoteException;

    void onConfigurationChanged(IDiracResult iDiracResult) throws RemoteException;

    void onDiracEnabledChanged(IDiracResult iDiracResult) throws RemoteException;

    void onDiracEnabledChangedForOutput(OutputType outputType, IDiracResult iDiracResult) throws RemoteException;

    void setConfiguration(String str, Bundle bundle) throws RemoteException;

    void setConfigurationAsync(String str, IDiracResult iDiracResult) throws RemoteException;

    void setEnabled(OutputType outputType, boolean z, Bundle bundle) throws RemoteException;

    void setParameter(String str, int i, float f, Bundle bundle) throws RemoteException;

    void setParameterAsync(String str, int i, float f, IDiracResult iDiracResult) throws RemoteException;

    void setParameterNormalized(Configuration configuration, int i, float f, Bundle bundle) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IAudioControlService {
        @Override // com.dirac.settings.api.IAudioControlService
        public boolean isInSafeMode() throws RemoteException {
            return false;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public boolean isDiracEnabled() throws RemoteException {
            return false;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public boolean isDiracEnabledForOutput(OutputType outputType) throws RemoteException {
            return false;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setEnabled(OutputType outputType, boolean state, Bundle exceptionInfo) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void onAcsReady(IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void onConfigurationChanged(IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void onDiracEnabledChanged(IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void onDiracEnabledChangedForOutput(OutputType outputType, IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setConfiguration(String name, Bundle exceptionInfo) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setConfigurationAsync(String name, IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setParameter(String configurationName, int id, float value, Bundle exceptionInfo) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setParameterAsync(String configurationName, int id, float value, IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public List<Configuration> listConfigurations(Bundle exceptionInfo) throws RemoteException {
            return null;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public Configuration getLatestConfigurationForStreamTypeAndOutputType(OutputType outputType, StreamType streamType, Bundle exceptionInfo) throws RemoteException {
            return null;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public List<Configuration> getLatestConfigurationsListForOutputType(OutputType outputType, Bundle exceptionInfo) throws RemoteException {
            return null;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public List<Configuration> getLatestConfigurationsList(Bundle exceptionInfo) throws RemoteException {
            return null;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void listConfigurationsAsync(IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public float getParameterValue(String configurationName, int parameterId, Bundle exceptionInfo) throws RemoteException {
            return 0.0f;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void setParameterNormalized(Configuration configuration, int id, float value, Bundle exceptionInfo) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public List<ParameterInfo> getParameterInfoList(String configurationName, Bundle exceptionInfo) throws RemoteException {
            return null;
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void getParameterInfoListAsync(String configurationName, IDiracResult result) throws RemoteException {
        }

        @Override // com.dirac.settings.api.IAudioControlService
        public void killAudioControlService() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IAudioControlService {
        private static final String DESCRIPTOR = "com.dirac.settings.api.IAudioControlService";
        static final int TRANSACTION_getLatestConfigurationForStreamTypeAndOutputType = 14;
        static final int TRANSACTION_getLatestConfigurationsList = 16;
        static final int TRANSACTION_getLatestConfigurationsListForOutputType = 15;
        static final int TRANSACTION_getParameterInfoList = 20;
        static final int TRANSACTION_getParameterInfoListAsync = 21;
        static final int TRANSACTION_getParameterValue = 18;
        static final int TRANSACTION_isDiracEnabled = 2;
        static final int TRANSACTION_isDiracEnabledForOutput = 3;
        static final int TRANSACTION_isInSafeMode = 1;
        static final int TRANSACTION_killAudioControlService = 22;
        static final int TRANSACTION_listConfigurations = 13;
        static final int TRANSACTION_listConfigurationsAsync = 17;
        static final int TRANSACTION_onAcsReady = 5;
        static final int TRANSACTION_onConfigurationChanged = 6;
        static final int TRANSACTION_onDiracEnabledChanged = 7;
        static final int TRANSACTION_onDiracEnabledChangedForOutput = 8;
        static final int TRANSACTION_setConfiguration = 9;
        static final int TRANSACTION_setConfigurationAsync = 10;
        static final int TRANSACTION_setEnabled = 4;
        static final int TRANSACTION_setParameter = 11;
        static final int TRANSACTION_setParameterAsync = 12;
        static final int TRANSACTION_setParameterNormalized = 19;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAudioControlService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAudioControlService)) {
                return (IAudioControlService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            OutputType outputType;
            OutputType outputType2;
            OutputType outputType3;
            OutputType outputType4;
            StreamType streamType;
            OutputType outputType5;
            Configuration configuration;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isInSafeMode = isInSafeMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(isInSafeMode ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isDiracEnabled = isDiracEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(isDiracEnabled ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        outputType = OutputType.CREATOR.createFromParcel(parcel);
                    } else {
                        outputType = null;
                    }
                    boolean isDiracEnabledForOutput = isDiracEnabledForOutput(outputType);
                    parcel2.writeNoException();
                    parcel2.writeInt(isDiracEnabledForOutput ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        outputType2 = OutputType.CREATOR.createFromParcel(parcel);
                    } else {
                        outputType2 = null;
                    }
                    boolean z = parcel.readInt() != 0;
                    Bundle bundle = new Bundle();
                    setEnabled(outputType2, z, bundle);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAcsReady(IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConfigurationChanged(IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDiracEnabledChanged(IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        outputType3 = OutputType.CREATOR.createFromParcel(parcel);
                    } else {
                        outputType3 = null;
                    }
                    onDiracEnabledChangedForOutput(outputType3, IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    Bundle bundle2 = new Bundle();
                    setConfiguration(readString, bundle2);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle2.writeToParcel(parcel2, 1);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setConfigurationAsync(parcel.readString(), IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString2 = parcel.readString();
                    int readInt = parcel.readInt();
                    float readFloat = parcel.readFloat();
                    Bundle bundle3 = new Bundle();
                    setParameter(readString2, readInt, readFloat, bundle3);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle3.writeToParcel(parcel2, 1);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setParameterAsync(parcel.readString(), parcel.readInt(), parcel.readFloat(), IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundle4 = new Bundle();
                    List<Configuration> listConfigurations = listConfigurations(bundle4);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listConfigurations);
                    parcel2.writeInt(1);
                    bundle4.writeToParcel(parcel2, 1);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        outputType4 = OutputType.CREATOR.createFromParcel(parcel);
                    } else {
                        outputType4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        streamType = StreamType.CREATOR.createFromParcel(parcel);
                    } else {
                        streamType = null;
                    }
                    Bundle bundle5 = new Bundle();
                    Configuration latestConfigurationForStreamTypeAndOutputType = getLatestConfigurationForStreamTypeAndOutputType(outputType4, streamType, bundle5);
                    parcel2.writeNoException();
                    if (latestConfigurationForStreamTypeAndOutputType != null) {
                        parcel2.writeInt(1);
                        latestConfigurationForStreamTypeAndOutputType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    parcel2.writeInt(1);
                    bundle5.writeToParcel(parcel2, 1);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        outputType5 = OutputType.CREATOR.createFromParcel(parcel);
                    } else {
                        outputType5 = null;
                    }
                    Bundle bundle6 = new Bundle();
                    List<Configuration> latestConfigurationsListForOutputType = getLatestConfigurationsListForOutputType(outputType5, bundle6);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(latestConfigurationsListForOutputType);
                    parcel2.writeInt(1);
                    bundle6.writeToParcel(parcel2, 1);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundle7 = new Bundle();
                    List<Configuration> latestConfigurationsList = getLatestConfigurationsList(bundle7);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(latestConfigurationsList);
                    parcel2.writeInt(1);
                    bundle7.writeToParcel(parcel2, 1);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    listConfigurationsAsync(IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString3 = parcel.readString();
                    int readInt2 = parcel.readInt();
                    Bundle bundle8 = new Bundle();
                    float parameterValue = getParameterValue(readString3, readInt2, bundle8);
                    parcel2.writeNoException();
                    parcel2.writeFloat(parameterValue);
                    parcel2.writeInt(1);
                    bundle8.writeToParcel(parcel2, 1);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        configuration = Configuration.CREATOR.createFromParcel(parcel);
                    } else {
                        configuration = null;
                    }
                    int readInt3 = parcel.readInt();
                    float readFloat2 = parcel.readFloat();
                    Bundle bundle9 = new Bundle();
                    setParameterNormalized(configuration, readInt3, readFloat2, bundle9);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle9.writeToParcel(parcel2, 1);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString4 = parcel.readString();
                    Bundle bundle10 = new Bundle();
                    List<ParameterInfo> parameterInfoList = getParameterInfoList(readString4, bundle10);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(parameterInfoList);
                    parcel2.writeInt(1);
                    bundle10.writeToParcel(parcel2, 1);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    getParameterInfoListAsync(parcel.readString(), IDiracResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    killAudioControlService();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IAudioControlService {
            public static IAudioControlService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public boolean isInSafeMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInSafeMode();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public boolean isDiracEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDiracEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public boolean isDiracEnabledForOutput(OutputType outputType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (outputType != null) {
                        _data.writeInt(1);
                        outputType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDiracEnabledForOutput(outputType);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setEnabled(OutputType outputType, boolean state, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (outputType != null) {
                        _data.writeInt(1);
                        outputType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!state) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEnabled(outputType, state, exceptionInfo);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void onAcsReady(IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAcsReady(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void onConfigurationChanged(IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigurationChanged(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void onDiracEnabledChanged(IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDiracEnabledChanged(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void onDiracEnabledChangedForOutput(OutputType outputType, IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (outputType != null) {
                        _data.writeInt(1);
                        outputType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDiracEnabledChangedForOutput(outputType, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setConfiguration(String name, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConfiguration(name, exceptionInfo);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setConfigurationAsync(String name, IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConfigurationAsync(name, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setParameter(String configurationName, int id, float value, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configurationName);
                    _data.writeInt(id);
                    _data.writeFloat(value);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setParameter(configurationName, id, value, exceptionInfo);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setParameterAsync(String configurationName, int id, float value, IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configurationName);
                    _data.writeInt(id);
                    _data.writeFloat(value);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setParameterAsync(configurationName, id, value, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public List<Configuration> listConfigurations(Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listConfigurations(exceptionInfo);
                    }
                    _reply.readException();
                    List<Configuration> _result = _reply.createTypedArrayList(Configuration.CREATOR);
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public Configuration getLatestConfigurationForStreamTypeAndOutputType(OutputType outputType, StreamType streamType, Bundle exceptionInfo) throws RemoteException {
                Configuration _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (outputType != null) {
                        _data.writeInt(1);
                        outputType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (streamType != null) {
                        _data.writeInt(1);
                        streamType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestConfigurationForStreamTypeAndOutputType(outputType, streamType, exceptionInfo);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Configuration.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public List<Configuration> getLatestConfigurationsListForOutputType(OutputType outputType, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (outputType != null) {
                        _data.writeInt(1);
                        outputType.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestConfigurationsListForOutputType(outputType, exceptionInfo);
                    }
                    _reply.readException();
                    List<Configuration> _result = _reply.createTypedArrayList(Configuration.CREATOR);
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public List<Configuration> getLatestConfigurationsList(Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestConfigurationsList(exceptionInfo);
                    }
                    _reply.readException();
                    List<Configuration> _result = _reply.createTypedArrayList(Configuration.CREATOR);
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void listConfigurationsAsync(IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().listConfigurationsAsync(result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public float getParameterValue(String configurationName, int parameterId, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configurationName);
                    _data.writeInt(parameterId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameterValue(configurationName, parameterId, exceptionInfo);
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void setParameterNormalized(Configuration configuration, int id, float value, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (configuration != null) {
                        _data.writeInt(1);
                        configuration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(id);
                    _data.writeFloat(value);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setParameterNormalized(configuration, id, value, exceptionInfo);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public List<ParameterInfo> getParameterInfoList(String configurationName, Bundle exceptionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configurationName);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameterInfoList(configurationName, exceptionInfo);
                    }
                    _reply.readException();
                    List<ParameterInfo> _result = _reply.createTypedArrayList(ParameterInfo.CREATOR);
                    if (_reply.readInt() != 0) {
                        exceptionInfo.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void getParameterInfoListAsync(String configurationName, IDiracResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configurationName);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getParameterInfoListAsync(configurationName, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.dirac.settings.api.IAudioControlService
            public void killAudioControlService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().killAudioControlService();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAudioControlService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAudioControlService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
