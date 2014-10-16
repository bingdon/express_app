package com.wukong.support.image;

import java.io.File;

import android.os.Environment;

public class SdUtils {
	/**
	 * �ж�sd�Ƿ�ɶ�
	 * 
	 * @return
	 */
	public static boolean ExistSDCard() {
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	public static boolean isSDCard() {
		File sd = Environment.getExternalStorageDirectory(); 
		if (sd.canRead()) {
			return true;
		}
		return false;
	}
	
}
