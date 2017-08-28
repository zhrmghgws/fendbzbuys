package com.hxd.fendbzbuys.moduler.shudetail_moduler;

import com.hxd.fendbzbuys.MyApplication;
import com.hxd.fendbzbuys.ui.MiExToast;

/**
 * Created by lichao on 17/8/20.
 */

public class DownLoadToastManager {
    public static MiExToast miToast;
    public static String bookid;

    public static void  creatToast(){
        miToast = new MiExToast(MyApplication.getMyapplication().getApplicationContext());
        miToast.setDuration(MiExToast.LENGTH_ALWAYS);
       miToast.show();
    }

}
