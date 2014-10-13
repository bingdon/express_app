package com.wukong.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.CheifActivity;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.bean.PersonInfoBean;
import com.wukong.debug.AppLog;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.UIUtils;
import com.wukong.utils.WKHttpClient;

/**
 * 
 * @ClassName: WelcomeActivity
 * @Description:登录页
 * @Author: zc
 * @CreateDate:
 * @ReviseUser:
 * @ReviseDate:
 * @ReviseRemark:
 * @version V1.0.0
 */
public class LoginActivity extends Activity implements OnClickListener {
	private static final String TAG = LoginActivity.class.getSimpleName();// log
	private ImageView headview;// 头像
	private EditText name;// 用户名
	private EditText password;// 密码
	private ImageView name_clear;// 清除用户名
	private ImageView password_clear;// 清除密码
	private Button login_sure;// 登录
	private TextView find_password;// 找回密码
	private int clear_index = 0;// 区别是用户，密码
	UIUtils utils;
	public static String id;
	private String tel;
	private String pwd;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		utils = new UIUtils();
		utils.hideSoftInput(LoginActivity.this);
		initView();
		setView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		headview = (ImageView) findViewById(R.id.login_headview);
		name = (EditText) findViewById(R.id.login_name);
		password = (EditText) findViewById(R.id.login_passwords);
		name_clear = (ImageView) findViewById(R.id.login_name_clear);
		password_clear = (ImageView) findViewById(R.id.login_passwords_clear);
		login_sure = (Button) findViewById(R.id.login_sure_btn);
		find_password = (TextView) findViewById(R.id.login_findpassword_btn);
		name_clear.setOnClickListener(this);
		password_clear.setOnClickListener(this);
		login_sure.setOnClickListener(this);
		find_password.setOnClickListener(this);
		
		progressDialog=new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.login_ing));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_name_clear:// 帐号清除内容事件
			if (name.getText().toString() != null
					|| !name.getText().toString().trim().equals("")) {
				name.setText("");
			} else {

			}
			break;
		case R.id.login_passwords_clear:// 密码清除内容事件
			if (password.getText().toString() != null
					|| !password.getText().toString().trim().equals("")) {
				password.setText("");
			} else {

			}
			break;
		case R.id.login_sure_btn:// 登录事件
			LoginAccessNet();
			break;
		case R.id.login_findpassword_btn:// 找回密码事件
			Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * 处理登录成功需要符合什么条件
	 */
	private void LoginAccessNet() {
		// TODO Auto-generated method stub
		if (name.getText().toString().trim() == null
				|| name.getText().toString().trim().equals("")) {
			ToastUtils.showShort(LoginActivity.this, "用户名/手机号不能为空！");
			return;
		} else if (name.getText().toString().trim().length() > 11) {
			ToastUtils.showShort(LoginActivity.this, "用户名/手机号不能超过11位！");
			return;
		}
		if (password.getText().toString().trim() == null
				|| password.getText().toString().trim().equals("")) {
			ToastUtils.showShort(LoginActivity.this, "密码不能为空！");
			return;
		}
		if (password.getText().toString().trim().length() < 6) {
			ToastUtils.showShort(LoginActivity.this, "密码不能小于6位！");
			return;
		}
		if (password.getText().toString().trim().length() > 12) {
			ToastUtils.showShort(LoginActivity.this, "密码不能超过12位！");
			return;
		}
		utils.hideSoftInput(LoginActivity.this);
		if (!TextUtils.isEmpty(name.getText().toString().trim())
				&& !TextUtils.isEmpty(password.getText().toString().trim())) {
			doLogin(name.getText().toString().trim(), password.getText()
					.toString().trim());
		}
	}

	private void doLogin(String username, String password) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				progressDialog.show();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				progressDialog.dismiss();
			}
			
			@Override
			public void onSuccess(String response) {
				System.out.println(response);
				parseUser(response);
				// TODO Auto-generated method stub
				try {
					JSONObject obj = new JSONObject(response);
					if (obj.getInt("result") == 1) {
						ToastUtils.showShort(getApplicationContext(), "登录成功！");
						Intent intent = new Intent(LoginActivity.this,
								CheifActivity.class);
						startActivity(intent);
						LoginActivity.this.finish();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.doHttpLogin(username, password, res);
	}

	protected void parseUser(String response) {
		// TODO Auto-generated method stub
		try {
			JSONObject result = new JSONObject(response);
			JSONObject obj = result.getJSONObject("user");
			AppLog.i(TAG, "用户数据:"+obj);
			PersonInfoBean personInfoBean=GsonUtlity.getPersonInfoBean(obj.toString());
			WKApplication.getInstance().setPersonInfoBean(personInfoBean);
			if (obj != null) {
				id = obj.getString("id");
				tel = obj.getString("username");
				pwd = obj.getString("password");
			}
			Log.i("response  id==", id);
			Log.i("response  tel==", tel);
			Log.i("response  pwd==", pwd);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void setView(){
		PersonInfoBean personInfoBean=WKApplication.getInstance().getPersonInfoBean();
		if (personInfoBean==null) {
			return;
		}
		
		name.setText(personInfoBean.getTel());
		password.setText(personInfoBean.getPassword());
		
	}

}
