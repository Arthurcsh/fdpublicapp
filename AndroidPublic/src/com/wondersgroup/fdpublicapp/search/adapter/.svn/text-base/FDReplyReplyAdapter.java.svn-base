package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonReply;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDCommentReplyActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentResponse;
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
 *  评价回复的回复  Adapter
 * @author chengshaohua
 *
 */
public class FDReplyReplyAdapter extends BaseAdapter {
    private Context activity;
    private int commentOwnerId;
	private List<FDCommentResponse> responseList;
	
	public FDReplyReplyAdapter(Context activity,List<FDCommentResponse> responseList) {
		super();
		this.activity = activity;
		this.responseList = responseList;
	}
	
	public int getCount() {
		if(responseList!=null) {
			return responseList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(responseList!=null) {
			return responseList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDCommentResponse commentResponse = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_rest_seek_comment_reply_reply_item, null);
			commentViewHolder.commentReplyNameText = (TextView) view.findViewById(R.id.fd_comment_reply_reply_name_textview);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.fd_reply_reply_commenter_name_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_reply_reply_date_textview);
			commentViewHolder.commentContentText = (TextView) view.findViewById(R.id.fd_reply_reply_content_textview);
			commentViewHolder.commentReplyLayout = (LinearLayout) view.findViewById(R.id.fd_comment_reply_reply_layout);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(responseList!=null) {
			commentResponse = responseList.get(position);
			final FDCommonReply commentReply = new FDCommonReply();
			commentReply.setContentId(commentResponse.getContentId());
			commentReply.setContentOwnerId(commentOwnerId);
			commentReply.setCommentName(commentResponse.getUserFrom());
			commentReply.setCommentData(commentResponse.getContent());
			commentReply.setCommentDate(commentResponse.getCreateDate());
			commentViewHolder.commentReplyNameText.setText(commentResponse.getUserFrom());
			commentViewHolder.commentNameText.setText(commentResponse.getUserTo());
			if(commentResponse.getCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(commentResponse.getCreateDate());
			}
			commentViewHolder.commentContentText.setText(commentResponse.getContent());
			commentViewHolder.commentReplyLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity, FDCommentReplyActivity.class);
					intent.putExtra(FDCommentReplyActivity.FD_COMMENT_REPLY_KEY, commentReply);
					activity.startActivity(intent);
				}
			});
		}
		
		return view;
	}

	public void setLeaveMessages(List<FDCommentResponse> responseReplyList) {
		this.responseList = (List<FDCommentResponse>) ((ArrayList<FDCommentResponse>)responseReplyList).clone();;
		this.notifyDataSetChanged();
	}
	
	static class ViewHolder {
		TextView commentReplyNameText;
		TextView commentNameText;
		TextView commentContentText;
		TextView commentDateText;
		LinearLayout commentReplyLayout;
	}

	public int getCommentOwnerId() {
		return commentOwnerId;
	}

	public void setCommentOwnerId(int commentOwnerId) {
		this.commentOwnerId = commentOwnerId;
	}
	
}
