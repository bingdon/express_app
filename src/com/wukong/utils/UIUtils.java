package com.wukong.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 
 * @ClassName: UIUtils
 * @Description: UI ������
 * @author zc,�������������õĶԻ����toast
 * @date
 * 
 */
public class UIUtils {
	/**
	 * ���������
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
