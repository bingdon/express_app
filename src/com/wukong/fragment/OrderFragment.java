package com.wukong.fragment;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.adapter.OrderAdapter;

public class OrderFragment extends Fragment implements OnClickListener,
		OnItemClickListener {
	// ��Ҫ�����ж�����ɱ����Fragment������������activity���Ƶ����⣬������ڽ�������ص�������
	private boolean isOrder = false;
	private TextView headbar_txt;// ͷ����������
	/******** ͷ����߲��� ********/
	private RelativeLayout headbar_left_layout;// ��߸��ؼ�
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	private Button order_left;// ���������
	private Button order_right;// �������Ҽ�
	private TextView order_total;// ��������
	private TextView order_miss;// ����ˬԼ��
	private ListView orderlist;// չʾ�����б�����
	private OrderAdapter orderadapter;// �����б�������

	/**
	 * ���󶨵�Activity�Ƿ�ʵ���˽ӿ�
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			isOrder = true;
		}
		// ����Fragment�Ƿ񵯳��Լ���صĲ˵���ť
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// �����ҳ�����ļ�
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		/******* ��ʼ���ؼ� *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		order_left = (Button) view.findViewById(R.id.order_left_btn);
		order_right = (Button) view.findViewById(R.id.order_right_btn);
		order_total = (TextView) view.findViewById(R.id.order_total);
		order_miss = (TextView) view.findViewById(R.id.order_miss);
		orderlist = (ListView) view.findViewById(R.id.order_listview);
		/******* �ı������ *******/
		headbar_txt.setText("�ҵĶ���");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("����");
		/********* ��ʼ�������� *********/
		// �ı���ʾΪ��ɫ
		ColorStateList corlorGray = OrderFragment.this.getResources()
				.getColorStateList(R.color.gray);
		// �ı���ʾΪ��ɫ
		ColorStateList corlorWhite = OrderFragment.this.getResources()
				.getColorStateList(R.color.white);
		order_left.setBackgroundResource(R.drawable.order_btn1);
		order_left.setTextColor(corlorGray);
		order_right.setBackgroundResource(R.drawable.order_btn2);
		order_right.setTextColor(corlorWhite);
		/********* �����б� *********/
		orderadapter = new OrderAdapter(getActivity());
		orderlist.setAdapter(orderadapter);
		orderlist.setOnItemClickListener(this);
		/********* ����¼� *********/
		order_left.setOnClickListener(this);
		order_right.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// �ı���ʾΪ��ɫ
		ColorStateList corlorGray = OrderFragment.this.getResources()
				.getColorStateList(R.color.gray);
		// �ı���ʾΪ��ɫ
		ColorStateList corlorWhite = OrderFragment.this.getResources()
				.getColorStateList(R.color.white);
		switch (arg0.getId()) {
		case R.id.order_left_btn:// �������������¼�
			order_left.setBackgroundResource(R.drawable.order_btn1);
			order_left.setTextColor(corlorGray);
			order_right.setBackgroundResource(R.drawable.order_btn2);
			order_right.setTextColor(corlorWhite);
			break;
		case R.id.order_right_btn:// �������Ҽ�����¼�
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
