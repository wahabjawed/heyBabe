package com.silversage.brosApp.activities;

import com.silversage.brosApp.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class messages extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		Resources resources = getResources();

		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();

		TabSpec specs = th.newTabSpec("tag1");

		specs = th.newTabSpec("tag1");
		specs.setContent(R.id.log);
		specs.setIndicator("",
				resources.getDrawable(android.R.drawable.ic_dialog_map));
		th.addTab(specs);

		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.newMessage);
		specs.setIndicator("",
				resources.getDrawable(android.R.drawable.star_off));
		th.addTab(specs);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}
}
