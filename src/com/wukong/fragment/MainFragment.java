package com.wukong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.main.RouteActivity;
import com.wukong.main.SendActivity;

public class MainFragment extends Fragment implements OnClickListener {
	// ��Ҫ�����ж�����ɱ����Fragment������������activity���Ƶ����⣬������ڽ�������ص�������
	private boolean isMain = false;
	private TextView headbar_txt;// ͷ����������
	/******** ͷ����߲��� ********/
	private RelativeLayout headbar_left_layout;// ��߸��ؼ�
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	/******** ͷ�����沼�� ********/
	// private RelativeLayout headbar_right_layout;// �ұ߸��ؼ�
	// private ImageView headbar_right_image;// ����ұ�ͼƬ�ؼ�
	// private TextView headbar_right_txt;// �༭�ұ����ݿؼ�
	private LinearLayout city_wide_layout;// ͬ�Ƿ���
	private LinearLayout different_places_layout;// ��ط���
	private LinearLayout long_distance_route_layout;// ��;��·
	private LinearLayout city_route_layout;// ͬ����·

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
			isMain = true;
		}
		// ����Fragment�Ƿ񵯳��Լ���صĲ˵���ť
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// �����ҳ�����ļ�
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		/******* ��ʼ���ؼ� *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		// headbar_right_layout = (RelativeLayout) view
		// .findViewById(R.id.headbar_right_layout);
		// headbar_right_image = (ImageView) view
		// .findViewById(R.id.headbar_right_btn);
		// headbar_right_txt = (TextView) view
		// .findViewById(R.id.headbar_right_txt);
		city_wide_layout = (LinearLayout) view
				.findViewById(R.id.city_wide_layout);
		different_places_layout = (LinearLayout) view
				.findViewById(R.id.different_places_layout);
		long_distance_route_layout = (LinearLayout) view
				.findViewById(R.id.long_distance_route_layout);
		city_route_layout = (LinearLayout) view
				.findViewById(R.id.city_route_layout);
		/******* �ı������ *******/
		headbar_txt.setText("��տ��");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("����");
		// headbar_right_layout.setVisibility(View.VISIBLE);
		// headbar_right_image.setVisibility(View.GONE);
		// headbar_right_txt.setText("����");
		/******* ����¼� *******/
		headbar_left_layout.setOnClickListener(this);
		// headbar_right_layout.setOnClickListener(this);
		city_wide_layout.setOnClickListener(this);
		different_places_layout.setOnClickListener(this);
		long_distance_route_layout.setOnClickListener(this);
		city_route_layout.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.city_wide_layout:// ͬ�Ƿ�������¼�
			Intent intent1 = new Intent(getActivity(), SendActivity.class);
			intent1.putExtra("tag", "e1");
			startActivity(intent1);
			break;
		case R.id.different_places_layout:// ��ط�������¼�
			Intent intent = new Intent(getActivity(), SendActivity.class);
			intent.putExtra("tag", "e2");
			startActivity(intent);
			break;
		case R.id.long_distance_route_layout:// ��;��·����¼�
			Intent routeintent1 = new Intent(getActivity(), RouteActivity.class);
			routeintent1.putExtra("tag", "e2");
			startActivity(routeintent1);
			break;
		case R.id.city_route_layout:// ͬ����·����¼�
			Intent routeintent2 = new Intent(getActivity(), RouteActivity.class);
			routeintent2.putExtra("tag", "e1");
			startActivity(routeintent2);
			break;

		default:
			break;
		}
	}

}
