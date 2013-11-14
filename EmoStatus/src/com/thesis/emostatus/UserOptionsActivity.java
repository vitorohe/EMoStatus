package com.thesis.emostatus;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import persistance.EmoStatus;

public class UserOptionsActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_options);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("history").setIndicator("Historial"),HistoryUserActivity.class,savedInstanceState);
        mTabHost.addTab(mTabHost.newTabSpec("monitoring").setIndicator("Monitoreo"),MonitorUserActivity.class,savedInstanceState);
        mTabHost.addTab(mTabHost.newTabSpec("alerts").setIndicator("Alertas"),AlertsUserActivity.class,savedInstanceState);

        EmoStatus app = (EmoStatus)getApplicationContext();
        user = app.getActualUserMonitorized();

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(user);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        startActivity(new Intent(this,UserListActivity.class));
        return true;
    }
}