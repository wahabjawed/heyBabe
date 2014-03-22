package com.silversage.brosApp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.silversage.brosApp.BrosApp;

public class SQLHelper {
	static SQLiteDatabase db = BrosApp.db;

	public static void SetupDB(Context context) {
		db = context
				.openOrCreateDatabase("brosApp", context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Preference(isFirst TEXT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Contact(ID TEXT, number TEXT,name TEXT, displayPic BLOB);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Ref_Message(ID TEXT, message TEXT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS ContractDetail(ContractID TEXT,name TEXT,desc TEXT,do TEXT, dont TEXT, pic BLOB);");

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

	public static void insertContact(String name, String no, byte[] pic) {
		Log.d("BrosApp--SQLHelper", "Contact--Data" + name + " " + no);
		ContentValues insertValues = new ContentValues();
		insertValues.put("name", name);
		insertValues.put("number", no);
		insertValues.put("displayPic", pic);

		db.insert("Contact", null, insertValues);

		Log.d("BrosApp--SQLHelper", "Contact--Data inserted");

	}

	public static void PopulateReferenceData() {
		// TODO Auto-generated method stub
		String data[][] = { { "1", "Hemani" }, { "2", "Wahab" },
				{ "3", "Zainu" }, { "4", "Ali" } };
		Log.d("BrosApp--SQLHelper", "Ref_Message---Truncate");
		db.delete("Ref_Message", null, null);
		Log.d("BrosApp--SQLHelper", "Ref_Message---Inserting");

		ContentValues insertValues;
		for (int i = 0; i < data.length; i++) {
			insertValues = new ContentValues();
			insertValues.put("ID", data[i][0]);
			insertValues.put("message", data[i][1]);
			
			Log.d("BrosApp--SQLHelper", "Ref_Message--"+data[i][1]);
			db.insert("Ref_Message", null, insertValues);
			
		}
		Log.d("BrosApp--SQLHelper", "Ref_Message--Data inserted");

	}

	public static Cursor getMessageList() {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "Message--Querying Data");
		return db.rawQuery("select * from Ref_Message", null);
	}
}
