package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 餐厅图片集
 * @author chengshaohua
 *
 */
public class PhotoDetailsAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<FDImage>  picList;	
	public PhotoDetailsAdapter(Activity activity, ArrayList<FDImage> picList) {
		super();
		this.activity = activity;
		this.picList = picList;
	}
	public int getCount() {
		return picList==null?0:picList.size();
	}
	public Object getItem(int position) {
		return picList==null?null:picList.get(position);
	}
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = activity.getLayoutInflater().inflate(R.layout.fd_restaurant_photo_details_item, null);
			holder.picImg = (ImageView) convertView.findViewById(R.id.griditem_picture_img);
			holder.nameText = (TextView) convertView.findViewById(R.id.griditem_picture_name_tv);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		if (picList != null && picList.size() != 0) {
			FDImage image = picList.get(position);
			FDViewUtil.showServerImage(activity, holder.picImg, image.getFilePath());
			holder.nameText.setText(picList.get(position).getName());
		}
		
		return convertView;
	}
	private class ViewHolder {
		public TextView nameText;
		public ImageView picImg;
	}
}
