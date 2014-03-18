package com.silversage.brosApp.adapters;

import com.silversage.brosApp.activities.SliderImage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SliderAdapter extends FragmentPagerAdapter {
	private Context _context;

	public SliderAdapter(Context context, FragmentManager fm) {
		super(fm);
		_context = context;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment f = new Fragment();
		switch (position) {
		case 0:
			f = new SliderImage().newInstance(_context,0);
			break;
		case 1:
			f = new SliderImage().newInstance(_context,1);
			break;

		case 2:
			f = new SliderImage().newInstance(_context,2);
			break;

		case 3:
			f = new SliderImage().newInstance(_context,3);
			break;

		case 4:
			f = new SliderImage().newInstance(_context,4);
			break;
		case 5:
			f = new SliderImage().newInstance(_context,5);
			break;
		case 6:
			f = new SliderImage().newInstance(_context,6);
			break;

		}
		return f;
	}

	@Override
	public int getCount() {
		return 7;
	}

}
