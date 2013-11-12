package com.thesis.emostatus;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.zip.CheckedInputStream;

import persistance.EmoStatus;
import persistance.OptionComponent;
import persistance.OptionInfoComponent;

public class MonitorUserActivity extends Fragment {

    private boolean[] days_marked;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        days_marked = new boolean[7];

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
                        showDaysDialog();
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
        View v = getActivity().findViewById(id);
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

    public void showDaysDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Elegir días de la semana");
        View view = getActivity().getLayoutInflater().inflate(R.layout.days_choser,alertDialog.getListView());
        setDaysViewListeners(view);
        alertDialog.setView(view);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //here you can add functions

            }
        });
        //alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();

    }

    private void setDaysViewListeners(View view) {
        final List<ToggleButton> days = new ArrayList<ToggleButton>();
        ToggleButton lu =  (ToggleButton)view.findViewById(R.id.toggleButton);
        days.add(lu);
        lu.setChecked(days_marked[0]);
        lu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[0] = true;
                } else {
                    days_marked[0] = false;
                }
            }
        });
        ToggleButton ma = (ToggleButton)view.findViewById(R.id.toggleButton1);
        days.add(ma);
        ma.setChecked(days_marked[1]);
        ma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[1] = true;
                } else {
                    days_marked[1] = false;
                }
            }
        });

        ToggleButton mi = (ToggleButton)view.findViewById(R.id.toggleButton2);
        days.add(mi);
        mi.setChecked(days_marked[2]);
        mi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[2] = true;
                } else {
                    days_marked[2] = false;
                }
            }
        });

        ToggleButton ju = (ToggleButton)view.findViewById(R.id.toggleButton3);
        days.add(ju);
        ju.setChecked(days_marked[3]);
        ju.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[3] = true;
                } else {
                    days_marked[3] = false;
                }
            }
        });

        ToggleButton vi = (ToggleButton)view.findViewById(R.id.toggleButton4);
        days.add(vi);
        vi.setChecked(days_marked[4]);
        vi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[4] = true;
                } else {
                    days_marked[4] = false;
                }
            }
        });

        ToggleButton sa = (ToggleButton)view.findViewById(R.id.toggleButton5);
        days.add(sa);
        sa.setChecked(days_marked[5]);
        sa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[5] = true;
                } else {
                    days_marked[5] = false;
                }
            }
        });

        ToggleButton dom = (ToggleButton)view.findViewById(R.id.toggleButton6);
        days.add(dom);
        dom.setChecked(days_marked[6]);
        dom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    days_marked[6] = true;
                } else {
                    days_marked[6] = false;
                }
            }
        });

        CheckBox all = (CheckBox)view.findViewById(R.id.allDays);
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                for(int i = 0; i < 7; i++){
                    days_marked[i] = checked;
                    days.get(i).setChecked(checked);
                }
            }
        });
    }
}