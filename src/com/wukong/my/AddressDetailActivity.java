package com.wukong.my;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
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
import com.wukong.data.AddressModel;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class AddressDetailActivity extends Activity implements OnClickListener {
	private TextView headbar_txt;// ͷ����������
	/******** ͷ����߲��� ********/
	private RelativeLayout headbar_left_layout;// ��߸��ؼ�
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	/******** ͷ�����沼�� ********/
	private RelativeLayout headbar_right_layout;// �ұ߸��ؼ�
	private ImageView headbar_right_image;// ����ұ�ͼƬ�ؼ�
	private TextView headbar_right_txt;// �༭�ұ����ݿؼ�
	/******** ��ַ����༭���� ********/
	private EditText reciver;// �ռ���
	private EditText tel;// �绰
	private EditText zipcode;// �ʱ�
	private EditText area;// ����
	private EditText detail;// ��ϸ��ַ
	private TextView delete;// ɾ��
	private Button setdefault;// ����Ĭ��

	private String id;// ��ַid
	private boolean isdefault = false;// �����Ƿ���Ĭ��ѡ��
	private AddressModel address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addressdetail_activity);
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		address = new AddressModel();
		addDetail(id);

	}

	private void addDetail(String id) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				Log.i("TAG", "����:" + response);
				parseaddressdetail(response);
				initView();
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.addDetail(id, res);
	}

	protected void parseaddressdetail(String response) {
		// TODO Auto-generated method stub
		JSONObject result;
		try {
			result = new JSONObject(response);
			JSONObject obj = result.getJSONObject("addDetail");

			address.setId(obj.getString("id"));
			address.setReciver(obj.getString("reciver"));
			address.setTel(obj.getString("tel"));
			address.setZipcode(obj.getString("zipcode"));
			address.setArea(obj.getString("area"));
			address.setDetail(obj.getString("detail"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		headbar_txt = (TextView) findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		headbar_right_layout = (RelativeLayout) findViewById(R.id.headbar_right_layout);
		headbar_right_image = (ImageView) findViewById(R.id.headbar_right_btn);
		headbar_right_txt = (TextView) findViewById(R.id.headbar_right_txt);
		delete = (TextView) findViewById(R.id.delete_address);
		reciver = (EditText) findViewById(R.id.receive_name);
		tel = (EditText) findViewById(R.id.receive_phonenum);
		zipcode = (EditText) findViewById(R.id.receive_ucode);
		area = (EditText) findViewById(R.id.receive_area);
		detail = (EditText) findViewById(R.id.receive_address);
		setdefault = (Button) findViewById(R.id.default_address_btn);
		/******* ��ʼ���������� *******/
		reciver.setText(address.getReciver());
		tel.setText(address.getTel());
		zipcode.setText(address.getZipcode());
		area.setText(address.getArea());
		detail.setText(address.getDetail());
		/******* �ı������ *******/
		headbar_txt.setText("��ַ����");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		headbar_right_layout.setVisibility(View.VISIBLE);
		headbar_right_image
				.setBackgroundResource(R.drawable.address_right_selector);
		headbar_right_txt.setVisibility(View.GONE);
		headbar_left_layout.setOnClickListener(this);
		headbar_right_layout.setOnClickListener(this);
		delete.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// �����»���
		setdefault.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ���ؼ�
			AddressDetailActivity.this.setResult(RESULT_OK);
			AddressDetailActivity.this.finish();
			break;
		case R.id.headbar_right_layout:// �༭��
			if (headbar_right_txt.isShown()) {
				UpdateAddress();
			} else {
				reciver.setEnabled(true);
				tel.setEnabled(true);
				zipcode.setEnabled(true);
				area.setEnabled(true);
				detail.setEnabled(true);
				headbar_right_image.setVisibility(View.GONE);
				headbar_right_txt.setVisibility(View.VISIBLE);
				headbar_right_txt.setText("���");
			}
			break;
		case R.id.delete_address:// ɾ����
			deleteAddress(id);
			break;
		case R.id.default_address_btn:// ����Ĭ�ϼ�
			
			break;
		default:
			break;
		}
	}

	private void UpdateAddress() {
		// TODO Auto-generated method stub
		if (reciver.getText().toString() == null
				|| reciver.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressDetailActivity.this, "�ռ��˲���Ϊ�գ�");
			return;
		}
		if (tel.getText().toString() == null
				|| tel.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressDetailActivity.this, "�ֻ��Ų���Ϊ�գ�");
			return;
		}
		if (tel.getText().toString().trim().length() != 11) {
			ToastUtils.showShort(AddressDetailActivity.this, "�ֻ��ű�����11λ��");
			return;
		}
		if (zipcode.getText().toString() == null
				|| zipcode.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressDetailActivity.this, "�ʱ಻��Ϊ�գ�");
			return;
		}

		if (zipcode.getText().toString().trim().length() != 6) {
			ToastUtils.showShort(AddressDetailActivity.this, "�ʱ����6λ��");
			return;
		}
		if (area.getText().toString() == null
				|| area.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressDetailActivity.this, "���ڵ�������Ϊ�գ�");
			return;
		}
		if (detail.getText().toString() == null
				|| detail.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressDetailActivity.this, "��ַ���鲻��Ϊ�գ�");
			return;
		}
		updateAddress(id, reciver.getText().toString().trim(), tel.getText()
				.toString().trim(), zipcode.getText().toString().trim(), area
				.getText().toString().trim(), detail.getText().toString()
				.trim());
	}

	private void updateAddress(String id, String reciver1, String tel1,
			String zipcode1, String area1, String detail1) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils.showShort(AddressDetailActivity.this,
								"�޸ĵ�ַ�ɹ���");
						reciver.setEnabled(false);
						tel.setEnabled(false);
						zipcode.setEnabled(false);
						area.setEnabled(false);
						detail.setEnabled(false);
						headbar_right_image.setVisibility(View.VISIBLE);
						headbar_right_image
								.setBackgroundResource(R.drawable.address_right_selector);
						headbar_right_txt.setVisibility(View.GONE);
					} else {
						ToastUtils.showShort(AddressDetailActivity.this,
								"�޸ĵ�ַʧ�ܣ�");
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
		client.updateAddress(id, reciver1, tel1, zipcode1, area1, detail1, res);
	}

	private void deleteAddress(String id) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {

			public void onSuccess(String response) {

				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils.showShort(AddressDetailActivity.this,
								"ɾ����ַ�ɹ���");
						AddressDetailActivity.this.setResult(RESULT_OK);
						AddressDetailActivity.this.finish();
					} else {
						ToastUtils.showShort(AddressDetailActivity.this,
								"ɾ����ַʧ��");
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
		client.deleteAddress(id, res);
	}
}
