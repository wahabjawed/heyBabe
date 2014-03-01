package com.silversage.heybabe.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.silversage.heybabe.HeyBabe;

public class SQLHelper {
	static SQLiteDatabase db = HeyBabe.db;

	public static void SetupSQL(Context context) {

	}

	public static Cursor getMenuItems(String resID) {
		return db.rawQuery(
				"select * from MenuItem where ID = '" + resID + "' ", null);
	}

	public static Cursor getRestaurants() {
		return db.rawQuery("select * from Restaurant order by name", null);
	}

	public static void clearData() {
		db.delete("MenuItem", null, null);
		Log.d("FastFood--SQLHelper", "Menu Item--Data Deleted");
		db.delete("Restaurant", null, null);
		Log.d("FastFood--SQLHelper", "Restaurant--Data Deleted");

	}

	public static void insertRestaurat(String _rid, String _name) {

		ContentValues insertValues = new ContentValues();
		insertValues.put("ID", _rid);
		insertValues.put("name", _name);

		db.insert("Restaurant", null, insertValues);
		Log.d("FastFood--SQLHelper", "Restaurant--Data inserted");
	}

	public static void insertMenuItem(String _mid, String _food,
			String _quantity, String _price) {

		ContentValues insertValues = new ContentValues();
		insertValues.put("ID", _mid);
		insertValues.put("food", _food);
		insertValues.put("quantity", _quantity);
		insertValues.put("price", _price);

		db.insert("MenuItem", null, insertValues);
		// Log.d("FastFood--SQLHelper", "Menu Item--Data inserted");
	}

	public static Cursor getSpecificRestaurants(String searchString) {
		// TODO Auto-generated method stub
		return db.rawQuery("select * from Restaurant where name like '"
				+ searchString + "%' order by name", null);
	}

	public static Object getUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
