package com.thesis.emostatus;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

	public void onClick(View arg0) {

		switch(arg0.getId()){
			case R.id.login:
            login();
		}

	}

    private void login() {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        //if(username.equals("emostatus") && password.equals("emopass")){
        if(username.equals("") && password.equals("")){

            getDataFromAccount();

            Intent login = new Intent(this,UserListActivity.class);
            this.startActivity(login);
            finish();
        }
        else{
            //Dialog of error or Toast
        }

    }

    private void getDataFromAccount() {
        EmoStatus app = (EmoStatus)getApplicationContext();
        List<String> usersList = new ArrayList<String>();
        usersList.add("Tío Pepe");
        usersList.add("Abuela Lorena");
        usersList.add("Tío Jorge");
        app.setUsersMonitorized(usersList);
    }

}
