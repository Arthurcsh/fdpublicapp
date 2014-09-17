package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.adapter.FDTextWatcher;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;
import com.wondersgroup.fdpublicapp.common.mode.FDBitmap;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseResult;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCommentInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.widget.FDPhotoPopupWindows;

/**
 *  营养餐评价
 * @author chengshaohua
 *
 */
public class FDStudentSchoolCommentActivity extends FDBaseActivity {
	private static final int TAKE_PICTURE = 0x000000;
	private String takePhotoPath = "";                         // 拍照图片路径
	private File imageFile;
	private FDPhotoPopupWindows photoPopupWindow;
	public FDMealItem mealItem;
	public LinearLayout backLayout;
	public TextView mealNameTextView;
	public EditText commentEditView;
	public TextView filterTextView;
	public FDHorizontalListView photoListView;
	public FDHorizontalListViewAdapter photoAdapter;
	public ArrayList<FDImage> photoList = new ArrayList<FDImage>();
	public ArrayList<FDImage> commentImageList = new ArrayList<FDImage>();
	public View contentView; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		contentView = View.inflate(this, R.layout.fd_common_photo_item_popupwindows, null);
		addContentView(contentView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setContentView(R.layout.fd_student_school_meal_comment);
		
		initNutritionMealCommentView();
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("student.meal.comment.key")) {
			mealItem = bundle.getParcelable("student.meal.comment.key");
			initNutritionMealCommentView();
		}
	}
	
	/**
	 *  初始化营养餐评价
	 */
	public void initNutritionMealCommentView() {
		backLayout = (LinearLayout) this.findViewById(R.id.student_school_meal_comment_back_img_layout);
		mealNameTextView = (TextView) this.findViewById(R.id.student_school_meal_comment_name_textview);
		commentEditView = (EditText) this.findViewById(R.id.student_school_meal_comment_textveiw);
		filterTextView = (TextView) this.findViewById(R.id.student_school_meal_comment_desc_textview);
		TextView mealCompanyTextView = (TextView) this.findViewById(R.id.student_school_meal_comment_company_textview);
		LinearLayout addImageLayout = (LinearLayout) this.findViewById(R.id.student_school_meal_comment_add_layout);
		photoListView = (FDHorizontalListView) this.findViewById(R.id.student_school_meal_comment_imagelistView);
		
		photoAdapter = new FDHorizontalListViewAdapter(this, commentImageList);
		photoListView.setAdapter(photoAdapter);
		Button submitButton = (Button) this.findViewById(R.id.student_school_meal_comment_submit_button);
		
		if(mealItem!=null) {
			mealNameTextView.setText(mealItem.getName());
			mealCompanyTextView.setText(mealItem.getCompanyName());
		}
		commentEditView.addTextChangedListener(new FDTextWatcher() {			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				filterCommentEditText(commentEditView, filterTextView);
			}
		});
		addImageLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				photoPopupWindow = new FDPhotoPopupWindows(FDStudentSchoolCommentActivity.this, contentView, new FDPhotoBucketListener(){
					public void onCameraPhoto() {
						takePhoto();
						photoPopupWindow.dismiss();
					}
					public void onChoicePhotos(LinkedHashMap<String, FDImage> imgPathMap) {
						if(imgPathMap!=null) {
							for(Map.Entry<String, FDImage> entry:imgPathMap.entrySet()) {
								if(entry==null) continue;
								boolean hasImage = false;
								for(int i=0;i<commentImageList.size();i++) {
									FDImage compareImage = commentImageList.get(i);
									if(compareImage.getFilePath().equals(entry.getKey())){
										hasImage = true;
										break;
									}
								}
								if(!hasImage) {
									commentImageList.add(entry.getValue());
								}
							}
							photoAdapter.notifyDataSetChanged();
						}
					}
				});
			}
		});
		submitButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				commentNutritionMeal();
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDStudentSchoolCommentActivity.this.finish();
			}
		});
	}
	
	/**
	 *  营养餐点评
	 */
	public void commentNutritionMeal() {
		if(mealItem==null) return;
		if(!FDViewUtil.verifyTextview(commentEditView, "请输入营养餐评价！")) return;
		
		String commentContent = commentEditView.getText().toString().trim();
		FDCommentInfo commentInfo = new FDCommentInfo();
		FDComment comment = new FDComment();
		
		comment.setContentTextData(commentContent);
		comment.setCreateDate(StringUtils.dateToString(new Date()));
		comment.setContentTitle(mealItem.getName());
		comment.setCreateUsername(appContext.getLoginUserName());
		commentInfo.setContent(comment);
		commentInfo.setStuMealId(mealItem.getId());
		
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(this);
		mealWrapper.setNutritionMealComment(commentInfo, commentImageList, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback!=null) {
					FDResultParser resultParser = (FDResultParser) callback;
					FDBaseResult baseResult = resultParser.getBaseResult();
					if(baseResult.getStatus()==0) {
						FDViewUtil.showToast(FDStudentSchoolCommentActivity.this, "营养餐点评成功..",true);
						Intent data = new Intent();  
						setResult(20, data);  
						FDStudentSchoolCommentActivity.this.finish();
					}
				}
			}
		});
	}
	
	/**
	 *  拍照取图
	 */
	public void takePhoto() {
		String savePath = FDBitmap.savePath;
		//判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();		
		if(storageState.equals(Environment.MEDIA_MOUNTED)){
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		}
		
		//没有挂载SD卡，无法保存文件
		if(StringUtils.isEmpty(savePath)){
			FDViewUtil.showToast(this, "无法保存照片，请检查SD卡是否挂载");
			return;
		}

		imageFile = FDBitmap.getLocalTimeStampFile();
		Uri uri = Uri.fromFile(imageFile);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, TAKE_PICTURE);
	}
	
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if(resultCode != RESULT_OK) return;
		
		if (requestCode == TAKE_PICTURE) {
			if(imageFile!=null) {
				Uri uri = Uri.fromFile(imageFile);
				takePhotoPath = uri.getPath();                       //该照片的绝对路径
				FDImage takeImage = new FDImage();
				takeImage.setName(imageFile.getName());
				takeImage.setFilePath(takePhotoPath);
				commentImageList.add(takeImage);
				photoAdapter.notifyDataSetChanged();
			}
		}
	}
	
	
	public static void filterCommentEditText(EditText editText, TextView filterText) {
		int maxLen = 100;
		Editable editable = editText.getText();
		int len = editable.length();
		filterText.setText("还可以输入"+(maxLen-len)+"字");
		
		if (len > maxLen) {
			int selEndIndex = Selection.getSelectionEnd(editable);
			String str = editable.toString();
			String newStr = str.substring(0, maxLen);          // 截取新字符串
			editText.setText(newStr);
			editable = editText.getText();

			int newLen = editable.length();                    // 新字符串的长度
			if (selEndIndex > newLen) {
				selEndIndex = editable.length();               // 旧光标位置超过字符串长度
			}
			Selection.setSelection(editable, selEndIndex);     // 设置新光标所在的位置
		}  
	}
}
