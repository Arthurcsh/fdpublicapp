package com.wondersgroup.fdpublicapp.widget;

import java.util.LinkedHashMap;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.ImageBucketAdapter;
import com.wondersgroup.fdpublicapp.common.adapter.ImageGridAdapter;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.mode.ImageBucket;
import com.wondersgroup.fdpublicapp.common.util.AlbumHelper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 *  相册选取图片
 * @author chengshaohua
 *
 */
public class FDPhotoPickWindow extends FDPopupWindow {

	public List<ImageBucket> dataList;                                    // 相册集
	public ImageBucketAdapter bucketAdapter;                              // 相册适配器
	public ImageGridAdapter imageAdapter;                                 // 图片适配器
	public GridView bucketGridView;
	public boolean isBucket;
	public LinkedHashMap<String, FDImage> photosMap = new LinkedHashMap<String, FDImage>();            // 选中图片集
	
	public FDPhotoPickWindow(Context context) {
		super(context);
		
		initPhotoPickWindow();
	}

	public FDPhotoPickWindow(Context context, FDPhotoBucketListener photoListener) {
		super(context);
        this.callbackListener = photoListener;
		
		initPhotoPickWindow();
	}
	
	/**
	 *  初始化相册视图
	 */
	public void initPhotoPickWindow() {
		View photoBucketLayout = LayoutInflater.from(context).inflate(R.layout.fd_common_photo_seek_view, null);
		LinearLayout backLayout = (LinearLayout) photoBucketLayout.findViewById(R.id.fd_common_photo_back_layout);
		LinearLayout finishLayout = (LinearLayout) photoBucketLayout.findViewById(R.id.fd_common_photo_confirm_layout);
		bucketGridView = (GridView) photoBucketLayout.findViewById(R.id.fd_common_photo_gridview);
		setContentView(photoBucketLayout);
		
		setBucketPhotoAndData();
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(!isBucket) {
					setBucketPhotoAndData();
				}else {
					dismiss();
				}
			}
		});
		finishLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				photosMap.clear();
				if(dataList!=null) {
					for(ImageBucket imageBucket:dataList) {
						if(imageBucket==null) continue;
						List<FDImage> images = imageBucket.getImageList();
						if(images==null) continue;
						for(FDImage image:images) {
							if(image==null) continue;
							if(image.isSelected()) {
								photosMap.put(image.getFilePath(), image);
							}
						}
					}
				}
				
				callbackListener.onChoicePhotos(photosMap);
				dismiss();
			}
		});
	}
	
	public void setBucketPhotoAndData() {
		isBucket = true;
		dataList = AlbumHelper.getHelper(context).getImagesBucketList(false);
		bucketAdapter = new ImageBucketAdapter(context,dataList);
		bucketGridView.setAdapter(bucketAdapter);
		bucketGridView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View convertView, int position, long id) {
				if(dataList==null) return;
				List<FDImage> images = dataList.get(position).getImageList();
				imageAdapter = new ImageGridAdapter(context, images);
				bucketGridView.setAdapter(imageAdapter);
				isBucket = false;
			}
		});
	}
}
