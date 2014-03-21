package com.silversage.brosApp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.silversage.brosApp.R;

public class newMsg extends Activity {

	Button timestamp;
	Button next;
	Button back;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_newmessage);

		timestamp = (Button) findViewById(R.id.time_selection);
		next = (Button) findViewById(R.id.next);
		back = (Button) findViewById(R.id.back);

		timestamp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}
}
