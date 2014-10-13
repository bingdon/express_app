package com.wukong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Window;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.bean.PersonInfoBean;
import com.wukong.login.LoginActivity;
import com.wukong.utils.UserInfoUtility;
import com.wukong.utils.WKHttpClient;

/**
 * 
 * @ClassName: WelcomeActivity
 * @Description:Æô¶¯Ò³
 * @Author: zc
 * @CreateDate:
 * @ReviseUser:
 * @ReviseDate:
 * @ReviseRemark:
 * @version V1.0.0
 */
public class WelcomeActivity extends Activity implements Callback {

	private Handler handler;

	private final long DELAY_TIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		handler = new Handler(this);

		UserInfoUtility.getPersonInfo(this);
		if (null == WKApplication.getInstance().getPersonInfoBean()) {
			handler.sendEmptyMessageDelayed(0, DELAY_TIME);
		} else {
			handler.sendEmptyMessageDelayed(1, DELAY_TIME);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		int what = msg.what;
		Intent sendIntent;
		switch (what) {
		case 0:
			loginbytel();
			sendIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
			WelcomeActivity.this.startActivity(sendIntent);
			WelcomeActivity.this.finish();
			break;

		case 1:
			sendIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
			WelcomeActivity.this.startActivity(sendIntent);
			WelcomeActivity.this.finish();
			break;

		default:
			break;
		}
		return false;
	}

	private void loginbytel() {
		PersonInfoBean personInfoBean = WKApplication.getInstance()
				.getPersonInfoBean();
		if (null == personInfoBean) {
			return;
		}

		new WKHttpClient().doHttpLogin(personInfoBean.getTel(),
				personInfoBean.getPassword(), new AsyncHttpResponseHandler());
	}

}
