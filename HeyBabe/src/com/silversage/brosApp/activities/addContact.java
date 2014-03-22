package com.silversage.brosApp.activities;

import com.silversage.brosApp.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addContact extends Activity {

	EditText name;
	EditText number;
	TextView intro_text;
	Button choosefromContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);

		intro_text = (TextView) findViewById(R.id.intro_text);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"AdobeGothicStd.otf");
		intro_text.setTypeface(face);
		
		choosefromContact=(Button) findViewById(R.id.ChoosefromContact);
		choosefromContact.setTypeface(face);

		name = (EditText) findViewById(R.id.name);

		name.setHint("Enter name here");

		number = (EditText) findViewById(R.id.number);
		number.setHint("Enter number here");
	}

}

