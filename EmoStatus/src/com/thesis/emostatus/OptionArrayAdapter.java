package com.thesis.emostatus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import persistance.OptionComponent;


public class OptionArrayAdapter extends ArrayAdapter<OptionComponent> {
	private final Context context;
	private final List<OptionComponent> values;

	public OptionArrayAdapter(Context context, List<OptionComponent> values) {
		super(context, R.layout.two_line_component_list, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View option = inflater.inflate(R.layout.icon_text_switch, parent, false);
		TextView title = (TextView) option.findViewById(R.id.title);
		ImageView icon= (ImageView) option.findViewById(R.id.icon);
		Switch button_switch = (Switch) option.findViewById(R.id.button_switch);
        button_switch.setTextOff(" ");
        button_switch.setTextOn(" ");
        title.setText(values.get(position).getTitle());
        //icon.setImageResource();
        button_switch.setChecked(values.get(position).isChecked());
		return option;
	}

}
