package com.wondersgroup.fdpublicapp.common.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDBitmap;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.BitmapCache;
import com.wondersgroup.fdpublicapp.common.util.BitmapCache.ImageCallback;

/**
 * @author chengshaohua
 * 
 */
public class ImageGridAdapter extends BaseAdapter {

	private TextCallback textcallback = null;
	Context act;
	List<FDImage> dataList;
	public Map<String, String> map = new HashMap<String, String>();
	BitmapCache cache;
	private Handler mHandler;
	private int selectTotal = 0;
	
	ImageCallback callback = new ImageCallback() {
		public void imageLoad(ImageView imageView, Bitmap bitmap,Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} 
			} 
		}
	};

	public static interface TextCallback {
		public void onListen(int count);
	}

	public void setTextCallback(TextCallback listener) {
		textcallback = listener;
	}

	public ImageGridAdapter(Context context, List<FDImage> list) {
		this.act = context;
		dataList = list;
		cache = new BitmapCache();
	}
	
	public ImageGridAdapter(Activity act, List<FDImage> list, Handler mHandler) {
		this.act = act;
		dataList = list;
		cache = new BitmapCache();
		this.mHandler = mHandler;
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

	public long getItemId(int position) {
		return position;
	}

	class Holder {
		private RelativeLayout itemLayout;
		private ImageView iv;
		private ImageView selected;
		private TextView text;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;

		if (convertView == null) {
			holder = new Holder();
			convertView = View.inflate(act, R.layout.fd_common_photo_grid_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.image);
			holder.selected = (ImageView) convertView.findViewById(R.id.isselected);
			holder.itemLayout = (RelativeLayout) convertView.findViewById(R.id.item_image_grid_layout);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		final FDImage item = dataList.get(position);
		holder.iv.setTag(item.getFilePath());
		cache.displayBmp(holder.iv, item.getThumbnailPath(), item.getFilePath(),callback);
		if (item.isSelected()) {
			holder.selected.setImageResource(R.drawable.fd_usercenter_register_valid_ok);
			holder.itemLayout.setBackgroundResource(R.drawable.bgd_relatly_line);
		} else {
			holder.selected.setImageDrawable(null);
			holder.itemLayout.setBackgroundColor(0x00000000);
		}
		holder.iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String path = dataList.get(position).getFilePath();

				if ((FDBitmap.drr.size() + selectTotal) < 9) {
					item.setSelected(!item.isSelected());
					if (item.isSelected()) {
						holder.selected.setImageResource(R.drawable.fd_usercenter_register_valid_ok);
						holder.itemLayout.setBackgroundResource(R.drawable.bgd_relatly_line);
						selectTotal++;
						if (textcallback != null)
							textcallback.onListen(selectTotal);
						map.put(path, path);
					} else {
						holder.selected.setImageDrawable(null);
						holder.itemLayout.setBackgroundColor(0x00000000);
						selectTotal--;
						if (textcallback != null)
							textcallback.onListen(selectTotal);
						map.remove(path);
					}
				} else {
					FDViewUtil.showToast(act, "最多选择9张图片");
				}
			}
		});

		return convertView;
	}
}
