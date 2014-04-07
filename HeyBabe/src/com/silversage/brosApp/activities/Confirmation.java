package com.silversage.brosApp.activities;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.objects.ContactVO;
import com.silversage.brosApp.util.SQLHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends Activity {

	Button next;
	Button back;
	TextView name;
	TextView number;
	TextView timeDay;
	TextView WiFi;
	TextView message;
	ContactVO contact = BrosApp.contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmation);
		setupView();

	}

	private void setupView() {
		// TODO Auto-generated method stub
		next = (Button) findViewById(R.id.next);
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Confirmation.this.finish();
			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLHelper.insertTran(contact);
				Intent intent = new Intent(getApplicationContext(),
						Dashboard.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
		name = (TextView) findViewById(R.id.text_number);
		name.setText(contact.getName());
		number = (TextView) findViewById(R.id.text_name);
		number.setText(contact.getNumber());
		timeDay = (TextView) findViewById(R.id.text_time_day);
		timeDay.setText(contact.getTime() + " " + contact.getDay());
		WiFi = (TextView) findViewById(R.id.text_wifi_condition);
		if (contact.WifiCondition.size() > 0) {
			WiFi.setLines(contact.WifiCondition.size());
			WiFi.setMaxLines(contact.WifiCondition.size());
			String Wifitext = "";
			for (int i = 0; i < contact.WifiCondition.size(); i++) {
				Wifitext = Wifitext + contact.WifiCondition.get(i).getName()
						+ "\n";

			}
			WiFi.setText(Wifitext);
		}

		message = (TextView) findViewById(R.id.text_message);
		message.setText(contact.getMessageText());
	}
}
