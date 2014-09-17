package com.wondersgroup.fdpublicapp.personal.activity;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.custom.pickDate.JudgeDate;
import com.wondersgroup.fdpublicapp.common.custom.pickDate.ScreenInfo;
import com.wondersgroup.fdpublicapp.common.custom.pickDate.WheelMain;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;
import com.wondersgroup.fdpublicapp.common.mode.FDBitmap;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterView;
import com.wondersgroup.fdpublicapp.widget.FDPhotoPopupWindows;

/**
 * 完善用户信息
 * @author chengshaohua
 *
 */
public class FDUserCenterCompleteActivity extends FDBaseActivity {
	private static final int FD_COMMON_TAKE_PICTURE = 0x002420;
	private View contentView;
	private LinearLayout backMainLayout;
	private TextView thumbUploadTextView;
	private TextView okBtnTextView;
	private EditText usernameTextView;
	private RadioGroup sexRadioView;
	private TextView birthdayTextView;
	private TextView birthdayButtonView;
	private EditText mobileTextView;
	private FDUser user;
	private FDPhotoPopupWindows photoPopupWindow;
	private File imageFile;
	private String takePhotoPath;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_complete_info);
		
		initResetPwView();
        setUserInformation();
	}
	
	/**
	 * 初始化用户信息视图
	 */
	public void initResetPwView() {

		// 获取控件实例
		contentView = View.inflate(context,R.layout.fd_common_photo_item_popupwindows, null);
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_complete_info_back);
		usernameTextView = (EditText) findViewById(R.id.textv_fd_usercenter_complete_info_nickname);
		sexRadioView = (RadioGroup) findViewById(R.id.rg_fd_usercenter_complete_info_sex);
		birthdayTextView = (TextView) findViewById(R.id.textv_fd_usercenter_complete_info_birthday);
		birthdayButtonView = (TextView) findViewById(R.id.button_fd_usercenter_complete_info_birthday);
		mobileTextView = (EditText) findViewById(R.id.editt_fd_usercenter_complete_info_phone);
		thumbUploadTextView = (TextView) findViewById(R.id.textv_fd_usercenter_complete_info_thumb_upload);
		okBtnTextView = (TextView) findViewById(R.id.tv_fd_usercenter_complete_info_okbtn);

		// 给控件绑定事件
		okBtnTextView.setOnClickListener(okBtnOnClickListener);
		backMainLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
		thumbUploadTextView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				uploadUserThumb();
			}
		});
		birthdayButtonView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				modifyBirthday();
			}
		});
	}

	/**
	 * 设置用户信息 
	 */
	private void setUserInformation() {
		if(appContext==null) return;
		
		this.user = (FDUser) appContext.readObject(FDAppContext.FD_USER_FILE);
		if(user==null) return;
		System.out.println("-------Nick Name ------"+ user.getNickName());
		usernameTextView.setText(user.getNickName());
		sexRadioView.check(user.getSex()==1?R.id.rb_fd_usercenter_complete_info_sex_m:R.id.rb_fd_usercenter_complete_info_sex_f);
		birthdayTextView.setText(user.getBirthday());
		mobileTextView.setText(user.getMobilePhone());
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	private FDUser getUserInformation() {
		FDUser completedUser = new FDUser();
		if(user!=null) {
			completedUser = user;
			completedUser.setUsername(appContext.getLoginUserName());
		}
		
		if(verifyTextViewError(usernameTextView,"用户昵称")
				&& verifyTextViewError(birthdayTextView,"出生日期")
				&& verifyTextViewError(mobileTextView,"联系电话")) {
			completedUser.setNickName(usernameTextView.getText().toString().trim());
			int sexCheck = sexRadioView.getCheckedRadioButtonId()==R.id.rb_fd_usercenter_complete_info_sex_m?1:0;
			completedUser.setSex(sexCheck);
			String userDate = birthdayTextView.getText().toString().trim();
			String userMobile = mobileTextView.getText().toString().trim();
			if(StringUtils.isDate(userDate)) {
				completedUser.setBirthday(userDate);
			}else {
				FDViewUtil.showToast(context, "输入出生日期格式错误.", true);
				return null;
			}
			if(StringUtils.isPhone(userMobile)) {
				completedUser.setMobilePhone(userMobile);
			}else {
				FDViewUtil.showToast(context, "输入用户手机号错误.", true);
				return null;
			}
			if(takePhotoPath!=null) {
				completedUser.setHeadImgUrl(takePhotoPath);
			}
		}else {
			return null;
		}
		return completedUser;
	}
	
	/**
	 * 完善用户信息
	 */
	public void saveCompletedUserInfo() {
		FDUser saveUser = getUserInformation();
		if(saveUser==null) return;
		
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(context);
		personalWrapper.setPersonalInformation(saveUser, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDUser userInfo = (FDUser) callback;
				appContext.saveLoginInfo(userInfo);
//				appContext.setProperty(FDUserCenterView.FD_USER_CENTER_COMPLETED, true);
				context.sendBroadcast(new Intent(FDUserCenterView.FD_USER_CENTER_LOGIN));
				finish();
			}
		});
	}
	
	// 提交 事件
	private OnClickListener okBtnOnClickListener = new OnClickListener() {
		public void onClick(View view) {
			saveCompletedUserInfo();
		}
	};
	
	/**
	 * 修改用户出生日期
	 */
	private void modifyBirthday() {
		final View timepickerview = LayoutInflater.from(context).inflate(R.layout.fd_common_timepicker, null);
		ScreenInfo screenInfo = new ScreenInfo(FDUserCenterCompleteActivity.this);
		final WheelMain wheelMain = new WheelMain(timepickerview);
		wheelMain.screenheight = screenInfo.getHeight();
		String time = birthdayTextView.getText().toString();
		Calendar calendar = Calendar.getInstance();
		if(JudgeDate.isDate(time, "yyyy-MM-dd")){
			calendar.setTime(StringUtils.toDate2(time));
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year,month,day);
		new AlertDialog.Builder(context)
		.setTitle("选择出生日期")
		.setView(timepickerview)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				birthdayTextView.setText(wheelMain.getTime());
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
	}
	
	/**
	 * 剪切用户头像
	 */
	private void uploadUserThumb() {
		photoPopupWindow = new FDPhotoPopupWindows(context, contentView,
				new FDPhotoBucketListener() {
					public void onCameraPhoto() {
						takeThumbPhoto();
						photoPopupWindow.dismiss();
					}

					public void onChoicePhotos(LinkedHashMap<String, FDImage> imgPathMap) {
						if (imgPathMap != null) {
							for (Map.Entry<String, FDImage> entry : imgPathMap.entrySet()) {
								if (entry == null) continue;
								if(entry.getValue()!=null) {
									takePhotoPath = entry.getValue().getFilePath();
									System.out.println("-------Photo------"+ imgPathMap);
								}
							}
						}
					}
				});
	}
	
	private void takeThumbPhoto() {
		String savePath = FDBitmap.savePath;
		// 判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		}

		// 没有挂载SD卡，无法保存文件
		if (StringUtils.isEmpty(savePath)) {
			FDViewUtil.showToast(context, "无法保存照片，请检查SD卡是否挂载");
			return;
		}

		imageFile = FDBitmap.getLocalTimeStampFile();
		Uri uri = Uri.fromFile(imageFile);

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		((Activity) context).startActivityForResult(intent, FD_COMMON_TAKE_PICTURE);
	}
	
	public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if(resultCode != ((Activity) context).RESULT_OK) return;
		
		if (requestCode == FD_COMMON_TAKE_PICTURE) {
			if(imageFile!=null) {
				Uri uri = Uri.fromFile(imageFile);
				takePhotoPath = uri.getPath();                       //该照片的绝对路径
//				FDImage takeImage = new FDImage();
//				takeImage.setName(imageFile.getName());
//				takeImage.setFilePath(takePhotoPath);
				System.out.println("-------Take Photo ------"+ takePhotoPath);
			}
		}
	}
	
	/**
	 * 验证字符组件
	 * @param textView
	 * @param msg
	 * @return
	 */
	private boolean verifyTextViewError(TextView textView, String msg) {
		if(textView==null) return false;
		String textString =  textView.getText().toString().trim();
		if(StringUtils.isEmpty(textString)) {
			FDViewUtil.showToast(context, msg+"不能为空.", true);
			return false;
		}
		
		return true;
	}
}
