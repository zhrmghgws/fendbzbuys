package com.hxd.fendbzbuys;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.base.DownLoadCallBack;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.manager.BookDownLoadManager;
import com.hxd.fendbzbuys.manager.BookPathBeanDaoManager;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.moduler.account_moduler.ShujiaFragment;
import com.hxd.fendbzbuys.moduler.fenlei_moduler.FenleiFragment;
import com.hxd.fendbzbuys.moduler.laon_moduler.PaihangFragment;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.DownLoadToastManager;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.utils.UIUtils;
import com.hxd.fendbzbuys.utils.ViewUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by lichao on 16/9/20.
 */
public class MainActivityPresenter extends BasePresenter<MainActivity> {
    public ShujiaFragment shujiaFragment;
    public PaihangFragment paihangFragment;
    public FenleiFragment fenleiFragment;
    private static Boolean isExit = false;
    public int currentPage = 0;
    MainViewpagerAdapter mainViewpagerAdapter;

    public MainActivityPresenter(MainActivity view) {
        super(view);
        init();
    }

    @Override
    public void initData() {
    }


    private void init() {
        paihangFragment = new PaihangFragment();
        fenleiFragment = new FenleiFragment();
        shujiaFragment = new ShujiaFragment();
        view.viewpage_mainactivity.setOffscreenPageLimit(3);
        view.viewpage_mainactivity.setOnPageChangeListener(onPageChangeListener);
        view.viewpage_mainactivity.setCurrentItem(0);
        onPageChangeListener.onPageSelected(0);
        if (mainViewpagerAdapter == null) {
            mainViewpagerAdapter = new MainViewpagerAdapter(view.getSupportFragmentManager());
        }
        view.viewpage_mainactivity.setAdapter(mainViewpagerAdapter);

    }

    ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            currentPage = position;
            if (position == 0) {
                view.tv_title_main.setText("我的书架");
                view.rl_xiazai_main.setVisibility(View.VISIBLE);
                view.iv_shujia_home.setBackgroundResource(R.mipmap.tab_book_shelf_selected);
                view.iv_paihang_home.setBackgroundResource(R.mipmap.tab_rank);
                view.iv_fenlei_home.setBackgroundResource(R.mipmap.tab_categoty);
                view.tv_shujia_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor_hover));
                view.tv_paihang_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.tv_fenlei_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.iv_shezhi_main.setVisibility(View.VISIBLE);
                view.rl_nanornv_titlebar.setVisibility(View.GONE);
            } else if (position == 1) {
                view.tv_title_main.setText("排行榜");
                view.rl_xiazai_main.setVisibility(View.GONE);
                view.iv_shujia_home.setBackgroundResource(R.mipmap.tab_book_shelf);
                view.iv_fenlei_home.setBackgroundResource(R.mipmap.tab_categoty);
                view.iv_paihang_home.setBackgroundResource(R.mipmap.tab_rank_selected);
                view.tv_shujia_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.tv_fenlei_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.tv_paihang_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor_hover));
                view.iv_shezhi_main.setVisibility(View.GONE);
                view.rl_nanornv_titlebar.setVisibility(View.VISIBLE);
            } else {
                view.tv_title_main.setText("分类书库");
                view.rl_xiazai_main.setVisibility(View.GONE);
                view.iv_shujia_home.setBackgroundResource(R.mipmap.tab_book_shelf);
                view.iv_paihang_home.setBackgroundResource(R.mipmap.tab_rank);
                view.iv_fenlei_home.setBackgroundResource(R.mipmap.tab_category_selected);
                view.tv_shujia_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.tv_paihang_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor));
                view.tv_fenlei_home.setTextColor(view.getResources().getColorStateList(R.color.hometextcolor_hover));
                view.iv_shezhi_main.setVisibility(View.GONE);
                view.rl_nanornv_titlebar.setVisibility(View.VISIBLE);
            }
        }
    };


    class MainViewpagerAdapter extends FragmentPagerAdapter {

        public MainViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return getMainFragment(arg0);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private Fragment getMainFragment(int position) {
        if (position == 0)
            return shujiaFragment;
        else if (position == 1)
            return paihangFragment;
        else
            return fenleiFragment;
    }

    public void homeloan_click() {
        view.viewpage_mainactivity.setCurrentItem(0);

    }

    public void homeaccount_click() {
        view.viewpage_mainactivity.setCurrentItem(1);

    }

    public void homefenlei_click() {
        view.viewpage_mainactivity.setCurrentItem(2);
        ;

    }

    /**
     * 双击退出应用
     */

    protected void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true;
            Toast.makeText(view, "再按一次退出程序", Toast.LENGTH_LONG).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    protected void gotoRegist() {

    }

    public void downLoadMain() {
        createDownLoadDialog(view, true);
    }

    ShujiaBookBeanDao shujiaBookBeanDao;
    public Dialog loadingDialog;
    public DownLoadAdapter adapter;
    Map<Integer, View> viewList;
    Map<Integer, View> viewBaseList;
    Map<Integer, TextView> textViewList;
    Map<Integer, TextView> textViewdownList;
    Map<Integer, BookDownLoadManager> downloadManagerList = new HashMap<>();
    Map<Integer, List<BookMuluInfo.ChaptersEntity>> mululistList = new HashMap<>();
    List<ShujiaBookBean> shujiaBookBeanList;
    ArrayList<Integer> duilie = new ArrayList<>();
    Timer mTimer;

    private void delayFinish() {
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if( adapter != null){
                            adapter.notifyDataSetChanged();
                        }
                        if (downloadManagerList.size() > 0 ) {
                            Log.e("刷新", "::::::::::下载列表刷新......... ");
                        } else {
                            if (mTimer != null) {
                                mTimer.cancel();
                                mTimer = null;
                            }

                        }

                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 3000);
    }

    private void createDownLoadDialog(Activity activity, boolean isCanCancle) {
        shujiaBookBeanDao = DaoManager.getInstance().getShujiaBookBeanDao();
        shujiaBookBeanList = shujiaBookBeanDao.queryBuilder().where(ShujiaBookBeanDao.Properties.BookpathBean.gt(0)).list();
        loadingDialog = new Dialog(activity, R.style.TranslucentBackground);
        View inflate = View.inflate(activity, R.layout.shouye_download_dialog, null);
        TextView tv2_down = (TextView) inflate.findViewById(R.id.tv2_down);
        LinearLayout ll_down = (LinearLayout) inflate.findViewById(R.id.ll_down);
        ListView lv_down = (ListView) inflate.findViewById(R.id.lv_down);
        View view_outside_down_dialog = inflate.findViewById(R.id.view_outside_down_dialog);

        adapter = new DownLoadAdapter(activity);
        lv_down.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        view_outside_down_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
                loadingDialog = null;
                adapter = null;
                viewList.clear();
                textViewList.clear();
                textViewdownList.clear();
                viewList = null;
                textViewList = null;
                textViewdownList = null;

            }
        });
        lv_down.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        loadingDialog.setCanceledOnTouchOutside(isCanCancle);
        loadingDialog.setCancelable(isCanCancle);// 不可以用“返回键”取消
        loadingDialog.setContentView(inflate);
        loadingDialog.show();
        startAnimationDown(ll_down);

    }

    private void startAnimationDown(View v) {
       /* DisplayMetrics dm = view.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        ValueAnimator va = ValueAnimator.ofInt(1, h_screen - UIUtils.dip2px(50) - ViewUtils.getStatusBarHeight(view));
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int widths = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams ll = v.getLayoutParams();
                ll.height = widths;

                v.setLayoutParams(ll);
            }
        });
        va.setDuration(1000);
        va.start();*/

        Animation myAnimation_Scale =new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        myAnimation_Scale.setDuration(500);
        //动画效果从XMl文件中定义
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.setAnimation(myAnimation_Scale);
    }

    class DownLoadAdapter extends BaseAdapter {
        Activity activity;


        public DownLoadAdapter(Activity activity) {
            this.activity = activity;
            viewList = new HashMap<>();
            textViewList = new HashMap<>();
            textViewdownList = new HashMap<>();
            viewBaseList = new HashMap<>();
        }

        @Override
        public int getCount() {
            return shujiaBookBeanList.size();
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

            View viewItem = View.inflate(activity, R.layout.item_download_dialog, null);
            TextView tv_name_down = (TextView) viewItem.findViewById(R.id.tv_name_down);
            TextView tv_auther_down = (TextView) viewItem.findViewById(R.id.tv_auther_down);
            TextView tv_manydownload_down = (TextView) viewItem.findViewById(R.id.tv_manydownload_down);
            TextView tv_down_down = (TextView) viewItem.findViewById(R.id.tv_down_down);
            textViewdownList.put(i, tv_down_down);
            TextView tv_jindu_down = (TextView) viewItem.findViewById(R.id.tv_jindu_down);
            textViewList.put(i, tv_jindu_down);
            View view_jindu_down = viewItem.findViewById(R.id.view_jindu_down);
            viewList.put(i, view_jindu_down);
            View view_jindu_base_down = viewItem.findViewById(R.id.view_jindu_base_down);
            viewBaseList.put(i, view_jindu_base_down);
            tv_name_down.setText(shujiaBookBeanList.get(i).bookName);
            tv_auther_down.setText(shujiaBookBeanList.get(i).author);
            int tatolCount = (int) BookPathBeanDaoManager.getDuiyingTitleCount(shujiaBookBeanList.get(i).bookpathBean);
            tv_manydownload_down.setText("已缓存 " + tatolCount + "章");
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view_jindu_down.getLayoutParams();
           /* BigDecimal value = new BigDecimal(tatleCount).divide(new BigDecimal(muluList.size()), 2, BigDecimal.ROUND_UP);
            int widthNew = new BigDecimal(UIUtils.dip2px(180)).multiply(value).intValue();
            int shuzhi = (int) (value.floatValue() * 100);
            lp.width=*/

            if (duilie.contains(i)) {
                view_jindu_base_down.setVisibility(View.VISIBLE);
                view_jindu_down.setVisibility(View.VISIBLE);
                tv_jindu_down.setVisibility(View.VISIBLE);
                tv_manydownload_down.setVisibility(View.GONE);
                tv_down_down.setBackgroundResource(R.drawable.yuanjiao_button_gray_down);
                tv_down_down.setClickable(false);
                tv_down_down.setText("等待中...");
                if(mululistList.get(i)!=null){
                    tv_down_down.setText("下载中...");
                    BigDecimal value = new BigDecimal(tatolCount).divide(new BigDecimal(mululistList.get(i).size()), 2, BigDecimal.ROUND_UP);
                    int widthNew = new BigDecimal(UIUtils.dip2px(100)).multiply(value).intValue();
                    int shuzhi = (int) (value.floatValue() * 100);
                    lp.width = widthNew;
                    view_jindu_down.setLayoutParams(lp);
                    tv_jindu_down.setText(shuzhi + "%");
                }else{

                }
            } else {
                tv_manydownload_down.setVisibility(View.VISIBLE);
                view_jindu_base_down.setVisibility(View.GONE);
                view_jindu_down.setVisibility(View.GONE);
                tv_jindu_down.setVisibility(View.GONE);
                tv_down_down.setBackgroundResource(R.drawable.yuanjiao_button_lanse_down);
                tv_down_down.setClickable(true);
            }
            tv_down_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DownLoadToastManager.miToast != null && DownLoadToastManager.bookid.equals(shujiaBookBeanList.get(i).bookId)) {
                        UIUtils.showToast("该书正在下载中...");
                    } else {
                        if (downloadManagerList.get(i) != null) {
                            UIUtils.showToast("该书正在下载中...");
                        } else {
                           /* if (downloadManagerList.size() > 1) {
                                UIUtils.showToast("当前下载队列较多,请稍后...");
                            } else {
                                tv_manydownload_down.setVisibility(View.GONE);
                                view_jindu_base_down.setVisibility(View.VISIBLE);
                                view_jindu_down.setVisibility(View.VISIBLE);
                                tv_jindu_down.setVisibility(View.VISIBLE);

                            }*/
                            tv_manydownload_down.setVisibility(View.GONE);
                            view_jindu_base_down.setVisibility(View.VISIBLE);
                            view_jindu_down.setVisibility(View.VISIBLE);
                            tv_jindu_down.setVisibility(View.VISIBLE);
                            if(duilie.size()==0){
                                duilie.add(i);
                                getBookMulu(i);
                                tv_down_down.setText("下载中...");
                            }else{
                                if(duilie.contains(i)){
                                    UIUtils.showToast("该书已经加入下载队列");
                                    tv_down_down.setText("等待中...");
                                }else{
                                    duilie.add(i);
                                }
                            }

                        }
                    }
                }
            });
            return viewItem;
        }
    }
    private void finishNextDown(int i){
        int j = duilie.indexOf(i);
        duilie.remove(j);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(duilie.size()>0){
            getBookMulu(duilie.get(0));
        }
    }
    private void getBookMulu(int i){
        int tatolCount = (int) BookPathBeanDaoManager.getDuiyingTitleCount(shujiaBookBeanList.get(i).bookpathBean);
        FBNetwork.getInstance().getBookmulu(shujiaBookBeanList.get(i).bookSourceID).subscribe(new ProcressSubsciber<BookMuluInfo>(false, false) {
            @Override
            public void onNext(BookMuluInfo httpResult) {
                super.onNext(httpResult);
                BigDecimal value = new BigDecimal(tatolCount).divide(new BigDecimal(httpResult.chapters.size()), 2, BigDecimal.ROUND_UP);
                int widthNew = new BigDecimal(UIUtils.dip2px(100)).multiply(value).intValue();
                int shuzhi = (int) (value.floatValue() * 100);
                Log.e("进度", ";:::::::widthNew:::::::::" + widthNew);
                Log.e("i", ";:::::::i==:::::::::" + i);
                if(viewList!=null&&viewList.get(i)!=null){
                    RelativeLayout.LayoutParams lpi = (RelativeLayout.LayoutParams) viewList.get(i).getLayoutParams();
                    lpi.width = widthNew;
                    viewList.get(i).setLayoutParams(lpi);
                    textViewList.get(i).setText(shuzhi + "%");
                }
                downloadBook(i, httpResult.chapters);
                if (mTimer == null) {
                    delayFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                finishNextDown(i);
            }
        });
    }
    private void downloadBook(int i, List<BookMuluInfo.ChaptersEntity> muluList) {
        mululistList.put(i, muluList);
        int tatolCount = (int) BookPathBeanDaoManager.getDuiyingTitleCount(shujiaBookBeanList.get(i).bookpathBean);
        BigDecimal value = new BigDecimal(tatolCount).divide(new BigDecimal(muluList.size()), 2, BigDecimal.ROUND_UP);
        int widthNew = new BigDecimal(UIUtils.dip2px(100)).multiply(value).intValue();
        int shuzhi = (int) (value.floatValue() * 100);
        Log.e("进度", ";:::::::widthNew:::::::::" + widthNew);
        Log.e("i", ";:::::::i==:::::::::" + i);
        if(viewList!=null&&viewList.get(i)!=null){
            RelativeLayout.LayoutParams lpi = (RelativeLayout.LayoutParams) viewList.get(i).getLayoutParams();
            lpi.width = widthNew;
            viewList.get(i).setLayoutParams(lpi);
            textViewList.get(i).setText(shuzhi + "%");
        }
        if (shuzhi == 100) {
            UIUtils.showToast("已经缓存完毕!");
            mululistList.remove(i);
            finishNextDown(i);
        } else {
            BookDownLoadManager downloadManager = new BookDownLoadManager(shujiaBookBeanList.get(i).bookpathBean, new DownLoadCallBack() {
                @Override
                public void start(int tatleCount) {

                }

                @Override
                public void update(int sucess) {

                }

                @Override
                public void finish() {
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            downloadManagerList.remove(i);
                            mululistList.remove(i);
                            finishNextDown(i);

                        }
                    });
                }

                @Override
                public void finishWithError(int sucess, int error) {
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            downloadManagerList.remove(i);
                            mululistList.remove(i);
                            finishNextDown(i);
                        }
                    });


                }
            }, muluList, shujiaBookBeanList.get(i).bookId);
            downloadManagerList.put(i, downloadManager);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    downloadManager.downLoad();
                }
            }).start();
        }

    }
}
