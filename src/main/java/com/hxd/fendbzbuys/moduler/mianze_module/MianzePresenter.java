package com.hxd.fendbzbuys.moduler.mianze_module;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.moduler.shumulu_moduler.ShuMuluActivity;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

import java.util.List;

/**
 * Created by lichao on 17/7/31.
 */

public class MianzePresenter extends BasePresenter<MianzeActivity> {
    public MianzePresenter(MianzeActivity view) {
        super(view);
    }

    @Override
    public void initData() {
    }



}
