package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by lichao on 17/8/2.
 */
@Entity
public class BangdanBean  {
    @Id
    public long id;
    public String sourceID;
    @Generated(hash = 380532760)
    public BangdanBean(long id, String sourceID) {
        this.id = id;
        this.sourceID = sourceID;
    }
    @Generated(hash = 259068103)
    public BangdanBean() {
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
