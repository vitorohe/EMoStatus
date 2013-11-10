package com.thesis.emostatus;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;
import persistance.OptionComponent;
import persistance.OptionInfoComponent;

public class MonitorUserActivity extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView sv = new ScrollView(getActivity());
        sv.setFillViewport(true);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        addOptionsToListLayout(ll);
        return sv;
    }

    private void addOptionsToListLayout(LinearLayout ll) {
        EmoStatus app = (EmoStatus)getActivity().getApplicationContext();

        List<OptionComponent> opts = new ArrayList<OptionComponent>();
        opts.add(new OptionComponent("Skype","",false));
        opts.add(new OptionComponent("Grabación","",false));

        ListView options = new ListView(getActivity());
        OptionArrayAdapter adapter = new OptionArrayAdapter(getActivity(),opts);
        options.setAdapter(adapter);
        ll.addView(options);

        List<OptionInfoComponent> recordingOptsInfo = new ArrayList<OptionInfoComponent>();
        recordingOptsInfo.add(new OptionInfoComponent("Días de la semana","Lu,Ma,Ju",R.id.days_week));
        recordingOptsInfo.add(new OptionInfoComponent("Hora inicio","09:30",R.id.init_hour));
        recordingOptsInfo.add(new OptionInfoComponent("Hora fin","22:00",R.id.end_hour));
        recordingOptsInfo.add(new OptionInfoComponent("Frecuencia","15 min",R.id.freq));

        ListView recordingOpts = new ListView(getActivity());
        OptionInfoArrayAdapter adapter2 = new OptionInfoArrayAdapter(getActivity(),recordingOptsInfo);
        recordingOpts.setAdapter(adapter2);

        recordingOpts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EmoStatus app;
                app = (EmoStatus)getActivity().getApplicationContext();
                Intent option;

                switch (view.getId()){
                    case R.id.days_week:
                        break;
                    case R.id.init_hour:
                        showTimePickerDialog(R.id.init_hour);
                        break;
                    case R.id.end_hour:
                        showTimePickerDialog(R.id.end_hour);
                        break;
                    case R.id.freq:break;
                    default: break;
                }
            }
        });


        ll.addView(recordingOpts);

    }

    
    private void showTimePickerDialog(final int id) {
        View v = (View)getActivity().findViewById(id);
        final TextView info = (TextView)v.findViewById(R.id.info);
        TextView title = (TextView)v.findViewById(R.id.title);

        TimePickerDialog.OnTimeSetListener timeL = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String hourS, minS;
                hourS = hour < 10 ? ("0" + hour) : ("" + hour);
                minS = min < 10 ? ("0" + min) : ("" + min);
                info.setText(hourS+":"+minS);
            }
        };

        String time = (String) info.getText();
        String[] data = time.split(":");
        int hour = Integer.parseInt(data[0]);
        int min = Integer.parseInt(data[1]);
        TimePickerDialog tmpd = new TimePickerDialog(getActivity(), timeL, hour, min, true);
        tmpd.setMessage("Fijar "+title.getText());
        tmpd.show();
    }
}