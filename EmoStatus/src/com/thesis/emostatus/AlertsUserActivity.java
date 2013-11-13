package com.thesis.emostatus;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;
import persistance.OptionComponent;

public class AlertsUserActivity extends Fragment {
        LayoutInflater inflaterA;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflaterA = inflater;
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

        List<OptionComponent> opts1 = new ArrayList<OptionComponent>();
        List<OptionComponent> opts2 = new ArrayList<OptionComponent>();
        List<OptionComponent> opts3 = new ArrayList<OptionComponent>();
        opts1.add(new OptionComponent("SMS",R.drawable.icon_sms,false));
        opts2.add(new OptionComponent("Correo electrónico",R.drawable.icon_email,false));
        opts3.add(new OptionComponent("EmoStatus notificación",R.drawable.icon_emonot,false));

        ListView options1 = new ListView(getActivity());
        OptionArrayAdapter adapter1 = new OptionArrayAdapter(getActivity(),opts1);
        options1.setAdapter(adapter1);
        ll.addView(options1);

        //View email = inflaterA.inflate(R.layout.alert_email,ll);
        //ll.addView(icon_email);

        ListView options2 = new ListView(getActivity());
        OptionArrayAdapter adapter2 = new OptionArrayAdapter(getActivity(),opts2);
        options2.setAdapter(adapter2);
        ll.addView(options2);

        //View phone = inflaterA.inflate(R.layout.alert_phone,ll);
        //ll.addView(phone);

        ListView options3 = new ListView(getActivity());
        OptionArrayAdapter adapter3 = new OptionArrayAdapter(getActivity(),opts3);
        options3.setAdapter(adapter3);
        ll.addView(options3);
    }
}