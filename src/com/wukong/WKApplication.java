package com.wukong;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wukong.bean.PersonInfoBean;
import com.wukong.login.LoginActivity;
import com.wukong.utils.UserInfoUtility;

public class WKApplication extends Application {
	/***** 记录生成的activity的链表 **************/
	private List<Activity> mList = new LinkedList<Activity>();

	/**
	 * 
	 * 功能说明：添加新的activity到链表中
	 * 
	 * @Title: addActivity
	 * @Description: TODO
	 * @param activity
	 * @return void 返回类型
	 * @exception
	 * @throws
	 */
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	/**
	 * 
	 * 功能说明:退出程序的时候，杀死所有的activity
	 * 
	 * @Title: exit
	 * @Description: TODO
	 * @return void 返回类型
	 * @exception
	 * @throws
	 */
	public void exit() { // 遍历List，退出每一个Activity
		try {
			for (Activity activity : mList) {
				// 如果该activity不为空
				if (activity != null) {
					if (!(activity instanceof LoginActivity))
						// 如果该activity为登录功能的activity，不杀死
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
		initImageLoader(instance);
	}
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions options;
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// .memoryCache(new LruMemoryCache(maxSize))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(false)
		.bitmapConfig(Bitmap.Config.ARGB_8888)
		.cacheOnDisc(true).considerExifParams(true).build();
		
		
	}
	
	

}
