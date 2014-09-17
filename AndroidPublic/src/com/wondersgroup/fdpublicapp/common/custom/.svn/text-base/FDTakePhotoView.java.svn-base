package com.wondersgroup.fdpublicapp.common.custom;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;
import com.wondersgroup.fdpublicapp.common.mode.FDBitmap;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.widget.FDPhotoPopupWindows;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 拍照、选取图片组件
 * 
 * @author chengshaohua
 * 
 */
public class FDTakePhotoView extends LinearLayout {
	private static final int FD_COMMON_TAKE_PICTURE = 0x000000;
	private String takePhotoPath = "";                         // 拍照图片路径
	private Activity activity;
	private Context context;
	private File imageFile;
	private View contentView;
	private LinearLayout horizontalLayout;
	private LinearLayout addImageLayout;
	private LinearLayout otherAddImageLayout;
	private Button submitButton;
	private FDPhotoPopupWindows photoPopupWindow;
	private FDHorizontalListView photoListView;
	private FDHorizontalListViewAdapter photoAdapter;
	private List<FDImage> takePhotoList = new ArrayList<FDImage>();
	private FDCallback addPhotoListener;
	private FDCommentPhotoListener commentSubmitListener;

	public FDTakePhotoView(Context context) {
		super(context);
		this.context = context;

		initTakePhotoView();
	}

	public FDTakePhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		initTakePhotoView();
	}

	public FDTakePhotoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		initTakePhotoView();
	}

	/**
	 * 初始化视图
	 */
	public void initTakePhotoView() {
		contentView = View.inflate(context,R.layout.fd_common_photo_item_popupwindows, null);
//		addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

		View takePhotoView = LayoutInflater.from(context).inflate(R.layout.fd_common_photo_take_view, this,true);
//		addView(takePhotoView);

		horizontalLayout = (LinearLayout) takePhotoView.findViewById(R.id.fd_common_take_image_layout);
		addImageLayout = (LinearLayout) takePhotoView.findViewById(R.id.fd_common_take_image_add_layout);
		submitButton = (Button) takePhotoView.findViewById(R.id.fd_common_take_photo_submit_button);
		photoListView = (FDHorizontalListView) takePhotoView.findViewById(R.id.fd_common_take_photo_imagelistView);
		photoAdapter = new FDHorizontalListViewAdapter(context, takePhotoList);
		photoListView.setAdapter(photoAdapter);

		addImageLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				addTakePhoto();
			}
		});
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if(commentSubmitListener!=null) {
					FDCommonComment commentPhoto = new FDCommonComment();
					commentPhoto.setCommentImages(takePhotoList);
					commentSubmitListener.commentCallback(commentPhoto);
				}
			}
		});
	}

	/**
	 * 添加图片(拍照、选图)
	 */
	public void addTakePhoto() {
		photoPopupWindow = new FDPhotoPopupWindows(context, contentView,
				new FDPhotoBucketListener() {
					public void onCameraPhoto() {
						takePhoto();
						photoPopupWindow.dismiss();
					}
					public void onChoicePhotos(LinkedHashMap<String, FDImage> imgPathMap) {
						if (imgPathMap != null) {
							for (Map.Entry<String, FDImage> entry : imgPathMap.entrySet()) {
								if (entry == null) continue;
								boolean hasImage = false;
								for (int i = 0; i < takePhotoList.size(); i++) {
									FDImage compareImage = takePhotoList.get(i);
									if (compareImage.getFilePath().equals(entry.getKey())) {
										hasImage = true;
										break;
									}
								}
								if (!hasImage) {
									takePhotoList.add(entry.getValue());
								}
							}
							photoAdapter.notifyDataSetChanged();
						}
					}
				});
	}

	/**
	 * 拍照取图
	 */
	public void takePhoto() {
		String savePath = FDBitmap.savePath;
		// 判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		}

		// 没有挂载SD卡，无法保存文件
		if (StringUtils.isEmpty(savePath)) {
			FDViewUtil.showToast(context, "无法保存照片，请检查SD卡是否挂载");
			return;
		}

		imageFile = FDBitmap.getLocalTimeStampFile();
		Uri uri = Uri.fromFile(imageFile);

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		activity.startActivityForResult(intent, FD_COMMON_TAKE_PICTURE);
	}
	
	public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if(resultCode != activity.RESULT_OK) return;
		
		if (requestCode == FD_COMMON_TAKE_PICTURE) {
			if(imageFile!=null) {
				Uri uri = Uri.fromFile(imageFile);
				takePhotoPath = uri.getPath();                       //该照片的绝对路径
				FDImage takeImage = new FDImage();
				takeImage.setName(imageFile.getName());
				takeImage.setFilePath(takePhotoPath);
				takePhotoList.add(takeImage);
				photoAdapter.notifyDataSetChanged();
			}
		}
	}
	
	/**
	 * 隐藏图片选择容器
	 * @param addTakeImageLayout
	 */
	public void setTakeAddPhotoLayout(LinearLayout addTakeImageLayout) {
		this.otherAddImageLayout = addTakeImageLayout;
		this.showHorizonalImageView();
		addImageLayout.setVisibility(View.GONE);
//		horizontalLayout.setVisibility(View.GONE);
		
		if(otherAddImageLayout!=null) {
			otherAddImageLayout.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					addTakePhoto();
				}
			});
		}
	}
	
	public void showHorizonalImageView() {
		Animation showAnimation = AnimationUtils.loadAnimation(context, R.anim.fd_common_top_down);
		horizontalLayout.startAnimation(showAnimation);
	}
	public void hideHorizonalImageView() {
		Animation hideAnimation = AnimationUtils.loadAnimation(context, R.anim.fd_common_bottom_up);
		horizontalLayout.startAnimation(hideAnimation);
	}
	
	public void setCommentPhotoListener(FDCommentPhotoListener commentInterface) {
		this.commentSubmitListener = commentInterface;
		if(commentSubmitListener instanceof Activity) {
			setCommentActivity((Activity) commentSubmitListener);
		}
	}
	
	public void setCommentActivity(Activity activity) {
		this.activity = activity;
	}
	
	public LinearLayout getHorizontalImageListLayout() {
		return horizontalLayout;
	}
	
	public interface FDCommentPhotoListener {
//		public void onActivityResult(final int requestCode, int resultCode, final Intent data);
		public void commentCallback(FDCommonComment commentCallback);
	}
}
