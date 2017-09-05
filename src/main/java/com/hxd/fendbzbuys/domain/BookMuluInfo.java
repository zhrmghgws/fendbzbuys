package com.hxd.fendbzbuys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichao on 17/7/31.
 */

public class BookMuluInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * _id : 581825122ed01394526750b0
     * link : http://book.my716.com/getBooks.aspx?method=chapterList&bookId=1228859
     * name : 176小说
     * book : 5816b415b06d1d32157790b1
     * chapters : [{"title":"第1章 沙漠中的彼岸花","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1228859&chapterFile=U_1228859_201706221658246177_1318_1.txt","totalpage":0,"partsize":0,"currency":0,"unreadble":false,"isVip":false},{"title":"第2章 后文明时代","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1228859&chapterFile=U_1228859_201706221700307622_9505_2.txt","totalpage":0,"partsize":0,"currency":0,"unreadble":false,"isVip":false},{"title":"第3章 青铜昆仑","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1228859&chapterFile=U_1228859_201706221701471764_5585_3.txt","totalpage":0,"partsize":0,"currency":0,"unreadble":false,"isVip":false}]
     * updated : 2017-07-30T19:57:36.609Z
     * host : book.my716.com
     */

    public String _id;
    public String link;
    public String name;
    public String book;
    public String updated;
    public String host;
    public List<ChaptersEntity> chapters;

    public  class ChaptersEntity {
        /**
         * title : 第1章 沙漠中的彼岸花
         * link : http://book.my716.com/getBooks.aspx?method=content&bookId=1228859&chapterFile=U_1228859_201706221658246177_1318_1.txt
         * totalpage : 0
         * partsize : 0
         * currency : 0
         * unreadble : false
         * isVip : false
         */

        public String title;
        public String link;
        public int totalpage;
        public int partsize;
        public int currency;
        public boolean unreadble;
        public boolean isVip;

       
    }
}
