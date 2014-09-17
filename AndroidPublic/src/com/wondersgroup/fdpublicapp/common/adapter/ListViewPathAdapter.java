package com.wondersgroup.fdpublicapp.common.adapter;

import java.util.List;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDOnPathOperateListener;
import com.wondersgroup.fdpublicapp.common.util.FileUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author chengshaohua
 *
 */
public class ListViewPathAdapter extends BaseAdapter {

	private Context context;                // 运行上下文
	private List<String> listItems;         // 数据集合
	private LayoutInflater listContainer;   // 视图容器
	private int itemViewResource;           // 自定义项视图源
	private FDOnPathOperateListener listener;

	public interface OnPathOperateListener {

		public final static int DEL = 0;
		public final static int RENAME = 1;

		public void onPathOperate(int type, int position, TextView pathName);
	}

	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public ListViewPathAdapter(Context context, List<String> data,int resource, FDOnPathOperateListener listener) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
		this.listener = listener;
	}

	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = listContainer.inflate(itemViewResource, null);
		}

		LinearLayout llOp = (LinearLayout) convertView.findViewById(R.id.ll_op);
		llOp.setVisibility(View.GONE);

		final TextView tvPath = (TextView) convertView.findViewById(R.id.tvPath);
		tvPath.setText(FileUtil.getPathName(listItems.get(position)));
		Button btnDel = (Button) convertView.findViewById(R.id.btn_del);
		btnDel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				listener.onPathOperate(OnPathOperateListener.DEL, position, tvPath);
			}
		});
		Button btnRename = (Button) convertView.findViewById(R.id.btn_rename);
		btnRename.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				listener.onPathOperate(OnPathOperateListener.RENAME, position, tvPath);
			}
		});
		return convertView;
	}

}
