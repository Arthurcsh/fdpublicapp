package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonNote;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.safety.activity.FDNoteDetailActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentNote;
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
 *  我评论的帖子  adapter
 */
public class FDUserCommentNoteAdapter extends BaseAdapter {

	private Context activity;
	public List<FDCommentNote> commentsList;
	
	public FDUserCommentNoteAdapter(Context activity,List<FDCommentNote> commentsList) {
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
		FDCommentNote comment = null;
		HolderView commentHolderView;
		if (convertView == null) {
			commentHolderView = new HolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_comments_note_item, null);
			commentHolderView.noteNameTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_note_textview);
			commentHolderView.noteDataTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_note_content_textview);
			commentHolderView.noteDateTextView = (TextView) convertView.findViewById(R.id.fd_usercenter_comments_note_date_tv);
			commentHolderView.noteDetailLayout = (LinearLayout) convertView.findViewById(R.id.fd_usercenter_comments_note_layout);
			convertView.setTag(commentHolderView);
		} else {
			commentHolderView = (HolderView) convertView.getTag();
		}
		if(commentsList!=null) {
			comment = commentsList.get(position);
			final FDCommentNote commentNote = comment;
			commentHolderView.noteNameTextView.setText(commentNote.getPostTitle());
			commentHolderView.noteDateTextView.setText(commentNote.getPostCreateTime());
			List<FDNoteComment> commentList = commentNote.getCommentList();
			if(commentList!=null) {
				FDNoteComment noteComment = commentList.get(0);
				commentHolderView.noteDataTextView.setText(noteComment.getContentTextData());
			}
			commentHolderView.noteDetailLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					if(commentNote!=null) {
						/**  转换成帖子详情  **/
						FDCommonNote commonNote = new FDCommonNote();
						commonNote.setContentId(commentNote.getPostId());
						commonNote.setContentTitle(commentNote.getPostTitle());
						
						Intent intent = new Intent(activity, FDNoteDetailActivity.class);
						intent.putExtra(FDNoteDetailActivity.FD_NOTE_DETAIL_KEY, commonNote);
						activity.startActivity(intent);
					}
				}
			});
		}
		return convertView;
	}
	
	static class HolderView {
		TextView noteNameTextView;
		TextView noteDataTextView;
		TextView noteDateTextView;
		LinearLayout noteDetailLayout;
	}
}
