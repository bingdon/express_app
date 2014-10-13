package com.wukong.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @������: ��װToast
 * @�ļ���: ToastUtils.java
 * @����·��: com.micronutrient.s_health.utils
 * @����ʱ�� 2013-1-4 ����5:08:54
 * @author zc
 * @version V1.0
 */
public class ToastUtils extends Toast {

	public ToastUtils(Context context) {
		super(context);
	}

	/**
	 * �Ƿ���ʾ false ��ʾ���߽׶Σ�true ��ʾ�����׶�
	 */
	private static final boolean isShow = true;

	/**
	 * ��ʾ����Toast,����showŶ
	 * 
	 * @param context
	 * @param msg
	 * @param duration
	 */
	public static void toast(Context context, String msg) {
		if (isShow) {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showShort(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

}