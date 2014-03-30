package com.silversage.brosApp.objects.adapters;

public class WiFiObject {

	private String Name;
	private String ID;
	private boolean runWhenConnect;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getID() {
		return ID;
	}

	public WiFiObject(String name, String iD, boolean runWhenConnect) {
		super();
		Name = name;
		ID = iD;
		this.runWhenConnect = runWhenConnect;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean isRunWhenConnect() {
		return runWhenConnect;
	}

	public void setRunWhenConnect(boolean runWhenConnect) {
		this.runWhenConnect = runWhenConnect;
	}

}
