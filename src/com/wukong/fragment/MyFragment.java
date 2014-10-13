package com.wukong.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.R;
import com.wukong.data.AddressModel;
import com.wukong.login.LoginActivity;
import com.wukong.my.MyAddressActivity;
import com.wukong.my.MyDataActivity;
import com.wukong.my.MyOrderActivity;
import com.wukong.my.MyRouteActivity;
import com.wukong.utils.WKHttpClient;

public class MyFragment extends Fragment implements OnClickListener {
	// ��Ҫ�����ж�����ɱ����Fragment������������activity���Ƶ����⣬������ڽ�������ص�������
	private boolean isMy = false;
	private TextView headbar_txt;// ͷ����������
	/******** ͷ����߲��� ********/
	private RelativeLayout headbar_left_layout;// ��߸��ؼ�
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	/******** ͷ�����沼�� ********/
	// private RelativeLayout headbar_right_layout;// �ұ߸��ؼ�
	// private ImageView headbar_right_image;// ����ұ�ͼƬ�ؼ�
	// private TextView headbar_right_txt;// �༭�ұ����ݿؼ�

	/******** �ҵ���Ϣ�� ********/
	private ImageView myhead;// ͷ��
	private TextView name;// ����
	private TextView balance;// ���
	/******** �ҵİ����� ********/
	private RelativeLayout my_order;// �ҵĶ���
	private RelativeLayout my_route;// �ҵ�·��
	private RelativeLayout my_data;// �ҵ�����
	private RelativeLayout my_Recharge;// ��ֵ����
	private RelativeLayout my_wukonger;// �������
	private RelativeLayout my_address;// ���õ�ַ
	private RelativeLayout my_share;// ����
	private String id;

	/**
	 * ���󶨵�Activity�Ƿ�ʵ���˽ӿ�
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			isMy = true;
		}
		// ����Fragment�Ƿ񵯳��Լ���صĲ˵���ť
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// �����ҳ�����ļ�
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		id = LoginActivity.id;
		/******* ��ʼ���ؼ� *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		// headbar_right_layout = (RelativeLayout) view
		// .findViewById(R.id.headbar_right_layout);
		// headbar_right_image = (ImageView) view
		// .findViewById(R.id.headbar_right_btn);
		// headbar_right_txt = (TextView) view
		// .findViewById(R.id.headbar_right_txt);
		myhead = (ImageView) view.findViewById(R.id.my_headview);
		name = (TextView) view.findViewById(R.id.my_name);
		balance = (TextView) view.findViewById(R.id.my_balance);
		my_order = (RelativeLayout) view.findViewById(R.id.my_order);
		my_route = (RelativeLayout) view.findViewById(R.id.my_route);
		my_data = (RelativeLayout) view.findViewById(R.id.my_data);
		my_Recharge = (RelativeLayout) view.findViewById(R.id.my_Recharge);
		my_wukonger = (RelativeLayout) view.findViewById(R.id.my_wukonger);
		my_address = (RelativeLayout) view.findViewById(R.id.my_address);
		my_share = (RelativeLayout) view.findViewById(R.id.my_share);
		/******* �ı������ *******/
		headbar_txt.setText("��������");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("����");
		// headbar_right_layout.setVisibility(View.VISIBLE);
		// headbar_right_image.setVisibility(View.GONE);
		// headbar_right_txt.setText("�༭");
		/******* ����¼� *******/
		// headbar_right_layout.setOnClickListener(this);
		my_order.setOnClickListener(this);
		my_route.setOnClickListener(this);
		my_data.setOnClickListener(this);
		my_Recharge.setOnClickListener(this);
		my_wukonger.setOnClickListener(this);
		my_address.setOnClickListener(this);
		my_share.setOnClickListener(this);
		GetUser(id);
		return view;

	}

	private void GetUser(String id) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				Log.i("TAG", "����:" + response);
				parseUser(response);
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.userdetail(id, res);
	}

	protected void parseUser(String response) {
		// TODO Auto-generated method stub
		JSONObject result;
		try {
			result = new JSONObject(response);
			// JSONObject obj = result.getJSONObject("addlist");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		// case R.id.headbar_right_layout:// ͷ���Ҽ��༭����¼�
		// break;
		case R.id.my_order:// �ҵĶ�������¼�
			Intent orderintent = new Intent(getActivity(),
					MyOrderActivity.class);
			startActivity(orderintent);
			break;
		case R.id.my_route:// �ҵ�·�ߵ���¼�
			Intent routeintent = new Intent(getActivity(),
					MyRouteActivity.class);
			startActivity(routeintent);
			break;
		case R.id.my_data:// �ҵ����ϵ���¼�
			Intent dataintent = new Intent(getActivity(), MyDataActivity.class);
			startActivity(dataintent);
			break;
		case R.id.my_Recharge:// �ҵĳ�ֵ���ֵ���¼�
			break;
		case R.id.my_wukonger:// ������յ���¼�
			break;
		case R.id.my_address:// ���õ�ַ����¼�
			Intent addressintent = new Intent(getActivity(),
					MyAddressActivity.class);
			startActivity(addressintent);
			break;
		case R.id.my_share:// �������¼�
			break;

		default:
			break;
		}
	}
}
