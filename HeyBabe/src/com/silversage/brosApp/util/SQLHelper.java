package com.silversage.brosApp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.silversage.brosApp.BrosApp;

public class SQLHelper {
	static SQLiteDatabase db = BrosApp.db;

	public static void SetupSQL(Context context) {

	}

	public static boolean isFirstTime() {
		Log.d("BrosApp--SQLHelper", "isFirstTime--Checking!");
		if ((db.rawQuery("select * from Preference", null)).getCount() > 0) {
			Log.d("BrosApp--SQLHelper", "isFirstTime--Dashboard");
			return false;
		} else {
			Log.d("BrosApp--SQLHelper", "isFirstTime--Splash");
			return true;

		}

	}

	public static void setFirstTime() {

		ContentValues insertValues = new ContentValues();
		insertValues.put("isFirst", "1");

		db.insert("Preference", null, insertValues);
		Log.d("BrosApp--SQLHelper", "Preference--Data inserted");
	}

	public static void clearData() {
		db.delete("Preference", null, null);
		Log.d(" BrosApp--SQLHelper", "Menu Item--Data Deleted");

	}

	public static Object getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Cursor getDashboardList() {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "Contact--Querying Data");
		return db.rawQuery("select * from Contact order by name", null);
	}
}
