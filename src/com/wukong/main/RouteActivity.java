package com.wukong.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.adapter.RouteAdapter;

public class RouteActivity extends Activity implements OnClickListener {
	/************* ������ **************/
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	private ListView routelist;
	private RouteAdapter routeadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.route_activity);
		titleView();
		routelist = (ListView) findViewById(R.id.route_listview);
		routeadapter = new RouteAdapter(RouteActivity.this);
		routelist.setAdapter(routeadapter);
	}

	private void titleView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		head_txt.setText("ͬ��·��");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ���ؼ�
			finish();
			break;

		default:
			break;
		}
	}
}
