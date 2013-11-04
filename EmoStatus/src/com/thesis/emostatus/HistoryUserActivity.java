package com.thesis.emostatus;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import persistance.EmoStatus;
import persistance.HistoryDay;

public class HistoryUserActivity extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView sv = new ScrollView(getActivity());
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        addDaysToListLayout(ll);
        return sv;
    }

    private void addDaysToListLayout(LinearLayout ll) {
        EmoStatus app = (EmoStatus)getActivity().getApplicationContext();
        List<HistoryDay> history = app.getHistoryActualUserMonitorized();

        for(HistoryDay hist : history){
            TextView day_date = new TextView(getActivity());
            day_date.setText(hist.getDay_date());
            ll.addView(day_date);

            ListView day = new ListView(getActivity());
            UserArrayAdapter adapter = new UserArrayAdapter(getActivity(),hist.getHistory());
            day.setAdapter(adapter);
            ll.addView(day);
        }
    }
}