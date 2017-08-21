package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lichao on 17/8/2.
 */
@Entity
public class StatisticsBean {
    @Index
    private String type;//  female/male
    public  String name ;
    public  String bookCount ;
    @Generated(hash = 96970067)
    public StatisticsBean(String type, String name, String bookCount) {
        this.type = type;
        this.name = name;
        this.bookCount = bookCount;
    }
    @Generated(hash = 1303279983)
    public StatisticsBean() {
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBookCount() {
        return this.bookCount;
    }
    public void setBookCount(String bookCount) {
        this.bookCount = bookCount;
    }
}
