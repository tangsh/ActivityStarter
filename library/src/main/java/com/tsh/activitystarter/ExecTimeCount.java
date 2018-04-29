package com.tsh.activitystarter;

/**
 * Created by TSH on 2018/4/29.
 */

class ExecTimeCount {
	private ExecTimeCount() {
	}

	private static long lastStartTime;

	static boolean isFastExec(long minExecIntervalTime) {
		if (System.currentTimeMillis() - lastStartTime <= minExecIntervalTime) {
			return true;
		}

		lastStartTime = System.currentTimeMillis();
		return false;
	}

}
