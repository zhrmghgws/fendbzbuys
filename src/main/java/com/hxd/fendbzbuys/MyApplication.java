package com.hxd.fendbzbuys;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import com.hxd.fendbzbuys.receiver.MyPushIntentService;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.tag.TagManager;


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
        MobclickAgent.setDebugMode( true );//打开集成测试,上线时需要关闭 // TODO: 17/8/30
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);
        umentTuisong();


    }
    private void umentTuisong(){
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("umeng推送", ":::注册成功:::: " );
                Log.e("umeng推送", ":::deviceToken:::: "+deviceToken );
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
       // mPushAgent.setDebugMode(false); 上线需打开,不输出log // TODO: 17/8/30

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
