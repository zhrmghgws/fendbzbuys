package com.hxd.fendbzbuys;

import android.content.SharedPreferences;
import android.util.Log;

import com.hxd.fendbzbuys.base.BaseActivity;
import com.hxd.fendbzbuys.domain.BangdanBooksBean;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.FenleiBookTypeInfo;
import com.hxd.fendbzbuys.domain.GenderInfo;
import com.hxd.fendbzbuys.domain.ShuSourceInfo;
import com.hxd.fendbzbuys.domain.StatisticsInfo;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;
import com.hxd.fendbzbuys.domain.gen.BangdanBooksBeanDao;
import com.hxd.fendbzbuys.manager.DaoManager;
import com.hxd.fendbzbuys.network.FBNetwork;
import com.hxd.fendbzbuys.network.ProcressSubsciber;

import java.util.List;

/**
 * Created by lichao on 16/9/22.
 * 全局变量
 */
public class Constant {
    public static String versioncode="1";
    public static String deviceID;
    public static String token;
    public static String userId;
    private static String registrationId;
    public static SharedPreferences sp;
    public static int registerResult;
    /**
     * 返回到借款页mainposition=LOAN_POSTION,返回到我的账户页面mainposition=ACCOUNT_POSITION
     * 从哪进返回到哪mainposition=NONE_POSITION
     */
    public static GenderInfo genderInfo;
    public static FenleiBookTypeInfo statisticsInfo;


    public static GenderInfo.FemaleInfo male_zuire;
    public static GenderInfo.FemaleInfo male_qianli;
    public static GenderInfo.FemaleInfo male_wanjie;
    public static GenderInfo.FemaleInfo female_zuire;
    public static GenderInfo.FemaleInfo female_qianli;
    public static GenderInfo.FemaleInfo female_wanjie;
    public static MainPostion mainposition=MainPostion.NONE_POSITION;
    public static int STYPE_maleZuireyue=1;
    public static int STYPE_maleZuirezong=2;
    public static int STYPE_maleZuirezhou=3;
    public static int STYPE_maleQianlizhou=4;
    public static int STYPE_maleQianliyue=5;
    public static int STYPE_maleQianlizong=6;
    public static int STYPE_maleWanjiezong=7;
    public static int STYPE_maleWanjieyue=8;
    public static int STYPE_maleWanjiezhou=9;
    public static int STYPE_femalWanjiezho=10;
    public static int STYPE_femalWanjieyue=11;
    public static int STYPE_femalWanjiezong=12;
    public static int STYPE_femaleQianlizong=13;
    public static int STYPE_femalQianliyue=14;
    public static int STYPE_femalQianlizho=15;
    public static int STYPE_femaleZuirezhou=16;
    public static int STYPE_femalZuireyue=17;
    public static int STYPE_femaleZuirezong=18;

    public static BaseActivity runActivity;
    public static List<BookMuluInfo.ChaptersEntity> muluList;
    public static String sourceid;
    public static List<ShuSourceInfo> sourceList;
    public static int taskID;
    public static boolean isNan=true;

    public enum MainPostion{
        SHUJIA_POSITION,PAIHANG_POSITION,FENLEISHUKU_POSITION,NONE_POSITION
    }

}
