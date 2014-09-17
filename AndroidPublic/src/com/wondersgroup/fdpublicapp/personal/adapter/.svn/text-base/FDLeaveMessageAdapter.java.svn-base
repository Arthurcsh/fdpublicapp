package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.personal.mode.FDLeaveMessage;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
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
 *  我的留言 Adapter
 * @author chengshaohua
 *
 */
public class FDLeaveMessageAdapter extends BaseAdapter {
    private Context activity;
	private List<FDLeaveMessage> messageList;
	private boolean isListReply;
	
	public FDLeaveMessageAdapter(Context activity,List<FDLeaveMessage> messageList) {
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
		FDLeaveMessage leaveMessage = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_message_item, null);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.fd_user_message_title_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_user_message_supervision_date_tv);
			commentViewHolder.commentContentText = (TextView) view.findViewById(R.id.fd_user_message_content_textview);
			commentViewHolder.commentReplyNameText = (TextView) view.findViewById(R.id.fd_user_message_reply_name_textview);
			commentViewHolder.commentReplyContentText = (TextView) view.findViewById(R.id.fd_user_message_reply_content_textview);
			commentViewHolder.sellerReplyLayout = (LinearLayout) view.findViewById(R.id.fd_user_message_seller_reply_layout);
			commentViewHolder.restDetailLayout = (LinearLayout) view.findViewById(R.id.fd_user_message_detail_layout);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(messageList!=null) {
			leaveMessage = messageList.get(position);
			final int restaurantId = leaveMessage.getCompanyId();
			commentViewHolder.commentNameText.setText(leaveMessage.getCompanyName());
			if(leaveMessage.getMsgCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(leaveMessage.getMsgCreateDate());
			}
			commentViewHolder.commentContentText.setText(leaveMessage.getMsgContent());
			commentViewHolder.commentReplyNameText.setText(leaveMessage.getCompanyName());
			commentViewHolder.commentReplyContentText.setText(leaveMessage.getReplayContent());
			commentViewHolder.restDetailLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity,FDSearchDetailsActivity.class);
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantId);
					activity.startActivity(intent);
				}
			});
		}
		if(isListReply) {
			commentViewHolder.sellerReplyLayout.setVisibility(View.VISIBLE);
		}else {
			commentViewHolder.sellerReplyLayout.setVisibility(View.GONE);
		}
		
		return view;
	}

	public void setLeaveMessages(List<FDLeaveMessage> messages) {
		this.messageList = (List<FDLeaveMessage>) ((ArrayList<FDLeaveMessage>)messages).clone();
		this.notifyDataSetChanged();
	}
	
	static class ViewHolder {
		TextView commentNameText;
		TextView commentContentText;
		TextView commentDateText;
		TextView commentReplyNameText;
		TextView commentReplyContentText;
		LinearLayout sellerReplyLayout;
		LinearLayout restDetailLayout;
	}
	
	public void setShowListReply(boolean isReply) {
		this.isListReply = isReply;
	}
}
