package com.hxd.fendbzbuys.domain;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hxd.fendbzbuys.domain.gen.DaoSession;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathBeanDao;

/**
 * Created by lichao on 17/8/2.
 */

@Entity
public class BookPathBean{
    @Id
    public long id;
    @Index
    public String bookId;
    public String netUrl;
    public String content;
    public String localPath;
    public String title;
    @Generated(hash = 836819406)
    public BookPathBean(long id, String bookId, String netUrl, String content,
            String localPath, String title) {
        this.id = id;
        this.bookId = bookId;
        this.netUrl = netUrl;
        this.content = content;
        this.localPath = localPath;
        this.title = title;
    }
    @Generated(hash = 2009931012)
    public BookPathBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBookId() {
        return this.bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getNetUrl() {
        return this.netUrl;
    }
    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getLocalPath() {
        return this.localPath;
    }
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
