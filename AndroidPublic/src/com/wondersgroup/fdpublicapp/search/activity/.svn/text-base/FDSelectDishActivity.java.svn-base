package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.ILoadingLayout;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshGridView;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshBase.OnRefreshListener2;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.search.adapter.FDRestDishSelectAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDDish;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 * 菜肴选择
 * @author chengshaohua
 *
 */
public class FDSelectDishActivity extends FDBaseActivity {
	public static final String FD_DISH_SELECTED = "fd.dish.selected";
	private int restaurantId;
	private List<FDDish> dishList = new ArrayList<FDDish>();
	private FDRestDishSelectAdapter dishAdapter;
	private PullToRefreshGridView dishSelectRefreshGridView;
	private FDResultPage<FDDish> dishPage;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_rest_seek_cuisine_gridview);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey("fd.rest.select.cuisine")) {
			restaurantId = bundle.getInt("fd.rest.select.cuisine");
		}
		
		initSelectDishView();
//		initIndicator();
	}
	
	/**
	 * 初始化菜肴选择
	 */
	private void initSelectDishView() {
		LinearLayout backLayout = (LinearLayout) findViewById(R.id.fd_rest_select_cuisine_back_layout);
		Button confirmButton = (Button) findViewById(R.id.fd_rest_select_cuisine_submit_button);
		dishSelectRefreshGridView = (PullToRefreshGridView) findViewById(R.id.fd_rest_pull_refresh_grid);
		dishAdapter = new FDRestDishSelectAdapter(this,dishList);
		dishSelectRefreshGridView.setAdapter(dishAdapter);
//		dishSelectRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {
//					public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
//						String label = DateUtils.formatDateTime(
//								getApplicationContext(),
//								System.currentTimeMillis(),
//								DateUtils.FORMAT_SHOW_TIME
//										| DateUtils.FORMAT_SHOW_DATE
//										| DateUtils.FORMAT_ABBREV_ALL);
//
//						refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//						new GetDataTask().execute();
//					}
//
//					public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
//						new GetDataTask().execute();
//					}
//				});

		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDSelectDishActivity.this.finish();
			}
		});
		confirmButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(dishList.size()>0) {
					StringBuilder dishBuilder = new StringBuilder();
					for(int i=0;i<dishList.size();i++) {
						FDDish dish = dishList.get(i);
						if(dish==null || !dish.isSelected()) continue;
						if(!StringUtils.isEmpty(dish.getCuisineId())) {
							dishBuilder.append(dish.getCuisineId()+",");
						}
					}
					if(dishBuilder.length()>0) {
						dishBuilder.deleteCharAt(dishBuilder.length()-1);
					}
					
					Intent intent = new Intent();
					intent.putExtra(FD_DISH_SELECTED, dishBuilder.toString());
					setResult(203, intent);  
				    finish();  
				}
			}
		});
		FDSearchWrapper searchWrapper = new FDSearchWrapper(this);
		searchWrapper.getDishWithRestaurant(restaurantId, -1, -1, new FDCallback() {
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				dishPage = (FDResultPage<FDDish>) callback;
				if(dishPage.getResultList()!=null) {
					dishList.addAll(dishPage.getResultList());
					dishAdapter.notifyDataSetChanged();
					dishSelectRefreshGridView.onRefreshComplete();
				}
				
			}
		});
	}
	
	private void initIndicator() {
		ILoadingLayout startLabels = dishSelectRefreshGridView.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("你可劲拉，拉...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("好嘞，正在刷新...");// 刷新时
		startLabels.setReleaseLabel("你敢放，我就敢刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = dishSelectRefreshGridView.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("你可劲拉，拉2...");// 刚下拉时，显示的提示
		endLabels.setRefreshingLabel("好嘞，正在刷新2...");// 刷新时
		endLabels.setReleaseLabel("你敢放，我就敢刷新2...");// 下来达到一定距离时，显示的提示
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, FDResultPage<FDDish>> {
		protected FDResultPage<FDDish> doInBackground(Void... params) {
			FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
			searchWrapper.getDishWithRestaurant(restaurantId, -1, -1, new FDCallback() {
				public void onCallback(Object callback) {
					if(callback==null) return;
					dishPage = (FDResultPage<FDDish>) callback;
				}
			});
			return dishPage;
		}

		protected void onPostExecute(FDResultPage<FDDish> restaurantPage) {
			if (restaurantPage == null) return;
			dishList.addAll(restaurantPage.getResultList());

			dishAdapter.notifyDataSetChanged();
			dishSelectRefreshGridView.onRefreshComplete();
		}
	}

}
