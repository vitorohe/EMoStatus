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

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;
import persistance.OptionComponent;

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
    }
}