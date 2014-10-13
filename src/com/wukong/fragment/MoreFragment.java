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
	// 主要用来判定程序被杀死后Fragment重新启动后不受activity控制的问题，归根在于解决界面重叠的问题
	private boolean isMore = false;
	private TextView headbar_txt;// 头部标题内容
	/******** 头部左边布局 ********/
	private RelativeLayout headbar_left_layout;// 左边父控件
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	/******** 更多帮助栏 ********/
	private RelativeLayout more_freight;// 运费查询
	private RelativeLayout more_send_receive;// 收送范围
	private RelativeLayout more_ban;// 禁寄品查询
	private RelativeLayout more_value;// 增值服务
	private RelativeLayout more_suggest;// 提建议
	private RelativeLayout more_about;// 关于悟空
	private RelativeLayout more_contact;// 联系悟空

	/**
	 * 检查绑定的Activity是否实现了接口
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
		// 决定Fragment是否弹出自己相关的菜单按钮
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 添加首页布局文件
		View view = inflater.inflate(R.layout.fragment_more, container, false);
		/******* 初始化控件 *******/
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
		/******* 改变标题栏 *******/
		headbar_txt.setText("更多工具");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("北京");
		/******* 点击事件 *******/
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
		case R.id.more_freight:// 运费查询
			Intent freightintent = new Intent(getActivity(),
					FreightActivity.class);
			startActivity(freightintent);
			break;
		case R.id.more_send_receive:// 收送范围
			Intent sendintent = new Intent(getActivity(),
					SendReceiveActivity.class);
			startActivity(sendintent);
			break;
		case R.id.more_ban:// 禁寄品查询
			Intent banintent = new Intent(getActivity(), BanActivity.class);
			startActivity(banintent);
			break;
		case R.id.more_value:// 增值服务
			break;
		case R.id.more_suggest:// 提建议
			Intent suggestintent = new Intent(getActivity(),
					SuggestActivity.class);
			startActivity(suggestintent);
			break;
		case R.id.more_about:// 关于悟空
			Intent aboutintent = new Intent(getActivity(), AboutActivity.class);
			startActivity(aboutintent);
			break;
		case R.id.more_contact:// 联系悟空
			Intent contactintent = new Intent(getActivity(),
					ContactActivity.class);
			startActivity(contactintent);
			break;
		default:
			break;
		}
	}
}
