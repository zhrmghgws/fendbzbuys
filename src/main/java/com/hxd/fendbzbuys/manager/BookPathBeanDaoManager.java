package com.hxd.fendbzbuys.manager;

import com.hxd.fendbzbuys.MyApplication;
import com.hxd.fendbzbuys.domain.BookContentInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.BookPathBean;
import com.hxd.fendbzbuys.domain.BookPathEightBean;
import com.hxd.fendbzbuys.domain.BookPathFiveBean;
import com.hxd.fendbzbuys.domain.BookPathFourBean;
import com.hxd.fendbzbuys.domain.BookPathNineBean;
import com.hxd.fendbzbuys.domain.BookPathOneBean;
import com.hxd.fendbzbuys.domain.BookPathSevenBean;
import com.hxd.fendbzbuys.domain.BookPathSixBean;
import com.hxd.fendbzbuys.domain.BookPathThreeBean;
import com.hxd.fendbzbuys.domain.BookPathTwoBean;
import com.hxd.fendbzbuys.domain.gen.BookPathBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathEightBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathFiveBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathFourBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathNineBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathOneBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathSevenBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathSixBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathThreeBeanDao;
import com.hxd.fendbzbuys.domain.gen.BookPathTwoBeanDao;

import java.util.List;

/**
 * Created by lichao on 17/8/19.
 */

public class BookPathBeanDaoManager {
    public static BookPathBeanDao bookPathBeanDao = DaoManager.getInstance().daoSession.getBookPathBeanDao();
    public static BookPathOneBeanDao bookPathBeanDao1 = DaoManager.getInstance().daoSession.getBookPathOneBeanDao();
    public static BookPathTwoBeanDao bookPathBeanDao2 = DaoManager.getInstance().daoSession.getBookPathTwoBeanDao();
    public static BookPathThreeBeanDao bookPathBeanDao3 = DaoManager.getInstance().daoSession.getBookPathThreeBeanDao();
    public static BookPathFourBeanDao bookPathBeanDao4 = DaoManager.getInstance().daoSession.getBookPathFourBeanDao();
    public static BookPathFiveBeanDao bookPathBeanDao5 = DaoManager.getInstance().daoSession.getBookPathFiveBeanDao();
    public static BookPathSixBeanDao bookPathBeanDao6 = DaoManager.getInstance().daoSession.getBookPathSixBeanDao();
    public static BookPathSevenBeanDao bookPathBeanDao7 = DaoManager.getInstance().daoSession.getBookPathSevenBeanDao();
    public static BookPathEightBeanDao bookPathBeanDao8 = DaoManager.getInstance().daoSession.getBookPathEightBeanDao();
    public static BookPathNineBeanDao bookPathBeanDao9 = DaoManager.getInstance().daoSession.getBookPathNineBeanDao();
    public static void saveduiyingBookPathBeanDao(int bookpathid, List<BookPathBean> beanlist) {
        switch (bookpathid) {
            case 0:
                bookPathBeanDao.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathBean bookPathBean = new BookPathBean();
                            bookPathBean.id = beanlist.get(i).id;
                            bookPathBean.bookId = beanlist.get(i).bookId;
                            bookPathBean.content = beanlist.get(i).content;
                            bookPathBean.localPath = beanlist.get(i).localPath;
                            bookPathBean.netUrl = beanlist.get(i).netUrl;
                            bookPathBean.title = beanlist.get(i).title;
                            bookPathBeanDao.insertOrReplace(bookPathBean);
                        }
                    }
                });
                break;
            case 1:
                bookPathBeanDao1.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathOneBean bookPathBean1 = new BookPathOneBean();
                            bookPathBean1.id = beanlist.get(i).id;
                            bookPathBean1.bookId = beanlist.get(i).bookId;
                            bookPathBean1.content = beanlist.get(i).content;
                            bookPathBean1.localPath = beanlist.get(i).localPath;
                            bookPathBean1.netUrl = beanlist.get(i).netUrl;
                            bookPathBean1.title = beanlist.get(i).title;
                            bookPathBeanDao1.insertOrReplace(bookPathBean1);
                        }
                    }
                });
                break;
            case 2:
                bookPathBeanDao2.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathTwoBean bookPathBean2 = new BookPathTwoBean();
                            bookPathBean2.id = beanlist.get(i).id;
                            bookPathBean2.bookId = beanlist.get(i).bookId;
                            bookPathBean2.content = beanlist.get(i).content;
                            bookPathBean2.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean2.netUrl = beanlist.get(i).netUrl;
                            bookPathBean2.title = beanlist.get(i).title;
                            bookPathBeanDao2.insertOrReplace(bookPathBean2);
                        }
                    }
                });

                break;
            case 3:
                bookPathBeanDao3.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathThreeBean bookPathBean3 = new BookPathThreeBean();
                            bookPathBean3.id = beanlist.get(i).id;
                            bookPathBean3.bookId = beanlist.get(i).bookId;
                            bookPathBean3.content = beanlist.get(i).content;
                            bookPathBean3.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean3.netUrl = beanlist.get(i).netUrl;
                            bookPathBean3.title = beanlist.get(i).title;
                            bookPathBeanDao3.insertOrReplace(bookPathBean3);
                        }
                    }
                });

                break;
            case 4:
                bookPathBeanDao4.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathFourBean bookPathBean4 = new BookPathFourBean();
                            bookPathBean4.id = beanlist.get(i).id;
                            bookPathBean4.bookId = beanlist.get(i).bookId;
                            bookPathBean4.content = beanlist.get(i).content;
                            bookPathBean4.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean4.netUrl = beanlist.get(i).netUrl;
                            bookPathBean4.title = beanlist.get(i).title;
                            bookPathBeanDao4.insertOrReplace(bookPathBean4);
                        }
                    }
                });

                break;
            case 5:
                bookPathBeanDao5.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathFiveBean bookPathBean5 = new BookPathFiveBean();
                            bookPathBean5.id = beanlist.get(i).id;
                            bookPathBean5.bookId = beanlist.get(i).bookId;
                            bookPathBean5.content = beanlist.get(i).content;
                            bookPathBean5.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean5.netUrl = beanlist.get(i).netUrl;
                            bookPathBean5.title = beanlist.get(i).title;
                            bookPathBeanDao5.insertOrReplace(bookPathBean5);
                        }
                    }
                });

                break;
            case 6:
                bookPathBeanDao6.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathSixBean bookPathBean6 = new BookPathSixBean();
                            bookPathBean6.id = beanlist.get(i).id;
                            bookPathBean6.bookId = beanlist.get(i).bookId;
                            bookPathBean6.content = beanlist.get(i).content;
                            bookPathBean6.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean6.netUrl = beanlist.get(i).netUrl;
                            bookPathBean6.title = beanlist.get(i).title;
                            bookPathBeanDao6.insertOrReplace(bookPathBean6);
                        }
                    }
                });

                break;
            case 7:
                bookPathBeanDao7.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathSevenBean bookPathBean7 = new BookPathSevenBean();
                            bookPathBean7.id = beanlist.get(i).id;
                            bookPathBean7.bookId = beanlist.get(i).bookId;
                            bookPathBean7.content = beanlist.get(i).content;
                            bookPathBean7.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean7.netUrl = beanlist.get(i).netUrl;
                            bookPathBean7.title = beanlist.get(i).title;
                            bookPathBeanDao7.insertOrReplace(bookPathBean7);
                        }
                    }
                });

                break;
            case 8:
                bookPathBeanDao8.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathEightBean bookPathBean8 = new BookPathEightBean();
                            bookPathBean8.id = beanlist.get(i).id;
                            bookPathBean8.bookId = beanlist.get(i).bookId;
                            bookPathBean8.content = beanlist.get(i).content;
                            bookPathBean8.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean8.netUrl = beanlist.get(i).netUrl;
                            bookPathBean8.title = beanlist.get(i).title;
                            bookPathBeanDao8.insertOrReplace(bookPathBean8);
                        }
                    }
                });

                break;
            case 9:
                bookPathBeanDao9.getSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < beanlist.size(); i++) {
                            BookPathNineBean bookPathBean9 = new BookPathNineBean();
                            bookPathBean9.id = beanlist.get(i).id;
                            bookPathBean9.bookId = beanlist.get(i).bookId;
                            bookPathBean9.content = beanlist.get(i).content;
                            bookPathBean9.localPath = beanlist.get(i).localPath;
                            ;
                            bookPathBean9.netUrl = beanlist.get(i).netUrl;
                            bookPathBean9.title = beanlist.get(i).title;
                            bookPathBeanDao9.insertOrReplace(bookPathBean9);
                        }
                    }
                });

                break;

        }
    }
    public static long getduiyingBookPathBeanDao(int i, int j, BookContentInfo bookContentInfo,String bookid,List<BookMuluInfo.ChaptersEntity> muluList) {
        switch (i) {
            case 0:
                if (bookPathBeanDao != null ) {
                    BookPathBean bookPathBean = new BookPathBean();
                    bookPathBean.id = Long.valueOf(j);
                    bookPathBean.bookId = bookid;
                    bookPathBean.content = bookContentInfo.chapter.body;
                    bookPathBean.localPath = "";
                    bookPathBean.netUrl = muluList.get(j).link;
                    bookPathBean.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao.insertOrReplace(bookPathBean);
                }
            case 1:
                if (bookPathBeanDao1 != null ) {
                    BookPathOneBean bookPathBean1 = new BookPathOneBean();
                    bookPathBean1.id = Long.valueOf(j);
                    bookPathBean1.bookId = bookid;
                    bookPathBean1.content = bookContentInfo.chapter.body;
                    bookPathBean1.localPath = "";
                    bookPathBean1.netUrl = muluList.get(j).link;
                    bookPathBean1.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao1.insertOrReplace(bookPathBean1);
                }

            case 2:
                if (bookPathBeanDao2 != null ) {
                    BookPathTwoBean bookPathBean2 = new BookPathTwoBean();
                    bookPathBean2.id = Long.valueOf(j);
                    bookPathBean2.bookId = bookid;
                    bookPathBean2.content = bookContentInfo.chapter.body;
                    bookPathBean2.localPath = "";
                    bookPathBean2.netUrl = muluList.get(j).link;
                    bookPathBean2.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao2.insertOrReplace(bookPathBean2);
                }

            case 3:
                if (bookPathBeanDao3 != null ) {
                    BookPathThreeBean bookPathBean3 = new BookPathThreeBean();
                    bookPathBean3.id = Long.valueOf(j);
                    bookPathBean3.bookId = bookid;
                    bookPathBean3.content = bookContentInfo.chapter.body;
                    bookPathBean3.localPath = "";
                    bookPathBean3.netUrl = muluList.get(j).link;
                    bookPathBean3.title =muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao3.insertOrReplace(bookPathBean3);
                }

            case 4:
                if (bookPathBeanDao4 != null ) {
                    BookPathFourBean bookPathBean4 = new BookPathFourBean();
                    bookPathBean4.id = Long.valueOf(j);
                    bookPathBean4.bookId = bookid;
                    bookPathBean4.content = bookContentInfo.chapter.body;
                    bookPathBean4.localPath = "";
                    bookPathBean4.netUrl =muluList.get(j).link;
                    bookPathBean4.title =muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao4.insertOrReplace(bookPathBean4);
                }

            case 5:
                if (bookPathBeanDao5 != null ) {
                    BookPathFiveBean bookPathBean5 = new BookPathFiveBean();
                    bookPathBean5.id = Long.valueOf(j);
                    bookPathBean5.bookId = bookid;
                    bookPathBean5.content = bookContentInfo.chapter.body;
                    bookPathBean5.localPath = "";
                    bookPathBean5.netUrl = muluList.get(j).link;
                    bookPathBean5.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao5.insertOrReplace(bookPathBean5);
                }

            case 6:
                if (bookPathBeanDao6 != null ) {
                    BookPathSixBean bookPathBean6 = new BookPathSixBean();
                    bookPathBean6.id = Long.valueOf(j);
                    bookPathBean6.bookId = bookid;
                    bookPathBean6.content = bookContentInfo.chapter.body;
                    bookPathBean6.localPath = "";
                    bookPathBean6.netUrl = muluList.get(j).link;
                    bookPathBean6.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao6.insertOrReplace(bookPathBean6);
                }

            case 7:
                if (bookPathBeanDao7 != null ) {
                    BookPathSevenBean bookPathBean7 = new BookPathSevenBean();
                    bookPathBean7.id = Long.valueOf(j);
                    bookPathBean7.bookId = bookid;
                    bookPathBean7.content = bookContentInfo.chapter.body;
                    bookPathBean7.localPath = "";
                    bookPathBean7.netUrl =muluList.get(j).link;
                    bookPathBean7.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao7.insertOrReplace(bookPathBean7);
                }
            case 8:
                if (bookPathBeanDao8 != null ) {
                    BookPathEightBean bookPathBean8 = new BookPathEightBean();
                    bookPathBean8.id = Long.valueOf(j);
                    bookPathBean8.bookId =bookid;
                    bookPathBean8.content = bookContentInfo.chapter.body;
                    bookPathBean8.localPath = "";
                    bookPathBean8.netUrl = muluList.get(j).link;
                    bookPathBean8.title =muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao8.insertOrReplace(bookPathBean8);
                }
            case 9:
                if (bookPathBeanDao9 != null ) {
                    BookPathNineBean bookPathBean9 = new BookPathNineBean();
                    bookPathBean9.id = Long.valueOf(j);
                    bookPathBean9.bookId =bookid;
                    bookPathBean9.content = bookContentInfo.chapter.body;
                    bookPathBean9.localPath = "";
                    bookPathBean9.netUrl = muluList.get(j).link;
                    bookPathBean9.title = muluList.get(j).title==null?"":muluList.get(j).title;
                    return bookPathBeanDao9.insertOrReplace(bookPathBean9);
                }
            default:
                return 0;
        }
    }
    public static long getDuiyingTitleCount(int bookpathid) {
        switch (bookpathid) {
            case 0:
                return bookPathBeanDao.count();

            case 1:
                return bookPathBeanDao1.count();

            case 2:
                return bookPathBeanDao2.count();

            case 3:
                return bookPathBeanDao3.count();

            case 4:
                return bookPathBeanDao4.count();

            case 5:
                return bookPathBeanDao5.count();
            case 6:
                return bookPathBeanDao6.count();
            case 7:
                return bookPathBeanDao7.count();
            case 8:
                return bookPathBeanDao8.count();

            case 9:
                return bookPathBeanDao9.count();
            default:
                return 0;

        }
    }

    public static boolean checkTenData(int i, int j) {
        switch (i) {
            case 0:
                return bookPathBeanDao.load(Long.valueOf(j)) == null;
            case 1:
                return bookPathBeanDao1.load(Long.valueOf(j)) == null;
            case 2:
                return bookPathBeanDao2.load(Long.valueOf(j)) == null;
            case 3:
                return bookPathBeanDao3.load(Long.valueOf(j)) == null;
            case 4:
                return bookPathBeanDao4.load(Long.valueOf(j)) == null;
            case 5:
                return bookPathBeanDao5.load(Long.valueOf(j)) == null;
            case 6:
                return bookPathBeanDao6.load(Long.valueOf(j)) == null;
            case 7:
                return bookPathBeanDao7.load(Long.valueOf(j)) == null;
            case 8:
                return bookPathBeanDao8.load(Long.valueOf(j)) == null;
            case 9:
                return bookPathBeanDao9.load(Long.valueOf(j)) == null;
            default:
                return false;
        }
    }
    public static void cleanBookPathBeanDao(int i) {
        switch (i) {
            case 0:
                bookPathBeanDao.deleteAll();
                break;
            case 1:
                bookPathBeanDao1.deleteAll();
                break;
            case 2:
                bookPathBeanDao2.deleteAll();
                break;
            case 3:
                bookPathBeanDao3.deleteAll();
                break;
            case 4:
                bookPathBeanDao4.deleteAll();
                break;
            case 5:
                bookPathBeanDao5.deleteAll();
                break;
            case 6:
                bookPathBeanDao6.deleteAll();
                break;
            case 7:
                bookPathBeanDao7.deleteAll();
                break;
            case 8:
                bookPathBeanDao8.deleteAll();
                break;
            case 9:
                bookPathBeanDao9.deleteAll();
                break;

        }
    }
}
