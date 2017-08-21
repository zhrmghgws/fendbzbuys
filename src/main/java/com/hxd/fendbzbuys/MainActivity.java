package com.hxd.fendbzbuys;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hxd.fendbzbuys.moduler.laon_moduler.PaihangFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

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
    //   registerMessageReceiver();

    }
    @OnClick(R.id.rl_xiazai_main) void xiazai_click(){
        presenter.downLoadMain();
    }
    @OnClick(R.id.rl_sousuo_main) void sousuo_click(){

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
            tv_nv_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
           // tv_nan_titlebar.setTextColor(getResources().getColor(R.color.color_666666));
           // tv_nv_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
            if(presenter.currentPage==1){
                presenter.paihangFragment.nvPaihang();
            }else if(presenter.currentPage==2){

            }
        }else{
            isNan=true;
            tv_nan_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
            tv_nv_titlebar.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
          //  tv_nan_titlebar.setTextColor(getResources().getColor(R.color.hometextcolor_hover_light));
           // tv_nv_titlebar.setTextColor(getResources().getColor(R.color.color_666666));
            if(presenter.currentPage==1){
                presenter.paihangFragment.nanPaihang();
            }else if(presenter.currentPage==2){

            }
        }
    }
}
