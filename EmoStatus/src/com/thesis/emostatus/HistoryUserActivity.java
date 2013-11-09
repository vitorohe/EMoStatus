package com.thesis.emostatus;

import android.graphics.Color;
import android.graphics.Typeface;
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

import static android.widget.LinearLayout.LayoutParams;

public class HistoryUserActivity extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView sv = new ScrollView(getActivity());
        sv.setFillViewport(true);
        sv.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        //sv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        //sv.setVerticalScrollBarEnabled(true);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        //ll.setVerticalScrollBarEnabled(true);
        //ll.setScrollContainer(true);
        //ll.setScrollbarFadingEnabled(true);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
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
            day_date.setPadding(15,10,0,10);
            day_date.setTypeface(null, Typeface.BOLD);
            day_date.setBackgroundColor(Color.LTGRAY);

            ListView day = new ListView(getActivity());
            day.addHeaderView(day_date);
            day.setHeaderDividersEnabled(true);
            ThreeCompArrayAdapter adapter = new ThreeCompArrayAdapter(getActivity(),hist.getHistory());
            day.setAdapter(adapter);
            //day.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            day.setEnabled(false);
            day_date.setVerticalScrollBarEnabled(false);
            ll.addView(day);
        }
    }
}