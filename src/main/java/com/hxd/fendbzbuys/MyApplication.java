package com.hxd.fendbzbuys;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hxd.fendbzbuys.domain.BookPathNineBean;
import com.hxd.fendbzbuys.domain.gen.BangdanBeanDao;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathEightBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathFiveBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathFourBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathNineBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathOneBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathSevenBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathSixBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathThreeBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathTwoBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookSourceBeanDao;
import com.hxd.fendbzbuys.domain.gen.DaoMaster;
import com.hxd.fendbzbuys.domain.gen.DaoSession;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.domain.gen.StatisticsBeanDao;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.greendao.database.Database;


/**
 * Created by lichao on 16/9/21.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static Handler handler;
    private static int mainThreadTid;

    public static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        handler=new Handler();
        mainThreadTid=android.os.Process.myTid();
        Constant.versioncode=getVersionName();
        initDao();
    }
    private void initDao(){
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,"fendbzbuys.db",null);
        DaoMaster daoMaster=new DaoMaster(devOpenHelper.getWritableDb());
        daoSession=daoMaster.newSession();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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
