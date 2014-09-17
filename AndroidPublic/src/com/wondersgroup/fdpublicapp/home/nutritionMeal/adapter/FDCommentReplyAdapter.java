package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonReply;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDCommentReplyActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  营养餐评论回复 Adapter
 * @author chengshaohua
 *
 */
public class FDCommentReplyAdapter extends BaseAdapter {
    private Context activity;
	private List<FDReply> replyList;
	
	public FDCommentReplyAdapter(Context activity,List<FDReply> replysList) {
		super();
		this.activity = activity;
		this.replyList = replysList;
	}
	
	public int getCount() {
		if(replyList!=null) {
			return replyList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(replyList!=null) {
			return replyList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDReply comment = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_comment_reply_item, null);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.textv_fd_usercenter_comments_reply_name);
			commentViewHolder.commentText = (TextView) view.findViewById(R.id.fd_comments_reply_content_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_usercenter_my_comments_date_textview);
			commentViewHolder.commentReplyLayout = (LinearLayout) view.findViewById(R.id.fd_comments_reply_imageview_layout);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(replyList!=null) {
			comment = replyList.get(position);
			commentViewHolder.commentNameText.setText(comment.getCreateUsername());
			commentViewHolder.commentText.setText(comment.getContentTextData());
			if(comment.getCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(comment.getCreateDate());
			}
//			final FDCommonReply commentReply = new FDCommonReply();
//			commentReply.setContentId(comment.getContentOwnerId());
//			commentReply.setContentOwnerId(comment.getContentOwnerId());
//			commentReply.setCommentName(comment.get);
//			commentReply.setCommentData(comment.getContentTextData());
//			commentReply.setCommentDate(comment.getCreateDate());
//			commentViewHolder.commentReplyLayout.setOnClickListener(new OnClickListener(){
//				public void onClick(View view) {
//					Intent intent = new Intent(activity, FDCommentReplyActivity.class);
//					intent.putExtra(FDCommentReplyActivity.FD_COMMENT_REPLY_KEY, commentReply);
//					activity.startActivity(intent);
//				}
//			});
		}
		
		return view;
	}

	static class ViewHolder {
		TextView commentNameText;
		TextView commentText;
		TextView commentDateText;
		LinearLayout commentReplyLayout;
	}
}
