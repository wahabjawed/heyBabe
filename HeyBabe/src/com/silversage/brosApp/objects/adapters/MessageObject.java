package com.silversage.brosApp.objects.adapters;

public class MessageObject {

	private String messageText;
	private String ID;
	private boolean isSelected;

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public MessageObject(String iD, String messageText, boolean isSelected) {
		super();
		this.messageText = messageText;
		this.ID = iD;
		this.isSelected = isSelected;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
