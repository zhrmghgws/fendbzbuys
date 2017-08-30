package com.hxd.fendbzbuys.moduler.laon_moduler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.MainActivity;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lichao on 16/9/21.
 */
public class PaihangFragment extends BaseFragment<PaihangPresenter> {
    @BindView(R.id.lv_paihang)
    ListView lv_paihang;

    @BindView(R.id.rl_wanjiezhou_paihang)
    RelativeLayout rl_wanjiezhou_paihang;
    @BindView(R.id.view_wanjiezhou)
    View view_wanjiezhou;
    @BindView(R.id.tv_wanjiezhou)
    TextView tv_wanjiezhou;

    @BindView(R.id.rl_fengyunyue_paihang)
    RelativeLayout rl_fengyunyue_paihang;
    @BindView(R.id.view_fengyunyue)
    View view_fengyunyue;
    @BindView(R.id.tv_fengyunyue)
    TextView tv_fengyunyue;

    @BindView(R.id.rl_fengyunzong_paihang)
    RelativeLayout rl_fengyunzong_paihang;
    @BindView(R.id.view_fengyunzong)
    View view_fengyunzong;
    @BindView(R.id.tv_fengyunzong)
    TextView tv_fengyunzong;

    @BindView(R.id.rl_fengyunzhou_paihang)
    RelativeLayout rl_fengyunzhou_paihang;
    @BindView(R.id.view_fengyunzhou)
    View view_fengyunzhou;
    @BindView(R.id.tv_fengyunzhou)
    TextView tv_fengyunzhou;

    @BindView(R.id.rl_zonghengzong_paihang)
    RelativeLayout rl_zonghengzong_paihang;
    @BindView(R.id.view_zonghengzong)
    View view_zonghengzong;
    @BindView(R.id.tv_zonghengzong)
    TextView tv_zonghengzong;

    @BindView(R.id.rl_zonghengyue_paihang)
    RelativeLayout rl_zonghengyue_paihang;
    @BindView(R.id.view_zonghengyue)
    View view_zonghengyue;
    @BindView(R.id.tv_zonghengyue)
    TextView tv_zonghengyue;

    @BindView(R.id.rl_zonghengzhou_paihang)
    RelativeLayout rl_zonghengzhou_paihang;
    @BindView(R.id.view_zonghengzhou)
    View view_zonghengzhou;
    @BindView(R.id.tv_zonghengzhou)
    TextView tv_zonghengzhou;

    @BindView(R.id.rl_wanjieyue_paihang)
    RelativeLayout rl_wanjieyue_paihang;
    @BindView(R.id.view_wanjieyue)
    View view_wanjieyue;
    @BindView(R.id.tv_wanjieyue)
    TextView tv_wanjieyue;

    @BindView(R.id.rl_wanjiezong_paihang)
    RelativeLayout rl_wanjiezong_paihang;
    @BindView(R.id.view_wanjiezong)
    View view_wanjiezong;
    @BindView(R.id.tv_wanjiezong)
    TextView tv_wanjiezong;


    @BindView(R.id.tv_nan_paihang)
    TextView tv_nan_paihang;



    @BindView(R.id.tv_nv_paihang)
    TextView tv_nv_paihang;



    @OnClick({R.id.rl_wanjiezhou_paihang,R.id.rl_wanjieyue_paihang,R.id.rl_wanjiezong_paihang,
            R.id.rl_fengyunzong_paihang,
            R.id.rl_fengyunyue_paihang,
            R.id.rl_fengyunzhou_paihang,
            R.id.rl_zonghengzong_paihang,
            R.id.rl_zonghengyue_paihang,
            R.id.rl_zonghengzhou_paihang,
    })
    void onclick(View view){
        switch (view.getId()){
            case R.id.rl_wanjiezhou_paihang:
                presenter.wanjiezhou();
                break;
            case R.id.rl_wanjieyue_paihang:
                presenter.wanjieyue();
                break;
            case R.id.rl_wanjiezong_paihang:
                presenter.wanjiezong();
                break;
            case R.id.rl_fengyunzong_paihang:
                presenter.fengyunzong();
                break;
            case R.id.rl_fengyunyue_paihang:
                presenter.fengyunyue();
                break;
            case R.id.rl_fengyunzhou_paihang:
                presenter.fengyunzhou();
                break;
            case R.id.rl_zonghengzong_paihang:
                presenter.zonghengzong();
                break;
            case R.id.rl_zonghengyue_paihang:
                presenter.zonghengyue();
                break;
            case R.id.rl_zonghengzhou_paihang:
                presenter.zonghengzhou();
                break;
        }
    }
    @Override
    public View setConentView() {
        return inflater.inflate(R.layout.fragment_loan, null);
    }

    @Override
    public PaihangPresenter creatPresenter() {
        return new PaihangPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("排行::", ":::::::::onResume: " );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            presenter.man= Constant.isNan;
            presenter.nanOrnvChange();
        }
    }

    public  void nvPaihang() {
        presenter.man=false;
        presenter.nvpaihang();
    }

    public void nanPaihang() {
        presenter.man=true;
        presenter.nanpaihang();
    }
}
