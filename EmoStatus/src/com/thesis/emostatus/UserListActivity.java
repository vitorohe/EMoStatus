package com.thesis.emostatus;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import adapters.ThreeCompArrayAdapter;
import persistance.EmoStatus;


public class UserListActivity extends ListActivity {

    private Menu menu;
    private AlertDialog alertDialog;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        EmoStatus app = (EmoStatus)getApplicationContext();
        ThreeCompArrayAdapter adapter = new ThreeCompArrayAdapter(this,app.getUsersMonitorized());
        setListAdapter(adapter);
	}

    @Override public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                MenuItem item = menu.findItem(R.id.overflow);
                item.expandActionView();
                return true;
        }
        return super.onKeyDown(keycode, e); }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.more_options, menu);
        menu.findItem(R.id.calendar).setVisible(false);
        this.menu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        return  true;
	}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        EmoStatus app;
        app = (EmoStatus)getApplicationContext();
        app.setActualUserMonitorized(position);
        Intent options;
        options = new Intent(this,UserOptionsActivity.class);
        this.startActivity(options);

    }

}
