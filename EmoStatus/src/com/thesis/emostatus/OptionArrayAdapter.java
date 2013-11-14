package com.thesis.emostatus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
	public View getView(final int position, View convertView, final ViewGroup parent) {
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View option = inflater.inflate(R.layout.icon_text_switch, parent, false);
		TextView title = (TextView) option.findViewById(R.id.title);
		ImageView icon= (ImageView) option.findViewById(R.id.icon);
		Switch button_switch = (Switch) option.findViewById(R.id.button_switch);
        button_switch.setTextOff("NO");
        button_switch.setTextOn("SÍ");
        title.setText(values.get(position).getTitle());
        icon.setImageResource(values.get(position).getIconId());
        button_switch.setChecked(values.get(position).isChecked());

        button_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (values.get(position).getIconId()){
                    case R.drawable.icon_skype:
                        break;
                    case R.drawable.icon_mic:
                        View days_week = compoundButton.getRootView().getRootView().findViewById(R.id.days_week);
                        days_week.findViewById(R.id.title).setEnabled(b);
                        days_week.findViewById(R.id.info).setEnabled(b);
                        days_week.setEnabled(b);

                        View init_hour = compoundButton.getRootView().getRootView().findViewById(R.id.init_hour);
                        init_hour.findViewById(R.id.title).setEnabled(b);
                        init_hour.findViewById(R.id.info).setEnabled(b);
                        init_hour.setEnabled(b);

                        View end_hour = compoundButton.getRootView().getRootView().findViewById(R.id.end_hour);
                        end_hour.findViewById(R.id.title).setEnabled(b);
                        end_hour.findViewById(R.id.info).setEnabled(b);
                        end_hour.setEnabled(b);
                        View list_mic = compoundButton.getRootView().getRootView().findViewById(R.id.list_mic);
                        list_mic.setEnabled(b);
                        break;
                    case R.drawable.icon_sms:
                        View alert_sms = compoundButton.getRootView().getRootView().findViewById(R.id.alert_sms);
                        alert_sms.findViewById(R.id.title).setEnabled(b);
                        alert_sms.setEnabled(b);
                        View list_alert_sms = compoundButton.getRootView().getRootView().findViewById(R.id.list_alert_sms);
                        list_alert_sms.setEnabled(b);
                        break;
                    case R.drawable.icon_email:
                        View alert_email = compoundButton.getRootView().getRootView().findViewById(R.id.alert_email);
                        alert_email.findViewById(R.id.title).setEnabled(b);
                        alert_email.setEnabled(b);
                        View list_alert_email = compoundButton.getRootView().getRootView().findViewById(R.id.list_alert_email);
                        list_alert_email.setEnabled(b);
                        break;
                    case R.drawable.icon_emonot:
                        break;
                    default:
                        break;
                }
            }
        });

		return option;
	}

}
