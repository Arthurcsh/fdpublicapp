package com.wondersgroup.fdpublicapp.home.delicacy.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonTraceItem;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.advert.CircleFlowIndicator;
import com.wondersgroup.fdpublicapp.home.advert.FDViewPagerAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredients;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Administrator
 *  菜单明细
 */

public class FDMenuViewPageView extends LinearLayout {
	private LayoutInflater flater;
	private Activity context;
	private ViewPager mViewPager;
	private LinearLayout mainViewLayout;       // 该应用的主布局LinearLayout
	private CircleFlowIndicator moreCircle;    // 主布局底部指示当前页面的小圆点视图
	public FDCommonTraceItem ingredientList;

	public FDMenuViewPageView(Context context, FDCommonTraceItem ingredientsItem) {
		super(context);
		this.context = (Activity) context;
		this.ingredientList = ingredientsItem;
		
		initViewPage();
	}

	private void initViewPage() {
		flater = LayoutInflater.from(context);
		mainViewLayout = (LinearLayout) flater.inflate(R.layout.fd_restaurant_menu_details_photo_listitem, null);
		mViewPager = (ViewPager) mainViewLayout.findViewById(R.id.myviewpager);
		moreCircle = (CircleFlowIndicator) mainViewLayout.findViewById(R.id.food_menu_ingredient_indicator); 
		this.addView(mainViewLayout);
		
		ArrayList<View> mPageViews = new ArrayList<View>();		
		if(ingredientList!=null && ingredientList.getIngredientList()!=null) {
			for(int i=0;i<ingredientList.getIngredientList().size();i++) {
				FDIngredients ingredient = ingredientList.getIngredientList().get(i);
				if(ingredient==null) continue;
				
				View itemView = flater.inflate(R.layout.fd_restaurant_menu_details_viewpage_item, null);
				LinearLayout imageLayout = (LinearLayout) itemView.findViewById(R.id.viewpage_item_img_layout);
				ImageView menuImage = (ImageView) itemView.findViewById(R.id.viewpage_item_img);
				TextView menuNameText = (TextView) itemView.findViewById(R.id.viewpage_item_name_tv);
				TextView menuNumberText = (TextView) itemView.findViewById(R.id.viewpage_item_number_tv);
				TextView menuStockDateText = (TextView) itemView.findViewById(R.id.viewpage_item_stock_date_tv);
				TextView menuExpirationDateText = (TextView) itemView.findViewById(R.id.viewpage_item_expiration_date_tv);
				TextView menuNormsText = (TextView) itemView.findViewById(R.id.viewpage_item_norms_tv);
				TextView menuProductText = (TextView) itemView.findViewById(R.id.viewpage_item_producte_tv);
				menuNameText.setText(ingredient.getName());
				menuNumberText.setText(ingredient.getWeight()+"g");
				menuStockDateText.setText(ingredient.getPurchaseDate());
				menuExpirationDateText.setText(ingredient.getGuaranteeDate());
				menuNormsText.setText(ingredient.getSpecifications());
				menuProductText.setText(ingredient.getManufacturer());
				
				/** 菜肴追溯显示图片，营养餐隐藏图片 **/
				if(StringUtils.isEmpty(ingredient.getImageFile())) {
					imageLayout.setVisibility(View.GONE);
				}else {
					imageLayout.setVisibility(View.VISIBLE);
					FDViewUtil.showServerImage(context, menuImage, ingredient.getImageFile());
				}
				if(ingredientList.getTraceType()==0) {
					menuNumberText.setVisibility(View.INVISIBLE);
				}else {
					menuNumberText.setVisibility(View.VISIBLE);
				}
				mPageViews.add(itemView);
			}
		}
		
		FDViewPagerAdapter viewPagerAdapter = new FDViewPagerAdapter(mPageViews);
		mViewPager.setAdapter(viewPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				moreCircle.selectedCircle(position);
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		moreCircle.addCircle(mPageViews.size());
		moreCircle.setViewPager(mViewPager);
	}

}
