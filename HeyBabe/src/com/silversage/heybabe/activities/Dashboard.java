package com.silversage.heybabe.activities;

import com.silversage.heybabe.R;
import com.silversage.heybabe.R.layout;
import com.silversage.heybabe.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Dashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
