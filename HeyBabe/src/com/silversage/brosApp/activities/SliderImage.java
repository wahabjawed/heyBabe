package com.silversage.brosApp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;

public class SliderImage extends Fragment {

	int index = 0;
	static int[] imageIndex = new int[5];
	Button finish;

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

		// question = (TextView) root.findViewById(R.id.textView1);
		finish = (Button) root.findViewById(R.id.button1);

		if(index<4){
			finish.setVisibility(View.INVISIBLE);
		}
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(BrosApp.context, Dashboard.class));
				getActivity().finish();
			}
		});

		setUI(index);

		return root;

	}

	private void setUI(int index2) {
		// TODO Auto-generated method stub
	
	}

}
