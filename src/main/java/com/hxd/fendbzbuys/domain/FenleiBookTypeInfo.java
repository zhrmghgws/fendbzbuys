package com.hxd.fendbzbuys.domain;

import java.util.List;

/**
 * Created by lichao on 17/8/27.
 */

public class FenleiBookTypeInfo {

    /**
     * male : [{"name":"玄幻","bookCount":464475,"monthlyCount":0,"icon":"/icon/玄幻_.png"},{"name":"奇幻","bookCount":43797,"monthlyCount":0,"icon":"/icon/奇幻_.png"},{"name":"武侠","bookCount":38232,"monthlyCount":0,"icon":"/icon/武侠_.png"},{"name":"仙侠","bookCount":123050,"monthlyCount":0,"icon":"/icon/仙侠_.png"},{"name":"都市","bookCount":326488,"monthlyCount":0,"icon":"/icon/都市_.png"},{"name":"职场","bookCount":14872,"monthlyCount":0,"icon":"/icon/职场_.png"},{"name":"历史","bookCount":65250,"monthlyCount":0,"icon":"/icon/历史_.png"},{"name":"军事","bookCount":14088,"monthlyCount":0,"icon":"/icon/军事_.png"},{"name":"游戏","bookCount":76059,"monthlyCount":0,"icon":"/icon/游戏_.png"},{"name":"竞技","bookCount":5358,"monthlyCount":0,"icon":"/icon/竞技_.png"},{"name":"科幻","bookCount":105126,"monthlyCount":0,"icon":"/icon/科幻_.png"},{"name":"灵异","bookCount":31105,"monthlyCount":0,"icon":"/icon/灵异_.png"},{"name":"同人","bookCount":36176,"monthlyCount":0,"icon":"/icon/同人_.png"},{"name":"轻小说","bookCount":4669,"monthlyCount":0,"icon":"/icon/轻小说_.png"}]
     * female : [{"name":"古代言情","bookCount":394679,"monthlyCount":0,"icon":"/icon/古代言情_.png"},{"name":"现代言情","bookCount":496103,"monthlyCount":0,"icon":"/icon/现代言情_.png"},{"name":"青春校园","bookCount":105017,"monthlyCount":0,"icon":"/icon/青春校园_.png"},{"name":"纯爱","bookCount":123364,"monthlyCount":0,"icon":"/icon/耽美_.png"},{"name":"玄幻奇幻","bookCount":119340,"monthlyCount":0,"icon":"/icon/玄幻奇幻_.png"},{"name":"武侠仙侠","bookCount":60737,"monthlyCount":0,"icon":"/icon/武侠仙侠_.png"},{"name":"科幻","bookCount":8728,"monthlyCount":0,"icon":"/icon/科幻_.png"},{"name":"游戏竞技","bookCount":5730,"monthlyCount":0,"icon":"/icon/游戏竞技_.png"},{"name":"悬疑灵异","bookCount":13062,"monthlyCount":0,"icon":"/icon/悬疑灵异_.png"},{"name":"同人","bookCount":122280,"monthlyCount":0,"icon":"/icon/同人_.png"},{"name":"女尊","bookCount":19896,"monthlyCount":0,"icon":"/icon/女尊_.png"},{"name":"莉莉","bookCount":24202,"monthlyCount":0,"icon":"/icon/百合_.png"}]
     * picture : [{"name":"热血","bookCount":330,"monthlyCount":0,"icon":"/icon/热血_.png"},{"name":"魔幻","bookCount":331,"monthlyCount":0,"icon":"/icon/魔幻_.png"},{"name":"科幻","bookCount":64,"monthlyCount":0,"icon":"/icon/科幻_.png"},{"name":"恋爱","bookCount":557,"monthlyCount":0,"icon":"/icon/恋爱_.png"},{"name":"搞笑","bookCount":562,"monthlyCount":0,"icon":"/icon/搞笑_.png"},{"name":"悬疑","bookCount":171,"monthlyCount":0,"icon":"/icon/悬疑_.png"},{"name":"少儿","bookCount":2610,"monthlyCount":0,"icon":"/icon/少儿_.png"}]
     * press : [{"name":"传记名著","bookCount":2767,"monthlyCount":0,"icon":"/icon/传记名著_.png"},{"name":"出版小说","bookCount":5375,"monthlyCount":0,"icon":"/icon/出版小说_.png"},{"name":"人文社科","bookCount":15014,"monthlyCount":0,"icon":"/icon/人文社科_.png"},{"name":"生活时尚","bookCount":1295,"monthlyCount":0,"icon":"/icon/生活时尚_.png"},{"name":"经管理财","bookCount":4712,"monthlyCount":0,"icon":"/icon/经管理财_.png"},{"name":"青春言情","bookCount":3649,"monthlyCount":0,"icon":"/icon/青春言情_.png"},{"name":"外文原版","bookCount":686,"monthlyCount":0,"icon":"/icon/外文原版_.png"},{"name":"政治军事","bookCount":312,"monthlyCount":0,"icon":"/icon/政治军事_.png"},{"name":"成功励志","bookCount":5629,"monthlyCount":0,"icon":"/icon/成功励志_.png"},{"name":"育儿健康","bookCount":4850,"monthlyCount":0,"icon":"/icon/育儿健康_.png"}]
     * ok : true
     */

    public boolean ok;
    public List<MaleEntity> male;
    public List<MaleEntity> female;
    public List<MaleEntity> picture;
    public List<MaleEntity> press;


    public class MaleEntity {
        /**
         * name : 玄幻
         * bookCount : 464475
         * monthlyCount : 0
         * icon : /icon/玄幻_.png
         */

        public String name;
        public int bookCount;
        public int monthlyCount;
        public String icon;


    }

}
