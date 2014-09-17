package com.wondersgroup.fdpublicapp.widget;

import java.util.regex.Pattern;
import com.wondersgroup.fdpublicapp.common.mode.FDNotice;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.home.main.activity.FDMainActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterLoginActivity;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchResultActivity;
import com.wondersgroup.fdpublicapp.widget.FDPathChooseDialog.ChooseCompleteListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.webkit.WebView;

/**
 * 应用程序UI工具包：封装UI相关的一些操作
 * @author chengshaohua
 *
 */
public class FDUIHelper {

	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;

	public final static int LISTVIEW_DATATYPE_NEWS = 0x01;
	public final static int LISTVIEW_DATATYPE_BLOG = 0x02;
	public final static int LISTVIEW_DATATYPE_POST = 0x03;
	public final static int LISTVIEW_DATATYPE_TWEET = 0x04;
	public final static int LISTVIEW_DATATYPE_ACTIVE = 0x05;
	public final static int LISTVIEW_DATATYPE_MESSAGE = 0x06;
	public final static int LISTVIEW_DATATYPE_COMMENT = 0x07;

	public final static int REQUEST_CODE_FOR_RESULT = 0x01;
	public final static int REQUEST_CODE_FOR_REPLY = 0x02;

	/** 表情图片匹配 */
	private static Pattern facePattern = Pattern
			.compile("\\[{1}([0-9]\\d*)\\]{1}");

	/** 全局web样式 */
	public final static String WEB_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:310px;} "
			+ "img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#fff;padding:2px;} "
			+ "pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;} "
			+ "a.tag {font-size:15px;text-decoration:none;background-color:#bbd6f3;border-bottom:2px solid #3E6D8E;border-right:2px solid #7F9FB6;color:#284a7b;margin:2px 2px 2px 0;padding:2px 4px;white-space:nowrap;}</style>";

	/**
	 * 显示首页
	 * 
	 * @param activity
	 */
	public static void showHome(Activity activity) {
		Intent intent = new Intent(activity, FDMainActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 显示登录页面
	 * 
	 * @param activity
	 */
	public static void showLoginDialog(Context context) {
		Intent intent = new Intent(context, FDUserCenterLoginActivity.class);
//		if (context instanceof Main)
//			intent.putExtra("LOGINTYPE", FDUserCenterLoginActivity.LOGIN_MAIN);
//		else if (context instanceof Setting)
//			intent.putExtra("LOGINTYPE", LoginDialog.LOGIN_SETTING);
//		else
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 无参数切换到其他界面
	 * @param <E>
	 * @param context
	 * @param activity   待切换到的Activity
	 */
	public static <E> void sendDirectActivity(Context context, Class<E> clazz) {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}

	/**
	 * 显示软件详情
	 * 
	 * @param context
	 * @param ident
	 */
	public static void showSoftwareDetail(Context context, String ident) {
//		Intent intent = new Intent(context, SoftwareDetail.class);
//		intent.putExtra("ident", ident);
//		context.startActivity(intent);
	}


	/**
	 * 调用系统安装了的应用分享
	 * 
	 * @param context
	 * @param title
	 * @param url
	 */
	public static void showShareMore(Activity context, final String title,
			final String url) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
		intent.putExtra(Intent.EXTRA_TEXT, title + " " + url);
		context.startActivity(Intent.createChooser(intent, "选择分享"));
	}

	/**
	 * 分享到'新浪微博'或'腾讯微博'的对话框
	 * 
	 * @param context
	 *            当前Activity
	 * @param title
	 *            分享的标题
	 * @param url
	 *            分享的链接
	 */
	public static void showShareDialog(final Activity context,
			final String title, final String url) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setIcon(android.R.drawable.btn_star);
//		builder.setTitle(context.getString(R.string.share));
//		builder.setItems(R.array.app_share_items,
//				new DialogInterface.OnClickListener() {
//					AppConfig cfgHelper = AppConfig.getAppConfig(context);
//					AccessInfo access = cfgHelper.getAccessInfo();
//
//					public void onClick(DialogInterface arg0, int arg1) {
//						switch (arg1) {
//						case 0:// 新浪微博
//								// 分享的内容
//							final String shareMessage = title + " " + url;
//							// 初始化微博
//							if (SinaWeiboHelper.isWeiboNull()) {
//								SinaWeiboHelper.initWeibo();
//							}
//							// 判断之前是否登陆过
//							if (access != null) {
//								SinaWeiboHelper.progressDialog = new ProgressDialog(
//										context);
//								SinaWeiboHelper.progressDialog
//										.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//								SinaWeiboHelper.progressDialog
//										.setMessage(context
//												.getString(R.string.sharing));
//								SinaWeiboHelper.progressDialog
//										.setCancelable(true);
//								SinaWeiboHelper.progressDialog.show();
//								new Thread() {
//									public void run() {
//										SinaWeiboHelper.setAccessToken(
//												access.getAccessToken(),
//												access.getAccessSecret(),
//												access.getExpiresIn());
//										SinaWeiboHelper.shareMessage(context,
//												shareMessage);
//									}
//								}.start();
//							} else {
//								SinaWeiboHelper
//										.authorize(context, shareMessage);
//							}
//							break;
//						case 1:// 腾讯微博
//							QQWeiboHelper.shareToQQ(context, title, url);
//							break;
//						case 2:// 截图分享
//							addScreenShot(context, new OnScreenShotListener() {
//
//								@SuppressLint("NewApi")
//								public void onComplete(Bitmap bm) {
//									Intent intent = new Intent(context,ScreenShotShare.class);
//									intent.putExtra("title", title);
//									intent.putExtra("url", url);
//									intent.putExtra("cut_image_tmp_path",ScreenShotView.TEMP_SHARE_FILE_NAME);
//									try {
//										ImageUtils.saveImageToSD(context,ScreenShotView.TEMP_SHARE_FILE_NAME,bm, 100);
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//									context.startActivity(intent);
//								}
//							});
//							break;
//						case 3:// 更多
//							showShareMore(context, title, url);
//							break;
//						}
//					}
//				});
		builder.create().show();
	}


	/**
	 * 显示搜索界面
	 * 
	 * @param context
	 */
	public static void showSearch(Context context) {
		Intent intent = new Intent(context, FDSearchResultActivity.class);
		context.startActivity(intent);
	}


	/**
	 * 显示路径选择对话框
	 * 
	 * @param context
	 */
	public static void showFilePathDialog(Activity context, ChooseCompleteListener listener) {
		new FDPathChooseDialog(context, listener).show();
	}



	/**
	 * 打开浏览器
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			e.printStackTrace();
//			ToastMessage(context, "无法浏览此网页", 500);
		}
	}


	/**
	 * 获取TextWatcher对象
	 * 
	 * @param context
	 * @param tmlKey
	 * @return
	 */
	public static TextWatcher getTextWatcher(final Activity context,
			final String temlKey) {
		return new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 保存当前EditText正在编辑的内容
//				((AppContext) context.getApplication()).setProperty(temlKey,
//						s.toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}



	/**
	 * 发送通知广播
	 * 
	 * @param context
	 * @param notice
	 */
	public static void sendBroadCast(Context context, FDNotice notice) {
		if (!((FDAppContext) context.getApplicationContext()).isLogin()
				|| notice == null)
			return;
		Intent intent = new Intent("com.wondersgroup.fdpublicapp.action.APPWIDGET_UPDATE");
		intent.putExtra("atmeCount", notice.getAtmeCount());
		intent.putExtra("msgCount", notice.getMsgCount());
		intent.putExtra("reviewCount", notice.getReviewCount());
		intent.putExtra("newFansCount", notice.getNewFansCount());
		context.sendBroadcast(intent);
	}



	/**
	 * 组合动态的回复文本
	 * 
	 * @param name
	 * @param body
	 * @return
	 */
	public static SpannableString parseActiveReply(String name, String body) {
		SpannableString sp = new SpannableString(name + "：" + body);
		// 设置用户名字体加粗、高亮
		sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
				name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new ForegroundColorSpan(Color.parseColor("#0e5986")), 0,
				name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}



	/**
	 * 点击返回监听事件
	 * 
	 * @param activity
	 * @return
	 */
	public static View.OnClickListener finish(final Activity activity) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				activity.finish();
			}
		};
	}


	/**
	 * 用户登录或注销
	 * 
	 * @param activity
	 */
	public static void loginOrLogout(Activity activity) {
		FDAppContext ac = (FDAppContext) activity.getApplication();
		if (ac.isLogin()) {
			ac.Logout();
//			ToastMessage(activity, "已退出登录");
		} else {
			showLoginDialog(activity);
		}
	}


	/**
	 * 清除app缓存
	 * 
	 * @param activity
	 */
	public static void clearAppCache(Activity activity) {
		final FDAppContext ac = (FDAppContext) activity.getApplication();
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
//					ToastMessage(ac, "缓存清除成功");
				} else {
//					ToastMessage(ac, "缓存清除失败");
				}
			}
		};
		new Thread() {
			public void run() {
				Message msg = new Message();
				try {
					ac.clearAppCache();
					msg.what = 1;
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
				}
				handler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * 发送App异常崩溃报告
	 * 
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont, final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("异常报告");
		builder.setMessage(crashReport);
		builder.setPositiveButton("报告",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 发送异常报告
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //模拟器
						i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,new String[] { "cshlxx@163.com" });
						i.putExtra(Intent.EXTRA_SUBJECT,"食安大众Android客户端 - 错误报告");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "发送错误报告"));
						
//						AppManager.getAppManager().AppExit(cont);   // 退出
					}
				});
		builder.setNegativeButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
//						AppManager.getAppManager().AppExit(cont);   // 退出
					}
				});
		builder.show();
	}

	/**
	 * 退出程序
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
//		builder.setTitle(R.string.app_menu_surelogout);
//		builder.setPositiveButton(R.string.sure,
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//						// 退出
//						AppManager.getAppManager().AppExit(cont);
//					}
//				});
//		builder.setNegativeButton(R.string.cancle,
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//					}
//				});
		builder.show();
	}


	/**
	 * 添加网页的点击图片展示支持
	 */
	public static void addWebImageShow(final Context cxt, WebView wv) {
		wv.getSettings().setJavaScriptEnabled(true);
//		wv.addJavascriptInterface(new OnWebViewImageListener() {
//			public void onImageClick(String bigImageUrl) {
//				if (bigImageUrl != null)
//					FDUIHelper.showImageZoomDialog(cxt, bigImageUrl);
//			}
//		}, "mWebViewImageListener");
	}
}
