package com.silversage.brosApp.activities;

import com.silversage.brosApp.util.SQLHelper;

import android.app.Activity;

public abstract class BrosAppActivity extends Activity{

	protected SQLHelper db;
	public abstract void PostExecute();

	public abstract void PreExecute();

	public abstract void ProgressUpdate(String update);
	
}
