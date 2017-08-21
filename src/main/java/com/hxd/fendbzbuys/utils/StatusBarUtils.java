package com.hxd.fendbzbuys.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ZRY on 2016/8/1.
 */
public class StatusBarUtils {
    /**
     *    拿到状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            Log.d("", "get status bar height fail");
            e1.printStackTrace();
            return 75;
        }
    }

    /**
     * 打开下拉栏
     */
    public static void openPullDown(Activity activity) {
        try {
            Object service = activity.getSystemService("statusbar");
            Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod("expand");
            expand.invoke(service);
        } catch (NoSuchMethodException e) {
            try {

                Object obj = activity.getSystemService("statusbar");
                Class.forName("android.app.StatusBarManager")
                        .getMethod("expandNotificationsPanel", new Class[0])
                        .invoke(obj, (Object[]) null);

            } catch (Exception e2) {
            }
        } catch (Exception e) {
        }
    }
    /**
     * 打开网络设置界面
     */
    public static void openNetSettingUi(Activity activity) {
            // 跳转到系统的网络设置界面
                Intent intent = null;
                // 判断当前系统版本
                if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
             //       intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setClassName("com.android.settings", "com.android.settings.Settings");
                }
                if (activity != null) {
                    activity.startActivity(intent);
                }
    }
}
