package com.wondersgroup.fdpublicapp.common.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.ImageBucket;
import com.wondersgroup.fdpublicapp.common.util.BitmapCache;
import com.wondersgroup.fdpublicapp.common.util.BitmapCache.ImageCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  相册适配器
 * @author chengshaohua
 *
 */
public class ImageBucketAdapter extends BaseAdapter {
	final String TAG = getClass().getSimpleName();
	Context act;
	List<ImageBucket> dataList;
	BitmapCache cache;
	ImageCallback callback = new ImageCallback() {
		public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					Log.e(TAG, "callback, bmp not match");
				}
			} 
		}
	};

	public ImageBucketAdapter(Context act, List<ImageBucket> list) {
		this.act = act;
		dataList = list;
		cache = new BitmapCache();
	}

	public int getCount() {
		int count = 0;
		if (dataList != null) {
			count = dataList.size();
		}
		return count;
	}

	public Object getItem(int position) {
		if(dataList!=null) {
			return dataList.get(position);
		}
		return null;
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	class Holder {
		private ImageView iv;
		private ImageView selected;
		private TextView name;
		private TextView count;
	}

	public View getView(int position, View view, ViewGroup arg2) {
		Holder holder;
		if (view == null) {
			holder = new Holder();
			view = View.inflate(act, R.layout.fd_common_photo_bucket_item, null);
			holder.iv = (ImageView) view.findViewById(R.id.image);
			holder.selected = (ImageView) view.findViewById(R.id.isselected);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.count = (TextView) view.findViewById(R.id.count);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		ImageBucket item = dataList.get(position);
		holder.count.setText(item.count+"");
		holder.name.setText(item.bucketName);
		holder.selected.setVisibility(View.GONE);
		if (item.imageList != null && item.imageList.size() > 0) {
			String thumbPath = item.imageList.get(0).getThumbnailPath();
			String sourcePath = item.imageList.get(0).getFilePath();
			holder.iv.setTag(sourcePath);
			cache.displayBmp(holder.iv, thumbPath, sourcePath, callback);
		} else {
			holder.iv.setImageBitmap(null);
			Log.e(TAG, "no images in bucket " + item.bucketName);
		}
		return view;
	}

}
