package com.silversage.brosApp.communicator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.activities.BrosAppActivity;

public abstract class IRequestHandler {

	public SQLiteDatabase db = BrosApp.getDb();
	public Context context = BrosApp.getContext();
	int Success = 0;

	public SQLiteDatabase getDb() {
		return db;
	}

	public int getSuccess() {
		return Success;
	}

	public void setSuccess(int success) {
		Success = success;
	}

	public abstract void PerformTask(BrosAppActivity _activity);

}
