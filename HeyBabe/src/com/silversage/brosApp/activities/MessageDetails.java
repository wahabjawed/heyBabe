package com.silversage.brosApp.activities;

import java.util.Calendar;

import com.silversage.brosApp.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

public class MessageDetails extends Activity {

	private TextView tvDisplayTime;
	private Button timeSelection;
	private TextView Selectdays;
	private Button nextbutton;
	private Button backbutton;
	private int hour;
	private int minute;
	private Button repeat;
	int screen_width;

	static final int TIME_DIALOG_ID = 999;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_details);

		Selectdays = (TextView) findViewById(R.id.Select_Days);

		repeat = (Button) findViewById(R.id.days_Button);
		repeat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();

			}

		});
		Display display = getWindowManager().getDefaultDisplay();
		screen_width = display.getWidth(); // deprecated

		tvDisplayTime = (TextView) findViewById(R.id.Select_Time);

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		nextbutton = (Button) findViewById(R.id.nextbutton);
		nextbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MessageDetails.this, Conditions.class);
				startActivity(i);
			}
		});

		backbutton = (Button) findViewById(R.id.back);
		backbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MessageDetails.this.finish();
			}
		});

		timeSelection = (Button) findViewById(R.id.time_selection);

		timeSelection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

	}

	private void initiatePopupWindow() {
		// TODO Auto-generated method stub
		final Button btnOpenPopup = (Button) findViewById(R.id.days_Button);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

			String[] data = { "Don't Repeat", "Daily", "Weekly", "Monthly" };

			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.screen_popup,
						null);

				final PopupWindow popupWindow = new PopupWindow(popupView,
						screen_width, 300, true);

				ListView listView = (ListView) popupView
						.findViewById(R.id.listview);
				listView.setAdapter(new ArrayAdapter<String>(
						getApplicationContext(),
						android.R.layout.simple_list_item_1, data));
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View view,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						Selectdays.setText(((TextView) view).getText());
						popupWindow.dismiss();
					}
				});

				popupWindow.showAsDropDown(btnOpenPopup, 20, -5);

			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);

		}
		return null;

	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// set current time into textview
			tvDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
