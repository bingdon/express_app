package com.wukong.more;

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
import com.wukong.login.ForgetActivity;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class SuggestActivity extends Activity implements OnClickListener {
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	private EditText content;// ������ݱ༭
	private EditText email;// ����༭
	private Button submit;// �ύ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.suggest_activity);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		content = (EditText) findViewById(R.id.suggest_content);
		email = (EditText) findViewById(R.id.suggest_emails);
		submit = (Button) findViewById(R.id.suggest_submit);
		head_txt.setText("�������");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ���ؼ�
			finish();
			break;
		case R.id.suggest_submit:// �ύ��
			HttpAdvise();
			break;

		default:
			break;
		}
	}

	private void HttpAdvise() {
		// TODO Auto-generated method stub
		if (content.getText().toString() == null
				|| content.getText().toString().trim().equals("")) {
			ToastUtils.showShort(SuggestActivity.this, "������ݲ���Ϊ�գ�");
			return;
		}
		if (content.getText().toString().trim().length() > 130) {
			ToastUtils.showShort(SuggestActivity.this, "��������������130���֣�");
			return;
		}
		if (email.getText().toString() == null
				|| email.getText().toString().trim().equals("")) {
			ToastUtils.showShort(SuggestActivity.this, "���䲻��Ϊ�գ�");
			return;
		}
		if (!email.getText().toString().trim()
				.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
			ToastUtils.showShort(SuggestActivity.this, "�����ַ����ȷ��");
			return;
		}
		doHttpAdvise(email.getText().toString().trim(), content.getText()
				.toString().trim());
	}

	private void doHttpAdvise(String email, String content) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils.showShort(SuggestActivity.this, "�ύ����ɹ���");
						SuggestActivity.this.finish();
					} else {
						ToastUtils.showShort(SuggestActivity.this, "�ύ����ʧ�ܣ�");
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
		client.doHttpAdvise(email, content, res);
	}
}
