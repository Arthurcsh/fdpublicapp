package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.personal.activity.FDDishDetailActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineComment;
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
 * @author chengshaohua
 *  我评论的菜肴  adapter
 */
public class FDUserCommentDishAdapter extends BaseAdapter {

	private Context activity;
	public List<FDCuisineComment> commentsList;
	
	public FDUserCommentDishAdapter(Context activity,List<FDCuisineComment> commentsList) {
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
		FDCuisineComment comment = null;
		HolderView commentHolderView;
		if (convertView == null) {
			commentHolderView = new HolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_comments_dish_item, null);
			commentHolderView.dishNameTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_dish_name_textview);
			commentHolderView.companyNameTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_company_name_textview);
			commentHolderView.commentDataTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_dish_content_textview);
			commentHolderView.commentImageListView = (FDHorizontalListView) convertView.findViewById(R.id.fd_usercenter_comments_dish_imagelistView);
			commentHolderView.commentDateTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_dish_date_textview);
			convertView.setTag(commentHolderView);
		} else {
			commentHolderView = (HolderView) convertView.getTag();
		}
		if(commentsList!=null) {
			comment = commentsList.get(position);
			commentHolderView.dishNameTextView.setText(comment.getCuisineName());
			commentHolderView.companyNameTextView.setText(comment.getRestaurantName());
			commentHolderView.commentDataTextView.setText(comment.getContentTextData());
			commentHolderView.commentDateTextView.setText(comment.getCreateDate());
			List<FDImage> commentImages = comment.getPics();
			FDHorizontalListViewAdapter imageAdapter = new FDHorizontalListViewAdapter(activity, commentImages, false);
			commentHolderView.commentImageListView.setAdapter(imageAdapter);
			
			final int cuisineId = comment.getCuisineId();
			commentHolderView.dishNameTextView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity, FDDishDetailActivity.class);
					intent.putExtra("fd.dish.detail.key", cuisineId);
					activity.startActivity(intent);
				}
			});
		}
		return convertView;
	}
	
	static class HolderView {
		TextView dishNameTextView;
		TextView companyNameTextView;
		TextView commentDataTextView;
		FDHorizontalListView commentImageListView;
		TextView commentDateTextView;
		LinearLayout replyLinearLayout;
	}
}
