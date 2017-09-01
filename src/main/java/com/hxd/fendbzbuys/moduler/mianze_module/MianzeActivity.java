package com.hxd.fendbzbuys.moduler.mianze_module;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.moduler.shumulu_moduler.ShuMuluPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/7/31.
 */

public class MianzeActivity extends MVPBaseActivity<MianzePresenter> {
    @BindView(R.id.iv_back_mianze)
    ImageView iv_back_mianze;
    @BindView(R.id.tv_back_mianze)
    TextView tv_back_mianze;
    @BindView(R.id.tv_mianzeshengming)
    TextView tv_mianzeshengming;

    public static void invoke(Activity activity){
        activity.startActivity(new Intent(activity,MianzeActivity.class));
    }
    @Override
    public MianzePresenter creatPresenter() {
        return new MianzePresenter(this);
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
            case R.id.iv_back_mianze:
            case R.id.tv_back_mianze:
                finish();
                break;
        }
    }
    @Override
    public int getLayoutID() {
        return R.layout.mianze_activity;
    }
}
