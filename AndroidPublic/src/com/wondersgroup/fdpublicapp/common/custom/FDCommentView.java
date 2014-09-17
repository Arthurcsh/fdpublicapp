package com.wondersgroup.fdpublicapp.common.custom;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDTakePhotoView.FDCommentPhotoListener;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 点评组件
 * 
 * @author chengshaohua
 * 
 */
public class FDCommentView extends LinearLayout implements FDCommentPhotoListener {
	private Context context;
	private EditText commentEditView;
	private TextView leaveCountView;
	private FDTakePhotoView takePhotoView;
	private FDCommentPhotoListener commentSubmitListener;

	public FDCommentView(Context context) {
		super(context);
		this.context = context;

		initCommentView();
	}

	public FDCommentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		initCommentView();
	}

	public FDCommentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		initCommentView();
	}

	/**
	 * 初始化视图
	 */
	public void initCommentView() {
		View commentView = LayoutInflater.from(context).inflate(R.layout.fd_common_comment_view, null);
		addView(commentView);

		commentEditView = (EditText) commentView.findViewById(R.id.fd_common_comment_textveiw);
		leaveCountView = (TextView) commentView.findViewById(R.id.fd_common_comment_desc_textview);
		takePhotoView = (FDTakePhotoView) commentView.findViewById(R.id.fd_common_comment_take_photo_view);

		commentEditView.addTextChangedListener(new FDTextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				filterCommentEditText();
			}
		});
	}

	/**
	 * EditText输入验证
	 */
	public void filterCommentEditText() {
		int maxLen = 100;
		Editable editable = commentEditView.getText();
		int len = editable.length();
		leaveCountView.setText("还可以输入" + (maxLen - len) + "字");

		if (len > maxLen) {
			int selEndIndex = Selection.getSelectionEnd(editable);
			String str = editable.toString();
			String newStr = str.substring(0, maxLen); // 截取新字符串
			commentEditView.setText(newStr);
			editable = commentEditView.getText();

			int newLen = editable.length(); // 新字符串的长度
			if (selEndIndex > newLen) {
				selEndIndex = editable.length(); // 旧光标位置超过字符串长度
			}
			Selection.setSelection(editable, selEndIndex); // 设置新光标所在的位置
		}
	}
	
	public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		takePhotoView.onActivityResult(requestCode, resultCode, data);
	}
	
	public void setCommentPhotoListener(FDCommentPhotoListener commentInterface) {
		this.commentSubmitListener = commentInterface;
		if(commentInterface instanceof Activity) {
			takePhotoView.setCommentActivity((Activity) commentInterface);
		}
		takePhotoView.setCommentPhotoListener(this);
	}
	
	public FDTakePhotoView getTakePhotoView() {
		return this.takePhotoView;
	}

	public void commentCallback(FDCommonComment commentCallback) {
		if(commentCallback!=null) {
			if(!FDViewUtil.verifyTextview(commentEditView, "请输入评价内容！")) return;
			
			String commentData = commentEditView.getText().toString().trim();
			commentCallback.setCommentData(commentData);
			commentSubmitListener.commentCallback(commentCallback);
		}
	}
}
