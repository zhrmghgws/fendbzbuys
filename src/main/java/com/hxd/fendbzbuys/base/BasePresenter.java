package com.hxd.fendbzbuys.base;

import android.content.Intent;

import com.hxd.fendbzbuys.R;


/**
 * Created by lichao on 16/9/20.
 */
public abstract class BasePresenter<T extends ActivityView> {
    public T view;
    public T getActivityView(){return view;}
    public MVPBaseActivity getActivity(){return (MVPBaseActivity) view.getXDActivity();}
    public BasePresenter(T view){
        this.view=view;
    }
    public abstract void initData();
    public void finish(){
        view.getXDActivity().finish();
        view.getXDActivity().overridePendingTransition(0, R.anim.back_right_out);
    }

    public void startActivity(Intent intent){
        view.getXDActivity().startActivity(intent);
        view.getXDActivity().  overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);

    }
    public void startActivityForResult(Intent intent,int requestCode){
        view.getXDActivity().startActivityForResult(intent, requestCode);
        view.getXDActivity().  overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);

    }

    public void setResult(int resultCode,Intent intent){
        view.getXDActivity().setResult(resultCode, intent);
        view.getXDActivity().  overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }
    public void destory(){
        view=null;
    }

}
