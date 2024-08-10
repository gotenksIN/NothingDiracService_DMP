package com.nothing.dirac_DMP;

import android.util.Log;
import com.nothing.dirac_DMP.DetectSceneService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class ThreadPool {
    private static final long THREAD_POOL_ALIVE_TIME = 30;
    private static final int THREAD_POOL_CORE_SIZE = 3;
    private static final int THREAD_POOL_MAX_SIZE = 3;
    private static final int THREAD_POOL_QUEUE_MAX_COUNT = 10;
    private boolean mBlocked;
    private final ExecutorService mExecutorService;
    private final Object mLock;
    private static final String TAG = ThreadPool.class.getSimpleName();
    private static ThreadPool sInstance = null;

    public static ThreadPool getInstance() {
        if (sInstance == null) {
            sInstance = new ThreadPool();
        }
        return sInstance;
    }

    private ThreadPool() {
        Log.d(TAG, "[ThreadPool] constructor");
        this.mLock = new Object();
        this.mBlocked = false;
        this.mExecutorService = new ThreadPoolExecutor(3, 3, THREAD_POOL_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public void block() {
        synchronized (this.mLock) {
            this.mBlocked = true;
        }
    }

    public boolean isBlocked() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mBlocked;
        }
        return z;
    }

    public void request(final DetectSceneService.Task task) {
        if (task != null) {
            Runnable newtask = new Runnable() { // from class: com.nothing.dirac_DMP.ThreadPool.1
                @Override // java.lang.Runnable
                public void run() {
                    task.run();
                    synchronized (ThreadPool.this.mLock) {
                        if (ThreadPool.this.mBlocked) {
                            try {
                                ThreadPool.this.mLock.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }
            };
            this.mExecutorService.submit(newtask);
        } else {
            Log.d(TAG, "task is null");
        }
    }
}
