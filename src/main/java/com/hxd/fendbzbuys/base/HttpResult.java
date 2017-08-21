package com.hxd.fendbzbuys.base;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {

    public boolean ok;
    public T data;

    @Override
    public String toString() {
        return "HttpResult::::{" +
                ", data=" + data.toString() +
                '}';
    }
}
