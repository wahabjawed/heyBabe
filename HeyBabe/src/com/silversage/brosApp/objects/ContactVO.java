package com.silversage.brosApp.objects;

import java.util.ArrayList;

public class ContactVO {

	public int ID;
	public String name;
	public String number;
	public MessageVO message = new MessageVO();

	class MessageVO {

		public String time;
		public String repeat;
		public ArrayList<WiFiConditionList> WifiCondition;

		class WiFiConditionList {

			boolean isEnabled;
			String SSID;
			boolean whenConnected;

		}

	}

}
