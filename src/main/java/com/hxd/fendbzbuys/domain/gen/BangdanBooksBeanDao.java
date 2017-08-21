package com.hxd.fendbzbuys.domain.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.BangdanBooksBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BANGDAN_BOOKS_BEAN".
*/
public class BangdanBooksBeanDao extends AbstractDao<BangdanBooksBean, Long> {

    public static final String TABLENAME = "BANGDAN_BOOKS_BEAN";

    /**
     * Properties of entity BangdanBooksBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property BookID = new Property(2, String.class, "bookID", false, "BOOK_ID");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Author = new Property(4, String.class, "author", false, "AUTHOR");
        public final static Property ShortIntro = new Property(5, String.class, "shortIntro", false, "SHORT_INTRO");
        public final static Property Cover = new Property(6, String.class, "cover", false, "COVER");
        public final static Property Site = new Property(7, String.class, "site", false, "SITE");
        public final static Property Banned = new Property(8, String.class, "banned", false, "BANNED");
        public final static Property LatelyFollower = new Property(9, String.class, "latelyFollower", false, "LATELY_FOLLOWER");
        public final static Property RetentionRatio = new Property(10, String.class, "retentionRatio", false, "RETENTION_RATIO");
    }


    public BangdanBooksBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BangdanBooksBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BANGDAN_BOOKS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"BOOK_ID\" TEXT," + // 2: bookID
                "\"TITLE\" TEXT," + // 3: title
                "\"AUTHOR\" TEXT," + // 4: author
                "\"SHORT_INTRO\" TEXT," + // 5: shortIntro
                "\"COVER\" TEXT," + // 6: cover
                "\"SITE\" TEXT," + // 7: site
                "\"BANNED\" TEXT," + // 8: banned
                "\"LATELY_FOLLOWER\" TEXT," + // 9: latelyFollower
                "\"RETENTION_RATIO\" TEXT);"); // 10: retentionRatio
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_BANGDAN_BOOKS_BEAN_TYPE ON BANGDAN_BOOKS_BEAN" +
                " (\"TYPE\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BANGDAN_BOOKS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BangdanBooksBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getType());
 
        String bookID = entity.getBookID();
        if (bookID != null) {
            stmt.bindString(3, bookID);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(5, author);
        }
 
        String shortIntro = entity.getShortIntro();
        if (shortIntro != null) {
            stmt.bindString(6, shortIntro);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(7, cover);
        }
 
        String site = entity.getSite();
        if (site != null) {
            stmt.bindString(8, site);
        }
 
        String banned = entity.getBanned();
        if (banned != null) {
            stmt.bindString(9, banned);
        }
 
        String latelyFollower = entity.getLatelyFollower();
        if (latelyFollower != null) {
            stmt.bindString(10, latelyFollower);
        }
 
        String retentionRatio = entity.getRetentionRatio();
        if (retentionRatio != null) {
            stmt.bindString(11, retentionRatio);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BangdanBooksBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getType());
 
        String bookID = entity.getBookID();
        if (bookID != null) {
            stmt.bindString(3, bookID);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(5, author);
        }
 
        String shortIntro = entity.getShortIntro();
        if (shortIntro != null) {
            stmt.bindString(6, shortIntro);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(7, cover);
        }
 
        String site = entity.getSite();
        if (site != null) {
            stmt.bindString(8, site);
        }
 
        String banned = entity.getBanned();
        if (banned != null) {
            stmt.bindString(9, banned);
        }
 
        String latelyFollower = entity.getLatelyFollower();
        if (latelyFollower != null) {
            stmt.bindString(10, latelyFollower);
        }
 
        String retentionRatio = entity.getRetentionRatio();
        if (retentionRatio != null) {
            stmt.bindString(11, retentionRatio);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public BangdanBooksBean readEntity(Cursor cursor, int offset) {
        BangdanBooksBean entity = new BangdanBooksBean( //
            cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // bookID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // author
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // shortIntro
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // cover
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // site
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // banned
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // latelyFollower
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // retentionRatio
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BangdanBooksBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setBookID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAuthor(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setShortIntro(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCover(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSite(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setBanned(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLatelyFollower(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRetentionRatio(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BangdanBooksBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BangdanBooksBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BangdanBooksBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}