package com.silversage.brosApp.braodcastManager;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.service.ServiceManager;
import com.silversage.brosApp.util.Notification;

public class BootScheduler extends BroadcastReceiver {

	// Restart service every 30 seconds
	private static final long REPEAT_TIME = 1000 * 6;

	@Override
	public void onReceive(Context context, Intent intent) {
		// SharedPreferences prefs = PreferenceManager
		// .getDefaultSharedPreferences(context);
		// if (prefs.getBoolean("first_time", false)) {
		Toast.makeText(context, "dfdf", Toast.LENGTH_SHORT).show();
		Notification.NewMessageNotification("fdfd", "fdfdf");
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

		// } else {
		Toast.makeText(BrosApp.context, "dfdf", Toast.LENGTH_SHORT).show();
		Notification.NewMessageNotification("sdsd", "sdsd");
		// }

	}
}