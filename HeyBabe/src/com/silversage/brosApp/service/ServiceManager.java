package com.silversage.brosApp.service;

import com.silversage.brosApp.service.MessagingService.MessagingService;
import com.silversage.brosApp.util.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ServiceManager extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Notification.NewMessageNotification("Hello", "Hello");
		Intent service = new Intent(context, MessagingService.class);
		context.startService(service);
	}

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		// TODO Auto-generated method stub
		return super.peekService(myContext, service);
	}

}
