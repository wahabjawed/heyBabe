package com.silversage.heybabe;

import com.silversage.heybabe.objects.User;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class HeyBabe extends Application{

	public static SQLiteDatabase db;
	public static Context context;
	public static User userObj;

	public static User getUserObj() {
		return userObj;
	}

	public static void setUserObj(User userObj) {
		HeyBabe.userObj = userObj;
	}

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	db = openOrCreateDatabase("heybabe", MODE_PRIVATE, null);
	db.execSQL("CREATE TABLE IF NOT EXISTS User(name TEXT,phone TEXT,countryID TEXT, pic BLOB);");
	db.execSQL("CREATE TABLE IF NOT EXISTS Contact(ID TEXT, number TEXT,name TEXT, pic BLOB);");
	db.execSQL("CREATE TABLE IF NOT EXISTS Contract(ID TEXT, sender TEXT,receiver TEXT);");
	db.execSQL("CREATE TABLE IF NOT EXISTS Messages(ContractID TEXT,name TEXT,desc TEXT,do TEXT, dont TEXT, pic BLOB);");
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
