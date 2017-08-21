package com.hxd.fendbzbuys.moduler.laon_moduler;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.ShuDetailActivity;

import java.util.List;


/**
 * Created by lichao on 16/9/21.
 */
public class PaihangPresenter extends BasePresenter<PaihangFragment> {
    int currentPoint =1;
    boolean man=true;
    listviewAdapter adapter;
    private List<BangdanBooksBean> maleZuireyue;
    private List<BangdanBooksBean> maleZuirezong;
    private List<BangdanBooksBean> maleZuirezhou;
    private List<BangdanBooksBean> maleQianlizhou;
    private List<BangdanBooksBean> maleQianliyue;
    private List<BangdanBooksBean> maleQianlizong;
    private List<BangdanBooksBean> maleWanjiezong;
    private List<BangdanBooksBean> maleWanjieyue;
    private List<BangdanBooksBean> maleWanjiezhou;
    private List<BangdanBooksBean> femalWanjiezhou;
    private List<BangdanBooksBean> femalWanjieyue;
    private List<BangdanBooksBean> femalWanjiezong;
    private List<BangdanBooksBean> femaleQianlizong;
    private List<BangdanBooksBean> femalQianliyue;
    private List<BangdanBooksBean> femalQianlizhou;
    private List<BangdanBooksBean> femaleZuirezhou;
    private List<BangdanBooksBean> femalZuireyue;
    private List<BangdanBooksBean> femaleZuirezong;
    List<BangdanBooksBean> data;
    public PaihangPresenter(PaihangFragment view) {
        super(view);
        maleZuireyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(1000),BangdanBooksBeanDao.Properties.Id.lt(2000)).list();
        maleZuirezong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(2000),BangdanBooksBeanDao.Properties.Id.lt(3000)).list();
        maleZuirezhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(3000),BangdanBooksBeanDao.Properties.Id.lt(4000)).list();
        maleQianlizhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(4000),BangdanBooksBeanDao.Properties.Id.lt(5000)).list();
        maleQianliyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(5000),BangdanBooksBeanDao.Properties.Id.lt(6000)).list();
        maleQianlizong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(6000),BangdanBooksBeanDao.Properties.Id.lt(7000)).list();
        maleWanjiezong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(7000),BangdanBooksBeanDao.Properties.Id.lt(8000)).list();
        maleWanjieyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(8000),BangdanBooksBeanDao.Properties.Id.lt(9000)).list();
        maleWanjiezhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(9000),BangdanBooksBeanDao.Properties.Id.lt(10000)).list();
        femalWanjiezhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(10000),BangdanBooksBeanDao.Properties.Id.lt(11000)).list();
        femalWanjieyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(11000),BangdanBooksBeanDao.Properties.Id.lt(12000)).list();
        femalWanjiezong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(12000),BangdanBooksBeanDao.Properties.Id.lt(13000)).list();
        femaleQianlizong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(13000),BangdanBooksBeanDao.Properties.Id.lt(14000)).list();
        femalQianliyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(14000),BangdanBooksBeanDao.Properties.Id.lt(15000)).list();
        femalQianlizhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(15000),BangdanBooksBeanDao.Properties.Id.lt(16000)).list();
        femaleZuirezhou= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(16000),BangdanBooksBeanDao.Properties.Id.lt(17000)).list();
        femalZuireyue= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(17000),BangdanBooksBeanDao.Properties.Id.lt(18000)).list();
        femaleZuirezong= DaoManager.getInstance().getBangdanBooksBeanDao().queryBuilder().where(BangdanBooksBeanDao.Properties.Id.ge(18000),BangdanBooksBeanDao.Properties.Id.lt(19000)).list();
        data= maleZuirezong;
    }

    @Override
    public void initData() {
        if(adapter==null){
            adapter=new listviewAdapter();
        }
        view.lv_paihang.setAdapter(adapter);
        view.lv_paihang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShuDetailActivity.invoke(PaihangPresenter.this.view.getActivity(),data.get(i));
            }
        });
    }
    class listviewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
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
        public View getView(int i, View v, ViewGroup viewGroup) {
            PaihangHolder holder;
            if(v==null){
                holder=new PaihangHolder();
                v=holder.initView();
                v.setTag(holder);
            }else{
                holder= (PaihangHolder) v.getTag();
            }
            holder.tv_shu_name_paihang.setText(data.get(i).title);
            holder.tv_shu_miaoshu_paihang.setText(data.get(i).shortIntro);
            holder.tv_shu_auther_paihang.setText(data.get(i).author);
            holder.tv_liang_paihang.setText(data.get(i).latelyFollower);
            Glide.with(view).load(Common.getIonicCommonUrl()+data.get(i).cover).into(holder.shu_icon_paihang);
            return v;
        }
    }
    class PaihangHolder{
        ImageView shu_icon_paihang;
        TextView tv_shu_name_paihang;
        TextView tv_shu_miaoshu_paihang;
        TextView tv_shu_auther_paihang;
        TextView tv_liang_paihang;
        private View initView(){
            View view=PaihangPresenter.this.view.inflater.inflate(R.layout.paihang_list,null);
            shu_icon_paihang= (ImageView) view.findViewById(R.id.shu_icon_paihang);
            tv_shu_name_paihang= (TextView) view.findViewById(R.id.tv_shu_name_paihang);
            tv_shu_miaoshu_paihang= (TextView) view.findViewById(R.id.tv_shu_miaoshu_paihang);
            tv_shu_auther_paihang= (TextView) view.findViewById(R.id.tv_shu_auther_paihang);
            tv_liang_paihang= (TextView) view.findViewById(R.id.tv_liang_paihang);
            return view;
        }
    }
    private int bgcolor_def=view.getResources().getColor(R.color.scrim_16000000);
    private int txcolor_def=view.getResources().getColor(R.color.color_666666);
    private void setView(int state){
        switch (state){
            case 1:
                view.rl_fengyunzong_paihang.setBackgroundColor(bgcolor_def);
                view.view_fengyunzong.setVisibility(View.GONE);
                view.tv_fengyunzong.setTextColor(txcolor_def);
                view.tv_fengyunzong.setTextSize(14);
                break;
            case 2:
                view.rl_fengyunyue_paihang.setBackgroundColor(bgcolor_def);
                view.view_fengyunyue.setVisibility(View.GONE);
                view.tv_fengyunyue.setTextColor(txcolor_def);
                view.tv_fengyunyue.setTextSize(14);
                break;
            case 3:
                view.rl_fengyunzhou_paihang.setBackgroundColor(bgcolor_def);
                view.view_fengyunzhou.setVisibility(View.GONE);
                view.tv_fengyunzhou.setTextColor(txcolor_def);
                view.tv_fengyunzhou.setTextSize(14);
                break;
            case 4:
                view.rl_zonghengzong_paihang.setBackgroundColor(bgcolor_def);
                view.view_zonghengzong.setVisibility(View.GONE);
                view.tv_zonghengzong.setTextColor(txcolor_def);
                view.tv_zonghengzong.setTextSize(14);
                break;
            case 5:
                view.rl_zonghengyue_paihang.setBackgroundColor(bgcolor_def);
                view.view_zonghengyue.setVisibility(View.GONE);
                view.tv_zonghengyue.setTextColor(txcolor_def);
                view.tv_zonghengyue.setTextSize(14);
                break;
            case 6:
                view.rl_zonghengzhou_paihang.setBackgroundColor(bgcolor_def);
                view.view_zonghengzhou.setVisibility(View.GONE);
                view.tv_zonghengzhou.setTextColor(txcolor_def);
                view.tv_zonghengzhou.setTextSize(14);
                break;
            case 7:
                view.rl_wanjiezong_paihang.setBackgroundColor(bgcolor_def);
                view.view_wanjiezong.setVisibility(View.GONE);
                view.tv_wanjiezong.setTextColor(txcolor_def);
                view.tv_wanjiezong.setTextSize(14);
                break;
            case 8:
                view.rl_wanjieyue_paihang.setBackgroundColor(bgcolor_def);
                view.view_wanjieyue.setVisibility(View.GONE);
                view.tv_wanjieyue.setTextColor(txcolor_def);
                view.tv_wanjieyue.setTextSize(14);
                break;
            case 9:
                view.rl_wanjiezhou_paihang.setBackgroundColor(bgcolor_def);
                view.view_wanjiezhou.setVisibility(View.GONE);
                view.tv_wanjiezhou.setTextColor(txcolor_def);
                view.tv_wanjiezhou.setTextSize(14);
                break;
        }




    }
    public void wanjiezhou() {
        setView(currentPoint);
        view.rl_wanjiezhou_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_wanjiezhou.setVisibility(View.VISIBLE);
        view.tv_wanjiezhou.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_wanjiezhou.setTextSize(16);
        currentPoint=9;
        if(man){
            data=maleWanjiezhou;
        }else {
            data=femalWanjiezhou;
        }
        adapter.notifyDataSetChanged();
    }

    public void wanjieyue() {
        setView(currentPoint);
        view.rl_wanjieyue_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_wanjieyue.setVisibility(View.VISIBLE);
        view.tv_wanjieyue.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_wanjieyue.setTextSize(16);
        currentPoint=8;
        if(man){
            data=maleWanjieyue;
        }else {
            data=femalWanjieyue;
        }
        adapter.notifyDataSetChanged();
    }

    public void wanjiezong() {
        setView(currentPoint);
        view.rl_wanjiezong_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_wanjiezong.setVisibility(View.VISIBLE);
        view.tv_wanjiezong.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_wanjiezong.setTextSize(16);
        currentPoint=7;
        if(man){
            data=maleWanjiezong;
        }else {
            data=femalWanjiezong;
        }
        adapter.notifyDataSetChanged();
    }

    public void fengyunzong() {
        setView(currentPoint);
        currentPoint=1;
        view.rl_fengyunzong_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_fengyunzong.setVisibility(View.VISIBLE);
        view.tv_fengyunzong.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_fengyunzong.setTextSize(16);
        if(man){
            data=maleZuirezong;
        }else {
            data=femaleZuirezong;
        }
        adapter.notifyDataSetChanged();
    }

    public void fengyunyue() {
        setView(currentPoint);
        currentPoint=2;
        view.rl_fengyunyue_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_fengyunyue.setVisibility(View.VISIBLE);
        view.tv_fengyunyue.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_fengyunyue.setTextSize(16);
        if(man){
            data=maleZuireyue;
        }else {
            data=femalZuireyue;
        }
        adapter.notifyDataSetChanged();
    }

    public void fengyunzhou() {
        setView(currentPoint);
        currentPoint=3;
        view.rl_fengyunzhou_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_fengyunzhou.setVisibility(View.VISIBLE);
        view.tv_fengyunzhou.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_fengyunzhou.setTextSize(16);
        if(man){
            data=maleZuirezhou;
        }else {
            data=maleZuirezhou;
        }
        adapter.notifyDataSetChanged();
    }

    public void zonghengzong() {
        setView(currentPoint);
        currentPoint=4;
        view.rl_zonghengzong_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_zonghengzong.setVisibility(View.VISIBLE);
        view.tv_zonghengzong.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_zonghengzong.setTextSize(16);
        if(man){
            data=maleQianlizhou;
        }else {
            data=femaleQianlizong;
        }
        adapter.notifyDataSetChanged();
    }

    public void zonghengyue() {
        setView(currentPoint);
        currentPoint=5;
        view.rl_zonghengyue_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_zonghengyue.setVisibility(View.VISIBLE);
        view.tv_zonghengyue.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_zonghengyue.setTextSize(16);
        if(man){
            data=maleQianliyue;
        }else {
            data=femalQianliyue;
        }
        adapter.notifyDataSetChanged();
    }

    public void zonghengzhou() {
        setView(currentPoint);
        currentPoint=6;
        view.rl_zonghengzhou_paihang.setBackgroundColor(Color.parseColor("#ffffff"));
        view.view_zonghengzhou.setVisibility(View.VISIBLE);
        view.tv_zonghengzhou.setTextColor(Color.parseColor("#03b5ff"));
        view.tv_zonghengzhou.setTextSize(16);
        if(man){
            data=maleQianlizong;
        }else {
            data=femalQianlizhou;
        }
        adapter.notifyDataSetChanged();
    }

    public void nanpaihang() {

        nanOrnvChange();
    }

    public void nvpaihang() {

        nanOrnvChange();
    }
    private void nanOrnvChange(){
        switch (currentPoint){
            case 1:
                if(man){
                    data=maleZuirezong;
                }else {
                    data=femaleZuirezong;
                }
                adapter.notifyDataSetChanged();
                break;
            case 2:
                if(man){
                    data=maleZuireyue;
                }else {
                    data=femalZuireyue;
                }
                adapter.notifyDataSetChanged();
                break;
            case 3:
                if(man){
                    data=maleZuirezhou;
                }else {
                    data=femaleZuirezhou;
                }
                adapter.notifyDataSetChanged();
                break;
            case 4:
                if(man){
                    data=maleQianlizhou;
                }else {
                    data=femaleQianlizong;
                }
                adapter.notifyDataSetChanged();
                break;
            case 5:
                if(man){
                    data=maleQianliyue;
                }else {
                    data=femalQianliyue;
                }
                adapter.notifyDataSetChanged();
                break;
            case 6:
                if(man){
                    data=maleQianlizong;
                }else {
                    data=femalQianlizhou;
                }
                adapter.notifyDataSetChanged();
                break;
            case 7:
                if(man){
                    data=maleWanjiezong;
                }else {
                    data=femalWanjiezong;
                }
                adapter.notifyDataSetChanged();
                break;
            case 8:
                if(man){
                    data=maleWanjieyue;
                }else {
                    data=femalWanjieyue;
                }
                adapter.notifyDataSetChanged();
                break;
            case 9:
                if(man){
                    data=maleWanjiezhou;
                }else {
                    data=femalWanjiezhou;
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
