package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.delicacy.adapter.FDRestDelicacyMainAdapter;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import com.wondersgroup.fdpublicapp.search.adapter.FDRestSeekResultMainAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * @author chengshaohua
 * @desc   餐厅查询列表
 */

public class FDSearchTableListView extends LinearLayout {
	private Context context;
	private BaseAdapter adapter;
	private LJListView listView;
	public List<FDRestaurant> inforList;	
	
	public FDSearchTableListView(Context context) {
		super(context);
		this.context = context;				
		
		initDetailsView();
	}
	
	/**
	 * 初始化餐厅列表视图
	 */
	public void initDetailsView() {
		View restaurantListView = LayoutInflater.from(context).inflate(R.layout.fd_common_pull_listview, null);
		addView(restaurantListView);
		
		listView = (LJListView) restaurantListView.findViewById(R.id.fd_common_xlistview_xml); 
		listView.setPullRefreshEnable(true);            // 设置下拉
		listView.setIsAnimation(true);                  // 设置动画
		listView.setPullLoadEnable(true, "......");     // 设置“查看更”
		
		adapter = new FDRestSeekResultMainAdapter(context,inforList);         // 适配adapter
		listView.setAdapter(adapter);                                         // setAdapter
		listView.setOnItemClickListener(new OnItemClickListener() {           // listview点击事件
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0) return;
				
				if(inforList!=null && position<=inforList.size()) {
					Intent intent = new Intent(context,FDSearchDetailsActivity.class);
					FDRestaurant restaurant = inforList.get(position-1);
					if(restaurant!=null) {
						intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurant.getId());
					}
					context.startActivity(intent);
				}
			}
		});
	}
	
	/**
	 *   更新餐厅信息
	 * @param restAllList
	 */
	public void setAllList(List<FDRestaurant> restAllList) {
		this.inforList = restAllList;
//		adapter = new FDRestSeekResultMainAdapter(context,inforList);
//		listView.setAdapter(adapter); 
		
		((FDRestSeekResultMainAdapter)adapter).setSearchTableData(inforList);
	}
	
	/**
	 *   更新当前活动、菜肴、优惠等信息
	 * @param restAllList  
	 */
	public void setDelicacyList(ArrayList<FDRestaurant> restAllList) {
		this.inforList = restAllList;  
//		adapter = new FDRestDelicacyMainAdapter(context,inforList);
//		listView.setAdapter(adapter); 
		((FDRestDelicacyMainAdapter)adapter).setSearchTableData(inforList);
	}
	
	public LJListView getLJListView() {
		return listView;
	}
}
