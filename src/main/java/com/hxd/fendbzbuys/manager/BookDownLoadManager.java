package com.hxd.fendbzbuys.manager;

import android.util.Log;
import android.view.View;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.DownLoadCallBack;
import com.hxd.fendbzbuys.domain.BookContentInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.moduler.read_moduler.ReadPresenter;
import com.hxd.fendbzbuys.network.Network;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.utils.NetworkUtils;
import com.hxd.fendbzbuys.utils.UIUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichao on 17/8/20.
 */

public class BookDownLoadManager {
    private  int bookPathid;
    private DownLoadCallBack callBack;
    private   List<BookMuluInfo.ChaptersEntity> muluList;
    private String bookId;
    private int tatleCount;
    public BookDownLoadManager(int bookPathid ,DownLoadCallBack callBack,List<BookMuluInfo.ChaptersEntity> muluList,String bookId){
        this.bookPathid=bookPathid;
        this.callBack=callBack;
        this.muluList=muluList;
        this.bookId=bookId;
    }
    int downLoadState = 0;
    int downLoadTatle;
    int downPoint = 0;
    List<Integer> downList = new ArrayList<>();
    private List<Integer> downSuccess = new ArrayList<>();
    private List<Integer> downError = new ArrayList<>();
    public void downLoad() {
        tatleCount = (int) BookPathBeanDaoManager.getDuiyingTitleCount(bookPathid);
        downLoadTatle = (int) (muluList.size() - tatleCount);
        if (downLoadTatle == 0) {
            UIUtils.showToast("老铁,你已经缓存过了!");
        } else {
            if(NetworkUtils.checkNetWorkType()==0){
                UIUtils.showToast("请检查你的网络连接");
            }else{
                downSuccess.clear();
                downError.clear();
                downList.clear();
                downPoint = 0;
                callBack.start((int) tatleCount);
                for (int i = 0; i < muluList.size(); i++) {
                    if (BookPathBeanDaoManager.checkTenData(bookPathid, i)) {
                        Log.e("要下载的", ":::: " + i);
                        downList.add(i);
                    }
                }
                //view.rl_download_hint.setVisibility(View.VISIBLE);
                for(int i=0;i<downList.size();i++){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getContentAndDownLoad(downList.get(i) );
                }
            }
        }
    }
    private boolean errorFlag=true;//当网络错误时,第一个才调用callback.error
    /*
        int j :章节
        boolean error:是否是重新下载错误的章节
     */
    private void getContentAndDownLoad(int j) {
        Network.getNewInstance().getContent(muluList.get(j).link).subscribe(new ProcressSubsciber<BookContentInfo>(false, false) {
            @Override
            public void onNext(BookContentInfo bookContentInfo) {
                super.onNext(bookContentInfo);

                    downSuccess.add(j);
                    if (downSuccess.size() + downError.size() == downLoadTatle) {
                        if (downError.size() == 0) {

                            callBack.finish();
                        } else {
                            callBack.finishWithError(downSuccess.size(),downError.size());
                        }
                    } else {
                      callBack.update(downSuccess.size()+(int) tatleCount);

                    }
                    BookPathBeanDaoManager.getduiyingBookPathBeanDao(bookPathid, j, bookContentInfo,bookId,muluList);
            }

            @Override
            public void onError(Throwable e) {
                 //super.onError(e);

                    downError.add(j);
                if (downSuccess.size() + downError.size() == downLoadTatle) {
                    callBack.finishWithError(downSuccess.size(),downError.size());
                }

            }
        });
    }
}
