package com.silversage.brosApp.activities.abstracts;

import com.actionbarsherlock.app.SherlockActivity;
import com.silversage.brosApp.util.SQLHelper;

public abstract class BrosAppSherlockActivity extends SherlockActivity{

	protected SQLHelper db;
	public abstract void PostExecute();

	public abstract void PreExecute();

	public abstract void ProgressUpdate(String update);
	
}
