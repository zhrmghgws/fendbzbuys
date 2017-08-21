package com.hxd.fendbzbuys.domain.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.BangdanBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BANGDAN_BEAN".
*/
public class BangdanBeanDao extends AbstractDao<BangdanBean, Long> {

    public static final String TABLENAME = "BANGDAN_BEAN";

    /**
     * Properties of entity BangdanBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property SourceID = new Property(1, String.class, "sourceID", false, "SOURCE_ID");
    }


    public BangdanBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BangdanBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BANGDAN_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"SOURCE_ID\" TEXT);"); // 1: sourceID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BANGDAN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BangdanBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String sourceID = entity.getSourceID();
        if (sourceID != null) {
            stmt.bindString(2, sourceID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BangdanBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String sourceID = entity.getSourceID();
        if (sourceID != null) {
            stmt.bindString(2, sourceID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public BangdanBean readEntity(Cursor cursor, int offset) {
        BangdanBean entity = new BangdanBean( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // sourceID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BangdanBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setSourceID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BangdanBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BangdanBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BangdanBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
