package com.silversage.brosApp.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.silversage.brosApp.R;
import com.silversage.brosApp.adapters.MessageAdapter;
import com.silversage.brosApp.objects.MessageObject;

public class NewMessage extends BrosAppActivity {

	Button timestamp;
	Button next;
	Button back;
	EditText selectTime;
	ListView messageList;
	MessageObject[] messageItem;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_newmessage);

		SetupView();
		PreExecute();
	}

	public void SetupView() {
		timestamp = (Button) findViewById(R.id.time_selection);
		next = (Button) findViewById(R.id.next);
		back = (Button) findViewById(R.id.back);
		selectTime = (EditText) findViewById(R.id.Select_Time);
		messageList = (ListView) findViewById(R.id.default_messages);

		selectTime.setHint("Enter Bro Time:");
		timestamp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		messageList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				CheckBox cBox = (CheckBox) view.findViewById(R.id.select);
				cBox.toggle();

				Toast.makeText(
						NewMessage.this,
						messageItem[0].isSelected() + "  "
								+ messageItem[1].isSelected(),
						Toast.LENGTH_SHORT).show();
				Log.d("BrosApp--MessageList", messageItem[0].isSelected()
						+ "  " + messageItem[1].isSelected());
			}
		});
	}

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

		Cursor _cursor = null;

		_cursor = db.getMessageList();

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
		}
		_cursor.close();

		MessageAdapter adapter = new MessageAdapter(this, messageItem);
		messageList.setAdapter(adapter);

		Log.d(" BrosApp--MessageList", "Populated");
	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}
}
