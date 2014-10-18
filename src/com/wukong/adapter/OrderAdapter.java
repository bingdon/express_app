package com.wukong.adapter;

import java.util.List;

import com.wukong.R;
import com.wukong.bean.OrderBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private List<OrderBean> list;
	/******* ���캯�� *******/
	public OrderAdapter(Context context) {
		// Log.d(TAG, "GoodsAdapter");
		this.mContext = context;
	}

	public OrderAdapter(Context context,List<OrderBean> list) {
		// Log.d(TAG, "GoodsAdapter");
		this.mContext = context;
		this.list=list;
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
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
					R.layout.order_list, null);
			/** �õ������ؼ��Ķ��� */
			holder.order_time = (TextView) arg1.findViewById(R.id.order_time);// ����ʱ��
			holder.order_from = (TextView) arg1.findViewById(R.id.order_from);// ��������
			holder.order_to = (TextView) arg1.findViewById(R.id.order_to);// �����յ�
			holder.isdone=(ImageView)arg1.findViewById(R.id.isdone);
			arg1.setTag(holder);// ��ViewHolder����
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		OrderBean orderBean=list.get(arg0);
		holder.order_from.setText(orderBean.getS_address());
		holder.order_to.setText(orderBean.getR_address());
		holder.order_time.setText(orderBean.getStarttime());
		if (orderBean.getStatus()==0) {
			holder.isdone.setImageResource(R.drawable.nodone);
		}else if(orderBean.getStatus()==1){
			holder.isdone.setImageResource(R.drawable.isdone);
		}else {
			holder.isdone.setImageResource(R.drawable.isover);
		}
		return arg1;
	}

	/****** ��ſؼ� ******/
	public final class ViewHolder {

		public TextView order_time;
		public TextView order_from;
		public TextView order_to;
		public ImageView isdone;

	}
}
