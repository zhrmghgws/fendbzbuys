package com.hxd.fendbzbuys.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * 检测网络
 * @author wbs
 *
 */
public class NetworkUtils {
	
	/** 网络不可用 */
	public static final int NONETWORK= 0;
	/** 是wifi连接 */
	public static final int WIFI = 1;
	/** 不是wifi连接 */
	public static final int NOWIFI = 2;

	/**
	 * 检验网络连接 并判断是否是wifi连接
	 * @return <li>没有网络：NetworkUtils.NONETWORK;</li> <li>wifi 连接：NetworkUtils.WIFI;</li> <li>mobile 连接：NetworkUtils.NOWIFI</li>
	 */
	public static int checkNetWorkType() {
	    
		if (!checkNetWork()) {
			return NetworkUtils.NONETWORK;
		}
		ConnectivityManager cm = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
			return NetworkUtils.WIFI;
		else
			return NetworkUtils.NOWIFI;
	}
	
	/**
	 * 检测网络是否连接
	 * @return
	 */
	public static boolean checkNetWork(){
		// 1.获得连接设备管理器
		ConnectivityManager cm = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null){
			return false;
		}
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if(ni == null || !ni.isAvailable()){
			return false;
		}
		return true;
	}
}
