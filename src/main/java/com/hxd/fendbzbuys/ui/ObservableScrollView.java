package com.hxd.fendbzbuys.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.hxd.fendbzbuys.base.ScrollViewListener;

/**
 * Created by lichao on 17/10/25.
 */

public class ObservableScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener;
    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        this.scrollViewListener=scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollViewListener!=null){
            scrollViewListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }
}
