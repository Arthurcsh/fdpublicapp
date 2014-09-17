package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *  全部消息回复 Adapter
 * @author chengshaohua
 *
 */
public class FDMessageReplyAdapter extends BaseAdapter {
    private Context activity;
	private List<FDMessage> messageReplyList;
	
	public FDMessageReplyAdapter(Context activity,List<FDMessage> messageList) {
		super();
		this.activity = activity;
		this.messageReplyList = messageList;
	}
	
	public int getCount() {
		if(messageReplyList!=null) {
			return messageReplyList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(messageReplyList!=null) {
			return messageReplyList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDMessage comment = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_message_reply_item, null);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.fd_user_message_reply_title_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_user_message_reply_date_tv);
			commentViewHolder.commentContentText = (TextView) view.findViewById(R.id.fd_user_message_reply_data_textview);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(messageReplyList!=null) {
			comment = messageReplyList.get(position);
			commentViewHolder.commentNameText.setText(comment.getFromUsername());
			if(comment.getCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(StringUtils.dateToString(comment.getLastModifiedDate()));
			}
			commentViewHolder.commentContentText.setText(comment.getCommContent());
		}
		
		return view;
	}

	public void setLeaveMessages(List<FDMessage> messages) {
		this.messageReplyList = messages;
		this.notifyDataSetChanged();
	}
	
	static class ViewHolder {
		TextView commentNameText;
		TextView commentContentText;
		TextView commentDateText;
	}
}
