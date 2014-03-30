package com.silversage.brosApp.objects.adapters;

public class DashboardObject {
	private String Name;
	private int ID;
	private String Number;
	private byte[] DisplayPic;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public DashboardObject(int iD, String name, String number,
			byte[] displayPic) {
		super();
		Name = name;
		ID = iD;
		Number = number;
		DisplayPic = displayPic;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public byte[] getDisplayPic() {
		return DisplayPic;
	}

	public void setDisplayPic(byte[] displayPic) {
		DisplayPic = displayPic;
	}
}