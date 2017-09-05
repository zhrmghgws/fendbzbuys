package com.hxd.fendbzbuys.domain;

import java.io.Serializable;

/**
 * Created by lichao on 17/9/5.
 */

public class MyUMessage implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * msg_id : us55091150461669819610
     * random_min : 0
     * display_type : notification
     * body : {"title":"cccccccccccc","ticker":"cccccccccccc","text":"cccccccccccccccccccccccccccc","url":"http://www.baidu.com","after_open":"go_url","play_vibrate":"true","play_sound":"true","play_lights":"true"}
     * extra : {"open":"true"}
     */

    public String msg_id;
    public int random_min;
    public String display_type;
    public BodyEntity body;
    public ExtraEntity extra;

    

    public  class BodyEntity {
        /**
         * title : cccccccccccc
         * ticker : cccccccccccc
         * text : cccccccccccccccccccccccccccc
         * url : http://www.baidu.com
         * after_open : go_url
         * play_vibrate : true
         * play_sound : true
         * play_lights : true
         */

        public String title;
        public String ticker;
        public String text;
        public String url;
        public String after_open;
        public String play_vibrate;
        public String play_sound;
        public String play_lights;

       
    }

    public  class ExtraEntity {
        /**
         * open : true
         */

        public String open;
       
    }
}
