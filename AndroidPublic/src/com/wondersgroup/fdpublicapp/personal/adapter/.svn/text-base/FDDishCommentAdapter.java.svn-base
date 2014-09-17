package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineComment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *  菜肴评论 Adapter
 * @author chengshaohua
 *
 */
public class FDDishCommentAdapter extends BaseAdapter {
    private Context activity;
	private List<FDCuisineComment> commentsList;
	
	public FDDishCommentAdapter(Context activity,List<FDCuisineComment> cuisineCommentsList) {
		super();
		this.activity = activity;
		this.commentsList = cuisineCommentsList;
	}
	
	public int getCount() {
		if(commentsList!=null) {
			return commentsList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(commentsList!=null) {
			return commentsList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDCuisineComment comment = null;
		ViewHolder commentViewHolder;
		View view = convertView;
		if (convertView == null) {
			commentViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_meal_comment_item, null);
			commentViewHolder.commentNameText = (TextView) view.findViewById(R.id.fd_student_meal_comment_username_textview);
			commentViewHolder.commentText = (TextView) view.findViewById(R.id.fd_student_meal_comment_textview);
			commentViewHolder.commentDateText = (TextView) view.findViewById(R.id.fd_student_meal_comment_date);
			
			view.setTag(commentViewHolder);
		} else {
			commentViewHolder = (ViewHolder) view.getTag();
		}
		if(commentsList!=null) {
			comment = commentsList.get(position);
			commentViewHolder.commentNameText.setText(comment.getCommentUsername());
			commentViewHolder.commentText.setText(comment.getContentTextData());
			if(comment.getCreateDate()!=null) {
				commentViewHolder.commentDateText.setText(comment.getCreateDate());
			}
		}
		
		return view;
	}

	static class ViewHolder {
		TextView commentNameText;
		TextView commentText;
		TextView commentDateText;
	}
}
