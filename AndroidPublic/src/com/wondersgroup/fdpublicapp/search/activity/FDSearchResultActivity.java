package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import com.amap.api.location.AMapLocation;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDFragmentAdapter;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 餐厅查询
 * @author chengshaohua
 *
 */
public class FDSearchResultActivity extends FDBaseActivity implements OnPageChangeListener{
	private int position;
	private FDCustomViewPager viewPager;
	private FDFragmentAdapter reataurantAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_search_view);
		
		initSearchResultView();
		
//		AppCrashHandler crashHandler = AppCrashHandler.getInstance();
//		crashHandler.init(this);
		
		openMapLocation();
	} 

	/**
	 * 初始化全城查询、附件查询
	 */
	private void initSearchResultView() {
		this.viewPager = (FDCustomViewPager) this.findViewById(R.id.search_view_pager);
		View searchArea = this.findViewById(R.id.search_footbar_area);
		View searchNear = this.findViewById(R.id.search_footbar_near);
		searchArea.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_ALL_AREA));
		searchNear.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_NEAR_BY));
		
		ArrayList<FDSearchFragment> searchFramgentList = new ArrayList<FDSearchFragment>();
		searchFramgentList.add(new FDSearchAreaFragment());
		searchFramgentList.add(new FDSearchNearByFragment());
		this.reataurantAdapter = new FDFragmentAdapter(this,getSupportFragmentManager(), searchFramgentList);
		viewPager.setAdapter(reataurantAdapter);
		viewPager.setOnPageChangeListener(this);
		
		this.position =  getIntent().getIntExtra(FDConst.FD_QUERY_DETAIL_TYPE_ID, 0);
		this.viewPager.setCurrentItem(position);
		setViewPagerBar(position);
	}
	
	public void setViewPagerBar(int index) {
		RadioGroup viewPagerGroup = (RadioGroup) this.findViewById(R.id.fd_search_tabbar);
		RadioButton seleceedButton = (RadioButton) viewPagerGroup.getChildAt(index);
		seleceedButton.setChecked(true);
	}
	
	public class SearchOnClickListener implements View.OnClickListener {
		int clickIndex = 0;
        public SearchOnClickListener(int index) {
        	clickIndex = index;
        }

        public void onClick(View v) {
        	viewPager.setCurrentItem(clickIndex);
        }
    };
    
 // 切换不同的Page要重新加载数据。
  	public void onPageSelected(int index) {
  		
  	}
  	public void onPageScrollStateChanged(int arg0) {
  		
  	}
  	public void onPageScrolled(int arg0, float arg1, int arg2) {
  		
  	}
  	
  	/**
  	 *  启动地理定位
  	 */
  	public void openMapLocation() {
  		FDLocation location = (FDLocation) ServiceManager.get(FDConst.FD_SERVER_LOCATION_POINT);
	    if (location != null) return;
	    
	    initApplicationLocation();
  	}
  	
  	public void onLocationChanged(AMapLocation amapLocation) {
		String locationMessage;
		if(isFinishing()) return;
		
		if (amapLocation != null) {
			Bundle bundle = amapLocation.getExtras();
			FDLocation location = new FDLocation();
			location.setLatitude(amapLocation.getLatitude());
			location.setLongitude(amapLocation.getLongitude());
			locationMessage = ("定位成功:(" + location.getLongitude() + "," + location.getLatitude() + ")")
					+ "\n地址: "+ bundle.getString("desc");
			
			// 刷新距离
			ServiceManager.putValue(FDConst.FD_SERVER_LOCATION_POINT, location);
			initSearchResultView();
			
			stopLocation();
		}else {
			locationMessage = getResources().getString(R.string.app_start_location_failure);
		}
		FDViewUtil.showToast(this, locationMessage);
	}
  	
}
