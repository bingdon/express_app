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
	private ArrayList<AddressModel> addressList;// 地址数据
	private LayoutInflater mInflater;

	/******* 构造函数 *******/
	public MyAddressAdapter(Context context, ArrayList<AddressModel> listItem) {
		this.mContext = context;
		this.addressList = listItem;
		this.mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (addressList != null) {// 当前数据不为空
			return addressList.size();// 返回数组的长度
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
		if (arg1 == null) {// 当前View为空
			holder = new ViewHolder();
			arg1 = (View) LayoutInflater.from(mContext).inflate(
					R.layout.myaddress_list, null);
			/** 得到各个控件的对象 */
			holder.address_city = (TextView) arg1
					.findViewById(R.id.myaddress_city);// 城市地址
			holder.address_content = (TextView) arg1
					.findViewById(R.id.myaddress_content);// 详细地址
			holder.address_default = (LinearLayout) arg1
					.findViewById(R.id.default_layout);// 默认地址
			arg1.setTag(holder);// 绑定ViewHolder对象
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

	/****** 存放控件 ******/
	public final class ViewHolder {
		public TextView address_city;
		public TextView address_content;
		public LinearLayout address_default;

	}

}
