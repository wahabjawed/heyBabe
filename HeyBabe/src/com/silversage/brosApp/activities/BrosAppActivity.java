package com.silversage.brosApp.activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.silversage.brosApp.util.SQLHelper;

import android.app.Activity;

public abstract class BrosAppActivity extends SherlockActivity{

	protected SQLHelper db;
	public abstract void PostExecute();

	public abstract void PreExecute();

	public abstract void ProgressUpdate(String update);
	
}
