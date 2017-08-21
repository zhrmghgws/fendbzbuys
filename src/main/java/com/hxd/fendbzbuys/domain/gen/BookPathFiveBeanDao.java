package com.hxd.fendbzbuys.domain.gen;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hxd.fendbzbuys.domain.ShujiaBookBean;

import com.hxd.fendbzbuys.domain.BookPathFiveBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_PATH_FIVE_BEAN".
*/
public class BookPathFiveBeanDao extends AbstractDao<BookPathFiveBean, Long> {

    public static final String TABLENAME = "BOOK_PATH_FIVE_BEAN";

    /**
     * Properties of entity BookPathFiveBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property BookId = new Property(1, String.class, "bookId", false, "BOOK_ID");
        public final static Property NetUrl = new Property(2, String.class, "netUrl", false, "NET_URL");
        public final static Property Content = new Property(3, String.class, "content", false, "CONTENT");
        public final static Property LocalPath = new Property(4, String.class, "localPath", false, "LOCAL_PATH");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property ZhangjieID = new Property(6, int.class, "zhangjieID", false, "ZHANGJIE_ID");
    }

    private DaoSession daoSession;


    public BookPathFiveBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BookPathFiveBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_PATH_FIVE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BOOK_ID\" TEXT," + // 1: bookId
                "\"NET_URL\" TEXT," + // 2: netUrl
                "\"CONTENT\" TEXT," + // 3: content
                "\"LOCAL_PATH\" TEXT," + // 4: localPath
                "\"TITLE\" TEXT," + // 5: title
                "\"ZHANGJIE_ID\" INTEGER NOT NULL );"); // 6: zhangjieID
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_BOOK_PATH_FIVE_BEAN_BOOK_ID ON BOOK_PATH_FIVE_BEAN" +
                " (\"BOOK_ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_PATH_FIVE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookPathFiveBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(2, bookId);
        }
 
        String netUrl = entity.getNetUrl();
        if (netUrl != null) {
            stmt.bindString(3, netUrl);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(5, localPath);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getZhangjieID());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookPathFiveBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(2, bookId);
        }
 
        String netUrl = entity.getNetUrl();
        if (netUrl != null) {
            stmt.bindString(3, netUrl);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(5, localPath);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getZhangjieID());
    }

    @Override
    protected final void attachEntity(BookPathFiveBean entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BookPathFiveBean readEntity(Cursor cursor, int offset) {
        BookPathFiveBean entity = new BookPathFiveBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bookId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // netUrl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // content
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // localPath
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.getInt(offset + 6) // zhangjieID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookPathFiveBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBookId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNetUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLocalPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setZhangjieID(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BookPathFiveBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BookPathFiveBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BookPathFiveBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getShujiaBookBeanDao().getAllColumns());
            builder.append(" FROM BOOK_PATH_FIVE_BEAN T");
            builder.append(" LEFT JOIN SHUJIA_BOOK_BEAN T0 ON T.\"BOOK_ID\"=T0.\"BOOK_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected BookPathFiveBean loadCurrentDeep(Cursor cursor, boolean lock) {
        BookPathFiveBean entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ShujiaBookBean shujiaBookBean = loadCurrentOther(daoSession.getShujiaBookBeanDao(), cursor, offset);
        entity.setShujiaBookBean(shujiaBookBean);

        return entity;    
    }

    public BookPathFiveBean loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<BookPathFiveBean> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<BookPathFiveBean> list = new ArrayList<BookPathFiveBean>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<BookPathFiveBean> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<BookPathFiveBean> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
