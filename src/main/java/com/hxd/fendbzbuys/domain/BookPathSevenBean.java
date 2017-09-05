package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hxd.fendbzbuys.domain.gen.DaoSession;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathSevenBeanDao;

import java.io.Serializable;

/**
 * Created by lichao on 17/8/2.
 */

@Entity
public class BookPathSevenBean  {
    @Id(autoincrement = true)
    public Long id;
    @Index
    public String bookId;
    @ToOne(joinProperty = "bookId")
    private ShujiaBookBean shujiaBookBean;
    public String netUrl;
    public String content;
    public String localPath;
    public  String title;
    public int zhangjieID;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 952215843)
    private transient BookPathSevenBeanDao myDao;
    @Generated(hash = 927288057)
    public BookPathSevenBean(Long id, String bookId, String netUrl, String content,
            String localPath, String title, int zhangjieID) {
        this.id = id;
        this.bookId = bookId;
        this.netUrl = netUrl;
        this.content = content;
        this.localPath = localPath;
        this.title = title;
        this.zhangjieID = zhangjieID;
    }
    @Generated(hash = 756551020)
    public BookPathSevenBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
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
    public int getZhangjieID() {
        return this.zhangjieID;
    }
    public void setZhangjieID(int zhangjieID) {
        this.zhangjieID = zhangjieID;
    }
    @Generated(hash = 896125678)
    private transient String shujiaBookBean__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 649597576)
    public ShujiaBookBean getShujiaBookBean() {
        String __key = this.bookId;
        if (shujiaBookBean__resolvedKey == null
                || shujiaBookBean__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ShujiaBookBeanDao targetDao = daoSession.getShujiaBookBeanDao();
            ShujiaBookBean shujiaBookBeanNew = targetDao.load(__key);
            synchronized (this) {
                shujiaBookBean = shujiaBookBeanNew;
                shujiaBookBean__resolvedKey = __key;
            }
        }
        return shujiaBookBean;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 710654203)
    public void setShujiaBookBean(ShujiaBookBean shujiaBookBean) {
        synchronized (this) {
            this.shujiaBookBean = shujiaBookBean;
            bookId = shujiaBookBean == null ? null : shujiaBookBean.getBookId();
            shujiaBookBean__resolvedKey = bookId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 991661080)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookPathSevenBeanDao() : null;
    }

}
