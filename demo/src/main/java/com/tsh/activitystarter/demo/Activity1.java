package com.tsh.activitystarter.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tsh.activitystarter.ActivityStarter;

public class Activity1 extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1);
	}


	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("msg", "i'm 9527 [activity 1 result msg]");
		setResult(0, intent);
		super.onBackPressed();
	}


	public void back(View view) {
		onBackPressed();
	}



}
