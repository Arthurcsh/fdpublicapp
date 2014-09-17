package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;

import com.wondersgroup.fdpublicapp.common.mode.FDCommonTraceGroup;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonTraceItem;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDMenuViewPageView;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredients;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author chengshaohua
 *  菜单明细成分追溯 Adapter
 */
public class FDMenuDetailsTraceListAdapter extends BaseAdapter{
	private Context activity;
	private FDCommonTraceGroup ingredientGroup;
	
	public FDMenuDetailsTraceListAdapter(Context activity, FDCommonTraceGroup ingredientTraceGroup) {
		super();
		
		this.activity = activity;
        this.ingredientGroup = ingredientTraceGroup;
	}
	
	public int getCount() {
		if(ingredientGroup!=null && ingredientGroup.getIngredientGroupsList()!=null) {
			return ingredientGroup.getIngredientGroupsList().size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(ingredientGroup!=null && ingredientGroup.getIngredientGroupsList()!=null) {
			return ingredientGroup.getIngredientGroupsList().get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDIngredientGroup ingredientGroupItem = null;
		if(convertView==null && ingredientGroup.getIngredientGroupsList()!=null){
			ingredientGroupItem = ingredientGroup.getIngredientGroupsList().get(position);
			if(ingredientGroupItem!=null) {
				List<FDIngredients> ingredientItemList = ingredientGroupItem.getIngredients();
				FDCommonTraceItem commentTraceItem = new FDCommonTraceItem();
				commentTraceItem.setTraceType(ingredientGroup.getTraceType());
				commentTraceItem.setIngredientList(ingredientItemList);
				convertView = new FDMenuViewPageView(activity,commentTraceItem);
			}
			
			convertView.setTag(ingredientGroupItem);
		}else{
			ingredientGroupItem = (FDIngredientGroup) convertView.getTag();
		}
		
		return convertView;
	}
	
}
