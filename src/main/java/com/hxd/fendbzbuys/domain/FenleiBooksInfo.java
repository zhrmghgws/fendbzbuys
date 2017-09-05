package com.hxd.fendbzbuys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichao on 17/8/27.
 */

public class FenleiBooksInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * total : 2231
     * books : [{"_id":"594ce7a37d50d2a82147c6a6","title":"轮回乐园","author":"那一只蚊子","shortIntro":"苏晓与轮回乐园签订契约，穿梭在各个动漫世界内执行任务。\n　　\n　　喰种世界，苏晓手持利刃追杀独眼枭·高槻泉十几条街区。\n　　\n　　顶上战场，苏晓站在海军的尸堆上，遥望处刑台上脸色难看的战国，以及目瞪口呆的艾斯。\n　　\n　　众契约者：\u201c你特么是不是开了挂，你根本不是契约者吧，你绝对是原著里的隐藏人物！\u201d\n　　\n　　苏晓当然不是普通契约者，他是猎杀者\u2026\u2026。\n---------------\n简介略显无力，本书动漫无限流，作者节操满满，从不断更，请放心入坑。\n读者群：534789565","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2044717%2F2044717_87093d361a4b483896b61fd55f8a2a28.jpg%2F","site":"zhuishuvip","majorCate":"同人","minorCate":"小说同人","sizetype":-1,"superscript":"","contentType":"txt","allowMonthly":false,"banned":0,"latelyFollower":30501,"retentionRatio":46.36,"lastChapter":"第十三章：这就是飞扬的感觉（第四更，月票加更）","tags":["二次元"]},{"_id":"584e84a38b1a0d355ed76316","title":"一切从斗破苍穹开始","author":"千影残光","shortIntro":"只是稍稍抱怨一下人生的苏邪，突然之间就穿越了，穿越的地方竟然是斗破苍穹的世界，一个普通人如何在强者如云的斗气大陆生存呢！还好，苏邪觉醒了自己的金手指，崇拜系统，为了赚取崇拜点，苏邪只能在装逼的道路上越走越远了\u2026\u2026","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1463733%2F_1463733_275105.jpg%2F","site":"zhuishuvip","majorCate":"同人","minorCate":"小说同人","sizetype":-1,"superscript":"","contentType":"txt","allowMonthly":false,"banned":0,"latelyFollower":22508,"retentionRatio":51.31,"lastChapter":"第793章 狂澜（第四更，求订阅）","tags":["同人","衍生同人","二次元"]}]
     * ok : true
     */

    public int total;
    public boolean ok;
    public List<BooksEntity> books;

   

    public  class BooksEntity {
        /**
         * _id : 594ce7a37d50d2a82147c6a6
         * title : 轮回乐园
         * author : 那一只蚊子
         * shortIntro : 苏晓与轮回乐园签订契约，穿梭在各个动漫世界内执行任务。

         喰种世界，苏晓手持利刃追杀独眼枭·高槻泉十几条街区。

         顶上战场，苏晓站在海军的尸堆上，遥望处刑台上脸色难看的战国，以及目瞪口呆的艾斯。

         众契约者：“你特么是不是开了挂，你根本不是契约者吧，你绝对是原著里的隐藏人物！”

         苏晓当然不是普通契约者，他是猎杀者……。
         ---------------
         简介略显无力，本书动漫无限流，作者节操满满，从不断更，请放心入坑。
         读者群：534789565
         * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2044717%2F2044717_87093d361a4b483896b61fd55f8a2a28.jpg%2F
         * site : zhuishuvip
         * majorCate : 同人
         * minorCate : 小说同人
         * sizetype : -1
         * superscript : 
         * contentType : txt
         * allowMonthly : false
         * banned : 0
         * latelyFollower : 30501
         * retentionRatio : 46.36
         * lastChapter : 第十三章：这就是飞扬的感觉（第四更，月票加更）
         * tags : ["二次元"]
         */

        public String _id;
        public String title;
        public String author;
        public String shortIntro;
        public String cover;
        public String site;
        public String majorCate;
        public String minorCate;
        public int sizetype;
        public String superscript;
        public String contentType;
        public boolean allowMonthly;
        public int banned;
        public int latelyFollower;
        public double retentionRatio;
        public String lastChapter;
        public List<String> tags;

      
    }
}
