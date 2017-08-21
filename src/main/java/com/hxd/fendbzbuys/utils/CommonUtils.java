package com.hxd.fendbzbuys.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2015/5/18.
 */
public class CommonUtils {
    /*打开新的activity
    bundle没有可以传null
     */
    public static void startNewActivity(Activity currentActivity, Class startActivity, Bundle bundle) {
        try {
            Intent intent = new Intent(currentActivity, startActivity);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            if (currentActivity == null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//              startActivity(intent);
            } else {
                currentActivity.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
