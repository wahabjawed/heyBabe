package com.silversage.brosApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.silversage.brosApp.R;

public class Slider extends BrosAppActivity {

	Button getStarted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);

		getStarted = (Button) findViewById(R.id.button1);
		getStarted.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.setFirstTime();
				startActivity(new Intent(Slider.this, Dashboard.class));
				Slider.this.finish();
			}
		});

	}

}
