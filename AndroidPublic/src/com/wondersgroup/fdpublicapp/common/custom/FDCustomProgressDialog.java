package com.wondersgroup.fdpublicapp.common.custom;

import java.util.Timer;
import com.wondersgroup.fdpublicapp.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  通用加载进度控件
 * @author chengshaohua
 *
 */
public class FDCustomProgressDialog extends Dialog {
	private Context context = null;
	private static FDCustomProgressDialog customProgressDialog = null;
	private long starTime = 0;
	private Timer progressTimer;
	private static final int FD_PROGRESS_DEFAULT_TIME_OUT = 10;	   // 默认超时时间为5秒
	private static final int FD_PROGRESS_FETCH_TIME_OUT = 0x0092;
	
	public FDCustomProgressDialog(Context context){
		super(context);
		this.context = context;
	}
	
	public FDCustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
	
//	private Handler pgsHandler = new Handler() {
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case FD_PROGRESS_FETCH_TIME_OUT:
//				int timeout = (Integer) msg.obj;
//				if (System.currentTimeMillis() - starTime > timeout * 1000) {
//					dismissDialog();
//					FDViewUtil.showToast(context, "加载超时..", true);
//				}
//				break;
//			}
//			super.handleMessage(msg);
//		}
//	};
	
	public static FDCustomProgressDialog createDialog(Context context){
		if(context==null) return null;
		
		customProgressDialog = new FDCustomProgressDialog(context,R.style.FDCustomProgressDialog);
		customProgressDialog.setContentView(R.layout.fd_common_progress_dialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		
		return customProgressDialog;
	}
 
    public void onWindowFocusChanged(boolean hasFocus){
    	if (customProgressDialog == null) return;
    	
        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.fd_progress_loading_imageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
 
    /**
     *  设置加载提示
     * @param strTitle
     * @return
     */
    public FDCustomProgressDialog setTitile(String strTitle){
    	return customProgressDialog;
    }
    
    /**
     *  设置加载消息
     * @param strMessage
     * @return
     */
    public FDCustomProgressDialog setMessage(String strMessage){
    	TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.fd_progress_loading_msg);
    	
    	if (tvMsg != null){
    		tvMsg.setText(strMessage);
    	}
    	
    	return customProgressDialog;
    }
    
    public void showProgressDialog() {
    	
    }
    
//    public void dismissDialog() {
//		if (progressTimer != null) {
//			progressTimer.cancel();
//		}
//		if (isShowing()) {
//			dismiss();
//		}
//	}
}
