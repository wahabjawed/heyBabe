package com.silversage.brosApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.silversage.brosApp.R;
import com.silversage.brosApp.objects.MessageObject;

public class MessageAdapter extends ArrayAdapter<MessageObject> {

	private Activity activity;
	private MessageObject[] data;

	public MessageAdapter(Activity context, MessageObject[] _data) {
		super(context, R.layout.row_message, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {

		public TextView text;
		public CheckBox select;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_message, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) vi.findViewById(R.id.message);
			viewHolder.select = (CheckBox) vi.findViewById(R.id.select);

			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		MessageObject obj = data[position];
		holder.text.setText(obj.getMessageText());
		holder.select.setChecked(obj.isSelected());

		return vi;
	}

}
