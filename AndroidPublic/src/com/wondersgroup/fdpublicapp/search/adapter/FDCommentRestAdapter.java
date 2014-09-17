package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonReply;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDCommentReplyActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentResponse;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  餐厅详情点评   adapter
 */
public class FDCommentRestAdapter extends BaseAdapter {

	private Context activity;
	public List<FDRestaurantComment> commentsList;
	
	public FDCommentRestAdapter(Context activity,List<FDRestaurantComment> commentsList) {
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
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_rest_seek_detail_comment_item, null);
			commentHolderView.commentNameTextView = (TextView) convertView.findViewById(R.id.fd_rest_comments_name_textview);
			commentHolderView.averageTextView = (TextView) convertView.findViewById(R.id.fd_rest_comments_average_textview);
			commentHolderView.commentDataTextView = (TextView) convertView.findViewById(R.id.fd_rest_comments_content_textview);
			commentHolderView.commentRatingBarView = (RatingBar) convertView.findViewById(R.id.fd_rest_comments_rating);
			commentHolderView.commentDateTextView = (TextView) convertView.findViewById(R.id.fd_rest_comments_date_textview);
			commentHolderView.commentReplyImageLayout = (LinearLayout) convertView.findViewById(R.id.fd_rest_comments_reply_imageview_layout);
			commentHolderView.commentImageList = (FDHorizontalListView) convertView.findViewById(R.id.fd_rest_comment_imagelistView);
			commentHolderView.commentReplyReplyListView = (ListView) convertView.findViewById(R.id.fd_rest_comments_reply_reply_listView);
			commentHolderView.commentReplyReplyConuntView = (TextView) convertView.findViewById(R.id.fd_rest_comments_reply_reply_count_textview);
			
			convertView.setTag(commentHolderView);
		} else {
			commentHolderView = (HolderView) convertView.getTag();
		}
		if(commentsList!=null) {
			comment = commentsList.get(position);
			List<FDCommentResponse> responseList = comment.getResponseList();
			List<FDImage> imageList = comment.getPics();
			
			commentHolderView.commentNameTextView.setText(comment.getCommentUsername());
			commentHolderView.averageTextView.setText(""+comment.getAverageComsumption());
			commentHolderView.commentRatingBarView.setRating(comment.getFoodSaftyRating());
			commentHolderView.commentDataTextView.setText(comment.getContentTextData());
			commentHolderView.commentDateTextView.setText(comment.getCreateDate());
			
			if(imageList!=null && imageList.size()>0) {
				commentHolderView.commentImageList.setVisibility(View.VISIBLE);
				FDHorizontalListViewAdapter photoAdapter = new FDHorizontalListViewAdapter(activity, imageList,false);
				commentHolderView.commentImageList.setAdapter(photoAdapter);
			}else {
				commentHolderView.commentImageList.setVisibility(View.GONE);
			}
			
			if(responseList!=null) {
				commentHolderView.commentReplyReplyConuntView.setText("( 共"+responseList.size()+"条 )");
				FDReplyReplyAdapter replyReplyAdapter = new FDReplyReplyAdapter(activity, responseList);
				replyReplyAdapter.setCommentOwnerId(comment.getContentId());
				commentHolderView.commentReplyReplyListView.setAdapter(replyReplyAdapter);
			}
			final FDCommonReply commentReply = new FDCommonReply();
			commentReply.setContentId(comment.getContentId());
			commentReply.setContentOwnerId(comment.getContentId());
			commentReply.setCommentName(comment.getCommentUsername());
			commentReply.setCommentData(comment.getContentTextData());
			commentReply.setCommentDate(comment.getCreateDate());
			commentHolderView.commentReplyImageLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity, FDCommentReplyActivity.class);
					intent.putExtra(FDCommentReplyActivity.FD_COMMENT_REPLY_KEY, commentReply);
					activity.startActivity(intent);
				}
			});
		}
		return convertView;
	}
	
	static class HolderView {
		TextView commentNameTextView;
		TextView averageTextView;
		TextView commentDataTextView;
		RatingBar commentRatingBarView;
		TextView commentDateTextView;
		LinearLayout commentReplyImageLayout;
		FDHorizontalListView commentImageList;
		LinearLayout commentReplyReplyLayout;
		ListView commentReplyReplyListView;
		TextView commentReplyReplyConuntView;
	}
	
	public void notifyCommentRestaurant(List<FDRestaurantComment> comments) {
		this.commentsList = comments;
		this.notifyDataSetChanged();
	}
}
