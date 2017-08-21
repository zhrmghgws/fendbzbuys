package com.hxd.fendbzbuys.domain;

import java.io.Serializable;

/**
 * Created by lichao on 17/8/3.
 */

public class BookContentInfo implements Serializable {
    public boolean ok;
    public BookContent chapter;
    public static class BookContent {
        public  String title;
        public  String body;
    }
}
