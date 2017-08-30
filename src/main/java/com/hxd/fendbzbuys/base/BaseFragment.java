package com.hxd.fendbzbuys.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxd.fendbzbuys.utils.UIUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lichao on 16/9/21.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements ActivityView{
    public T presenter;
    public View contentView;
    public LayoutInflater inflater;
    private Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate", ":::::BaseFragment::: " );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater=inflater;
        Log.e("onCreateView", ":::::BaseFragment::: " );
        contentView=setConentView();
        presenter= UIUtils.checkNotNull(creatPresenter());
        unbinder=ButterKnife.bind(this,contentView);
        return contentView;

    }
    public abstract View setConentView();
    public abstract T creatPresenter();

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        presenter.initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    @Override
    public Activity getXDActivity() {
        return this.getActivity();
    }
}
