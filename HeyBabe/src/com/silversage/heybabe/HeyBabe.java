package com.silversage.heybabe;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class HeyBabe extends Application{

	public static SQLiteDatabase db;
	public static Context context;

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
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
	}
	
}
