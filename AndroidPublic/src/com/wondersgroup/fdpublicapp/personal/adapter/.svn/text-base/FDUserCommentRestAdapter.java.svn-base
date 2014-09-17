package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  我评论的餐馆  adapter
 */
public class FDUserCommentRestAdapter extends BaseAdapter {

	private Context activity;
	public List<FDRestaurantComment> commentsList;
	
	public FDUserCommentRestAdapter(Context activity,List<FDRestaurantComment> commentsList) {
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
		FDRestaurantComment comment = null;
		HolderView commentHolderView;
		if (convertView == null) {
			commentHolderView = new HolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_comments_rest_item, null);
			commentHolderView.restNameTextView = (TextView) convertView.findViewById(R.id.textv_fd_usercenter_my_comments_item_restname);
			commentHolderView.creditImageView = (ImageView) convertView.findViewById(R.id.imgv_fd_usercenter_my_comments_item_credit_img);
			commentHolderView.restOrgTextView = (TextView) convertView.findViewById(R.id.textv_fd_usercenter_my_comments_item_credit_org);
			commentHolderView.restOrgDateTextView = (TextView) convertView.findViewById(R.id.textv_fd_usercenter_my_comments_item_credit_time);
			commentHolderView.restRatingView = (RatingBar) convertView.findViewById(R.id.fd_usercenter_my_comments_rating);
			commentHolderView.restConsumptionTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_my_comments_average_textview);
			commentHolderView.restCommentTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_my_comments_content_textview);
			commentHolderView.commentDateTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_my_comments_date_textview);
			commentHolderView.restDetailLayout = (LinearLayout) convertView.findViewById(R.id.fd_usercenter_my_comments_detail_layout);
			convertView.setTag(commentHolderView);
		} else {
			commentHolderView = (HolderView) convertView.getTag();
		}
		if(commentsList!=null) {
			comment = commentsList.get(position);
			final FDRestaurantComment restaurant = comment;
			commentHolderView.restNameTextView.setText(comment.getRestaurantNameAbbrev());
			FDViewUtil.showSafetyRating(comment.getFoodSaftyRating(),commentHolderView.creditImageView);
			commentHolderView.restOrgTextView.setText(comment.getOrgName());
			commentHolderView.restOrgDateTextView.setText(StringUtils.dateToString(comment.getFoodSaftyRatingDate()));
			commentHolderView.restRatingView.setRating(comment.getStar());
			commentHolderView.restCommentTextView.setText(comment.getContentTextData());
			commentHolderView.restConsumptionTextView.setText(""+comment.getAverageComsumption());
			commentHolderView.commentDateTextView.setText(comment.getCreateDate());
			commentHolderView.restDetailLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					if(restaurant==null) return;
					Intent intent = new Intent(activity, FDSearchDetailsActivity.class);
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurant.getRestaurantId());
					activity.startActivity(intent);
				}
			});
		}
		return convertView;
	}

	static class HolderView {
		TextView restNameTextView;
		ImageView creditImageView;
		TextView restOrgTextView;
		TextView restOrgDateTextView;
		RatingBar restRatingView;
		TextView restConsumptionTextView;
		TextView restCommentTextView;
		TextView commentDateTextView;
		LinearLayout restDetailLayout;
	}
}
