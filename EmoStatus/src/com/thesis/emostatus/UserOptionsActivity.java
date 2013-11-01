package com.thesis.emostatus;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentActivity;
import persistance.EmoStatus;

public class UserOptionsActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private int user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_options);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("history").setIndicator("Historial"),HistoryUserActivity.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("monitoring").setIndicator("Monitoreo"),MonitorUserActivity.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("alerts").setIndicator("Alertas"),AlertsUserActivity.class,null);

        EmoStatus app = (EmoStatus)getApplicationContext();
        user = app.getUserMonitorized();

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(""+user);
    }
}