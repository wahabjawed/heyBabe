package com.silversage.brosApp.activities;

import java.util.Calendar;

import com.silversage.brosApp.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MessageDetails extends Activity {

	private TextView tvDisplayTime;
	private Button timeSelection;
	private int hour;
	private int minute;

	static final int TIME_DIALOG_ID = 999;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_details);

		setCurrentTimeOnView();

		timeSelection = (Button) findViewById(R.id.time_selection);

		timeSelection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

	}

	// display current time
	public void setCurrentTimeOnView() {

		tvDisplayTime = (TextView) findViewById(R.id.time_selection);

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		// set current time into textview
		tvDisplayTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));

		// set current time into timepicker

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
