package com.silversage.brosApp.util;

import android.telephony.SmsManager;

public class SMSManager {

	public static int SendSMS(String number, String message) {

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(number, null, message, null, null);
		return 0;

	}

}
