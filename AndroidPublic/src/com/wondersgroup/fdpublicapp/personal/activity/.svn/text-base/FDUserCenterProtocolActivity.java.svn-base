package com.wondersgroup.fdpublicapp.personal.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;

/**
 *  用户注册协议
 * @author chengshaohua
 *
 */
public class FDUserCenterProtocolActivity extends FDBaseActivity {
	public final static String FD_USER_CENTER_TITLE_KEY = "fd.user.center.title.key";
	public final static String FD_USER_CENTER_XML_KEY   = "fd.user.center.xml.key";
	
	public TextView titleTextView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_info_view_xml);
		
		initRegisterProtocolView();
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null) {
			if(bundle.containsKey(FD_USER_CENTER_TITLE_KEY)) {
				String title = bundle.getString(FD_USER_CENTER_TITLE_KEY);
				titleTextView.setText(title);
			}
			if(bundle.containsKey(FD_USER_CENTER_XML_KEY)) {
				String cententXML = bundle.getString(FD_USER_CENTER_XML_KEY);
				
			}
		}
		
	}
	
	public void initRegisterProtocolView() {
		LinearLayout backLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_back);
		titleTextView = (TextView) findViewById(R.id.tv_fd_usercenter_info_view_title);
		LinearLayout infoXMLView = (LinearLayout) findViewById(R.id.layout_fd_usercenter_info_view_xml);
		View protocolTextView = LayoutInflater.from(this).inflate(R.layout.fd_usercenter_register_protocol_view, null);
		infoXMLView.addView(protocolTextView);
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				finish();
			}
		});
	}
}
