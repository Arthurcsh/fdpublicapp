package com.wondersgroup.fdpublicapp.home.delicacy.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonTraceGroup;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealTrace;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.search.adapter.FDMenuDetailsTraceListAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  菜单明细成分 (食材追溯)
 */
public class FDRestMenuDetailsTraceListActivity extends FDBaseActivity {
	private ListView mainListView;
	private LinearLayout backLayout;
	private RelativeLayout recommendLayout;
	private RelativeLayout favoriteLayout;
	private ImageView recommendImageView;
	private ImageView favoriteImageView;
	private TextView menuHeaderTextView;
    private FDMenuDetailsTraceListAdapter adapter;
    private FDRestMenu restaurantMenu = new FDRestMenu();
    private LinearLayout imageViewLayout;
	private ImageView dishImageView;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_restaurant_menu_details_photo_list);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle == null || !bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) return;
		restaurantMenu = bundle.getParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		
		initMenuTraceView();
		if(restaurantMenu!=null) {
			loadRestaurantMenuTrace(restaurantMenu.getMenuType());
		}
	}
	
	public void initMenuTraceView() {
		backLayout = (LinearLayout) findViewById(R.id.menu_photo_list_img_ll);
//		recommendLayout = (RelativeLayout) findViewById(R.id.fd_menu_photo_recommend_layout);
//		favoriteLayout = (RelativeLayout) findViewById(R.id.fd_menu_photo_favorite_layout);
//		recommendImageView = (ImageView) findViewById(R.id.fd_menu_photo_support_img);
//		favoriteImageView = (ImageView) findViewById(R.id.fd_menu_photo_favorite_img);
		menuHeaderTextView = (TextView) findViewById(R.id.menu_photo_list_name_tv);
		imageViewLayout = (LinearLayout) findViewById(R.id.fd_trace_viewpage_layout);
		dishImageView = (ImageView) findViewById(R.id.fd_trace_viewpage_dish_img);
		mainListView = (ListView) findViewById(R.id.menu_photo_list_details_listview);
		
		if(restaurantMenu!=null) {
			if(!StringUtils.isEmpty(restaurantMenu.getName())) {
				menuHeaderTextView.setText(restaurantMenu.getName());
			}
		}
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				FDRestMenuDetailsTraceListActivity.this.finish();
			}
		});
		if(restaurantMenu!=null) {
			imageViewLayout.setVisibility(restaurantMenu.getMenuType()==0?View.VISIBLE:View.GONE);
			if(restaurantMenu.getPicList()!=null && restaurantMenu.getPicList().size()>0) {
				FDViewUtil.showServerImage(this, dishImageView, restaurantMenu.getPicList().get(0).getFilePath());
			}
		}
	}
	
	/**
	 * 加载食材追溯信息
	 */
	public void loadRestaurantMenuTrace(int traceType) {
		if(restaurantMenu==null) return;
		
		final FDCommonTraceGroup commonTraceGroup = new FDCommonTraceGroup();
		commonTraceGroup.setTraceType(restaurantMenu.getMenuType());
		
		if(traceType==1) {
			FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
			nutritionMealWrapper.getSchoolNutritionMealTraceInfo(restaurantMenu.getOutputMatId(), restaurantMenu.getOutputDate(), new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					
					ArrayList<FDMealTrace> mealTraceList = (ArrayList<FDMealTrace>) callback;
					if(mealTraceList!=null) {
						List<FDIngredientGroup> menuIngredientList = FDSearchWrapper.getMenuIngredientList(mealTraceList);
						commonTraceGroup.setIngredientGroupsList(menuIngredientList);
						adapter = new FDMenuDetailsTraceListAdapter(context, commonTraceGroup);
						mainListView.setAdapter(adapter);	
					}
				}
			});
		} else {
			FDSearchWrapper searchWrapper = new FDSearchWrapper(this);
			searchWrapper.getRestaurantMenusTrace(restaurantMenu.getId(), new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					
					List<FDMealTrace> ingredientsTraceList = (List<FDMealTrace>) callback;
					if(ingredientsTraceList!=null) {
						List<FDIngredientGroup> menuIngredientList = FDSearchWrapper.getMenuIngredientList(ingredientsTraceList);
						commonTraceGroup.setIngredientGroupsList(menuIngredientList);
						adapter = new FDMenuDetailsTraceListAdapter(context, commonTraceGroup);
						mainListView.setAdapter(adapter);	
					}
				}
			});
		}
		
	}
}
