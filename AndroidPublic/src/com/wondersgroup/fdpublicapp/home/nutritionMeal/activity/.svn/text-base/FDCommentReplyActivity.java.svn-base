package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDCommentView;
import com.wondersgroup.fdpublicapp.common.custom.FDTakePhotoView.FDCommentPhotoListener;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonReply;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 *  评价回复
 * @author chengshaohua
 *
 */
public class FDCommentReplyActivity extends FDBaseActivity implements FDCommentPhotoListener {
	public static final String FD_COMMENT_REPLY_KEY       = "fd.comment.reply.key";
	public static final String FD_COMMENT_REPLY_BROADCAST = "fd.comment.reply.broadcast";
	public LinearLayout backLayout;
	public LinearLayout commentCreatorLayout;
	public TextView commentTitleTextView;
	public TextView nameTextView;                        // 点评者
	public TextView commentTextView;                     // 点评内容
	public FDCommentView commentView;                    // 点评组件
	public FDCommonReply commentReply;                   // 点评回复.
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fd_common_comment_reply);
		
//		initCommentReplyView();
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey(FD_COMMENT_REPLY_KEY)) {
			commentReply = bundle.getParcelable(FD_COMMENT_REPLY_KEY);
		}
		initCommentReplyView();
	}
	
	/**
	 *  初始化评价回复
	 */
	public void initCommentReplyView() {
		backLayout = (LinearLayout) this.findViewById(R.id.fd_comment_reply_back_img_layout);
		commentCreatorLayout = (LinearLayout) this.findViewById(R.id.fd_comment_reply_creator_layout);
		commentTitleTextView = (TextView) this.findViewById(R.id.fd_comment_reply_name_header_title);
		nameTextView = (TextView) this.findViewById(R.id.fd_comment_reply_name_textview);
		commentTextView = (TextView) this.findViewById(R.id.fd_comment_content_textview);
		commentView = (FDCommentView) this.findViewById(R.id.fd_comment_reply_view);
		commentView.setCommentPhotoListener(this);
		
		if(commentReply!=null) {
			nameTextView.setText(commentReply.getCommentName()+": ");
			commentTextView.setText(commentReply.getCommentData());
		}
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDCommentReplyActivity.this.finish();
			}
		});
	}
	
	/**
	 *  点评回复
	 */
	public void submitCommentRestaurantReply(FDCommonComment commonReply) {
		if(commonReply==null) return;
		String loginUserName = appContext.getLoginUserName();
		
		FDReply reply = new FDReply();
		reply.setContentTextData(commonReply.getCommentData());
		reply.setCreateDate(StringUtils.dateToString(new Date()));
		if(commentReply!=null) {
			reply.setContentType("COMMENT_RLY");
			reply.setContentOwnerId(commentReply.getContentOwnerId());
			reply.setCreateUsername(loginUserName);
			reply.setContentTo(commentReply.getCommentName());
			reply.setContentFrom(loginUserName);
			reply.setPicList(commonReply.getCommentImages());
		}
		
		FDSearchWrapper searchWrapper = new FDSearchWrapper(this);
		searchWrapper.setCommonCommentReply(reply, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback!=null) {
					int replyStatus = (Integer) callback;
					if(replyStatus==1) {
						FDViewUtil.showToast(context, "点评回复失败..",true);
					}else if(replyStatus==0) {
						FDViewUtil.showToast(context, "点评回复成功..",true);
						context.sendBroadcast(new Intent(FD_COMMENT_REPLY_BROADCAST));  
						FDCommentReplyActivity.this.finish();
					}
				}
			}
		});
	}

	/**
	 * 必须实现
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		commentView.onActivityResult(requestCode, resultCode, data);
		
	}
	
	public void commentCallback(FDCommonComment commentCallback) {
		if (commentCallback == null) return;
		
		submitCommentRestaurantReply(commentCallback);
	}
	
}
