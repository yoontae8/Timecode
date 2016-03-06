package com.example.timecode;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Intro extends Activity {
	
	public static Activity intro;
	Button btnLoginInterface;
	Button btnRegInterface;
	ImageView introImage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		Log.i("activity", "Intro.java");
		
		intro = Intro.this;
		
		//xml�̶� ����
		btnLoginInterface = (Button)findViewById(R.id.interlogin);
		btnRegInterface = (Button)findViewById(R.id.interreg);
		introImage = (ImageView)findViewById(R.id.introimage);
		
		//intent ó�� 
		btnLoginInterface.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intro.this, Login.class);
				startActivity(i);
				
			}
		});
		
		
		btnRegInterface.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
			
			Intent i = new Intent(Intro.this, Register.class);
			startActivity(i);
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

}
