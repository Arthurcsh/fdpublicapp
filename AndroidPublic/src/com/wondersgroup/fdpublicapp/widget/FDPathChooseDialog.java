package com.wondersgroup.fdpublicapp.widget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.ListViewPathAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDOnPathOperateListener;
import com.wondersgroup.fdpublicapp.common.util.FileUtil;
import com.wondersgroup.fdpublicapp.common.util.FileUtil.PathStatus;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 路径选择弹
 * @author chengshaohua
 *
 */
public class FDPathChooseDialog extends Dialog {

    private ListView listview;
	private Button btnComfirm;
	private Button btnBack;
	private Button btnNew;
	private TextView tvCurPath;
	private Context ctx;
	private List<String> data;
	private ListAdapter listAdapter;
	private ChooseCompleteListener listener;
	private Stack<String> pathStack = new Stack<String>();

	private int firstIndex = 0;
	private boolean isBack = false;

	private View lastSelectItem; //上一个长按操作的View
	
	//监听操作事件
	private FDOnPathOperateListener pListener = new FDOnPathOperateListener() {
		public void onPathOperate(int type, final int position,
				final TextView pathName) {
			if (type == FDOnPathOperateListener.DEL) {
				String path = data.get(position);
				int rs = FileUtil.deleteBlankPath(path);
				if (rs == 0) {
					data.remove(position);
					refleshListView(data, firstIndex);
					FDViewUtil.showToast(ctx, "删除成功");
				} else if (rs == 1) {
					FDViewUtil.showToast(ctx, "没有权限");
				} else if (rs == 2) {
					FDViewUtil.showToast(ctx, "不能删除非空目录");
				}

			} else if (type == FDOnPathOperateListener.RENAME) {
				final EditText et = new EditText(ctx);
				et.setText(FileUtil.getPathName(data.get(position)));
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setTitle("重命名");
				builder.setView(et);
				builder.setCancelable(true);
				builder.setPositiveButton("确定", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String input = et.getText().toString();
						if (StringUtils.isEmpty(input)) {
							FDViewUtil.showToast(ctx, "输入不能为空");
						} else {
							String newPath = pathStack.peek() + File.separator + input;
							boolean rs = FileUtil.reNamePath(data.get(position), newPath);
							if (rs == true) {
								pathName.setText(input);
								data.set(position, newPath);
								FDViewUtil.showToast(ctx, "重命名成功");
							} else {
								FDViewUtil.showToast(ctx, "重命名失败");
							}
						}
						dialog.dismiss();
					}
				});
				builder.setNegativeButton("取消", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		}
	};

	public interface ChooseCompleteListener {
		void onComplete(String finalPath);
	}

	public FDPathChooseDialog(Context context, ChooseCompleteListener listener) {
		super(context);
		this.ctx = context;
		this.listener = listener;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fd_common_file_path_chooser);
		setCanceledOnTouchOutside(true);
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		listview = (ListView) findViewById(android.R.id.list);
		btnComfirm = (Button) findViewById(R.id.btn_comfirm);
		btnBack = (Button) findViewById(R.id.btn_back);
		btnNew = (Button) findViewById(R.id.btn_new);
		tvCurPath = (TextView) findViewById(R.id.tv_cur_path);
		
		//获得内置SD卡的根路径
		String rootPath = null;
		if (FileUtil.checkExternalSDExists()) {
			rootPath = "/storage";
			data = new ArrayList<String>();
			data.add(FileUtil.getSDRoot());
			data.add(FileUtil.getExternalSDRoot());
		} else {
			rootPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			data = FileUtil.listPath(rootPath);
		}
		
		tvCurPath.setText(rootPath);

		pathStack.add(rootPath);

		refleshListView(data, 0);
        //单击
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				firstIndex = position;
				String currentPath = data.get(position);
				tvCurPath.setText(currentPath);
				data = FileUtil.listPath(currentPath);
				pathStack.add(currentPath);
				refleshListView(data, pathStack.size() - 1);
			}
		});
		 //长按
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				if(lastSelectItem!=null&&!lastSelectItem.equals(view)){
					 lastSelectItem.findViewById(R.id.ll_op).setVisibility(View.GONE);
				}
				LinearLayout llOp = (LinearLayout) view.findViewById(R.id.ll_op);
				int visible = llOp.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
				llOp.setVisibility(visible);
				lastSelectItem = view;
				return true;
			}
		});
        
		//确认
		btnComfirm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				listener.onComplete(pathStack.peek());
				dismiss();
			}
		});
         
		//后退
		btnBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (pathStack.size() >= 2) {
					isBack = true;
					pathStack.pop();
					data = FileUtil.listPath(pathStack.peek());
					tvCurPath.setText(pathStack.peek());
					refleshListView(data, firstIndex);
				}
			}
		});
        
		//新建
		btnNew.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final EditText et = new EditText(ctx);
				et.setText("新建文件夹");
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setTitle("新建文件夹");
				builder.setView(et);
				builder.setCancelable(true);
				builder.setPositiveButton("确定", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String rs = et.getText().toString();
						if (StringUtils.isEmpty(rs)) {
							FDViewUtil.showToast(ctx, "输入不能为空");
						} else {
							String newPath = pathStack.peek() + File.separator
									+ rs;
							PathStatus status = FileUtil.createPath(newPath);
							switch (status) {
							case SUCCESS:
								data.add(newPath);
								refleshListView(data, data.size()-1);
								FDViewUtil.showToast(ctx, "创建成功");
								break;
							case ERROR:
								FDViewUtil.showToast(ctx, "创建失败");
								break;
							case EXITS:
								FDViewUtil.showToast(ctx, "文件名重复");
								break;
							}
						}
						dialog.dismiss();
					}
				});
				builder.setNegativeButton("取消", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		});
	}

	/**
	 * 更新listView视图
	 * 
	 * @param data
	 */
	private void refleshListView(List<String> data, int firstItem) {
		String lost = FileUtil.getSDRoot() + "lost+found";
		data.remove(lost);
		listAdapter = new ListViewPathAdapter(ctx, data, R.layout.fd_common_file_path_listitem, pListener);
		listview.setAdapter(listAdapter);
		listview.setSelection(firstItem);
	}
}
