package com.wukong.adapter;

import com.wukong.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private Context mContext;

	/******* 构造函数 *******/
	public OrderAdapter(Context context) {
		// Log.d(TAG, "GoodsAdapter");
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (arg1 == null) {// 当前View为空
			holder = new ViewHolder();
			arg1 = (View) LayoutInflater.from(mContext).inflate(
					R.layout.order_list, null);
			/** 得到各个控件的对象 */
			holder.order_time = (TextView) arg1.findViewById(R.id.order_time);// 订单时间
			holder.order_from = (TextView) arg1.findViewById(R.id.order_from);// 订单出发
			holder.order_to = (TextView) arg1.findViewById(R.id.order_to);// 订单终点
			arg1.setTag(holder);// 绑定ViewHolder对象
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		return arg1;
	}

	/****** 存放控件 ******/
	public final class ViewHolder {

		public TextView order_time;
		public TextView order_from;
		public TextView order_to;

	}
}
