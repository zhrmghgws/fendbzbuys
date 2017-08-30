package com.hxd.fendbzbuys.moduler.sousu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.domain.AutherBooksList;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.HotWordInfo;
import com.hxd.fendbzbuys.domain.MohuSousuInfo;
import com.hxd.fendbzbuys.domain.SousuoHistoryBean;
import com.hxd.fendbzbuys.domain.WanZhengSousuInfo;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.ShuDetailActivity;
import com.hxd.fendbzbuys.moduler.shudetail_moduler.ShuDetailPresenter;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;
import com.hxd.fendbzbuys.ui.FlowLayout;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/8/25.
 */

public class SousuoActivity extends MVPBaseActivity<SousuoPresenter> {
    @BindView(R.id.lv_mohukeywords_sousuo)
    ListView lv_mohukeywords_sousuo;
    @BindView(R.id.lv_history)
    ListView lv_history;
    @BindView(R.id.lv_wanzheng_sousuo)
    ListView lv_wanzheng_sousuo;
    @BindView(R.id.rl_back_sousuo)
    RelativeLayout rl_back_sousuo;
    @BindView(R.id.iv_historyhint_sousuo)
    ImageView iv_historyhint_sousuo;
    @BindView(R.id.et_sousuo)
    EditText et_sousuo;
    @BindView(R.id.rl_title_sousuo)
    RelativeLayout rl_title_sousuo;
    @BindView(R.id.tv_hotchange_sousuo)
    TextView tv_hotchange_sousuo;
    @BindView(R.id.fl_sousuo)
    FlowLayout fl_sousuo;
    @BindView(R.id.rl_content_sousuo)
    RelativeLayout rl_content_sousuo;
    @BindView(R.id.rl_clearinput_sousuo)
    RelativeLayout rl_clearinput_sousuo;
    LayoutInflater mInflate;
    List<String> hotWords;
    List<SousuoHistoryBean> hisKeywords;
    public List<WanZhengSousuInfo.BooksEntity> books;
    MohuAdapter mohuAdapter;
    WanzhengAdapter wanZadapter;
    HistroyAdapter historyAdapter;

    public static void invoke(Activity activity){
        activity.startActivity(new Intent(activity,SousuoActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflate=LayoutInflater.from(this);
        initView();
        hisKeywords=DaoManager.getInstance().getSousuoHistoryBeanDao().loadAll();
        if(historyAdapter==null){
            historyAdapter=new HistroyAdapter();
            lv_history.setAdapter(historyAdapter);
            historyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public SousuoPresenter creatPresenter() {
        return new SousuoPresenter(this);
    }

    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return false;
    }
    int count;
    int yushu;
    int start=0;
    @Override
    public void init() {
        FBNetwork.getInstance().getHotWord().subscribe(new ProcressSubsciber<HotWordInfo>(false,false) {
            @Override
            public void onNext(HotWordInfo hotWordInfo) {
                super.onNext(hotWordInfo);
                hotWords=hotWordInfo.hotWords;
                count=hotWords.size()/5;
                yushu=hotWords.size()%5;
                Log.e("余数", ":::::::: "+3/5 );
                Log.e("商", ":::::::: "+3%5 );
                setHotWords(start);
            }
        });
    }

    private void initView(){
        lv_mohukeywords_sousuo.setVisibility(View.GONE);
        lv_wanzheng_sousuo.setVisibility(View.GONE);
        et_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_SEARCH){
                    String key=et_sousuo.getText().toString().trim();
                    if(!TextUtils.isEmpty(key)){
                        sousuo();
                    }
                }
                return false;
            }
        });
        et_sousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("text", ":::::: "+charSequence );
                String keyword=et_sousuo.getText().toString().trim();
                if(TextUtils.isEmpty(keyword)){
                    rl_clearinput_sousuo.setVisibility(View.GONE);
                    lv_mohukeywords_sousuo.setVisibility(View.GONE);
                    lv_wanzheng_sousuo.setVisibility(View.GONE);
                    rl_content_sousuo.setVisibility(View.VISIBLE);
                    isshezhi=false;
                }else{
                    Log.e("text", ":::::不为空搜索 " +keyword);
                    rl_clearinput_sousuo.setVisibility(View.VISIBLE);
                    if(lv_wanzheng_sousuo.getVisibility()!=View.VISIBLE && !isshezhi){
                        mohuSousuo(keyword);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lv_wanzheng_sousuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BangdanBooksBean bookinfo=new BangdanBooksBean();
                bookinfo.shortIntro=books.get(i).shortIntro;
                bookinfo.latelyFollower=books.get(i).latelyFollower+"";
                bookinfo.cover=books.get(i).cover;
                bookinfo.bookID=books.get(i)._id;
                bookinfo.title=books.get(i).title;
                bookinfo.author=books.get(i).author;
                bookinfo.site=books.get(i).site;
                ShuDetailActivity.invoke(SousuoActivity.this,bookinfo);
            }
        });
        lv_mohukeywords_sousuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setTextAndSous(hotWords.get(i));
            }
        });
        lv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               setTextAndSous(hisKeywords.get(hisKeywords.size()-1-i).keywords);
            }
        });
    }
    boolean isshezhi=false;
    private void setTextAndSous(String keyword){
        isshezhi=true;
        et_sousuo.setText(keyword);
        wanZhengSs(keyword);
    }
    class MohuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return hotWords.size();
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
            MoHuHolder moHuHolder;
            if(view==null){
                moHuHolder=new MoHuHolder();
                view=moHuHolder.initView();
                view.setTag(moHuHolder);
            }else{
                moHuHolder= (MoHuHolder) view.getTag();
            }
            moHuHolder.tv_mohusousuo.setText(hotWords.get(i));
            return view;
        }
    }
    class MoHuHolder{
        ImageView iv_mohusousuo;
        TextView tv_mohusousuo;
        private View initView(){
            View view=View.inflate(SousuoActivity.this,R.layout.muhusousuo_adapter,null);
            iv_mohusousuo= (ImageView) view.findViewById(R.id.iv_mohusousuo);
            tv_mohusousuo= (TextView) view.findViewById(R.id.tv_mohusousuo);
            return view;
        }
    }
    class HistroyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return hisKeywords.size();
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
            HistoryHolder moHuHolder;
            if(view==null){
                moHuHolder=new HistoryHolder();
                view=moHuHolder.initView();
                view.setTag(moHuHolder);
            }else{
                moHuHolder= (HistoryHolder) view.getTag();
            }
            moHuHolder.tv_histrorysousuo.setText(hisKeywords.get(hisKeywords.size()-1-i).keywords);
            return view;
        }
    }
    class HistoryHolder{
        ImageView iv_histrorysousuo;
        TextView tv_histrorysousuo;
        private View initView(){
            View view=View.inflate(SousuoActivity.this,R.layout.historysousuo_adapter,null);
            iv_histrorysousuo= (ImageView) view.findViewById(R.id.iv_histrorysousuo);
            tv_histrorysousuo= (TextView) view.findViewById(R.id.tv_histrorysousuo);
            return view;
        }
    }
    class WanzhengAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return books.size();
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
            WanzhengHolder holder2 = null;
            if (view == null) {
                holder2 = new WanzhengHolder();
                view = holder2.initView();
                view.setTag(holder2);
            } else {
                holder2 = (WanzhengHolder) view.getTag();
            }
            Glide.with(SousuoActivity.this).load(Common.getIonicCommonUrl() + books.get(i).cover).into(holder2.iv_icon_shudetail_item);
            holder2.tv_name_shudetail_item.setText(books.get(i).title);
            holder2.tv_auther_shudetail_item.setText(books.get(i).author);
            holder2.tv_state_shudetail_item.setText(books.get(i).lastChapter);
            holder2.tv_shorttitle_shudetail_item.setText(books.get(i).shortIntro);
            return view;

        }
    }

    class WanzhengHolder {
        ImageView iv_icon_shudetail_item;
        TextView tv_name_shudetail_item;
        TextView tv_auther_shudetail_item;
        TextView tv_state_shudetail_item;
        TextView tv_shorttitle_shudetail_item;

        private View initView() {
            View view = View.inflate(SousuoActivity.this, R.layout.shudetail_listitem, null);
            iv_icon_shudetail_item = (ImageView) view.findViewById(R.id.iv_icon_shudetail_item);
            tv_name_shudetail_item = (TextView) view.findViewById(R.id.tv_name_shudetail_item);
            tv_auther_shudetail_item = (TextView) view.findViewById(R.id.tv_auther_shudetail_item);
            tv_state_shudetail_item = (TextView) view.findViewById(R.id.tv_state_shudetail_item);
            tv_shorttitle_shudetail_item = (TextView) view.findViewById(R.id.tv_shorttitle_shudetail_item);
            return view;
        }
    }

    private void wanZhengSs(String keyword){
        FBNetwork.getInstance().wanZhengSousuo(keyword).subscribe(new ProcressSubsciber<WanZhengSousuInfo>(false,false) {
            @Override
            public void onNext(WanZhengSousuInfo wanZhengSousuInfo) {
                super.onNext(wanZhengSousuInfo);
                lv_wanzheng_sousuo.setVisibility(View.VISIBLE);
                lv_mohukeywords_sousuo.setVisibility(View.GONE);
                rl_content_sousuo.setVisibility(View.GONE);
                books=wanZhengSousuInfo.books;
                if(wanZhengSousuInfo.books.size()>0){
                    if(wanZadapter==null){
                        wanZadapter=new WanzhengAdapter();
                        lv_wanzheng_sousuo.setAdapter(wanZadapter);
                    }
                    wanZadapter.notifyDataSetChanged();
                    insterHistory(keyword);
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
    private void insterHistory(String keyword){
        SousuoHistoryBean history=new SousuoHistoryBean();
        history.keywords=keyword;
        DaoManager.getInstance().getSousuoHistoryBeanDao().insertOrReplace(history);
        hisKeywords=DaoManager.getInstance().getSousuoHistoryBeanDao().loadAll();
        if(hisKeywords.size()>100){
            DaoManager.getInstance().getSousuoHistoryBeanDao().deleteAll();
        }
        historyAdapter.notifyDataSetChanged();
    }
    private void mohuSousuo(String keywords){
        Log.e("mohusous", ":::::执行 " );
        FBNetwork.getInstance().mohuSousuo(keywords).subscribe(new ProcressSubsciber<MohuSousuInfo>(false,false) {
            @Override
            public void onNext(MohuSousuInfo mohuSousuInfo) {
                super.onNext(mohuSousuInfo);
                hotWords=mohuSousuInfo.keywords;
                if(mohuSousuInfo.keywords.size()>0){
                    Log.e("mohusous", ":::::搜索 " +hotWords.size());
                    lv_mohukeywords_sousuo.setVisibility(View.VISIBLE);
                    rl_content_sousuo.setVisibility(View.GONE);
                    rl_clearinput_sousuo.setVisibility(View.VISIBLE);
                    if(mohuAdapter==null){
                        mohuAdapter=new MohuAdapter();
                        lv_mohukeywords_sousuo.setAdapter(mohuAdapter);
                    }
                    mohuAdapter.notifyDataSetChanged();
                }

            }
        });
    }
    private void setHotWords(int flat){
        if(count==0 &&yushu>0){
            for (int i = 0; i < hotWords.size(); i++) {

                TextView tv = (TextView) mInflate.inflate(
                        R.layout.sousou_hotword, fl_sousuo,false);
                tv.setText(hotWords.get(i));
                final String str = tv.getText().toString();
                //点击事件
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       setTextAndSous(str);
                    }
                });
                fl_sousuo.addView(tv);//添加到父View
            }
        }else{
                for (int j = 0+flat*5; j < 5+flat*5; j++) {

                    TextView tv = (TextView) mInflate.inflate(
                            R.layout.sousou_hotword, fl_sousuo,false);
                    tv.setText(hotWords.get(j));
                    final String str = tv.getText().toString();
                    //点击事件
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setTextAndSous(str);
                        }
                    });
                    fl_sousuo.addView(tv);//添加到父View
                }
        }


    }
    @OnClick(R.id.iv_historyhint_sousuo)void clearHistory(){
        DaoManager.getInstance().getSousuoHistoryBeanDao().deleteAll();
        hisKeywords.clear();
        historyAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.rl_back_sousuo)void close(){
        finish();
    }
    @OnClick(R.id.rl_clearinput_sousuo)void clearInput(){
        if(TextUtils.isEmpty(et_sousuo.getText())){
            lv_wanzheng_sousuo.setVisibility(View.GONE);
            rl_content_sousuo.setVisibility(View.VISIBLE);
            isshezhi=false;
            et_sousuo.setText("");
        }
    }
    @OnClick(R.id.rl_title_sousuo)void sousuo(){
        String keyword=et_sousuo.getText().toString().trim();
        if(!TextUtils.isEmpty(keyword)){
            wanZhengSs(keyword);
        }
    }
    @OnClick(R.id.tv_hotchange_sousuo)void changeHot(){
        fl_sousuo.removeAllViews();
        start=start+1;
        if(start<count-1){
            setHotWords(start);
        }else if(start==count-1 &&yushu!=0){

            for (int j = 0+start*5; j < 5+yushu; j++) {

                TextView tv = (TextView) mInflate.inflate(
                        R.layout.sousou_hotword, fl_sousuo,false);
                tv.setText(hotWords.get(j));
                final String str = tv.getText().toString();
                //点击事件
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTextAndSous(str);
                    }
                });
                fl_sousuo.addView(tv);//添加到父View
            }
        }else {
            start=0;
            setHotWords(start);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_sousuo;
    }
}
