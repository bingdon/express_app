package com.wukong.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.adapter.OrderAdapter;
import com.wukong.bean.OrderBean;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.httpUtils.HttpUtility;
import com.wukong.main.RouteActivity;
import com.wukong.main.TraceActivity;
import com.wukong.support.debug.AppLog;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class OrderFragment extends Fragment implements OnClickListener,
		OnItemClickListener, OnRefreshListener2<ListView> {
	// ��Ҫ�����ж�����ɱ����Fragment������������activity���Ƶ����⣬������ڽ�������ص�������
	private static final String TAG = OrderFragment.class.getSimpleName();
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
	private PullToRefreshListView orderlist;// չʾ�����б�����
	private OrderAdapter orderadapter;// �����б�������
	private List<OrderBean> list = new ArrayList<OrderBean>();
	private String json = "";
	private ListView orderlistReal;

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
		orderlist = (PullToRefreshListView) view
				.findViewById(R.id.order_listview);
		orderlistReal = orderlist.getRefreshableView();
		registerForContextMenu(orderlistReal);
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
		orderadapter = new OrderAdapter(getActivity(), list);
		orderlistReal.setAdapter(orderadapter);
		orderlistReal.setOnItemClickListener(this);
		orderlist.setOnRefreshListener(this);
		/********* ����¼� *********/
		order_left.setOnClickListener(this);
		order_right.setOnClickListener(this);
		listMyOrder();
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
		ToastUtils.showShort(getActivity(), "ID:" + list.get(arg2 - 1).getTid());
		Intent intent=new Intent();
		intent.putExtra("id", list.get(arg2 - 1).getTid());
		intent.setClass(getActivity(), TraceActivity.class);
		startActivity(intent);
	}

	private void listMyOrder() {
		WKHttpClient.findmyExpress(WKApplication.getInstance()
				.getPersonInfoBean().getId()/* "1" */, handler);
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
			AppLog.i(TAG, "��������ʧ��:" + errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			AppLog.i(TAG, "��������:" + response);
			parseJson(response);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			orderlist.onRefreshComplete();
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			// if (!orderlist.isRefreshing()) {
			orderlist.setRefreshing(false);
			// }
		}

	};

	private void parseJson(JSONObject response) {
		if (HttpUtility.isSuccess(response)) {
			try {
				if (json.equals(response + "")) {
					if (getActivity() != null) {
						ToastUtils.showShort(getActivity(), "û������Ϣ");
					}
					return;
				}
				json = response + "";
				JSONObject object = response.getJSONObject("myExp");
				JSONArray jsonArray = object.getJSONArray("resultlist");
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					OrderBean orderBean = GsonUtlity.getOrderBean(jsonArray
							.getJSONObject(i) + "");
					if (!dealOrder(orderBean)) {
						list.add(0, orderBean);
					}
				}
				orderadapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = new MenuInflater(getActivity());
		inflater.inflate(R.menu.order, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		if (item.getItemId() == R.id.delete) {
			AppLog.i("λ��:" + info);
			deleteOrder(info.position - 1);
		}
		return super.onContextItemSelected(item);
	}

	private boolean dealOrder(OrderBean orderBean) {
		if (list.size() == 0) {
			return false;
		}
		int length = list.size();
		for (int i = 0; i < length; i++) {
			if (list.get(i).equals(orderBean)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(getActivity(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		listMyOrder();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

	private void deleteOrder(int position) {
		AppLog.i("λ��:" + position);
		WKHttpClient.deleteExp(list.get(position).getId(), new DelHanlder(
				position));
		list.remove(position);
		orderadapter.notifyDataSetChanged();
	}

	public class DelHanlder extends JsonHttpResponseHandler {
		private int position;

		public DelHanlder(int position) {
			super();
			this.position = position;
		}

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

	}

}
