package com.wukong.support.image;

import android.widget.ImageView;

import com.wukong.WKApplication;

public class LoadImageUtility {

	public static void displayWebImage(String url, ImageView imageView) {
		WKApplication.imageLoader.displayImage(url, imageView,
				WKApplication.options);
	}
	
	public static void displaySdImage(String url, ImageView imageView) {
		WKApplication.imageLoader.displayImage("file://"+url, imageView,
				WKApplication.options);
	}

}
