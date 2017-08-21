package com.hxd.fendbzbuys.base;

/**
 * Created by lichao on 17/8/20.
 */

public interface  DownLoadCallBack {
    void start(int tatleCount);
    void update(int sucess);
    void finish();
    void finishWithError(int sucess,int error);
    //void error();
}
