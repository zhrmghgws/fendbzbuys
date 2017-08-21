package com.hxd.fendbzbuys.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import java.io.ByteArrayOutputStream;

public class DrawableUtils {

    /**
     * 创建Shape图形资源
     *
     * @param contentColor
     * @param radius
     * @param stokeColor
     * @return
     */
    public static GradientDrawable createGragientDrawable(int contentColor, int radius, int stokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.RECTANGLE);// 指定了矩形
        drawable.setColor(contentColor);
        drawable.setCornerRadius(radius);
        drawable.setStroke(1, stokeColor);
        return drawable;

    }
    public static GradientDrawable createGragientDrawable(int radius, int stokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.RECTANGLE);// 指定了矩形
        drawable.setCornerRadius(radius);
        drawable.setStroke(UIUtils.dip2px(1), stokeColor);
        return drawable;

    }
    /**
     * 创建状态选择器
     *
     * @param normalDrawable
     * @param pressDrawable
     * @return
     */
    public static StateListDrawable createStateListDrawable(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressDrawable);
        drawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        drawable.addState(new int[]{}, normalDrawable);
        return drawable;

    }

    /**
     * 将图片转换成十六进制字符串
     *
     * @param
     * @return
     */
    public static String sendPhoto(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);// (0 -
        // 100)压缩文件
        byte[] bt = stream.toByteArray();
        String photoStr = byte2hex(bt);
        return photoStr;
    }

    /**
     * 二进制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString();
    }

    /**
     * 判断2张drawable是否是一张图片
     *
     * @param drawable1
     * @param drawable2
     * @return
     */
    public static boolean eqluasPic(Drawable drawable1, Drawable drawable2) {
        return drawable1.getConstantState().equals(drawable2.getConstantState());

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
