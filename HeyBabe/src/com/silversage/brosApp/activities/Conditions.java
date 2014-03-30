package com.silversage.brosApp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;

public class Conditions extends BrosAppActivity {

	Button openWiFi;
	ListView WiFiList;
	private static final int PICK_WIFI = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conditions);
		setupView();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case (PICK_WIFI):
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				Toast.makeText(Conditions.this, extras.get("data").toString(),
						Toast.LENGTH_LONG).show();
			}
			break;
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub

		openWiFi = (Button) findViewById(R.id.add_wifi);
		WiFiList = (ListView) findViewById(R.id.wifi_list);

		openWiFi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivityForResult(new Intent(
						WifiManager.ACTION_PICK_WIFI_NETWORK), PICK_WIFI);
			}
		});
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

}
