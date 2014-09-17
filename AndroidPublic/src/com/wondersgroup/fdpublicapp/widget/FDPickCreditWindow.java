package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectCreditView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FDPickCreditWindow extends FDPopupWindow implements FDQueryConditionListener{

	// view 事件派发组件
	public FDPickCreditWindow(Context context, View view) {
		super(context);
		this.onEventView = view;
		
		initCreditView();
	}

	public FDPickCreditWindow(Context context, FDQueryConditionListener queryConditionListener) {
		super(context);
		this.conditionListener = queryConditionListener;
		
		initCreditView();
	}
	
	public void initCreditView() {
		View creditLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_credit, null);
		LinearLayout creditlistL = (LinearLayout) creditLayout.findViewById(R.id.fd_restaurant_seek_credit_list_ll);
		FDSearchSelectCreditView creditView = new FDSearchSelectCreditView(context);
		if(conditionListener==null) {
			conditionListener = this;
		}
		creditView.setQueryConditionListener(conditionListener);
		creditlistL.addView(creditView);
		
		setContentWindowView(creditLayout);
		RelativeLayout backLayout = (RelativeLayout) creditLayout.findViewById(R.id.fd_restaurant_seek_credit_rl);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDPickCreditWindow.this.dismiss();
			}
		});
	}
	
	public void onChangedConditionCallback(int viewType, Object conditionMode) {
		if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_CREDIT==viewType) {
			FDCredit credit = (FDCredit) conditionMode;
			TextView creditTextView = (TextView) onEventView.findViewById(R.id.fd_restaurant_seek_credit_textview);
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(credit.getCreditCode())) {
				creditTextView.setText(context.getString(R.string.search_select_credit_text));
				this.onEventView.setTag(null);
			}else {
				creditTextView.setText(credit.getCreditName());
				this.onEventView.setTag(credit);
			}
			
			this.dismiss();
		}
		
	}

}
