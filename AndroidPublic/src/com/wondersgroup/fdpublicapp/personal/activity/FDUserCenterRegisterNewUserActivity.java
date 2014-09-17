package com.wondersgroup.fdpublicapp.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDClearEditText;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 *  用户注册
 * @author mengke
 *
 */
public class FDUserCenterRegisterNewUserActivity extends FDBaseActivity {
	
	private LinearLayout backMainLayout;
	private RelativeLayout registerLayout;
	private FDClearEditText loginEmailTextView;
	private FDClearEditText loginMobileTextView;
	private FDClearEditText userNameTextView;
	private FDClearEditText pwTextView;
	private FDClearEditText againPwTextView;
	private TextView registerBtnTextView;
	private CheckBox agreeTermsCheckBox;
	private TextView termsTextView;
	private FDUser registerUser;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_register);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("fd.login.user")) {
			registerUser = bundle.getParcelable("fd.login.user");
		}
		initUserRegisterView();
	}
	
	private void initUserRegisterView() {
		//获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_register_back);
		registerLayout = (RelativeLayout) findViewById(R.id.llo_fd_usercenter_login_btn);
		termsTextView = (TextView) findViewById(R.id.tv_fd_usercenter_register_terms);
		loginEmailTextView = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_login_email);
		loginMobileTextView = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_login_mobile);
		userNameTextView = (FDClearEditText) findViewById(R.id.et_fd_usercenter_register_nickName);
		pwTextView = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_register_pw);
		againPwTextView = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_register_pw_confirm);
		registerBtnTextView = (TextView) findViewById(R.id.tv_fd_usercenter_register_terms);
		
		loginEmailTextView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String email = loginEmailTextView.getText().toString().trim();
				verifyTextViewError(loginEmailTextView,"",StringUtils.isEmail(email));
			}
		});
		loginMobileTextView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String phone = loginMobileTextView.getText().toString().trim();
				verifyTextViewError(loginMobileTextView,"",StringUtils.isPhone(phone));
			}
		});
		userNameTextView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String username = userNameTextView.getText().toString().trim();
				verifyTextViewError(userNameTextView,"",StringUtils.isPassword(username));
			}
		});
		pwTextView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String password = pwTextView.getText().toString().trim();
				verifyTextViewError(pwTextView,"",StringUtils.isPassword(password));
			}
		});
		againPwTextView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String password = againPwTextView.getText().toString().trim();
				verifyTextViewError(againPwTextView,"",StringUtils.isPassword(password));
			}
		});

		// 给控件绑定事件
		backMainLayout.setOnClickListener(backOnClickListener);
		registerLayout.setOnClickListener(registerOnClickListener);
		registerBtnTextView.setOnClickListener(registerProtocolOnClickListener);

		// 给文字加下划线
		SpannableString termsContent = new SpannableString(termsTextView.getText());
		termsContent.setSpan(new UnderlineSpan(), 0, termsContent.length(), 0);
		termsTextView.setText(termsContent);
	}

	//注册新用户
	private OnClickListener registerOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			registerUserCenter();
		}
	};
	private OnClickListener registerProtocolOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(FDUserCenterRegisterNewUserActivity.this,FDUserCenterProtocolActivity.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			onBackPressed();
		}
	};
	
	private void verifyTextViewError(TextView textView, String msg, boolean verify) {
		if(textView==null) return;
		RelativeLayout layoutView = (RelativeLayout) textView.getParent();
		ImageView verifyImageView = (ImageView) layoutView.findViewById(R.id.imgv_fd_usercenter_register_verify);
		if(verify) {
			verifyImageView.setImageResource(R.drawable.fd_usercenter_register_valid_btn);
		}else {
			verifyImageView.setImageResource(R.drawable.fd_usercenter_register_valid_fail);
		}
	}
	
	/**
	 *  注册用户
	 */
	private void registerUserCenter() {
		String loginEmail = loginEmailTextView.getText().toString().trim();
		String loginMobile = loginMobileTextView.getText().toString().trim();
		String userName = userNameTextView.getText().toString().trim();
		String password = pwTextView.getText().toString().trim();
		String anginWord = againPwTextView.getText().toString().trim();
		if(FDViewUtil.verifyTextview(loginEmailTextView, "用户邮箱") &&
				FDViewUtil.verifyTextview(loginMobileTextView, "手机号") &&
				FDViewUtil.verifyTextview(userNameTextView, "用户名") &&
				FDViewUtil.verifyTextview(pwTextView, "用户密码") &&
				FDViewUtil.verifyTextview(againPwTextView, "用户密码")) {
			if(!password.equals(anginWord)) {
				FDViewUtil.showToast(this, "两次输入密码不一致！");
				return;
			}
			if(registerUser==null) {
				registerUser = new FDUser();
			}
			registerUser.setUsername(userName);
			registerUser.setEmail(loginEmail);
			registerUser.setMobilePhone(loginMobile);
			registerUser.setPassword(password);
			FDPersonalWrapper personalWrapper = new FDPersonalWrapper(FDUserCenterRegisterNewUserActivity.this, true);
			personalWrapper.getPersonalRegister(registerUser, new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) {
						FDViewUtil.showToast(FDUserCenterRegisterNewUserActivity.this, "用户注册失败");
						return;
					}
					FDResultParser resultParser = (FDResultParser) callback;
					if(resultParser.getBaseResult().getStatus()==0) {
						try {
							FDUser user = resultParser.getResultData(FDUser.class);
						} catch (FDParseException e) {
							e.printStackTrace();
						}
						FDUserCenterRegisterNewUserActivity.this.finish();
					}else {
						FDViewUtil.showToast(FDUserCenterRegisterNewUserActivity.this, resultParser.getBaseResult().getMessage());
					}
				}
			});
		}
	}
	
}
