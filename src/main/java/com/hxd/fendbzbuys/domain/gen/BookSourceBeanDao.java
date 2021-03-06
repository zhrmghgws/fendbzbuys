package com.hxd.fendbzbuys.domain.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.BookSourceBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_SOURCE_BEAN".
*/
public class BookSourceBeanDao extends AbstractDao<BookSourceBean, Void> {

    public static final String TABLENAME = "BOOK_SOURCE_BEAN";

    /**
     * Properties of entity BookSourceBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookID = new Property(0, String.class, "bookID", false, "BOOK_ID");
        public final static Property ZhuishuSrcid = new Property(1, String.class, "zhuishuSrcid", false, "ZHUISHU_SRCID");
        public final static Property My176Srcid = new Property(2, String.class, "my176Srcid", false, "MY176_SRCID");
        public final static Property ShuwuSrcid = new Property(3, String.class, "shuwuSrcid", false, "SHUWU_SRCID");
        public final static Property W2ytSrcid = new Property(4, String.class, "w2ytSrcid", false, "W2YT_SRCID");
        public final static Property ShuhahaSrcid = new Property(5, String.class, "shuhahaSrcid", false, "SHUHAHA_SRCID");
        public final static Property HunhunSrcid = new Property(6, String.class, "hunhunSrcid", false, "HUNHUN_SRCID");
        public final static Property SanjianggeSrcid = new Property(7, String.class, "sanjianggeSrcid", false, "SANJIANGGE_SRCID");
        public final static Property LuoqiuSrcid = new Property(8, String.class, "luoqiuSrcid", false, "LUOQIU_SRCID");
    }


    public BookSourceBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BookSourceBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_SOURCE_BEAN\" (" + //
                "\"BOOK_ID\" TEXT," + // 0: bookID
                "\"ZHUISHU_SRCID\" TEXT," + // 1: zhuishuSrcid
                "\"MY176_SRCID\" TEXT," + // 2: my176Srcid
                "\"SHUWU_SRCID\" TEXT," + // 3: shuwuSrcid
                "\"W2YT_SRCID\" TEXT," + // 4: w2ytSrcid
                "\"SHUHAHA_SRCID\" TEXT," + // 5: shuhahaSrcid
                "\"HUNHUN_SRCID\" TEXT," + // 6: hunhunSrcid
                "\"SANJIANGGE_SRCID\" TEXT," + // 7: sanjianggeSrcid
                "\"LUOQIU_SRCID\" TEXT);"); // 8: luoqiuSrcid
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_BOOK_SOURCE_BEAN_BOOK_ID ON \"BOOK_SOURCE_BEAN\"" +
                " (\"BOOK_ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_SOURCE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookSourceBean entity) {
        stmt.clearBindings();
 
        String bookID = entity.getBookID();
        if (bookID != null) {
            stmt.bindString(1, bookID);
        }
 
        String zhuishuSrcid = entity.getZhuishuSrcid();
        if (zhuishuSrcid != null) {
            stmt.bindString(2, zhuishuSrcid);
        }
 
        String my176Srcid = entity.getMy176Srcid();
        if (my176Srcid != null) {
            stmt.bindString(3, my176Srcid);
        }
 
        String shuwuSrcid = entity.getShuwuSrcid();
        if (shuwuSrcid != null) {
            stmt.bindString(4, shuwuSrcid);
        }
 
        String w2ytSrcid = entity.getW2ytSrcid();
        if (w2ytSrcid != null) {
            stmt.bindString(5, w2ytSrcid);
        }
 
        String shuhahaSrcid = entity.getShuhahaSrcid();
        if (shuhahaSrcid != null) {
            stmt.bindString(6, shuhahaSrcid);
        }
 
        String hunhunSrcid = entity.getHunhunSrcid();
        if (hunhunSrcid != null) {
            stmt.bindString(7, hunhunSrcid);
        }
 
        String sanjianggeSrcid = entity.getSanjianggeSrcid();
        if (sanjianggeSrcid != null) {
            stmt.bindString(8, sanjianggeSrcid);
        }
 
        String luoqiuSrcid = entity.getLuoqiuSrcid();
        if (luoqiuSrcid != null) {
            stmt.bindString(9, luoqiuSrcid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookSourceBean entity) {
        stmt.clearBindings();
 
        String bookID = entity.getBookID();
        if (bookID != null) {
            stmt.bindString(1, bookID);
        }
 
        String zhuishuSrcid = entity.getZhuishuSrcid();
        if (zhuishuSrcid != null) {
            stmt.bindString(2, zhuishuSrcid);
        }
 
        String my176Srcid = entity.getMy176Srcid();
        if (my176Srcid != null) {
            stmt.bindString(3, my176Srcid);
        }
 
        String shuwuSrcid = entity.getShuwuSrcid();
        if (shuwuSrcid != null) {
            stmt.bindString(4, shuwuSrcid);
        }
 
        String w2ytSrcid = entity.getW2ytSrcid();
        if (w2ytSrcid != null) {
            stmt.bindString(5, w2ytSrcid);
        }
 
        String shuhahaSrcid = entity.getShuhahaSrcid();
        if (shuhahaSrcid != null) {
            stmt.bindString(6, shuhahaSrcid);
        }
 
        String hunhunSrcid = entity.getHunhunSrcid();
        if (hunhunSrcid != null) {
            stmt.bindString(7, hunhunSrcid);
        }
 
        String sanjianggeSrcid = entity.getSanjianggeSrcid();
        if (sanjianggeSrcid != null) {
            stmt.bindString(8, sanjianggeSrcid);
        }
 
        String luoqiuSrcid = entity.getLuoqiuSrcid();
        if (luoqiuSrcid != null) {
            stmt.bindString(9, luoqiuSrcid);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public BookSourceBean readEntity(Cursor cursor, int offset) {
        BookSourceBean entity = new BookSourceBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // bookID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // zhuishuSrcid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // my176Srcid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // shuwuSrcid
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // w2ytSrcid
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // shuhahaSrcid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // hunhunSrcid
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // sanjianggeSrcid
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // luoqiuSrcid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookSourceBean entity, int offset) {
        entity.setBookID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setZhuishuSrcid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMy176Srcid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setShuwuSrcid(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setW2ytSrcid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setShuhahaSrcid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHunhunSrcid(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSanjianggeSrcid(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLuoqiuSrcid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(BookSourceBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(BookSourceBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(BookSourceBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
