package com.hxd.fendbzbuys;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;


/**
 * Created by lichao on 16/9/21.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static Handler handler;
    private static int mainThreadTid;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        handler=new Handler();
        mainThreadTid=android.os.Process.myTid();
        Constant.versioncode=getVersionName();

    }


    public static MyApplication getMyapplication(){
        return myApplication;
    }
    public static Handler getHandler() {
        return handler;
    }
    public static int getMainThreadId() {
        return mainThreadTid;
    }
    public String getVersionName() {
        String code="";
        PackageManager pm = getApplicationContext().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(getApplicationContext().getPackageName(), 0);
            code = info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }


}
