package com.silversage.brosApp.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.silversage.brosApp.R;

public class messages extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		TabHost tabHost = getTabHost();

		TabSpec newmsg = tabHost.newTabSpec("tab1");
		newmsg.setIndicator("",
				getResources().getDrawable(R.drawable.compose));
		Intent newmsgIntent = new Intent(this, NewMessage.class);
		newmsg.setContent(newmsgIntent);

		TabSpec log = tabHost.newTabSpec("tab2");
		log.setIndicator("",
				getResources().getDrawable(R.drawable.logs));
		Intent logIntent = new Intent(this, MessageLog.class);
		log.setContent(logIntent);

		tabHost.addTab(newmsg);
		tabHost.addTab(log);
	}

}
