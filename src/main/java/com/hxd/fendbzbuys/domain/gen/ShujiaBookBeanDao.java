package com.hxd.fendbzbuys.domain.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.ShujiaBookBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SHUJIA_BOOK_BEAN".
*/
public class ShujiaBookBeanDao extends AbstractDao<ShujiaBookBean, String> {

    public static final String TABLENAME = "SHUJIA_BOOK_BEAN";

    /**
     * Properties of entity ShujiaBookBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookId = new Property(0, String.class, "bookId", true, "BOOK_ID");
        public final static Property BookName = new Property(1, String.class, "bookName", false, "BOOK_NAME");
        public final static Property Cover = new Property(2, String.class, "cover", false, "COVER");
        public final static Property MinorCate = new Property(3, String.class, "minorCate", false, "MINOR_CATE");
        public final static Property Author = new Property(4, String.class, "author", false, "AUTHOR");
        public final static Property IsSerial = new Property(5, boolean.class, "isSerial", false, "IS_SERIAL");
        public final static Property Shuqian = new Property(6, String.class, "shuqian", false, "SHUQIAN");
        public final static Property BookSourceID = new Property(7, String.class, "bookSourceID", false, "BOOK_SOURCE_ID");
        public final static Property CurrentZhangjie = new Property(8, String.class, "currentZhangjie", false, "CURRENT_ZHANGJIE");
        public final static Property LastChapter = new Property(9, String.class, "lastChapter", false, "LAST_CHAPTER");
        public final static Property LongIntro = new Property(10, String.class, "longIntro", false, "LONG_INTRO");
        public final static Property ManyDownload = new Property(11, int.class, "manyDownload", false, "MANY_DOWNLOAD");
        public final static Property BookpathBean = new Property(12, int.class, "bookpathBean", false, "BOOKPATH_BEAN");
        public final static Property IsZhudong = new Property(13, boolean.class, "isZhudong", false, "IS_ZHUDONG");
        public final static Property JiaruDate = new Property(14, long.class, "jiaruDate", false, "JIARU_DATE");
    }


    public ShujiaBookBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ShujiaBookBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SHUJIA_BOOK_BEAN\" (" + //
                "\"BOOK_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: bookId
                "\"BOOK_NAME\" TEXT," + // 1: bookName
                "\"COVER\" TEXT," + // 2: cover
                "\"MINOR_CATE\" TEXT," + // 3: minorCate
                "\"AUTHOR\" TEXT," + // 4: author
                "\"IS_SERIAL\" INTEGER NOT NULL ," + // 5: isSerial
                "\"SHUQIAN\" TEXT," + // 6: shuqian
                "\"BOOK_SOURCE_ID\" TEXT," + // 7: bookSourceID
                "\"CURRENT_ZHANGJIE\" TEXT," + // 8: currentZhangjie
                "\"LAST_CHAPTER\" TEXT," + // 9: lastChapter
                "\"LONG_INTRO\" TEXT," + // 10: longIntro
                "\"MANY_DOWNLOAD\" INTEGER NOT NULL ," + // 11: manyDownload
                "\"BOOKPATH_BEAN\" INTEGER NOT NULL ," + // 12: bookpathBean
                "\"IS_ZHUDONG\" INTEGER NOT NULL ," + // 13: isZhudong
                "\"JIARU_DATE\" INTEGER NOT NULL );"); // 14: jiaruDate
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_SHUJIA_BOOK_BEAN_BOOK_ID ON SHUJIA_BOOK_BEAN" +
                " (\"BOOK_ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SHUJIA_BOOK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ShujiaBookBean entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(2, bookName);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(3, cover);
        }
 
        String minorCate = entity.getMinorCate();
        if (minorCate != null) {
            stmt.bindString(4, minorCate);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(5, author);
        }
        stmt.bindLong(6, entity.getIsSerial() ? 1L: 0L);
 
        String shuqian = entity.getShuqian();
        if (shuqian != null) {
            stmt.bindString(7, shuqian);
        }
 
        String bookSourceID = entity.getBookSourceID();
        if (bookSourceID != null) {
            stmt.bindString(8, bookSourceID);
        }
 
        String currentZhangjie = entity.getCurrentZhangjie();
        if (currentZhangjie != null) {
            stmt.bindString(9, currentZhangjie);
        }
 
        String lastChapter = entity.getLastChapter();
        if (lastChapter != null) {
            stmt.bindString(10, lastChapter);
        }
 
        String longIntro = entity.getLongIntro();
        if (longIntro != null) {
            stmt.bindString(11, longIntro);
        }
        stmt.bindLong(12, entity.getManyDownload());
        stmt.bindLong(13, entity.getBookpathBean());
        stmt.bindLong(14, entity.getIsZhudong() ? 1L: 0L);
        stmt.bindLong(15, entity.getJiaruDate());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ShujiaBookBean entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(2, bookName);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(3, cover);
        }
 
        String minorCate = entity.getMinorCate();
        if (minorCate != null) {
            stmt.bindString(4, minorCate);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(5, author);
        }
        stmt.bindLong(6, entity.getIsSerial() ? 1L: 0L);
 
        String shuqian = entity.getShuqian();
        if (shuqian != null) {
            stmt.bindString(7, shuqian);
        }
 
        String bookSourceID = entity.getBookSourceID();
        if (bookSourceID != null) {
            stmt.bindString(8, bookSourceID);
        }
 
        String currentZhangjie = entity.getCurrentZhangjie();
        if (currentZhangjie != null) {
            stmt.bindString(9, currentZhangjie);
        }
 
        String lastChapter = entity.getLastChapter();
        if (lastChapter != null) {
            stmt.bindString(10, lastChapter);
        }
 
        String longIntro = entity.getLongIntro();
        if (longIntro != null) {
            stmt.bindString(11, longIntro);
        }
        stmt.bindLong(12, entity.getManyDownload());
        stmt.bindLong(13, entity.getBookpathBean());
        stmt.bindLong(14, entity.getIsZhudong() ? 1L: 0L);
        stmt.bindLong(15, entity.getJiaruDate());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ShujiaBookBean readEntity(Cursor cursor, int offset) {
        ShujiaBookBean entity = new ShujiaBookBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // bookId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bookName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cover
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // minorCate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // author
            cursor.getShort(offset + 5) != 0, // isSerial
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // shuqian
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // bookSourceID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // currentZhangjie
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // lastChapter
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // longIntro
            cursor.getInt(offset + 11), // manyDownload
            cursor.getInt(offset + 12), // bookpathBean
            cursor.getShort(offset + 13) != 0, // isZhudong
            cursor.getLong(offset + 14) // jiaruDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ShujiaBookBean entity, int offset) {
        entity.setBookId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBookName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCover(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMinorCate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAuthor(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIsSerial(cursor.getShort(offset + 5) != 0);
        entity.setShuqian(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBookSourceID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCurrentZhangjie(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLastChapter(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLongIntro(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setManyDownload(cursor.getInt(offset + 11));
        entity.setBookpathBean(cursor.getInt(offset + 12));
        entity.setIsZhudong(cursor.getShort(offset + 13) != 0);
        entity.setJiaruDate(cursor.getLong(offset + 14));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ShujiaBookBean entity, long rowId) {
        return entity.getBookId();
    }
    
    @Override
    public String getKey(ShujiaBookBean entity) {
        if(entity != null) {
            return entity.getBookId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ShujiaBookBean entity) {
        return entity.getBookId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}