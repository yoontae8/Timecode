package com.example.timecode;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.timecode.R;


public class Loading extends Activity{

	ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading_page);
		
		img = (ImageView)findViewById(R.id.loading_image);
		
		img.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent= new Intent(getApplicationContext(), Intro.class);
				startActivity(intent);
				finish();
				
			}
			
		});
		

		
	}
}
