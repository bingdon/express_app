package com.wukong.main;

import java.util.ArrayList;
import java.util.List;

import com.wukong.R;
import com.wukong.adapter.TraceAdapter;
import com.wukong.bean.RoutBean;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class TraceActivity extends Activity implements ActivityInitInterface, OnClickListener{
	
	private ListView traceListView;
	private TraceAdapter traceAdapter;
	private List<RoutBean> traceList=new ArrayList<RoutBean>();
	private Context context;
	private TextView head_txt;// 标题头内容
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_trace);
		context=this;
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		traceListView=(ListView)findViewById(R.id.trace_list);
		head_txt.setText(R.string.activity_trace_title);
		findViewById(R.id.headbar_left_layout).setVisibility(View.VISIBLE);
		findViewById(R.id.headbar_left_layout).setOnClickListener(this);
		traceAdapter=new TraceAdapter(traceList, context);
		traceListView.setAdapter(traceAdapter);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch (id) {
		case R.id.headbar_left_layout:
			finish();
			break;

		default:
			break;
		}
	}
	
}
