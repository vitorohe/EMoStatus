package com.thesis.emostatus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import persistance.EmoStatus;
import persistance.ThreeDataComponent;

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
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }

    }

    private void getDataFromAccount() {
        EmoStatus app = (EmoStatus)getApplicationContext();
        List<ThreeDataComponent> usersList = new ArrayList<ThreeDataComponent>();
        usersList.add(new ThreeDataComponent("Tío Pepe","Triste (90%)",R.drawable.user_tio_pepe));
        usersList.add(new ThreeDataComponent("Abuela Lorena","Triste (75%)",R.drawable.user_abuela_lorena));
        usersList.add(new ThreeDataComponent("Tío Jorge","Normal",R.drawable.user_tio_jorge));
        app.setUsersMonitorized(usersList);
    }

}
