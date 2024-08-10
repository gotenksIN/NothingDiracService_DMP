package com.dirac.android;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.dirac.android.exceptions.CouldNotBindException;
import com.dirac.wrapper.ServiceManager;

/* loaded from: classes4.dex */
public class SystemServiceWatcher implements AutoCloseable {
    public static final String SERVICE_AUDIOPOLICY = "media.audio_policy";
    private IBinder bind;
    private boolean isUnLinked;
    private final LifeEvents lifeEvents;
    private final String serviceName;
    private final Runnable testForService;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.dirac.android.SystemServiceWatcher.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            SystemServiceWatcher.this.bind = null;
            if (!SystemServiceWatcher.this.isUnLinked) {
                SystemServiceWatcher.this.lifeEvents.onDeath();
                SystemServiceWatcher.this.testForService.run();
            }
        }
    };

    /* loaded from: classes4.dex */
    public interface LifeEvents {
        void onBirth();

        void onDeath();
    }

    public SystemServiceWatcher(String serviceName, LifeEvents lifeEvents) {
        Runnable runnable = new Runnable() { // from class: com.dirac.android.SystemServiceWatcher.2
            @Override // java.lang.Runnable
            public void run() {
                if (SystemServiceWatcher.this.bind != null) {
                    throw new CouldNotBindException();
                }
                SystemServiceWatcher systemServiceWatcher = SystemServiceWatcher.this;
                systemServiceWatcher.bind = ServiceManager.getService(systemServiceWatcher.serviceName);
                if (SystemServiceWatcher.this.bind != null) {
                    SystemServiceWatcher.this.lifeEvents.onBirth();
                    try {
                        SystemServiceWatcher.this.bind.linkToDeath(SystemServiceWatcher.this.deathRecipient, 1);
                        return;
                    } catch (RemoteException e) {
                        SystemServiceWatcher.this.bind = null;
                    }
                }
                SystemServiceWatcher.this.mainHandler.postDelayed(SystemServiceWatcher.this.testForService, 1000L);
            }
        };
        this.testForService = runnable;
        if (lifeEvents == null) {
            throw new NullPointerException();
        }
        this.lifeEvents = lifeEvents;
        this.serviceName = serviceName;
        runnable.run();
        this.isUnLinked = false;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mainHandler.removeCallbacks(this.testForService);
        IBinder iBinder = this.bind;
        if (iBinder != null) {
            iBinder.unlinkToDeath(this.deathRecipient, 1);
            this.isUnLinked = true;
        }
    }
}
