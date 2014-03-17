package com.silversage.brosApp.service;

import com.silversage.brosApp.service.SMSService.SMSService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ServiceManager extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, SMSService.class);
		context.startService(service);
	}

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		// TODO Auto-generated method stub
		return super.peekService(myContext, service);
	}

}
