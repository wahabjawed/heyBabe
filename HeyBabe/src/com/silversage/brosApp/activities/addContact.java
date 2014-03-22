package com.silversage.brosApp.activities;

import com.silversage.brosApp.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class addContact extends Activity {
	
	EditText name;
	EditText number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		
		name = (EditText) findViewById(R.id.name);
		name.setHint("Enter name here");
		
		number = (EditText) findViewById(R.id.number);
		number.setHint("Enter number here");
	}

}
