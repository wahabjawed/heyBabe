package com.silversage.brosApp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.silversage.brosApp.R;
import com.silversage.brosApp.adapters.DashboardAdapter;
import com.silversage.brosApp.adapters.DashboardObject;

public class Dashboard extends BrosAppActivity {

	ListView List;
	TextView ProText;
	DashboardObject[] dashboardItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!db.isFirstTime()) {

			setContentView(R.layout.dashboard);
			setupView();
			PreExecute();

		} else {

			startActivity(new Intent(this, SplashScreen.class));
			this.finish();
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub
		List = (ListView) findViewById(R.id.dListView);

		ProText = (TextView) findViewById(R.id.mProText);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub
		
				Cursor _cursor = null;
			
					_cursor = db.getDashboardList();
				
				Log.d(" BrosApp--DashboardList", "Cursor populated");
				if (_cursor.getCount() > 0) {
					Log.d(" BrosApp--DashboardList", "Data Exist");
					_cursor.moveToFirst();
					Log.d(" BrosApp--DashboardList", "Populating Adapter");

					for (int i = 0; i < _cursor.getCount(); i++) {

						dashboardItem[i]=(new DashboardObject(_cursor
								.getString(_cursor.getColumnIndex("name")), _cursor
								.getString(_cursor.getColumnIndex("ID"))));

						_cursor.moveToNext();
					}
					_cursor.close();

					DashboardAdapter adapter = new DashboardAdapter(this,
							dashboardItem);

					List.setAdapter(adapter);
					ProText.setVisibility(View.INVISIBLE);
					Log.d(" BrosApp--DashboardList", "Populated");
				} else {
					Log.d(" BrosApp--DashboardList", "No Data Found");
				}
				
				
			
				
	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub
		
	}

}
