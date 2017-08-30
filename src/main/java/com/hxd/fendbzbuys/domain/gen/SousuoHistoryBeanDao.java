package com.hxd.fendbzbuys.domain.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.SousuoHistoryBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SOUSUO_HISTORY_BEAN".
*/
public class SousuoHistoryBeanDao extends AbstractDao<SousuoHistoryBean, Long> {

    public static final String TABLENAME = "SOUSUO_HISTORY_BEAN";

    /**
     * Properties of entity SousuoHistoryBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Keywords = new Property(1, String.class, "keywords", false, "KEYWORDS");
    }


    public SousuoHistoryBeanDao(DaoConfig config) {
        super(config);
    }
    
    public SousuoHistoryBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SOUSUO_HISTORY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"KEYWORDS\" TEXT);"); // 1: keywords
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SOUSUO_HISTORY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SousuoHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String keywords = entity.getKeywords();
        if (keywords != null) {
            stmt.bindString(2, keywords);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SousuoHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String keywords = entity.getKeywords();
        if (keywords != null) {
            stmt.bindString(2, keywords);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SousuoHistoryBean readEntity(Cursor cursor, int offset) {
        SousuoHistoryBean entity = new SousuoHistoryBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // keywords
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SousuoHistoryBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKeywords(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SousuoHistoryBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SousuoHistoryBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SousuoHistoryBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}