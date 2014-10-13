package com.wukong.utils;

/**
 * 
 * @ClassName: Contsants
 * @Description: 常量值
 * @author zc
 * @date
 * 
 */
public class Constants {
	/*** 被销毁前FRAGMENT所处的位置 */
	public final class FRAGMENT_TYPE {
		public static final int MAIN = 1;
		public static final int ORDER = 2;
		public static final int MY = 3;
		public static final int NEAR = 4;
		public static final int MORE = 5;
	}

	public final class START_ACTIVITY {
		public static final int SEND_TO_TYPE = 20001;
		public static final int SEND_TO_PRICE = 20002;
		public static final int SEND_TO_INFO = 20003;
		public static final int SEND_TO_SENDADDRESS = 20004;
		public static final int SEND_TO_RECEIVEADDRESS = 20005;
		public static final int ADDRESS_TO_ADDRESSDETAIL = 20006;
		public static final int ADDRESS_TO_ADD_ADDRESS = 20007;
	}

	public final class SHARED_PREFERENCES {
		public static final String TEL = "tel";
		public static final String PWD = "password";
	}

	public static String sdpath = "/sdcard/wukong/fast";// 项目根目录

}
