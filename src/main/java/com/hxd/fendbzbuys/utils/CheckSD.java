package com.hxd.fendbzbuys.utils;

import android.os.Environment;

/**
 * 检查是否存在SDCard
 * 
 * @return
 */

public class CheckSD {
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	} 
}
