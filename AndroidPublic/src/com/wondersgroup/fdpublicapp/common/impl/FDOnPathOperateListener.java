package com.wondersgroup.fdpublicapp.common.impl;

import android.widget.TextView;

public interface FDOnPathOperateListener {

	public final static int DEL = 0;
	public final static int RENAME = 1;

	public void onPathOperate(int type, int position, TextView pathName);
}
