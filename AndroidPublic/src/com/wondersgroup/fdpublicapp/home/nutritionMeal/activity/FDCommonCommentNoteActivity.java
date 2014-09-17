package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 通用帖子详情点评 
 * @author chengshaohua
 *
 */
public class FDCommonCommentNoteActivity extends FDBaseActivity {

	private FDCommonComment commonComment;
	private LinearLayout backLayout;
	private LinearLayout defineLayout;
	private TextView defineNameTextView;
	private TextView commentLeaveTextView;
	private EditText commentEditView;
	private Button submitButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_common_note_comment);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("fd.common.comment.key")) {
			commonComment = bundle.getParcelable("fd.common.comment.key");
		}
		
		initNoteCommentView();
		setCommonCommentView();
	}
	
	/**
	 * 初始化帖子详情点评 
	 */
	public void initNoteCommentView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_common_comment_back_img_layout);
		defineLayout = (LinearLayout) findViewById(R.id.fd_common_comment_reply_creator_layout);
		defineNameTextView = (TextView) findViewById(R.id.fd_common_comment_desc_textview);
		commentLeaveTextView = (TextView) findViewById(R.id.fd_common_comment_leave_textview);
		commentEditView = (EditText) findViewById(R.id.fd_common_comment_content_textveiw);
		submitButton = (Button) findViewById(R.id.fd_common_comment_submit_button);
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				finish();
			}
		});
		commentEditView.addTextChangedListener(new FDTextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				FDStudentSchoolCommentActivity.filterCommentEditText(commentEditView,commentLeaveTextView);
			}
		});
		
		submitButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(commonComment==null) return;
				String commentData = commentEditView.getText().toString().trim();
				if(StringUtils.isEmpty(commentData)) {
					FDViewUtil.showToast(context, "请输入点评内容..", true);
					return;
				}
				commonComment.setCommentData(commentData);
				submitCommentNote(commonComment);
			}
		});
	}
	
	/**
	 * 设置点评视图
	 */
	public void setCommonCommentView() {
		if(commonComment==null) return;
		defineNameTextView.setText(commonComment.getContentTitle());
	}
	
	/**
	 * 提交点评
	 * @param noteComment
	 */
	public void submitCommentNote(FDCommonComment noteComment) {
		if(noteComment==null) return;
		FDReply commonComment = new FDReply();
		commonComment.setContentOwnerId(noteComment.getContentId());
		commonComment.setContentTextData(noteComment.getCommentData());
		commonComment.setContentTitle(noteComment.getContentTitle());
		commonComment.setIsRead(0);
		commonComment.setContentType("COMMENT");
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(context);
		mealWrapper.setFavoriteNoteComment(commonComment, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				int commentStatus = (Integer) callback;
				if(commentStatus==0) {
					setResult(204);
					finish();
				}else {
					FDViewUtil.showToast(context, "点评帖子失败..", true);
				}
			}
		});
	}
}
