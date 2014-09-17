package com.wondersgroup.fdpublicapp.common.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  水平列表适配器
 * @author chengshaohua
 *
 */
public class FDHorizontalListViewAdapter extends BaseAdapter {
	
	private Context mContext ;
	private List<FDImage> mList;
	private boolean isDeleted = true;
	
	public FDHorizontalListViewAdapter(Context context ,List<FDImage> list){
		this(context, list, true);
	}
	
	public FDHorizontalListViewAdapter(Context context ,List<FDImage> list, boolean isDeleted){
		this.mContext = context;
		this.mList = list;
		this.isDeleted = isDeleted;
	}
	
	public int getCount() {
		if(mList==null) return 0;
		return mList.size();
	}

	public Object getItem(int position) {
		if(mList==null) return null;
		return mList.get(position);   
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view  = convertView;
		
		if(view == null ){
			holderView = new HolderView();
			view = LayoutInflater.from(mContext).inflate(R.layout.fd_common_horizontal_view_item, parent, false);
			
			holderView.imageView =(ImageView) view.findViewById(R.id.common_horizontal_imageView);
			holderView.textView = (TextView) view.findViewById(R.id.common_horizontal_textView);
			holderView.selectLayout = (LinearLayout) view.findViewById(R.id.layout_common_horizontal_item_deleted);
			holderView.deleteImageView =(ImageView) view.findViewById(R.id.common_horizontal_isselected);
			
			view.setTag(holderView);
		}else{
			holderView = (HolderView) view.getTag();
		}
		final FDImage imageHorizontal = mList.get(position);
		if(imageHorizontal!=null) {
			System.out.println("*****************"+imageHorizontal.getFilePath());
			FDViewUtil.showServerImage(mContext, holderView.imageView, imageHorizontal.getFilePath());
//			holderView.imageView.setImageBitmap(FDViewUtil.getBitMap(imageHorizontal.getFilePath()));
		}
		holderView.textView.setVisibility(View.GONE);
		if(isDeleted) {
			holderView.deleteImageView.setVisibility(View.VISIBLE);
			holderView.selectLayout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					imageHorizontal.setSelected(false);
					mList.remove(imageHorizontal);
					notifyDataSetChanged();
				}
			});
		}
		
		return view;
	}
	
	class HolderView{
		ImageView imageView;
		TextView textView;
		LinearLayout selectLayout;
		ImageView deleteImageView;
	}
}
