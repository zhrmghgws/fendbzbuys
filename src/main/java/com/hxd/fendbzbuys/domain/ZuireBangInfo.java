package com.hxd.fendbzbuys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichao on 17/7/29.
 */

public class ZuireBangInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public boolean ok;
    public ZuireYueBang ranking;
    public class ZuireYueBang{
        public String _id;
        public String updated;
        public String title;
        public String tag;
        public String cover;
        public String __v;
        public String created;
        public String isSub;
        public String collapse;
        public String gender;
        public String priority;
        public String id;
        public String total;

        public List<BooksInfo> books;
        public class BooksInfo{
            public String _id;
            public String title;
            public String author;
            public String shortIntro;
            public String cover;
            public String site;//"zhuishuvip"
            public String banned;
            public String latelyFollower;//追随量
            public String retentionRatio;//留存率
        }
    }
}
