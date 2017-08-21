package com.hxd.fendbzbuys.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zry on 2015/4/20.
 */
public class SpUtils<T> {

    //存储的sharedpreferences文件名
    private static final String FILE_NAME = "save_file_name";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    /**
     * 保存数据到文件
     *
     * @param key
     * @param data
     */
    public static void saveData(String key, Object data) {

        String type = data.getClass().getSimpleName();
        initSp();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }


    /**
     * 从文件中读取数据
     *
     * @param key
     * @param defType
     * @return
     */
    public static Object getData(String key, Object defType) {
        String type=null;
        if (defType != null) {
             type = defType.getClass().getSimpleName();
        }
        initSp();
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defType);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defType);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defType);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defType);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defType);
        }

        return null;
    }/**
     * 从文件中读取数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getDefaultKeyData(String key, String defValue) {
        initSp();
        return sharedPreferences.getString(key,defValue);
    }

    private static void initSp() {
        if (sharedPreferences == null) {
            sharedPreferences = UIUtils.getContext()
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        if (editor == null) {
            editor = sharedPreferences.edit();
        }
    }
    //是否是第一次启动
    public static int isFirst(){
        initSp();
        int user_first = sharedPreferences.getInt("first",1);
        if(user_first == 1){//第一次
            editor.putInt("first", 0).commit();
        }
        return user_first;
    }

}