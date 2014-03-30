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
		// db = context
		// .openOrCreateDatabase("brosApp", context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS ContactList(number TEXT,name TEXT, displayPic BLOB);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Contact(ID INTEGER PRIMARY KEY, number TEXT,name TEXT, displayPic BLOB);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Ref_Message(ID TEXT, message TEXT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Ref_WiFi(ID INTEGER PRIMARY KEY, ssid TEXT,bssid TEXT);");

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

	public static void DeleteContact(int id) {
		db.delete("Contact", "ID=" + id, null);
		Log.d(" BrosApp--SQLHelper", "Dashboard List--Data Deleted");

	}

	public static Object getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Cursor getDashboardContactList() {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "Contact--Querying Data");
		return db.rawQuery("select * from Contact order by name", null);
	}

	public static Cursor getDashboardContactList(int ID) {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "Contact--Querying Data");
		return db.rawQuery("select * from Contact where ID = " + ID, null);
	}

	public static Cursor getWiFiList(int ID) {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "WiFi List--Querying Data");
		return db.rawQuery("select * from Ref_WiFi order by ssid", null);
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

	public static void updateContact(int id, String name, String no, byte[] pic) {
		Log.d("BrosApp--SQLHelper", "Contact--Data" + name + " " + no);
		ContentValues updateValues = new ContentValues();
		updateValues.put("name", name);
		updateValues.put("number", no);
		updateValues.put("displayPic", pic);

		db.update("Contact", updateValues, "ID= " + id, null);

		Log.d("BrosApp--SQLHelper", "Contact--Data uptated");
	}

	public static void PopulateWiFiList(String[][] data) {

		Log.d("BrosApp--SQLHelper", "Ref_WiFi---Truncate");
		db.delete("Ref_WiFi", null, null);
		Log.d("BrosApp--SQLHelper", "Ref_WiFi---Inserting");

		ContentValues insertValues;
		for (int i = 0; i < data.length; i++) {
			insertValues = new ContentValues();
			insertValues.put("ssid", data[i][0]);
			insertValues.put("bssid", data[i][1]);

			Log.d("BrosApp--SQLHelper", "Ref_WiFi--" + data[i][0]);
			db.insert("Ref_WiFi", null, insertValues);

		}
		Log.d("BrosApp--SQLHelper", "Ref_WiFi--Data inserted");


	}

	public static void PopulateMessageList() {
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

			Log.d("BrosApp--SQLHelper", "Ref_Message--" + data[i][1]);
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
