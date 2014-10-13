package com.wukong.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wukong.bean.PersonInfoBean;

public class WKHttpClient {
	/**
	 * ������ַ
	 */
	public static final String BASE_URL = "http://115.28.165.94:8080/sun/api/";
	public static final String sigin_url = BASE_URL + "postUser?username=";
	public static final String sigin_url_ = BASE_URL + "postUser?tel=";
	public static final String login_url = BASE_URL + "findUser?tel=";
	public static final String user_url = BASE_URL + "userDetail?id=";
	public static final String advise_url = BASE_URL + "postAdvise?email=";
	public static final String add_address_url = BASE_URL + "postAddress?uid=";
	public static final String address_url = BASE_URL + "usedAddress?uid=";
	public static final String address_detail_url = BASE_URL + "addDetail?id=";
	public static final String address_change_url = BASE_URL
			+ "updateAddress?id=";
	public static final String delete_address_url = BASE_URL
			+ "deleteAddress?id=";
	public static final String order_url = BASE_URL + "findMyOrder?id=";
	/**
	 * �����û���Ϣ
	 */
	public static final String UPDATE_USER_URL = BASE_URL + "updateUser";
	/**
	 * �޸��û�ͷ��
	 */
	public static final String UPLOAD_HEADIMAGE_URL = BASE_URL + "uploadHeadimage";
	private static AsyncHttpClient client = new AsyncHttpClient();

	// ע��
	public void doHttpForget(String username, String password,
			AsyncHttpResponseHandler res) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();

		HttpUtil.get(sigin_url + username + "&password=" + password, params,
				res);
		Log.i("ע�᣺", sigin_url + username + "&password=" + password);
	}

	public void doHttpForgetbytel(String tel, String password,
			AsyncHttpResponseHandler res) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();

		HttpUtil.get(sigin_url_ + tel + "&password=" + password, params, res);
		Log.i("ע�᣺", sigin_url + tel + "&password=" + password);
	}

	// ��¼
	public void doHttpLogin(String tel, String password,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(login_url + tel + "&password=" + password, params, res);
		Log.i("��¼��", login_url + tel + "&password=" + password);
	}

	public void doHttpLoginbyphone(String tel, String password,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(login_url + tel + "&password=" + password, params, res);
		Log.i("��¼��", login_url + tel + "&password=" + password);
	}

	// ��ȡ�û���Ϣ
	public void userdetail(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(user_url + id, params, res);
		Log.i("�û���Ϣ��", user_url + id);
	}

	// �Ὠ��
	public void doHttpAdvise(String email, String content,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(advise_url + email + "&content=" + content, params, res);
		Log.i("���飺", advise_url + email + "&content=" + content);
	}

	// ������ַ
	public void postAddress(String uid, String reciver, String tel,
			String zipcode, String area, String detail,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(add_address_url + uid + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail, params, res);
		Log.i("������ַ��", add_address_url + uid + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail);
	}

	// ��ַ�б�
	public void usedAddress(String uid, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_url + uid, params, res);
		Log.i("��ַ�б�", address_url + uid);
	}

	// ��ַ����
	public void addDetail(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_detail_url + id, params, res);
		Log.i("��ַ���飺", address_detail_url + id);
	}

	// �޸ĵ�ַ
	public void updateAddress(String id, String reciver, String tel,
			String zipcode, String area, String detail,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_change_url + id + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail, params, res);
		Log.i("�޸ĵ�ַ��", address_change_url + id + "&reciver=" + reciver
				+ "&tel=" + tel + "&zipcode=" + zipcode + "&area=" + area
				+ "&detail=" + detail);
	}

	// ɾ����ַ
	public void deleteAddress(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(delete_address_url + id, params, res);
		Log.i("ɾ����ַ��", delete_address_url + id);
	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param personInfoBean
	 *            �û���Ϣ
	 * @param handler
	 *            ������
	 */
	public static void modifyUserInfo(PersonInfoBean personInfoBean,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", personInfoBean.getId());
		params.put("username", personInfoBean.getUsername());
		params.put("gendar", personInfoBean.getGendar());
		params.put("age", personInfoBean.getAge());
		client.post(UPDATE_USER_URL, params, handler);
	}
	/**
	 * �޸��û�ͷ��
	 * @param id
	 * @param headimage
	 * @param handler
	 */
	public static void modifyUserHead(String id,String headimage,AsyncHttpResponseHandler handler){
		RequestParams params=new RequestParams();
		params.put("headimage", headimage);
		params.put("id", id);
		client.post(UPLOAD_HEADIMAGE_URL, params, handler);
	}
	
}
