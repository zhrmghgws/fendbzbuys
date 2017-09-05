package com.hxd.fendbzbuys.manager;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.MyCallBack;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.moduler.mianze_module.MianzeActivity;
import com.hxd.fendbzbuys.ui.ListViewForScrollView;
import com.hxd.fendbzbuys.utils.UIUtils;
import com.hxd.fendbzbuys.utils.ViewUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lichao on 16/9/23.
 */
public class DialogManager {

    /**
     * 加载时候的dialog
     *
     * @param activity
     * @param isCanCancle 是否可以取消dialog
     */
    public static Dialog createLoadingDialog(Activity activity, boolean isCanCancle) {
        RotateAnimation anim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(-1);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

        View inflate = View.inflate(activity, R.layout.dialog_loading, null);
        ImageView iv_dialogloading_img = (ImageView) inflate.findViewById(R.id.iv_dialogloading_img);
        TextView tipTextView = (TextView) inflate
                .findViewById(R.id.tv_dialog_loading_loadmessage);// 提示文字
        tipTextView.setText("");// 设置加载信息
        // 加载动画
        iv_dialogloading_img.startAnimation(anim);

        Dialog loadingDialog = new Dialog(activity, R.style.TranslucentBackground);
        loadingDialog.setCanceledOnTouchOutside(isCanCancle);
        loadingDialog.setCancelable(isCanCancle);// 不可以用“返回键”取消
        loadingDialog.setContentView(inflate);
        return loadingDialog;
    }

    private static Map<Integer, String> bookIDList;

    public static void createYiChuShujiaDialog(Activity activity, boolean isCanCancle, boolean isShouye,MyCallBack callBack) {
        ShujiaBookBeanDao shujiaBookBeanDao = DaoManager.getInstance().getShujiaBookBeanDao();
        List<ShujiaBookBean> shujiaBookBeanList = shujiaBookBeanDao.queryBuilder().where(ShujiaBookBeanDao.Properties.BookpathBean.gt(0)).list();
        bookIDList = new HashMap<Integer, String>();
        Dialog loadingDialog = new Dialog(activity, R.style.TranslucentBackground);
        View inflate = View.inflate(activity, R.layout.yichushujia_dialog, null);
        RelativeLayout ll_shouyezhanshi = (RelativeLayout) inflate.findViewById(R.id.ll_shouyezhanshi);
        RelativeLayout ll_piyichushujia = (RelativeLayout) inflate.findViewById(R.id.ll_piyichushujia);
        RelativeLayout ll_gengxin = (RelativeLayout) inflate.findViewById(R.id.ll_gengxin);
        RelativeLayout ll_mianze = (RelativeLayout) inflate.findViewById(R.id.ll_mianze);

        RelativeLayout rl_yichushujia = (RelativeLayout) inflate.findViewById(R.id.rl_yichushujia);
        RelativeLayout rl_yichushujia_content = (RelativeLayout) inflate.findViewById(R.id.rl_yichushujia_content);
        View view_outside_yichudialog = inflate.findViewById(R.id.view_outside_yichudialog);
        TextView tv_yichu_ok_dialog = (TextView) inflate.findViewById(R.id.tv_yichu_ok_dialog);
        TextView tv_yichu_no_dialog = (TextView) inflate.findViewById(R.id.tv_yichu_no_dialog);
        TextView tv2_yichushujia = (TextView) inflate.findViewById(R.id.tv2_yichushujia);
        ListView lv_yichu = (ListView) inflate.findViewById(R.id.lv_yichushujia);
        if(isShouye){
            rl_yichushujia_content.setVisibility(View.GONE);
            ll_shouyezhanshi.setVisibility(View.VISIBLE);
        }else{
            rl_yichushujia_content.setVisibility(View.VISIBLE);
            ll_shouyezhanshi.setVisibility(View.GONE);
        }
        if (isShouye) {
            tv2_yichushujia.setText("点击选中后,可以批量移出书架");
            tv2_yichushujia.setTextColor(activity.getResources().getColor(R.color.color_999999));
        }
        ll_piyichushujia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yichushujia_content.setVisibility(View.VISIBLE);
                ll_shouyezhanshi.setVisibility(View.GONE);
            }
        });
        ll_mianze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MianzeActivity.invoke(activity);
            }
        });
        YichuShujiaAdapter adapter = new YichuShujiaAdapter(activity, shujiaBookBeanList);
        lv_yichu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        view_outside_yichudialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
            }
        });
        tv_yichu_no_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
            }
        });
        tv_yichu_ok_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookIDList.size() > 0) {
                    loadingDialog.dismiss();
                    for (Map.Entry<Integer, String> entry : bookIDList.entrySet()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                BookPathBeanDaoManager.cleanBookPathBeanDao(shujiaBookBeanList.get(entry.getKey()).bookpathBean);
                                shujiaBookBeanDao.deleteByKey(entry.getValue());
                            }
                        }).start();
                        Log.e("entry.getKey()", "::::::: " + entry.getKey() + "::::::entry.getValue" + ":::::" + entry.getValue());
                    }
                    UIUtils.showToast("移除成功");
                    if(callBack!=null){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        callBack.next();
                    }
                } else {
                    UIUtils.showToast("请先选中你要移除书架的书");
                }

            }
        });
        lv_yichu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView iv = (ImageView) view.findViewById(R.id.iv_isxuanze_yichu);
                if (bookIDList.containsKey(i)) {
                    bookIDList.remove(i);
                    iv.setBackgroundResource(R.mipmap.me_anquan_xz);
                } else {
                    bookIDList.put(i, shujiaBookBeanList.get(i).bookId);
                    iv.setBackgroundResource(R.mipmap.me_anquan_xzh);
                }
            }
        });
        loadingDialog.setCanceledOnTouchOutside(isCanCancle);
        loadingDialog.setCancelable(isCanCancle);// 不可以用“返回键”取消
        loadingDialog.setContentView(inflate);
        loadingDialog.show();
        rl_yichushujia.setVisibility(View.VISIBLE);
        startAnimationDown(activity,rl_yichushujia);

    }
    private static void startAnimationDown(Activity activity,View v){
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
    static class YichuShujiaAdapter extends BaseAdapter {
        Activity activity;
        List<ShujiaBookBean> shujiaBookBeanList;

        public YichuShujiaAdapter(Activity activity, List<ShujiaBookBean> list) {
            this.activity = activity;
            this.shujiaBookBeanList = list;
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

            View viewItem = View.inflate(activity, R.layout.item_yichushujia_dialog, null);
            TextView tv_name_yichu = (TextView) viewItem.findViewById(R.id.tv_name_yichu);
            TextView tv_auther_yichu = (TextView) viewItem.findViewById(R.id.tv_auther_yichu);
            TextView tv_state_yichu = (TextView) viewItem.findViewById(R.id.tv_state_yichu);
            TextView tv_lastchart_yichu = (TextView) viewItem.findViewById(R.id.tv_lastchart_yichu);
            TextView tv_manydownload_yichu = (TextView) viewItem.findViewById(R.id.tv_manydownload_yichu);
            TextView tv_currentchart_yichu = (TextView) viewItem.findViewById(R.id.tv_currentchart_yichu);
            TextView tv_jiarudate_yichu = (TextView) viewItem.findViewById(R.id.tv_jiarudate_yichu);
            ImageView iv_isxuanze_yichu = (ImageView) viewItem.findViewById(R.id.iv_isxuanze_yichu);

            tv_name_yichu.setText(shujiaBookBeanList.get(i).bookName);
            tv_auther_yichu.setText(shujiaBookBeanList.get(i).author);
            long time = shujiaBookBeanList.get(i).jiaruDate;
            int data = (int) ((System.currentTimeMillis() - time) / (1000 * 60 * 60));
            if (data > 24) {
                tv_jiarudate_yichu.setText((int) data / 24 + "天前加入");
            } else {
                if (data == 0) {
                    tv_jiarudate_yichu.setText("刚刚加入");
                } else {
                    tv_jiarudate_yichu.setText(data + "小时前加入");
                }
            }
            if(!TextUtils.isEmpty(shujiaBookBeanList.get(i).currentZhangjie)){
                tv_currentchart_yichu.setText("你读到 第" + (Integer.parseInt(shujiaBookBeanList.get(i).currentZhangjie) + 1) + "章");
            }else{
                tv_currentchart_yichu.setText("你读到 第0章");
            }

            tv_lastchart_yichu.setText("更新至 " + shujiaBookBeanList.get(i).lastChapter);
            if (shujiaBookBeanList.get(i).isSerial) {
                tv_state_yichu.setText("连载中_");
            } else {
                tv_state_yichu.setText("已完结_");
            }
            tv_manydownload_yichu.setText("已缓存 " + BookPathBeanDaoManager.getDuiyingTitleCount(shujiaBookBeanList.get(i).bookpathBean) + "章");
            if (bookIDList.containsKey(i)) {
                iv_isxuanze_yichu.setBackgroundResource(R.mipmap.me_anquan_xzh);
            } else {
                iv_isxuanze_yichu.setBackgroundResource(R.mipmap.me_anquan_xz);
            }
            return viewItem;
        }
    }

}
