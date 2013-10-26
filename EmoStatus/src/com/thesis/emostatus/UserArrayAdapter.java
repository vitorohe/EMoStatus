package com.thesis.emostatus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class UserArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	
	public UserArrayAdapter(Context context, String[] values) {
		super(context, R.layout.user_status_list, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.user_status_list, parent, false);
		TextView name = (TextView) rowView.findViewById(R.id.name);
		TextView status = (TextView) rowView.findViewById(R.id.status);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);
		return rowView;
	}

}
