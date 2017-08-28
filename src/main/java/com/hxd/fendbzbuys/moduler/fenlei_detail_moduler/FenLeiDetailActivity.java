package com.hxd.fendbzbuys.moduler.fenlei_detail_moduler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.domain.AutherBooksList;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.FenleiBooksInfo;
import com.hxd.fendbzbuys.domain.MohuSousuInfo;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.ShuDetailActivity;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.ShuDetailPresenter;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/8/27.
 */

public class FenLeiDetailActivity extends MVPBaseActivity<FenLeiDetailPresenter> {
    @BindView(R.id.tv_back_fenleidetail)
    TextView tv_back_fenleidetail;
    @BindView(R.id.iv_back_fenleideteail)
    ImageView iv_back_fenleideteail;
    @BindView(R.id.tv_title_fenleidetail)
    TextView tv_title_fenleidetail;
    @BindView(R.id.lv_fenleidetail)
    ListView lv_fenleidetail;
    LtAdapter adapter;
    List<FenleiBooksInfo.BooksEntity> books=new ArrayList<>();
    static String grend;
    static String type;
    static String fenleiname;
    int count;
    @OnClick({R.id.iv_back_fenleideteail,R.id.tv_back_fenleidetail})void finishclick(){
        finish();
    }

    public  static void invoke(Activity activity, String male, String hot, String name){
        grend=male;
        type=hot;
        fenleiname=name;
        activity.startActivity(new Intent(activity,FenLeiDetailActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void initView() {
        Log.e("标题", ":::::: "+fenleiname );
        tv_title_fenleidetail.setText(fenleiname);
    }

    @Override
    public FenLeiDetailPresenter creatPresenter() {
        return new FenLeiDetailPresenter(this);
    }

    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return false;
    }
    int start=0;
    @Override
    public void init() {
        initView();
        lv_fenleidetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BangdanBooksBean been=new BangdanBooksBean();
                been.bookID=books.get(i)._id;
                been.title=books.get(i).title;
                been.author=books.get(i).author;
                been.shortIntro=books.get(i).shortIntro;
                been.cover=books.get(i).cover;
                been.site=books.get(i).site;
                been.banned=books.get(i).banned+"";
                been.latelyFollower=books.get(i).latelyFollower+"";
                been.retentionRatio=books.get(i).retentionRatio+"";
                ShuDetailActivity.invoke(FenLeiDetailActivity.this,been);
            }
        });
       getData(start+"",(start+20)+"");
    }
    private void getData(String sta,String lim){
        FBNetwork.getInstance().getFenleiBook(grend,type,fenleiname,sta,lim).subscribe(new ProcressSubsciber<FenleiBooksInfo>(false,false) {
            @Override
            public void onNext(FenleiBooksInfo fenleiBooksInfo) {
                super.onNext(fenleiBooksInfo);
                start=start+fenleiBooksInfo.books.size();
                books.addAll(fenleiBooksInfo.books);
                count=fenleiBooksInfo.total;
                if(adapter==null){
                    adapter=new LtAdapter();
                    lv_fenleidetail.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    MoreHolder moreHolder= new MoreHolder();
    class LtAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return books.size()+1;
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
            if(i==books.size()){
                view =moreHolder.initView();
                getMoreData();
            }else{
                LsHolder holder2 = null;
                if (view == null) {
                    holder2 = new LsHolder();
                    view = holder2.initView();
                    view.setTag(holder2);
                } else {
                    holder2 = (LsHolder) view.getTag();
                }
                if(holder2==null){
                    holder2 = new LsHolder();
                    view = holder2.initView();
                }
                Glide.with(FenLeiDetailActivity.this).load(Common.getIonicCommonUrl() + books.get(i).cover).into(holder2.iv_icon_shudetail_item);
                holder2.tv_name_shudetail_item.setText(books.get(i).title);
                holder2.tv_auther_shudetail_item.setText("作者: "+books.get(i).author);
                holder2.tv_state_shudetail_item.setText(books.get(i).lastChapter);
                holder2.tv_shorttitle_shudetail_item.setText(books.get(i).shortIntro);
            }
            return view;

        }
    }

    class LsHolder {
        LinearLayout ll_shudetail_listitem;
        ImageView iv_icon_shudetail_item;
        TextView tv_name_shudetail_item;
        TextView tv_auther_shudetail_item;
        TextView tv_state_shudetail_item;
        TextView tv_shorttitle_shudetail_item;

        private View initView() {
            View view = View.inflate(FenLeiDetailActivity.this, R.layout.shudetail_listitem, null);
            iv_icon_shudetail_item = (ImageView) view.findViewById(R.id.iv_icon_shudetail_item);
            ll_shudetail_listitem = (LinearLayout) view.findViewById(R.id.ll_shudetail_listitem);
            tv_name_shudetail_item = (TextView) view.findViewById(R.id.tv_name_shudetail_item);
            tv_auther_shudetail_item = (TextView) view.findViewById(R.id.tv_auther_shudetail_item);
            tv_state_shudetail_item = (TextView) view.findViewById(R.id.tv_state_shudetail_item);
            tv_shorttitle_shudetail_item = (TextView) view.findViewById(R.id.tv_shorttitle_shudetail_item);
            ll_shudetail_listitem.setBackgroundColor(Color.argb(0,0,0,0));
            return view;
        }
    }
    class MoreHolder{
        TextView tv_loadmor_fenleidetail;
        private View initView(){
            View view=View.inflate(FenLeiDetailActivity.this,R.layout.fenleidetail_list_bootview,null);
            tv_loadmor_fenleidetail= (TextView) view.findViewById(R.id.tv_loadmor_fenleidetail);
            return view;
        }
    }
    private void getMoreData(){
       int end;
        if(start<count){
            if(start+20<= count){
                end=start+20;
            }else{
                end=count;
            }
            getData(start+"",end+"");
        }else{
            moreHolder.tv_loadmor_fenleidetail.setText("全部加载完毕!");
        }


    }
    @Override
    public int getLayoutID() {
        return R.layout.activity_fenlei_detail;
    }

}
