package com.silversage.brosApp.objects;

import java.util.ArrayList;

import com.silversage.brosApp.objects.adapters.WiFiObject;

public class ContactVO {

	public int ID;
	public String name;
	public String number;
	public String messageText;
	public int messageID;
	public String time;
	public String repeat;
	public ArrayList<WiFiObject> WifiCondition;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public ArrayList<WiFiObject> getWifiCondition() {
		return WifiCondition;
	}

	public void setWifiCondition(ArrayList<WiFiObject> wifiCondition) {
		WifiCondition = wifiCondition;
	}

}
