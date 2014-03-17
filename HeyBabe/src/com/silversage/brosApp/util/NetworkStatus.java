package com.silversage.brosApp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.silversage.brosApp.BrosApp;

public class NetworkStatus {

	// static Context context;
	static ConnectivityManager connManager;

	static NetworkInfo mobile;
	static NetworkInfo mWifi;
	static Context context = BrosApp.getContext();
	static NetworkInfo activeNetworkInfo;

	public static void Setup() {
		connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		activeNetworkInfo = connManager.getActiveNetworkInfo();
	}

	public static boolean IsConnected() {
		// boolean isConnect = false;
		Setup();
		if ((mWifi.isConnected() || mobile.isConnected())
				&& activeNetworkInfo != null) {
			return true;
		}
		return false;
	}

}
