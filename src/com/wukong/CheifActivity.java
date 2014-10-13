package com.wukong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.wukong.fragment.MainFragment;
import com.wukong.fragment.MoreFragment;
import com.wukong.fragment.MyFragment;
import com.wukong.fragment.NearFragment;
import com.wukong.fragment.OrderFragment;
import com.wukong.utils.Constants;

/**
 * @ClassName: CheifActivity
 * @Description:Ӧ�ó���������棬�����ӽ��������Fragment��ʵ����
 * @author zc
 * @date
 * 
 */
public class CheifActivity extends FragmentActivity implements OnClickListener {
	private ImageView bottom_main;// ��ҳ�˵�
	private ImageView bottom_order;// �����˵�
	private ImageView bottom_my;// �ҵĲ˵�
	private ImageView bottom_near;// �����˵�
	private ImageView bottom_more;// ����˵�
	private MainFragment mainfragment;// ��ҳ����
	private OrderFragment orderfragment;// ��������
	private MyFragment myfragment;// �ҵĽ���
	private NearFragment nearfragment;// ��������
	private MoreFragment morefragment;// �������
	// ����fragment
	private FragmentManager fragmentManager = null;
	// ��ǰ����
	private Fragment currentFragment = null;
	// ���Ƶ�ǰ����ı�־
	private int current = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cheif_activity);
		// ��ʼ���ײ�������
		initNavigatioLayout();
		// ������ʾ��ҳ����
		fragmentManager = this.getSupportFragmentManager();
		if (arg0 != null) {
			current = arg0.getInt("current", -1);
		} else {
			current = -1;
		}
		/************ ��ǰ��������ҳ���� ***********/
		if (current <= 0) {
			mainfragment = new MainFragment();
			currentFragment = mainfragment;
			fragmentManager.beginTransaction()
					.add(R.id.chief_content_frame, currentFragment).commit();
			current = Constants.FRAGMENT_TYPE.MAIN;
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1_pressed);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
		} else if (current == Constants.FRAGMENT_TYPE.MAIN) {
			if (bottom_main != null) {
				switchBottomCorlor(bottom_main);
			}
			mainfragment = new MainFragment();// ʵ����
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, mainfragment).commit();
			currentFragment = mainfragment;// ��ǰ����Ϊ��Ϣ
			current = Constants.FRAGMENT_TYPE.MAIN;// ��ǰ�����־ΪConstants.FRAGMENT_TYPE.MESSAGE
			/************ ��ǰ�����Ƕ������� ***********/
		} else if (current == Constants.FRAGMENT_TYPE.ORDER) {
			if (bottom_order != null) {
				switchBottomCorlor(bottom_order);
			}
			orderfragment = new OrderFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, orderfragment).commit();
			// showTabContent(orderFragment);
			currentFragment = orderfragment;
			current = Constants.FRAGMENT_TYPE.ORDER;
			/************ ��ǰ�������ҵĽ��� ***********/
		} else if (current == Constants.FRAGMENT_TYPE.MY) {
			if (bottom_my != null) {
				switchBottomCorlor(bottom_my);
			}
			myfragment = new MyFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, myfragment).commit();
			currentFragment = myfragment;
			current = Constants.FRAGMENT_TYPE.MY;
			/************ ��ǰ�����Ǹ������� ***********/
		} else if (current == Constants.FRAGMENT_TYPE.NEAR) {
			if (bottom_near != null) {
				switchBottomCorlor(bottom_near);
			}
			nearfragment = new NearFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, nearfragment).commit();
			currentFragment = nearfragment;
			current = Constants.FRAGMENT_TYPE.NEAR;
			/************ ��ǰ�����Ǹ������Ľ��� ***********/
		} else if (current == Constants.FRAGMENT_TYPE.MORE) {
			if (bottom_more != null) {
				switchBottomCorlor(bottom_more);
			}
			morefragment = new MoreFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, morefragment).commit();
			currentFragment = morefragment;
			current = Constants.FRAGMENT_TYPE.MORE;
		}
	}

	/********** ����onSaveInstanceState()��������Activity״̬ ***********/
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putInt("current", current);// ���浱ǰ�л���״̬
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// Log.d(TAG, "this cheif is onPause");
	}

	/***
	 * 
	 * ����˵���� ���Ƚ��е��ǵײ��������ĳ�ʼ��
	 * 
	 * @Title: initNavigatioLayout
	 * @Description: ��ʼ���ײ�������
	 * @return void ��������
	 * @exception
	 * @throws
	 */
	private void initNavigatioLayout() {
		// TODO Auto-generated method stub
		bottom_main = (ImageView) findViewById(R.id.app_bottom_main);
		bottom_order = (ImageView) findViewById(R.id.app_bottom_order);
		bottom_my = (ImageView) findViewById(R.id.app_bottom_my);
		bottom_near = (ImageView) findViewById(R.id.app_bottom_near);
		bottom_more = (ImageView) findViewById(R.id.app_bottom_more);
		bottom_main.setOnClickListener(this);
		bottom_order.setOnClickListener(this);
		bottom_my.setOnClickListener(this);
		bottom_near.setOnClickListener(this);
		bottom_more.setOnClickListener(this);
	}

	/**
	 * �ײ��������ĵ���¼�
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.app_bottom_main:// ��ҳ����¼�
			if (!(currentFragment instanceof MainFragment)) {// �����ǰ������ҳҳ��
				/*********** �����ǰ����ҳҳ�� ***********/
				mainfragment = new MainFragment();// ʵ������ҳҳ��
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, mainfragment)
						.commit();// �仯֮�䵼�����ύ����
				currentFragment = mainfragment;// ��ǰ����Ϊ��ҳ
				current = Constants.FRAGMENT_TYPE.MAIN;// ��ǰ�����־ΪConstants.FRAGMENT_TYPE.MAIN
			}
			// Log.d(TAG, "The click indexnavigationIcon = " + v.getId());
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_order:// ��������¼�
			if (!(currentFragment instanceof OrderFragment)) {
				orderfragment = new OrderFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, orderfragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = orderfragment;
				current = Constants.FRAGMENT_TYPE.ORDER;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_my:// �ҵĵ���¼�
			if (!(currentFragment instanceof MyFragment)) {
				myfragment = new MyFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, myfragment).commit();
				// showTabContent(orderFragment);
				currentFragment = myfragment;
				current = Constants.FRAGMENT_TYPE.MY;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_near:// ��������¼�
			if (!(currentFragment instanceof NearFragment)) {
				nearfragment = new NearFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, nearfragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = nearfragment;
				current = Constants.FRAGMENT_TYPE.NEAR;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_more:// �������¼�
			if (!(currentFragment instanceof MoreFragment)) {
				morefragment = new MoreFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, morefragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = morefragment;
				current = Constants.FRAGMENT_TYPE.MORE;
			}
			switchBottomCorlor(arg0);
			break;
		default:
			if (!(currentFragment instanceof MainFragment)) {// �����ǰ������ҳҳ��
				/*********** �����ǰ����ҳҳ�� ***********/
				mainfragment = new MainFragment();// ʵ������ҳҳ��
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, mainfragment)
						.commit();// �仯֮�䵼�����ύ����
				currentFragment = mainfragment;// ��ǰ����Ϊ��ҳ
				current = Constants.FRAGMENT_TYPE.MAIN;// ��ǰ�����־ΪConstants.FRAGMENT_TYPE.MAIN
			}
			// Log.d(TAG, "The click indexnavigationIcon = " + v.getId());
			switchBottomCorlor(arg0);
			break;
		}
	}

	/**
	 * 
	 * ����˵�����ײ��Ĳ�����ɫ�ı仯
	 * 
	 * @Title: switchBottomCorlor
	 * @Description: TODO
	 * @param view
	 *            ��ǰ�����View
	 * @return void ��������
	 * @exception
	 * @throws
	 */
	private void switchBottomCorlor(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.app_bottom_main:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1_pressed);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_order:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2_pressed);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_my:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3_pressed);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_near:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4_pressed);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_more:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5_pressed);
			break;
		default:

			break;
		}
	}

}
