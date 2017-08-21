package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by lichao on 17/8/2.
 */

@Entity
public class BangdanBooksBean {
    @Id
    public long id;
    @Index
    public int type;
    public String bookID;
    public String title;
    public String author;
    public String shortIntro;
    public String cover;
    public String site;//"zhuishuvip"
    public String banned;
    public String latelyFollower;//追随量
    public String retentionRatio;//留存率
    @Generated(hash = 1737729632)
    public BangdanBooksBean(long id, int type, String bookID, String title,
            String author, String shortIntro, String cover, String site,
            String banned, String latelyFollower, String retentionRatio) {
        this.id = id;
        this.type = type;
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.shortIntro = shortIntro;
        this.cover = cover;
        this.site = site;
        this.banned = banned;
        this.latelyFollower = latelyFollower;
        this.retentionRatio = retentionRatio;
    }
    @Generated(hash = 1554776059)
    public BangdanBooksBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getBookID() {
        return this.bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getShortIntro() {
        return this.shortIntro;
    }
    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getSite() {
        return this.site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public String getBanned() {
        return this.banned;
    }
    public void setBanned(String banned) {
        this.banned = banned;
    }
    public String getLatelyFollower() {
        return this.latelyFollower;
    }
    public void setLatelyFollower(String latelyFollower) {
        this.latelyFollower = latelyFollower;
    }
    public String getRetentionRatio() {
        return this.retentionRatio;
    }
    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }


   
}
