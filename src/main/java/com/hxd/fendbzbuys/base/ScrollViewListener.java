package com.hxd.fendbzbuys.base;

import com.hxd.fendbzbuys.ui.ObservableScrollView;

/**
 * Created by lichao on 17/10/25.
 */

public interface ScrollViewListener {
    void onScrollChanged(ObservableScrollView observableScrollView, int x, int y, int oldx, int oldy);
}
