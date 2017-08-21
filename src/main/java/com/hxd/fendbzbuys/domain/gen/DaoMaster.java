package com.hxd.fendbzbuys.domain.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        BangdanBeanDao.createTable(db, ifNotExists);
        BangdanBooksBeanDao.createTable(db, ifNotExists);
        BookPathBeanDao.createTable(db, ifNotExists);
        BookPathEightBeanDao.createTable(db, ifNotExists);
        BookPathFiveBeanDao.createTable(db, ifNotExists);
        BookPathFourBeanDao.createTable(db, ifNotExists);
        BookPathNineBeanDao.createTable(db, ifNotExists);
        BookPathOneBeanDao.createTable(db, ifNotExists);
        BookPathSevenBeanDao.createTable(db, ifNotExists);
        BookPathSixBeanDao.createTable(db, ifNotExists);
        BookPathThreeBeanDao.createTable(db, ifNotExists);
        BookPathTwoBeanDao.createTable(db, ifNotExists);
        BookSourceBeanDao.createTable(db, ifNotExists);
        ShujiaBookBeanDao.createTable(db, ifNotExists);
        StatisticsBeanDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        BangdanBeanDao.dropTable(db, ifExists);
        BangdanBooksBeanDao.dropTable(db, ifExists);
        BookPathBeanDao.dropTable(db, ifExists);
        BookPathEightBeanDao.dropTable(db, ifExists);
        BookPathFiveBeanDao.dropTable(db, ifExists);
        BookPathFourBeanDao.dropTable(db, ifExists);
        BookPathNineBeanDao.dropTable(db, ifExists);
        BookPathOneBeanDao.dropTable(db, ifExists);
        BookPathSevenBeanDao.dropTable(db, ifExists);
        BookPathSixBeanDao.dropTable(db, ifExists);
        BookPathThreeBeanDao.dropTable(db, ifExists);
        BookPathTwoBeanDao.dropTable(db, ifExists);
        BookSourceBeanDao.dropTable(db, ifExists);
        ShujiaBookBeanDao.dropTable(db, ifExists);
        StatisticsBeanDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(BangdanBeanDao.class);
        registerDaoClass(BangdanBooksBeanDao.class);
        registerDaoClass(BookPathBeanDao.class);
        registerDaoClass(BookPathEightBeanDao.class);
        registerDaoClass(BookPathFiveBeanDao.class);
        registerDaoClass(BookPathFourBeanDao.class);
        registerDaoClass(BookPathNineBeanDao.class);
        registerDaoClass(BookPathOneBeanDao.class);
        registerDaoClass(BookPathSevenBeanDao.class);
        registerDaoClass(BookPathSixBeanDao.class);
        registerDaoClass(BookPathThreeBeanDao.class);
        registerDaoClass(BookPathTwoBeanDao.class);
        registerDaoClass(BookSourceBeanDao.class);
        registerDaoClass(ShujiaBookBeanDao.class);
        registerDaoClass(StatisticsBeanDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
