package com.hxd.fendbzbuys.manager;

import android.util.Log;

import com.apkfuns.logutils.utils.ArrayUtil;
import com.hxd.fendbzbuys.MyApplication;
import com.hxd.fendbzbuys.domain.BookPathBean;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.BangdanBeanDao;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
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
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;

import org.greenrobot.greendao.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichao on 17/8/8.
 */

public class DaoManager {
    private BangdanBeanDao bangdanBeanDao=MyApplication.daoSession.getBangdanBeanDao();
    private BangdanBooksBeanDao bangdanBooksBeanDao=MyApplication.daoSession.getBangdanBooksBeanDao();
    private  ShujiaBookBeanDao  shujiaBookBeanDao = MyApplication.daoSession.getShujiaBookBeanDao();
    private  BookPathBeanDao bookPathBeanDao = MyApplication.daoSession.getBookPathBeanDao();
    private  BookPathOneBeanDao bookPathBeanDao1 = MyApplication.daoSession.getBookPathOneBeanDao();
    private  BookPathTwoBeanDao bookPathBeanDao2 = MyApplication.daoSession.getBookPathTwoBeanDao();
    private  BookPathThreeBeanDao bookPathBeanDao3 = MyApplication.daoSession.getBookPathThreeBeanDao();
    private  BookPathFourBeanDao bookPathBeanDao4 = MyApplication.daoSession.getBookPathFourBeanDao();
    private  BookPathFiveBeanDao bookPathBeanDao5 = MyApplication.daoSession.getBookPathFiveBeanDao();
    private  BookPathSixBeanDao bookPathBeanDao6 = MyApplication.daoSession.getBookPathSixBeanDao();
    private  BookPathSevenBeanDao bookPathBeanDao7 = MyApplication.daoSession.getBookPathSevenBeanDao();
    private  BookPathEightBeanDao bookPathBeanDao8 = MyApplication.daoSession.getBookPathEightBeanDao();
    private  BookPathNineBeanDao bookPathBeanDao9 = MyApplication.daoSession.getBookPathNineBeanDao();




    private static class SingleClass{
        private static final DaoManager DAO_MANAGER=new DaoManager();
    }
    public static DaoManager getInstance(){
        return SingleClass.DAO_MANAGER;
    }
    public   BookPathOneBeanDao getBookPathBeanDao1() {
        return bookPathBeanDao1;
    }

    public   BookPathTwoBeanDao getBookPathBeanDao2() {
        return bookPathBeanDao2;
    }

    public   BookPathThreeBeanDao getBookPathBeanDao3() {
        return bookPathBeanDao3;
    }

    public   BookPathFourBeanDao getBookPathBeanDao4() {
        return bookPathBeanDao4;
    }

    public   BookPathFiveBeanDao getBookPathBeanDao5() {
        return bookPathBeanDao5;
    }

    public   BookPathSixBeanDao getBookPathBeanDao6() {
        return bookPathBeanDao6;
    }

    public   BookPathSevenBeanDao getBookPathBeanDao7() {
        return bookPathBeanDao7;
    }

    public  BookPathEightBeanDao getBookPathBeanDao8() {
        return bookPathBeanDao8;
    }

    public  BookPathNineBeanDao getBookPathBeanDao9() {
        return bookPathBeanDao9;
    }

    public   ShujiaBookBeanDao getShujiaBookBeanDao() {
        return shujiaBookBeanDao;
    }
    public   BangdanBeanDao getBangdanBeanDao() {
        return bangdanBeanDao;
    }public   BangdanBooksBeanDao getBangdanBooksBeanDao() {
        return bangdanBooksBeanDao;
    }

    public   BookPathBeanDao getBookPathBeanDao() {
        return bookPathBeanDao;
    }
    public int getKongXianBookPathBeanDao(){

        List<Integer> source=new ArrayList();
        for(int i=0;i<10;i++){
            source.add(i);
        }
        List<ShujiaBookBean>shujiaBookBeanList=shujiaBookBeanDao.loadAll();
        for(int i=0;i<shujiaBookBeanList.size();i++){
          if(source.contains(shujiaBookBeanList.get(i).bookpathBean)){
              if(shujiaBookBeanList.get(i).bookpathBean!=0){
                  for(int j=0;j<source.size();j++){
                      if(source.get(j)==shujiaBookBeanList.get(i).bookpathBean){
                          source.remove(j);
                      }
                  }

              }
          }
        }
        if(source.size()>1){
            return source.get(1);
        }else{
            return 20;
        }

    }

}
