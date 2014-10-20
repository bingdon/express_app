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
	 * 公共地址
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
	 * 更新用户信息
	 */
	public static final String UPDATE_USER_URL = BASE_URL + "updateUser";
	/**
	 * 修改用户头像
	 */
	public static final String UPLOAD_HEADIMAGE_URL = BASE_URL
			+ "uploadHeadimage";
	/**
	 * 图片地址
	 */
	public static final String IMAGER_URL = URL + "upload";
	/**
	 * @deprecated 订单查询地址
	 */
	public static final String SEARCH_ORDER = BASE_URL + "findMyOrder";
	/**
	 * @deprecated 订单查询地址
	 */
	public static final String LIST_ORDER = BASE_URL + "findExpOrder";
	/**
	 * 订单查询地址
	 */
	public static final String LIST_MY_ORDER = BASE_URL + "myExpress";
	/**
	 * 发布线路
	 */
	public static final String POST_ROUTE = BASE_URL + "postSchedule";
	/**
	 * 发件
	 */
	public static final String POST_GOODS = BASE_URL + "post";
	/**
	 * 货物类型
	 */
	public static final String LIST_GOODS_TYPE = BASE_URL + "list";
	/**
	 * 卫未生成订单
	 */
	public static final String LIST_TO_DO_ORDER = BASE_URL + "secExpress";
	/**
	 * 查询我的订单
	 */
	public static final String FIND_MY_Order_ORDER = BASE_URL + "findMyOrder";
	/**
	 * 获取订单
	 */
	public static final String GET_ORDER = BASE_URL + "postTrade";
	/**
	 * 跟踪快件
	 */
	public static final String GET_TRADER_ORDER = BASE_URL + "findByDid";

	public static final String DEL_ORDER = BASE_URL + "deleteExp";

	private static AsyncHttpClient client = new AsyncHttpClient();

	// static {
	// client.setURLEncodingEnabled(true);
	// }

	// 注册
	public void doHttpForget(String username, String password,
			AsyncHttpResponseHandler res) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();

		HttpUtil.get(sigin_url + username + "&password=" + password, params,
				res);
		Log.i("注册：", sigin_url + username + "&password=" + password);
	}

	public void doHttpForgetbytel(String tel, String password,
			AsyncHttpResponseHandler res) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();

		HttpUtil.get(sigin_url_ + tel + "&password=" + password, params, res);
		Log.i("注册：", sigin_url + tel + "&password=" + password);
	}

	// 登录
	public void doHttpLogin(String tel, String password,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(login_url + tel + "&password=" + password, params, res);
		Log.i("登录：", login_url + tel + "&password=" + password);
	}

	public void doHttpLoginbyphone(String tel, String password,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(login_url + tel + "&password=" + password, params, res);
		Log.i("登录：", login_url + tel + "&password=" + password);
	}

	// 获取用户信息
	public void userdetail(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(user_url + id, params, res);
		Log.i("用户信息：", user_url + id);
	}

	// 提建议
	public void doHttpAdvise(String email, String content,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(advise_url + email + "&content=" + content, params, res);
		Log.i("建议：", advise_url + email + "&content=" + content);
	}

	// 新增地址
	public void postAddress(String uid, String reciver, String tel,
			String zipcode, String area, String detail,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(add_address_url + uid + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail, params, res);
		Log.i("新增地址：", add_address_url + uid + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail);
	}

	// 地址列表
	public void usedAddress(String uid, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_url + uid, params, res);
		Log.i("地址列表：", address_url + uid);
	}

	// 地址详情
	public void addDetail(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_detail_url + id, params, res);
		Log.i("地址详情：", address_detail_url + id);
	}

	// 修改地址
	public void updateAddress(String id, String reciver, String tel,
			String zipcode, String area, String detail,
			AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(address_change_url + id + "&reciver=" + reciver + "&tel="
				+ tel + "&zipcode=" + zipcode + "&area=" + area + "&detail="
				+ detail, params, res);
		Log.i("修改地址：", address_change_url + id + "&reciver=" + reciver
				+ "&tel=" + tel + "&zipcode=" + zipcode + "&area=" + area
				+ "&detail=" + detail);
	}

	// 删除地址
	public void deleteAddress(String id, AsyncHttpResponseHandler res) {
		RequestParams params = new RequestParams();
		// TODO Auto-generated method stub
		HttpUtil.get(delete_address_url + id, params, res);
		Log.i("删除地址：", delete_address_url + id);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param personInfoBean
	 *            用户信息
	 * @param handler
	 *            处理返回
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
	 * 修改用户头像
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
	 * 订单查询
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
	 * 发布行程
	 * 
	 * @param id
	 *            发布ID
	 * @param start
	 *            起始位置
	 * @param end
	 *            发送终点
	 * @param vehicle
	 *            交通方式
	 * @param trainnum
	 *            车次
	 * @param begintime
	 *            开始时间
	 * @param arrivetime
	 *            到达时间
	 * @param receiveway
	 *            接收方式
	 * @param getway
	 *            获取方式
	 * @param demand
	 *            要求
	 * @param handler
	 *            处理返回
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
	 * 发送快件
	 * 
	 * @param orderBean
	 *            快件
	 * @param handler
	 *            返回处理
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
	 * 获取货物类型
	 * 
	 * @param handler
	 */
	public static void listGoodsType(AsyncHttpResponseHandler handler) {
		client.post(LIST_GOODS_TYPE, handler);
	}

	/**
	 * 查询等待订单
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
	 * 查询订单
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
	 * 查询订单
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
	 * 抢订单
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
	 * 跟踪快件
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
	 * 删除订单
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
