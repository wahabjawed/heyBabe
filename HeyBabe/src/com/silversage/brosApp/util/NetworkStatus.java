package com.silversage.brosApp.util;

import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.silversage.brosApp.BrosApp;

public class NetworkStatus {

	// static Context context;
	static ConnectivityManager connManager;

	static NetworkInfo mobile;
	static NetworkInfo mWifi;
	static Context context = BrosApp.getContext();
	static NetworkInfo activeNetworkInfo;
	static WifiManager mainWifi;
	static WifiReceiver receiverWifi;
	static List<ScanResult> wifiList;

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

	public static void isOnWifi(String ID) {
		Setup();
		if (mWifi.isConnected() && activeNetworkInfo != null) {
			final WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			final WifiInfo _WifiInfo = wifiManager.getConnectionInfo();
			if (_WifiInfo != null) {
				_WifiInfo.getBSSID();
			}
		}

	}

	public static void FetchWiFi() {
		Setup();

		mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		mainWifi.setWifiEnabled(true);
		receiverWifi = new WifiReceiver();
		context.registerReceiver(receiverWifi, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		mainWifi.startScan();

	}

	static class WifiReceiver extends BroadcastReceiver {
		public void onReceive(Context c, Intent intent) {

			wifiList = mainWifi.getScanResults();
			String[][] data = new String[wifiList.size()][2];

			for (int i = 0; i < wifiList.size(); i++) {

				data[i][0] = (wifiList.get(i)).SSID;
				data[i][1] = (wifiList.get(i)).BSSID;
			}
			BrosApp.WifiList = data;
			SQLHelper.PopulateWiFiList(data);
		}
	}
}
