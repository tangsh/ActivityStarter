package com.tsh.activitystarter.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tsh.activitystarter.ActivityStarter;
import com.tsh.activitystarter.OnResultListener;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActivityStarter.enableDebug(true);
	}

	public void openActivity1(View view) {
		Intent intent = new Intent(this, Activity1.class);

		ActivityStarter.with(this).setIntent(intent).startActivity();

	}
	public void openActivity2(View view) {
		Intent intent = new Intent(this, Activity2.class);

		ActivityStarter.with(this).setIntent(intent).startActivity();
	}

	public void openActivity1ForResult(View view) {
		Intent intent = new Intent(this, Activity1.class);
		Bundle bundle = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getLeft(),
				view.getTop(), view.getWidth(), view.getHeight()).toBundle();

		ActivityStarter.with(this)
				.setIntent(intent)
				.setActivityOptions(bundle)
				.startActivity(new OnResultListener() {
					@Override
					public void onActivityResult(int resultCode, Intent data) {
						showData(data);
					}
				});
	}


	public void openActivity2ForResult(View view) {
		Intent intent = new Intent(this, Activity2.class);
		Bundle bundle = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getLeft(),
				view.getTop(), view.getWidth(), view.getHeight()).toBundle();

		ActivityStarter.with(this)
				.setIntent(intent)
				.setActivityOptions(bundle)
				.startActivity(new OnResultListener() {
					@Override
					public void onActivityResult(int resultCode, Intent data) {
						showData(data);
					}
				});
	}

	public void openNonExistActivity(View view) {
		Intent intent = new Intent();

		intent.setClassName(this, "com.tsh.activitystarter.demo.Activity333");

		ActivityStarter.with(this)
				.setIntent(intent)
				.startActivity(new OnResultListener() {
					@Override
					public void onActivityResult(int resultCode, Intent data) {
						showData(data);
					}
				});
	}

	private void showData(Intent data) {
		if (data != null && data.hasExtra("msg")) {
			Toast.makeText(getApplication(), data.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
		}
	}


	public void fastOpenActivity(final View view) {
		findViewById(R.id.button1).performClick();
		// 模拟快速点击
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				findViewById(R.id.button1).performClick();
			}
		}, 200L);
	}


	public void openQq(View view) {
		Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
		ActivityStarter.with(this).setIntent(intent).startActivity();
	}

}
