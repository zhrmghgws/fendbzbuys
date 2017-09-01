package com.hxd.fendbzbuys.moduler.fenlei_moduler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BaseFragment;
import com.hxd.fendbzbuys.domain.FenleiBookTypeInfo;
import com.hxd.fendbzbuys.domain.FenleiBooksInfo;
import com.hxd.fendbzbuys.domain.StatisticsInfo;
import com.hxd.fendbzbuys.moduler.fenlei_detail_moduler.FenLeiDetailActivity;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.ui.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 16/9/21.
 */
public class FenleiFragment extends BaseFragment<FenleiPresenter> {

    @BindView(R.id.gv_xiaoshuo_fenlei)
    MyGridView gv_xiaoshuo_fenlei;
    @BindView(R.id.gv_chuban_fenlei)
    MyGridView gv_chuban_fenlei;
    @BindView(R.id.rl_dataerror_fenlei)
    RelativeLayout rl_dataerror_fenlei;
    @BindView(R.id.sv_fenlei)
    ScrollView sv_fenlei;

    List<FenleiBookTypeInfo.MaleEntity> maleOrFemFenleiList;
    List<FenleiBookTypeInfo.MaleEntity> pressFenleiList;
    FenleiBookTypeInfo statisticsInfos;
    FLxiaoShuoAdapter xiaoshuoAdapter;
    FLxiaoShuoAdapter chubanAdapter;
    @Override
    public View setConentView() {
        View view = inflater.inflate(R.layout.fenlei_fragment, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
    }

    @Override
    public void onResume() {
        super.onResume();
        isNan= Constant.isNan;
        Log.e("分类::", ":::::::::onResume: " );

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isNan= Constant.isNan;
            if(isNan){
                nanPaihang();
            }else{
                nvPaihang();
            }
        }
    }

    private void initView(){
        gv_xiaoshuo_fenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isNan){
                    FenLeiDetailActivity.invoke(FenleiFragment.this.getActivity(),"male","hot",maleOrFemFenleiList.get(i).name);
                }else{
                    FenLeiDetailActivity.invoke(FenleiFragment.this.getActivity(),"female","hot",maleOrFemFenleiList.get(i).name);
                }
            }
        });
        gv_chuban_fenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FenLeiDetailActivity.invoke(FenleiFragment.this.getActivity(),"press","hot",pressFenleiList.get(i).name);
            }
        });
    }
    @OnClick(R.id. rl_dataerror_fenlei)void errorClick(){
        initDate();
    }
    private void initDate(){
        if(Constant.statisticsInfo==null){
            FBNetwork.getInstance().getStatistics().subscribe(new ProcressSubsciber<FenleiBookTypeInfo>(false,false) {
                @Override
                public void onNext(FenleiBookTypeInfo statisticsInfo) {
                    super.onNext(statisticsInfo);
                    statisticsInfos=statisticsInfo;
                    if(FenleiFragment.this!=null&&FenleiFragment.this.isVisible()){
                        sv_fenlei.setVisibility(View.VISIBLE);
                        rl_dataerror_fenlei.setVisibility(View.GONE);
                        pressFenleiList=statisticsInfo.press;
                        setViewData();
                    }

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    if(FenleiFragment.this!=null&& FenleiFragment.this.isVisible()){
                        sv_fenlei.setVisibility(View.GONE);
                        rl_dataerror_fenlei.setVisibility(View.VISIBLE);
                    }

                }
            });
        }else{
            if(FenleiFragment.this!=null&&FenleiFragment.this.isVisible()){
                statisticsInfos=Constant.statisticsInfo;
                sv_fenlei.setVisibility(View.VISIBLE);
                rl_dataerror_fenlei.setVisibility(View.GONE);
                pressFenleiList=Constant.statisticsInfo.press;
                setViewData();
            }
        }
    }

    private void setViewData() {
        maleOrFemFenleiList=statisticsInfos.male;
        if(xiaoshuoAdapter==null){
            xiaoshuoAdapter=new FLxiaoShuoAdapter(maleOrFemFenleiList,true);
            gv_xiaoshuo_fenlei.setAdapter(xiaoshuoAdapter);
        }
        xiaoshuoAdapter.notifyDataSetChanged();
        if(chubanAdapter==null){
            chubanAdapter=new FLxiaoShuoAdapter(pressFenleiList,false);
            gv_chuban_fenlei.setAdapter(chubanAdapter);
        }
        chubanAdapter.notifyDataSetChanged();
        initView();
    }

    @Override
    public FenleiPresenter creatPresenter() {

        return new FenleiPresenter(this);
    }
    boolean isNan;
    public void nanPaihang() {
        maleOrFemFenleiList=statisticsInfos.male;
        xiaoshuoAdapter.notifyDataSetChanged();
        isNan=true;
    }

    public void nvPaihang() {
        maleOrFemFenleiList=statisticsInfos.female;
        xiaoshuoAdapter.notifyDataSetChanged();
        isNan=false;
    }

    class FLxiaoShuoAdapter extends BaseAdapter{
        boolean isxiaoshuo;
        List<FenleiBookTypeInfo.MaleEntity> list;
        public FLxiaoShuoAdapter(List<FenleiBookTypeInfo.MaleEntity> manylist,boolean xiaoshuo){
            list=manylist;
            isxiaoshuo=xiaoshuo;
        }
        @Override
        public int getCount() {
            if(isxiaoshuo){
                return maleOrFemFenleiList.size();
            }else{
                return list.size();
            }
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
            FLHolder holder;
            if(view==null){
                holder=new FLHolder();
                view=holder.initView();
                view.setTag(holder);
            }else{
                holder= (FLHolder) view.getTag();
            }
            if(isxiaoshuo){
                holder.tv_fenlei_name_item.setText(maleOrFemFenleiList.get(i).name);
                holder.tv_fenlei_count_item.setText("共"+maleOrFemFenleiList.get(i).bookCount+"本");
            }else{
                holder.tv_fenlei_name_item.setText(list.get(i).name);
                holder.tv_fenlei_count_item.setText("共"+list.get(i).bookCount+"本");
            }
            return view;
        }
    }
    class FLHolder{
         TextView tv_fenlei_name_item;
         TextView tv_fenlei_count_item;
        private  View initView(){
            View view=View.inflate(FenleiFragment.this.getActivity(),R.layout.item_adapter_fenlei,null);
            tv_fenlei_name_item= (TextView) view.findViewById(R.id.tv_fenlei_name_item);
            tv_fenlei_count_item= (TextView) view.findViewById(R.id.tv_fenlei_count_item);
            return view;
        }
    }
}
