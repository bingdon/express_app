package com.wukong.my;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.bean.PersonInfoBean;
import com.wukong.constants.ConstantS;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.support.debug.AppLog;
import com.wukong.support.image.ImageUtility;
import com.wukong.support.image.LoadImageUtility;
import com.wukong.support.notice.NoticeUtils;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyDataActivity extends Activity implements OnClickListener {
	private static final String TAG = MyDataActivity.class.getSimpleName();
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private RelativeLayout data_head_layout;
	private RelativeLayout data_sex_layout;
	private RelativeLayout data_age_layout;
	private ImageView submit;
	private ImageView headImageView;
	private String path;
	private Context context;
	private int gendar;
	private String age;
	private String username;
	private TextView usernameTextView;
	private TextView ageTextView;
	private TextView gendarTextView;
	private TextView data_timeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mydata_activity);
		context = this;
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		headImageView = (ImageView) findViewById(R.id.data_myhead);
		usernameTextView = (TextView) findViewById(R.id.data_name);
		ageTextView = (TextView) findViewById(R.id.data_age);
		gendarTextView = (TextView) findViewById(R.id.data_sex);
		submit = (ImageView) findViewById(R.id.headbar_right_btn);
		data_timeTextView=(TextView)findViewById(R.id.data_time);
		submit.setImageResource(R.drawable.submit_sec);
		findViewById(R.id.headbar_right_layout).setVisibility(View.VISIBLE);
		head_txt.setText("我的资料");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		submit.setOnClickListener(this);
		headImageView.setOnClickListener(this);
		findViewById(R.id.data_age_layout).setOnClickListener(this);
		findViewById(R.id.data_sex_layout).setOnClickListener(this);
		findViewById(R.id.data_name_layout).setOnClickListener(this);
		initData();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.headbar_right_btn:
			updateUserInfo();
			uploadHead();
			break;
		case R.id.data_myhead:
			ImageUtility.secPic(MyDataActivity.this);
			break;

		case R.id.data_sex_layout:
			showSexDialog();
			break;

		case R.id.data_age_layout:
			showAgeDialog();
			break;

		case R.id.data_name_layout:
			showNameDialog();
			break;

		default:
			break;
		}
	}

	private void initData() {
		PersonInfoBean personInfoBean = WKApplication.getInstance()
				.getPersonInfoBean();
		age = personInfoBean.getAge();
		try {
			gendar = Integer.valueOf(personInfoBean.getGendar());
		} catch (Exception e) {
			// TODO: handle exception
		}
		username = personInfoBean.getUsername();
		if (!TextUtils.isEmpty(username)) {
			usernameTextView.setText(username);
		}
		if (!TextUtils.isEmpty(age)) {
			ageTextView.setText(age);
		}
		if (gendar == 0) {
			gendarTextView.setText(R.string.man);
		} else {
			gendarTextView.setText(R.string.woman);
		}
		
		data_timeTextView.setText(personInfoBean.getCreatetime());

		LoadImageUtility.displayWebImage(WKHttpClient.IMAGER_URL
				+ personInfoBean.getHeadimage(), headImageView);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}

		if (requestCode == 0) {
			try {

				final String str;
				Uri localUri = data.getData();
				String[] arrayOfString = new String[1];
				arrayOfString[0] = "_data";
				Cursor localCursor = getContentResolver().query(localUri,
						arrayOfString, null, null, null);
				if (localCursor == null)
					return;
				localCursor.moveToFirst();
				str = localCursor.getString(localCursor
						.getColumnIndex(arrayOfString[0]));
				localCursor.close();
				AppLog.i(TAG, "路径:" + str);
				path = str;
				LoadImageUtility.displaySdImage(str, headImageView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (requestCode == 1) {
			try {
				path = ImageUtility.getPicPathFromUri(
						ImageUtility.imageFileUri, this);
				AppLog.i(TAG, "路径:" + path);
				LoadImageUtility.displaySdImage(path, headImageView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void uploadHead() {
		if (TextUtils.isEmpty(path)) {
			return;
		}

		new Thread() {
			public void run() {
				WKHttpClient.modifyUserHead(WKApplication.getInstance()
						.getPersonInfoBean().getId(),
						ImageUtility.bitmapNCutToString(path), uploadHandler);
			};
		}.start();

	}

	private JsonHttpResponseHandler uploadHandler = new JsonHttpResponseHandler() {

		public void onStart() {
			super.onStart();
		};

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
			AppLog.i(TAG, "修改头像:" + response);
			parseHeadResponse(response);
		}

	};

	private void updateUserInfo() {
		PersonInfoBean personInfoBean = WKApplication.getInstance()
				.getPersonInfoBean();
		if (TextUtils.isEmpty(age)) {
			ToastUtils.showShort(context, getString(R.string.age_notice));
			return;
		}
		if (TextUtils.isEmpty(username)) {
			ToastUtils.showShort(context, getString(R.string.name_notice));
			return;
		}
		personInfoBean.setAge(age);
		personInfoBean.setUsername(username);
		personInfoBean.setGendar(gendar + "");
		WKHttpClient.modifyUserInfo(personInfoBean, updateHandler);
	}

	private JsonHttpResponseHandler updateHandler = new JsonHttpResponseHandler() {
		public void onStart() {
			super.onStart();
			NoticeUtils.notice(context, getString(R.string.update_info), 0);
			finish();
		}

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
			NoticeUtils.removeNotice(0, context);
			NoticeUtils.showFailePublish(context);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			AppLog.i(TAG, "上传返回:" + response);
			parseUserResponse(response);
			NoticeUtils.removeNotice(0, context);
			NoticeUtils.showSuccessfulNotification(context);
		};

	};

	private void parseUserResponse(JSONObject response) {
		try {
			JSONObject user = response.getJSONObject("user");
			PersonInfoBean personInfoBean = GsonUtlity.getPersonInfoBean(user
					.toString());
			WKApplication.getInstance().setPersonInfoBean(personInfoBean);
			sendNoticesuccess();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseHeadResponse(JSONObject response) {
		try {
			String headimage = response.getString("headimage");
			PersonInfoBean personInfoBean = WKApplication.getInstance()
					.getPersonInfoBean();
			personInfoBean.setHeadimage(headimage);
			WKApplication.getInstance().setPersonInfoBean(personInfoBean);
			sendNoticesuccess();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 性别
	 */
	int sex = 0;

	private void showSexDialog() {
		final View view = getLayoutInflater().inflate(R.layout.dialog_sex_item,
				null, false);
		RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.sex);
		sex = 0;
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == R.id.man) {
					sex = 0;
				} else if (checkedId == R.id.woman) {
					sex = 1;
				}
			}
		});
		new AlertDialog.Builder(context)
				.setView(view)
				.setTitle(R.string.sec_sex)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								gendar = sex;
								if (gendar == 0) {
									gendarTextView.setText(R.string.man);
								} else {
									gendarTextView.setText(R.string.woman);
								}
							}
						}).show();
	}

	private void showAgeDialog() {
		final EditText ageEditText = new EditText(context);
		ageEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
		new AlertDialog.Builder(context)
				.setView(ageEditText)
				.setTitle(R.string.input_age)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String age_ = ageEditText.getText().toString();
								if (!TextUtils.isEmpty(age_)) {
									age=age_;
									ageTextView.setText(age);
								}
							}
						}).show();
	}

	private void showNameDialog() {
		final EditText nameEditText = new EditText(context);
		nameEditText.setHint(R.string.name);
		new AlertDialog.Builder(context)
				.setView(nameEditText)
				.setTitle(R.string.input_age)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								String  username_ = nameEditText.getText().toString();
								if (!TextUtils.isEmpty(username_)) {
									username=username_;
									usernameTextView.setText(username);
								}

							}
						}).show();
	}

	private void sendNoticesuccess() {
		sendBroadcast(new Intent(ConstantS.ACTION_UPDATE_USERINFO));
	}

}
