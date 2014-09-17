package com.wondersgroup.fdpublicapp.home.delicacy.views;

import java.util.ArrayList;
import org.apache.http.Header;
import org.json.JSONException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.protocol.AbstractLoadServer;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurantTable;
import com.wondersgroup.fdpublicapp.search.views.FDSearchTableListView;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FDIndexDelicacyView extends LinearLayout {

	public Context context;
	public LJListView xlistView;
	public FDSearchTableListView searchResultTableView;
	public RequestParams searchRequestParams = new RequestParams();
	public ArrayList<FDRestaurant> currentRestaurant = new ArrayList<FDRestaurant>();
	public int searchIndex = 1;
	public int pageCount;
	
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
	 * 初始化餐厅信息列表
	 */
	public void initIndexDelicacyListView() {
		this.searchResultTableView = new FDSearchTableListView(context);
		this.xlistView = (LJListView) searchResultTableView.getLJListView();
		
		addView(searchResultTableView);
		setSearchLocationCondition();
		loadSearcNearByhDetailList(1);
		xlistView.setVisibility(View.INVISIBLE);
	}

	public void setSearchLocationCondition() {
		
	}
	
	/** 加载附件餐厅 **/
	public void loadSearcNearByhDetailList(int count) {
		
	}
	
	public void updateRestaurantTable(ArrayList<FDRestaurant> searchAllList) {
		
	}
	
}
