package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.personal.adapter.FDLeaveMessageAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDLeaveMessage;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 *  我的留言
 * @author chengshaohua
 *
 */
public class FDUserCenterMyMessageActivity extends FDBaseActivity implements IXListViewListener {
	
	private LJListView messageListView;
	private Handler mHandler;
	private List<FDLeaveMessage> leaveMessage = new ArrayList<FDLeaveMessage>();
	private FDLeaveMessageAdapter messageAdapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_my_message);
		
		initMyMessageView();
	}
	
	/**
	 * 初始化我的留言
	 */
	public void initMyMessageView() {
		LinearLayout backLayout = (LinearLayout) findViewById(R.id.layout_usercenter_message_back);
		final ToggleButton listMessageLayout = (ToggleButton) findViewById(R.id.fd_usercenter_message_list_imageview);
		messageListView = (LJListView) findViewById(R.id.fd_usercenter_message_listview);
		messageListView.setPullLoadEnable(true, "..");
		messageListView.setPullRefreshEnable(true);
		messageListView.setIsAnimation(true);
		messageListView.setXListViewListener(this);
		messageAdapter = new FDLeaveMessageAdapter(context, leaveMessage);
		messageListView.setAdapter(messageAdapter);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				finish();
			}
		});
		listMessageLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				boolean isCheck = listMessageLayout.isChecked();
				messageAdapter.setShowListReply(isCheck);
				messageAdapter.setLeaveMessages(leaveMessage);
			}
		});
		
		loadLeaveMessages();
	}
	
	/**
	 * 分页加载我的留言
	 */
	public void loadLeaveMessages() {
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(this);
		personalWrapper.getMyLeaveMessages(pageNo++,LOAD_MORE_MAX_COUNT, new FDCallback() {
			public void onCallback(Object callback) {
				FDResultPage<FDLeaveMessage> messagePage = (FDResultPage<FDLeaveMessage>) callback;
				if(messagePage==null) return;
				
				totalPage = (int) (1+messagePage.getTotalRecord()/LOAD_MORE_MAX_COUNT);
				leaveMessage = messagePage.getResultList();
				messageAdapter.setLeaveMessages(leaveMessage);
				
				onCommentLoad(messagePage.getTotalRecord());
			}
		});
	}

	public void onRefresh() {
		mHandler = new Handler();

		pageNo = 1;
		onLoadMore();
	}

	public void onLoadMore() {
		mHandler = new Handler();
		
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		
		mHandler.post(new Runnable(){
			public void run() {
				loadLeaveMessages();
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count
	 */
	private void onCommentLoad(long count) {
		messageListView.setCount(""+count);
		messageListView.stopRefresh();
		messageListView.stopLoadMore(pageNo>totalPage);
		messageListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
}
