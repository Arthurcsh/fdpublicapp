package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCategory;

/**
 *  营养餐学校类型查询Adapter
 * @author chengshaohua
 *
 */
public class FDStudentSchoolCategoryAdpter extends BaseAdapter{
	private Context activity;
	private ArrayList<FDCategory> fdCategoryList;
	
	public FDStudentSchoolCategoryAdpter(Context activity, ArrayList<FDCategory> schoolTypeList) {
		super();
		this.activity = activity;
		this.fdCategoryList = schoolTypeList;
	}

	public int getCount() {
			return fdCategoryList==null?0:fdCategoryList.size();
	}

	public Object getItem(int position) {
		return fdCategoryList==null||fdCategoryList.size()==0?null:fdCategoryList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = LayoutInflater.from(activity);
		convertView = layoutInflater.inflate(R.layout.fd_student_school_type_item, null);
		
		TextView nameTextView = (TextView) convertView.findViewById(R.id.fd_student_text_list_tv);
		if(fdCategoryList!=null && fdCategoryList.size()>0) {
			nameTextView.setText(fdCategoryList.get(position).getName());
		}
		return convertView;
	}
}
