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
import com.hxd.fendbzbuys.domain.gen.DaoMaster;
import com.hxd.fendbzbuys.domain.gen.DaoSession;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.domain.gen.SousuoHistoryBeanDao;

import org.greenrobot.greendao.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichao on 17/8/8.
 */

public class DaoManager {
    public  DaoSession daoSession;
    public DaoManager(){
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(MyApplication.getMyapplication().getApplicationContext(),"fendbzbuys.db",null);
        DaoMaster daoMaster=new DaoMaster(devOpenHelper.getWritableDb());
        this.daoSession=daoMaster.newSession();
    }


    private static class SingleClass{
        private static final DaoManager DAO_MANAGER=new DaoManager();
    }
    public static DaoManager getInstance(){
        return SingleClass.DAO_MANAGER;
    }
    public   BookPathOneBeanDao getBookPathBeanDao1() {
        return daoSession.getBookPathOneBeanDao();
    }
    public SousuoHistoryBeanDao getSousuoHistoryBeanDao() {
        return daoSession.getSousuoHistoryBeanDao();
    }

    public   BookPathTwoBeanDao getBookPathBeanDao2() {
        return daoSession.getBookPathTwoBeanDao();
    }

    public   BookPathThreeBeanDao getBookPathBeanDao3() {
        return daoSession.getBookPathThreeBeanDao();
    }

    public   BookPathFourBeanDao getBookPathBeanDao4() {
        return daoSession.getBookPathFourBeanDao();
    }

    public   BookPathFiveBeanDao getBookPathBeanDao5() {
        return daoSession.getBookPathFiveBeanDao();
    }

    public   BookPathSixBeanDao getBookPathBeanDao6() {
        return daoSession.getBookPathSixBeanDao();
    }

    public   BookPathSevenBeanDao getBookPathBeanDao7() {
        return daoSession.getBookPathSevenBeanDao();
    }

    public  BookPathEightBeanDao getBookPathBeanDao8() {
        return daoSession.getBookPathEightBeanDao();
    }

    public  BookPathNineBeanDao getBookPathBeanDao9() {
        return daoSession.getBookPathNineBeanDao();
    }

    public   ShujiaBookBeanDao getShujiaBookBeanDao() {
        return daoSession.getShujiaBookBeanDao();
    }
    public   BangdanBeanDao getBangdanBeanDao() {
        return daoSession.getBangdanBeanDao();
    }public   BangdanBooksBeanDao getBangdanBooksBeanDao() {
        return daoSession.getBangdanBooksBeanDao();
    }

    public   BookPathBeanDao getBookPathBeanDao() {
        return daoSession.getBookPathBeanDao();
    }
    public int getKongXianBookPathBeanDao(){

        List<Integer> source=new ArrayList();
        for(int i=0;i<10;i++){
            source.add(i);
        }
        List<ShujiaBookBean>shujiaBookBeanList=daoSession.getShujiaBookBeanDao().loadAll();
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
