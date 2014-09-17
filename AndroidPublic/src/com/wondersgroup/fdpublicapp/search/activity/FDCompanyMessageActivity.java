package com.wondersgroup.fdpublicapp.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentSchoolCommentActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterView;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 *  评价回复
 * @author chengshaohua
 *
 */
public class FDCompanyMessageActivity extends FDBaseActivity  {
	public static final String FD_COMPANY_MESSAGE_KEY       = "fd.company.message.key";
	public static final String FD_COMMENT_REPLY_BROADCAST   = "fd.comment.reply.broadcast";
	public LinearLayout backLayout;
	public LinearLayout commentCreatorLayout;
	public TextView commentHeadTextView;
	public TextView commentTitleTextView;
	public EditText commentEditView;                     // 留言内容
	public TextView leaveTextView;                       // 留言字符
	public Button submitMessageButton;                   // 提交按钮
	public FDRestaurant toCompany;                       // 点评回复.
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fd_common_note_comment);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey(FD_COMPANY_MESSAGE_KEY)) {
			toCompany = bundle.getParcelable(FD_COMPANY_MESSAGE_KEY);
		}
		initToLeaveMessageCompanyView();
	}
	
	/**
	 *  初始化给商家留言
	 */
	public void initToLeaveMessageCompanyView() {
		backLayout = (LinearLayout) this.findViewById(R.id.fd_common_comment_back_img_layout);
		commentCreatorLayout = (LinearLayout) this.findViewById(R.id.fd_common_comment_define_layout);
		commentHeadTextView = (TextView) this.findViewById(R.id.fd_common_comment_name_header_title);
		commentTitleTextView = (TextView) this.findViewById(R.id.fd_common_comment_desc_textview);
		commentEditView = (EditText) this.findViewById(R.id.fd_common_comment_content_textveiw);
		leaveTextView = (TextView) this.findViewById(R.id.fd_common_comment_leave_textview);
		submitMessageButton = (Button) this.findViewById(R.id.fd_common_comment_submit_button);
		
		commentCreatorLayout.setVisibility(View.GONE);
		commentHeadTextView.setText("商家留言");
		if(toCompany!=null) {
			commentTitleTextView.setText(toCompany.getName());
		}
		commentEditView.addTextChangedListener(new FDTextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				FDStudentSchoolCommentActivity.filterCommentEditText(commentEditView,leaveTextView);
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDCompanyMessageActivity.this.finish();
			}
		});
		submitMessageButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				submitToLeaveMessageCompany();
			}
		});
	}
	
	/**
	 *  点评回复
	 */
	public void submitToLeaveMessageCompany() {
		if(toCompany==null) return;
		String leaveMessageContent = commentEditView.getText().toString().trim();
		if(StringUtils.isEmpty(leaveMessageContent)) {
			FDViewUtil.showToast(context, "请输入商家留言消息..", true);
			return;
		}
		
		String loginUserName = appContext.getLoginUserName();
		FDMessage submitMessage = new FDMessage();
		submitMessage.setCompanyTo(toCompany.getId());
		submitMessage.setCommContent(leaveMessageContent);
		submitMessage.setFromUsername(loginUserName);
		submitMessage.setMessageType("COM_MSG");
		
		
		FDSearchWrapper searchWrapper = new FDSearchWrapper(this);
		searchWrapper.setCreateLeaveMessageToCompany(submitMessage, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback!=null) {
					int replyStatus = (Integer) callback;
					if(replyStatus==1) {
						FDViewUtil.showToast(context, "商家留言失败..",true);
					}else if(replyStatus==0) {
						FDViewUtil.showToast(context, "商家留言成功..",true);
						context.sendBroadcast(new Intent(FDUserCenterView.FD_USER_CENTER_LOGIN));  
						FDCompanyMessageActivity.this.finish();
					}
				}
			}
		});
	}

	
}
