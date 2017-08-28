package com.hxd.fendbzbuys;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.domain.BookTotalInfo;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.moduler.account_moduler.ShujiaPresenter;
import com.hxd.fendbzbuys.moduler.laon_moduler.PaihangFragment;
import com.hxd.fendbzbuys.moduler.sousu.SousuoActivity;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends MVPBaseActivity<MainActivityPresenter> {
    private  TelephonyManager tm;
    @BindView(R.id.main_home_shujia)
    RelativeLayout main_home_shujia;
    @BindView(R.id.rl_xiazai_main)
    RelativeLayout rl_xiazai_main;
    @BindView(R.id.rl_sousuo_main)
    RelativeLayout rl_sousuo_main;
    @BindView(R.id.main_home_paihang)
    RelativeLayout main_home_paihang;
    @BindView(R.id.main_home_fenlei)
    RelativeLayout main_home_fenlei;
    @BindView(R.id.viewpage_mainactivity)
    ViewPager viewpage_mainactivity;
    @BindView(R.id.iv_shujia_home)
    ImageView iv_shujia_home;
    @BindView(R.id.iv_shezhi_main)
    ImageView iv_shezhi_main;
    @BindView(R.id.iv_fenlei_home)
    ImageView iv_fenlei_home;
    @BindView(R.id.tv_shujia_home)
    TextView tv_shujia_home;
    @BindView(R.id.tv_title_main)
    TextView tv_title_main;
    @BindView(R.id.iv_paihang_home)
    ImageView iv_paihang_home;
    @BindView(R.id.tv_paihang_home)
    TextView tv_paihang_home;
    @BindView(R.id.tv_nan_titlebar)
    TextView tv_nan_titlebar;
    @BindView(R.id.tv_nv_titlebar)
    TextView tv_nv_titlebar;
    @BindView(R.id.tv_fengexian_titlebar)
    TextView tv_fengexian_titlebar;

    @BindView(R.id.tv_fenlei_home)
    TextView tv_fenlei_home;
    @BindView(R.id.rl_nanornv_titlebar)
    RelativeLayout rl_nanornv_titlebar;
    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.xindai.hxd.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
   // private MessageReceiver mMessageReceiver;
    public boolean isNan=true;
    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return true;
    }
    int width,height;
    @Override
    public void init() {
        Constant.sp=getSharedPreferences("name",MODE_PRIVATE);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();//屏幕宽度
        height = wm.getDefaultDisplay().getHeight();//屏幕高度
        Log.e("屏幕::", "::::::::宽"+width+"::::高" +height);
        Constant.taskID=this.getTaskId();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(Constant.deviceID)) {
            try {
                tm= (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
                new RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE).subscribe(agree->{
                    if(agree){
                        Constant.deviceID = tm.getDeviceId();
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        delayRUN();
    }
    @OnClick(R.id.rl_xiazai_main) void xiazai_click(){
        presenter.downLoadMain();
    }
    @OnClick(R.id.rl_sousuo_main) void sousuo_click(){
        SousuoActivity.invoke(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // unregisterReceiver(mMessageReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        if(Constant.mainposition==Constant.MainPostion.SHUJIA_POSITION){
            presenter.homeloan_click();
        }else if (Constant.mainposition==Constant.MainPostion.PAIHANG_POSITION){
            presenter.homeaccount_click();
        }else if(Constant.mainposition==Constant.MainPostion.FENLEISHUKU_POSITION){
            presenter.homefenlei_click();
        }
        Constant.mainposition= Constant.MainPostion.NONE_POSITION;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground=false;
    }

    @Override
    public MainActivityPresenter creatPresenter() {

        return new MainActivityPresenter(this);
    }




    @OnClick(R.id.main_home_shujia)
    void onclick_homeloan(View view) {
        presenter.homeloan_click();
    }

    @OnClick(R.id.main_home_paihang)
    void onclick_homeaccount(View view) {
            presenter.homeaccount_click();
    }
    @OnClick(R.id.main_home_fenlei)
    void oncli_homefenlei(View view){
        presenter.homefenlei_click();
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    presenter.exitBy2Click();
                    return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    @OnClick(R.id.rl_nanornv_titlebar)
    void onclick_nanornv(View view){
        if(isNan){
            isNan=false;
            tv_nan_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
            tv_nv_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            tv_fengexian_titlebar.setTextColor(getResources().getColor(R.color.e99497));
            tv_nan_titlebar.setTextColor(getResources().getColor(R.color.color_999999));
            tv_nv_titlebar.setTextColor(getResources().getColor(R.color.e99497));

           // tv_nan_titlebar.setTextColor(getResources().getColor(R.color.color_666666));
           // tv_nv_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
            if(presenter.currentPage==1){
                presenter.paihangFragment.nvPaihang();
            }else if(presenter.currentPage==2){
                presenter.fenleiFragment.nvPaihang();
            }
        }else{
            isNan=true;
            tv_nan_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            tv_fengexian_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
            tv_nan_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
            tv_nv_titlebar.setTextColor(getResources().getColor(R.color.color_999999));
            tv_nv_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
          //  tv_nan_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
           // tv_nv_titlebar.setTextColor(getResources().getColor(R.color.color_666666));
            if(presenter.currentPage==1){
                presenter.paihangFragment.nanPaihang();
            }else if(presenter.currentPage==2){
                presenter.fenleiFragment.nanPaihang();
            }
        }
    }
    private void delayRUN(){
        Timer mTimer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getBookUpdate();
                    }
                });
            }
        };
        mTimer.schedule(task,1000,1000*60*60);
    }
    List<ShujiaBookBean> shujiaBookBeanList;
    List<ShujiaBookBean> shujiaBookJiaruBeanList=new ArrayList<>();
    private void getBookUpdate(){
        shujiaBookBeanList= DaoManager.getInstance().getShujiaBookBeanDao().loadAll();
        shujiaBookJiaruBeanList.clear();
        if(shujiaBookBeanList.size()>0){
            for(int i=0;i<shujiaBookBeanList.size();i++){
                if(shujiaBookBeanList.get(i).bookpathBean>0){
                    shujiaBookJiaruBeanList.add(shujiaBookBeanList.get(i));
                }
            }}

        String bookids="";
        if(shujiaBookJiaruBeanList.size()>0){
            for(int i=0;i<shujiaBookJiaruBeanList.size();i++){
                if(i==0){
                    bookids=shujiaBookJiaruBeanList.get(i).bookId;
                }else{
                    bookids=bookids+","+shujiaBookJiaruBeanList.get(i).bookId;
                }

            }
            FBNetwork.getInstance().getTotalCount(bookids).subscribe(new ProcressSubsciber<List<BookTotalInfo>>(false,false) {
                @Override
                public void onNext(List<BookTotalInfo> bookTotalInfos) {
                    super.onNext(bookTotalInfos);
                    for(int i=0;i<bookTotalInfos.size();i++){
                        for(int j=0;j<shujiaBookJiaruBeanList.size();j++){
                            if(shujiaBookJiaruBeanList.get(j).bookId.equals(bookTotalInfos.get(i)._id+"")){
                                ShujiaBookBean shujiaBookBean=shujiaBookJiaruBeanList.get(i);
                                if(!bookTotalInfos.get(i).chaptersCount.equals(shujiaBookBean.bookTotakCount)){
                                    shujiaBookBean.lastChapter=bookTotalInfos.get(i).lastChapter;
                                    shujiaBookBean.bookTotakCount=Integer.parseInt(bookTotalInfos.get(i).chaptersCount);
                                    DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                                    sentNotification(shujiaBookBean);
                                }
                            }
                        }

                    }
                }
            });
        }


    }
    private ActivityManager am;
    public boolean isBackground(Context context) {
        am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                Log.e("后台", "::::::::::::");
                am.moveTaskToFront(Constant.taskID, ActivityManager.MOVE_TASK_WITH_HOME);
                return true;
            }
        }
        Log.e("前台", "::::::::");
        return false;
    }
    private void sentNotification(ShujiaBookBean shujiaBookBean){
        NotificationManager notificationManager = (NotificationManager) MyApplication.getMyapplication().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent(MyApplication.getMyapplication(), MainActivity.class);
        contentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        MyApplication.getMyapplication(),
                        0,
                        contentIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyApplication.getMyapplication().getApplicationContext());
        Notification notification = builder.setTicker("你的书有更新了")
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(resultPendingIntent)
                .setContentTitle(shujiaBookBean.bookName+"有新更新了")
                .setContentText(shujiaBookBean.lastChapter)
                .setAutoCancel(true)
                .build();
        if(notificationManager != null && notification != null) {
            notificationManager.notify(1, notification);
        }
    }
}
