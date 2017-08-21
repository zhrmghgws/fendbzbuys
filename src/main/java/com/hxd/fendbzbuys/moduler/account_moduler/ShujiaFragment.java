package com.hxd.fendbzbuys.moduler.account_moduler;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BaseFragment;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;

import butterknife.BindView;

/**
 * Created by lichao on 16/9/21.
 */
public class ShujiaFragment extends BaseFragment<ShujiaPresenter> {

    @BindView(R.id.lv_shujia_main)
    ListView lv_shujia_main;
    @BindView(R.id.rl_shujiakong_main)
    RelativeLayout rl_shujiakong_main;
    @BindView(R.id.rl_lishizujikong_main)
    RelativeLayout rl_lishizujikong_main;
    @BindView(R.id.lv_lishizuji_main)
    ListView lv_lishizuji_main;
    @BindView(R.id.tv_shujiahint_fragment)
    TextView tv_shujiahint_fragment;


    @Override
    public View setConentView() {
        View view = inflater.inflate(R.layout.fragment_account, null);
        return view;
    }

    @Override
    public ShujiaPresenter creatPresenter() {
        return new ShujiaPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", ":::::::::: onResume" );
        presenter.resume();
    }
}
