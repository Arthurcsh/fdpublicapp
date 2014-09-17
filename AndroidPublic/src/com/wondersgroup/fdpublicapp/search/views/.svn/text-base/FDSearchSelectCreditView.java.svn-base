package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessCreditAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FDSearchSelectCreditView extends FDBaseConditionItemView {
	private Context context;
	private ListView listView;
	private BusinessCreditAdapter adapter;
	private ArrayList<FDCredit> newCreditList = new ArrayList<FDCredit>();
	private ArrayList<FDCredit> creditList = new ArrayList<FDCredit>();
	public FDSearchSelectCreditView(Context context) {
		super(context);
		this.context = context;
		
		initCreditView();
	}

	public FDSearchSelectCreditView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FDSearchSelectCreditView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		
		initCreditView();
	}
	
	private void initCreditView() {
		View creditView = LayoutInflater.from(context).inflate(R.layout.fd_serach_select_list, null);
		addView(creditView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		listView = (ListView) creditView.findViewById(R.id.fd_seek_select_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setVerticalScrollBarEnabled(false);
		newCreditList = (ArrayList<FDCredit>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_CREDIT_LIST);

		if(newCreditList!=null){
			FDCredit unlimitedCredit = new FDCredit();
			unlimitedCredit.setCreditCode(FDConst.UNLIMITED_CONDITION_KEY);
			unlimitedCredit.setCreditName(FDConst.UNLIMITED_CONDITION_VALUE);
			creditList.add(unlimitedCredit);
			creditList.add(newCreditList.get(newCreditList.size()-1));
			for(int i=0;i<newCreditList.size()-1;i++){
				creditList.add(newCreditList.get(i));
			}	
		}
		if (creditList != null && creditList.size()>0) {
			adapter = new BusinessCreditAdapter(context, creditList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					FDCredit credit = creditList.get(position);
					if(conditionChangedListener!=null) {
						conditionChangedListener.onChangedConditionCallback(QUERY_RESTAURANT_CONDITION_CREDIT, credit);
					}
				}
			});
		}
	}

}
