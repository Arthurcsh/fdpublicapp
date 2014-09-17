package com.wondersgroup.fdpublicapp.common.service;

import com.wondersgroup.fdpublicapp.common.impl.FDCallbackListener;

/**
 * 提供一个回调接口的默认适配器,
 * new CallbackListener 的地方可以改为new CallbackListenerAdapter,
 * 只需要覆盖实现自已关心的那些方法
 *
 */
public class FDCallbackListenerAdapter implements FDCallbackListener {
	public void onStart() {
	}

	public void onLoading() {
	}

	public void onFailure(Throwable failure) {
	}

	public void onSuccess(String result) {
	}
}