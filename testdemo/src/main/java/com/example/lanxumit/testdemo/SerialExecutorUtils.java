package com.example.lanxumit.testdemo;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lanxumit on 2017/4/28.
 */

public class SerialExecutorUtils {
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
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();

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
                THREAD_POOL_EXECUTOR.execute(mActivite);
            }
        }
    }

}
