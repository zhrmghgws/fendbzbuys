package com.hxd.fendbzbuys.manager;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.ui.ListViewForScrollView;
import com.hxd.fendbzbuys.utils.UIUtils;

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

    public static void createYiChuShujiaDialog(Activity activity, boolean isCanCancle, boolean isShouye) {
        ShujiaBookBeanDao shujiaBookBeanDao = DaoManager.getInstance().getShujiaBookBeanDao();
        List<ShujiaBookBean> shujiaBookBeanList = shujiaBookBeanDao.queryBuilder().where(ShujiaBookBeanDao.Properties.BookpathBean.gt(0)).list();
        bookIDList = new HashMap<Integer, String>();
        Dialog loadingDialog = new Dialog(activity, R.style.TranslucentBackground);
        View inflate = View.inflate(activity, R.layout.yichushujia_dialog, null);
        TextView tv_yichu_ok_dialog = (TextView) inflate.findViewById(R.id.tv_yichu_ok_dialog);
        TextView tv_yichu_no_dialog = (TextView) inflate.findViewById(R.id.tv_yichu_no_dialog);
        TextView tv2_yichushujia = (TextView) inflate.findViewById(R.id.tv2_yichushujia);
        ListView lv_yichu = (ListView) inflate.findViewById(R.id.lv_yichushujia);
        View view_outside_yichudialog = inflate.findViewById(R.id.view_outside_yichudialog);
        if (isShouye) {
            tv2_yichushujia.setText("点击选中后,可以批量移出书架");
            tv2_yichushujia.setTextColor(activity.getResources().getColor(R.color.color_999999));
        }
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
            tv_currentchart_yichu.setText("你读到 第" + (Integer.parseInt(shujiaBookBeanList.get(i).currentZhangjie) + 1) + "章");
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
