package com.wukong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.wukong.login.LoginActivity;

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
public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		Intent sendIntent = new Intent(WelcomeActivity.this,
				LoginActivity.class);
		WelcomeActivity.this.startActivity(sendIntent);
		WelcomeActivity.this.finish();
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

}
