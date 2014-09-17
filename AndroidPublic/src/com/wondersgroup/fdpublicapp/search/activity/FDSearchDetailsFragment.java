package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.coupon.activity.FDCouponActivity;
import com.wondersgroup.fdpublicapp.home.delicacy.activity.FDRestaurantTrafficActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  餐厅查询详情
 * @author chengshaohua
 *
 */
public class FDSearchDetailsFragment extends FDBaseFragment implements OnClickListener {
	private int restaurantId;
	private FDCallback backListener;
	private View searchDetailView;
	private LinearLayout backImgLl;
	private RelativeLayout recommendLayout;
	private RelativeLayout favoriteLayout;
	private RelativeLayout details_phone_layout;
	private RelativeLayout couponInfoLayout;
	private RelativeLayout leaveMessageLayout;
	private LinearLayout details_comment_layout;
	private ImageView photoImg, menuImg, rest_details_credit_img;
	private ImageView recommendImageView;
	private ImageView favoriteImageView;
	private TextView restNameText, creditText, companyNameText, companyDateText;
	private TextView expenseText, collectCountText, recommendCountText;
	private TextView addressText, phoneText;
	private TextView couponInfoText;
	private TextView recommendCuisineText;
	private TextView commentNumberText;
	private FDRestaurant restaurantDetail;
	private TextView commentNameTextView;
	private TextView averageTextView;
	private TextView commentDataTextView;
	private RatingBar commentRatingBarView;
	private TextView commentDateTextView;
	private ArrayList<FDImage> picList;
	private long totalRecord;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		searchDetailView = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_details_xml, null);
		
		initSearchDetailView();
		loadSearchDetailData();
		loadSearchDetailCommentsData();
		
		return searchDetailView;
	}	
	
	public FDSearchDetailsFragment() {
	}
	
	public FDSearchDetailsFragment(FDCallback backListener, int restId) {
		this.backListener = backListener;
		this.restaurantId = restId;
	}
	
	public static FDSearchDetailsFragment getInstanceFragment(int restId){
		FDSearchDetailsFragment searchDetailFragment = new FDSearchDetailsFragment();
		searchDetailFragment.restaurantId = restId;
        return searchDetailFragment;
    }

	/**
	 * 初始化餐厅详情
	 */
	private void initSearchDetailView() {
		rest_details_credit_img = (ImageView) searchDetailView.findViewById(R.id.rest_details_credit_img);               //笑脸
		backImgLl = (LinearLayout) searchDetailView.findViewById(R.id.rest_details_back_img_ll);                         // 返回按钮
		recommendLayout = (RelativeLayout) searchDetailView.findViewById(R.id.fd_search_details_recommend_layout);
		favoriteLayout = (RelativeLayout) searchDetailView.findViewById(R.id.fd_search_details_favorite_layout);
		couponInfoLayout = (RelativeLayout) searchDetailView.findViewById(R.id.rest_details_coupon_layout);
		leaveMessageLayout = (RelativeLayout) searchDetailView.findViewById(R.id.rest_details_leave_word_layout);
		couponInfoText = (TextView) searchDetailView.findViewById(R.id.rest_details_special_offers_tv);
		recommendImageView = (ImageView) searchDetailView.findViewById(R.id.fd_search_details_support_img); 
		favoriteImageView = (ImageView) searchDetailView.findViewById(R.id.fd_search_details_favorite_img); 
		restNameText = (TextView) searchDetailView.findViewById(R.id.rest_details_name_tv);                               //店名
		photoImg = (ImageView) searchDetailView.findViewById(R.id.rest_details_photo_img);                                //照片
		menuImg = (ImageView) searchDetailView.findViewById(R.id.rest_details_menu_img);                                  //察看菜单
		creditText = (TextView) searchDetailView.findViewById(R.id.rest_details_credit_tv);                               //笑脸对应的值
		companyNameText = (TextView) searchDetailView.findViewById(R.id.rest_details_company_name_tv);                    //对应公司，店招
		companyDateText = (TextView) searchDetailView.findViewById(R.id.rest_details_company_date_tv);                    //日期
		expenseText = (TextView) searchDetailView.findViewById(R.id.rest_details_expense_tv);                             //人均消费
		collectCountText = (TextView) searchDetailView.findViewById(R.id.rest_details_collect_tv);                        //收藏数
		recommendCountText = (TextView) searchDetailView.findViewById(R.id.rest_details_recommend_tv);                    //推荐数
		addressText = (TextView) searchDetailView.findViewById(R.id.rest_details_address_tv);                             //地址
		phoneText = (TextView) searchDetailView.findViewById(R.id.rest_details_phone_tv);                                 //电话
		recommendCuisineText = (TextView) searchDetailView.findViewById(R.id.rest_details_recommend_cuisine_tv);          //网友推荐
		commentNumberText = (TextView) searchDetailView.findViewById(R.id.rest_details_comment_number_tv);                //点评总数
		commentDataTextView = (TextView) searchDetailView.findViewById(R.id.fd_rest_comments_content_textview);           //点评内容
		commentDateTextView = (TextView) searchDetailView.findViewById(R.id.fd_rest_comments_date_textview);              //点评日期
		commentNameTextView = (TextView) searchDetailView.findViewById(R.id.fd_rest_comments_name_textview);
		averageTextView = (TextView) searchDetailView.findViewById(R.id.fd_rest_comments_average_textview);
		commentRatingBarView = (RatingBar) searchDetailView.findViewById(R.id.fd_rest_comments_rating);
		details_phone_layout = (RelativeLayout) searchDetailView.findViewById(R.id.fd_rest_seek_details_phone_layout);
		details_comment_layout = (LinearLayout) searchDetailView.findViewById(R.id.rest_details_comment_layout);
		
		backImgLl.setOnClickListener(this);
		recommendLayout.setOnClickListener(this);
		favoriteLayout.setOnClickListener(this);
		couponInfoLayout.setOnClickListener(this);
		photoImg.setOnClickListener(this);
		menuImg.setOnClickListener(this);
		addressText.setOnClickListener(this);
		details_phone_layout.setOnClickListener(this);
		details_comment_layout.setOnClickListener(this);
		leaveMessageLayout.setOnClickListener(this);
	}
	
	/**
	 * 设置餐厅详情信息
	 * @param restaurant
	 */
	public void setRestaurantView(final FDRestaurant restaurant) {
		if (restaurant == null) return;
		restNameText.setText(restaurant.getShopSign());               // 店招
		if(restaurantDetail.getIsRecommended()==0) {
			recommendImageView.setImageResource(R.drawable.fd_restaurant_details_support_disable);
		}else if(restaurantDetail.getIsRecommended()==1) {
			recommendImageView.setImageResource(R.drawable.fd_restaurant_details_support_enable);
		}
		if(restaurantDetail.getIsCollected()==0) {
			favoriteImageView.setImageResource(R.drawable.fd_restaurant_collection_disable);
		}else if(restaurantDetail.getIsCollected()==1) {
			favoriteImageView.setImageResource(R.drawable.fd_restaurant_collection_enable);
		}
		picList = restaurant.getImageList();
		if(picList!=null && picList.size()!=0){
			FDViewUtil.showServerImage(context, photoImg, picList.get(0).getFilePath());
		}
		FDViewUtil.showSafetyRating(restaurant.getFoodSaftyRating(), rest_details_credit_img);
		couponInfoText.setText(restaurant.getCouponInfoData());
		collectCountText.setText(""+restaurant.getCountOfCollect());
		recommendCountText.setText(""+restaurant.getCountOfRecommended());
		creditText.setText(restaurant.getFoodSaftyRatingValue());		
		companyNameText.setText(restaurant.getShopSign());
		expenseText.setText(restaurant.getAverageComsumptionValue());
		addressText.setText(restaurant.getBizAddress());
		phoneText.setText(restaurant.getTelephone());
		companyDateText.setText(restaurant.getFoodSaftyRatingDate());
		recommendCuisineText.setText(restaurant.getRecommendedCuisines());
	}

	/**
	 * 设置餐厅详情评论
	 */
	public void setRestaurantComment(FDRestaurantComment restComment) {
		if(restComment==null) return;
		commentNumberText.setText("点评  ( 共 "+totalRecord+" 条  )");
		commentNameTextView.setText(restComment.getCommentUsername());
		averageTextView.setText(""+restComment.getAverageComsumption());
		commentRatingBarView.setRating(restComment.getFoodSaftyRating());
		commentDataTextView.setText(restComment.getContentTextData());
		commentDateTextView.setText(restComment.getCreateDate());
	}
	
	/**
	 * 加载餐馆详情
	 */
	public void loadSearchDetailData() {
		if(restaurantId<0) return;
		FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
		searchWrapper.getRestaurantDeatail(restaurantId, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				restaurantDetail = (FDRestaurant) callback;
				setRestaurantView(restaurantDetail);
				backListener.onCallback(restaurantDetail);
			}
		});
	}

	/**
	 * 加载对该餐馆的点评
	 */
	public void loadSearchDetailCommentsData() {
		if(restaurantId<0) return;
		
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getCommentForRestaurant(restaurantId, 1, 2, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDRestaurantComment> resultPage = (FDResultPage<FDRestaurantComment>) callback;
				if(resultPage!=null) {
					totalRecord = resultPage.getTotalRecord();
					List<FDRestaurantComment> resultRestList = resultPage.getResultList();
					if(resultRestList!=null) {
						FDRestaurantComment restComment = resultRestList.get(0);
						setRestaurantComment(restComment);
					}
				}
			}
		});
	}
	
	public void onClick(View view) {
		if (view == backImgLl) {
			if(backListener!=null) {
				backListener.onCallback(null);
			}
		}else if(view == recommendLayout) {
			if(restaurantDetail==null) return;
			FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
			final int recommendEanble = restaurantDetail.getIsRecommended()==0?1:0;
			searchWrapper.setRecommondRestaurant(restaurantDetail.getId(), recommendEanble, new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					FDResultParser dishResultParser = (FDResultParser) callback;
					int status = dishResultParser.getBaseResult().getStatus();
					if(status==0) {
						FDViewUtil.showTaggleToast(context, restaurantDetail.getIsRecommended(), "推荐");
						loadSearchDetailData();
					}
				}
			});
		}else if(view == favoriteLayout) {
			if(restaurantDetail==null) return;
			FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
			int favoriteEanble = restaurantDetail.getIsCollected()==0?1:0;
			searchWrapper.setForaviteRestaurant(restaurantDetail.getId(), favoriteEanble, new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					FDResultParser dishResultParser = (FDResultParser) callback;
					int status = dishResultParser.getBaseResult().getStatus();
					if(status==0) {
						FDViewUtil.showTaggleToast(context, restaurantDetail.getIsCollected(), "收藏");
						loadSearchDetailData();
					}
				}
			});
		}else if (view == photoImg) {
			if(restaurantDetail==null) return;
			Intent intent = new Intent(context, FDSearchPhotoDetailsActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantDetail);
			bundle.putSerializable("picListValue", picList);
			intent.putExtras(bundle);
			startActivity(intent);
		}else if (view == menuImg) {
			if(restaurantDetail==null) return;
			Intent intent = new Intent(context, FDSearchMenuDetailsActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantDetail);		
			intent.putExtras(bundle);
			startActivity(intent);
		}else if (view == addressText) {
			if(restaurantDetail==null) return;
			Intent intent = new Intent(context, FDRestaurantTrafficActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantDetail);
			intent.putExtras(bundle);
			startActivity(intent);
		}else if(view == details_phone_layout) {
			if(restaurantDetail==null) return;
			try {
				String phonetextnumber = restaurantDetail.getTelephone().toString();
				Intent detailsPhoneCall = new Intent("android.intent.action.CALL", Uri.parse("tel:"+ phonetextnumber));
				startActivity(detailsPhoneCall);
				FDViewUtil.showToast(context, "你所拨打的餐厅电话是: " + phonetextnumber,true);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
				FDViewUtil.showToast(context, "Sorry, we couldn't find any app to send an SMS!");
			}
		}else if(view == details_comment_layout) {
			if(restaurantDetail==null) return;
			if(backListener!=null) {
				backListener.onCallback("");
			}
		}else if(view == couponInfoLayout) {
			if(restaurantDetail==null) return;
			Intent intent = new Intent(context, FDCouponActivity.class);
			intent.putExtra(FDConst.FD_QUERY_COUPON_EXTRA_KEY, restaurantDetail);		
			startActivity(intent);
		}else if(view == leaveMessageLayout) {
			if(restaurantDetail==null) return;
			Intent intent = new Intent(context, FDCompanyMessageActivity.class);
			intent.putExtra(FDCompanyMessageActivity.FD_COMPANY_MESSAGE_KEY, restaurantDetail);		
			startActivity(intent);
		}
	}

}
