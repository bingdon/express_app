package com.wukong.utils;

import com.wukong.R;

import android.content.Context;
import android.content.Intent;

public class ShareUtility {
	public static void share2Fre(Context context) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT,
				context.getString(R.string.share_content));
		shareIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(shareIntent,
				context.getString(R.string.share_title)));
	}

}
