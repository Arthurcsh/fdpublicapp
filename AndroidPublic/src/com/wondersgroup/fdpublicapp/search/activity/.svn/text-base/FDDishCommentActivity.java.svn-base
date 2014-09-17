package com.wondersgroup.fdpublicapp.search.activity;

import java.util.Date;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentSchoolCommentActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCommentInfo;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineDetail;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 * 菜肴点评
 * @author chengshaohua
 *
 */
public class FDDishCommentActivity extends FDBaseActivity implements FDCommentPhotoListener {
	private FDCuisineDetail dishDetail;
	private FDCommentInfo commentDish = new FDCommentInfo();
	private LinearLayout backLayout;
	private TextView dishNameTextView;
	private TextView restNameTextView;
	private EditText commentEditText;
	private TextView leaveTextView;
	private FDTakePhotoView takePhotoView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_comment_dish_view);		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey(FDConst.FD_QUERY_CUISINE_EXTRA_KEY)) {
			dishDetail = bundle.getParcelable(FDConst.FD_QUERY_CUISINE_EXTRA_KEY);
		}
		
		initDishCommentView();
	}
	
	/**
	 * 初始化菜肴点评视图
	 */
	public void initDishCommentView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_dish_comment_back_layout);
		commentEditText = (EditText) findViewById(R.id.fd_dish_comment_textveiw);
		leaveTextView = (TextView) findViewById(R.id.fd_dish_comment_leave_textview);
		dishNameTextView = (TextView) findViewById(R.id.fd_dish_comment_name_textview);
		restNameTextView = (TextView) findViewById(R.id.fd_dish_comment_rest_name_textview);
		takePhotoView = (FDTakePhotoView) findViewById(R.id.fd_dish_comment_takePhoto_view);
		takePhotoView.setCommentPhotoListener(this);
		commentEditText.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				FDStudentSchoolCommentActivity.filterCommentEditText(commentEditText, leaveTextView);
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDDishCommentActivity.this.finish();
			}
		});
		
		setCommentDish();
	}
	
	public void setCommentDish() {
		if(dishDetail==null) return;
		dishNameTextView.setText(dishDetail.getName());
		restNameTextView.setText(dishDetail.getRestaurantName());
	}
	
	/**
	 * 菜肴点评内容
	 * @param restComment
	 */
	public void submitCommentDish(FDCommonComment restComment) {
		if(dishDetail==null) return;
		
		String dishCommentData = commentEditText.getText().toString().trim();
		FDComment cuisineComment = new FDComment();
		if(!StringUtils.isEmpty(dishCommentData)) {
			FDUser loginUser = appContext.getLoginInfo();
			if(loginUser!=null) {
				cuisineComment.setCreateUsername(loginUser.getUsername());
			}
			cuisineComment.setContentTextData(dishCommentData);
			cuisineComment.setPics(restComment.getCommentImages());
			cuisineComment.setContentTitle(restComment.getContentTitle());
			cuisineComment.setCreateDate(StringUtils.dateToString(new Date()));
			commentDish.setOutputMatId(dishDetail.getId());
			commentDish.setContent(cuisineComment);
		}else {
			FDViewUtil.showToast(context, "请输入菜肴点评内容..", true);
			return;
		}
		
		FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
		searchWrapper.setDishComment(commentDish, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				int status = (Integer) callback;
				if(status==0) {
					setResult(20);
					finish();
				}else {
					FDViewUtil.showToast(context, "点评菜肴失败..", true);
				}
			}
		});
	}

	/**
	 * 该方法一定要实现
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void commentCallback(FDCommonComment commentCallback) {
		if(commentCallback==null) return;
		
		submitCommentDish(commentCallback);
	}
}
