package com.thesis.emostatus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vito on 25-11-13.
 */
public class InformationActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> val = new ArrayList<String>();
        val.add("Guía Rápida EmoStatus");
        val.add("Información sobre monitoreo");
        val.add("Información sobre alertas");

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, val));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position){
            case 0:
                Intent tuto = new Intent(this,TutorialActivity.class);
                tuto.putExtra("action_back",true);
                this.startActivity(tuto);
                break;
            case 1:
                Intent monitorInfo = new Intent(this,MonitorInfoActivity.class);
                this.startActivity(monitorInfo);
                break;
            case 2:
                Intent alertsInfo = new Intent(this,AlertsInfoActivity.class);
                this.startActivity(alertsInfo);
                break;
        }
    }
}
