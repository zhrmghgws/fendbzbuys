package com.hxd.fendbzbuys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichao on 17/8/25.
 */

public class WanZhengSousuInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * books : [{"_id":"50865988d7a545903b000009","hasCp":true,"title":"斗破苍穹","cat":"玄幻","author":"天蚕土豆","site":"zhuishuvip","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F_41615_067553.jpg%2F","shortIntro":"这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！\r\n新书等级制度：斗者，斗师，大斗师，斗灵，斗王，斗皇，斗宗，斗尊，斗圣，斗帝。\r\n恳请推荐票支持，各位兄弟看完之后，请顺手丢上几张推荐票吧，土豆谢谢！！","lastChapter":"第一章 五帝破空","retentionRatio":53.53,"banned":0,"latelyFollower":55178,"wordCount":5415899,"contentType":"txt","superscript":"","sizetype":-1},{"_id":"548ed51723c8d2a948798c83","hasCp":true,"title":"《斗破苍穹》","cat":"都市","author":"不灭火","site":"zhuishuvip","cover":"/agent/http://images.zhulang.com/book_cover/image/11/72/117267.jpg","shortIntro":"种族的争斗，使在异国的云龙过上非人的生活。 一颗龙魂吊坠塑造了一个顽强的灵魂，一本上古神功青龙决创造了一片新的天空。 云龙遭受凌辱下无意中吞下了龙魂吊坠，得到了...","lastChapter":"第67章 黑翼王死，大结局","retentionRatio":3.6,"banned":0,"latelyFollower":1020,"wordCount":205209,"contentType":"txt","superscript":"","sizetype":-1}]
     * ok : true
     */

    public boolean ok;
    public List<BooksEntity> books;

    

    public  class BooksEntity {
        /**
         * _id : 50865988d7a545903b000009
         * hasCp : true
         * title : 斗破苍穹
         * cat : 玄幻
         * author : 天蚕土豆
         * site : zhuishuvip
         * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F41615%2F_41615_067553.jpg%2F
         * shortIntro : 这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！
         新书等级制度：斗者，斗师，大斗师，斗灵，斗王，斗皇，斗宗，斗尊，斗圣，斗帝。
         恳请推荐票支持，各位兄弟看完之后，请顺手丢上几张推荐票吧，土豆谢谢！！
         * lastChapter : 第一章 五帝破空
         * retentionRatio : 53.53
         * banned : 0
         * latelyFollower : 55178
         * wordCount : 5415899
         * contentType : txt
         * superscript : 
         * sizetype : -1
         */

        public String _id;
        public boolean hasCp;
        public String title;
        public String cat;
        public String author;
        public String site;
        public String cover;
        public String shortIntro;
        public String lastChapter;
        public double retentionRatio;
        public int banned;
        public int latelyFollower;
        public int wordCount;
        public String contentType;
        public String superscript;
        public int sizetype;

       
    }
}
