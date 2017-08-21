package com.hxd.fendbzbuys.domain;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;


/**
 * Created by lichao on 16/9/22.
 */
public class GenderInfo {
    public boolean ok;
    public List<FemaleInfo> female;
    public List<FemaleInfo> male;

    public int registerResult;

    public  class FemaleInfo {
        public  String _id ;
        public  String title ;
        public  String cover ;
        public  boolean collapse ;
        public  String monthRank ;
        public  String totalRank ;
        public  String shortTitle ;

    }
}
