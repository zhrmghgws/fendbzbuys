package com.hxd.fendbzbuys.base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.R;
import com.hxd.fendbzbuys.manager.SystemBarTintManager;
import com.hxd.fendbzbuys.utils.UIUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;

/**
 * Created by lichao on 16/9/19.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements ActivityView {
    protected View actionbar;
    @BindView(R.id.iv_left_actionbar)
    protected ImageView mIvLeftActionbar;
    @BindView(R.id.tv_lefttitle_actionbar)
    protected TextView mTvLefttitleActionbar;
    @BindView(R.id.actionbar_rl_back)
    protected RelativeLayout mActionbarRlBack;
    @BindView(R.id.tv_centertitle_actionbar)
    protected TextView mTvCentertitleActionbar;
    @BindView(R.id.tv_righttitle_actionbar)
    protected TextView mTvRighttitleActionbar;
    @BindView(R.id.iv_right_actionbar)
    protected ImageView mIvRightActionbar;
    @BindView(R.id.actionbar)
    protected RelativeLayout mActionbar;
    @BindView(R.id.fl_actionbar)
    protected FrameLayout mFlActionbar;
    @BindView(R.id.view_line)
    protected View mline;
    @BindView(R.id.tv_hint)
    protected TextView tv_hint;
    private Unbinder unbinder;
    private boolean flag = true;
    private boolean isContainFragment=false;
    private final class XDLinearLayout extends LinearLayout {

        public XDLinearLayout(Context context) {
            super(context);
            setOrientation(VERTICAL);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        XDLinearLayout xdLinearLayout = new XDLinearLayout(getApplicationContext());
        View xdActionbar = getXDActionbar();
        View hint = getXDHint();
        xdLinearLayout.addView(xdActionbar);
        xdLinearLayout.addView(hint);
        xdLinearLayout.addView(LayoutInflater.from(this).inflate(getLayoutID(), null));
        setContentView(xdLinearLayout);
        Constant.runActivity=this;
        unbinder = ButterKnife.bind(this);
        initActionBar(xdActionbar);
        initSystemBar();
        init();
    }

    public View getXDActionbar() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.actionbar, null);
    }

    public View getXDHint() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.actionbar_hint, null);
    }

    protected void initActionBar(View xdActionbar) {
        ActionbarAtrribute actionbarAtrribute = getActionbarAtrribute();
        if (View.VISIBLE == actionbarAtrribute.getVisible()) {
            setUpActionbarUi(actionbarAtrribute);
        } else {
            xdActionbar.setVisibility(GONE);
        }
    }

    protected void setUpActionbarUi(ActionbarAtrribute actionbarAtrribute) {
        mTvLefttitleActionbar.setText(actionbarAtrribute.getLeftTitle());
        if(actionbarAtrribute.getmLeftImageVisible()){
            mIvLeftActionbar.setImageResource(actionbarAtrribute.getLeftImageId());
        }else{
            mIvLeftActionbar.setVisibility(View.GONE);
        }
        mTvCentertitleActionbar.setText(actionbarAtrribute.getCenterString());
        mTvRighttitleActionbar.setText(actionbarAtrribute.getRightTitle());
        if(actionbarAtrribute.getRightOnClickListener()!= null){
            mTvRighttitleActionbar.setOnClickListener(actionbarAtrribute.getRightOnClickListener());
        }
        mActionbarRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public abstract ActionbarAtrribute getActionbarAtrribute();
    public abstract boolean isContainFragments();

    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(UIUtils.getColor(R.color.transparent));
        }
    }

    public abstract void init();

    public abstract int getLayoutID();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.back_right_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isContainFragment=isContainFragments();

    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        throw new RuntimeException("不要手动调用...");
    }

    @Override
    public void setContentView(View view) {
        if (!(view instanceof XDLinearLayout)) {
            throw new RuntimeException("不要手动调用...");
        } else {
            super.setContentView(view);
        }
    }


    @Override
    public BaseActivity getXDActivity() {
        return this;
    }

    public void otherPophint(String text) {
        if (!flag) {
            tv_hint.setText(text);
            return;
        }
        flag = false;
        if(tv_hint != null) {
            tv_hint.setVisibility(View.VISIBLE);
            tv_hint.setText(text);
            final LinearLayout.LayoutParams lphint = (LinearLayout.LayoutParams) tv_hint.getLayoutParams();
            lphint.height = 0;
            tv_hint.setLayoutParams(lphint);
            ValueAnimator va = ValueAnimator.ofInt(0, UIUtils.dip2px(30));
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    lphint.height = value;
                    if(tv_hint != null) {
                        tv_hint.setLayoutParams(lphint);
                    }
                }
            });
            va.setDuration(300);
            va.start();
            UIUtils.executeDelay(new Runnable() {
                @Override
                public void run() {
                    ValueAnimator va = ValueAnimator.ofInt(UIUtils.dip2px(30), 0);
                    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int) animation.getAnimatedValue();
                            lphint.height = value;
                            if (tv_hint!=null){
                                tv_hint.setLayoutParams(lphint);
                            }
                        }
                    });
                    va.setDuration(300);
                    va.start();
                    va.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            flag = true;
                            if(tv_hint!=null){
                                tv_hint.setVisibility(GONE);
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                }
            }, 1500);

        }else{
            UIUtils.showToast(text);
        }
    }
}
