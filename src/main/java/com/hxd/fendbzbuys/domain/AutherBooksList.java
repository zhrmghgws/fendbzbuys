package com.hxd.fendbzbuys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichao on 17/7/30.
 */

public class AutherBooksList implements Serializable {

    /**
     * books : [{"_id":"5816b415b06d1d32157790b1","title":"圣墟","author":"辰东","shortIntro":"在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角\u2026\u2026","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F","site":"zhuishuvip","majorCate":"玄幻","minorCate":"东方玄幻","banned":0,"latelyFollower":285439,"followerCount":0,"retentionRatio":73.29,"lastChapter":"第547章 全灭"},{"_id":"50864bf69dacd30e3a000014","author":"辰东","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F42216%2F_42216_203892.jpg%2F","shortIntro":"冰冷与黑暗并存的宇宙深处，九具庞大的龙尸拉着一口青铜古棺，亘古长存。\n这是太空探测器在枯寂的宇宙中捕捉到的一幅极其震撼的画面。\n九龙拉棺，究竟是回到了上古，还是来到了星空的彼岸？\n一个浩大的仙侠世界，光怪陆离，神秘无尽。热血似火山沸腾，激情若瀚海汹涌，欲望如深渊无止境\u2026\u2026\n登天路，踏歌行，弹指遮天。","title":"遮天","site":"zhuishuvip","majorCate":"仙侠","minorCate":"幻想修仙","banned":0,"latelyFollower":29749,"followerCount":30028,"retentionRatio":60.1,"lastChapter":"第一千八百二十二章 遮天大结局"}]
     * ok : true
     */

    public boolean ok;
    public List<BooksEntity> books;

  
    public class BooksEntity {
        /**
         * _id : 5816b415b06d1d32157790b1
         * title : 圣墟
         * author : 辰东
         * shortIntro : 在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……
         * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859%2F_1228859_441552.jpg%2F
         * site : zhuishuvip
         * majorCate : 玄幻
         * minorCate : 东方玄幻
         * banned : 0
         * latelyFollower : 285439
         * followerCount : 0
         * retentionRatio : 73.29
         * lastChapter : 第547章 全灭
         */

        public String _id;
        public String title;
        public String author;
        public String shortIntro;
        public String cover;
        public String site;
        public String majorCate;
        public String minorCate;
        public int banned;
        public int latelyFollower;
        public int followerCount;
        public double retentionRatio;
        public String lastChapter;

       
    }
}
