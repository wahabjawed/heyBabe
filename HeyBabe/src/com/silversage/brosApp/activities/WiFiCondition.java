package com.silversage.brosApp.activities;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;
import com.silversage.brosApp.adapters.WiFiAdapter;
import com.silversage.brosApp.objects.adapters.WiFiObject;

public class WiFiCondition extends BrosAppActivity {

	Button openWiFi;
	ListView WiFiList;
	Button nextButton;
	Button backButton;

	ArrayList<WiFiObject> PickList = new ArrayList<WiFiObject>();
	String[][] menuItems = BrosApp.WifiList;
	boolean notifyVal = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conditions);
		setupView();
		actionBarSetup();
		if (getIntent().getExtras().getString("REQUEST").equals("UPDATE")) {
			PickList = BrosApp.contact.getWifiCondition();
			Log.d("BrosApp--WifiList", PickList.get(0).getName());
			PreExecute();
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub

		openWiFi = (Button) findViewById(R.id.add_wifi);
		WiFiList = (ListView) findViewById(R.id.wifi_list);

		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BrosApp.contact.setWifiCondition(PickList);
				BrosApp.contact.nofity = ((notifyVal == true) ? 1 : 0);
				Intent i = new Intent(WiFiCondition.this, Confirmation.class);
				i.putExtra("REQUEST",
						getIntent().getExtras().getString("REQUEST"));
				startActivity(i);

			}
		});

		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WiFiCondition.this.finish();
			}
		});
		registerForContextMenu(openWiFi);
		openWiFi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				openContextMenu(openWiFi);

			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		PickList.add(new WiFiObject(menuItems[item.getItemId()][0],
				menuItems[item.getItemId()][1], false));
		PreExecute();

		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.add_wifi) {
			// AdapterView.AdapterContextMenuInfo info =
			// (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle("Pick Wifi");

			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i][0]);
			}
		}
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub
		WiFiAdapter adapter = new WiFiAdapter(this, PickList);
		WiFiList.setAdapter(adapter);
	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void actionBarSetup() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			android.app.ActionBar actionBar = getActionBar();
			actionBar.setTitle("Wifi Settings");
		}
	}

}
