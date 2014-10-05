package com.silversage.brosApp;

import com.silversage.brosApp.objects.ContactVO;
import com.silversage.brosApp.service.MessagingService.MessagingService;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class BrosApp extends Application {

	public static SQLiteDatabase db;
	public static Context context;
	public static String[][] WifiList;
	public static ContactVO contact;

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		db = openOrCreateDatabase("brosApp", MODE_PRIVATE, null);
		context = getApplicationContext();
		startService(new Intent(getApplicationContext(), MessagingService.class));
		super.onCreate();

	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		db.close();
	}

}
