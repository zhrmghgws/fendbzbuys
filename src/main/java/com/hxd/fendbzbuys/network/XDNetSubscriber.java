package com.hxd.fendbzbuys.network;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.hxd.fendbzbuys.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Created by lichao on 16/9/23.
 */
public abstract class XDNetSubscriber<T> extends DefaultSubscriber<T> {

    public XDNetSubscriber() {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NetworkOnMainThreadException) {
            //在主线程访问网络了
            Log.e("httperror:::", "在主线程访问网络了");
        } else if (e instanceof UnknownHostException || e instanceof ConnectException) {
            Constant.runActivity.otherPophint("请检查您的网络连接");
        } else if (e instanceof SocketTimeoutException) {
            Constant.runActivity.otherPophint("服务器请求超时");
        } else if (e instanceof HttpException) {
            Constant.runActivity.otherPophint("服务器内部错误!");
//            ((BaseActivity)activity).otherPophint("服务器错误:"+((HttpException) e).code());
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            int resultCode = apiException.resultCode;
            //不同的错误resultCode在这里处理

        } else {
            try {
                throw e;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {

    }
}
