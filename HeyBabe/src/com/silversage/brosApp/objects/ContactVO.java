package com.silversage.brosApp.objects;

import java.util.ArrayList;

public class ContactVO {

	int ID;
	String name;
	MessageVO message = new MessageVO();

	class MessageVO {

		String time;
		String repeat;
		ArrayList<WiFiConditionList> WifiCondition;

		class WiFiConditionList {

			boolean isEnabled;
			String SSID;
			boolean whenConnected;

		}

	}

}
