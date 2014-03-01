package com.silversage.heybabe.braodcastManager;

import java.util.Calendar;

import com.silversage.heybabe.service.ServiceManager;
import com.silversage.heybabe.util.SQLHelper;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootScheduler extends BroadcastReceiver {

	// Restart service every 30 seconds
	private static final long REPEAT_TIME = 1000 * 10;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!SQLHelper.getUser().equals("")) {

			Log.d("dfdf", "dfdf");
			AlarmManager service = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			Intent i = new Intent(context, ServiceManager.class);
			PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,
					PendingIntent.FLAG_CANCEL_CURRENT);
			Calendar cal = Calendar.getInstance();
			// Start 30 seconds after boot completed
			cal.add(Calendar.SECOND, 10);
			//
			// Fetch every 30 seconds
			// InexactRepeating allows Android to optimize the energy
			// consumption
			service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
					cal.getTimeInMillis(), REPEAT_TIME, pending);

			// service.setRepeating(AlarmManager.RTC_WAKEUP,
			// cal.getTimeInMillis(),
			// REPEAT_TIME, pending);

		}
	}
}
