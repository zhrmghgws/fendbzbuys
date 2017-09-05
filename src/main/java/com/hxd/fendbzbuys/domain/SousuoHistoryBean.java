package com.hxd.fendbzbuys.domain;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by lichao on 17/8/27.
 */
@Entity
public class SousuoHistoryBean  {
    @Id(autoincrement = true)
    public Long id;
    public String keywords;
    @Generated(hash = 1742944819)
    public SousuoHistoryBean(Long id, String keywords) {
        this.id = id;
        this.keywords = keywords;
    }
    @Generated(hash = 1871953966)
    public SousuoHistoryBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKeywords() {
        return this.keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
