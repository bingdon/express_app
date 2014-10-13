package com.wukong.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.R;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

/**
 * 
 * @ClassName: WelcomeActivity
 * @Description:找回密码页
 * @Author: zc
 * @CreateDate:
 * @ReviseUser:
 * @ReviseDate:
 * @ReviseRemark:
 * @version V1.0.0
 */
public class ForgetActivity extends Activity implements OnClickListener {
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private EditText phone;// 手机号
	private EditText code;// 验证码
	private EditText password;// 设置密码
	private EditText sure_password;// 确认密码
	private Button send_code;// 发送验证码
	private Button submit;// 提交
	public static String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_activity);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		phone = (EditText) findViewById(R.id.forget_phonenum);
		code = (EditText) findViewById(R.id.forget_code);
		password = (EditText) findViewById(R.id.set_passwords);
		sure_password = (EditText) findViewById(R.id.sure_passwords);
		send_code = (Button) findViewById(R.id.send_code);
		submit = (Button) findViewById(R.id.forget_submit);
		head_txt.setText("注册");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		send_code.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 头部返回键的点击事件
			finish();
			break;
		case R.id.send_code:// 发送验证码点击事件

			break;
		case R.id.forget_submit:// 提交点击事件
			ForgetAccessNet();
			break;
		default:
			break;
		}
	}

	/**
	 * 判断是否提交成功
	 */
	private void ForgetAccessNet() {
		// TODO Auto-generated method stub
		if (phone.getText().toString() == null
				|| phone.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "手机号不能为空！");
			return;
		} else if (phone.getText().toString().trim().length() != 11) {
			ToastUtils.showShort(ForgetActivity.this, "手机号必须是11位！");
			return;
		}
		if (code.getText().toString() == null
				|| code.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "验证码不能为空！");
			return;
		}
		if (password.getText().toString() == null
				|| password.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "密码不能为空！");
			return;
		}
		if (sure_password.getText().toString() == null
				|| sure_password.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "确认密码不能为空！");
			return;
		} else if (!sure_password.getText().toString().trim()
				.equals(password.getText().toString().trim())) {
			ToastUtils.showShort(ForgetActivity.this, "两次密码不一致！");
			return;
		}
		doHttpForget(phone.getText().toString().trim(), password.getText()
				.toString().trim());
	}

	private void doHttpForget(String username, String password) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {

			public void onSuccess(String response) {
				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils.showShort(ForgetActivity.this, "注册成功！");
						ForgetActivity.this.finish();
					} else {
						ToastUtils.showShort(ForgetActivity.this, "注册失败");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable error, String response) {
				// TODO Auto-generated method stub
				super.onFailure(error, response);
			}

			@Override
			public void onFailure(Throwable error) {
				// TODO Auto-generated method stub
				super.onFailure(error);
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.doHttpForgetbytel(username, password, res);
	}

}
