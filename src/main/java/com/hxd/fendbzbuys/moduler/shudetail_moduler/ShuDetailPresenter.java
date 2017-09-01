package com.hxd.fendbzbuys.moduler.shudetail_moduler;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.base.DownLoadCallBack;
import com.hxd.fendbzbuys.base.MyCallBack;
import com.hxd.fendbzbuys.domain.AutherBooksList;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.BookInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.ShuSourceInfo;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.manager.BookDownLoadManager;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.manager.DialogManager;
import com.hxd.fendbzbuys.moduler.read_moduler.ReadActivity;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.utils.UIUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.hxd.fendbzbuys.utils.UIUtils.runOnUiThread;

/**
 * Created by lichao on 17/7/30.
 */

public class ShuDetailPresenter extends BasePresenter<ShuDetailActivity> {
    public ShuDetailPresenter(ShuDetailActivity view) {
        super(view);
    }

    private BookInfo bookInfo;
    private List<AutherBooksList.BooksEntity> autherBooks;
    private List<AutherBooksList.BooksEntity> bookNameBooks;
    public String bookSourceID;
    private LtAdapter autherAdapter;
    private LtAdapter shuAdapter;
    private BangdanBooksBean bangdanBooksBean;

    @Override
    public void initData() {
        getBookInfo();
        getShuSources();
        getTuijianshuOne();
        getTuijianshuTwo();
        view.lv_authertuijian_shudetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bangdanBooksBean = new BangdanBooksBean();
                bangdanBooksBean.bookID = autherBooks.get(i)._id;
                bangdanBooksBean.author = autherBooks.get(i).author;
                bangdanBooksBean.cover = autherBooks.get(i).cover;
                bangdanBooksBean.latelyFollower = autherBooks.get(i).latelyFollower + "";
                bangdanBooksBean.shortIntro = autherBooks.get(i).shortIntro;
                ShuDetailActivity.invoke(ShuDetailPresenter.this.view, bangdanBooksBean);
                ShuDetailPresenter.this.view.finish();
            }
        });
        view.lv_booktuijian_shudetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bangdanBooksBean = new BangdanBooksBean();
                bangdanBooksBean.bookID = bookNameBooks.get(i)._id;
                bangdanBooksBean.author = bookNameBooks.get(i).author;
                bangdanBooksBean.cover = bookNameBooks.get(i).cover;
                bangdanBooksBean.latelyFollower = bookNameBooks.get(i).latelyFollower + "";
                bangdanBooksBean.shortIntro = bookNameBooks.get(i).shortIntro;
                ShuDetailActivity.invoke(ShuDetailPresenter.this.view, bangdanBooksBean);
                ShuDetailPresenter.this.view.finish();
            }
        });
    }

    private void getBookInfo() {
        FBNetwork.getInstance().getBookInfo(view.bookInfo.bookID).subscribe(new ProcressSubsciber<BookInfo>(false, false) {
            @Override
            public void onNext(BookInfo bookInfo) {
                super.onNext(bookInfo);
                ShuDetailPresenter.this.bookInfo = bookInfo;
                setView();

            }
        });
    }

    private void getTuijianshuOne() {
        FBNetwork.getInstance().getAutherBooks(view.bookInfo.author).subscribe(new ProcressSubsciber<AutherBooksList>(false, false) {
            @Override
            public void onNext(AutherBooksList httpResult) {
                super.onNext(httpResult);
                autherBooks = httpResult.books;
                if (autherBooks.size() == 0) {
                    ShuDetailPresenter.this.view.rl_auther_tuijian.setVisibility(View.GONE);
                } else {
                    ShuDetailPresenter.this.view.rl_auther_tuijian.setVisibility(View.VISIBLE);
                    if (autherAdapter == null) {
                        autherAdapter = new LtAdapter(autherBooks);
                    }
                    ShuDetailPresenter.this.view.lv_authertuijian_shudetail.setAdapter(autherAdapter);
                    autherAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void getShuSources() {
        FBNetwork.getInstance().getShuSources(view.bookInfo.bookID).subscribe(new ProcressSubsciber<List<ShuSourceInfo>>(false, false) {
            @Override
            public void onNext(List<ShuSourceInfo> httpResult) {
                super.onNext(httpResult);
                Constant.sourceList = httpResult;
                if(httpResult.size()>1){
                    bookSourceID = httpResult.get(httpResult.size() - 1)._id;
                    for (int i = 0; i < httpResult.size(); i++) {
                        if ("my176".equals(httpResult.get(i).source)) {
                            bookSourceID = httpResult.get(i)._id;
                        }
                    }
                }else{
                    if((httpResult.size()==1&& httpResult.get(0).source.contains("vip"))|| httpResult.size()==0){
                        UIUtils.showToast("没找到此书,或者是没有免费版");
                    }
                }

            }
        });
    }

    private void getTuijianshuTwo() {
        FBNetwork.getInstance().getBooksTuijian(view.bookInfo.bookID).subscribe(new ProcressSubsciber<AutherBooksList>(false, false) {
            @Override
            public void onNext(AutherBooksList autherBooksList) {
                super.onNext(autherBooksList);
                bookNameBooks = autherBooksList.books;
                if (bookNameBooks.size() > 0) {
                    if (shuAdapter == null) {
                        shuAdapter = new LtAdapter(bookNameBooks);
                    }
                    ShuDetailPresenter.this.view.lv_booktuijian_shudetail.setAdapter(shuAdapter);
                    shuAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void setView() {
        view.tv_auther_oter.setText(view.bookInfo.author + " 还写过");
        view.tv_shuname_shudetail.setText(bookInfo.title);
        view.tv_shustyle_shudetail.setText(bookInfo.minorCate);
        if (bookInfo.isSerial) {
            view.tv_shustyle2_shudetail.setText("_连载中");
        } else {
            view.tv_shustyle2_shudetail.setText("_已完结");
        }
        view.tv_auther_shudetail.setText("作者 " + bookInfo.author);
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1;
        String datastr = "今天";
        try {
            date1 = sf1.parse(bookInfo.updated);
            datastr = sf2.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        view.tv_updatatime_shudetail.setText("更新时间 " + datastr);
        view.tv_shudetail_shudetail.setText(bookInfo.longIntro);
        view.tv_shudetail2_shudetail.setText(bookInfo.longIntro);
        view.tv_lastzhangjie_shudetail.setText("更新至 " + bookInfo.lastChapter);
        Glide.with(view).load(Common.getIonicCommonUrl() + bookInfo.cover).into(view.iv_shuicon_shudetail);
    }

    boolean flag = false;

    public void zhankai() {
        if (flag) {
            flag = false;
            view.tv_zhankai_shudetail.setText("展开");
            view.tv_shudetail2_shudetail.setVisibility(View.GONE);
            view.tv_shudetail_shudetail.setVisibility(View.VISIBLE);
        } else {
            flag = true;
            view.tv_zhankai_shudetail.setText("收起");
            view.tv_shudetail2_shudetail.setVisibility(View.VISIBLE);
            view.tv_shudetail_shudetail.setVisibility(View.GONE);
        }

    }

    public void clickXiazai() {
        if (DownLoadToastManager.miToast != null) {
            UIUtils.showToast("请稍后,当前有任务正在缓存中...");
            UIUtils.showToast("您也可以加入书架进行批量下载");
        } else {
            clickJiarushujia(true);
        }
    }

    public void clickLijiyuedu() {
        if (bookInfo != null) {
            saveBookInfoShujia();
        }

    }

    private void saveBookInfoShujia() {
        ShujiaBookBean shujiaBookBean = DaoManager.getInstance().getShujiaBookBeanDao().load(bookInfo._id);
        if (shujiaBookBean == null) {
            ReadActivity.invoke(view, view.bookInfo.bookID, this.bookSourceID, 0);
            shujiaBookBean = new ShujiaBookBean();
            shujiaBookBean.bookId = bookInfo._id;
            shujiaBookBean.author = bookInfo.author;
            shujiaBookBean.bookName = bookInfo.title;
            shujiaBookBean.bookSourceID = this.bookSourceID;
            shujiaBookBean.cover = bookInfo.cover;
            shujiaBookBean.lastChapter = bookInfo.lastChapter;
            shujiaBookBean.isSerial = bookInfo.isSerial;
            shujiaBookBean.isZhudong = false;
            shujiaBookBean.longIntro = bookInfo.longIntro;
            shujiaBookBean.bookpathBean = 0;
            shujiaBookBean.manyDownload = 0;
            shujiaBookBean.jiaruDate = System.currentTimeMillis();
            DaoManager.getInstance().getShujiaBookBeanDao().insert(shujiaBookBean);
            Log.e("savebookinfo", "::::::::::::::::::::::::::::::::::::");
        } else {
            ReadActivity.invoke(view, view.bookInfo.bookID, this.bookSourceID, shujiaBookBean.bookpathBean);
        }
    }

    public void clickJiarushujia(boolean isDownLoad) {
        ShujiaBookBean shujiaBookBean = DaoManager.getInstance().getShujiaBookBeanDao().load(bookInfo._id);
        if (shujiaBookBean != null && shujiaBookBean.bookpathBean > 0) {
            if (isDownLoad) {
                //1已经在书架中,需要获取当前下载了多少涨
                if (downloadManager != null) {
                    UIUtils.showToast("正在缓存中...");
                } else {
                    downLoad(shujiaBookBean.bookpathBean);
                }
            } else {
                UIUtils.showToast("此书已经在你的书架中了");
            }

        } else {
            int bookpathid = DaoManager.getInstance().getKongXianBookPathBeanDao();
            if (bookpathid == 20) {
                DialogManager.createYiChuShujiaDialog(view, true, false);
            } else {
                if (shujiaBookBean == null) {
                    shujiaBookBean = new ShujiaBookBean();
                    shujiaBookBean.bookId = bookInfo._id;
                    shujiaBookBean.author = bookInfo.author;
                    shujiaBookBean.bookName = bookInfo.title;
                    shujiaBookBean.bookSourceID = this.bookSourceID;
                    shujiaBookBean.cover = bookInfo.cover;
                    shujiaBookBean.lastChapter = bookInfo.lastChapter;
                    shujiaBookBean.isSerial = bookInfo.isSerial;
                    shujiaBookBean.isZhudong = false;
                    shujiaBookBean.longIntro = bookInfo.longIntro;
                    shujiaBookBean.bookpathBean = bookpathid;
                    shujiaBookBean.isZhudong = true;
                    shujiaBookBean.manyDownload = 0;
                    shujiaBookBean.currentZhangjie = 0 + "";
                    shujiaBookBean.jiaruDate = System.currentTimeMillis();
                    shujiaBookBean.bookTotakCount=0;
                    DaoManager.getInstance().getShujiaBookBeanDao().insert(shujiaBookBean);
                    if (isDownLoad) {
                        //书架中没有,也没有看过,当前下载为0
                        if (downloadManager != null) {
                            UIUtils.showToast("正在缓存中...");
                        } else {
                            downLoad(shujiaBookBean.bookpathBean);
                        }
                    } else {
                        UIUtils.showToast("成功加入书架");
                    }


                } else {
                    if (shujiaBookBean.bookpathBean == 0) {
                        shujiaBookBean.bookpathBean = bookpathid;
                        shujiaBookBean.isZhudong = true;
                        shujiaBookBean.manyDownload = 0;
                        DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                        if (isDownLoad) {
                            //书架中没有,已经看过,当前下载为0
                            if (downloadManager != null) {
                                UIUtils.showToast("正在缓存中...");
                            } else {
                                downLoad(shujiaBookBean.bookpathBean);
                            }
                        } else {
                            UIUtils.showToast("成功加入书架");
                        }
                    }

                }

            }
        }


    }

    private void downLoad(int bookPathid) {
        downLoadPre(bookPathid, new DownLoadCallBack() {
            @Override
            public void start(int tatleCount) {
                BigDecimal value = new BigDecimal(tatleCount).divide(new BigDecimal(muluList.size()), 2, BigDecimal.ROUND_UP);
                int widthNew = new BigDecimal(UIUtils.dip2px(180)).multiply(value).intValue();
                int shuzhi = (int) (value.floatValue() * 100);
                Log.e("start", "::::::: " + tatleCount);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("初始化toast", "::::::: " + widthNew);
                        DownLoadToastManager.creatToast();
                        DownLoadToastManager.bookid=bookInfo._id;
                        DownLoadToastManager.miToast.setErrorHintGone();
                        DownLoadToastManager.miToast.setJinduText("缓存中:");
                        DownLoadToastManager.miToast.setJindu(widthNew);
                        DownLoadToastManager.miToast.setJinduText(shuzhi + "%");
                    }
                });
            }

            @Override
            public void update(int sucess) {
                if (sucess >= 100 && sucess % 100 == 0) {
                    Log.e("sucess", "::::::: " + sucess);
                    Log.e("sucess", "::::::: " + muluList.size());
                    BigDecimal value = new BigDecimal(sucess).divide(new BigDecimal(muluList.size()), 2, BigDecimal.ROUND_UP);
                    Log.e("value", "::::::: " + value);
                    int widthNew = new BigDecimal(UIUtils.dip2px(180)).multiply(value).intValue();
                    Log.e("widthNew", "::::::: " + widthNew);
                    int shuzhi = (int) (value.floatValue() * 100);
                    Log.e("shuzhi", "::::::: " + shuzhi);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DownLoadToastManager.miToast.setJindu(widthNew);
                            DownLoadToastManager.miToast.setJinduText(shuzhi + "%");
                        }
                    });
                }

            }

            @Override
            public void finish() {
                singleflag = 3;
                goneDownLoadHintNew();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DownLoadToastManager.miToast.setTvText("已完毕:");
                        DownLoadToastManager.miToast.setJindu(UIUtils.dip2px(180));
                        DownLoadToastManager.miToast.setJinduText(100 + "%");
                        //downloadManager = null;
                    }
                });
            }

            @Override
            public void finishWithError(int sucess, int error) {
                Log.e("缓存完毕", ":::::::有错误:::::::::::: ");
                Log.e("缓存完毕", ":::::::toast还在:::::::::::: ");
                if(DownLoadToastManager.miToast.toast==null){
                    Log.e("缓存完毕", ":::::::toase为空:::::::::::: ");
                }else{
                    Log.e("缓存完毕", ":::::::toase不为空:::::::::::: ");
                }
                singleflag = 30;
                goneDownLoadHintNew();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DownLoadToastManager.miToast.setErrorHintVisible();
                        DownLoadToastManager.miToast.tv_error_floattips.setText("有" + error + "章缓存失败,请确认你的网络连接正常,然后点击重新缓存失败章节");
                        DownLoadToastManager.miToast.setErrorOnclick(new MyCallBack() {
                            @Override
                            public void next() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        downloadManager.downLoad();
                                    }
                                });
                            }
                        });
                    }
                });


            }

        }, bookInfo._id);
    }

    private List<BookMuluInfo.ChaptersEntity> muluList;
    private BookDownLoadManager downloadManager;

    private void downLoadPre(int bookPathid, DownLoadCallBack callBack, String bookId) {
        FBNetwork.getInstance().getBookmulu(this.bookSourceID).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
            @Override
            public void onNext(BookMuluInfo httpResult) {
                super.onNext(httpResult);
                muluList = httpResult.chapters;
                //保存目录
                ShujiaBookBean shujiaBookBean=DaoManager.getInstance().getShujiaBookBeanDao().load(bookId);
                shujiaBookBean.bookTotakCount=httpResult.chapters.size();
                DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                downloadManager = new BookDownLoadManager(bookPathid, callBack, muluList, bookId);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloadManager.downLoad();
                    }
                }).start();

            }
        });

    }

    class LtAdapter extends BaseAdapter {
        List<AutherBooksList.BooksEntity> list;

        LtAdapter(List<AutherBooksList.BooksEntity> ls) {
            this.list = ls;
        }

        @Override
        public int getCount() {
            return list.size();
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
            LsHolder holder2 = null;
            if (view == null) {
                holder2 = new LsHolder();
                view = holder2.initView();
                view.setTag(holder2);
            } else {
                holder2 = (LsHolder) view.getTag();
            }
            Glide.with(ShuDetailPresenter.this.view).load(Common.getIonicCommonUrl() + list.get(i).cover).into(holder2.iv_icon_shudetail_item);
            holder2.tv_name_shudetail_item.setText(list.get(i).title);
            holder2.tv_auther_shudetail_item.setText(list.get(i).author);
            holder2.tv_state_shudetail_item.setText(list.get(i).lastChapter);
            holder2.tv_shorttitle_shudetail_item.setText(list.get(i).shortIntro);
            return view;

        }
    }

    class LsHolder {
        ImageView iv_icon_shudetail_item;
        TextView tv_name_shudetail_item;
        TextView tv_auther_shudetail_item;
        TextView tv_state_shudetail_item;
        TextView tv_shorttitle_shudetail_item;

        private View initView() {
            View view = View.inflate(ShuDetailPresenter.this.view, R.layout.shudetail_listitem, null);
            iv_icon_shudetail_item = (ImageView) view.findViewById(R.id.iv_icon_shudetail_item);
            tv_name_shudetail_item = (TextView) view.findViewById(R.id.tv_name_shudetail_item);
            tv_auther_shudetail_item = (TextView) view.findViewById(R.id.tv_auther_shudetail_item);
            tv_state_shudetail_item = (TextView) view.findViewById(R.id.tv_state_shudetail_item);
            tv_shorttitle_shudetail_item = (TextView) view.findViewById(R.id.tv_shorttitle_shudetail_item);
            return view;
        }
    }

    int singleflag = 3;

    private void goneDownLoadHintNew() {
        Timer mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (singleflag > 0) {
                            singleflag--;
                        } else {
                            mTimer.cancel();
                            if (DownLoadToastManager.miToast != null) {
                                DownLoadToastManager.miToast.hide();
                                DownLoadToastManager.miToast = null;
                                DownLoadToastManager.bookid="";

                            }
                            downloadManager = null;
                        }

                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 1000);
    }

}
