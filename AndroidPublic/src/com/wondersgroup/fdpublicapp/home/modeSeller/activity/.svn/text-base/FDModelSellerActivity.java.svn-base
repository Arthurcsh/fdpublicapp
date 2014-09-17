package com.wondersgroup.fdpublicapp.home.modeSeller.activity;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.modeSeller.adapter.FDModelSellerAdapter;
import com.wondersgroup.fdpublicapp.search.views.FDSearchTableListView;

/**
 * 示范商家
 * @author chengshaohua
 *
 */
public class FDModelSellerActivity extends FDBaseActivity {

	public LJListView xlistView;
	public FDModelSellerAdapter modelAdapter;
	public ArrayList<FDRestaurant> sellersList = new ArrayList<FDRestaurant>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_model_sellers_main);
		
		loadModelSellerData();
		initModelSellerView();
	}
	
	public void initModelSellerView() {
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.fd_model_sellers_back_label);
		LinearLayout modelSellerXml = (LinearLayout) this.findViewById(R.id.fd_model_sellers_xml);
		
		FDSearchTableListView modelResultTableView = new FDSearchTableListView(this);
		this.xlistView = (LJListView) modelResultTableView.getLJListView();
		modelSellerXml.addView(modelResultTableView);
		
		modelAdapter = new FDModelSellerAdapter(this,sellersList);         
		xlistView.setAdapter(modelAdapter);                                   
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDModelSellerActivity.this.finish();
			}
		});
	}
	
	public void loadModelSellerData() {
		for(int i=0;i<9;i++) {
			FDRestaurant restaurant = new FDRestaurant();
			restaurant.setId(5);
			restaurant.setName("避风塘(浦江店)");
			restaurant.setSupervisionOrg("上海安食办");
			restaurant.setFoodSaftyRating(21003);
			restaurant.setFoodSaftyRatingValue("一般");
			restaurant.setFoodSaftyRatingDate("2014-03-25");
			restaurant.setAverageComsumptionValue("160");
			restaurant.setBizAddress("闵行区浦江镇三鲁公路3089号");
			restaurant.setTelephone("021-63590688");
			sellersList.add(restaurant);
		}
	}
	
}
