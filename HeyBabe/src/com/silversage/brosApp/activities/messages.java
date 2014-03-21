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
				getResources().getDrawable(android.R.drawable.ic_dialog_map));
		Intent newmsgIntent = new Intent(this, newMsg.class);
		newmsg.setContent(newmsgIntent);

		TabSpec log = tabHost.newTabSpec("tab2");
		log.setIndicator("",
				getResources().getDrawable(android.R.drawable.star_off));
		Intent logIntent = new Intent(this, msgLogs.class);
		log.setContent(logIntent);

		tabHost.addTab(newmsg);
		tabHost.addTab(log);
	}

}
