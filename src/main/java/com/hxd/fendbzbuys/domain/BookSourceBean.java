package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by lichao on 17/8/2.
 */
@Entity
public class BookSourceBean  {
    @Index
    private String bookID;
    private String zhuishuSrcid;
    private String my176Srcid;
    private String shuwuSrcid;
    private String w2ytSrcid;
    private String shuhahaSrcid;//79xs
    private String hunhunSrcid;
    private String sanjianggeSrcid;
    private String luoqiuSrcid;
    @Generated(hash = 349478652)
    public BookSourceBean(String bookID, String zhuishuSrcid, String my176Srcid,
            String shuwuSrcid, String w2ytSrcid, String shuhahaSrcid,
            String hunhunSrcid, String sanjianggeSrcid, String luoqiuSrcid) {
        this.bookID = bookID;
        this.zhuishuSrcid = zhuishuSrcid;
        this.my176Srcid = my176Srcid;
        this.shuwuSrcid = shuwuSrcid;
        this.w2ytSrcid = w2ytSrcid;
        this.shuhahaSrcid = shuhahaSrcid;
        this.hunhunSrcid = hunhunSrcid;
        this.sanjianggeSrcid = sanjianggeSrcid;
        this.luoqiuSrcid = luoqiuSrcid;
    }
    @Generated(hash = 1512565980)
    public BookSourceBean() {
    }
    public String getBookID() {
        return this.bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getZhuishuSrcid() {
        return this.zhuishuSrcid;
    }
    public void setZhuishuSrcid(String zhuishuSrcid) {
        this.zhuishuSrcid = zhuishuSrcid;
    }
    public String getMy176Srcid() {
        return this.my176Srcid;
    }
    public void setMy176Srcid(String my176Srcid) {
        this.my176Srcid = my176Srcid;
    }
    public String getShuwuSrcid() {
        return this.shuwuSrcid;
    }
    public void setShuwuSrcid(String shuwuSrcid) {
        this.shuwuSrcid = shuwuSrcid;
    }
    public String getW2ytSrcid() {
        return this.w2ytSrcid;
    }
    public void setW2ytSrcid(String w2ytSrcid) {
        this.w2ytSrcid = w2ytSrcid;
    }
    public String getShuhahaSrcid() {
        return this.shuhahaSrcid;
    }
    public void setShuhahaSrcid(String shuhahaSrcid) {
        this.shuhahaSrcid = shuhahaSrcid;
    }
    public String getHunhunSrcid() {
        return this.hunhunSrcid;
    }
    public void setHunhunSrcid(String hunhunSrcid) {
        this.hunhunSrcid = hunhunSrcid;
    }
    public String getSanjianggeSrcid() {
        return this.sanjianggeSrcid;
    }
    public void setSanjianggeSrcid(String sanjianggeSrcid) {
        this.sanjianggeSrcid = sanjianggeSrcid;
    }
    public String getLuoqiuSrcid() {
        return this.luoqiuSrcid;
    }
    public void setLuoqiuSrcid(String luoqiuSrcid) {
        this.luoqiuSrcid = luoqiuSrcid;
    }

}
