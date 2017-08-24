package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lichao on 17/8/2.
 */
@Entity
public class BangdansBean {
    @Id
    public long id;
    public String sourceID;
    @Generated(hash = 864609476)
    public BangdansBean(long id, String sourceID) {
        this.id = id;
        this.sourceID = sourceID;
    }
    @Generated(hash = 556655092)
    public BangdansBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSourceID() {
        return this.sourceID;
    }
    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }



}
