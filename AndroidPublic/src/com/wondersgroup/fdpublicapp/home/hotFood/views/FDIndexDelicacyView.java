package com.wondersgroup.fdpublicapp.home.hotFood.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.hotFood.service.FDHotFoodWrapper;
import com.wondersgroup.fdpublicapp.home.main.activity.FDMainActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import com.wondersgroup.fdpublicapp.search.adapter.FDRestSeekResultMainAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 热点美食--猜你想吃
 * @author chengshaohua
 *
 */
public class FDIndexDelicacyView extends LinearLayout implements IXListViewListener {

	public Context context;
	public LJListView hotFoodListView;
	public List<FDRestaurant> hotFoodRestaurant = new ArrayList<FDRestaurant>();
	public FDRestSeekResultMainAdapter hotFoodRestAdapter;
	public final static int LOAD_MORE_MAX_COUNT  = 8;
	public final static int HOT_FOOD_REST_DISTANCE = 6000;
	public FDLocation location;
	private Handler mHandler;
	public int pageNo = 1;
	public int totalPage;
	
	public FDIndexDelicacyView(Context context) {
		super(context);
		this.context = context;
		
		initIndexDelicacyListView();
	}
	
	public FDIndexDelicacyView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}
	
	public FDIndexDelicacyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        this.context = context;
        
		initIndexDelicacyListView();
	}
	
	/**
	 * 初始化热点餐厅信息列表
	 */
	public void initIndexDelicacyListView() {
		View hotFoodView = LayoutInflater.from(context).inflate(R.layout.fd_common_pull_listview, null);
		addView(hotFoodView);
		hotFoodListView = (LJListView) hotFoodView.findViewById(R.id.fd_common_xlistview_xml);
		hotFoodListView.setPullLoadEnable(true, "..");
		hotFoodListView.setPullRefreshEnable(true);
		hotFoodListView.setIsAnimation(true);
		hotFoodListView.setXListViewListener(this);
		hotFoodRestAdapter = new FDRestSeekResultMainAdapter(context, hotFoodRestaurant);
		hotFoodListView.setAdapter(hotFoodRestAdapter);
		hotFoodListView.setOnItemClickListener(new OnItemClickListener() {          
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0) return;
				
				if(hotFoodRestaurant!=null && position<=hotFoodRestaurant.size()) {
					Intent intent = new Intent(context,FDSearchDetailsActivity.class);
					FDRestaurant restaurant = hotFoodRestaurant.get(position-1);
					if(restaurant!=null) {
						intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurant.getId());
					}
					context.startActivity(intent);
				}
			}
		});
		
//		loadHotFoodRestNearByList();
	}

	public void setSearchLocationCondition(FDLocation location) {
		if(location==null) return;
		this.location = location;
		
		loadHotFoodRestNearByList(location.getLatitude(), location.getLongitude());
	}
	
	/** 加载热点附件美食餐厅 **/
	public void loadHotFoodRestNearByList(double latitude, double longitude) {
		FDHotFoodWrapper hotFoodWrapper = new FDHotFoodWrapper(context);
		hotFoodWrapper.getHotFoods(HOT_FOOD_REST_DISTANCE, latitude, longitude, pageNo, LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDRestaurant> hotFoodRestPage = (FDResultPage<FDRestaurant>) callback;
				if(hotFoodRestPage!=null) {
					long totalRecord = 0;
					totalPage = hotFoodRestPage.getPageCount();
					List<FDRestaurant> resultRestList = hotFoodRestPage.getResultList();
					if(resultRestList==null || pageNo==1) {
						hotFoodRestaurant.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						hotFoodRestaurant.addAll(resultRestList);
						totalRecord = hotFoodRestPage.getTotalRecord();
					}
					hotFoodRestAdapter.notifyDataSetChanged();
					onCommentLoad(totalRecord);
				}
			}
		});
	}
	

	public void onRefresh() {
		if(location==null) {
			context.sendBroadcast(new Intent(FDMainActivity.FD_MAIN_LOCATION));
			return;
		}
		
		mHandler = new Handler();
		this.pageNo = 1;
		mHandler.post(new Runnable(){
			public void run() {
				loadHotFoodRestNearByList(location.getLatitude(), location.getLongitude());
			}
		});
	}

	public void onLoadMore() {
		if(location==null) {
			context.sendBroadcast(new Intent(FDMainActivity.FD_MAIN_LOCATION));
			return;
		}
		
		pageNo++;
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		mHandler.post(new Runnable(){
			public void run() {
				loadHotFoodRestNearByList(location.getLatitude(), location.getLongitude());
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count
	 */
	private void onCommentLoad(long count) {
		hotFoodListView.setCount(""+count);
		hotFoodListView.stopRefresh();
		hotFoodListView.stopLoadMore(pageNo>totalPage);
		hotFoodListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
}
