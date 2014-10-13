package com.wukong.my;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.adapter.OrderAdapter;
import com.wukong.fragment.OrderFragment;

public class MyOrderActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	private Button order_left;// ���������
	private Button order_right;// �������Ҽ�
	private ListView orderlist;// չʾ�����б�����
	private OrderAdapter orderadapter;// �����б�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myorder_activity);
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		order_left = (Button) findViewById(R.id.myorder_left_btn);
		order_right = (Button) findViewById(R.id.myorder_right_btn);
		orderlist = (ListView) findViewById(R.id.myorder_listview);
		head_txt.setText("�ҵĶ���");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
		left_layout.setVisibility(View.VISIBLE);
		// �ı���ʾΪ��ɫ
		ColorStateList corlorGray = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.gray);
		// �ı���ʾΪ��ɫ
		ColorStateList corlorWhite = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.white);
		order_left.setBackgroundResource(R.drawable.order_btn1);
		order_left.setTextColor(corlorGray);
		order_right.setBackgroundResource(R.drawable.order_btn2);
		order_right.setTextColor(corlorWhite);
		/********* ����¼� *********/
		order_left.setOnClickListener(this);
		order_right.setOnClickListener(this);
		left_layout.setOnClickListener(this);
		/********* �����б� *********/
		orderadapter = new OrderAdapter(MyOrderActivity.this);
		orderlist.setAdapter(orderadapter);
		orderlist.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// �ı���ʾΪ��ɫ
		ColorStateList corlorGray = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.gray);
		// �ı���ʾΪ��ɫ
		ColorStateList corlorWhite = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.white);
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// ���ؼ�
			finish();
			break;
		case R.id.myorder_left_btn:// �������������¼�
			order_left.setBackgroundResource(R.drawable.order_btn1);
			order_left.setTextColor(corlorGray);
			order_right.setBackgroundResource(R.drawable.order_btn2);
			order_right.setTextColor(corlorWhite);
			break;
		case R.id.myorder_right_btn:// �������Ҽ�����¼�
			order_left.setBackgroundResource(R.drawable.order_btn1_pressed);
			order_left.setTextColor(corlorWhite);
			order_right.setBackgroundResource(R.drawable.order_btn2_pressed);
			order_right.setTextColor(corlorGray);
			break;
		default:
			break;
		}
	}

	/**
	 * ������Ҫ������ת��ÿ������������ҳ
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
