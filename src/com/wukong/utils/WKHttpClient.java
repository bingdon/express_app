package com.wukong.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wukong.bean.OrderBean;
import com.wukong.bean.PersonInfoBean;
import com.wukong.support.debug.AppLog;

public class WKHttpClient {
	/**
	 * ������ַ
	 */
	public static final String BASE_URL = "http://115.28.165.94:8080/sun/api/";
	public static final String URL = "http://115.28.165.94:8080/sun/";
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
	public static final String UPLOAD_HEADIMAGE_URL = BASE_URL
			+ "uploadHeadimage";
	/**
	 * ͼƬ��ַ
	 */
	public static final String IMAGER_URL = URL + "upload";
	/**
	 * @deprecated ������ѯ��ַ
	 */
	public static final String SEARCH_ORDER = BASE_URL + "findMyOrder";
	/**
	 * @deprecated ������ѯ��ַ
	 */
	public static final String LIST_ORDER = BASE_URL + "findExpOrder";
	/**
	 * ������ѯ��ַ
	 */
	public static final String LIST_MY_ORDER = BASE_URL + "myExpress";
	/**
	 * ������·
	 */
	public static final String POST_ROUTE = BASE_URL + "postSchedule";
	/**
	 * ����
	 */
	public static final String POST_GOODS = BASE_URL + "post";
	/**
	 * ��������
	 */
	public static final String LIST_GOODS_TYPE = BASE_URL + "list";
	/**
	 * ��δ���ɶ���
	 */
	public static final String LIST_TO_DO_ORDER = BASE_URL + "secExpress";
	/**
	 * ��ѯ�ҵĶ���
	 */
	public static final String FIND_MY_Order_ORDER = BASE_URL + "findMyOrder";
	/**
	 * ��ȡ����
	 */
	public static final String GET_ORDER = BASE_URL + "postTrade";
	/**
	 * ���ٿ��
	 */
	public static final String GET_TRADER_ORDER = BASE_URL + "findByDid";

	public static final String DEL_ORDER = BASE_URL + "deleteExp";

	private static AsyncHttpClient client = new AsyncHttpClient();

	// static {
	// client.setURLEncodingEnabled(true);
	// }

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
	 * 
	 * @param id
	 * @param headimage
	 * @param handler
	 */
	public static void modifyUserHead(String id, String headimage,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("headimage", headimage);
		params.put("id", id);
		client.post(UPLOAD_HEADIMAGE_URL, params, handler);
	}

	/**
	 * ������ѯ
	 * 
	 * @deprecated
	 * @param id
	 * @param handler
	 */
	public static void findMyOrder(String id, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("did", id);
		client.post(SEARCH_ORDER, params, handler);
	}

	/**
	 * �����г�
	 * 
	 * @param id
	 *            ����ID
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            �����յ�
	 * @param vehicle
	 *            ��ͨ��ʽ
	 * @param trainnum
	 *            ����
	 * @param begintime
	 *            ��ʼʱ��
	 * @param arrivetime
	 *            ����ʱ��
	 * @param receiveway
	 *            ���շ�ʽ
	 * @param getway
	 *            ��ȡ��ʽ
	 * @param demand
	 *            Ҫ��
	 * @param handler
	 *            ������
	 */
	public static void postSchedule(String id, String start, String end,
			String vehicle, String trainnum, String begintime,
			String arrivetime, String receiveway, String getway, String demand,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("did", id);
		params.put("start", start);
		params.put("end", end);
		params.put("vehicle", vehicle);
		params.put("trainnum", trainnum);
		params.put("begintime", begintime);
		params.put("arrivetime", arrivetime);
		params.put("receiveway", receiveway);
		params.put("getway", getway);
		params.put("demand", demand);
		client.post(POST_ROUTE, params, handler);
	}

	/**
	 * ���Ϳ��
	 * 
	 * @param orderBean
	 *            ���
	 * @param handler
	 *            ���ش���
	 */
	public static void postGoods(OrderBean orderBean,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		// params.put("id", orderBean.getId());
		params.put("uid", orderBean.getUid());
		params.put("shipper", orderBean.getShipper());
		params.put("s_tel", orderBean.getS_tel());
		params.put("s_address", orderBean.getS_address());
		params.put("receiver", orderBean.getReceiver());
		params.put("r_tel", orderBean.getR_tel());
		params.put("r_address", orderBean.getR_address());
		params.put("way", orderBean.getWay());
		params.put("category", orderBean.getCategory());
		params.put("gname", orderBean.getGname());
		params.put("weight", orderBean.getWeight());
		params.put("price", orderBean.getPrice());
		params.put("remarks", orderBean.getRemarks());
		params.put("cost", orderBean.getCost());
		params.put("tag", orderBean.getTag());
//		try {
//			params = new RequestParams(Bean2HashMapUtility.strToHash(orderBean));
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		client.post(POST_GOODS, params, handler);
		AppLog.i(POST_GOODS + "?" + params);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param handler
	 */
	public static void listGoodsType(AsyncHttpResponseHandler handler) {
		client.post(LIST_GOODS_TYPE, handler);
	}

	/**
	 * ��ѯ�ȴ�����
	 * 
	 * @param tag
	 * @param handler
	 */
	public static void secExpress(String tag, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("tag", tag);
		client.post(LIST_TO_DO_ORDER, params, handler);
	}

	/**
	 * ��ѯ����
	 * 
	 * @deprecated
	 * @param id
	 * @param handler
	 */
	public static void findExpOrder(String id, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("eid", id);
		client.post(LIST_ORDER, params, handler);
	}

	/**
	 * ��ѯ����
	 * 
	 * @param uid
	 * @param handler
	 */
	public static void findmyExpress(String uid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		client.post(LIST_MY_ORDER, params, handler);
	}

	/**
	 * ������
	 * 
	 * @param ide
	 * @param orid
	 * @param handler
	 */
	public static void postTrade(String ide, String orid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("did", ide);
		params.put("eid", orid);
		client.post(GET_ORDER, params, handler);
	}

	/**
	 * ���ٿ��
	 * 
	 * @param id
	 * @param handler
	 */
	public static void findByDid(String id, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		client.post(GET_TRADER_ORDER, params, handler);
	}

	/**
	 * ɾ������
	 * 
	 * @param id
	 * @param handler
	 */
	public static void deleteExp(String id, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		client.post(DEL_ORDER, params, handler);
	}
}
