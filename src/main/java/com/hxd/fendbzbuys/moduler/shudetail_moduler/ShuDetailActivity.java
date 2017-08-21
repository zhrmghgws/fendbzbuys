package com.hxd.fendbzbuys.moduler.shudetail_moduler;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;
import com.hxd.fendbzbuys.moduler.shumulu_moduler.ShuMuluActivity;
import com.hxd.fendbzbuys.ui.ListViewForScrollView;
import com.hxd.fendbzbuys.utils.UIUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/7/30.
 */

public class ShuDetailActivity extends MVPBaseActivity<ShuDetailPresenter> {
    @BindView(R.id.iv_back_detail)
    ImageView iv_back_detail;
    @BindView(R.id.tv_back_shudetail)
    TextView tv_back_shudetail;
    @BindView(R.id.iv_shuicon_shudetail)
    ImageView iv_shuicon_shudetail;
    @BindView(R.id.tv_shuname_shudetail)
    TextView tv_shuname_shudetail;
    @BindView(R.id.tv_shustyle_shudetail)
    TextView tv_shustyle_shudetail;
    @BindView(R.id.tv_shustyle2_shudetail)
    TextView tv_shustyle2_shudetail;
    @BindView(R.id.tv_auther_shudetail)
    TextView tv_auther_shudetail;
    @BindView(R.id.tv_updatatime_shudetail)
    TextView tv_updatatime_shudetail;
    @BindView(R.id.tv_shudetail2_shudetail)
    TextView tv_shudetail2_shudetail;
    @BindView(R.id.tv_zhankai_shudetail)
    TextView tv_zhankai_shudetail;
    @BindView(R.id.tv_lastzhangjie_shudetail)
    TextView tv_lastzhangjie_shudetail;
    @BindView(R.id.rl_neirongjieshao_shudetail)
    RelativeLayout rl_neirongjieshao_shudetail;
    @BindView(R.id.tv_shudetail_shudetail)
    TextView tv_shudetail_shudetail;
    @BindView(R.id.tv_auther_oter)
    TextView tv_auther_oter;
    @BindView(R.id.rl_chakanmulu_shudetail)
    RelativeLayout rl_chakanmulu_shudetail;
    @BindView(R.id.lv_authertuijian_shudetail)
    ListViewForScrollView lv_authertuijian_shudetail;
    @BindView(R.id.lv_booktuijian_shudetail)
    ListViewForScrollView lv_booktuijian_shudetail;
    @BindView(R.id.bt_xiazai_shudetail)
    RelativeLayout bt_xiazai_shudetail;
    @BindView(R.id.bt_lijiyuedu_shudetail)
    RelativeLayout bt_lijiyuedu_shudetail;
    @BindView(R.id.bt_jiarushujia_shudetail)
    RelativeLayout bt_jiarushujia_shudetail;
    @BindView(R.id.rl_auther_tuijian)
    RelativeLayout rl_auther_tuijian;
    @BindView(R.id.scrollview_shudetail)
    ScrollView scrollview_shudetail;
    public static BangdanBooksBean bookInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPermissions();
        //scrollview_shudetail.scrollTo(0, 0);
    }
    private void getPermissions(){
        new RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS).subscribe(granted->{
            if(granted){
                UIUtils.showToast("获取权限成功");
            }else{
                UIUtils.showToast("获取权限失败");
            }
        });
    }
    public static void invoke(Activity activity, BangdanBooksBean booksInfo){
        bookInfo=booksInfo;
        activity.startActivity(new Intent(activity,ShuDetailActivity.class));

    }
    @Override
    public ShuDetailPresenter creatPresenter() {
        return new ShuDetailPresenter(this);
    }

    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return false;
    }

    @Override
    public void init() {

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.rl_neirongjieshao_shudetail,
            R.id.rl_chakanmulu_shudetail,
            R.id.bt_jiarushujia_shudetail,
            R.id.bt_lijiyuedu_shudetail,
            R.id.bt_xiazai_shudetail,
            R.id.iv_back_detail,
            R.id.tv_back_shudetail
    })
    void onclick(View view){
        switch (view.getId()){
            case R.id.rl_neirongjieshao_shudetail:
                presenter.zhankai();
                break;
            case R.id.rl_chakanmulu_shudetail:
                if(!TextUtils.isEmpty(presenter.bookSourceID)){
                    ShuMuluActivity.invoke(this,presenter.bookSourceID);
                }else{
                    presenter.getShuSources();
                }

                break;
            case R.id.bt_jiarushujia_shudetail:
                presenter.clickJiarushujia(false);
                break;
            case R.id.bt_lijiyuedu_shudetail:
                presenter.clickLijiyuedu();
                break;
            case R.id.bt_xiazai_shudetail:
                presenter.clickXiazai();
                break;
            case R.id.iv_back_detail:
            case R.id.tv_back_shudetail:
                finish();
                break;
        }
    }
    @Override
    public int getLayoutID() {
        return R.layout.activity_shudetail;

    }

}
