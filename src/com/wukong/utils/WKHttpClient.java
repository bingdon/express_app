package com.wukong.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class WKHttpClient {
	/**
	 * ������ַ
	 */
	public static final String BASE_URL = "http://115.28.165.94:8080/sun/api/";
	public static final String sigin_url = "http://115.28.165.94:8080/sun/api/postUser?username=";
	public static final String login_url = "http://115.28.165.94:8080/sun/api/findUser?tel=";
	public static final String user_url = "http://115.28.165.94:8080/sun/api/userDetail?id=";
	public static final String advise_url = "http://115.28.165.94:8080/sun/api/postAdvise?email=";
	public static final String add_address_url = "http://115.28.165.94:8080/sun/api/postAddress?uid=";
	public static final String address_url = "http://115.28.165.94:8080/sun/api/usedAddress?uid=";
	public static final String address_detail_url = "http://115.28.165.94:8080/sun/api/addDetail?id=";
	public static final String address_change_url = "http://115.28.165.94:8080/sun/api/updateAddress?id=";
	public static final String delete_address_url = "http://115.28.165.94:8080/sun/api/deleteAddress?id=";
	public static final String order_url = "http://115.28.165.94:8080/sun/api/findMyOrder?id=";

	// ע��
	public void doHttpForget(String username, String password,
			AsyncHttpResponseHandler res) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();

		HttpUtil.get(sigin_url + username + "&password=" + password, params,
				res);
		Log.i("ע�᣺", sigin_url + username + "&password=" + password);
	}

	// ��¼
	public void doHttpLogin(String username, String password,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(login_url + username + "&password=" + password, params,
				res);
		Log.i("��¼��", login_url + username + "&password=" + password);
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

}
