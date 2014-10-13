package com.wukong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wukong.R;

public class RouteAdapter extends BaseAdapter {
	private Context mContext;

	/******* 构造函数 *******/
	public RouteAdapter(Context context) {
		// Log.d(TAG, "GoodsAdapter");
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
					R.layout.route_list, null);
			/** 得到各个控件的对象 */
			holder.route_head = (ImageView) arg1
					.findViewById(R.id.route_myhead);// 头像
			holder.route_name = (TextView) arg1
					.findViewById(R.id.route_receive_name);// 发件人
			holder.route_from_to = (TextView) arg1
					.findViewById(R.id.route_from_to);// 地址
			holder.route_time = (TextView) arg1.findViewById(R.id.route_time);// 时间
			holder.route_info = (TextView) arg1
					.findViewById(R.id.route_goodsinfo);// 信息
			holder.route_tel = (TextView) arg1.findViewById(R.id.route_tel);// 电话
			arg1.setTag(holder);// 绑定ViewHolder对象
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		return arg1;
	}

	/****** 存放控件 ******/
	public final class ViewHolder {
		public ImageView route_head;
		public TextView route_name;
		public TextView route_from_to;
		public TextView route_time;
		public TextView route_info;
		public TextView route_tel;

	}
}
