package com.silversage.brosApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.silversage.brosApp.R;

public class DashboardAdapter extends ArrayAdapter<DashboardObject> {

	private Activity activity;
	private DashboardObject[] data;

	public DashboardAdapter(Activity context, DashboardObject[] _data) {
		super(context, R.layout.row_dashboard, _data);
		this.activity = context;
		this.data = _data;
	}

	static class ViewHolder {

		public TextView text;
		public TextView number;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (vi == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			Log.d("BrosApp--DashboardAdapter", "Inflating Layout");
			vi = inflater.inflate(R.layout.row_dashboard, parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) vi.findViewById(R.id.name);
			viewHolder.number = (TextView) vi.findViewById(R.id.timestamp);

			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		DashboardObject list = data[position];
		holder.text.setText(list.getName());
		holder.number.setText(list.getID());

		return vi;
	}

}
