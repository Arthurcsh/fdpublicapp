package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonNote;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteNote;
import com.wondersgroup.fdpublicapp.home.safety.activity.FDNoteDetailActivity;

/**
 *  收藏的帖子Adapter
 * @author chengshaohua
 *
 */
public class FDFavoriteNoteAdpter extends BaseAdapter{
	private Context activity;
	private List<FDFavoriteNote> noteList;
	private FDHorizontalListViewAdapter photoAdapter;
	private List<FDImage> noteImageList = new ArrayList<FDImage>();
	
	public FDFavoriteNoteAdpter(Context activity, List<FDFavoriteNote> noteList) {
		super();
		this.activity = activity;
		this.noteList = noteList;
	}

	public int getCount() {
			return noteList==null?0:noteList.size();
	}

	public Object getItem(int position) {
		return noteList==null||noteList.size()==0?null:noteList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		FDFavoriteNote favoriteNote = null;
		NoteHolderView noteHolderView;
		if(convertView==null) {
			noteHolderView = new NoteHolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_favorite_note_item, null);
			noteHolderView.noteImageListViewLayout = (LinearLayout) convertView.findViewById(R.id.fd_school_favorite_note_imagelistView_layout);
			noteHolderView.noteImageListView = (FDHorizontalListView) convertView.findViewById(R.id.fd_school_favorite_note_imagelistView);
			noteHolderView.noteNameTextView = (TextView) convertView.findViewById(R.id.fd_school_favorite_note_title_textview);
			noteHolderView.noteContentTextView = (TextView) convertView.findViewById(R.id.fd_school_favorite_note_content_textview);
			noteHolderView.noteUserTextView = (TextView) convertView.findViewById(R.id.fd_school_favorite_note_supervision_name_tv);
			noteHolderView.noteDateTextView = (TextView) convertView.findViewById(R.id.fd_school_favorite_note_supervision_date_tv);
			convertView.setTag(noteHolderView);
		}else {
			noteHolderView = (NoteHolderView) convertView.getTag();
		}
		if(noteList!=null) {
			favoriteNote = noteList.get(position);
			if(favoriteNote!=null) {
				noteImageList = favoriteNote.getPics();
				if(noteImageList==null || noteImageList.size()==0) {
					noteHolderView.noteImageListViewLayout.setVisibility(View.GONE);
				}else {
					if(noteImageList.size()>0) {
						noteHolderView.noteImageListViewLayout.setVisibility(View.VISIBLE);
						photoAdapter = new FDHorizontalListViewAdapter(activity, noteImageList, false);
						noteHolderView.noteImageListView.setAdapter(photoAdapter);
					}
				}
				
				noteHolderView.noteNameTextView.setText(favoriteNote.getContentTitle());
				noteHolderView.noteContentTextView.setText(favoriteNote.getContentTextData());
				noteHolderView.noteUserTextView.setText(favoriteNote.getCommentUserName());
				noteHolderView.noteDateTextView.setText(StringUtils.dateToString(favoriteNote.getCreateDate()));
			}
		}
		final FDFavoriteNote note = favoriteNote;
		LinearLayout noteDetailLayout = (LinearLayout) convertView.findViewById(R.id.fd_school_favorite_note_detail_layout);
		noteDetailLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(note!=null) {
					FDCommonNote commonNote = new FDCommonNote();
					commonNote.setContentId(note.getContentId());
					commonNote.setCreateUsername(note.getCreateUsername());
					commonNote.setCreateUserNickname(note.getCreateUserNickname());
					commonNote.setCreateDate(note.getCreateDate());
					commonNote.setContentTitle(note.getContentTitle());
					commonNote.setContentTextData(note.getContentTextData());
					
					Intent intent = new Intent(activity, FDNoteDetailActivity.class);
					intent.putExtra(FDNoteDetailActivity.FD_NOTE_DETAIL_KEY, commonNote);
					activity.startActivity(intent);
				}
			}
		});
		return convertView;
	}
	
	static class NoteHolderView {
		TextView noteNameTextView;
		TextView noteContentTextView;
		TextView noteUserTextView;
		TextView noteDateTextView;
		LinearLayout noteImageListViewLayout;
		FDHorizontalListView noteImageListView;
	}
}
