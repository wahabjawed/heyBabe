package com.silversage.brosApp.activities;

import com.silversage.brosApp.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends BrosAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash_screen);
		final Handler handler = new Handler();
		final Runnable r = new Runnable() {

			public void run() {
				
				startActivity(new Intent(SplashScreen.this,Slider.class));
				SplashScreen.this.finish();
			
			}
		};
		handler.postDelayed(r, 1000 * 3);

	}

	
	
}
