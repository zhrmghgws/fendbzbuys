package com.hxd.fendbzbuys.domain;


import java.io.Serializable;
import java.util.List;


/**
 * Created by lichao on 16/9/22.
 */
public class StatisticsInfo implements Serializable {
    public boolean ok;
    public List<Statistics> female;
    public List<Statistics> male;
    public List<Statistics> picture;
    public List<Statistics> press;

    public int registerResult;


    public  class Statistics implements Serializable{
        public  String name ;
        public  String bookCount ;

    }
}
