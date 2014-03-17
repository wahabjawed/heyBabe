package com.silversage.brosApp.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.silversage.brosApp.R;

public class Slider extends BrosAppActivity {

	TextView detailed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		detailed=(TextView) findViewById(R.id.detail);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

}
