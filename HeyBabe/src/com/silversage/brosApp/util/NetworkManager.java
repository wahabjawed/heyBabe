package com.silversage.brosApp.util;

import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.silversage.brosApp.BrosApp;

public class NetworkManager {

	// static Context context;
	static ConnectivityManager connManager;

	static NetworkInfo mobile;
	static NetworkInfo mWifi;
	static Context context = BrosApp.getContext();
	static NetworkInfo activeNetworkInfo;
	static WifiManager mainWifi;
	static WifiReceiver receiverWifi;
	static List<ScanResult> wifiList;
	static List<WifiConfiguration> LastWifi;

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
				WifiManager.WIFI_STATE_CHANGED_ACTION));

	}

	static class WifiReceiver extends BroadcastReceiver {
		public void onReceive(Context c, Intent intent) {

			int extraWifiState = intent.getIntExtra(
					WifiManager.EXTRA_WIFI_STATE,
					WifiManager.WIFI_STATE_UNKNOWN);

			if (extraWifiState == WifiManager.WIFI_STATE_ENABLED) {

				LastWifi = mainWifi.getConfiguredNetworks();
				String[][] data = new String[LastWifi.size()][2];

				for (int i = 0; i < LastWifi.size(); i++) {

					Log.d("Wifi List", LastWifi.get(i).SSID);

					data[i][0] = (LastWifi.get(i)).SSID;
					data[i][1] = (LastWifi.get(i)).BSSID;

				}
				BrosApp.WifiList = data;
				context.unregisterReceiver(receiverWifi);
			}
			
		}
	}
}
