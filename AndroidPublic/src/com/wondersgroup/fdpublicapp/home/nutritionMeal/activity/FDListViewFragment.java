package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.search.activity.FDBaseFragment;

/**
 * 数据列表
 * @author chengshaohua
 *
 */
public abstract class FDListViewFragment extends FDBaseFragment implements IXListViewListener {
	protected int pageIndex = 1;
	protected int pageCount; 
	protected View fragmentView;
	protected RelativeLayout headerLayout;
	protected LinearLayout backLayout;
	protected TextView titleTextView;
	protected LJListView fdListView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initListView();
		
		return container;
	}	
	
	/**
	 *  初始化列表视图
	 */
	public void initListView() {
		fragmentView = LayoutInflater.from(context).inflate(R.layout.fd_common_pull_listview, null);
		headerLayout = (RelativeLayout) fragmentView.findViewById(R.id.fd_common_list_title_label);
		backLayout = (LinearLayout) fragmentView.findViewById(R.id.fd_common_list_back_layout);
		titleTextView = (TextView) fragmentView.findViewById(R.id.fd_common_list_title_textview);
		fdListView = (LJListView) fragmentView.findViewById(R.id.fd_common_xlistview_xml);
		fdListView.getListView().setSelector(R.color.grays);
		fdListView.setPullLoadEnable(true, "");
		fdListView.setPullRefreshEnable(true);
		fdListView.setIsAnimation(true);
		fdListView.setXListViewListener(this);
	}
	
	/**
	 * 查询分页
	 * @param page
	 */
	public void loadFragmentPageList(int page) {
		this.pageIndex = page;
		loadFragmentPageList();
	}
	
	public abstract void loadFragmentPageList();

	/**
	 * 下拉刷新
	 */
	public void onRefresh() {
		this.pageIndex = 1;
		handler.post(new Runnable(){
			public void run() {
				loadFragmentPageList();
			}
		});
	}

	/**
	 *  上拉加载更多
	 */
	public void onLoadMore() {
		pageIndex++;
		if(pageIndex>pageCount) {
			onListViewLoad(0);
			return;
		}
		handler.post(new Runnable(){
			public void run() {
				loadFragmentPageList();
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count  更新条数
	 */
	protected void onListViewLoad(long count) {
		fdListView.setCount(""+count);
		fdListView.stopRefresh();
		fdListView.stopLoadMore(pageIndex>pageCount);
		fdListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
	
	protected void setListViewTitle(String title) {
		if(title==null) return;
		
		headerLayout.setVisibility(View.VISIBLE);
		titleTextView.setText(title);
	}
}
