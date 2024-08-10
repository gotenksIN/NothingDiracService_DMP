package com.nothing.dirac_DMP;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IAADiracService extends IInterface {
    public static final String DESCRIPTOR = "com.nothing.dirac_DMP.IAADiracService";

    void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException;

    boolean getEffectEnable() throws RemoteException;

    int getPreset() throws RemoteException;

    void setCustomerEq(int i, float f) throws RemoteException;

    void setEffectEnable(boolean z) throws RemoteException;

    void setPreset(int i) throws RemoteException;

    boolean setPresetEq(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IAADiracService {
        @Override // com.nothing.dirac_DMP.IAADiracService
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setEffectEnable(boolean activate) throws RemoteException {
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public boolean getEffectEnable() throws RemoteException {
            return false;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setPreset(int NewPresetID) throws RemoteException {
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public int getPreset() throws RemoteException {
            return 0;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public boolean setPresetEq(int preset) throws RemoteException {
            return false;
        }

        @Override // com.nothing.dirac_DMP.IAADiracService
        public void setCustomerEq(int band, float value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IAADiracService {
        static final int TRANSACTION_basicTypes = 1;
        static final int TRANSACTION_getEffectEnable = 3;
        static final int TRANSACTION_getPreset = 5;
        static final int TRANSACTION_setCustomerEq = 7;
        static final int TRANSACTION_setEffectEnable = 2;
        static final int TRANSACTION_setPreset = 4;
        static final int TRANSACTION_setPresetEq = 6;

        public Stub() {
            attachInterface(this, IAADiracService.DESCRIPTOR);
        }

        public static IAADiracService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAADiracService.DESCRIPTOR);
            if (iin != null && (iin instanceof IAADiracService)) {
                return (IAADiracService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAADiracService.DESCRIPTOR);
            }
            switch (i) {
                case 1598968902:
                    parcel2.writeString(IAADiracService.DESCRIPTOR);
                    return true;
                default:
                    boolean z2 = false;
                    switch (i) {
                        case 1:
                            int readInt = parcel.readInt();
                            long readLong = parcel.readLong();
                            if (parcel.readInt() != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            basicTypes(readInt, readLong, z, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                            parcel2.writeNoException();
                            return true;
                        case 2:
                            if (parcel.readInt() != 0) {
                                z2 = true;
                            }
                            setEffectEnable(z2);
                            parcel2.writeNoException();
                            return true;
                        case 3:
                            boolean effectEnable = getEffectEnable();
                            parcel2.writeNoException();
                            parcel2.writeInt(effectEnable ? 1 : 0);
                            return true;
                        case 4:
                            setPreset(parcel.readInt());
                            parcel2.writeNoException();
                            return true;
                        case 5:
                            int preset = getPreset();
                            parcel2.writeNoException();
                            parcel2.writeInt(preset);
                            return true;
                        case 6:
                            boolean presetEq = setPresetEq(parcel.readInt());
                            parcel2.writeNoException();
                            parcel2.writeInt(presetEq ? 1 : 0);
                            return true;
                        case 7:
                            setCustomerEq(parcel.readInt(), parcel.readFloat());
                            parcel2.writeNoException();
                            return true;
                        default:
                            return super.onTransact(i, parcel, parcel2, i2);
                    }
            }
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements IAADiracService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAADiracService.DESCRIPTOR;
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeLong(aLong);
                    _data.writeInt(aBoolean ? 1 : 0);
                    _data.writeFloat(aFloat);
                    _data.writeDouble(aDouble);
                    _data.writeString(aString);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public void setEffectEnable(boolean activate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    _data.writeInt(activate ? 1 : 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public boolean getEffectEnable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _status = _reply.readInt() != 0;
                    return _status;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public void setPreset(int NewPresetID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    _data.writeInt(NewPresetID);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public int getPreset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public boolean setPresetEq(int preset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    _data.writeInt(preset);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    boolean _status = _reply.readInt() != 0;
                    return _status;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nothing.dirac_DMP.IAADiracService
            public void setCustomerEq(int band, float value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAADiracService.DESCRIPTOR);
                    _data.writeInt(band);
                    _data.writeFloat(value);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
