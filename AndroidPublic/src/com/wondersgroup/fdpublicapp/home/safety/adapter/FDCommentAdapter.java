package com.wondersgroup.fdpublicapp.home.safety.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.safety.mode.FDSafetyComment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  评论 adapter
 */
public class FDCommentAdapter extends BaseAdapter {

	private Context activity;
	public ArrayList<FDSafetyComment> commentsList;
	
	public FDCommentAdapter(Context activity,ArrayList<FDSafetyComment> commentsList) {
		super();
		this.activity = activity;
		this.commentsList = commentsList;
	}

	public int getCount() {
		return commentsList==null?0:commentsList.size();
	}

	public Object getItem(int position) {
		return commentsList==null?null:commentsList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDSafetyComment comment = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_comment_item, null);
			TextView commentNameText = (TextView) convertView.findViewById(R.id.safety_contenter_textview);
			TextView commentContentText = (TextView) convertView.findViewById(R.id.safety_comment_content_textview);
			TextView commentDateText = (TextView) convertView.findViewById(R.id.safety_comment_date_tv);
			if(commentsList!=null) {
				comment = commentsList.get(position);
				commentNameText.setText(comment.getCommenterName());
				commentContentText.setText(comment.getContent());
				commentDateText.setText(comment.getCommentDate());
			}
			convertView.setTag(comment);
		} else {
			comment = (FDSafetyComment) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(ArrayList<FDSafetyComment> comments) {
		this.commentsList = comments;
		this.notifyDataSetChanged();
	}
}
