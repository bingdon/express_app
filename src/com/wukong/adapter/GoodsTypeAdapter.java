package com.wukong.adapter;

import java.util.List;

import com.wukong.R;
import com.wukong.bean.GoodsTypeBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsTypeAdapter extends BaseAdapter {

	private List<GoodsTypeBean> list;
	
	private Context context;
	
	private LayoutInflater inflater;
	
	public GoodsTypeAdapter(List<GoodsTypeBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.goods_type_item, parent, false);
			holder.goodsname=(TextView)convertView.findViewById(R.id.goodsname);
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder)convertView.getTag();
		}
		holder.goodsname.setText(list.get(position).getName());
		return convertView;
	}
	
	public class ViewHolder{
		public TextView goodsname;
	}
	

}
