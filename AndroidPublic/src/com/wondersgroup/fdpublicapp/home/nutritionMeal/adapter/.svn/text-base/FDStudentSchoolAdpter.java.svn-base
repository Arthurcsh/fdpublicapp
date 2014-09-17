package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;

/**
 *  营养餐学校查询Adapter
 * @author chengshaohua
 *
 */
public class FDStudentSchoolAdpter extends BaseAdapter{
	private Context activity;
	private List<FDSchool> fdSchoolList;
	
	public FDStudentSchoolAdpter(Context activity, List<FDSchool> schoolTypeList) {
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
		LayoutInflater layoutInflater = LayoutInflater.from(activity);
		convertView = layoutInflater.inflate(R.layout.fd_student_school_list_item, null);
		
		TextView nameTextView = (TextView) convertView.findViewById(R.id.fd_student_text_list_tv);
		if(fdSchoolList!=null && fdSchoolList.size()>0) {
			nameTextView.setText(fdSchoolList.get(position).getSchoolName());
		}
		return convertView;
	}
}
