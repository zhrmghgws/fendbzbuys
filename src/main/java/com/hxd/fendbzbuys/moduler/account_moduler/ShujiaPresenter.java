package com.hxd.fendbzbuys.moduler.account_moduler;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.MainActivity;
import com.hxd.fendbzbuys.MyApplication;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.domain.BookTotalInfo;
import com.hxd.fendbzbuys.domain.ShujiaBookBean;
import com.hxd.fendbzbuys.domain.gen.ShujiaBookBeanDao;
import com.hxd.fendbzbuys.manager.BookPathBeanDaoManager;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.moduler.read_moduler.ReadActivity;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.utils.NetworkUtils;
import com.hxd.fendbzbuys.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lichao on 16/9/21.
 */
public class ShujiaPresenter extends BasePresenter<ShujiaFragment> {
    ShujiaBookBeanDao shujiaBookBeanDao;
    List<ShujiaBookBean> shujiaBookBeanList;
    List<ShujiaBookBean> shujiaBookJiaruBeanList=new ArrayList<>();
    List<ShujiaBookBean> shujiaBookZujiBeanList=new ArrayList<>();
    ShujiaAdapter jiaRuAdapter;
    ShujiaAdapter zuJiAdapter;
    int cacheSize=20;
    public ShujiaPresenter(ShujiaFragment view) {
        super(view);
        shujiaBookBeanDao= DaoManager.getInstance().getShujiaBookBeanDao();

    }

    @Override
    public void initData() {
        view.lv_shujia_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShujiaBookBean shujiaBookBean=DaoManager.getInstance().getShujiaBookBeanDao().load(shujiaBookJiaruBeanList.get(i).bookId);
                if(NetworkUtils.checkNetWorkType()==0){
                    if(shujiaBookBean.bookTotakCount==0){

                        UIUtils.showToast("你没有缓冲该书,请检查你的网络连接");
                    }else{
                        ReadActivity.invoke(ShujiaPresenter.this.getActivity(), shujiaBookJiaruBeanList.get(i).bookId, shujiaBookJiaruBeanList.get(i).bookSourceID,shujiaBookJiaruBeanList.get(i).bookpathBean);
                    }
                }else{
                    ReadActivity.invoke(ShujiaPresenter.this.getActivity(), shujiaBookJiaruBeanList.get(i).bookId, shujiaBookJiaruBeanList.get(i).bookSourceID,shujiaBookJiaruBeanList.get(i).bookpathBean);
                }
            }
        });
        view.lv_lishizuji_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(NetworkUtils.checkNetWorkType()==0){
                    UIUtils.showToast("请检查你的网络连接");
                }else{
                    ReadActivity.invoke(ShujiaPresenter.this.getActivity(), shujiaBookZujiBeanList.get(i).bookId, shujiaBookZujiBeanList.get(i).bookSourceID,0);
                }
            }
        });
    }

    public void resume() {
        shujiaBookBeanList=shujiaBookBeanDao.loadAll();
        shujiaBookJiaruBeanList.clear();
        shujiaBookZujiBeanList.clear();
        if(shujiaBookBeanList.size()>0){
            for(int i=0;i<shujiaBookBeanList.size();i++){
                if(shujiaBookBeanList.get(i).bookpathBean>0){
                    shujiaBookJiaruBeanList.add(shujiaBookBeanList.get(i));
                }else{
                    shujiaBookZujiBeanList.add(shujiaBookBeanList.get(i));
                }
            }
            if(shujiaBookJiaruBeanList.size()>0){
                view.lv_shujia_main.setVisibility(View.VISIBLE);
                view.rl_shujiakong_main.setVisibility(View.GONE);
                if(jiaRuAdapter==null){
                    jiaRuAdapter=new ShujiaAdapter(shujiaBookJiaruBeanList,true);
                    view.lv_shujia_main.setAdapter(jiaRuAdapter);
                }
                jiaRuAdapter.notifyDataSetChanged();
                if(shujiaBookJiaruBeanList.size()==9){
                    ShujiaPresenter.this.view.tv_shujiahint_fragment.setVisibility(View.VISIBLE);
                }else{
                    ShujiaPresenter.this.view.tv_shujiahint_fragment.setVisibility(View.GONE);
                }
                String bookids="";
                for(int i=0;i<shujiaBookJiaruBeanList.size();i++){
                    if(i==0){
                        bookids=shujiaBookJiaruBeanList.get(i).bookId;
                    }else{
                        bookids=bookids+","+shujiaBookJiaruBeanList.get(i).bookId;
                    }

                }
                getUpdatedata(bookids);
            }else{
                view.lv_shujia_main.setVisibility(View.GONE);
                view.rl_shujiakong_main.setVisibility(View.VISIBLE);
            }
            if(shujiaBookZujiBeanList.size()>0){
                view.lv_lishizuji_main.setVisibility(View.VISIBLE);
                view.rl_lishizujikong_main.setVisibility(View.GONE);
                if(zuJiAdapter==null){
                    zuJiAdapter=new ShujiaAdapter(shujiaBookZujiBeanList,false);
                    view.lv_lishizuji_main.setAdapter(zuJiAdapter);
                }
                zuJiAdapter.notifyDataSetChanged();
                if(shujiaBookZujiBeanList.size()>cacheSize){
                    ShujiaBookBean  temp; // 记录临时中间值
                    int size = shujiaBookZujiBeanList.size(); // 数组大小
                    for (int i = 0; i < size - 1; i++) {
                        for (int j = i + 1; j < size; j++) {
                            if (shujiaBookZujiBeanList.get(i).jiaruDate > shujiaBookZujiBeanList.get(j).jiaruDate ) { // 交换两数的位置
                                temp = shujiaBookZujiBeanList.get(i);
                                shujiaBookZujiBeanList.add(i,shujiaBookZujiBeanList.get(j));
                                shujiaBookZujiBeanList.add(j,temp);
                            }
                        }
                    }
                    for(int m=0;m<shujiaBookZujiBeanList.size();m++){
                        Log.e("time::::", ":::::::: "+shujiaBookZujiBeanList.get(m).jiaruDate );
                        if(m<shujiaBookZujiBeanList.size()-cacheSize){
                            shujiaBookBeanDao.deleteByKey(shujiaBookZujiBeanList.get(m).bookId);
                        }

                    }
                }
            }else{
                view.lv_lishizuji_main.setVisibility(View.GONE);
                view.rl_lishizujikong_main.setVisibility(View.VISIBLE);
            }
        }else{
            view.lv_shujia_main.setVisibility(View.GONE);
            view.lv_lishizuji_main.setVisibility(View.GONE);
            view.rl_shujiakong_main.setVisibility(View.VISIBLE);
            view.rl_lishizujikong_main.setVisibility(View.VISIBLE);
        }

    }
    private void getUpdatedata(String bookids){
        FBNetwork.getInstance().getTotalCount(bookids).subscribe(new ProcressSubsciber<List<BookTotalInfo>>(false,false) {
            @Override
            public void onNext(List<BookTotalInfo> bookTotalInfos) {
                super.onNext(bookTotalInfos);
                for(int i=0;i<bookTotalInfos.size();i++){
                    for(int j=0;j<shujiaBookJiaruBeanList.size();j++){
                        if(shujiaBookJiaruBeanList.get(j).bookId.equals(bookTotalInfos.get(i)._id+"")){
                            ShujiaBookBean shujiaBookBean=shujiaBookJiaruBeanList.get(i);
                            if(bookTotalInfos.get(i).chaptersCount.equals(shujiaBookBean.bookTotakCount))
                                shujiaBookBean.lastChapter=bookTotalInfos.get(i).lastChapter;
                            shujiaBookBean.bookTotakCount=Integer.parseInt(bookTotalInfos.get(i).chaptersCount);
                            DaoManager.getInstance().getShujiaBookBeanDao().update(shujiaBookBean);
                        }
                    }

                }
            }
        });
    }

    class ShujiaAdapter extends BaseAdapter {
        List<ShujiaBookBean> list;
        boolean isJiaRu;
        public ShujiaAdapter( List<ShujiaBookBean> shujiaBookBeanList,boolean jiaRu){
            this.list=shujiaBookBeanList;
            this.isJiaRu=jiaRu;
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
            ShujiaHolder holder;
            if(view==null){
                holder=new ShujiaHolder();
                view=holder.initview();
                view.setTag(holder);
            }else{
                holder= (ShujiaHolder) view.getTag();
            }
            Glide.with(ShujiaPresenter.this.view.getActivity()).load(Common.getIonicCommonUrl()+list.get(i).cover).into(holder.iv_shujia_adapter);
            holder.tv_name_adapter.setText(list.get(i).bookName);
            holder.tv_auther_adapter.setText("作者: "+list.get(i).author);
            if(isJiaRu){
                holder.tv_state_adapter.setVisibility(View.VISIBLE);
                holder.tv_lastchart_adapter.setVisibility(View.VISIBLE);
                holder.tv_currentchart_adapter.setVisibility(View.VISIBLE);
                holder.tv_jiarudate_adapter.setVisibility(View.VISIBLE);
                holder.tv_longchart_adapter.setVisibility(View.GONE);
                holder.tv_datatime_adapter.setVisibility(View.VISIBLE);
                if(list.get(i).isSerial){
                    holder.tv_state_adapter.setText("连载中_");
                }else{
                    holder.tv_state_adapter.setText("已完结_");
                }
                holder.tv_lastchart_adapter.setText("更新至 "+list.get(i).lastChapter);
                if(TextUtils.isEmpty(list.get(i).currentZhangjie)){
                    holder.tv_currentchart_adapter.setText("你读到 第1章");
                }else{
                    holder.tv_currentchart_adapter.setText("你读到 第"+(Integer.parseInt(list.get(i).currentZhangjie)+1)+"章");
                }
                long time=list.get(i).jiaruDate;
                int data= (int) ((System.currentTimeMillis()-time)/(1000*60*60));
                if(data>24){
                    holder.tv_datatime_adapter.setText((int)data/24+"天前加入");
                }else{
                    if(data==0){
                        holder.tv_datatime_adapter.setText("刚刚加入");
                    }else{
                        holder.tv_datatime_adapter.setText(data+"小时前加入");
                    }
                }
                holder.tv_jiarudate_adapter.setText("已缓存"+(BookPathBeanDaoManager.getDuiyingTitleCount(list.get(i).bookpathBean))+"章");

            }else{
                holder.tv_state_adapter.setVisibility(View.GONE);
                holder.tv_lastchart_adapter.setVisibility(View.GONE);
                holder.tv_currentchart_adapter.setVisibility(View.GONE);
                holder.tv_jiarudate_adapter.setVisibility(View.GONE);
                holder.tv_longchart_adapter.setVisibility(View.VISIBLE);
                holder.tv_datatime_adapter.setVisibility(View.VISIBLE);
                holder.tv_longchart_adapter.setText(list.get(i).longIntro);
                long time=list.get(i).jiaruDate;
                int data= (int) ((System.currentTimeMillis()-time)/(1000*60*60));
                if(data>24){
                    holder.tv_datatime_adapter.setText((int)data/24+"天前读过");
                }else{
                    if(data==0){
                        holder.tv_datatime_adapter.setText("刚刚读过");
                    }else{
                        holder.tv_datatime_adapter.setText(data+"小时前读过");
                    }
                }
            }
            return view;
        }
    }
    class ShujiaHolder{
        ImageView iv_shujia_adapter;
        TextView tv_name_adapter;
        TextView tv_state_adapter;
        TextView tv_lastchart_adapter;
        TextView tv_currentchart_adapter;
        TextView tv_jiarudate_adapter;
        TextView tv_auther_adapter;
        TextView tv_longchart_adapter;
        TextView tv_datatime_adapter;

        private View initview(){
            View view = ShujiaPresenter.this.view.inflater.inflate(R.layout.shujiaa_adapter_view, null);
            iv_shujia_adapter= (ImageView) view.findViewById(R.id.iv_shujia_adapter);
            tv_name_adapter= (TextView) view.findViewById(R.id.tv_name_adapter);
            tv_state_adapter= (TextView) view.findViewById(R.id.tv_state_adapter);
            tv_lastchart_adapter= (TextView) view.findViewById(R.id.tv_lastchart_adapter);
            tv_currentchart_adapter= (TextView) view.findViewById(R.id.tv_currentchart_adapter);
            tv_jiarudate_adapter= (TextView) view.findViewById(R.id.tv_jiarudate_adapter);
            tv_auther_adapter= (TextView) view.findViewById(R.id.tv_auther_adapter);
            tv_longchart_adapter= (TextView) view.findViewById(R.id.tv_longchart_adapter);
            tv_datatime_adapter= (TextView) view.findViewById(R.id.tv_datatime_adapter);
            return view;
        }

    }
}
