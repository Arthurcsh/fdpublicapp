package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;

/**
 *  收藏的学校Adapter
 * @author chengshaohua
 *
 */
public class FDFavoriteSchoolAdpter extends BaseAdapter{
	private Context activity;
	private List<FDSchool> fdSchoolList;
	
	public FDFavoriteSchoolAdpter(Context activity, List<FDSchool> schoolTypeList) {
		super();
		this.activity = activity;
		this.fdSchoolList = schoolTypeList;
	}

	public int getCount() {
			return fdSchoolList==null?0:fdSchoolList.size();
	}

	public Object getItem(int position) {
		return fdSchoolList==null||fdSchoolList.size()==0?null:fdSchoolList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		FDSchool favoriteSchool;
		SchoolHolderView schoolHolderView;
		if(convertView==null) {
			schoolHolderView = new SchoolHolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_detail_item, null);
			schoolHolderView.schoolImageView = (ImageView) convertView.findViewById(R.id.fd_student_meals_school_detail_picture_image);
			schoolHolderView.schoolNameTextView = (TextView) convertView.findViewById(R.id.fd_student_meals_school_detail_name_label);
			schoolHolderView.schoolRegionTextView = (TextView) convertView.findViewById(R.id.fd_student_meals_school_detail_erea_text);
			schoolHolderView.schoolTypeTextView = (TextView) convertView.findViewById(R.id.fd_student_meals_school_detail_type_text);
			convertView.setTag(schoolHolderView);
		}else {
			schoolHolderView = (SchoolHolderView) convertView.getTag();
		}
		if(fdSchoolList!=null) {
			favoriteSchool = fdSchoolList.get(position);
			List<FDImage> picList = favoriteSchool.getAttachList();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, schoolHolderView.schoolImageView, picList.get(0).getFilePath());
			}
			schoolHolderView.schoolNameTextView.setText(favoriteSchool.getSchoolName());
			schoolHolderView.schoolRegionTextView.setText(favoriteSchool.getRegionValue());
			schoolHolderView.schoolTypeTextView.setText(favoriteSchool.getSchoolTypeValue());
		}
		return convertView;
	}
	
	static class SchoolHolderView {
		ImageView schoolImageView;
		TextView schoolNameTextView;
		TextView schoolRegionTextView;
		TextView schoolTypeTextView;
	}
}
