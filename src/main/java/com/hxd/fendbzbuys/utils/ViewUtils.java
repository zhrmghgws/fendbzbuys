package com.hxd.fendbzbuys.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class ViewUtils {

    public static void removeParent(View view) {
        // 一般情况下view 挂载到ViewGroup上 总有一个控件的爹是屏幕窗体
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent; // 获取到了View爹
            group.removeView(view);// 把view对象从它爹身上移除
        }
    }

    //得到一个activity的根view对象
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     *
     * @param editText
     * @return 是空返回 true 不是返回false
     */
    public static boolean iSEmpty(EditText editText) {
        return editText.getText().toString().trim().length() <= 0 ? true : false;
    }
    public static boolean iSdayu(EditText editText,int n) {
        return editText.getText().toString().trim().length() >= n ? true : false;
    }
    public static String editTextToString(TextView editText) {
        return editText.getText().toString().trim();
    }
    /**
     * 图片压缩
     * */
    public static String photoCompress(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bt = stream.toByteArray();
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < bt.length; n++) {
            stmp = Integer.toHexString(bt[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        String name = sb.toString();
        return name;
    }
}