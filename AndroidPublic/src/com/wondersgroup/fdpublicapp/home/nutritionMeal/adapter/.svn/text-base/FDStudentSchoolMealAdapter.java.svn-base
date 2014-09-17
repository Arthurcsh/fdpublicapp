package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  营养餐次 ItemAdapter
 * @author chengshaohua
 *
 */
public class FDStudentSchoolMealAdapter extends BaseAdapter {
    private Context activity;
	private ArrayList<FDMealItem> mealItemList;
	
	public FDStudentSchoolMealAdapter(Context activity,ArrayList<FDMealItem> mealItemList) {
		super();
		this.activity = activity;
		this.mealItemList = mealItemList;
	}
	
	public int getCount() {
		if(mealItemList!=null) {
			return mealItemList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(mealItemList!=null) {
			return mealItemList.get(position);
		}
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup arg2) {
		FDMealItem mealItemMode = null;
		ViewHolder mealViewHolder;
		View view = convertView;
		if (convertView == null) {
			mealViewHolder = new ViewHolder();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_meals_listitem, null);
			mealViewHolder.mealNameText = (TextView) view.findViewById(R.id.school_meal_table_item_info_name);
			mealViewHolder.mealImageView = (ImageView) view.findViewById(R.id.school_meal_table_item_imageview);
			mealViewHolder.mealDescText = (TextView) view.findViewById(R.id.school_meal_table_item_desc_textview);
			mealViewHolder.mealCompanyText = (TextView) view.findViewById(R.id.school_meal_table_item_company_textview);
			
			view.setTag(mealViewHolder);
		} else {
			mealViewHolder = (ViewHolder) view.getTag();
		}
		if(mealItemList!=null) {
			mealItemMode = mealItemList.get(position);
			mealViewHolder.mealNameText.setText(mealItemMode.getName());
			mealViewHolder.mealDescText.setText(mealItemMode.getDescription());
			mealViewHolder.mealCompanyText.setText(mealItemMode.getCompanyName());
			ArrayList<FDImage> mealImages = mealItemMode.getPicList();
			String fileString = null;
			if(mealImages!=null && mealImages.size()>0) {
				FDImage mealImage = mealImages.get(0);
				fileString = mealImage.getFilePath();
			}
			FDViewUtil.showServerImage(activity, mealViewHolder.mealImageView, fileString);
		}
		
		return view;
	}

	static class ViewHolder {
		TextView mealNameText;
		ImageView mealImageView;
		TextView mealDescText;
		TextView mealCompanyText;
	}
}
