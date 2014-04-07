package com.silversage.brosApp.activities.abstracts;

import android.app.Activity;
import android.os.Bundle;

import com.silversage.brosApp.util.SQLHelper;

public abstract class BrosAppActivity extends Activity{

	protected SQLHelper db;
	public abstract void PostExecute();

	public abstract void PreExecute();

	public abstract void ProgressUpdate(String update);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
}
