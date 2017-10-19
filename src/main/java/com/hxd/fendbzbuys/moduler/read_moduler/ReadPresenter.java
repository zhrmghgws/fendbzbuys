package com.hxd.fendbzbuys.moduler.read_moduler;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.base.DownLoadCallBack;
import com.hxd.fendbzbuys.base.MyCallBack;
import com.hxd.fendbzbuys.domain.BookContentInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.BookPathBean;
import com.hxd.fendbzbuys.domain.ShuSourceInfo;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.manager.BookDownLoadManager;
import com.hxd.fendbzbuys.manager.BookPathBeanDaoManager;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.manager.DialogManager;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.utils.NetworkUtils;
import com.hxd.fendbzbuys.utils.UIUtils;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.hxd.fendbzbuys.utils.UIUtils.runOnUiThread;

/**
 * Created by lichao on 17/8/1.
 */

public class ReadPresenter extends BasePresenter<ReadActivity> {
    public ReadPresenter(ReadActivity view) {
        super(view);
    }
    private int currentCount;
    ShujiaBookBean shujiaBookBean;
    private void getConentPre() {
        if (Constant.muluList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("请先打开你的网络连接");
            } else {
                FBNetwork.getInstance().getBookmulu(view.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                    @Override
                    public void onNext(BookMuluInfo httpResult) {
                        super.onNext(httpResult);
                        Constant.muluList = httpResult.chapters;
                        Log.e("muluList.size", "::::::: "+Constant.muluList.size() );
                        getContent(currentCount, false);
                    }
                });
            }
        } else {
            getContent(currentCount, false);
        }
    }





    private void setData(int i) {
        Log.e("bookpathbean", "::::setData:::: "+i );
        switch (i) {
            case 0:
                if (BookPathBeanDaoManager.bookPathBeanDao.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao.load(Long.valueOf(currentCount)).title);
                }
                break;
            case 1:
                if (BookPathBeanDaoManager.bookPathBeanDao1.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao1.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao1.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 2:
                if (BookPathBeanDaoManager.bookPathBeanDao2.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao2.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao2.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 3:
                if (BookPathBeanDaoManager.bookPathBeanDao3.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao3.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao3.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 4:
                if (BookPathBeanDaoManager.bookPathBeanDao4.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao4.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao4.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 5:
                if (BookPathBeanDaoManager.bookPathBeanDao5.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao5.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao5.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 6:
                if (BookPathBeanDaoManager.bookPathBeanDao6.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao6.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao6.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 7:
                if (BookPathBeanDaoManager.bookPathBeanDao7.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao7.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao7.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 8:
                if (BookPathBeanDaoManager.bookPathBeanDao8.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao8.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao8.load(Long.valueOf(currentCount)).title);
                }

                break;
            case 9:
                if (BookPathBeanDaoManager.bookPathBeanDao9.load(Long.valueOf(currentCount)) == null) {
                    getConentPre();
                } else {
                    view.tv_read.setText(dealContent(BookPathBeanDaoManager.bookPathBeanDao9.load(Long.valueOf(currentCount)).content));
                    view.tv_title_read.setText(BookPathBeanDaoManager.bookPathBeanDao9.load(Long.valueOf(currentCount)).title);
                }

                break;

        }
    }

    @Override
    public void initData() {

        shujiaBookBean = DaoManager.getInstance().getShujiaBookBeanDao().load(ReadPresenter.this.view.bookid);
        if (NetworkUtils.checkNetWorkType() == 0) {
            currentCount = Integer.parseInt(shujiaBookBean.getCurrentZhangjie());
            setData(view.bookPathid);
        } else {
            getShumulu();
            Log.e("initdata", ":::::::getShumulu::::: " );
        }
    }

    private void getShumulu() {

        FBNetwork.getInstance().getBookmulu(view.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
            @Override
            public void onNext(BookMuluInfo httpResult) {
                super.onNext(httpResult);
                Constant.muluList = httpResult.chapters;
                Log.e("muluList.size", "::::::: "+Constant.muluList.size() );
                if (view.bookPathid == 0) {
                    getContents(0, 20);
                } else {
                    if(TextUtils.isEmpty(shujiaBookBean.currentZhangjie)){
                        currentCount=0;
                    }else{
                        currentCount = Integer.parseInt(shujiaBookBean.currentZhangjie);
                    }
                    Log.e("getShumulu", ":::::::setData::::: "+view.bookPathid );
                    setData(view.bookPathid);
                }
            }
        });


    }

    private void getContents(int start, int end) {
        for (int j = start; j < end; j++) {
            if (j <= Constant.muluList.size() - 1) {
                getContent(j, true);
            }
        }


    }

    private void getContent(int j, boolean nomal) {
        FBNetwork.getNewInstance().getContent(Constant.muluList.get(j).link).subscribe(new ProcressSubsciber<BookContentInfo>(false, false) {
            @Override
            public void onNext(BookContentInfo bookContentInfo) {
                super.onNext(bookContentInfo);
                // bookContexts.add(j,new BookAllZhangjie(Constant.muluList.get(j).title,bookContentInfo.chapter.body));
                BookPathBeanDaoManager.getduiyingBookPathBeanDao(ReadPresenter.this.view.bookPathid, j, bookContentInfo,ReadPresenter.this.view.bookid,Constant.muluList);
                //当setdata某一章为空时,nomal=false,单独请求的,需要设置展示数据
                if(!view.isFinishing()&&view!=null){
                    if (!nomal) {
                        view.tv_read.setText(dealContent(bookContentInfo.chapter.body));
                        view.tv_title_read.setText(Constant.muluList.get(j).title);
                    }
                    if (j == 0 && currentCount == 0) {
                        view.tv_read.setText(dealContent(bookContentInfo.chapter.body));
                        view.tv_title_read.setText(Constant.muluList.get(0).title);
                    }
                }
            }
        });
    }

    private List<Integer> downSuccess = new ArrayList<>();
    private List<Integer> downError = new ArrayList<>();

    /*
        int j :章节
        boolean error:是否是重新下载错误的章节
     */
    private void getContentAndDownLoad(int j, boolean error) {
        FBNetwork.getNewInstance().getContent(Constant.muluList.get(j).link).subscribe(new ProcressSubsciber<BookContentInfo>(false, false) {
            @Override
            public void onNext(BookContentInfo bookContentInfo) {
                super.onNext(bookContentInfo);
                // bookContexts.add(j,new BookAllZhangjie(Constant.muluList.get(j).title,bookContentInfo.chapter.body));
                //downLoadState++;
                if (ReadPresenter.this.view.isFinishing()) {
                    Log.e("activity::",  "::::::::已经关闭了::::");
                } else {
                    downSuccess.add(j);
                    if (error && downError.size() > 0) {
                        downError.remove(0);
                    }
                    Log.e("downLoadState::", downLoadState + ":::::downLoadTatle:::::::" + downLoadTatle);
                    if (downSuccess.size() + downError.size() == downLoadTatle) {
                        if (downError.size() == 0) {
                            ReadPresenter.this.view.tv_download_hint.setText("全部缓存完毕!");
                            goneDownLoadHint();
                        } else {
                            if (!error) {
                                ReadPresenter.this.view.tv_download_hint.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_e73b47));
                                ReadPresenter.this.view.tv_download_hint.setText("缓存完毕,有" + downError.size() + "章下载失败,点击重新下载该章节");
                                ReadPresenter.this.view.rl_download_hint.setClickable(true);
                                ReadPresenter.this.view.rl_download_hint.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        getContentAndDownLoad(downError.get(0), true);
                                        ReadPresenter.this.view.rl_download_hint.setClickable(false);
                                        ReadPresenter.this.view.tv_download_hint.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_dadada));
                                    }
                                });
                            } else {
                                ReadPresenter.this.view.tv_download_hint.setText("正在缓存:" + Constant.muluList.get(j).title + "(" + j + "/" + Constant.muluList.size() + ")...");
                                if (downError.size() > 0) {
                                    getContentAndDownLoad(downError.get(0), true);
                                }
                            }


                        }
                    } else {
                        if ((!error)) {
                            ReadPresenter.this.view.tv_download_hint.setText("正在缓存:" + Constant.muluList.get(j).title + "(" + j + "/" + Constant.muluList.size() + ")...");
                            downPoint = downPoint + 1;
                            if (downPoint < downLoadTatle) {
                                getContentAndDownLoad(downList.get(downPoint), false);
                            }
                        } else {
                            if (downSuccess.size() == downLoadTatle) {
                                ReadPresenter.this.view.tv_download_hint.setText("全部缓存完毕!");
                                goneDownLoadHint();
                                downError.clear();
                            } else {

                            }

                        }
                        ReadPresenter.this.view.tv_download_hint.setText("正在缓存:" + Constant.muluList.get(j).title + "(" + j + "/" + Constant.muluList.size() + ")...");
                    }
                    BookPathBeanDaoManager.getduiyingBookPathBeanDao(ReadPresenter.this.view.bookPathid, j, bookContentInfo,ReadPresenter.this.view.bookid,Constant.muluList);
                }
            }

            @Override
            public void onError(Throwable e) {
                // super.onError(e);
                if (e instanceof UnknownHostException || e instanceof ConnectException) {
                    Constant.runActivity.otherPophint("请检查您的网络连接");
                    if(!ReadPresenter.this.view.isFinishing()){
                        ReadPresenter.this.view.tv_download_hint.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_e73b47));
                        ReadPresenter.this.view.tv_download_hint.setText("第" + (j + 1) + "章下载失败,请确认你的网络连接正常,然后点击重试");
                        ReadPresenter.this.view.rl_download_hint.setClickable(true);
                        ReadPresenter.this.view.rl_download_hint.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ReadPresenter.this.view.tv_download_hint.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_dadada));
                                getContentAndDownLoad(j, false);
                                ReadPresenter.this.view.rl_download_hint.setClickable(false);
                            }
                        });
                    }
                } else if (e instanceof SocketTimeoutException) {
                    downError.add(j);
                    int i = j + 1;
                    getContentAndDownLoad(i, false);
                }

            }
        });
    }

    private int flag = 4;

    private void goneDownLoadHint() {
        Timer mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (flag > 0) {
                            flag--;
                        } else {
                            mTimer.cancel();
                            ReadPresenter.this.view.rl_download_hint.setVisibility(View.GONE);
                        }

                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 1000);
    }
    private void goneDownLoadHintNew() {
        Timer mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (flag > 0) {
                            flag--;
                        } else {
                            mTimer.cancel();
                            if(ReadPresenter.this!=null){
                                ReadPresenter.this.view.rl_download_hint_new.setVisibility(View.GONE);
                            }
                        }

                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 1000);
    }
    public void nextZhangPre() {
        if (Constant.muluList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                if(shujiaBookBean.bookTotakCount-1>currentCount+1){
                    nextZhang(true);
                }else{
                    UIUtils.showToast("当前缓存章节已经读完,请先打开你的网络连接");
                }
            } else {
                FBNetwork.getInstance().getBookmulu(view.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                    @Override
                    public void onNext(BookMuluInfo httpResult) {
                        super.onNext(httpResult);
                        Constant.muluList = httpResult.chapters;
                        nextZhang(false);
                    }
                });
            }
        } else {
            nextZhang(false);
        }
    }

    public void nextZhang(boolean isHasMulu) {
        if(!isHasMulu){
            if (currentCount + 1 > Constant.muluList.size() - 1) {
                UIUtils.showToast("已经最后一章了");
                return;
            }
        }else{
            if (currentCount + 1 > shujiaBookBean.bookTotakCount - 1) {
                UIUtils.showToast("已经最后一章了");
                return;
            }
        }

        currentCount = currentCount + 1;
        animation(view.tv_read);
        view.scrollview_read.scrollTo(0, 0);
        setData(view.bookPathid);
        if (currentCount % 10 == 0 && BookPathBeanDaoManager.checkTenData(view.bookPathid, currentCount + 10)) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("预加载失败,请检查你的网络连接");
            } else {
                getContents(currentCount + 10, currentCount + 20);
            }
        }
        if (view.bookPathid != 0) {
            shujiaBookBean.currentZhangjie = currentCount + "";
            shujiaBookBean.manyDownload= (int) BookPathBeanDaoManager.getDuiyingTitleCount(view.bookPathid);
            DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
        }

    }

    private String dealContent(String content) {
        String newStr = content.replace("\n", "\n      ");
        return newStr;
    }

    private void animation(View v) {
        /*TranslateAnimation animation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1.0f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setRepeatMode(Animation.REVERSE);*/
        Animation animation = new AlphaAnimation(1.0f, 0);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(1);
        animation.setDuration(100);
        v.startAnimation(animation);
    }

    public void preZhang() {
        animation(view.tv_read);
        if (currentCount <= 0) {
            currentCount = 0;
            UIUtils.showToast("已经是第一章了");
        } else {
            view.scrollview_read.scrollTo(0, 0);
            currentCount = currentCount - 1;
            setData(view.bookPathid);
            if (view.bookPathid != 0) {
                shujiaBookBean.currentZhangjie = currentCount + "";
                shujiaBookBean.manyDownload= (int) BookPathBeanDaoManager.getDuiyingTitleCount(view.bookPathid);
                DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
            }
        }

    }

    public boolean savaData() {
        int bookpathid = DaoManager.getInstance().getKongXianBookPathBeanDao();
        if (bookpathid == 20) {
            DialogManager.createYiChuShujiaDialog(ReadPresenter.this.view, true, false,null);
            return false;
        }else{
            shujiaBookBean.currentZhangjie = currentCount + "";
            shujiaBookBean.isZhudong = true;
            shujiaBookBean.jiaruDate=System.currentTimeMillis();
            shujiaBookBean.bookpathBean = bookpathid;
            List<BookPathBean> beanlist = BookPathBeanDaoManager.bookPathBeanDao.loadAll();
            shujiaBookBean.manyDownload=beanlist.size();
            shujiaBookBean.bookTotakCount=Constant.muluList.size();
            DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
            Log.e("bookpathid", ":::::::" + bookpathid);
            BookPathBeanDaoManager.saveduiyingBookPathBeanDao(bookpathid, beanlist);
            return true;
        }
    }
    private  void startAnimationDown( View v){
        /*DisplayMetrics dm =activity.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        int i=h_screen-UIUtils.dip2px(50)- ViewUtils.getStatusBarHeight(activity);
        Log.e("动画时长", ":::::::: "+ i);
        ValueAnimator va=ValueAnimator.ofInt(1,i);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int widths = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams ll = v.getLayoutParams();
                ll.height=widths;

                v.setLayoutParams(ll);
            }
        });
        va.setDuration(300);
        va.start();*/
        Animation myAnimation_Scale =new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        myAnimation_Scale.setDuration(500);
        //动画效果从XMl文件中定义
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.setAnimation(myAnimation_Scale);
    }
    private  void startAnimationChangeSource( View v){
        /*DisplayMetrics dm =activity.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        int i=h_screen-UIUtils.dip2px(50)- ViewUtils.getStatusBarHeight(activity);
        Log.e("动画时长", ":::::::: "+ i);
        ValueAnimator va=ValueAnimator.ofInt(1,i);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int widths = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams ll = v.getLayoutParams();
                ll.height=widths;

                v.setLayoutParams(ll);
            }
        });
        va.setDuration(300);
        va.start();*/
        Animation myAnimation_Scale =new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1f);
        myAnimation_Scale.setDuration(500);
        //动画效果从XMl文件中定义
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.setAnimation(myAnimation_Scale);
    }
    public void showDialog() {
        if (downLoadManager!=null) {
            UIUtils.showToast("请稍后在退出,正在缓存中...");
        }
        else{
            final Dialog authorestampdialog = new Dialog(view,
                    R.style.TranslucentBackground);
            authorestampdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View authorestampview = UIUtils.inflate(
                    R.layout.dialog_jiarushujia);
            TextView tv_no_jiarushujia = (TextView) authorestampview.findViewById(R.id.tv_no_jiarushujia);
            TextView tv_ok_jiarushujia = (TextView) authorestampview.findViewById(R.id.tv_ok_jiarushujia);

            tv_no_jiarushujia.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View view) {
                                                         authorestampdialog.dismiss();
                                                         BookPathBeanDaoManager.bookPathBeanDao.deleteAll();
                                                         saveFinish();
                                                     }
                                                 }

            );
            tv_ok_jiarushujia.setOnClickListener(v -> {
                        if(savaData()){
                            UIUtils.showToast("加入成功");
                            BookPathBeanDaoManager.bookPathBeanDao.deleteAll();
                            authorestampdialog.dismiss();
                            ReadPresenter.this.view.finish();
                        }
                    }
            );
            authorestampdialog.setContentView(authorestampview);

            authorestampdialog.show();
        }

    }

    public void saveFinish() {
        //int bookpathid = DaoManager.getInstance().getKongXianBookPathBeanDao();
        if (view.bookPathid != 0) {
            shujiaBookBean.currentZhangjie = currentCount + "";
            shujiaBookBean.manyDownload= (int) BookPathBeanDaoManager.getDuiyingTitleCount(view.bookPathid);
            DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
        }
        view.finish();
    }

    private int bgstate = Constant.sharedPreferences.getInt("bgstate",14);

    public void switchBackground(int state) {
        switch (state) {
            case 1:
                setGonebg();
                //view.rl_content_read.setBackgroundColor(view.getResources().getColor(R.color.read_theme_gray));
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj1);
                view.iv_textbj1_read.setVisibility(View.VISIBLE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.black));
                bgstate = 1;
                Constant.sharedPreferences.edit().putInt("bgstate",1).commit();
                break;
            case 2:
                setGonebg();
                //view.rl_content_read.setBackgroundColor(view.getResources().getColor(R.color.read_theme_white));
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj2);
                view.iv_textbj2_read.setVisibility(View.VISIBLE);
                bgstate = 2;
                Constant.sharedPreferences.edit().putInt("bgstate",2).commit();
                break;
            case 3:
                setGonebg();
                // view.rl_content_read.setBackgroundColor(view.getResources().getColor(R.color.read_theme_yellow));
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj3);
                view.iv_textbj3_read.setVisibility(View.VISIBLE);
                bgstate = 3;
                Constant.sharedPreferences.edit().putInt("bgstate",3).commit();
                break;
            case 4:
                setGonebg();
                //view.rl_content_read.setBackgroundColor(view.getResources().getColor(R.color.read_theme_green));
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj4peg);
                view.iv_textbj4_read.setVisibility(View.VISIBLE);
                bgstate = 4;
                Constant.sharedPreferences.edit().putInt("bgstate",4).commit();
                break;
            case 5:
                setGonebg();
                //view.rl_content_read.setBackgroundColor(view.getResources().getColor(R.color.read_theme_night));
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj5);
                view.iv_textbj5_read.setVisibility(View.VISIBLE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.color_d9a564));
                bgstate = 5;
                Constant.sharedPreferences.edit().putInt("bgstate",5).commit();
                break;
            case 6:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj6);
                view.iv_textbj6_read.setVisibility(View.VISIBLE);
                bgstate = 6;
                Constant.sharedPreferences.edit().putInt("bgstate",6).commit();
                break;
            case 7:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj7);
                view.iv_textbj7_read.setVisibility(View.VISIBLE);
                bgstate = 7;
                Constant.sharedPreferences.edit().putInt("bgstate",7).commit();
                break;
            case 8:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj8);
                view.iv_textbj8_read.setVisibility(View.VISIBLE);
                bgstate = 8;
                Constant.sharedPreferences.edit().putInt("bgstate",8).commit();
                break;
            case 9:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj9);
                view.iv_textbj9_read.setVisibility(View.VISIBLE);
                bgstate = 9;
                Constant.sharedPreferences.edit().putInt("bgstate",9).commit();
                break;
            case 10:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj10);
                view.iv_textbj10_read.setVisibility(View.VISIBLE);
                bgstate = 10;
                Constant.sharedPreferences.edit().putInt("bgstate",10).commit();
                break;
            case 11:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj11);
                view.iv_textbj11_read.setVisibility(View.VISIBLE);
                bgstate = 11;
                Constant.sharedPreferences.edit().putInt("bgstate",11).commit();
                break;
            case 12:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj12);
                view.iv_textbj12_read.setVisibility(View.VISIBLE);
                bgstate = 12;
                Constant.sharedPreferences.edit().putInt("bgstate",12).commit();
                break;
            case 13:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj13);
                view.iv_textbj13_read.setVisibility(View.VISIBLE);
                bgstate = 13;
                Constant.sharedPreferences.edit().putInt("bgstate",13).commit();
                break;
            case 14:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj14);
                view.iv_textbj14_read.setVisibility(View.VISIBLE);
                bgstate = 14;
                Constant.sharedPreferences.edit().putInt("bgstate",14).commit();
                break;
            case 15:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj15);
                view.iv_textbj15_read.setVisibility(View.VISIBLE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.color_b6cd9d));
                bgstate = 15;
                Constant.sharedPreferences.edit().putInt("bgstate",15).commit();
                break;
            case 16:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj16);
                view.iv_textbj16_read.setVisibility(View.VISIBLE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.light_coffee));
                bgstate = 16;
                Constant.sharedPreferences.edit().putInt("bgstate",16).commit();
                break;
            case 17:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj17);
                view.iv_textbj17_read.setVisibility(View.VISIBLE);
                bgstate = 17;
                Constant.sharedPreferences.edit().putInt("bgstate",17).commit();
                break;
            case 20:
                setGonebg();
                //view.rl_content_read.setBackgroundResource(R.mipmap.theme_leather_bg);
                view.rl_content_read.setBackgroundResource(R.mipmap.shujia_bj20);
                view.iv_textbj20_read.setVisibility(View.VISIBLE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.light_coffee));
                bgstate = 20;
                Constant.sharedPreferences.edit().putInt("bgstate",20).commit();
                break;
        }
    }

    private void setGonebg() {
        switch (bgstate) {
            case 1:
                view.iv_textbj1_read.setVisibility(View.GONE);
                break;
            case 2:
                view.iv_textbj2_read.setVisibility(View.GONE);
                break;
            case 3:
                view.iv_textbj3_read.setVisibility(View.GONE);
                break;
            case 4:
                view.iv_textbj4_read.setVisibility(View.GONE);
                break;
            case 5:
                view.iv_textbj5_read.setVisibility(View.GONE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.black));
                break;
            case 6:
                view.iv_textbj6_read.setVisibility(View.GONE);
                break;
            case 7:
                view.iv_textbj7_read.setVisibility(View.GONE);
                break;
            case 8:
                view.iv_textbj8_read.setVisibility(View.GONE);
                break;
            case 9:
                view.iv_textbj9_read.setVisibility(View.GONE);
                break;
            case 10:
                view.iv_textbj10_read.setVisibility(View.GONE);
                break;
            case 11:
                view.iv_textbj11_read.setVisibility(View.GONE);
                break;
            case 12:
                view.iv_textbj12_read.setVisibility(View.GONE);
                break;
            case 13:
                view.iv_textbj13_read.setVisibility(View.GONE);
                break;
            case 14:
                view.iv_textbj14_read.setVisibility(View.GONE);
                break;
            case 15:
                view.iv_textbj15_read.setVisibility(View.GONE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.black));
                break;
            case 16:
                view.iv_textbj16_read.setVisibility(View.GONE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.black));
                break;
            case 17:
                view.iv_textbj17_read.setVisibility(View.GONE);
                break;
            case 20:
                view.iv_textbj20_read.setVisibility(View.GONE);
                view.tv_read.setTextColor(view.getResources().getColor(R.color.black));
                break;
        }
    }

    int downLoadState = 0;
    int downLoadTatle;
    int downPoint = 0;
    List<Integer> downList = new ArrayList<>();

    public void downLoad() {
        long tatleCount = BookPathBeanDaoManager.getDuiyingTitleCount(view.bookPathid);
        downLoadTatle = (int) (Constant.muluList.size() - tatleCount);
        if (downLoadTatle == 0) {
            UIUtils.showToast("老铁,你已经缓存过了!");
        } else {
            if (downPoint != 0 && downList.size()>0&&downPoint < downLoadTatle-1) {
                UIUtils.showToast("正在缓存中");
            } else {
                downSuccess.clear();
                downError.clear();
                downList.clear();
                downPoint = 0;
                for (int i = 0; i < Constant.muluList.size(); i++) {
                    if (BookPathBeanDaoManager.checkTenData(view.bookPathid, i)) {
                        Log.e("要下载的", ":::: " + i);
                        downList.add(i);
                    }
                }
                view.rl_download_hint.setVisibility(View.VISIBLE);
                getContentAndDownLoad(downList.get(downPoint), false);
            }
        }
    }
    private BookDownLoadManager downLoadManager;
    public void downLoadNew(){
        if(downLoadManager!=null){
            UIUtils.showToast("正在缓存中...");
        }
        downLoadManager=new BookDownLoadManager(view.bookPathid, new DownLoadCallBack() {
            @Override
            public void start(int tatleCount) {
                if(ReadPresenter.this!=null&&!ReadPresenter.this.view.isFinishing()){

                    BigDecimal value=new BigDecimal(tatleCount).divide(new BigDecimal(Constant.muluList.size()), 2, BigDecimal.ROUND_UP);
                    int widthNew=new BigDecimal(UIUtils.dip2px(200)).multiply(value).intValue();
                    int shuzhi= (int) (value.floatValue()*100);
                    Log.e("tatlecount", ":::start::::: "+tatleCount );
                    Log.e("tatlecount", ":::start:::::: "+Constant.muluList.size() );
                    Log.e("value", "::::start:::::: "+value);
                    Log.e("width", "::::start:::::: "+widthNew);
                    Log.e("shuzhi", "::::start::::::: "+shuzhi);
                    ReadPresenter.this.view.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ReadPresenter.this.view.rl_download_hint_new.setVisibility(View.VISIBLE);
                            ReadPresenter.this.view.rl_download_content_new.setVisibility(View.VISIBLE);
                            ReadPresenter.this.view.tv_download_error_new.setVisibility(View.GONE);
                            view.setViewWidth(widthNew,shuzhi);
                        }
                    });
                }

            }

            @Override
            public void update(int sucess) {
                if(ReadPresenter.this!=null&&!ReadPresenter.this.view.isFinishing()){
                    if(sucess>100&&sucess%100==0){
                        BigDecimal value=new BigDecimal(sucess).divide(new BigDecimal(Constant.muluList.size()), 2, BigDecimal.ROUND_UP);
                        int widthNew=new BigDecimal(UIUtils.dip2px(200)).multiply(value).intValue();
                        int shuzhi= (int) (value.floatValue()*100);
                        Log.e("dip2px(200)", "::::: "+UIUtils.dip2px(200) );
                        Log.e("bizhi", "::::: "+value );
                        Log.e("sucess", "::::: "+sucess );
                        Log.e("数值", "::::: "+shuzhi);
                        Log.e("width", "::::: "+widthNew);
                        ReadPresenter.this.view.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.setViewWidth(widthNew,shuzhi);
                            }
                        });
                    }
                }

            }

            @Override
            public void finish() {
                if(ReadPresenter.this!=null&&!ReadPresenter.this.view.isFinishing()){

                    goneDownLoadHintNew();
                    downLoadManager=null;
                    ReadPresenter.this.view.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ReadPresenter.this.view.tv_download_hint_new.setText("缓存完毕");
                            RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) ReadPresenter.this.view.view_download_jindu_new.getLayoutParams();
                            lp.width=UIUtils.dip2px(200);
                            ReadPresenter.this.view.view_download_jindu_new.setLayoutParams(lp);
                            ReadPresenter.this.view.tv_download_jindu_new.setText("100%");
                        }
                    });
                }else{
                    UIUtils.showToast("缓存完毕!");
                }
            }

            @Override
            public void finishWithError(int sucess, int error) {
                if(ReadPresenter.this!=null&&!ReadPresenter.this.view.isFinishing()){
                    downLoadManager=null;
                    ReadPresenter.this.view.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ReadPresenter.this.view.rl_download_content_new.setVisibility(View.GONE);
                            ReadPresenter.this.view.tv_download_error_new.setVisibility(View.VISIBLE);
                            ReadPresenter.this.view.tv_download_error_new.setText("有"+error+"章缓存失败,请确认你的网络连接正常,然后点击重新缓存失败章节");
                        }
                    });
                }else{
                    UIUtils.showToast("有"+error+"章缓存失败,请确认你的网络连接正常,然后在书架中点击下载重试");
                }
            }
        },Constant.muluList,view.bookid);
       new Thread( new Runnable(){

           @Override
           public void run() {
               downLoadManager.downLoad();
           }
       }).start();

    }
    private boolean zhengxu = true;

    public void showMuluPre() {
        if (Constant.muluList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("请先打开你的网络连接");
            } else {
                FBNetwork.getInstance().getBookmulu(view.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                    @Override
                    public void onNext(BookMuluInfo httpResult) {
                        super.onNext(httpResult);
                        if(!ReadPresenter.this.view.isFinishing()){
                            Constant.muluList = httpResult.chapters;
                            showMulu();
                        }
                    }
                });
            }
        } else {
            showMulu();
        }
    }

    public void showMulu() {
        final Dialog authorestampdialog = new Dialog(view,
                R.style.TranslucentBackground);
        authorestampdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View authorestampview = UIUtils.inflate(
                R.layout.dialog_mulu_read);
        ListView ls_mulu_read = (ListView) authorestampview.findViewById(R.id.ls_mulu_read);
        RelativeLayout ll_mulu_read = (RelativeLayout) authorestampview.findViewById(R.id.ll_mulu_read);
        LinearLayout ll_outside_mulu_read = (LinearLayout) authorestampview.findViewById(R.id.ll_outside_mulu_read);
        TextView tv_daoxu_mulu_read = (TextView) authorestampview.findViewById(R.id.tv_daoxu_mulu_read);
        TextView tv_current_mulu_read = (TextView) authorestampview.findViewById(R.id.tv_current_mulu_read);

        MuluReadAdapter adapter = new MuluReadAdapter();
        ls_mulu_read.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tv_daoxu_mulu_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ReadPresenter.this.zhengxu) {
                    ReadPresenter.this.zhengxu = false;
                    tv_daoxu_mulu_read.setText("正序");
                } else {
                    ReadPresenter.this.zhengxu = true;
                    tv_daoxu_mulu_read.setText("倒序");
                }
                adapter.notifyDataSetChanged();
            }
        });
        ls_mulu_read.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != currentCount) {
                    if (zhengxu) {
                        if (i > 0) {
                            currentCount = i - 1;
                            nextZhangPre();
                        } else {
                            currentCount = 1;
                            preZhang();
                        }
                    } else {
                        if (Constant.muluList.size() - 1 - i > 0) {
                            currentCount = Constant.muluList.size() - 1 - i - 1;
                            nextZhangPre();
                        } else {
                            currentCount = 1;
                            preZhang();
                        }
                    }

                }
                authorestampdialog.dismiss();
            }
        });
        ll_outside_mulu_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorestampdialog.dismiss();
            }
        });
        tv_current_mulu_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zhengxu) {
                    ls_mulu_read.setSelection(currentCount);
                } else {
                    ls_mulu_read.setSelection(Constant.muluList.size() - 1 - currentCount);
                }

            }
        });
        authorestampdialog.setContentView(authorestampview);
        authorestampdialog.show();
        startAnimationDown(ll_mulu_read);
        authorestampdialog.setCanceledOnTouchOutside(true);

    }

    public void downLoadPre() {
        if (Constant.muluList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("请先打开你的网络连接");
            } else {
                FBNetwork.getInstance().getBookmulu(view.sourceid).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                    @Override
                    public void onNext(BookMuluInfo httpResult) {
                        super.onNext(httpResult);
                        Constant.muluList = httpResult.chapters;
                        downLoad();
                    }
                });
            }
        } else {
            downLoad();
        }
    }

    public void changeSourcePre() {
        if (Constant.sourceList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("请先打开你的网络连接");
            } else {
                FBNetwork.getInstance().getShuSources(view.bookid).subscribe(new ProcressSubsciber<List<ShuSourceInfo>>(false, false) {
                    @Override
                    public void onNext(List<ShuSourceInfo> httpResult) {
                        super.onNext(httpResult);
                        Constant.sourceList = httpResult;
                        changeSource();
                    }
                });
            }
        } else {
            changeSource();
        }
    }

    private void changeSource() {
        final Dialog authorestampdialog = new Dialog(view,
                R.style.TranslucentBackground);
        authorestampdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View authorestampview = UIUtils.inflate(
                R.layout.dialog_source_read);
        ListView ls_source_read = (ListView) authorestampview.findViewById(R.id.ls_source_read);
        LinearLayout ll_outside_source_read = (LinearLayout) authorestampview.findViewById(R.id.ll_outside_source_read);
        RelativeLayout rl_change_source_read = (RelativeLayout) authorestampview.findViewById(R.id.rl_change_source_read);
        RelativeLayout rl_outside_source_read = (RelativeLayout) authorestampview.findViewById(R.id.rl_outside_source_read);

        SourceReadAdapter adapter = new SourceReadAdapter();
        ls_source_read.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ls_source_read.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (Constant.sourceList.get(i).source.contains("vip")) {
                    UIUtils.showToast("老铁,此书源是收费的,换个吧");
                } else {
                    FBNetwork.getInstance().getBookmulu(Constant.sourceList.get(i)._id).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
                        @Override
                        public void onNext(BookMuluInfo httpResult) {
                            super.onNext(httpResult);
                            Constant.muluList = httpResult.chapters;
                            ReadPresenter.this.shujiaBookBean.bookSourceID=Constant.sourceList.get(i)._id;
                            DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                            Log.e("源相关name::", "::::: " + Constant.sourceList.get(i).name);
                            Log.e("源相关link::", "::::: " + Constant.muluList.get(i).link);
                            BookPathBeanDaoManager.cleanBookPathBeanDao(ReadPresenter.this.view.bookPathid);
                            getContent(currentCount, false);
                        }
                    });
                    authorestampdialog.dismiss();
                }

            }
        });
        ll_outside_source_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorestampdialog.dismiss();
            }
        });
        rl_outside_source_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorestampdialog.dismiss();
            }
        });
        authorestampdialog.setContentView(authorestampview);
        authorestampdialog.show();
        startAnimationChangeSource(rl_change_source_read);
        authorestampdialog.setCanceledOnTouchOutside(true);
    }

    List<String> shuqianDateList = new ArrayList<>();

    public void showShuqian() {
        final Dialog authorestampdialog = new Dialog(view,
                R.style.TranslucentBackground);
        authorestampdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View authorestampview = UIUtils.inflate(
                R.layout.dialog_shuqian_read);
        ListView ls_shuqian_read = (ListView) authorestampview.findViewById(R.id.ls_shuqian_read);
        RelativeLayout rl_outside_shuqian = (RelativeLayout) authorestampview.findViewById(R.id.rl_outside_shuqian);
        RelativeLayout rl_new_shuqian_read = (RelativeLayout) authorestampview.findViewById(R.id.rl_new_shuqian_read);
        shuqianDateList.clear();
        if (!TextUtils.isEmpty(shujiaBookBean.shuqian)) {
            if (shujiaBookBean.shuqian.contains("-")) {
                String[] arr = shujiaBookBean.shuqian.split("-");
                for (int i = 0; i < arr.length; i++) {
                    shuqianDateList.add(arr[i]);
                }
            } else {
                shuqianDateList.add(shujiaBookBean.shuqian);
            }

        }
        ShuqianReadAdapter adapter = new ShuqianReadAdapter();
        ls_shuqian_read.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ls_shuqian_read.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (shuqianDateList.contains(currentCount + "")) {

                } else {
                    shuqianDateList.add(currentCount + "");
                    adapter.notifyDataSetChanged();
                    shujiaBookBean.shuqian = shujiaBookBean.shuqian + "-" + currentCount;
                    DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                }
                currentCount = Integer.parseInt(shuqianDateList.get(i));
                getContent(currentCount, false);
                authorestampdialog.dismiss();
            }
        });
        rl_outside_shuqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorestampdialog.dismiss();
            }
        });
        rl_new_shuqian_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shuqianDateList.size() == 0) {
                    shuqianDateList.add(currentCount + "");
                    adapter.notifyDataSetChanged();
                    shujiaBookBean.shuqian = currentCount + "";
                    DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                } else {
                    if (shuqianDateList.contains(currentCount + "")) {
                        UIUtils.showToast("此书签已经添加了");
                    } else {
                        shuqianDateList.add(currentCount + "");
                        adapter.notifyDataSetChanged();
                        shujiaBookBean.shuqian = shujiaBookBean.shuqian + "-" + currentCount;
                        DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                    }
                }

            }


        });
        authorestampdialog.setContentView(authorestampview);
        authorestampdialog.show();
        authorestampdialog.setCanceledOnTouchOutside(true);
    }

    public void showShuqianPre() {
        if (Constant.sourceList == null) {
            if (NetworkUtils.checkNetWorkType() == 0) {
                UIUtils.showToast("请先打开你的网络连接");
            } else {
                FBNetwork.getInstance().getShuSources(view.bookid).subscribe(new ProcressSubsciber<List<ShuSourceInfo>>(false, false) {
                    @Override
                    public void onNext(List<ShuSourceInfo> httpResult) {
                        super.onNext(httpResult);
                        Constant.sourceList = httpResult;
                        showShuqian();
                    }
                });
            }
        } else {
            showShuqian();
        }
    }

    private class MuluReadAdapter extends BaseAdapter {
        private long cacheCount = BookPathBeanDaoManager.getDuiyingTitleCount(view.bookPathid);

        @Override
        public int getCount() {
            return Constant.muluList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MuluHolder holder = null;
            if (view == null) {
                holder = new MuluHolder();
                view = holder.initview();
                view.setTag(holder);
            } else {
                holder = (MuluHolder) view.getTag();
            }
            Log.e("cacheCount", "::::::: " + cacheCount);
            if (ReadPresenter.this.zhengxu) {
                holder.tv_title_muluitem.setText(Constant.muluList.get(i).title);
                if (i < cacheCount) {
                    holder.tv_state_muluitem.setText("已缓存");
                    holder.tv_title_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.light_coffee));
                    holder.tv_state_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.light_coffee));
                } else {
                    holder.tv_state_muluitem.setText("未缓存");
                    holder.tv_state_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_999999));
                    holder.tv_title_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_999999));
                }
            } else {
                holder.tv_title_muluitem.setText(Constant.muluList.get(Constant.muluList.size() - 1 - i).title);
                if (Constant.muluList.size() - 1 - i >= cacheCount) {
                    holder.tv_state_muluitem.setText("未缓存");
                    holder.tv_state_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_999999));
                    holder.tv_title_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.color_999999));
                } else {
                    holder.tv_state_muluitem.setText("已缓存");
                    holder.tv_title_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.light_coffee));
                    holder.tv_state_muluitem.setTextColor(ReadPresenter.this.view.getResources().getColor(R.color.light_coffee));
                }
            }


            return view;
        }
    }

    private class MuluHolder {
        private TextView tv_title_muluitem;
        private TextView tv_state_muluitem;

        private View initview() {
            View view = View.inflate(ReadPresenter.this.view, R.layout.item_mulu_read, null);
            tv_state_muluitem = (TextView) view.findViewById(R.id.tv_state_muluitem);
            tv_title_muluitem = (TextView) view.findViewById(R.id.tv_title_muluitem);
            return view;
        }
    }

    private class SourceReadAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Constant.sourceList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SourceHolder holder = null;
            if (view == null) {
                holder = new SourceHolder();
                view = holder.initview();
                view.setTag(holder);
            } else {
                holder = (SourceHolder) view.getTag();
            }
            holder.tv_title_muluitem.setText(Constant.sourceList.get(i).name + "(" + Constant.sourceList.get(i).source + ")");
            holder.tv_state_muluitem.setText(Constant.sourceList.get(i).lastChapter);
            return view;
        }
    }

    private class SourceHolder {
        private TextView tv_title_muluitem;
        private TextView tv_state_muluitem;

        private View initview() {
            View view = View.inflate(ReadPresenter.this.view, R.layout.item_source_read, null);
            tv_state_muluitem = (TextView) view.findViewById(R.id.tv_state_muluitem);
            tv_title_muluitem = (TextView) view.findViewById(R.id.tv_title_muluitem);
            return view;
        }
    }

    private class ShuqianReadAdapter extends BaseAdapter {
        public ShuqianReadAdapter() {
        }

        @Override
        public int getCount() {
            return shuqianDateList == null ? 0 : shuqianDateList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ShuqianHolder holder = null;
            if (view == null) {
                holder = new ShuqianHolder();
                view = holder.initview();
                view.setTag(holder);
            } else {
                holder = (ShuqianHolder) view.getTag();
            }
            if (shuqianDateList != null) {
                Log.e("shuqian:::", ":::::: " + shuqianDateList.get(i));
                Log.e("shuqiansize():::", ":::::: " + shuqianDateList.size());
                holder.tv_currentcount_shuqianitem.setText(Constant.muluList.get(Integer.parseInt(shuqianDateList.get(i))).title);
            }
            holder.rl_delete_shuqian_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shuqianDateList.remove(i);
                    ShuqianReadAdapter.this.notifyDataSetChanged();
                    String shuqian = "";
                    if (shuqianDateList.size() == 1) {
                        shuqian = shuqianDateList.get(0) + "";
                    } else if (shuqianDateList.size() > 1) {
                        for (int i = 0; i < shuqianDateList.size(); i++) {
                            if (i == 0) {
                                shuqian = shuqianDateList.get(0) + "";
                            } else {
                                shuqian = shuqian + "-" + shuqianDateList.get(i);
                            }

                        }
                    }
                    shujiaBookBean.shuqian = shuqian;
                    DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);

                }
            });
            return view;
        }
    }

    private class ShuqianHolder {
        private TextView tv_currentcount_shuqianitem;
        private RelativeLayout rl_delete_shuqian_item;

        private View initview() {
            View view = View.inflate(ReadPresenter.this.view, R.layout.item_shuqian_read, null);
            tv_currentcount_shuqianitem = (TextView) view.findViewById(R.id.tv_currentcount_shuqianitem);
            rl_delete_shuqian_item = (RelativeLayout) view.findViewById(R.id.rl_delete_shuqian_item);
            return view;
        }
    }
}
