package com.silversage.brosApp.activities;

import java.io.ByteArrayOutputStream;
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
import android.provider.MediaStore;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silversage.brosApp.R;
import com.silversage.brosApp.activities.abstracts.BrosAppActivity;

public class AddContact extends BrosAppActivity {
	private static final int CAMERA = 0;
	private static final int GALLERY = 1;
	EditText name;
	EditText number;
	TextView intro_text;
	Button choosefromContact;
	ImageView displayPic;
	Button nextButton;
	Button backButton;
	private static final int PICK_CONTACT = 3;
	private String contactId;
	InputStream input = null;

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

		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddContact.this.finish();
			}
		});

		name = (EditText) findViewById(R.id.name);
		name.setHint("Enter name here");

		number = (EditText) findViewById(R.id.number);
		number.setHint("Enter number here");
		displayPic = (ImageView) findViewById(R.id.contact_photo);
		displayPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				registerForContextMenu(displayPic);
				openContextMenu(displayPic);
				unregisterForContextMenu(displayPic);

			}
		});

		choosefromContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});

		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (name.getText().toString() == null
						&& number.getText() == null) {

					Toast.makeText(AddContact.this,
							"Kindly Enter a Valid Name and Number",
							Toast.LENGTH_SHORT).show();
				} else if (name.getText().toString() == null) {

					Toast.makeText(AddContact.this,
							"Kindly Enter a Valid Name", Toast.LENGTH_SHORT)
							.show();

				} else if (number.getText().toString() == null) {

					Toast.makeText(AddContact.this,
							"Kindly Enter a Valid Number", Toast.LENGTH_SHORT)
							.show();

				} else if (number.getText().toString().length() < 9) {

					Toast.makeText(AddContact.this,
							"Kindly Enter a Valid Number", Toast.LENGTH_SHORT)
							.show();

				} else {
					if (input != null) {
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						(retrieveContactPhoto()).compress(
								Bitmap.CompressFormat.PNG, 10, stream);
						byte[] byteArray = stream.toByteArray();
						db.insertContact(name.getText().toString(), number
								.getText().toString(), byteArray);
					} else {
						db.insertContact(name.getText().toString(), number
								.getText().toString(), null);
					}
					AddContact.this.finish();

				}

			}
		});

	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (CAMERA):
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				Bitmap bmp = (Bitmap) extras.get("data");
				displayPic.setImageBitmap(bmp);
			}
			break;

		case (GALLERY):
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				if (selectedImage != null) {
					String[] filePathColumn = { MediaStore.Images.Media.DATA };
					Cursor cursor = getContentResolver().query(selectedImage,
							filePathColumn, null, null, null);
					cursor.moveToFirst();
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String picturePath = cursor.getString(columnIndex);
					cursor.close();

					displayPic.setImageBitmap(BitmapFactory
							.decodeFile(picturePath));
				}
			}

			break;

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

							}
						}
						Uri uri = ContentUris.withAppendedId(
								ContactsContract.Contacts.CONTENT_URI,
								Long.parseLong(contactId));
						input = ContactsContract.Contacts
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
			break;

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

	@Override
	public void PostExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ProgressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.item1) {

			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, CAMERA);

		} else if (item.getItemId() == R.id.item2) {
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("image/*");
			startActivityForResult(intent, GALLERY);

		}

		return super.onContextItemSelected(item);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.display_pic_menu, menu);

	}

}