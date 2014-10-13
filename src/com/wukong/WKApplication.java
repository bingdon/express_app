package com.wukong;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

import com.wukong.bean.PersonInfoBean;
import com.wukong.login.LoginActivity;
import com.wukong.utils.UserInfoUtility;

public class WKApplication extends Application {
	/***** ��¼���ɵ�activity������ **************/
	private List<Activity> mList = new LinkedList<Activity>();

	/**
	 * 
	 * ����˵��������µ�activity��������
	 * 
	 * @Title: addActivity
	 * @Description: TODO
	 * @param activity
	 * @return void ��������
	 * @exception
	 * @throws
	 */
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	/**
	 * 
	 * ����˵��:�˳������ʱ��ɱ�����е�activity
	 * 
	 * @Title: exit
	 * @Description: TODO
	 * @return void ��������
	 * @exception
	 * @throws
	 */
	public void exit() { // ����List���˳�ÿһ��Activity
		try {
			for (Activity activity : mList) {
				// �����activity��Ϊ��
				if (activity != null) {
					if (!(activity instanceof LoginActivity))
						// �����activityΪ��¼���ܵ�activity����ɱ��
						activity.finish();
				}

			}
			mList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	
	private PersonInfoBean personInfoBean;
	
	private static WKApplication instance;
	
	public static WKApplication getInstance() {
		return instance;
	}
	
	public void setPersonInfoBean(PersonInfoBean personInfoBean) {
		UserInfoUtility.savePersonInfo(personInfoBean, this);
		this.personInfoBean = personInfoBean;
	}
	
	public PersonInfoBean getPersonInfoBean() {
		return personInfoBean;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance=this;
	}
	
	
	
	

}
