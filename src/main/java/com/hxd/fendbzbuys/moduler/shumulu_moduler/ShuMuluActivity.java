package com.hxd.fendbzbuys.moduler.shumulu_moduler;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/7/31.
 */

public class ShuMuluActivity extends MVPBaseActivity<ShuMuluPresenter> {
    @BindView(R.id.iv_back_shumulu)
    ImageView iv_back_shumulu;
    @BindView(R.id.tv_back_shumulu)
    TextView tv_back_shumulu;
    @BindView(R.id.lv_mulu_shumulu)
    ListView lv_mulu_shumulu;
    @BindView(R.id.tv_daoxu_mulu)
    TextView tv_daoxu_mulu;
    public static String bookSourceId;
    public static void invoke(Activity activity,String bookID){
        bookSourceId=bookID;
        activity.startActivity(new Intent(activity,ShuMuluActivity.class));
    }
    @Override
    public ShuMuluPresenter creatPresenter() {
        return new ShuMuluPresenter(this);
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
    @OnClick({R.id.tv_back_shumulu,R.id.iv_back_shumulu,R.id.tv_daoxu_mulu})
    void click(View v){
        switch (v.getId()){
            case R.id.iv_back_shumulu:
            case R.id.tv_back_shumulu:
                finish();
                break;
            case R.id.tv_daoxu_mulu:
                presenter.daoxu();
                break;
        }
    }
    @Override
    public int getLayoutID() {
        return R.layout.shumulu_activity;
    }
}
