package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *  营养餐评论 Adapter
 * @author chengshaohua
 *
 */
public class FDStudentMealCommentAdapter extends BaseAdapter {
    private Context activity;
	private List<FDComment> commentsList;
	
	public FDStudentMealCommentAdapter(Context activity,List<FDComment> mealCommentsList) {
		super();
		this.activity = activity;
		this.commentsList = mealCommentsList;
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
		FDComment comment = null;
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
			System.out.println("------   comment user name  -------"+comment.getCommentUserNickname());
			commentViewHolder.commentNameText.setText(comment.getCommentUserNickname());
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
