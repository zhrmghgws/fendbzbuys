package com.hxd.fendbzbuys.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.hxd.fendbzbuys.Common;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by ZRY on 2016/8/30.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type adapter;

    GsonResponseBodyConverter(Gson gson, Type adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Log.d("NetworkUtils", "response>>" + response);
            return gson.fromJson(response, adapter);
        }catch (JsonParseException e){
            e.printStackTrace();
            throw new ApiException(Common.formatErrorCode, Common.formatErrorTip);
        }finally {
            value.close();
        }
        /*JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }*/
    }
}