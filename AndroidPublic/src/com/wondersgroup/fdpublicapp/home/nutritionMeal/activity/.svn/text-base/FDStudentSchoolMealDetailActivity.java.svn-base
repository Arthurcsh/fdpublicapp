package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.delicacy.activity.FDRestMenuDetailsTraceListActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDStudentMealCommentAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNutrition;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;

/**
 *  营养餐餐次菜肴单元详情
 * @author chengshaohua
 *
 */
public class FDStudentSchoolMealDetailActivity extends FDBaseActivity implements IXListViewListener{

	private FDMealInfo mealInfo;                    // 餐次详情
	private ArrayList<FDNutrition> nutritionList;   // 营养成分
	private ImageView mealImageView;
	private TextView titleTextView;
	private TextView nameTextView;
	private TextView descTextView;
	private TextView companyTextView;
	private TextView mainMatTextView;
	private ImageView commentImageView;
	private LJListView commentListView;
	private TextView commentCountTextView;
	private FDStudentMealCommentAdapter commentAdapter;
	private List<FDComment> commentList;
	private Handler mHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_meal_details_xml);
		
		initNutritionMealDetailView();
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("student.meal.key")) {
			int mealItemId = bundle.getInt("student.meal.key");
			
			loadNutritionMealDeatilData(mealItemId);
		}
	}
	
	/**
	 * 初始化营养餐次菜肴详情视图
	 */
	public void initNutritionMealDetailView() {
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.student_school_meal_details_back_img_layout);
		RelativeLayout retrospectLayout = (RelativeLayout) this.findViewById(R.id.student_school_meal_details_retrospect_layout);
		RelativeLayout nutritionLayout = (RelativeLayout) this.findViewById(R.id.student_school_meal_details_nutrition_ingredient_layout);
		mealImageView = (ImageView) this.findViewById(R.id.student_school_meal_details_photo_img);
		titleTextView = (TextView) this.findViewById(R.id.student_school_meal_details_name_header_title);
		commentCountTextView = (TextView) this.findViewById(R.id.student_school_meal_detail_comment_textview);
		nameTextView = (TextView) this.findViewById(R.id.student_school_meal_details_name_textview);
		descTextView = (TextView) this.findViewById(R.id.student_school_meal_details_desc_textview);
		companyTextView = (TextView) this.findViewById(R.id.student_school_meal_details_company_textview);
		mainMatTextView = (TextView) this.findViewById(R.id.student_school_meal_details_mainmat_textview);
		commentImageView = (ImageView) this.findViewById(R.id.student_school_meal_detail_comment_imageview);
		commentListView = (LJListView) this.findViewById(R.id.student_school_meal_detail_comment_xml);
		commentListView.setPullLoadEnable(true, "..");
		commentListView.setPullRefreshEnable(true);
		commentListView.setIsAnimation(true);
		commentListView.setXListViewListener(this);
		commentListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
				if(commentList==null || position==0) return;
				
				FDComment comment = commentList.get(position-1);      // 评论
				Intent intent = new Intent(context,FDStudentSchoolDishCommentActivity.class);
				intent.putExtra("fd.meal.comment.key", comment);
				startActivity(intent);
			}
		});
		
		retrospectLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				laodNutritionMealTraceData();
			}
		});
		nutritionLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				Intent intent = new Intent(context,FDStudentSchoolNutritionComponentActivity.class);
				if(mealInfo!=null) {
					intent.putExtra("student.meal.nutrition.key", mealInfo);
				}
				startActivity(intent);
			}
		});
		commentImageView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(mealInfo==null || mealInfo.getStuMealInfo()==null) return;
				
				Intent intent = new Intent(context,FDStudentSchoolCommentActivity.class);
				intent.putExtra("student.meal.comment.key", mealInfo.getStuMealInfo());
				startActivityForResult(intent,100);
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDStudentSchoolMealDetailActivity.this.finish();
			}
		});
	}
	
	/**
	 *  点评成功后返回更新
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if (resultCode != 20) return;
		
		pageNo = 1;
		loadNutritionMealComments();

		super.onActivityResult(requestCode, resultCode, data);  
	}
	
	public void setNutritionMealView() {
		if(mealInfo==null || mealInfo.getStuMealInfo()==null) return;
		
		FDMealItem mealItem = mealInfo.getStuMealInfo();
		titleTextView.setText(mealItem.getName());
		nameTextView.setText(mealItem.getName());
		descTextView.setText(mealItem.getDescription());
		companyTextView.setText(mealItem.getRestaurantNameAbbrev());
		mainMatTextView.setText(mealItem.getMainIngredients());
		ArrayList<FDImage> mealImages = mealItem.getPicList();
		if(mealImages!=null && mealImages.size()>0) {
			FDImage mealImage = mealImages.get(0);
			FDViewUtil.showServerImage(this, mealImageView, mealImage.getFilePath());
		}
	}
	
	/**
	 *  加载营养餐食材台账信息
	 */
	public void laodNutritionMealTraceData() {
		if(mealInfo==null) return;
		FDMealItem mealItem = mealInfo.getStuMealInfo();
		if(mealItem==null) return;
		
		if(StringUtils.isEmpty(mealItem.getOutputMatId())){
			FDViewUtil.showToast(context, "没有查到相关食材台账记录..",true);
			return;
		}
		FDRestMenu studMenu = new FDRestMenu();
		studMenu.setMenuType(1);                        // 食材追溯类型 1=营养餐
		studMenu.setOutputMatId(mealItem.getOutputMatId());
		studMenu.setName(mealItem.getName());
		studMenu.setOutputDate(StringUtils.dateToString(mealItem.getDate()));
		studMenu.setCusineType(mealItem.getType());
		studMenu.setCusineTypeValue(mealItem.getTypeValue());
		studMenu.setPicList(mealItem.getPicList());
		studMenu.setDescription(mealItem.getDescription());
		Intent intent = new Intent(context,FDRestMenuDetailsTraceListActivity.class);
		intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, studMenu);
		startActivity(intent);
	}
	
	/**
	 *  加载营养餐详情信息
	 *  mealId  餐次编号
	 */
	public void loadNutritionMealDeatilData(int mealId) {
		if(mealId<0) return;
		
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getSchoolNutritionMealDetailInfo(mealId, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				mealInfo = (FDMealInfo) callback;
				if(mealInfo!=null) {
					nutritionList = mealInfo.getNutritionList();
				}
				
				mHandler = new Handler();
				mHandler.post(new Runnable(){
					public void run() {
						setNutritionMealView();
						loadNutritionMealComments();
					}
				});
			}
		});
	}

	/**
	 * 点评信息
	 */
	public void loadNutritionMealComments() {
		if(mealInfo==null) return;
		
		FDMealItem mealItem = mealInfo.getStuMealInfo();
		if(mealItem==null) return;
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getNutritionMealComments(pageNo++,LOAD_MORE_MAX_COUNT,mealItem.getId(), new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDComment> resultPage = (FDResultPage<FDComment>) callback;
				initNutritionMealCommentView(resultPage);
			}
		});
	}
	
	public void initNutritionMealCommentView(FDResultPage<FDComment> resultPage) {
		if(resultPage==null) return;
		totalPage = (int) (1+resultPage.getTotalRecord()/LOAD_MORE_MAX_COUNT);
		
		commentCountTextView.setText("(共"+resultPage.getTotalRecord()+"条)");
		commentList = resultPage.getResultList();
		commentAdapter = new FDStudentMealCommentAdapter(this,commentList);
		commentListView.setAdapter(commentAdapter);
		
		onCommentLoad(resultPage.getTotalRecord());
	}
	
	public void onRefresh() {
		mHandler = new Handler();
		
		pageNo = 1;
		onLoadMore();
	}

	public void onLoadMore() {
		if(mealInfo==null) return;
		
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		
		mHandler.post(new Runnable(){
			public void run() {
				loadNutritionMealComments();
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count
	 */
	private void onCommentLoad(long count) {
		commentListView.setCount(""+count);
		commentListView.stopRefresh();
		commentListView.stopLoadMore(pageNo>totalPage);
		commentListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
}
