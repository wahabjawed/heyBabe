package com.silversage.brosApp.adapters;

public class DashboardObject {
	private String Name;
	private String ID;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public DashboardObject(String name, String iD) {
		super();
		Name = name;
		ID = iD;
	}
}