package com.hxd.fendbzbuys.moduler.fenlei_moduler;

import android.view.View;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 16/9/21.
 */
public class FenleiFragment extends BaseFragment<FenleiPresenter> {

    @BindView(R.id.tv_shopphonthint_account)
    TextView tv_shopphonthint_account;


    @Override
    public View setConentView() {
        View view = inflater.inflate(R.layout.fenlei_fragment, null);
        return view;
    }

    @Override
    public FenleiPresenter creatPresenter() {
        return new FenleiPresenter(this);
    }
    @OnClick(R.id.tv_shopphonthint_account)void settingOnclick(){
        presenter.settingClick();
    }


}
