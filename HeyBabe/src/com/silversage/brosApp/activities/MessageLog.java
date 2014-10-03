package com.silversage.brosApp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;
import com.silversage.brosApp.adapters.LogMessage;
import com.silversage.brosApp.objects.adapters.MessageObject;
import com.silversage.brosApp.util.SQLHelper;

public class MessageLog extends BrosAppActivity {

	ListView messageList;
	MessageObject[] messageItem = new MessageObject[0];
	TextView broMessage;
	TextView Selectbropowered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_log);
		setupView();

		PreExecute();
	}

	private void setupView() {
		// TODO Auto-generated method stub
		messageList = (ListView) findViewById(R.id.default_messages);

		broMessage = (TextView) findViewById(R.id.Bro_messages);
		Selectbropowered = (TextView) findViewById(R.id.Select_bro_message);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"ufonts.com_gotham_medium.ttf");
		broMessage.setTypeface(face);

		Typeface face2 = Typeface.createFromAsset(getAssets(),
				"AdobeGothicStd.otf");
		Selectbropowered.setTypeface(face2);

		// messageList.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // TODO Auto-generated method stub
		// Intent i = new Intent(MessageLog.this, Confirmation.class);
		// i.putExtra("ACTION", "DELETE");
		// i.putExtra("ID", messageItem[position].getID());
		// startActivity(i);
		// }
		// });

		registerForContextMenu(messageList);
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

		Log.d("", "" + BrosApp.contact.ID);
		Cursor _cursor = SQLHelper.getLogMessageList(BrosApp.contact.ID);

		Log.d(" BrosApp--MessageList", "Cursor populated");
		if (_cursor.getCount() > 0) {
			messageItem = new MessageObject[_cursor.getCount()];
			Log.d(" BrosApp--MessageList", "Data Exist");
			_cursor.moveToFirst();
			Log.d(" BrosApp--MessageList", "Populating Adapter");

			for (int i = 0; i < _cursor.getCount(); i++) {

				messageItem[i] = (new MessageObject(_cursor.getString(_cursor
						.getColumnIndex("ID")), _cursor.getString(_cursor
						.getColumnIndex("message")), false));

				_cursor.moveToNext();
			}
		} else {

			messageItem = new MessageObject[0];
		}
		_cursor.close();

		LogMessage adapter = new LogMessage(this, messageItem);
		messageList.setAdapter(adapter);

		Log.d(" BrosApp--MessageList", "Populated");
	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		if (item.getItemId() == R.id.lUpdate) {
			SQLHelper.getTran(messageItem[info.position].getID());
			Intent activity = new Intent(MessageLog.this, NewMessage.class);
			activity.putExtra("REQUEST", "UPDATE");
			startActivity(activity);

		} else if (item.getItemId() == R.id.lDelete) {

			SQLHelper.DeleteTran(BrosApp.contact.ID,
					messageItem[info.position].getID());
			PreExecute();
		}

		return super.onContextItemSelected(item);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.default_messages) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle("Bros App");
			Log.d("BroApp--Log Message", "Index " + info.position);

			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.log_listmenu, menu);
		}
	}

}
