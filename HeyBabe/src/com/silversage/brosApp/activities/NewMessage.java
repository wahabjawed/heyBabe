package com.silversage.brosApp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;
import com.silversage.brosApp.adapters.MessageAdapter;
import com.silversage.brosApp.objects.adapters.MessageObject;

public class NewMessage extends BrosAppActivity {

	Button timestamp;
	Button next;
	Button back;
	EditText selectTime;
	ListView messageList;
	MessageObject[] messageItem;
	Button addMessage;

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
		addMessage = (Button) findViewById(R.id.add_message);
		selectTime.setHint("Enter Bro Time:");

		addMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(NewMessage.this);
				View promptsView = li
						.inflate(R.layout.new_message_prompt, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						NewMessage.this);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);

				// set dialog message
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// get user input and set it to result
										// edit text
										if (userInput.getText().toString() != "") {
											db.insertRefMessage(
													BrosApp.contact.ID,
													userInput.getText()
															.toString());
											PreExecute();
										}
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
		});

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
				Intent i = new Intent(NewMessage.this, MessageDetails.class);
				startActivity(i);
			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NewMessage.this.finish();
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

		_cursor = db.getMessageList(BrosApp.contact.ID);

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
