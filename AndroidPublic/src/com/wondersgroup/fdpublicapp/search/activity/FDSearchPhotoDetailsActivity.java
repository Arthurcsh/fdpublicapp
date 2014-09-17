package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.adapter.PhotoDetailsAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  餐厅图片展
 * @author chengshaohua
 *
 */
public class FDSearchPhotoDetailsActivity extends FDBaseActivity implements OnClickListener{
	private GridView photoGrid;
	private PhotoDetailsAdapter adapter;
	private LinearLayout backLl;
	private FDRestaurant restaurantDetail;
	private TextView nameText;
	private String nameValue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_restaurant_photo_details_xml);
		
		initMenuPhotoData();
		
		loadSearchNearByList();
	}
	
	private void initMenuPhotoData() {
		nameText = (TextView) findViewById(R.id.photo_details_company_name_tv);
		backLl = (LinearLayout) findViewById(R.id.photo_details_back_ll);
		backLl.setOnClickListener(this);
		photoGrid = (GridView) findViewById(R.id.photo_details_gridview);
		restaurantDetail = getIntent().getParcelableExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		ArrayList<FDImage> picList = (ArrayList<FDImage>) getIntent().getSerializableExtra("picListValue");	
		if (picList != null && picList.size() != 0) {
			adapter = new PhotoDetailsAdapter(this, picList);
			photoGrid.setAdapter(adapter);
		} else {
			Toast.makeText(this, "没有照片可以显示", Toast.LENGTH_SHORT).show();
		}
		if (restaurantDetail != null) {
			nameValue = restaurantDetail.getShopSign();
			nameText.setText(nameValue);
		}
	}
	
	// 餐厅附件列表
	public void loadSearchNearByList() {
		Bundle bundle = getIntent().getExtras();
		if (bundle == null || !bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) return;
		FDRestaurant restaurant = bundle.getParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		if(restaurant==null) return;
		
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_ATTACHMENT) + restaurant.getId();
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(40000);
		asynchClient.get(url, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				System.out.println("Http success...." + responseString);
				if (responseString == null)
					return;

			}

			public void onStart() {
				System.out.println("Http start...");
			}

			public void onFinish() {
			}

			public void onFailure(Throwable throwable, String content) {
				System.out.println("Http onFailure..." + throwable.getStackTrace());
			}
		});
	}
		
	public void onClick(View view) {
		if(view==backLl){
			finish();
		}
	}
}
