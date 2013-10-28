package com.thesis.emostatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

	public void onClick(View arg0) {

		switch(arg0.getId()){
			case R.id.login:
			Intent login = new Intent(this,UserListActivity.class);
//				start_game.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(login);
			finish();
		}

	}
    
}
