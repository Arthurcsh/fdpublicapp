package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDSpinnerAdapter;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.personal.activity.FDDishDetailActivity;
import com.wondersgroup.fdpublicapp.search.adapter.FDMenuDetailsAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/** 
 *  餐厅菜单
 * @author chengshaohua
 *
 */
public class FDSearchMenuDetailsActivity extends FDBaseActivity implements OnClickListener{
	private GridView menuGrid;
	private LinearLayout backLl;
	private LinearLayout sortLl;
	private PopupWindow popSpinnerListWin;      //选项列表弹出窗口
	private FDSpinnerAdapter spinnerAdapter;
	private View popupWinTypeView;              //类型分类列表布局
	private ListView listView;
	private List<FDSuperMode> cuisineList;
	private FDRestaurant restaurantDetail;
	private FDMenuDetailsAdapter menuAdapter;
	private ArrayList<FDRestMenu> restaurantMenus = new ArrayList<FDRestMenu>();
	private TextView nameText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_restaurant_menu_details_xml);
		
		cuisineList = new ArrayList<FDSuperMode>();
		FDSuperMode unlimitedCuisine = new FDSuperMode();
		unlimitedCuisine.setCode(FDConst.UNLIMITED_CONDITION_KEY);
		unlimitedCuisine.setName(FDConst.UNLIMITED_CONDITION_VALUE);
		cuisineList.add(0, unlimitedCuisine);
		
		popupWinTypeView = LayoutInflater.from(this).inflate(R.layout.fd_restaurant_list_spinner, null);
		List<FDSuperMode> vegetableList = (List<FDSuperMode>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_VEGETABLE_TYPE);
		if (vegetableList != null && vegetableList.size() != 0) {
			cuisineList.addAll(vegetableList);
		}
		
		initMenuView();
		loadSearchMenuInfoList();
	}
	
	public void initMenuView() {
		nameText=(TextView) findViewById(R.id.menu_details_company_name_tv);
		restaurantDetail = getIntent().getParcelableExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		if(restaurantDetail!=null){
			nameText.setText(restaurantDetail.getShopSign());
		}
		listView = (ListView) popupWinTypeView.findViewById(R.id.lv_spinner_dish_type);
		listView.setCacheColorHint(Color.TRANSPARENT);
		backLl = (LinearLayout) findViewById(R.id.menu_details_back_ll);
		sortLl = (LinearLayout) findViewById(R.id.menu_sort_details_ll);
		backLl.setOnClickListener(this);
		sortLl.setOnClickListener(this);
		menuAdapter = new FDMenuDetailsAdapter(this, restaurantMenus);
		menuGrid = (GridView) findViewById(R.id.menu_details_gridview);
		menuGrid.setAdapter(menuAdapter);
		menuGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {      // 食材追溯
				FDRestMenu restMenu = restaurantMenus.get(position);
				Intent intent = new Intent(context,FDDishDetailActivity.class);
				if(restMenu!=null) {
					intent.putExtra("fd.dish.detail.key", restMenu.getId());
				}
				startActivity(intent);
			}
		});
	}
	
	public void onClick(View view) {
		if(view==backLl){
			finish();
		}else if(view==sortLl){
			int [] pos = new int[2]; 
			sortLl.getLocationOnScreen(pos);
			spinnerAdapter = new FDSpinnerAdapter(this, cuisineList);
			listView.setAdapter(spinnerAdapter);
			popSpinnerListWin = new PopupWindow(popupWinTypeView, sortLl.getWidth()+10,sortLl.getHeight()*cuisineList.size());		
			popSpinnerListWin.setFocusable(true);
			popSpinnerListWin.setBackgroundDrawable(new BitmapDrawable());
			popSpinnerListWin.setOutsideTouchable(true);
			popSpinnerListWin.showAsDropDown(sortLl, -8, 0);	
			onItemClickListener();
		}
	}
	
	private void onItemClickListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				spinnerAdapter.setSelectedItem(position);
				popSpinnerListWin.dismiss();
				popSpinnerListWin = null;
				String cuisineCode = cuisineList.get(position).getCode();
			}
		});
		
	}
	
	// 餐厅菜单列表
	public void loadSearchMenuInfoList() {
		Bundle bundle = getIntent().getExtras();
		if (bundle == null|| !bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) return;
		
		FDRestaurant restaurant = bundle.getParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		if (restaurant == null) return;

		FDSearchWrapper searchWrapper = new FDSearchWrapper(this);
		searchWrapper.getRestaurantMenus(restaurant.getId(), new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDRestMenu> menuPage = (FDResultPage<FDRestMenu>) callback;
				if(restaurantMenus!=null) {
					restaurantMenus = (ArrayList<FDRestMenu>) menuPage.getResultList();
					menuAdapter = new FDMenuDetailsAdapter(context, restaurantMenus);
					menuGrid.setAdapter(menuAdapter);
					menuAdapter.notifyDataSetChanged();
				}
			}
		});
	}
}
