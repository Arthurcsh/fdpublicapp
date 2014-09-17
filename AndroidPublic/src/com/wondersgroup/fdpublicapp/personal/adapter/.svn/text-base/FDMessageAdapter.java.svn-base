package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentSchoolCommentActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentReply;
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
 *  全部消息 Adapter
 * @author chengshaohua
 *
 */
public class FDMessageAdapter extends BaseAdapter {
    private Context activity;
	private List<FDCommentReply> messageList;
	
	public FDMessageAdapter(Context activity,List<FDCommentReply> messageList) {
		super();
		this.activity = activity;
		this.messageList = messageList;
	}
	
	public int getCount() {
		if(messageList!=null) {
			return messageList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(messageList!=null) {
			return messageList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDCommentReply comment = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_all_message_item, null);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.fd_user_message_title_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_usercenter_message_date_textview);
			commentViewHolder.commentContentText = (TextView) view.findViewById(R.id.fd_user_message_content_textview);
			commentViewHolder.commentReplyLayout = (LinearLayout) view.findViewById(R.id.fd_usercenter_message_reply_layout);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(messageList!=null) {
			comment = messageList.get(position);
			commentViewHolder.commentNameText.setText(comment.getUserFromNickName());
			if(comment.getCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(comment.getCreateDate());
			}
			commentViewHolder.commentContentText.setText(comment.getContent());
			commentViewHolder.commentReplyLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity, FDStudentSchoolCommentActivity.class);
					activity.startActivity(intent);
				}
			});
		}
		
		return view;
	}

	public void setLeaveMessages(List<FDCommentReply> messages) {
		this.messageList = messages;
		this.notifyDataSetChanged();
	}
	
	static class ViewHolder {
		TextView commentNameText;
		TextView commentContentText;
		TextView commentDateText;
		LinearLayout commentReplyLayout;
	}
}
