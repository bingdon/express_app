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
 * @Description:�һ�����ҳ
 * @Author: zc
 * @CreateDate:
 * @ReviseUser:
 * @ReviseDate:
 * @ReviseRemark:
 * @version V1.0.0
 */
public class ForgetActivity extends Activity implements OnClickListener {
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	private EditText phone;// �ֻ���
	private EditText code;// ��֤��
	private EditText password;// ��������
	private EditText sure_password;// ȷ������
	private Button send_code;// ������֤��
	private Button submit;// �ύ
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
	 * ��ʼ���ؼ�
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
		head_txt.setText("ע��");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		send_code.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ͷ�����ؼ��ĵ���¼�
			finish();
			break;
		case R.id.send_code:// ������֤�����¼�

			break;
		case R.id.forget_submit:// �ύ����¼�
			ForgetAccessNet();
			break;
		default:
			break;
		}
	}

	/**
	 * �ж��Ƿ��ύ�ɹ�
	 */
	private void ForgetAccessNet() {
		// TODO Auto-generated method stub
		if (phone.getText().toString() == null
				|| phone.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "�ֻ��Ų���Ϊ�գ�");
			return;
		} else if (phone.getText().toString().trim().length() != 11) {
			ToastUtils.showShort(ForgetActivity.this, "�ֻ��ű�����11λ��");
			return;
		}
		if (code.getText().toString() == null
				|| code.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "��֤�벻��Ϊ�գ�");
			return;
		}
		if (password.getText().toString() == null
				|| password.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "���벻��Ϊ�գ�");
			return;
		}
		if (sure_password.getText().toString() == null
				|| sure_password.getText().toString().trim().equals("")) {
			ToastUtils.showShort(ForgetActivity.this, "ȷ�����벻��Ϊ�գ�");
			return;
		} else if (!sure_password.getText().toString().trim()
				.equals(password.getText().toString().trim())) {
			ToastUtils.showShort(ForgetActivity.this, "�������벻һ�£�");
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
						ToastUtils.showShort(ForgetActivity.this, "ע��ɹ���");
						ForgetActivity.this.finish();
					} else {
						ToastUtils.showShort(ForgetActivity.this, "ע��ʧ��");
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
