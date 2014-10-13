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

	/******* ���캯�� *******/
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
		if (arg1 == null) {// ��ǰViewΪ��
			holder = new ViewHolder();
			arg1 = (View) LayoutInflater.from(mContext).inflate(
					R.layout.order_list, null);
			/** �õ������ؼ��Ķ��� */
			holder.order_time = (TextView) arg1.findViewById(R.id.order_time);// ����ʱ��
			holder.order_from = (TextView) arg1.findViewById(R.id.order_from);// ��������
			holder.order_to = (TextView) arg1.findViewById(R.id.order_to);// �����յ�
			arg1.setTag(holder);// ��ViewHolder����
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		return arg1;
	}

	/****** ��ſؼ� ******/
	public final class ViewHolder {

		public TextView order_time;
		public TextView order_from;
		public TextView order_to;

	}
}
