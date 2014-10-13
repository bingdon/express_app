package com.wukong.fragment;

import com.wukong.R;
import com.wukong.more.AboutActivity;
import com.wukong.more.BanActivity;
import com.wukong.more.ContactActivity;
import com.wukong.more.FreightActivity;
import com.wukong.more.SendReceiveActivity;
import com.wukong.more.SuggestActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MoreFragment extends Fragment implements OnClickListener {
	// ��Ҫ�����ж�����ɱ����Fragment������������activity���Ƶ����⣬������ڽ�������ص�������
	private boolean isMore = false;
	private TextView headbar_txt;// ͷ����������
	/******** ͷ����߲��� ********/
	private RelativeLayout headbar_left_layout;// ��߸��ؼ�
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	/******** ��������� ********/
	private RelativeLayout more_freight;// �˷Ѳ�ѯ
	private RelativeLayout more_send_receive;// ���ͷ�Χ
	private RelativeLayout more_ban;// ����Ʒ��ѯ
	private RelativeLayout more_value;// ��ֵ����
	private RelativeLayout more_suggest;// �Ὠ��
	private RelativeLayout more_about;// �������
	private RelativeLayout more_contact;// ��ϵ���

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
			isMore = true;
		}
		// ����Fragment�Ƿ񵯳��Լ���صĲ˵���ť
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// �����ҳ�����ļ�
		View view = inflater.inflate(R.layout.fragment_more, container, false);
		/******* ��ʼ���ؼ� *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		more_freight = (RelativeLayout) view.findViewById(R.id.more_freight);
		more_send_receive = (RelativeLayout) view
				.findViewById(R.id.more_send_receive);
		more_ban = (RelativeLayout) view.findViewById(R.id.more_ban);
		more_value = (RelativeLayout) view.findViewById(R.id.more_value);
		more_suggest = (RelativeLayout) view.findViewById(R.id.more_suggest);
		more_about = (RelativeLayout) view.findViewById(R.id.more_about);
		more_contact = (RelativeLayout) view.findViewById(R.id.more_contact);
		/******* �ı������ *******/
		headbar_txt.setText("���๤��");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("����");
		/******* ����¼� *******/
		more_freight.setOnClickListener(this);
		more_send_receive.setOnClickListener(this);
		more_ban.setOnClickListener(this);
		more_value.setOnClickListener(this);
		more_suggest.setOnClickListener(this);
		more_about.setOnClickListener(this);
		more_contact.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.more_freight:// �˷Ѳ�ѯ
			Intent freightintent = new Intent(getActivity(),
					FreightActivity.class);
			startActivity(freightintent);
			break;
		case R.id.more_send_receive:// ���ͷ�Χ
			Intent sendintent = new Intent(getActivity(),
					SendReceiveActivity.class);
			startActivity(sendintent);
			break;
		case R.id.more_ban:// ����Ʒ��ѯ
			Intent banintent = new Intent(getActivity(), BanActivity.class);
			startActivity(banintent);
			break;
		case R.id.more_value:// ��ֵ����
			break;
		case R.id.more_suggest:// �Ὠ��
			Intent suggestintent = new Intent(getActivity(),
					SuggestActivity.class);
			startActivity(suggestintent);
			break;
		case R.id.more_about:// �������
			Intent aboutintent = new Intent(getActivity(), AboutActivity.class);
			startActivity(aboutintent);
			break;
		case R.id.more_contact:// ��ϵ���
			Intent contactintent = new Intent(getActivity(),
					ContactActivity.class);
			startActivity(contactintent);
			break;
		default:
			break;
		}
	}
}
