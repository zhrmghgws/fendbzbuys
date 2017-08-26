package com.hxd.fendbzbuys.moduler.sousu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.domain.HotWordInfo;
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
    @BindView(R.id.iv_back_sousuo)
    ImageView iv_back_sousuo;
    @BindView(R.id.et_sousuo)
    EditText et_sousuo;
    @BindView(R.id.tv_title_sousuo)
    TextView tv_title_sousuo;
    @BindView(R.id.tv_hotchange_sousuo)
    TextView tv_hotchange_sousuo;
    @BindView(R.id.fl_sousuo)
    FlowLayout fl_sousuo;
    LayoutInflater mInflate;
    List<String> hotWords;
    public static void invoke(Activity activity){
        activity.startActivity(new Intent(activity,SousuoActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflate=LayoutInflater.from(this);
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
                Log.e("余数", ":::::::: "+yushu );
                Log.e("商", ":::::::: "+count );
                setHotWords(start);
            }
        });
    }
    private void setHotWords(int flat){
        if(count<=1 &&yushu==0){
            for (int i = 0; i < hotWords.size(); i++) {

                TextView tv = (TextView) mInflate.inflate(
                        R.layout.sousou_hotword, fl_sousuo,false);
                tv.setText(hotWords.get(i));
                final String str = tv.getText().toString();
                //点击事件
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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

                        }
                    });
                    fl_sousuo.addView(tv);//添加到父View
                }
        }


    }
    @OnClick(R.id.tv_hotchange_sousuo)void changeHot(){
        fl_sousuo.removeAllViews();
        start=start+1;
        if(start<count-1){
            setHotWords(start);
        }else if(start==count-1){
            for (int j = 0+start*5; j < 5+start*5+yushu; j++) {

                TextView tv = (TextView) mInflate.inflate(
                        R.layout.sousou_hotword, fl_sousuo,false);
                tv.setText(hotWords.get(j));
                final String str = tv.getText().toString();
                //点击事件
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                fl_sousuo.addView(tv);//添加到父View
            }
        }else if(start>count-1){
            start=0;
            setHotWords(start);
        }
    }
    @Override
    public int getLayoutID() {
        return R.layout.activity_sousuo;
    }
}
