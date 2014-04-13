package com.silversage.brosApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;

public class SplashScreen extends BrosAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash_screen);
		final Handler handler = new Handler();
		final Runnable r = new Runnable() {

			public void run() {

				startActivity(new Intent(SplashScreen.this, Slider.class));
				SplashScreen.this.finish();

			}
		};
		handler.postDelayed(r, 1000 * 3);

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
