package com.silversage.brosApp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppSherlockActivity;
import com.silversage.brosApp.adapters.DashboardAdapter;
import com.silversage.brosApp.objects.ContactVO;
import com.silversage.brosApp.objects.adapters.DashboardObject;
import com.silversage.brosApp.util.NetworkManager;
import com.silversage.brosApp.util.SQLHelper;

public class Dashboard extends BrosAppSherlockActivity {

	int screen_width;
	boolean isListEmpty = true;

	ListView List;
	TextView ProText;
	DashboardObject[] dashboardItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("first_time", false)) {

			setContentView(R.layout.dashboard);

			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			setupView();

		} else {
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.splash_screen);
			final Handler handler = new Handler();
			final Runnable r = new Runnable() {

				public void run() {

					startActivity(new Intent(Dashboard.this, Slider.class));
					Dashboard.this.finish();

				}
			};
			handler.postDelayed(r, 1000 * 3);

			new Task().execute();

			// startActivity(new Intent(this, SplashScreen.class));
			// this.finish();
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub
		List = (ListView) findViewById(R.id.dListView);
		ProText = (TextView) findViewById(R.id.mProText);
		List.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				BrosApp.contact = new ContactVO();
				BrosApp.contact.ID = dashboardItem[arg2].getID();
				BrosApp.contact.name = dashboardItem[arg2].getName();
				BrosApp.contact.number = dashboardItem[arg2].getNumber();
				Intent activity = new Intent(Dashboard.this, messages.class);
				startActivity(activity);

			}
		});
		registerForContextMenu(List);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		if (item.getItemId() == R.id.dLUpdate) {

			Intent activity = new Intent(Dashboard.this, AddContact.class);
			activity.putExtra("REQUEST", "UPDATE");
			activity.putExtra("ID", dashboardItem[info.position].getID());
			startActivity(activity);
		} else if (item.getItemId() == R.id.dLDelete) {

			db.DeleteContact(dashboardItem[info.position].getID());
			PreExecute();
		}

		return super.onContextItemSelected(item);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.dListView) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle(dashboardItem[info.position].getName());
			Log.d("BroApp--Dashboard", "Index " + info.position);

			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.dashboard_listmenu, menu);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.dashboard, menu);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setTitle("Bro-App Contacts");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case (R.id.new_message):

			Intent activity = new Intent(Dashboard.this, AddContact.class);
			activity.putExtra("REQUEST", "CREATE");
			startActivity(activity);

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

		_cursor = db.getDashboardContactList();

		Log.d(" BrosApp--DashboardList", "Cursor populated");
		if (_cursor.getCount() > 0) {
			dashboardItem = new DashboardObject[_cursor.getCount()];
			Log.d(" BrosApp--DashboardList", "Data Exist");
			_cursor.moveToFirst();
			Log.d(" BrosApp--DashboardList", "Populating Adapter");

			isListEmpty = false;
			for (int i = 0; i < _cursor.getCount(); i++) {

				dashboardItem[i] = (new DashboardObject(_cursor.getInt(_cursor
						.getColumnIndex("ID")), _cursor.getString(_cursor
						.getColumnIndex("name")), _cursor.getString(_cursor
						.getColumnIndex("number")), _cursor.getBlob(_cursor
						.getColumnIndex("displayPic"))));

				_cursor.moveToNext();
			}

			_cursor.close();

			DashboardAdapter adapter = new DashboardAdapter(this, dashboardItem);

			List.setAdapter(adapter);
			ProText.setVisibility(View.INVISIBLE);
			Log.d(" BrosApp--DashboardList", "Populated");
		} else {
			ProText.setVisibility(View.VISIBLE);
			Log.d(" BrosApp--DashboardList", "No Data Found");
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("first_time", false)) {
			PreExecute();
			NetworkManager.FetchWiFi();

			if (isListEmpty)
				showPopup();
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
				Display display = getWindowManager().getDefaultDisplay();
				screen_width = display.getWidth(); // deprecated
				popup.showAtLocation(layout, Gravity.NO_GRAVITY, 500 - 100, 90);
			}
		});

	}

	class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SQLHelper.SetupDB(getBaseContext());
			SQLHelper.PopulateMessageList();

			return null;
		}

	}

}
