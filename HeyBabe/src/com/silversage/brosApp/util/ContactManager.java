package com.silversage.brosApp.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.util.Log;

import com.silversage.brosApp.BrosApp;

@SuppressWarnings("deprecation")
public class ContactManager {

	static SQLiteDatabase db = BrosApp.db;
	static Context context = BrosApp.context;

	@SuppressWarnings("deprecation")
	public static void CopyContactList() {
		String contactNumber = null;
		String nameOfContact = null;
		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query(Phones.CONTENT_URI, null, null, null, null);

		if (cur.getCount() > 0) {
			cur.moveToFirst();

			for (int i = 0; i < cur.getCount(); i++) {

				nameOfContact = cur.getString(cur.getColumnIndex(People.NAME));
				contactNumber = cur
						.getString(cur.getColumnIndex(People.NUMBER));

				if ((nameOfContact != null) && (contactNumber != null)) {

					Log.i("con", "...Contact Name ...." + nameOfContact
							+ "...contact Number..." + contactNumber);

					ContentValues insertValues = new ContentValues();
					insertValues.put("number", contactNumber.trim());
					insertValues.put("name", nameOfContact.trim());

					db.insert("ContactList", null, insertValues);

				}
				cur.moveToNext();
			}
		}
		cur.close();

	}

}
