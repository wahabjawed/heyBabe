package com.silversage.brosApp.objects;

import java.util.ArrayList;

public class ContactVO {

	String name;
	String no;
	byte pic;
	String countryID;
	ArrayList messagesScheduler= new ArrayList<MessagesScheduler>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public byte getPic() {
		return pic;
	}

	public void setPic(byte pic) {
		this.pic = pic;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	class MessagesScheduler {

	}

}
