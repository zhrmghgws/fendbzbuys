package com.hxd.fendbzbuys.network;

import android.app.Dialog;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.manager.DialogManager;


/**
 * Created by lichao on 16/9/23.
 */
public abstract class ProcressSubsciber<T> extends XDNetSubscriber<T> {
    private Dialog loadingDialog;
    private boolean showDialog;
    private boolean isCanCanle;
    /**
     *
     * @param isShow 是否显示请求网络dialog
     * @param isCanCanle 是否可以点击dismiss dialog
     *
     */
    public ProcressSubsciber(boolean isShow,boolean isCanCanle) {
        this.showDialog=isShow;
        this.isCanCanle=isCanCanle;
    }




    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }
    protected void dismissProgressDialog() {
        if (showDialog && loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    protected void showProgressDialog() {

        if (showDialog) {
            loadingDialog = DialogManager.createLoadingDialog(Constant.runActivity,isCanCanle);
            loadingDialog.show();
        }

    }
}
