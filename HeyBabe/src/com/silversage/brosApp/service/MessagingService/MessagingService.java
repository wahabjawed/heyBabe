package com.silversage.brosApp.service.MessagingService;

import com.silversage.brosApp.util.Notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessagingService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Notification.NewMessageNotification("Hello", "Hello");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Notification.NewMessageNotification("Hello", "Hello-Bye");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
