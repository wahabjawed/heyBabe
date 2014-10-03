package com.silversage.brosApp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;
import com.silversage.brosApp.adapters.MessageAdapter;
import com.silversage.brosApp.objects.adapters.MessageObject;

public class NewMessage extends BrosAppActivity {

	Button next;
	Button back;
	TextView broMessage;
	TextView Selectbropowered;
	public int selectedMsgID = -1;
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
		next = (Button) findViewById(R.id.next);
		back = (Button) findViewById(R.id.back);
		messageList = (ListView) findViewById(R.id.default_messages);
		addMessage = (Button) findViewById(R.id.add_message);

		broMessage = (TextView) findViewById(R.id.Bro_messages);
		Selectbropowered = (TextView) findViewById(R.id.Select_bro_message);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"ufonts.com_gotham_medium.ttf");
		broMessage.setTypeface(face);

		Typeface face2 = Typeface.createFromAsset(getAssets(),
				"AdobeGothicStd.otf");
		Selectbropowered.setTypeface(face2);

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
											selectedMsgID = -1;
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
				alertDialog.getWindow().setSoftInputMode(
						LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				// show it
				alertDialog.show();
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (selectedMsgID != -1) {
					BrosApp.contact.messageID = Integer
							.parseInt(messageItem[selectedMsgID].getID());
					BrosApp.contact.messageText = messageItem[selectedMsgID]
							.getMessageText();
					Intent i = new Intent(NewMessage.this, MessageDetails.class);
					i.putExtra("REQUEST",
							getIntent().getExtras().getString("REQUEST"));
					startActivity(i);
				} else {
					Toast.makeText(NewMessage.this,
							"Hey Bro, Select a message!", Toast.LENGTH_SHORT)
							.show();

				}

			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NewMessage.this.finish();
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

		Log.i(" BrosApp--MessageList", "Cursor populated");
		if (_cursor.getCount() > 0) {
			messageItem = new MessageObject[_cursor.getCount()];
			Log.i(" BrosApp--MessageList", "Data Exist");
			_cursor.moveToFirst();
			Log.i(" BrosApp--MessageList", "Populating Adapter");

			for (int i = 0; i < _cursor.getCount(); i++) {

				messageItem[i] = (new MessageObject(_cursor.getString(_cursor
						.getColumnIndex("ID")), _cursor.getString(_cursor
						.getColumnIndex("message")), false));

				_cursor.moveToNext();
			}
		}
		_cursor.close();

		if (getIntent().getExtras().getString("REQUEST").equals("UPDATE")) {

			for (int i = 0; i < messageItem.length; i++) {
				if (Integer.parseInt(messageItem[i].getID()) == BrosApp.contact
						.getMessageID()) {
					messageItem[i].setSelected(true);
					selectedMsgID = i;
					Log.i(" BrosApp--MessageList", "In Update Mode: "
							+ BrosApp.contact.getMessageID() + " iteration: "
							+ i);
				}
			}
		}
		MessageAdapter adapter = new MessageAdapter(this, messageItem);
		messageList.setAdapter(adapter);

		Log.i(" BrosApp--MessageList", "Populated");
	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

}
