package com.silversage.brosApp.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;

public class MessageDetails extends BrosAppActivity {

	private TextView tvDisplayTime;
	private Button timeSelection;
	private Button nextbutton;
	private Button backbutton;
	private int hour;
	private int minute;

	private Button daySelection;
	private Button frequency;
	private TextView tvFrequency;
	private TextView tvDay;

	static final int TIME_DIALOG_ID = 999;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_details);

		setupView();

	}

	private void setupView() {

		tvDay = (TextView) findViewById(R.id.Select_Days);
		daySelection = (Button) findViewById(R.id.days_Button);
		tvFrequency = (TextView) findViewById(R.id.ReminderMe);
		frequency = (Button) findViewById(R.id.Reminder_Button);
		tvDisplayTime = (TextView) findViewById(R.id.Select_Time);

		frequency.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				registerForContextMenu(frequency);
				openContextMenu(frequency);
				unregisterForContextMenu(frequency);

			}
		});

		daySelection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerForContextMenu(daySelection);
				openContextMenu(daySelection);
				unregisterForContextMenu(daySelection);
			}
		});

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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.Reminder_Button) {
			menu.setHeaderTitle("Choose Repeat");

			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.repeat, menu);
		} else if (v.getId() == R.id.days_Button) {

			menu.setHeaderTitle("Choose Day");

			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.day_select, menu);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		return true;
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

}
