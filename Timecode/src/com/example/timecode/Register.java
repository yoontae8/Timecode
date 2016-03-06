package com.example.timecode;

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
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	//���� ����
	ImageView Registerbar;
	TextView StudNum;
	EditText Edit_stunum;
	TextView StuId;
	EditText Edit_stuid;
	TextView PhoneNum;
	EditText Edit_phonenum;
	TextView Passwd;
	EditText Edit_passwd;
	Button btnRegister;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regitster);
		Log.i("activity", "Register.java");
		
		//xml�� ����
		Registerbar = (ImageView) findViewById(R.id.registerbar);
		
		StudNum = (TextView) findViewById(R.id.stunum);
		Edit_stunum = (EditText) findViewById(R.id.editStunum);
		StuId = (TextView) findViewById(R.id.stuId);
		Edit_stuid = (EditText) findViewById(R.id.editStuid);
		PhoneNum = (TextView) findViewById(R.id.phonenum);
		Edit_phonenum = (EditText) findViewById(R.id.editPhoneNum);
		Passwd = (TextView) findViewById(R.id.passwd);
		Edit_passwd = (EditText) findViewById(R.id.editPasswd);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		
		final Toast t1 = Toast.makeText(this, "Register Success", 2000);
		final Toast t2 = Toast.makeText(this, "Register fail", 2000);

		//Ű���� �����ֱ�
		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(Edit_stunum, InputMethodManager.SHOW_IMPLICIT);
		mgr.showSoftInput(Edit_stuid, InputMethodManager.SHOW_IMPLICIT);
		mgr.showSoftInput(Edit_phonenum, InputMethodManager.SHOW_IMPLICIT);
		mgr.showSoftInput(Edit_passwd, InputMethodManager.SHOW_IMPLICIT);
		
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String StudentNum = Edit_stunum.getText().toString();
				final String StuId = Edit_stuid.getText().toString();
				final String Phonenumber = Edit_phonenum.getText().toString();
				final String Password = Edit_passwd.getText().toString();

				Log.d("Button","Register");	

				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String insertQuery = "INSERT+INTO+"+GlobalVariable.MEMBER_TABLE+"+("+GlobalVariable.PHONE_mbTAG
								+","+GlobalVariable.Id_mbTAG+","+GlobalVariable.PASSWD_mbTAG+","+GlobalVariable.S_ID_mbTAG+")+"
										+ "VALUES+('"+Phonenumber+"','"+StuId+"','"+Password+"','"+StudentNum+"')";

						String url = GlobalVariable.NONQUERY_PHP+"?query="+insertQuery;
						Log.i("url", url);
						JSONParser.getJSONFromUrl(url);
						int code = JSONParser.getResultCode();
						if( code == 1) {
							//register ����
							Log.i("register", "register success");
							t1.show();
							UserInfo.ID = StuId;
							Intent intent = new Intent(Register.this, Mainmenu.class);
							startActivity(intent);
							Intro.intro.finish();
							finish();
						}
						else {
							//register ����
							Log.i("register", "register fail");
							t2.show();
						}

					} }).start();

			}
		});
		
		
	}
}
