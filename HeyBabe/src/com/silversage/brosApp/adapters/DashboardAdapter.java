package com.silversage.brosApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silversage.brosApp.R;
import com.silversage.brosApp.objects.adapters.DashboardObject;

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
		public ImageView displayPic;

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
			viewHolder.displayPic = (ImageView) vi
					.findViewById(R.id.list_image);
			vi.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) vi.getTag();
		DashboardObject obj = data[position];
		if (obj.getDisplayPic() != null) {
			Bitmap bmp = BitmapFactory.decodeByteArray(obj.getDisplayPic(), 0,
					obj.getDisplayPic().length);
			holder.displayPic.setImageBitmap(bmp);
		}
		holder.text.setText(obj.getName());
		holder.number.setText(obj.getNumber());

		return vi;
	}

}
