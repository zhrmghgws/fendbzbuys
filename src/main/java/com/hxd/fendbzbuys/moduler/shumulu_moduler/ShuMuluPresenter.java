package com.hxd.fendbzbuys.moduler.shumulu_moduler;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.BasePresenter;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

import java.util.List;

/**
 * Created by lichao on 17/7/31.
 */

public class ShuMuluPresenter extends BasePresenter<ShuMuluActivity> {
    private boolean daoxu=false;
    private  List<BookMuluInfo.ChaptersEntity> muluList;
    private MuluAdapter adapter;
    public ShuMuluPresenter(ShuMuluActivity view) {
        super(view);
    }

    @Override
    public void initData() {
        getBookMuLu();
    }
    private void getBookMuLu(){
        FBNetwork.getInstance().getBookmulu(view.bookSourceId).subscribe(new ProcressSubsciber<BookMuluInfo>(false,false) {
            @Override
            public void onNext(BookMuluInfo httpResult) {
                super.onNext(httpResult);
                muluList=httpResult.chapters;
                if(adapter==null){
                    adapter=new MuluAdapter();
                }
                ShuMuluPresenter.this.view.lv_mulu_shumulu.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void daoxu() {
        if(!daoxu){
            daoxu=true;
            view.tv_daoxu_mulu.setText("正序");
        }else{
            daoxu=false;
            view.tv_daoxu_mulu.setText("倒序");
        }
        adapter.notifyDataSetChanged();
    }

    class MuluAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return muluList.size();
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
            MuluHolder holder=null;
            if(view==null){
                holder=new MuluHolder();
                view=holder.initView();
                view.setTag(holder);
            }else{
                holder= (MuluHolder) view.getTag();
            }
            if(!daoxu){
                holder.tv_mulu_item.setText(muluList.get(i).title);
            }else{
                holder.tv_mulu_item.setText(muluList.get(muluList.size()-1-i).title);
            }
            return view;
        }
    }
    class MuluHolder{
        TextView tv_mulu_item;
        private View initView(){
            View view=View.inflate(ShuMuluPresenter.this.view, R.layout.bookmulu_listitem,null);
            tv_mulu_item= (TextView) view.findViewById(R.id.tv_mulu_item);
            return view;
        }
    }
}
