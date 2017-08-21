package com.hxd.fendbzbuys.utils;

import android.util.Log;

public class LogUtils {
    private final static String TAG = "HXDLOGUTILS";

    public static int ERROR_LEVEL = 5;
    public static int WARN_LEVEL = 4;
    public static int INFO_LEVEL = 3;
    public static int DEBUG_LEVEL = 2;
    public static int VERB_LEVEL = 1;

    private static int level = -1;

    public static void e(Object obect, String log) {
        if (level <= ERROR_LEVEL) {
            Log.e(obect.getClass().getSimpleName(), log);
        }
    }

    public static void e(String log) {
        if (level <= ERROR_LEVEL) {
            Log.e(TAG, log);
        }
    }

    public static void e(Exception e) {
        if (level <= ERROR_LEVEL) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public static void w(String log) {
        if (level <= WARN_LEVEL) {
            Log.w(TAG, log);
        }
    }

    public static void w(String TAG, String log) {
        if (level <= WARN_LEVEL) {
            Log.w(TAG, log);
        }
    }

    public static void i(String log) {
        if (level <= INFO_LEVEL) {
            Log.i(TAG, log);
        }
    }

    public static void i(String TAG, String log) {
        if (level <= INFO_LEVEL) {
            Log.i(TAG, log);
        }
    }

    public static void d(String log) {
        if (level <= DEBUG_LEVEL) {
            Log.d(TAG, log);
        }
    }

    public static void d(String TAG, String log) {
        if (level <= DEBUG_LEVEL) {
            Log.d(TAG, log);
        }
    }

    public static void v(String log) {
        if (level <= VERB_LEVEL) {
            Log.v(TAG, log);
        }
    }

    public static void v(String TAG, String log) {
        if (level <= VERB_LEVEL) {
            Log.v(TAG, log);
        }
    }
}
