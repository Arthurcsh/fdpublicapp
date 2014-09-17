package com.wondersgroup.fdpublicapp.common.custom;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  视图通用控件
 * @author chengshaohua
 *
 */
public class FDViewUtil {
	private static ImageLoader imageLoader = ImageLoader.getInstance();
	private static DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(false)
			.showImageForEmptyUri(R.drawable.fd_gv_pic_show_failed_default)
			.showImageOnFail(R.drawable.fd_gv_pic_show_failed_default)
			.showStubImage(R.drawable.fd_gv_pic_show_failed_default).build();
	
	/**
	 * 显示指定进度窗口
	 * 
	 * @param context
	 * @param pd
	 * @param title
	 */
	public static void showProgressDialog(Context context,final FDCustomProgressDialog pd, final String title) {
		pd.setMessage(title);                  // 设置ProgressDialog标题
		pd.setCanceledOnTouchOutside(false);   // 按对话框以外的地方不起作用
		pd.show();
	}

	/**
	 * 关闭指定进度窗口
	 * 
	 * @param pd
	 */
	public static void dismissProgressDialog(FDCustomProgressDialog pd) {
		if (pd != null) {
			if (pd.isShowing()) {
				pd.dismiss();
			}
		}
	}

	/**
	 * 在页面中显示一个信息，几秒后自动隐去
	 * 
	 * @param msg
	 * @param context
	 */
	public static void showToast(Context context, String text) {
		if (context == null) return;
		
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, String text, boolean isLong) {
		if (context == null) return;
		if(isLong) {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		}else {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void showToast(Context context, View view, String title) {
		Toast toast = Toast.makeText(context, title, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		toastView.setBackgroundDrawable(new ColorDrawable(0xb0000000));
		toastView.addView(view, 0);
		toast.show();
	}
	
	/**
	 * 开关提示
	 * @param context
	 * @param imageView
	 * @param status       0=取消，1=成功
	 * @param title
	 */
	public static void showTaggleToast(Context context, int status, String title) {
		ImageView collectView = new ImageView(context);
		collectView.setImageResource(R.drawable.fd_restaurant_favorites);
		if(status==0) {
			FDViewUtil.showToast(context, collectView, "已经"+title);
		}else {
			FDViewUtil.showToast(context, collectView, "取消"+title);
		}
	}
	
	/**
	 * 弹出一个只带确认按钮的对话框，用来显示指定的消息
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showAlertDialog(Context context, String msg) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}


	/**
	 * 确认提示
	 * @param context
	 * @param message     消息内容
	 * @param positive    确定
	 * @param negative    取消
	 */
	public static void showAlertDialog(Context context, String message, DialogInterface.OnClickListener positive, 
			DialogInterface.OnClickListener negative) {
		showAlertDialog(context,null,null,message,positive,negative);
	}
	
	/**
	 * 确认提示
	 * @param context  同下
	 * @param title
	 * @param message
	 * @param positive
	 * @param negative
	 */
	public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener positive, 
			DialogInterface.OnClickListener negative) {
		showAlertDialog(context,null,title,message,positive,negative);
	}
	
	/**
	 * 
	 *  确认提示            注意：icon需要与title一起设置才能生效
	 *  
	 * @param context    上下文
	 * @param icon        图片
	 * @param title       提示
	 * @param message     消息
	 * @param positive    确定
	 * @param negative    取消
	 */
	public static void showAlertDialog(Context context, Drawable icon, String title, String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
		Builder builder = new AlertDialog.Builder(context);
		if(title!=null) {
			builder.setTitle(title);
		}
		if(icon!=null) {
			builder.setIcon(icon);
		}
		if(message!=null) {
			builder.setMessage(message);
		}
		builder.setPositiveButton("确定", positive);
		builder.setNegativeButton("取消", negative);
		
		Dialog alertDialog = builder.create();;
		alertDialog.show();
	}

	/**
	 * 选项控件
	 * @param choiceWidget
	 * @param title
	 * @param choices
	 * @param callback
	 */
	public static void showSpinnerSelection(final Button choiceWidget, final String title, final String[] choices, final FDCallback callback) {
		if(choices==null || callback==null) return;
		
		choiceWidget.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View view) {
				new AlertDialog.Builder(choiceWidget.getContext())
                .setTitle(title).setSingleChoiceItems(choices, 0,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	choiceWidget.setText(choices[which]);
                    	callback.onCallback(Integer.valueOf(which));
                        dialog.dismiss();
                    }
                }).create().show();
			}
		});
	}
	
	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param view
	 */
	public static void hideSoftInputIme(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	/**
	 *  延迟加载图片资源
	 * @param ctxt   Context
	 * @param view   ImageView
	 * @param uri    图片地址
	 */
	public static void showServerImage(Context ctxt, ImageView view, String uri) {
		if(view==null || uri==null) return;
		
		String serverURI = uri;
		if(!uri.startsWith("http")){
			if(uri.startsWith("/mnt/") || uri.startsWith("/sdcard/") || uri.startsWith("/storage/")) {
				view.setImageBitmap(getBitMap(uri));
				return;
			}else {
				serverURI = ServiceManager.HOSTURL_PICTURE + uri;
			}
		}
//		System.out.println("------   Server image   ------"+serverURI);
		try {
			if (!imageLoader.isInited()) {
				imageLoader.init(ImageLoaderConfiguration.createDefault(ctxt));
			}
			imageLoader.displayImage(serverURI, view, options);
		} catch (Exception e) {
			view.setImageResource(R.drawable.fd_gv_pic_show_failed_default);
			e.printStackTrace();
		}
	}
	
	/**
	 *  设置餐厅信用等级
	 * @param safetyRating
	 * @param imageView
	 */
	public static void showSafetyRating(int safetyRating, ImageView imageView) {
		if(imageView==null) return;
		if(safetyRating==21004){
			imageView.setImageResource(R.drawable.fd_business_credit_smile);
		}else if(safetyRating==21003){
			imageView.setImageResource(R.drawable.fd_business_credit_peace);
		}else if (safetyRating==21002) {
			imageView.setImageResource(R.drawable.fd_business_credit_unhappy);
		}else{
			imageView.setImageResource(R.drawable.fd_business_credit_unreview);
		}
	}
	
	/**
	 *  验证输入组件
	 * @param textView
	 * @param message
	 * @return
	 */
	public static boolean verifyTextview(TextView textView, String message) {
		if(textView==null) return false;
		String textString = textView.getText().toString().trim();
		if(StringUtils.isEmpty(textString)) {
			showToast(textView.getContext(), message+"不能为空，请重新输入..");
			return false;
		}
		return true;
	}
	
	public static Bitmap getBitMap(String imagePath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		return BitmapFactory.decodeFile(imagePath, opts);
	}
	
	/**
	 * 转换绝对地址
	 * @param uri
	 * @return
	 */
	public static String getAbsoluteImageUrl(String uri) {
		if(uri==null) return null;
		String serverURI = uri;
		if(!uri.startsWith("http")){
			if(!uri.startsWith("/mnt/") && !uri.startsWith("/sdcard/") && !uri.startsWith("/storage/")) {
				serverURI = ServiceManager.HOSTURL_PICTURE + uri;
			}
		}
		return serverURI;
	}
}
