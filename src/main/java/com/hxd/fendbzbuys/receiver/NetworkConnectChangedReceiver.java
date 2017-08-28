package com.hxd.fendbzbuys.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.base.OnSuccessCallback;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

/**
 * Created by lichao on 17/8/14.
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.isConnected()) {
                    onSuccessCallback.nextStep();
                    if(!TextUtils.isEmpty(Constant.sourceid) && Constant.muluList==null){
                        FBNetwork.getInstance().getBookmulu(Constant.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                            @Override
                            public void onNext(BookMuluInfo httpResult) {
                                super.onNext(httpResult);
                                Constant.muluList=httpResult.chapters;
                            }
                        });
                    }
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        Log.e("网络::::::", "当前移动网络连接可用 ");
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        Log.e("网络::::::", "当前移动网络连接可用 ");
                    }
                } else {
                    Log.e("网络::::::", "当前没有网络连接，请确保你已经打开网络 ");
                }



            } else {   // not connected to the internet
                Log.e("网络::::::", "当前没有网络连接aaaa，请确保你已经打开网络 ");

            }


        }
    }
    static OnSuccessCallback onSuccessCallback;
    public static void netWorkOnlineDosomeThing(OnSuccessCallback callback){
        onSuccessCallback=callback;
    }
}
