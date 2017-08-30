package com.hxd.fendbzbuys.manager;

import android.util.Log;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.base.GetDataCallBack;
import com.hxd.fendbzbuys.domain.BangdanBean;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.FenleiBookTypeInfo;
import com.hxd.fendbzbuys.domain.GenderInfo;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;
import com.hxd.fendbzbuys.domain.gen.BangdanBeanDao;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
import com.hxd.fendbzbuys.domain.gen.DaoMaster;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichao on 17/8/30.
 */

public class GetDataManager {
    public static void  getData(GetDataCallBack callBack){
        FBNetwork.getInstance().getGender().subscribe(new ProcressSubsciber<GenderInfo>(false,false) {
            @Override
            public void onNext(GenderInfo genderInfo) {
                super.onNext(genderInfo);
                callBack.onNext();
                Constant.sp.edit().putLong("preupdate",System.currentTimeMillis()).commit();
                Log.e("aaaaa", "-----------------------------------------------");
                for(int i=0;i<genderInfo.male.size();i++){
                    if("最热榜".equals(genderInfo.male.get(i).shortTitle)){
                        Constant.male_zuire=genderInfo.male.get(i);
                    }else if("潜力榜".equals(genderInfo.male.get(i).shortTitle)){
                        Constant.male_qianli=genderInfo.male.get(i);
                    }else if("完结榜".equals(genderInfo.male.get(i).shortTitle)){
                        Constant.male_wanjie=genderInfo.male.get(i);
                    }
                }
                for(int i=0;i<genderInfo.female.size();i++){
                    if("最热榜".equals(genderInfo.female.get(i).shortTitle)){
                        Constant.female_zuire=genderInfo.female.get(i);
                    }else if("潜力榜".equals(genderInfo.female.get(i).shortTitle)){
                        Constant.female_qianli=genderInfo.female.get(i);
                    }else if("完结榜".equals(genderInfo.female.get(i).shortTitle)){
                        Constant.female_wanjie=genderInfo.female.get(i);
                    }
                }
              
                savaBangDanData();


            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onError();
                
            }
        });
    }
    public static void getFenleiInfo(GetDataCallBack callBack){
        FBNetwork.getInstance().getStatistics().subscribe(new ProcressSubsciber<FenleiBookTypeInfo>(false,false) {
            @Override
            public void onNext(FenleiBookTypeInfo statisticsInfo) {
                Log.e("bbbbb", "-----------------------------------------------");
                super.onNext(statisticsInfo);
                callBack.onNext();
                Constant.statisticsInfo=statisticsInfo;
                //  String statisticsInfostr= GsonUtils.bean2json(statisticsInfo);
                // Constant.sp.edit().putString("statisticsInfo",statisticsInfostr).commit();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onError();
            }
        });
    }
    public static  List<BangdanBooksBean> saveMaleZuireyue(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1000;i<zuireBangInfo.ranking.books.size()+1000;i++){
                    int j=i-1000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleZuireyue;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);

                }
            }
        }).start();
        return maleZuireyue;
    }
    public static List<BangdanBooksBean>  savegMaleZuirezong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=2000;i<2000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-2000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleZuirezong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }

    public static List<BangdanBooksBean> saveMaleZuirezhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=3000;i<3000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-3000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleZuirezhou;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static  List<BangdanBooksBean> saveMaleQianlizhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=4000;i<4000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-4000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleQianlizhou;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveMaleQianliyue(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=5000;i<5000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-5000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleQianliyue;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveMaleQianlizong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=6000;i<6000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-6000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleQianlizong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveMaleWanjiezong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=7000;i<7000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-7000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleWanjiezong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static  List<BangdanBooksBean> saveMaleWanjieyue(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=8000;i<8000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-8000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleWanjieyue;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveMaleWanjiezhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=9000;i<9000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-9000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_maleWanjiezhou;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveFemalWanjiezhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=10000;i<10000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-10000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femalWanjiezho;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }

   public static List<BangdanBooksBean> saveFemalWanjieyue(ZuireBangInfo zuireBangInfo){
       BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
       List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
       new Thread(new Runnable() {
           @Override
           public void run() {
               for(int i=11000;i<11000+zuireBangInfo.ranking.books.size();i++){
                   int j=i-11000;
                   BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                   bangdanBooksBean.id=i;
                   bangdanBooksBean.type=Constant.STYPE_femalWanjieyue;
                   bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                   bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                   bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                   bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                   bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                   bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                   bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                   bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                   bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                   maleZuireyue.add(bangdanBooksBean);
                   bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
               }
           }
       }).start();
      return maleZuireyue;
   }
    public static List<BangdanBooksBean> saveFemalWanjiezong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=12000;i<12000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-12000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femalWanjiezong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveFemalQianlizong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=13000;i<13000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-13000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femaleQianlizong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveFemalQianliyue(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=14000;i<14000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-14000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femalQianliyue;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }
    public static  List<BangdanBooksBean> saveFemalQianlizhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=15000;i<15000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-15000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femalQianlizho;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
      return maleZuireyue;
    }

    public static  List<BangdanBooksBean> saveFemalZuirezhou(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
         List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=16000;i<16000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-16000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femaleZuirezhou;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
       return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveFemalZuireyue(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=17000;i<17000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-17000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femalZuireyue;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);
                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }
    public static List<BangdanBooksBean> saveFemalZuirezong(ZuireBangInfo zuireBangInfo){
        BangdanBooksBeanDao bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
        List<BangdanBooksBean> maleZuireyue=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=18000;i<18000+zuireBangInfo.ranking.books.size();i++){
                    int j=i-18000;
                    BangdanBooksBean bangdanBooksBean=new BangdanBooksBean();
                    bangdanBooksBean.id=i;
                    bangdanBooksBean.type=Constant.STYPE_femaleZuirezong;
                    bangdanBooksBean.bookID=zuireBangInfo.ranking.books.get(j)._id;
                    bangdanBooksBean.author=zuireBangInfo.ranking.books.get(j).author;
                    bangdanBooksBean.cover=zuireBangInfo.ranking.books.get(j).cover;
                    bangdanBooksBean.banned=zuireBangInfo.ranking.books.get(j).banned;
                    bangdanBooksBean.title=zuireBangInfo.ranking.books.get(j).title;
                    bangdanBooksBean.shortIntro=zuireBangInfo.ranking.books.get(j).shortIntro;
                    bangdanBooksBean.site=zuireBangInfo.ranking.books.get(j).site;
                    bangdanBooksBean.latelyFollower=zuireBangInfo.ranking.books.get(j).latelyFollower;
                    bangdanBooksBean.retentionRatio=zuireBangInfo.ranking.books.get(j).retentionRatio;
                    maleZuireyue.add(bangdanBooksBean);

                    bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                }
            }
        }).start();
        return maleZuireyue;
    }

    public static void savaBangDanData() {
        BangdanBeanDao bangdanBeanDao=DaoManager.getInstance().getBangdanBeanDao();
        if(bangdanBeanDao.loadAll().size()>0){
            bangdanBeanDao.deleteAll();
        }
        for(int i=1;i<19;i++){
            BangdanBean bangdanbean=new BangdanBean();
            switch (i){
                case 1:
                    bangdanbean.id=Constant.STYPE_maleZuirezong;
                    bangdanbean.sourceID=Constant.male_zuire._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 2:
                    bangdanbean.id=Constant.STYPE_maleZuireyue;
                    bangdanbean.sourceID=Constant.male_zuire.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 3:
                    bangdanbean.id=Constant.STYPE_maleZuirezhou;
                    bangdanbean.sourceID=Constant.male_zuire.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 4:
                    bangdanbean.id=Constant.STYPE_maleQianlizong;
                    bangdanbean.sourceID=Constant.male_qianli._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 5:
                    bangdanbean.id=Constant.STYPE_maleQianliyue;
                    bangdanbean.sourceID=Constant.male_qianli.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 6:
                    bangdanbean.id=Constant.STYPE_maleQianlizhou;
                    bangdanbean.sourceID=Constant.male_qianli.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 7:
                    bangdanbean.id=Constant.STYPE_maleWanjiezong;
                    bangdanbean.sourceID=Constant.male_qianli._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 8:
                    bangdanbean.id=Constant.STYPE_maleWanjieyue;
                    bangdanbean.sourceID=Constant.male_qianli.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 9:
                    bangdanbean.id=Constant.STYPE_maleWanjiezhou;
                    bangdanbean.sourceID=Constant.male_qianli.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 10:
                    bangdanbean.id=Constant.STYPE_femaleZuirezong;
                    bangdanbean.sourceID=Constant.female_zuire._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 11:
                    bangdanbean.id=Constant.STYPE_femaleZuirezhou;
                    bangdanbean.sourceID=Constant.female_zuire.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 12:
                    bangdanbean.id=Constant.STYPE_femalZuireyue;
                    bangdanbean.sourceID=Constant.female_zuire.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 13:
                    bangdanbean.id=Constant.STYPE_femaleQianlizong;
                    bangdanbean.sourceID=Constant.female_qianli._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 14:
                    bangdanbean.id=Constant.STYPE_femalQianliyue;
                    bangdanbean.sourceID=Constant.female_qianli.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 15:
                    bangdanbean.id=Constant.STYPE_femalQianlizho;
                    bangdanbean.sourceID=Constant.female_qianli.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 16:
                    bangdanbean.id=Constant.STYPE_femalWanjiezong;
                    bangdanbean.sourceID=Constant.female_wanjie._id;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 17:
                    bangdanbean.id=Constant.STYPE_femalWanjieyue;
                    bangdanbean.sourceID=Constant.female_wanjie.totalRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;
                case 18:
                    bangdanbean.id=Constant.STYPE_femalWanjiezho;
                    bangdanbean.sourceID=Constant.female_wanjie.monthRank;
                    bangdanBeanDao.insertOrReplace(bangdanbean);
                    break;

            }
        }
    }
}
