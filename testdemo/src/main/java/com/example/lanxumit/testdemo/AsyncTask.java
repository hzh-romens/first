package com.example.lanxumit.testdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lanxumit on 2017/4/27.
 */

public abstract class AsyncTask<Params, Progress, Result> {
    //获取CPU数量
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //线程池大小
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    //线程池的最大size
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    private static final ThreadFactory factory = new ThreadFactory() {
        private final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "AsyncTask #" + ATOMIC_INTEGER.getAndIncrement());
        }
    };

    //线程队列及其大小
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingDeque<Runnable>(128);
    //线程池（用来并发处理线程的）
    public static final Executor THREAD_POOL_EXECUTOR;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, sPoolWorkQueue, factory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    //用来顺序处理线程
    private static final Executor SERIAL_EXECUTOR = new SerialExecutor();
    private static final int MESSAGE_POST_RESULT = 0x1;
    private static final int MESSAGE_POST_PROGRESS = 0x2;
    private static volatile Executor sDefaultExecutor = SERIAL_EXECUTOR;
    private static InternalHadler sHadler;
   // private final WorkerRunnable<Params, Result> mWorker;
    //private final FutureTask<Result> mFuture;
    private volatile Status mStatus = Status.PENDING;
    //
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();


    public enum Status {
        PENDING,
        RUNNING,
        FINISHED,
    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> mTask = new ArrayDeque<Runnable>();
        Runnable mActivite;

        @Override
        public void execute(@NonNull final Runnable command) {
            mTask.offer(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActivite == null) {
                scheduleNext();
            }
            ;
        }

        private void scheduleNext() {
            if ((mActivite = mTask.poll()) != null) {
                SERIAL_EXECUTOR.execute(mActivite);
            }
        }
    }
    @MainThread
    protected static void execute(Runnable runnable){
        sDefaultExecutor.execute(runnable);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @MainThread
    protected void onProgressUpdate(Progress... progresses) {
    }


    private static class InternalHadler extends Handler {
        public InternalHadler() {
            super(Looper.getMainLooper());
        }

        public void handlerMessage(Message msg) {
            AsyncTaskResult<?> result = (AsyncTaskResult<?>) msg.obj;
            switch (msg.what) {
                case MESSAGE_POST_RESULT:
                    result.mTask.finish(result.mData[0]);
                    break;
                case MESSAGE_POST_PROGRESS:
                    result.mTask.onProgressUpdate(result.mData[1]);
                    break;
            }
        }
    }

    private void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        mStatus = Status.FINISHED;
    }

    @MainThread
    protected void onCancelled() {

    }

    @SuppressWarnings({"UnusedDeclaration"})
    @MainThread
    protected void onPostExecute(Result result) {

    }

    @SuppressWarnings({"UnusedParameters"})
    @MainThread
    protected void onCancelled(Result result) {
        onCancelled();
    }

    private final boolean isCancelled() {
        return mCancelled.get();
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;
    }


    @SuppressWarnings({"RawUseOfParameterizedType"})
    private static class AsyncTaskResult<Data> {
        final AsyncTask mTask;
        final Data[] mData;

        AsyncTaskResult(AsyncTask task, Data... data) {
            mTask = task;
            mData = data;
        }
    }


}
