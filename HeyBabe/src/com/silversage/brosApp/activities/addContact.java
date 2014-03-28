package com.silversage.brosApp.activities;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silversage.brosApp.R;

public class addContact extends Activity {

	EditText name;
	EditText number;
	TextView intro_text;
	Button choosefromContact;
	ImageView displayPic;
	private static final int PICK_CONTACT = 3;
	private String contactId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		setupView();

	}

	private void setupView() {

		intro_text = (TextView) findViewById(R.id.intro_text);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"AdobeGothicStd.otf");
		intro_text.setTypeface(face);

		choosefromContact = (Button) findViewById(R.id.ChoosefromContact);
		choosefromContact.setTypeface(face);

		name = (EditText) findViewById(R.id.name);

		name.setHint("Enter name here");

		number = (EditText) findViewById(R.id.number);
		number.setHint("Enter number here");
		displayPic = (ImageView) findViewById(R.id.contact_photo);
		choosefromContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});

	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				ContentResolver cr = getContentResolver();
				name.setText(null);
				number.setText(null);

				Cursor c = managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {

					if (c.getColumnIndex(ContactsContract.Contacts._ID) != -1) {
						contactId = c.getString(c
								.getColumnIndex(ContactsContract.Contacts._ID));

						if (c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME) != -1) {
							Log.d("name",
									""
											+ c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
							name.setText(c.getString(c
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
						}

						Cursor phones = cr.query(Phone.CONTENT_URI, null,
								Phone.CONTACT_ID + " = " + contactId, null,
								null);
						if (phones.moveToFirst()) {
							if (phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) != -1) {
								{
									Log.d("number",
											""
													+ phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
									number.setText(phones.getString(phones
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
								}

								// ByteArrayOutputStream stream = new
								// ByteArrayOutputStream();
								// (retrieveContactPhoto()).compress(
								// Bitmap.CompressFormat.PNG, 10, stream);
								// byte[] byteArray = stream.toByteArray();
								// db.insertContact(name, phoneNumber, null);

							}
						}
						Uri uri = ContentUris.withAppendedId(
								ContactsContract.Contacts.CONTENT_URI,
								Long.parseLong(contactId));
						InputStream input = ContactsContract.Contacts
								.openContactPhotoInputStream(cr, uri);
						if (input != null) {
							displayPic.setImageBitmap(BitmapFactory
									.decodeStream(input));

						} else {
							displayPic.setImageResource(R.drawable.photo);
						}

						// retrieveContactPhoto();

					}

				}

			}
		}
	}

	private Bitmap retrieveContactPhoto() {

		Bitmap photo = null;

		try {
			InputStream inputStream = ContactsContract.Contacts
					.openContactPhotoInputStream(getContentResolver(),
							ContentUris.withAppendedId(
									ContactsContract.Contacts.CONTENT_URI,
									new Long(contactId)));

			if (inputStream != null) {
				photo = BitmapFactory.decodeStream(inputStream);
			}

			assert inputStream != null;
			inputStream.close();

		} catch (IOException e) {
			Log.d("image ex", e.getMessage());
		}
		return photo;

	}
}
