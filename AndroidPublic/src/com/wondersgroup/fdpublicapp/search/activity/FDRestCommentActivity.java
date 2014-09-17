package com.wondersgroup.fdpublicapp.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDTakePhotoView;
import com.wondersgroup.fdpublicapp.common.custom.FDTakePhotoView.FDCommentPhotoListener;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentSchoolCommentActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.search.mode.FDCommentRestaurant;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 * 餐厅点评
 * @author chengshaohua
 *
 */
public class FDRestCommentActivity extends FDBaseActivity implements FDCommentPhotoListener {
	private FDRestaurant restaurant;
	private FDCommentRestaurant commentRestaurant = new FDCommentRestaurant();
//	private String dishSelected;
	private LinearLayout backLayout;
	private TextView restNameTextView;
	private RatingBar freshRatingView;
	private RatingBar transparencyRatingView;
	private RatingBar flavorRatingView;
	private RatingBar serverRatingView;
	private RatingBar priceRatingView;
	private EditText commentEditText;
	private TextView leaveTextView;
	private TextView comsumptionTextView;
	private TextView selectDishTextView;
	private FDTakePhotoView takePhotoView;
	private LinearLayout addPhotoLayout;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_restaurant_comment_view);		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) {
			restaurant = bundle.getParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		}
		
		initRestaurantCommentView();
	}
	
	/**
	 * 初始化餐厅点评视图
	 */
	public void initRestaurantCommentView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_restaurant_comment_back_layout);
		commentEditText = (EditText) findViewById(R.id.fd_restaurant_comment_textveiw);
		leaveTextView = (TextView) findViewById(R.id.fd_restaurant_comment_leave_textview);
		restNameTextView = (TextView) findViewById(R.id.fd_restaurant_comment_name_textview);
		selectDishTextView = (TextView) findViewById(R.id.fd_restaurant_comment_dish_select_textview);
		comsumptionTextView = (TextView) findViewById(R.id.fd_restaurant_comment_average_textview);
		freshRatingView = (RatingBar) findViewById(R.id.fd_rest_comments_fresh_rating);
		transparencyRatingView = (RatingBar) findViewById(R.id.fd_rest_comments_transparency_rating);
		flavorRatingView = (RatingBar) findViewById(R.id.fd_rest_comments_flavor_rating);
		serverRatingView = (RatingBar) findViewById(R.id.fd_rest_comments_server_rating);
		priceRatingView = (RatingBar) findViewById(R.id.fd_rest_comments_price_rating);
		takePhotoView = (FDTakePhotoView) findViewById(R.id.fd_restaurant_comment_takePhoto_view);
		addPhotoLayout = (LinearLayout) findViewById(R.id.fd_restaurant_comment_add_photo_layout);
		takePhotoView.setTakeAddPhotoLayout(addPhotoLayout);
		takePhotoView.setCommentPhotoListener(this);
		commentEditText.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				FDStudentSchoolCommentActivity.filterCommentEditText(commentEditText, leaveTextView);
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDRestCommentActivity.this.finish();
			}
		});
		selectDishTextView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				Intent intent = new Intent(context,FDSelectDishActivity.class);
				if(restaurant!=null) {
					intent.putExtra("fd.rest.select.cuisine", restaurant.getId());
				}
				
				startActivityForResult(intent, 100);
			}
		});
		comsumptionTextView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDInputPopupWindows inputPopupWindow = new FDInputPopupWindows(context, comsumptionTextView,new FDCallback(){
					public void onCallback(Object callback) {
						if(callback==null) return;
						int averageComsumption = Integer.parseInt((String) callback);
						commentRestaurant.setAverageComsumption(averageComsumption);
					}
				});
			}
		});
		
		setCommentRestaurant();
	}
	
	public void setCommentRestaurant() {
		if(restaurant==null) return;
		restNameTextView.setText(restaurant.getShopSign());
	}
	
	/**
	 * 餐厅点评内容
	 * @param restComment
	 */
	public void submitCommentRestaurant(FDCommonComment restComment) {
		String restCommentData = commentEditText.getText().toString().trim();
		FDNoteComment comment = new FDNoteComment();
		if(!StringUtils.isEmpty(restCommentData)) {
			comment.setContentTextData(restCommentData);
			comment.setPicList(restComment.getCommentImages());
			commentRestaurant.setCompanyId(restaurant.getId());
			commentRestaurant.setContent(comment);
		}else {
			FDViewUtil.showToast(context, "请输入餐厅点评内容..", true);
			return;
		}
		commentRestaurant.setFoodFreshLvl((int) (freshRatingView.getRating()*2));
		commentRestaurant.setInfoTranspLvl((int) (transparencyRatingView.getRating()*2));
		commentRestaurant.setTasteLvl((int) (flavorRatingView.getRating()*2));
		commentRestaurant.setServiceLvl((int) (serverRatingView.getRating()*2));
		commentRestaurant.setPriceLvl((int) (priceRatingView.getRating()*2));
		
		FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
		searchWrapper.setCommentRestaurant(commentRestaurant, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				int status = (Integer) callback;
				if(status==0) {
					finish();
				}
			}
		});
	}

	/**
	 * 该方法一定要实现
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		takePhotoView.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==203) {
			String dishSelected = data.getExtras().getString(FDSelectDishActivity.FD_DISH_SELECTED);
			if(!StringUtils.isEmpty(dishSelected)) {
				commentRestaurant.setRecommendCuisineIds(dishSelected);
			}
		}
	}
	
	public void commentCallback(FDCommonComment commentCallback) {
		if(commentCallback==null) return;
		
		submitCommentRestaurant(commentCallback);
	}
}
