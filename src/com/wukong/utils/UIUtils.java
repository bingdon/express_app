package com.wukong.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 
 * @ClassName: UIUtils
 * @Description: UI 工具类
 * @author zc,定制整个程序公用的对话框和toast
 * @date
 * 
 */
public class UIUtils {
	/**
	 * 隐藏软件盘
	 */
	public void hideSoftInput(Activity context) {
		View view = context.getCurrentFocus();
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null && view != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}

	}
}
