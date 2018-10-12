package com.tsh.activitystarter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by TSH on 2018/4/29.
 */
public class ActivityStarter {
	private static final String INTERNAL_FRAGMENT_TAG = "internal_fragment_tag";
	static final int INTERNAL_REQUEST_CODE = 9527;
	private static final int MIN_INTERVAL_TIME = 1000;
	private static boolean isDebug;

	private Activity activity;
	private InternalFragment internalFragment;
	private Intent intent;
	private Bundle activityOptions;
	private long minExecIntervalTime = MIN_INTERVAL_TIME;


	private ActivityStarter(Activity activity) {
		this.activity = activity;
		checkThread();
		internalFragment = getInternalFragment();
	}

	private InternalFragment getInternalFragment() {
		InternalFragment fragment = findInternalFragment();

		if (fragment == null) {
			synchronized (InternalFragment.class) {
				fragment = findInternalFragment();
				if (fragment == null) {
					fragment = new InternalFragment();
					FragmentManager fragmentManager = activity.getFragmentManager();

					fragmentManager.beginTransaction()
							.add(fragment, INTERNAL_FRAGMENT_TAG)
							.commitAllowingStateLoss();
					// commit()调用之后加上 executePendingTransactions()来保证立即执行, 即变异步为同步.
					fragmentManager.executePendingTransactions();
				}
			}
		}
		return fragment;
	}

	private InternalFragment findInternalFragment() {
		return (InternalFragment) activity.getFragmentManager().findFragmentByTag(INTERNAL_FRAGMENT_TAG);
	}

	private void checkThread() {
		if (Looper.getMainLooper() != Looper.myLooper()) {
			throw new RuntimeException("需要在主线程中执行!!!");
		}
	}

	private boolean isMatchActivity() {
		return activity.getPackageManager().resolveActivity(intent, PackageManager.GET_RESOLVED_FILTER) != null;
	}

	private void showNotMatchActivity() {
		Toast.makeText(activity, String.format("Not Match Activity\n%s", intent.toString()), Toast.LENGTH_SHORT).show();
	}


	public void startActivity() {
		if (ExecTimeCount.isFastExec(minExecIntervalTime)) {
			return;
		}

		if (!isMatchActivity()) {
			if (isDebug) {
				showNotMatchActivity();
			}
			return;
		}

		if (activityOptions != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			internalFragment.startActivity(intent, activityOptions);
		} else {
			internalFragment.startActivity(intent);
		}
	}

	public void startActivity(OnResultListener listener) {
		if (ExecTimeCount.isFastExec(minExecIntervalTime)) {
			return;
		}

		if (!isMatchActivity()) {
			if (isDebug) {
				showNotMatchActivity();
			}
			return;
		}

		internalFragment.setOnResultListener(listener);
		if (activityOptions != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			internalFragment.startActivityForResult(intent, INTERNAL_REQUEST_CODE, activityOptions);
		} else {
			internalFragment.startActivityForResult(intent, INTERNAL_REQUEST_CODE);
		}

	}

	public static ActivityStarter with(Activity activity) {
		return new ActivityStarter(activity);
	}

	public static ActivityStarter with(Fragment fragment) {
		return new ActivityStarter(fragment.getActivity());
	}

	public static ActivityStarter with(android.support.v4.app.Fragment fragment) {
		return new ActivityStarter(fragment.getActivity());
	}

	public ActivityStarter setIntent(Intent intent) {
		this.intent = intent;
		return this;
	}

	public ActivityStarter setActivityOptions(Bundle activityOptions) {
		this.activityOptions = activityOptions;
		return this;
	}

	public ActivityStarter setMinExecIntervalTime(long minExecIntervalTime) {
		this.minExecIntervalTime = minExecIntervalTime;
		return this;
	}

	public static void enableDebug(boolean isDebug) {
		ActivityStarter.isDebug = isDebug;
	}
}
