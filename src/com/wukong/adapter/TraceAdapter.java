package com.wukong.adapter;

import java.util.List;

import com.wukong.R;
import com.wukong.bean.RoutBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TraceAdapter extends BaseAdapter {

	private List<RoutBean> list;
	private Context context;
	private LayoutInflater inflater;

	public TraceAdapter(List<RoutBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.trace_item, parent, false);
			holder.time = (TextView) convertView.findViewById(R.id.trace_time);
			holder.content = (TextView) convertView
					.findViewById(R.id.trace_content);
			holder.statue = (ImageView) convertView
					.findViewById(R.id.trace_statue);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.time.setText("十月十日");
		holder.content.setText("开始走了");
		return convertView;
	}

	public class ViewHolder {
		public TextView time;
		public TextView content;
		public ImageView statue;
	}

}
