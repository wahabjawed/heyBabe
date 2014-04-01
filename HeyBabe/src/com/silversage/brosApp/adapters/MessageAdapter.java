package com.silversage.brosApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.objects.adapters.MessageObject;

public class MessageAdapter extends ArrayAdapter<MessageObject> {

	private Activity activity;
	private MessageObject[] data;
	private RadioButton mSelectedRB;
	private int mSelectedPosition = -1;

	public MessageAdapter(Activity context, MessageObject[] _data) {
		super(context, R.layout.row_message, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {

		public TextView text;
		public RadioButton select;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		final int pos = position;
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_message, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) vi.findViewById(R.id.message);
			viewHolder.select = (RadioButton) vi.findViewById(R.id.select);
			Typeface face = Typeface.createFromAsset(BrosApp.getContext().getAssets(),
					"AdobeGothicStd.otf");
			viewHolder.text.setTypeface(face);

			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		MessageObject obj = data[position];
		holder.text.setText(obj.getMessageText());
		holder.select.setChecked(obj.isSelected());
		holder.select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (position != mSelectedPosition && mSelectedRB != null) {
					mSelectedRB.setChecked(false);
				}

				mSelectedPosition = position;
				mSelectedRB = (RadioButton) v;
				data[pos].setSelected(!data[pos].isSelected());

			}
		});

		if (mSelectedPosition != position) {
			holder.select.setChecked(false);
		} else {
			holder.select.setChecked(true);
			if (mSelectedRB != null && holder.select != mSelectedRB) {
				mSelectedRB = holder.select;
			}
		}
		return vi;
	}
}
