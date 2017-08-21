package com.hxd.fendbzbuys.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class GsonUtils {
    public static <T> T jison2bean(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static String bean2json(Object object) {
        Gson gson = new Gson();
        LogUtils.i(gson.toJson(object));
        return gson.toJson(object);
    }    /* * 函数名称: parseData
    * 函数描述: 将json字符串转换为map
    * @param data
    * @return
            */

    public static HashMap<String, String> parseMap(String data) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        HashMap<String, String> map = g.fromJson(data, new TypeToken<HashMap<String, String>>() {
        }.getType());
        return map;
    }
}
