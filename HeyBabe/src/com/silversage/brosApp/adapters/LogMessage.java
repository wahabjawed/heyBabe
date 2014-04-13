package com.silversage.brosApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.silversage.brosApp.BrosApp;
import com.silversage.brosApp.R;
import com.silversage.brosApp.objects.adapters.MessageObject;

public class LogMessage extends ArrayAdapter<MessageObject> {

	private Activity activity;
	private MessageObject[] data;

	public LogMessage(Activity context, MessageObject[] _data) {
		super(context, R.layout.row_logmessage, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {
		public TextView text;

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
			vi = inflater.inflate(R.layout.row_logmessage, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) vi.findViewById(R.id.message);

			Typeface face = Typeface.createFromAsset(BrosApp.getContext()
					.getAssets(), "AdobeGothicStd.otf");
			viewHolder.text.setTypeface(face);

			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		MessageObject obj = data[position];
		holder.text.setText(obj.getMessageText());

		return vi;
	}
}
