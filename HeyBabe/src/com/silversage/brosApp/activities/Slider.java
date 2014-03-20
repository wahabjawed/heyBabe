package com.silversage.brosApp.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.silversage.brosApp.R;
import com.silversage.brosApp.adapters.SliderAdapter;

public class Slider extends FragmentActivity {
	ViewPager _mViewPager;
	SliderAdapter _adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);
		setUpView();
		
	}

	private void setUpView() {

		_mViewPager = (ViewPager) findViewById(R.id.viewPager);
		_adapter = new SliderAdapter(getApplicationContext(),
				getSupportFragmentManager());
		_mViewPager.setAdapter(_adapter);
		_mViewPager.setCurrentItem(0);
	}

}