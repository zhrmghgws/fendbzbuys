package com.hxd.fendbzbuys.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hxd.fendbzbuys.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 专门处理界面相关的工具类
 *
 * @author itcast
 */
public class UIUtils {
    private static Toast makeText;



    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static void showToast(int stringId) {
        // Toast长时间轮流显示问题
        if (makeText == null) {
            makeText = Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT);
            makeText.setGravity(Gravity.TOP | Gravity.CENTER,
                    makeText.getXOffset() / 2, 400);
        } else {
            makeText.setText(getString(stringId));
        }
        makeText.show();
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void showToast(String str) {
        // Toast长时间轮流显示问题
        if (makeText == null) {
            makeText = Toast.makeText(getContext(), str, Toast.LENGTH_LONG);
            makeText.setGravity(Gravity.TOP | Gravity.CENTER,
                    makeText.getXOffset() / 2, 600);
        } else {
            makeText.setText(str);
        }
        makeText.show();
    }

    public static Context getContext() {
        return MyApplication.getMyapplication().getApplicationContext();
    }

    /**
     * 返回资源文件下,字符数组
     *
     * @param id 资源id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    public static String getString(int id) {
        return getResource().getString(id);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);

    }

    /**
     * 根据资源id  返回一个颜色
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    /**
     * 创建View对象
     *
     * @param layoutId 布局id
     * @return
     */
    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * 在主线程中 执行代码
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isRunUiThread()) {
            runnable.run();
        } else {
            executeTask(runnable);
        }
    }

    public static void executeTask(Runnable runnable) {
        getHandlder().post(runnable);
    }

    /**
     * 延迟执行任务
     *
     * @param runnable
     * @param delayMillis
     */
    public static void executeDelay(Runnable runnable, long delayMillis) {
        getHandlder().postDelayed(runnable, delayMillis);
    }

    /**
     * 移除一个任务
     *
     * @param runnable
     */
    public static void removeTask(Runnable runnable) {
        getHandlder().removeCallbacks(runnable);
    }

    private static Handler getHandlder() {
        return MyApplication.getHandler();
    }

    /**
     * 判断程序是否在主线程中运行的
     */
    public static boolean isRunUiThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    private static int getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    public static int getDimens(int id) {
        return (int) getResource().getDimension(id);
    }

    /**
     * 取消异步任务
     *
     * @param
     */
    public static void cancelTask(Runnable r) {
        getHandlder().removeCallbacks(r);
    }

   /* public static void startActivity(Intent intent) {
        if (getRunActivity() != null) {
            getRunActivity().startActivity(intent);
        } else {
            //  因为activity显示需要依赖任务栈
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }*/

   /* public static application.android.com.xindai.Activity getRunActivity() {
        application.android.com.xindai.Activity runActivity = BaseActivity.getRunActivity();
        if (runActivity != null) {
            return runActivity;
        } else if (HXBaseActivity.getRunActivity() != null) {
            return HXBaseActivity.getRunActivity();
        } else return null;
    }

    public static void showToastorHint(String text) {
        if (UIUtils.getRunActivity() != null) {
            if ((UIUtils.getRunActivity()) instanceof BaseActivity && ((BaseActivity) UIUtils.getRunActivity()).havePopHint()) {
                BaseActivity pophint = (BaseActivity) UIUtils.getRunActivity();
                pophint.pophint(PophintAttribute.getInstance().initFailure().setText(text));
            } else if ((UIUtils.getRunActivity()) instanceof HXBaseActivity && ((HXBaseActivity) UIUtils.getRunActivity()).havePopHint()) {
                HXBaseActivity pophint = (HXBaseActivity) UIUtils.getRunActivity();
                pophint.pophint(PophintAttribute.getInstance().initFailure().setText(text));

            } else UIUtils.showToast(text);
        } else {
            UIUtils.showToast(text);
        }
    }*/

    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        PackageManager pm = context.getPackageManager();
        try {
            if (pm != null) {
                ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (info != null) {
                    if (info.metaData != null) {
                        resultData = info.metaData.getString(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 判断输入的是否是汉字
     */
    public static boolean isChinese(String text) {
        boolean flag = false;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher m = p.matcher(text);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断输入的是否是手机号
     */
    public static boolean isMobile(String text) {
        Pattern p = Pattern.compile("0?(13|14|15|17|18)[0-9]{9}");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 判断输入的是否是邮箱地址
     */
    public static boolean isEmail(String text) {
        Pattern p = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
        Matcher m = p.matcher(text);
        if (text.length() >= 6 && text.length() <= 32) {
            return m.matches();
        }
        return false;
    }

    /**
     * 判断输入的是否是座机号
     */
    public static boolean isPhone(String text) {
        Pattern p = Pattern.compile("^0[0-9]{2,3}-\\d{7,8}$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 判断输入的是否是微信号
     */
    public static boolean isWeChat(String text) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_-]{5,19}$");
        Matcher m = p.matcher(text);
        if (text.length()<1){
            return true;
        }
        return m.matches();
    }

    /**
     * 判断输入的是否没有特殊字符
     */
    public static boolean isNormal(String text) {
        String[] strings = {"&", "*", ";", "+", "$", ",", "?", "#", "[", "]", "%"};
        boolean isNormal = true;
        for (String string : strings) {
            boolean b = text.contains(string);
            if (b) {
                isNormal = false;
            }
        }
        return isNormal;
    }
    /**
     * 键盘是否隐藏
     * */
    public static void inputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 判断输入的是否没有Emoji表情符
     */
    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return !m.find();
    }

}
