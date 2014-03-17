package com.silversage.brosApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.silversage.brosApp.R;

public class Dashboard extends BrosAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!db.isFirstTime()) {

			setContentView(R.layout.dashboard);

		} else {
			
			startActivity(new Intent(this, SplashScreen.class));
			this.finish();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
