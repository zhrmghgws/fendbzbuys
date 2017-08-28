package com.hxd.fendbzbuys.moduler.read_moduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.ActionbarAtrribute;
import com.hxd.fendbzbuys.base.MVPBaseActivity;
import com.hxd.fendbzbuys.utils.UIUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lichao on 17/8/1.
 */

public class ReadActivity extends MVPBaseActivity<ReadPresenter> {
    @BindView(R.id.tv_read)
    TextView tv_read;
    @BindView(R.id.tv_download_hint)
    TextView tv_download_hint;
    @BindView(R.id.tv_title_read)
    TextView tv_title_read;
    @BindView(R.id.tv_huanyuan_read)
    TextView tv_huanyuan_read;
    @BindView(R.id.tv_back_read)
    TextView tv_back_read;
    @BindView(R.id.view_process_hover_bright)
    View view_process_hover_bright;
    @BindView(R.id.view_process_hover_textsize)
    View view_process_hover_textsize;
    @BindView(R.id.iv_back_read)
    ImageView iv_back_read;
    @BindView(R.id.iv_textsize_seekbar)
    ImageView iv_textsize_seekbar;
    @BindView(R.id.iv_brightness_seekbar)
    ImageView iv_brightness_seekbar;
    @BindView(R.id.rl_actionbar_read)
    RelativeLayout rl_actionbar_read;
    @BindView(R.id.rl_bottom_read)
    RelativeLayout rl_bottom_read;

    @BindView(R.id.rl_textbj1_read)
    RelativeLayout rl_textbj1_read;
    @BindView(R.id.rl_textbj2_read)
    RelativeLayout rl_textbj2_read;
    @BindView(R.id.rl_textbj3_read)
    RelativeLayout rl_textbj3_read;
    @BindView(R.id.rl_textbj4_read)
    RelativeLayout rl_textbj4_read;
    @BindView(R.id.rl_textbj5_read)
    RelativeLayout rl_textbj5_read;
    @BindView(R.id.rl_textbj6_read)
    RelativeLayout rl_textbj6_read;
    @BindView(R.id.rl_textbj7_read)
    RelativeLayout rl_textbj7_read;
    @BindView(R.id.rl_textbj8_read)
    RelativeLayout rl_textbj8_read;
    @BindView(R.id.rl_textbj9_read)
    RelativeLayout rl_textbj9_read;
    @BindView(R.id.rl_textbj10_read)
    RelativeLayout rl_textbj10_read;
    @BindView(R.id.rl_textbj11_read)
    RelativeLayout rl_textbj11_read;
    @BindView(R.id.rl_textbj12_read)
    RelativeLayout rl_textbj12_read;
    @BindView(R.id.rl_textbj13_read)
    RelativeLayout rl_textbj13_read;
    @BindView(R.id.rl_textbj14_read)
    RelativeLayout rl_textbj14_read;
    @BindView(R.id.rl_textbj15_read)
    RelativeLayout rl_textbj15_read;
    @BindView(R.id.rl_textbj16_read)
    RelativeLayout rl_textbj16_read;
    @BindView(R.id.rl_textbj17_read)
    RelativeLayout rl_textbj17_read;
    @BindView(R.id.rl_textbj20_read)
    RelativeLayout rl_textbj20_read;

    @BindView(R.id.iv_textbj1_read)
    ImageView iv_textbj1_read;
    @BindView(R.id.iv_textbj2_read)
    ImageView iv_textbj2_read;
    @BindView(R.id.iv_textbj3_read)
    ImageView iv_textbj3_read;
    @BindView(R.id.iv_textbj4_read)
    ImageView iv_textbj4_read;
    @BindView(R.id.iv_textbj5_read)
    ImageView iv_textbj5_read;
    @BindView(R.id.iv_textbj6_read)
    ImageView iv_textbj6_read;
    @BindView(R.id.iv_textbj7_read)
    ImageView iv_textbj7_read;
    @BindView(R.id.iv_textbj8_read)
    ImageView iv_textbj8_read;
    @BindView(R.id.iv_textbj9_read)
    ImageView iv_textbj9_read;
    @BindView(R.id.iv_textbj10_read)
    ImageView iv_textbj10_read;
    @BindView(R.id.iv_textbj11_read)
    ImageView iv_textbj11_read;
    @BindView(R.id.iv_textbj12_read)
    ImageView iv_textbj12_read;
    @BindView(R.id.iv_textbj13_read)
    ImageView iv_textbj13_read;
    @BindView(R.id.iv_textbj14_read)
    ImageView iv_textbj14_read;
    @BindView(R.id.iv_textbj15_read)
    ImageView iv_textbj15_read;
    @BindView(R.id.iv_textbj16_read)
    ImageView iv_textbj16_read;
    @BindView(R.id.iv_textbj17_read)
    ImageView iv_textbj17_read;
    @BindView(R.id.iv_textbj20_read)
    ImageView iv_textbj20_read;


    @BindView(R.id.rl_mulu_read)
    RelativeLayout rl_mulu_read;
    @BindView(R.id.rl_yejian_read)
    RelativeLayout rl_yejian_read;
    @BindView(R.id.rl_xiazai_read)
    RelativeLayout rl_xiazai_read;
    @BindView(R.id.rl_shuqian_read)
    RelativeLayout rl_shuqian_read;
    @BindView(R.id.rl_content_read)
    RelativeLayout rl_content_read;
    @BindView(R.id.rl_download_hint)
    RelativeLayout rl_download_hint;
    @BindView(R.id.rl_download_hint_new)
    RelativeLayout rl_download_hint_new;
    @BindView(R.id.rl_download_content_new)
    RelativeLayout rl_download_content_new;
    @BindView(R.id.view_process_textsize)
    View view_process_textsize;
    @BindView(R.id.view_download_jindu_new)
    View view_download_jindu_new;
    @BindView(R.id.tv_download_jindu_new)
    TextView tv_download_jindu_new;
    @BindView(R.id.tv_download_error_new)
    TextView tv_download_error_new;
    @BindView(R.id.tv_download_hint_new)
    TextView tv_download_hint_new;


    @BindView(R.id.scrollview_read)
    ScrollView scrollview_read;
    public static String sourceid;
    public static String bookid;
    public static int bookPathid;
    public static void invoke(Activity activity,String bookId,String sourceID,int bookPathId) {
        bookid=bookId;
        sourceid=sourceID;
        bookPathid=bookPathId;
        activity.startActivity(new Intent(activity, ReadActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        Constant.sourceid=sourceid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constant.sourceid="";
        Constant.muluList=null;
    }

    @Override
    public ReadPresenter creatPresenter() {
        return new ReadPresenter(this);
    }

    @Override
    public ActionbarAtrribute getActionbarAtrribute() {
        return new ActionbarAtrribute(View.GONE);
    }

    @Override
    public boolean isContainFragments() {
        return false;
    }

    int width;
    int height;
    RelativeLayout.LayoutParams lpJindu;
    @Override
    public void init() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
         width = wm.getDefaultDisplay().getWidth();//屏幕宽度
         height = wm.getDefaultDisplay().getHeight();//屏幕高度
        tv_read.setOnTouchListener(new TextVOntouchListener());
        tv_read.setLongClickable(true);
        iv_textsize_seekbar.setOnTouchListener(new ImavOntouchListener());
        iv_brightness_seekbar.setOnTouchListener(new ImavOntouchListener());
        int brightness=getSystemBrightness();
        RelativeLayout.LayoutParams lpbrght= (RelativeLayout.LayoutParams) view_process_hover_bright.getLayoutParams();
        lpbrght.width= (int) (UIUtils.dip2px(200)*(brightness/255f));
        view_process_hover_bright.setLayoutParams(lpbrght);
        lpJindu= (RelativeLayout.LayoutParams)view_download_jindu_new.getLayoutParams();

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    if(bookPathid==0){
                        presenter.showDialog();
                    }else{
                        presenter.saveFinish();
                    }
                    return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_read;
    }
    boolean flag=true;
    @OnClick({R.id.tv_read, R.id.tv_back_read,
            R.id.iv_back_read,
            R.id.rl_textbj1_read,
            R.id.rl_textbj3_read,
            R.id.rl_textbj2_read,
            R.id.rl_textbj4_read,
            R.id.rl_textbj5_read,
            R.id.rl_textbj6_read,
            R.id.rl_textbj7_read,
            R.id.rl_textbj8_read,
            R.id.rl_textbj9_read,
            R.id.rl_textbj10_read,
            R.id.rl_textbj11_read,
            R.id.rl_textbj12_read,
            R.id.rl_textbj13_read,
            R.id.rl_textbj14_read,
            R.id.rl_textbj15_read,
            R.id.rl_textbj16_read,
            R.id.rl_textbj17_read,
            R.id.rl_textbj20_read,
            R.id.rl_mulu_read,
            R.id.rl_shuqian_read,
            R.id.rl_xiazai_read,
            R.id.rl_yejian_read,
            R.id.tv_huanyuan_read,
            R.id.tv_download_error_new
    }) void onclick(View v) {
        switch (v.getId()){
            case R.id.tv_read:
                Log.e("clickcenter", "::::::::::::: "+clickCenter );
                if(clickCenter){
                    if(!flag){
                        flag=true;
                        rl_actionbar_read.setVisibility(View.GONE);
                        rl_bottom_read.setVisibility(View.GONE);
                    }else {
                        flag=false;
                        rl_actionbar_read.setVisibility(View.VISIBLE);
                        rl_bottom_read.setVisibility(View.VISIBLE);

                    }
                }
                break;
            case R.id.tv_back_read:
            case R.id.iv_back_read:
                if(bookPathid==0){
                    presenter.showDialog();
                }else{
                    presenter.saveFinish();
                }
                break;
            case R.id.rl_textbj1_read:
                presenter.switchBackground(1);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj2_read:
                presenter.switchBackground(2);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj3_read:
                presenter.switchBackground(3);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj4_read:
                presenter.switchBackground(4);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj5_read:
                presenter.switchBackground(5);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj6_read:
                presenter.switchBackground(6);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj7_read:
                presenter.switchBackground(7);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj8_read:
                presenter.switchBackground(8);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj9_read:
                presenter.switchBackground(9);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj10_read:
                presenter.switchBackground(10);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj11_read:
                presenter.switchBackground(11);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj12_read:
                presenter.switchBackground(12);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj13_read:
                presenter.switchBackground(13);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj14_read:
                presenter.switchBackground(14);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj15_read:
                presenter.switchBackground(15);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj16_read:
                presenter.switchBackground(16);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj17_read:
                presenter.switchBackground(17);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_textbj20_read:
                presenter.switchBackground(20);
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_xiazai_read:
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                presenter.downLoadNew();
                flag=true;
                break;
            case R.id.rl_mulu_read:
                presenter.showMuluPre();
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.tv_huanyuan_read:
                presenter.changeSourcePre();
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.rl_shuqian_read:
                presenter.showShuqianPre();
                rl_actionbar_read.setVisibility(View.GONE);
                rl_bottom_read.setVisibility(View.GONE);
                flag=true;
                break;
            case R.id.tv_download_error_new:
                //重新加载错误章节
                presenter.downLoadNew();
                break;


        }
    }
    int  downX;
    int  downY;
    int moveX;
    int borderLeft;
    int borderRight;

    public void setViewWidth(int width ,int shuzhi) {

        lpJindu.width=width;
        view_download_jindu_new.setLayoutParams(lpJindu);
        tv_download_jindu_new.setText(shuzhi+"%");
    }

    private class ImavOntouchListener implements View.OnTouchListener{
        int downX;
        int moveX;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    downX= (int) motionEvent.getRawX();
                    borderLeft= (int) view_process_textsize.getX();
                    borderRight= (int) (view_process_textsize.getX()+view_process_textsize.getWidth());
                    return true;
                case MotionEvent.ACTION_MOVE:
                    moveX= (int) motionEvent.getRawX();
                    if(borderLeft<moveX && moveX<borderRight){
                        if(view.getId()==R.id.iv_textsize_seekbar){
                            RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) view_process_hover_textsize.getLayoutParams();
                            lp.width=moveX-borderLeft;
                            view_process_hover_textsize.setLayoutParams(lp);
                            float value=(float)(moveX-borderLeft)/UIUtils.dip2px(200);
                            if(value>=0 && value<0.2f){
                                if(tv_read.getTextSize()!=UIUtils.dip2px(14)){
                                    tv_read.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
                                }
                            }else if(value>=0.2f &&value<0.4f){
                                if(tv_read.getTextSize()!=UIUtils.dip2px(15)){
                                    tv_read.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
                                }
                            }else if(value>=0.4f &&value<0.6f){
                                if(tv_read.getTextSize()!=UIUtils.dip2px(16)){
                                    tv_read.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
                                }
                            }else if(value>=0.6f &&value<0.8f){
                                if(tv_read.getTextSize()!=UIUtils.dip2px(17)){
                                    tv_read.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);
                                }
                            }else if(value>=0.8f &&value<1){
                                if(tv_read.getTextSize()!=UIUtils.dip2px(18)){
                                    tv_read.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                                }
                            }
                        }else if(view.getId()==R.id.iv_brightness_seekbar){
                            RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) view_process_hover_bright.getLayoutParams();
                            lp.width=moveX-borderLeft;
                            view_process_hover_bright.setLayoutParams(lp);
                            changeBrightness((int) (((float)(moveX-borderLeft)/UIUtils.dip2px(200))*255));
                        }

                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    return true;
            }
            return false;
        }
    }
    boolean clickCenter=true;
    private class TextVOntouchListener implements View.OnTouchListener{
        private boolean next=false;
        private boolean pre=false;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:

                    downX= (int) motionEvent.getX();
                    downY= (int) motionEvent.getY();
                    if(!flag){
                        if(downX<width/3*2 &&downX>width/3 && motionEvent.getRawY()>height/3 && motionEvent.getRawY()<height*2/3){
                            clickCenter=true;
                        }else {
                            clickCenter=false;
                        }
                        return false;
                    }else{
                        if(downX<width/3*2 &&downX>width/3 && motionEvent.getRawY()>height/3 && motionEvent.getRawY()<height*2/3){
                            clickCenter=true;
                            return false;
                        }
                    }


                    return true;
                case MotionEvent.ACTION_MOVE:
                    if(!flag){
                        return false;
                    }
                    moveX= (int) motionEvent.getX();
                    if(moveX-downX >50){
                        pre=true;
                    }else if(moveX-downX<-50){
                        next=true;
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    if(!flag){
                        return false;
                    }
                    if(next){
                        presenter.nextZhang();
                        next=false;
                    }
                    if(pre){
                        presenter.preZhang();
                        pre=false;
                    }
                    if( (int)motionEvent.getX()>(int)width/3*2 &&
                            Math.abs((int)motionEvent.getX()-downX)<10 &&
                            Math.abs((int)motionEvent.getY()-downY)<10){
                        Log.e("下一页", ":::::::::点击了下一页 ");
                        presenter.nextZhang();
                        return true;
                    }else if( (int)motionEvent.getX()<(int)width/3 &&Math.abs((int)motionEvent.getX()-downX)<10&&
                            Math.abs((int)motionEvent.getY()-downY)<10){
                        Log.e("下一页", ":::::::::点击了下一页 ");
                        presenter.preZhang();
                        return true;
                    }
            }
            return false;
        }
    }
    private int getSystemBrightness(){
        int systemBrightness=0;
        try {
            systemBrightness=Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;
    }
    private void changeBrightness(int brightNess){
        Window window=this.getWindow();
        WindowManager.LayoutParams lp=window.getAttributes();
        if(brightNess==-1){
            lp.screenBrightness=WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        }else{
            lp.screenBrightness=brightNess/255f;
        }
        window.setAttributes(lp);
    }
}
