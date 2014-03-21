package com.silversage.brosApp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;

public class SliderImage extends Fragment {

	int index = 0;
	static int[] imageIndex = new int[5];
	Button finish;
	RelativeLayout relLayout;

	public Fragment newInstance(Context context, int index) {
		SliderImage f = new SliderImage();
		f.index = index;
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.slider, null);

		relLayout = (RelativeLayout) root.findViewById(R.id.relLayout);
		finish = (Button) root.findViewById(R.id.button1);

		if (index < 6) {
			finish.setVisibility(View.INVISIBLE);
		}
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("first_time", true);
				editor.commit();
				startActivity(new Intent(BrosApp.context, Dashboard.class));
				getActivity().finish();
			}
		});

		setUI();

		return root;

	}

	private void setUI() {
		// TODO Auto-generated method stub
		if (index == 0) {
			relLayout.setBackgroundResource(R.drawable.splash2);
		} else if (index == 1) {
			relLayout.setBackgroundResource(R.drawable.splash2);
		} else if (index == 2) {
			relLayout.setBackgroundResource(R.drawable.splash3);
		} else if (index == 3) {
			relLayout.setBackgroundResource(R.drawable.splash4);
		} else if (index == 4) {
			relLayout.setBackgroundResource(R.drawable.splash5);
		} else if (index == 5) {
			relLayout.setBackgroundResource(R.drawable.splash6);
		} else if (index == 6) {
			relLayout.setBackgroundResource(R.drawable.splash7);
		}

	}
}
