package com.hxd.fendbzbuys.domain;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.hxd.fendbzbuys.domain.gen.DaoSession;
import com.hxd.fendbzbuys.domain.gen.BookPathBeanDao;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;

/**
 * Created by lichao on 17/8/2.
 */
@Entity
public class ShujiaBookBean  {
    @Index @Id
    public String bookId;
    public String bookName;
    public String cover;
    public String minorCate;
    public String author;
    public boolean isSerial;
    public String shuqian;
    public String bookSourceID;
    public String currentZhangjie;
    public String lastChapter;
    public String longIntro;
    public int manyDownload;
    public int bookpathBean;
    public int bookTotakCount;
    public boolean isZhudong;
    public long jiaruDate;
    public int currentX;
    public int currentY;
    @Generated(hash = 1300156331)
    public ShujiaBookBean(String bookId, String bookName, String cover,
            String minorCate, String author, boolean isSerial, String shuqian,
            String bookSourceID, String currentZhangjie, String lastChapter,
            String longIntro, int manyDownload, int bookpathBean,
            int bookTotakCount, boolean isZhudong, long jiaruDate, int currentX,
            int currentY) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.cover = cover;
        this.minorCate = minorCate;
        this.author = author;
        this.isSerial = isSerial;
        this.shuqian = shuqian;
        this.bookSourceID = bookSourceID;
        this.currentZhangjie = currentZhangjie;
        this.lastChapter = lastChapter;
        this.longIntro = longIntro;
        this.manyDownload = manyDownload;
        this.bookpathBean = bookpathBean;
        this.bookTotakCount = bookTotakCount;
        this.isZhudong = isZhudong;
        this.jiaruDate = jiaruDate;
        this.currentX = currentX;
        this.currentY = currentY;
    }
    @Generated(hash = 340162246)
    public ShujiaBookBean() {
    }
    public String getBookId() {
        return this.bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getMinorCate() {
        return this.minorCate;
    }
    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean getIsSerial() {
        return this.isSerial;
    }
    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }
    public String getShuqian() {
        return this.shuqian;
    }
    public void setShuqian(String shuqian) {
        this.shuqian = shuqian;
    }
    public String getBookSourceID() {
        return this.bookSourceID;
    }
    public void setBookSourceID(String bookSourceID) {
        this.bookSourceID = bookSourceID;
    }
    public String getCurrentZhangjie() {
        return this.currentZhangjie;
    }
    public void setCurrentZhangjie(String currentZhangjie) {
        this.currentZhangjie = currentZhangjie;
    }
    public String getLastChapter() {
        return this.lastChapter;
    }
    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }
    public String getLongIntro() {
        return this.longIntro;
    }
    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }
    public int getManyDownload() {
        return this.manyDownload;
    }
    public void setManyDownload(int manyDownload) {
        this.manyDownload = manyDownload;
    }
    public int getBookpathBean() {
        return this.bookpathBean;
    }
    public void setBookpathBean(int bookpathBean) {
        this.bookpathBean = bookpathBean;
    }
    public int getBookTotakCount() {
        return this.bookTotakCount;
    }
    public void setBookTotakCount(int bookTotakCount) {
        this.bookTotakCount = bookTotakCount;
    }
    public boolean getIsZhudong() {
        return this.isZhudong;
    }
    public void setIsZhudong(boolean isZhudong) {
        this.isZhudong = isZhudong;
    }
    public long getJiaruDate() {
        return this.jiaruDate;
    }
    public void setJiaruDate(long jiaruDate) {
        this.jiaruDate = jiaruDate;
    }
    public int getCurrentX() {
        return this.currentX;
    }
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }
    public int getCurrentY() {
        return this.currentY;
    }
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
}
