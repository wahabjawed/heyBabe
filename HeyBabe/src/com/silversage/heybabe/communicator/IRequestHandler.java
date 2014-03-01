package com.silversage.heybabe.communicator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.silversage.heybabe.HeyBabe;
import com.silversage.heybabe.activities.HeyBabeActivity;

public abstract class IRequestHandler {

	public SQLiteDatabase db = HeyBabe.getDb();
	public Context context = HeyBabe.getContext();
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

	public abstract void PerformTask(HeyBabeActivity _activity);

}
