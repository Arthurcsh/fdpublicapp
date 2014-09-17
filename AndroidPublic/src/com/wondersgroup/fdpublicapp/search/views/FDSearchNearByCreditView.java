package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessCreditAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FDSearchNearByCreditView extends LinearLayout{
	private Activity context;
	private ListView listView;
	private BusinessCreditAdapter adapter;
	private LayoutInflater flater;
	private ArrayList<FDCredit> newCreditList = new ArrayList<FDCredit>();
	
	public FDSearchNearByCreditView(Context context) {
		super(context);
		this.context = (Activity) context;
		initCreditView();
	}
	
	private void initCreditView() {
		flater = LayoutInflater.from(context);
	    View creditView = flater.inflate(R.layout.fd_rest_seek_result_ll,null);
		addView(creditView);
		listView=(ListView) creditView.findViewById(R.id.fd_seek_nearby_public_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setVerticalScrollBarEnabled(false);
		final ArrayList<FDCredit> creditList=(ArrayList<FDCredit>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_CREDIT_LIST);

		if(creditList!=null){
			newCreditList.add(creditList.get(creditList.size()-1));
			for(int i=0;i<creditList.size()-1;i++){
				newCreditList.add(creditList.get(i));
			}	
		}
		if(newCreditList!=null){
			adapter=new BusinessCreditAdapter(context,newCreditList);
			listView.setAdapter(adapter);	
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) 				{					
					removeAllViews();
					FDSearchTableListView view1=new FDSearchTableListView(context);
					addView(view1);
				}
			});
		}		
		creditView.findViewById(R.id.fd_seek_nearby_public_img).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				removeAllViews();
				FDSearchTableListView view=new FDSearchTableListView(context);
				addView(view);
			}
		});
	}

}
