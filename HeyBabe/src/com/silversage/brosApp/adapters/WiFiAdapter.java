package com.silversage.brosApp.adapters;

import java.util.ArrayList;

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
import com.silversage.brosApp.objects.adapters.WiFiObject;

public class WiFiAdapter extends ArrayAdapter<WiFiObject> {

	private Activity activity;
	private ArrayList<WiFiObject> data;

	public WiFiAdapter(Activity context, ArrayList<WiFiObject> _data) {
		super(context, R.layout.row_wifi, _data);
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
		final int pos = position;
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_wifi, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) vi.findViewById(R.id.name);
			viewHolder.select = (CheckBox) vi.findViewById(R.id.select);

			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		WiFiObject obj = data.get(position);
		holder.text.setText(obj.getName());
		holder.select.setChecked(obj.isRunWhenConnect());

		return vi;
	}

}
