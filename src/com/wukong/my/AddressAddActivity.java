package com.wukong.my;

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
import com.wukong.WKApplication;
import com.wukong.login.LoginActivity;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class AddressAddActivity extends Activity implements OnClickListener {
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�

	/********** ������ַ���� ***********/
	private EditText reciver;// �ռ���
	private EditText tel;// �绰
	private EditText zipcode;// �ʱ�
	private EditText area;// ����
	private EditText detail;// ��ϸ��ַ
	private Button save;// ����
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addaddress_activity);
		id = WKApplication.getInstance().getPersonInfoBean().getId();
		InitView();
	}

	private void InitView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		reciver = (EditText) findViewById(R.id.receive_name);
		tel = (EditText) findViewById(R.id.receive_phonenum);
		zipcode = (EditText) findViewById(R.id.receive_ucode);
		area = (EditText) findViewById(R.id.receive_area);
		detail = (EditText) findViewById(R.id.receive_address);
		save = (Button) findViewById(R.id.add_address_save);
		save.setOnClickListener(this);
		head_txt.setText("��ӵ�ַ");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ���ؼ�
			finish();
			break;
		case R.id.add_address_save:// �����
			httpAddress();
			break;
		default:
			break;
		}
	}

	private void httpAddress() {
		// TODO Auto-generated method stub
		if (reciver.getText().toString() == null
				|| reciver.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "�ռ��˲���Ϊ�գ�");
			return;
		}
		if (tel.getText().toString() == null
				|| tel.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "�ֻ��Ų���Ϊ�գ�");
			return;
		}
		if (tel.getText().toString().trim().length() != 11) {
			ToastUtils.showShort(AddressAddActivity.this, "�ֻ��ű�����11λ��");
			return;
		}
		if (zipcode.getText().toString() == null
				|| zipcode.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "�ʱ಻��Ϊ�գ�");
			return;
		}

		if (zipcode.getText().toString().trim().length() != 6) {
			ToastUtils.showShort(AddressAddActivity.this, "�ʱ����6λ��");
			return;
		}
		if (area.getText().toString() == null
				|| area.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "���ڵ�������Ϊ�գ�");
			return;
		}
		if (detail.getText().toString() == null
				|| detail.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "��ַ���鲻��Ϊ�գ�");
			return;
		}
		postAddress(id, reciver.getText().toString().trim(), tel.getText()
				.toString().trim(), zipcode.getText().toString().trim(), area
				.getText().toString().trim(), detail.getText().toString()
				.trim());
	}

	private void postAddress(String id, String reciver, String tel,
			String zipcode, String area, String detail) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils
								.showShort(AddressAddActivity.this, "��ӵ�ַ�ɹ���");
						AddressAddActivity.this.setResult(RESULT_OK);
						AddressAddActivity.this.finish();
					} else {
						ToastUtils
								.showShort(AddressAddActivity.this, "��ӵ�ַʧ�ܣ�");
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
		client.postAddress(id, reciver, tel, zipcode, area, detail, res);
	}
}
