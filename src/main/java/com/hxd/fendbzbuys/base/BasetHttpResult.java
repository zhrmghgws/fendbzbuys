package com.hxd.fendbzbuys.base;

import java.io.Serializable;

/**
 * Created by lichao on 16/9/22.
 */
public class BasetHttpResult<T> implements Serializable {
    public int ret_code;
    public String ret_msg;
    public T data;
    public T getData(){
        return  data;
    }
}
