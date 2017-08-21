package com.hxd.fendbzbuys.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangruiyu on 2015/3/8.
 */
public class ThreadManager {
    private static ThreadPoolExecutor longExecutor;
    private static ThreadPoolExecutor shortExecutor;
    private static Object longLock = new Object();
    private static Object shortLock = new Object();

    /**
     *
     * @param runnable 执行耗时长的任务
     */
    public static void executeLong(Runnable runnable) {
        synchronized (longLock){
            ThreadPoollProxy proxy = new ThreadPoollProxy(5, 5, 100);
            longExecutor =  proxy.createPool();
        }
        longExecutor.execute(runnable);
    }
    /**
     * 取消长的任务
     * @param runnable
     */
    public static void cancelLong(Runnable runnable){
        if(longExecutor==null||longExecutor.isShutdown()||longExecutor.isTerminated()){
            return ;
        }else{
            longExecutor.getQueue().remove(runnable);
        }
    }

    /**
     *
     * @param runnable 执行耗时短的任务
     */
    public static void executeShort(Runnable runnable) {
        synchronized (shortLock){
            ThreadPoollProxy proxy = new ThreadPoollProxy(2, 2, 5);
            shortExecutor =  proxy.createPool();
        }
        shortExecutor.execute(runnable);
    }
    public static void cancelShort(Runnable runnable){
        if(shortExecutor==null||shortExecutor.isShutdown()||shortExecutor.isTerminated()){
            return ;
        }else{
            shortExecutor.getQueue().remove(runnable);
        }
    }
    /**
     * 线程池的代理对象
     *
     */
    private static class ThreadPoollProxy{
        private int coreNum;
        private int maxNum;
        private long keepTime;
        private  ThreadPoolExecutor pool;
        public ThreadPoollProxy(int coreNum, int maxNum, long keepTime) {
            this.coreNum = coreNum;
            this.maxNum = maxNum;
            this.keepTime = keepTime;
        }
        public ThreadPoolExecutor createPool() {
            if (pool == null) {
				/*
				 * 1 线程池的任务数量
				 * 2 如果队列放满了 额外创建的线程
				 * 3 没有任务的还能活多久
				 * 4 存活时间的单位
				 * 5 线程池满了队列   可以指定上限
				 * 6 创建线程池的工厂
				 * 7 处理异常的Handler (固定写法 )

				 *
				 */
                pool = new ThreadPoolExecutor(coreNum, maxNum, keepTime,
                        TimeUnit.MICROSECONDS,
                        new LinkedBlockingQueue<Runnable>(10),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
            }
            return pool;
        }
    }
}
