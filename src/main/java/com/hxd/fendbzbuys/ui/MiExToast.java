package com.hxd.fendbzbuys.ui;

/**
 * Created by lichao on 17/8/20.
 */

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxd.fendbzbuys.MyApplication;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.base.MyCallBack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MiExToast {
    private static final String TAG = "ExToast";

    public static final int LENGTH_ALWAYS = 0;
    public static final int LENGTH_SHORT = 2;
    public static final int LENGTH_LONG = 4;

    private Toast toast;
    private Context mContext;
    private int mDuration = LENGTH_SHORT;
    private int animations = -1;
    private boolean isShow = false;

    private Object mTN;
    private Method show;
    private Method hide;
    private WindowManager mWM;
    private WindowManager.LayoutParams params;
    private View mView;

    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    public TextView tv;
    public TextView tv_jindu_float_tips;
    public TextView tv_error_floattips;
    public RelativeLayout rl_float_tips;
    public View view_jindu_float_tips;
    public View view_float_tips;
    private Handler handler = new Handler();

    public MiExToast(Context context){
        this.mContext = context;
        if (toast == null) {
            toast = new Toast(mContext);
        }
        LayoutInflater inflate = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflate.inflate(R.layout.float_tips_layout, null);

    }

    private Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    public void setText(String str){
        tv.setText(str);
    }
    public void setJindu(int width){
       RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) view_jindu_float_tips.getLayoutParams();
        layoutParams.width=width;
        view_jindu_float_tips.setLayoutParams(layoutParams);
    }
    public void setJinduText(String str){
        tv_jindu_float_tips.setText(str);
    }
    public void setErrorHintVisible(){
        tv_error_floattips.setVisibility(View.VISIBLE);
        rl_float_tips.setVisibility(View.GONE);
    }
    public void setErrorHintGone(){
        tv_error_floattips.setVisibility(View.GONE);
        rl_float_tips.setVisibility(View.VISIBLE);
    }
    /**
     * Show the view for the specified duration.
     */
    int height;
    public void show(){
        if (isShow) return;
        WindowManager wm = (WindowManager) MyApplication.getMyapplication().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();//屏幕高度
        tv= (TextView)mView.findViewById(R.id.tv_float_tips);
        tv_jindu_float_tips= (TextView)mView.findViewById(R.id.tv_jindu_float_tips);
        tv_error_floattips= (TextView)mView.findViewById(R.id.tv_error_floattips);
        rl_float_tips= (RelativeLayout) mView.findViewById(R.id.rl_float_tips);
        view_float_tips = mView.findViewById(R.id.view_float_tips);
        view_jindu_float_tips = mView.findViewById(R.id.view_jindu_float_tips);
        tv.setText("缓存中: ");
        view_float_tips.setOnTouchListener(new MyonTouch());
        toast.setView(mView);
        initTN();
        try {
            show.invoke(mTN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isShow = true;
        //判断duration，如果大于#LENGTH_ALWAYS 则设置消失时间
        if (mDuration > LENGTH_ALWAYS) {
            handler.postDelayed(hideRunnable, mDuration * 1000);
        }
    }
    public void setTvText(String str){
        tv.setText(str);
    }
    /**
     * Close the view if it's showing, or don't show it if it isn't showing yet.
     * You do not normally have to call this.  Normally view will disappear on its own
     * after the appropriate duration.
     */
    public void hide(){
        if(!isShow) return;
        try {
            hide.invoke(mTN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isShow = false;
    }

    public void setView(View view) {
        toast.setView(view);
    }

    public View getView() {
        return toast.getView();
    }

    /**
     * Set how long to show the view for.
     * @see #LENGTH_SHORT
     * @see #LENGTH_LONG
     * @see #LENGTH_ALWAYS
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setMargin(float horizontalMargin, float verticalMargin) {
        toast.setMargin(horizontalMargin,verticalMargin);
    }

    public float getHorizontalMargin() {
        return toast.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return toast.getVerticalMargin();
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        toast.setGravity(gravity,xOffset,yOffset);
    }

    public int getGravity() {
        return toast.getGravity();
    }

    public int getXOffset() {
        return toast.getXOffset();
    }

    public int getYOffset() {
        return toast.getYOffset();
    }

    public static MiExToast makeText( CharSequence text, int duration) {
        Toast toast = Toast.makeText(MyApplication.getMyapplication().getApplicationContext(),text,Toast.LENGTH_SHORT);
        MiExToast exToast = new MiExToast(MyApplication.getMyapplication().getApplicationContext());
        exToast.toast = toast;
        exToast.mDuration = duration;

        return exToast;
    }

    /*public static MiExToast makeText( int resId, int duration)
            throws Resources.NotFoundException {
        return makeText(MyApplication.getMyapplication().getApplicationContext(), MyApplication.getMyapplication().getApplicationContext().getResources().getText(resId), duration);
    }*/

    public void setText(int resId) {
        setText(mContext.getText(resId));
    }

    public void setText(CharSequence s) {
        toast.setText(s);
    }

    public int getAnimations() {
        return animations;
    }

    public void setAnimations(int animations) {
        this.animations = animations;
    }

    private void initTN() {
        try {
            Field tnField = toast.getClass().getDeclaredField("mTN");
            tnField.setAccessible(true);
            mTN = tnField.get(toast);
            show = mTN.getClass().getMethod("show");
            hide = mTN.getClass().getMethod("hide");

            Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
            tnParamsField.setAccessible(true);
            params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            /**设置动画*/
            if (animations != -1) {
                params.windowAnimations = animations;
            }

            /**调用tn.show()之前一定要先设置mNextView*/
            Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN, toast.getView());

            mWM = (WindowManager)mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setGravity(Gravity.TOP | Gravity.CENTER,0 ,0);
    }

    private void updateViewPosition(){
        //更新浮动窗口位置参数
        int marY=(int) (height-mTouchStartY);
        if(marY>(int)(height/6)*5){
            params.y=(int)(height/6)*5;
        }else if(marY<(int)height/6){
            params.y=(int)height/6;
        }else{
            params.y=marY;
        }
        Log.i(" params.y:::",":::::"+marY);
        mWM.updateViewLayout(toast.getView(), params);  //刷新显示
    }

    public void setErrorOnclick(MyCallBack callback) {
        callback.next();
    }

    private class MyonTouch implements View.OnTouchListener {
        //获取相对屏幕的坐标，即以屏幕左上角为原点


        @Override
        public boolean onTouch(View view, MotionEvent event) {
            /*x = motionEvent.getRawX();
            y = motionEvent.getRawY();
            Log.i("currP", "currX"+x+"====currY"+y);
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                    //获取相对View的坐标，即以此View左上角为原点
                    mTouchStartX =  motionEvent.getX();
                    mTouchStartY =  motionEvent.getY();
                    Log.i("startP","startX"+mTouchStartX+"====startY"+mTouchStartY);
                    return  true;
                case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作
                    //updateViewPosition();
                    return  true;
                case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                    //updateViewPosition();
                    return  true;
            }*/
            y = event.getRawY();
            Log.i("currP", "currX"+x+"====currY"+y);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                    //获取相对View的坐标，即以此View左上角为原点
                    mTouchStartX =  event.getRawX();
                    mTouchStartY =  event.getRawY();

                    break;
                case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作

                    mTouchStartY =  event.getRawY();
                    Log.i("mTouchStartY:::",":::::"+mTouchStartY);
                    //updateViewPosition();
                    break;
                case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                   // updateViewPosition();
                    break;
            }
            return true;
        }
    }

}
