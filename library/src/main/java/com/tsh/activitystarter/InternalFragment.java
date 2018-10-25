package com.tsh.activitystarter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

class InternalFragment extends Fragment {

	private OnResultListener onResultListener;

	void setOnResultListener(OnResultListener listener) {
		this.onResultListener = listener;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 默认情况下，但配置发生变化时，Fragment会随着它们的宿主Activity被创建和销毁。
		// 调用Fragment#setRetaininstance(true)允许我们跳过销毁和重新创建的周期。
		// 指示系统保留当前的fragment实例，即使是在Activity被创新创建的时候。
		setRetainInstance(true);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (ActivityStarter.INTERNAL_REQUEST_CODE == requestCode && this.onResultListener != null
				&& (getActivity() != null && !getActivity().isFinishing())) {
			this.onResultListener.onActivityResult(resultCode, data);
		}
	}

}