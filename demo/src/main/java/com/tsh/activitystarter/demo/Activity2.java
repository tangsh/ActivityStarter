package com.tsh.activitystarter.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tsh.activitystarter.ActivityStarter;

public class Activity2 extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_2);
	}


	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("msg", "i'm 9527 [activity 2 result msg]");
		setResult(0, intent);
		super.onBackPressed();
	}


	public void back(View view) {
		onBackPressed();
	}


	public void openNonExistActivity(View view) {
		Intent intent = new Intent();

		intent.setClassName(this, "com.tsh.activitystarter.demo.Activity444");

		ActivityStarter.with(this).setIntent(intent).startActivity();
	}
}
