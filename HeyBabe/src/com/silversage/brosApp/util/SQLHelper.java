package com.silversage.brosApp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.objects.ContactVO;
import com.silversage.brosApp.objects.adapters.WiFiObject;

public class SQLHelper {
	static SQLiteDatabase db = BrosApp.db;

	public static void SetupDB(Context context) {
		db.execSQL("CREATE TABLE IF NOT EXISTS ContactList(number TEXT,name TEXT, displayPic BLOB);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Contact(ID INTEGER PRIMARY KEY, number TEXT,name TEXT, displayPic BLOB);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Ref_Message(ID INTEGER PRIMARY KEY, message TEXT, refID INTEGER);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Tran_Message(ID INTEGER, ContactID INTEGER, MessageID INTEGER, Nofity INTEGER, Time TEXT, Day TEXT, Reminder TEXT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Tran_WiFi(ID INTEGER, ssid TEXT,bssid TEXT, whenConnected INTEGER);");
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
		String data[][] = { { "1", "hey babe, how was your day?" },
				{ "2", "Miss you :)" },
				{ "3", "Hi darl, how did you go today?" },
				{ "4", "Hey babe, what are you up to tonight?" },
				{ "5", "Hi! How was your day?" },
				{ "6", "See you tonight darl :)" } };
		Log.d("BrosApp--SQLHelper", "Ref_Message---Truncate");
		db.delete("Ref_Message", null, null);
		Log.d("BrosApp--SQLHelper", "Ref_Message---Inserting");

		ContentValues insertValues;
		for (int i = 0; i < data.length; i++) {
			insertValues = new ContentValues();
			insertValues.put("ID", Integer.parseInt(data[i][0]));
			insertValues.put("message", data[i][1]);
			insertValues.put("refID", -1);

			Log.d("BrosApp--SQLHelper", "Ref_Message--" + data[i][1]);
			db.insert("Ref_Message", null, insertValues);

		}
		Log.d("BrosApp--SQLHelper", "Ref_Message--Data inserted");

	}

	public static void insertRefMessage(int ID, String message) {

		ContentValues insertValues = new ContentValues();
		insertValues.put("message", message);
		insertValues.put("refID", ID);
		Log.d("BrosApp--SQLHelper", "Ref_Message--" + message);
		db.insert("Ref_Message", null, insertValues);

	}

	public static Cursor getMessageList(int ID) {
		// TODO Auto-generated method stub
		Log.d(" BrosApp--SQLHelper", "Message--Querying Data");
		return db.rawQuery(
				"select * from Ref_Message where refID = -1 or refID=" + ID,
				null);
	}

	public static void insertTran(ContactVO contacts) {

		int ID = getTranSequence();
		Log.d("BrosApp--SQLHelper", "Tran_Message--Sequence ID = " + ID);
		ContentValues insertValues = new ContentValues();
		insertValues.put("ID", ID);
		insertValues.put("ContactID", contacts.getID());
		insertValues.put("MessageID", contacts.getMessageID());
		insertValues.put("Time", contacts.getTime());
		insertValues.put("Day", contacts.getDay());
		insertValues.put("Reminder", contacts.getRepeat());
		insertValues.put("Nofity", contacts.getNofity());
		db.insert("Tran_Message", null, insertValues);
		Log.d("BrosApp--SQLHelper", "Tran_Message--Inserted");

		for (int i = 0; i < contacts.getWifiCondition().size(); i++) {

			WiFiObject obj = contacts.getWifiCondition().get(i);
			insertValues = new ContentValues();
			insertValues.put("ID", ID);
			insertValues.put("ssid", obj.getName());
			insertValues.put("bssid", contacts.getMessageID());
			db.insert("Tran_WiFi", null, insertValues);
			Log.d("BrosApp--SQLHelper", "Tran_WiFi--Inserting WiFi No " + i);

		}
		Log.d("BrosApp--SQLHelper", "Tran_WiFi--Inserted");

	}

	public static int getTranSequence() {

		Cursor c = db.rawQuery("select * from Tran_Message order by ID desc",
				null);
		if (c.moveToFirst()) {
			return c.getInt(c.getColumnIndex("ID")) + 1;
		} else {
			return 0;

		}

	}

	public static Cursor getLogMessageList(int iD) {
		// TODO Auto-generated method stub
		Log.d("BrosApp--SQLHelper", "Tran_Message--Fetching Log Message");

		return db
				.rawQuery(
						"select * from Ref_Message where ID in (select MessageID from Tran_Message where ContactID="
								+ iD + ")", null);

	}

	public static void DeleteTran(int iD, String msgID) {
		// TODO Auto-generated method stub

		db.delete("Tran_WiFi",
				"ID in (select ID from Tran_Message where ContactID =" + iD
						+ " and MessageID = " + Integer.parseInt(msgID) + ")",
				null);

		db.delete("Tran_Message", "ContactID=" + iD + " and MessageID = "
				+ Integer.parseInt(msgID) + " ", null);

		Log.d(" BrosApp--SQLHelper", "Log List--Data Deleted");
	}

}
