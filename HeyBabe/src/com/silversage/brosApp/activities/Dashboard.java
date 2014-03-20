package com.silversage.brosApp.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silversage.brosApp.R;
import com.silversage.brosApp.adapters.DashboardAdapter;
import com.silversage.brosApp.adapters.DashboardObject;

public class Dashboard extends BrosAppActivity {

	private static final int PICK_CONTACT = 3;
	ListView List;
	TextView ProText;
	DashboardObject[] dashboardItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!db.isFirstTime()) {

			setContentView(R.layout.dashboard);
			setupView();
			PreExecute();

		} else {

			startActivity(new Intent(this, SplashScreen.class));
			this.finish();
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub
		List = (ListView) findViewById(R.id.dListView);
		ProText = (TextView) findViewById(R.id.mProText);

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
				Cursor c = managedQuery(contactData, null, null, null, null);

				if (c.moveToFirst()) {
					// other data is available for the Contact. I have decided
					// to only get the name of the Contact.
					String name = c
							.getString(c
									.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
					Toast.makeText(getApplicationContext(), name,
							Toast.LENGTH_SHORT).show();
					dashboardItem[0]=new DashboardObject(name, "0");

				}
			}
		}
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
			Log.d(" BrosApp--DashboardList", "Data Exist");
			_cursor.moveToFirst();
			Log.d(" BrosApp--DashboardList", "Populating Adapter");

			for (int i = 0; i < _cursor.getCount(); i++) {

				dashboardItem[i] = (new DashboardObject(
						_cursor.getString(_cursor.getColumnIndex("name")),
						_cursor.getString(_cursor.getColumnIndex("ID"))));

				_cursor.moveToNext();
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

}
