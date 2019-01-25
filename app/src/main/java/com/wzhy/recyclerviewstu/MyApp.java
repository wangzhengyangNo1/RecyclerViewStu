package com.wzhy.recyclerviewstu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.Stack;

public class MyApp extends Application {

    private static MyApp mApp;

    private static Context mAppContext;

    private Stack<Activity> mActivityStack;

    public static MyApp getMyApp() {
        return mApp;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mAppContext = getApplicationContext();

        mActivityStack = new Stack<>();

        //注册Activity生命周期回调
        registerActivityLifecycleCallbacks(mActivityLifecyclerCallbacks);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(mActivityLifecyclerCallbacks);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    ActivityLifecycleCallbacks mActivityLifecyclerCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mActivityStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mActivityStack.remove(activity);
        }
    };

    public void exitApp() {
        finishAllActivities();
        killProcess();
    }

    /**
     * 当前Activity
     */
    public Activity currActivity() {
        return mActivityStack.lastElement();
    }

    public void finishAllActivities() {

        // 逐个退出Activity
        for (Activity activity : mActivityStack) {
            activity.finish();
        }

    }

    public void killProcess() {
        // 方式1：android.os.Process.killProcess（）
        android.os.Process.killProcess(android.os.Process.myPid());

        // 方式2：System.exit()
        // System.exit() = Java中结束进程的方法：关闭当前JVM虚拟机
        System.exit(0);

        // System.exit(0)和System.exit(1)的区别
        // 1. System.exit(0)：正常退出；
        // 2. System.exit(1)：非正常退出，通常这种退出方式应该放在catch块中。

    }
}
