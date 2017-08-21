package com.hxd.fendbzbuys;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;



/**
 * 全局公共类
 */
public class Common {


    private static final String Umeng_key = "UMENG_CHANNEL";
    //gson解析错误的code
    public static int formatErrorCode = 409;
    public static String formatErrorTip = "服务器数据解析错误,请稍后重试!";
    public static final boolean online = true;
    public static String getCommonUrl() {
           return "http://api.zhuishushenqi.com";
    }
    public static String getContentUrl() {
        return "http://chapter2.zhuishushenqi.com";
    }
    public static String getIonicCommonUrl() {
        return "http://statics.zhuishushenqi.com";
    }


    public static String getAppMetaData(Context context){
        if(context == null || TextUtils.isEmpty(Umeng_key)){
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(Umeng_key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }
}



