package com.hxd.fendbzbuys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.BaseActivity;
import com.hxd.fendbzbuys.base.OnSuccessCallback;
import com.hxd.fendbzbuys.domain.BangdanBean;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.FenleiBookTypeInfo;
import com.hxd.fendbzbuys.domain.GenderInfo;
import com.hxd.fendbzbuys.domain.StatisticsInfo;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;
import com.hxd.fendbzbuys.domain.gen.BangdanBeanDao;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
import com.hxd.fendbzbuys.domain.gen.DaoMaster;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.receiver.NetworkConnectChangedReceiver;
import com.hxd.fendbzbuys.utils.UIUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by lichao on 17/7/3.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.textview_splash)
    TextView textview;
    Timer mTimer;
    int flag=3;
    BangdanBeanDao bangdanBeanDao;
    BangdanBooksBeanDao bangdanBooksBeanDao;

    boolean isUpdate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE
                ).subscribe(granted->{
            if(granted){
                Log.e("获取权限成功:::::", "---------READ_PHONE_STATE--");
            }else{
                Log.e("获取权限失败:::::", "---------READ_PHONE_STATE--");
            }
        });
        new RxPermissions(this).request(
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(granted->{
            if(granted){
                Log.e("获取权限成功:::::", "---------READ_EXTERNAL_STORAGE--");
            }else{
                Log.e("获取权限失败:::::", "---------READ_EXTERNAL_STORAGE--");
            }
        });
        new RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).subscribe(granted->{
            if(granted){
                Log.e("获取权限成功:::::", "---------WRITE_EXTERNAL_STORAGE--");
            }else{
                Log.e("获取权限失败:::::", "---------WRITE_EXTERNAL_STORAGE--");
            }
        });
        new RxPermissions(this).request(
                Manifest.permission.WRITE_SETTINGS).subscribe(granted->{
            if(granted){
                Log.e("获取权限成功:::::", "---------WRITE_SETTINGS--");
            }else{
                Log.e("获取权限失败:::::", "---------WRITE_SETTINGS--");
            }
        });
        new RxPermissions(this).request(
                Manifest.permission.REORDER_TASKS).subscribe(granted->{
            if(granted){
                Log.e("获取权限成功:::::", "---------WRITE_SETTINGS--");
            }else{
                Log.e("获取权限失败:::::", "---------WRITE_SETTINGS--");
            }
        });
       initApp();

    }
    private void initApp(){
        if(Constant.sp==null){
            Constant.sp=getPreferences(MODE_PRIVATE);
        }
        long preupdate=Constant.sp.getLong("preupdate",0);
        long currentdate=System.currentTimeMillis();
        Log.e("preupdate:::::", "-----------"+preupdate);
        Log.e("currentdate:::::", "-----------"+currentdate);
        Log.e("shijianchazhi:::::", "-----------"+(currentdate-preupdate)/(1000*60*60));
        if(preupdate==0 ||(currentdate-preupdate)/(1000*60*60)>24){
            isUpdate=true;

        }else {
            isUpdate=false;
        }
        isinitdata();
        Log.e("isUpdate:::::", "-----------"+isUpdate);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        initNetWorkReceiver();
    }
    private void initNetWorkReceiver() {
        NetworkConnectChangedReceiver.netWorkOnlineDosomeThing(new OnSuccessCallback() {
            @Override
            public void nextStep() {
                if(SplashActivity.this!=null&&!SplashActivity.this.isFinishing()){
                    isinitdata();
                }
            }
        });
    }

    private void delayFinish(){
        textview.setVisibility(View.VISIBLE);
        textview.setText(flag+"s 后进入");
        mTimer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(flag>0){
                            textview.setText(flag+"s 后进入");
                            flag--;
                        }else{
                            mTimer.cancel();
                            SplashActivity.this.startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        }

                    }
                });
            }
        };
        mTimer.schedule(task,1000,1000);
    }
    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return  new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return false;
    }

    @Override
    public void init() {

    }
    private void isinitdata(){
        if (isUpdate) {
            Log.e("更新", "-----------------------------------------------");
            FBNetwork.getInstance().getGender().subscribe(new ProcressSubsciber<GenderInfo>(false,false) {
                @Override
                public void onNext(GenderInfo genderInfo) {
                    super.onNext(genderInfo);
                    delayFinish();
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
                    //
                    /*new RxPermissions(SplashActivity.this).request(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_SETTINGS).subscribe(granted->{
                        if(granted){
                            UIUtils.showToast("获取权限成功");

                        }else{
                            UIUtils.showToast("本应用需要存储权限才能使用");
                        }
                    });*/
                    bangdanBeanDao= DaoManager.getInstance().getBangdanBeanDao();
                    bangdanBooksBeanDao=DaoManager.getInstance().getBangdanBooksBeanDao();
                    savaBangDanData();

                    getMaleAndFemalAll();

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    textview.setText("请检查你的网络连接");
                }
            });
            getFenleiInfo();
        }else{
            delayFinish();
        }

    }
    private void savaBangDanData() {
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

    private void getFenleiInfo(){
        FBNetwork.getInstance().getStatistics().subscribe(new ProcressSubsciber<FenleiBookTypeInfo>(false,false) {
            @Override
            public void onNext(FenleiBookTypeInfo statisticsInfo) {
                Log.e("bbbbb", "-----------------------------------------------");
                super.onNext(statisticsInfo);
                Constant.statisticsInfo=statisticsInfo;
              //  String statisticsInfostr= GsonUtils.bean2json(statisticsInfo);
               // Constant.sp.edit().putString("statisticsInfo",statisticsInfostr).commit();
            }
        });
    }
    private void getMaleAndFemalAll(){
        getMaleZuireyue();
        getMaleZuirezong();
        getMaleZuirezhou();
        getMaleQianlizhou();
        getMaleQianliyue();
        getMaleQianlizong();
        getMaleWanjiezong();
        getMaleWanjieyue();
        getMaleWanjiezhou();
        getFemalWanjiezhou();
        getFemalWanjieyue();
        getFemalWanjiezong();
        getFemalQianlizong();
        getFemalQianliyue();
        getFemalQianlizhou();
        getFemalZuirezhou();
        getFemalZuireyue();
        getFemalZuirezong();
    }
    private void getMaleZuireyue(){
        FBNetwork.getInstance().getMaleZuireyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                Log.e("STYPE_maleZuireyue", ":::::: "+zuireBangInfo.ranking.books.size() );
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(1000),BangdanBooksBeanDao.Properties.Id.lt(2000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }

                }

            }
        });
    }
    private void getMaleZuirezong(){
        FBNetwork.getInstance().getMaleZuirezong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                Log.e("STYPE_maleZuirezong", ":::::: "+zuireBangInfo.ranking.books.size() );
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(2000),BangdanBooksBeanDao.Properties.Id.lt(3000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleZuirezhou(){
        FBNetwork.getInstance().getMaleZuirezhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                Log.e("STYPE_maleZuirezong", ":::::: "+zuireBangInfo.ranking.books.size() );
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(3000),BangdanBooksBeanDao.Properties.Id.lt(4000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleQianlizhou(){
        FBNetwork.getInstance().getMaleQianlizhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(4000),BangdanBooksBeanDao.Properties.Id.lt(5000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleQianliyue(){
        FBNetwork.getInstance().getMaleQianliyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(5000),BangdanBooksBeanDao.Properties.Id.lt(6000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleQianlizong(){
        FBNetwork.getInstance().getMaleQianlizong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(6000),BangdanBooksBeanDao.Properties.Id.lt(7000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleWanjiezong(){
        FBNetwork.getInstance().getMaleWanjiezong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(7000),BangdanBooksBeanDao.Properties.Id.lt(8000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleWanjieyue(){
        FBNetwork.getInstance().getMaleWanjieyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(8000),BangdanBooksBeanDao.Properties.Id.lt(9000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getMaleWanjiezhou(){
        FBNetwork.getInstance().getMaleWanjiezhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(9000),BangdanBooksBeanDao.Properties.Id.lt(10000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalWanjiezhou(){
        FBNetwork.getInstance().getFemaleWanjiezhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(10000),BangdanBooksBeanDao.Properties.Id.lt(11000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalWanjieyue(){
        FBNetwork.getInstance().getFemaleWanjieyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(11000),BangdanBooksBeanDao.Properties.Id.lt(12000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalWanjiezong(){
        FBNetwork.getInstance().getFemaleWanjiezong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(12000),BangdanBooksBeanDao.Properties.Id.lt(13000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalQianlizong(){
        FBNetwork.getInstance().getFemaleQianlizong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(13000),BangdanBooksBeanDao.Properties.Id.lt(14000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalQianliyue(){
        FBNetwork.getInstance().getFemaleQianliyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(14000),BangdanBooksBeanDao.Properties.Id.lt(15000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalQianlizhou(){
        FBNetwork.getInstance().getFemaleQianlizhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(15000),BangdanBooksBeanDao.Properties.Id.lt(16000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalZuirezhou(){
        FBNetwork.getInstance().getFemaleZuirezhou().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(16000),BangdanBooksBeanDao.Properties.Id.lt(17000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalZuireyue(){
        FBNetwork.getInstance().getFemaleZuireyue().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(17000),BangdanBooksBeanDao.Properties.Id.lt(18000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    private void getFemalZuirezong(){
        FBNetwork.getInstance().getFemaleZuirezong().subscribe(new ProcressSubsciber<ZuireBangInfo>(false,false) {
            @Override
            public void onNext(ZuireBangInfo zuireBangInfo) {
                super.onNext(zuireBangInfo);
                boolean isUpdate=false;
                if(bangdanBooksBeanDao.queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(18000),BangdanBooksBeanDao.Properties.Id.lt(19000)).list().size()>0){
                    isUpdate=true;
                }
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
                    if(isUpdate){
                        bangdanBooksBeanDao.update(bangdanBooksBean);
                    }else {
                        bangdanBooksBeanDao.insertOrReplace(bangdanBooksBean);
                    }
                }
            }
        });
    }
    @Override
    public int getLayoutID() {
        return R.layout.oneactivity;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
