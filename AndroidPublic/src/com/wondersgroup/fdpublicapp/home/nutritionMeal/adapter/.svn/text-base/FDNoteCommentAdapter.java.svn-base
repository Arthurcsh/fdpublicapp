package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  帖子评论 adapter
 */
public class FDNoteCommentAdapter extends BaseAdapter {

	private Context activity;
	public List<FDNoteComment> commentsList;
	
	public FDNoteCommentAdapter(Context activity,List<FDNoteComment> commentsList) {
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
		FDNoteComment comment = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_comment_item, null);
			TextView commentNameText = (TextView) convertView.findViewById(R.id.safety_contenter_textview);
			TextView commentContentText = (TextView) convertView.findViewById(R.id.safety_comment_content_textview);
			TextView commentDateText = (TextView) convertView.findViewById(R.id.safety_comment_date_tv);
			if(commentsList!=null) {
				comment = commentsList.get(position);
				commentNameText.setText(comment.getContentTitle());
				commentContentText.setText(comment.getContentTextData());
				commentDateText.setText(comment.getCreateDate());
			}
			convertView.setTag(comment);
		} else {
			comment = (FDNoteComment) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(List<FDNoteComment> comments) {
		this.commentsList = comments;
		this.notifyDataSetChanged();
	}
}
