package com.silversage.brosApp.activities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silversage.brosApp.R;
import com.silversage.brosApp.adapters.DashboardAdapter;
import com.silversage.brosApp.adapters.DashboardObject;

public class Dashboard extends BrosAppActivity {

	int screen_width;
	boolean isListEmpty = true;
	private static final int PICK_CONTACT = 3;
	ListView List;
	TextView ProText;
	DashboardObject[] dashboardItem;
	private String contactId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("first_time", false)) {

			setContentView(R.layout.dashboard);
			setupView();
			PreExecute();

		} else {

			startActivity(new Intent(this, SplashScreen.class));
			this.finish();
		}

		Display display = getWindowManager().getDefaultDisplay();
		screen_width = display.getWidth(); // deprecated
		if (isListEmpty)
			showPopup();

	}

	private void setupView() {
		// TODO Auto-generated method stub
		List = (ListView) findViewById(R.id.dListView);
		ProText = (TextView) findViewById(R.id.mProText);
		List.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivity(new Intent(Dashboard.this, messages.class));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.dashboard,
				(com.actionbarsherlock.view.Menu) menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				ContentResolver cr = getContentResolver();

				Cursor c = managedQuery(contactData, null, null, null, null);

				contactId = c.getString(c
						.getColumnIndex(ContactsContract.Contacts._ID));
				Cursor phones = cr.query(Phone.CONTENT_URI, null,
						Phone.CONTACT_ID + " = " + contactId, null, null);

				if (c.moveToFirst()) {
					// other data is available for the Contact. I have decided
					// to only get the name of the Contact.
					String name = c
							.getString(c
									.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
					String phoneNumber = phones
							.getString(phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					(retrieveContactPhoto()).compress(
							Bitmap.CompressFormat.PNG, 10, stream);
					byte[] byteArray = stream.toByteArray();
					db.insertContact(name, phoneNumber, byteArray);
					PreExecute();

				}
			}
		}
	}

	private Bitmap retrieveContactPhoto() {

		Bitmap photo = null;

		try {
			InputStream inputStream = ContactsContract.Contacts
					.openContactPhotoInputStream(getContentResolver(),
							ContentUris.withAppendedId(
									ContactsContract.Contacts.CONTENT_URI,
									new Long(contactId)));

			if (inputStream != null) {
				photo = BitmapFactory.decodeStream(inputStream);
				// ImageView imageView = (ImageView)
				// findViewById(R.id.img_contact);
				// imageView.setImageBitmap(photo);
			}

			assert inputStream != null;
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return photo;

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case (R.id.new_message):

			Intent intent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT);
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

		Cursor _cursor = null;

		_cursor = db.getDashboardList();

		Log.d(" BrosApp--DashboardList", "Cursor populated");
		if (_cursor.getCount() > 0) {
			dashboardItem = new DashboardObject[_cursor.getCount()];
			Log.d(" BrosApp--DashboardList", "Data Exist");
			_cursor.moveToFirst();
			Log.d(" BrosApp--DashboardList", "Populating Adapter");

			if (_cursor.getCount() > 0) {
				isListEmpty = false;
				for (int i = 0; i < _cursor.getCount(); i++) {

					dashboardItem[i] = (new DashboardObject(
							_cursor.getString(_cursor.getColumnIndex("ID")),
							_cursor.getString(_cursor.getColumnIndex("name")),
							_cursor.getString(_cursor.getColumnIndex("number")),
							_cursor.getBlob(_cursor
									.getColumnIndex("displayPic"))));

					_cursor.moveToNext();
				}
			}
			_cursor.close();

			DashboardAdapter adapter = new DashboardAdapter(this, dashboardItem);

			List.setAdapter(adapter);
			ProText.setVisibility(View.INVISIBLE);
			Log.d(" BrosApp--DashboardList", "Populated");
		} else {

			Log.d(" BrosApp--DashboardList", "No Data Found");
		}

	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	// The method that displays the popup.
	private void showPopup() {
		int popupWidth = 200;
		int popupHeight = 150;

		// Inflate the popup_layout.xml
		RelativeLayout viewGroup = (RelativeLayout) findViewById(R.id.popup);
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout = layoutInflater.inflate(R.layout.popup_layout,
				viewGroup);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(this);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());
		layout.post(new Runnable() {
			public void run() {
				// Displaying the popup at the specified location, + offsets.
				popup.showAtLocation(layout, Gravity.NO_GRAVITY,
						screen_width - 100, 90);
			}
		});

	}

}
