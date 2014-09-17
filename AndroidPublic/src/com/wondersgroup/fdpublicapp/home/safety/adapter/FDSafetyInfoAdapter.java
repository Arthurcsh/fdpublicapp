package com.wondersgroup.fdpublicapp.home.safety.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonNote;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.safety.activity.FDNoteDetailActivity;

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
 *  食品安全信息 adapter
 */
public class FDSafetyInfoAdapter extends BaseAdapter {

	private Context activity;
	public List<FDNoteComment> safetyInfoList;
	
	public FDSafetyInfoAdapter(Context activity, List<FDNoteComment> restlist) {
		super();
		this.activity = activity;
		this.safetyInfoList = restlist;
	}

	public int getCount() {
		return safetyInfoList==null?0:safetyInfoList.size();
	}

	public Object getItem(int position) {
		return safetyInfoList==null?null:safetyInfoList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDNoteComment safetyInfo = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_safety_info_item, null);
			TextView safetTitleText = (TextView) convertView.findViewById(R.id.safety_info_title_textview);
			TextView safetyInfoText = (TextView) convertView.findViewById(R.id.safety_info_content_textview);
			TextView supervision = (TextView) convertView.findViewById(R.id.safety_supervision_name_tv);
			TextView supervisionDate = (TextView) convertView.findViewById(R.id.safety_supervision_date_tv);
			FDHorizontalListView safetyImageListView = (FDHorizontalListView) convertView.findViewById(R.id.fd_safety_info_photo_imagelistView);
			LinearLayout detailLayout = (LinearLayout) convertView.findViewById(R.id.safety_info_detail_layout);
			if(safetyInfoList!=null) {
				safetyInfo = safetyInfoList.get(position);
				safetTitleText.setText(safetyInfo.getContentTitle());
				safetyInfoText.setText(safetyInfo.getContentTextData());
				supervision.setText(safetyInfo.getCreateUsername());
				supervisionDate.setText(safetyInfo.getCreateDate());
				List<FDImage> picList = safetyInfo.getPicList();
				FDHorizontalListViewAdapter imageAdapter = new FDHorizontalListViewAdapter(activity, picList, false);
				safetyImageListView.setAdapter(imageAdapter);
				
				final FDNoteComment safetyInfoDetail = safetyInfo;
				detailLayout.setOnClickListener(new OnClickListener(){
					public void onClick(View view) {
						if(safetyInfoDetail!=null) {
							FDCommonNote safetyDetail = new FDCommonNote();
							safetyDetail.setContentId(safetyInfoDetail.getContentId());
							safetyDetail.setContentTitle(safetyInfoDetail.getContentTitle());
							safetyDetail.setContentTextData(safetyInfoDetail.getContentTextData());
							
							Intent intent = new Intent(activity,FDNoteDetailActivity.class);
							intent.putExtra(FDNoteDetailActivity.FD_NOTE_DETAIL_KEY, safetyDetail);
							activity.startActivity(intent);
						}
						
					}
				});
			}
			convertView.setTag(safetyInfo);
		} else {
			safetyInfo = (FDNoteComment) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(ArrayList<FDNoteComment> safetyInfos) {
		this.safetyInfoList = (List<FDNoteComment>) safetyInfos.clone();
		this.notifyDataSetChanged();
	}
}
