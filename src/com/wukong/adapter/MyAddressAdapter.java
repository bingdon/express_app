package com.wukong.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.data.AddressModel;

public class MyAddressAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<AddressModel> addressList;// ��ַ����
	private LayoutInflater mInflater;

	/******* ���캯�� *******/
	public MyAddressAdapter(Context context, ArrayList<AddressModel> listItem) {
		this.mContext = context;
		this.addressList = listItem;
		this.mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (addressList != null) {// ��ǰ���ݲ�Ϊ��
			return addressList.size();// ��������ĳ���
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		if (addressList != null) {
			return addressList.get(arg0);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (arg1 == null) {// ��ǰViewΪ��
			holder = new ViewHolder();
			arg1 = (View) LayoutInflater.from(mContext).inflate(
					R.layout.myaddress_list, null);
			/** �õ������ؼ��Ķ��� */
			holder.address_city = (TextView) arg1
					.findViewById(R.id.myaddress_city);// ���е�ַ
			holder.address_content = (TextView) arg1
					.findViewById(R.id.myaddress_content);// ��ϸ��ַ
			holder.address_default = (LinearLayout) arg1
					.findViewById(R.id.default_layout);// Ĭ�ϵ�ַ
			arg1.setTag(holder);// ��ViewHolder����
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		if (addressList != null) {
			holder.address_city.setText(addressList.get(arg0).getArea());
			holder.address_content.setText(addressList.get(arg0).getDetail());
			if (addressList.get(arg0).isChecked() == true) {
				holder.address_default.setVisibility(View.VISIBLE);
			} else {
				holder.address_default.setVisibility(View.GONE);
			}
		}
		return arg1;
	}

	/****** ��ſؼ� ******/
	public final class ViewHolder {
		public TextView address_city;
		public TextView address_content;
		public LinearLayout address_default;

	}

}
