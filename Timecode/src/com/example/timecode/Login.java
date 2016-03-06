package com.example.timecode;

import org.json.JSONObject;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends Activity {

	//declare ����
	
	ImageView Loginbar;
	EditText Id;
	EditText Passwd;
	Button GoLogin;
	Toast t1;
	Toast t2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Log.e("activity", "Login.java");
		
		//xml�� ����
		Loginbar = (ImageView)findViewById(R.id.imageView1);
		Id = (EditText)findViewById(R.id.Id);
		Passwd = (EditText)findViewById(R.id.passwd);
		GoLogin = (Button)findViewById(R.id.GoLogin);
		
		//Ű���� �����ֱ�
		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(Id, InputMethodManager.SHOW_IMPLICIT);
		mgr.showSoftInput(Passwd, InputMethodManager.SHOW_IMPLICIT);
		
		// �α��� �� �޾Ƽ� �����ϰ� �ϱ�. �α����� ������ string���� �� �� �� ����
		
		t1 = Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT);
		t2 = Toast.makeText(this, "Login Fail - Try Again", Toast.LENGTH_SHORT);
		GoLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final String id = Id.getText().toString();
				final String password = Passwd.getText().toString();
				Log.d("Button","Login");

				new Thread(new Runnable() {
					JSONObject json = null;

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String selectQuery = "SELECT+" + GlobalVariable.Id_mbTAG + 
								"+from+" + GlobalVariable.MEMBER_TABLE + "+WHERE+(" + GlobalVariable.Id_mbTAG + "='" + id + "')"
								+ "+and+(" + GlobalVariable.PASSWD_mbTAG + "='" + password + "')";
						String url = GlobalVariable.QUERY_LOGIN_PHP+"?query=" + selectQuery;
						Log.i("url", url);
						json = JSONParser.getJSONFromUrl(url);

						if(JSONParser.getResultCode() == 1)
						{	
							Log.i("login", "login success");
							//ID ����. �α���!
							//�ٸ� activivty�� �Ѿ.
							t1.show();
							UserInfo.ID = id;
							Intent intent = new Intent(Login.this, Mainmenu.class);
							startActivity(intent);
							Intro.intro.finish();
							finish();
						}			
						else
						{	
							Log.i("login", "login fail");
							t2.show();
						}		
					}
				}
						).start();}


		});

	}
	
}