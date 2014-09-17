package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessCreditAdapter extends BaseAdapter{
	private Context activity;
	private ArrayList<FDCredit> creditList;
	
	public BusinessCreditAdapter(Context activity, ArrayList<FDCredit> creditList) {
		super();
		this.activity = activity;
		this.creditList = creditList;
	}

	public int getCount() {
			return creditList==null?0:creditList.size();
	}

	public Object getItem(int position) {
		return creditList==null||creditList.size()==0?null:creditList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = LayoutInflater.from(activity);
		convertView = layoutInflater.inflate(R.layout.fd_business_credit_listitem, null);
		
		TextView nameTextView = (TextView) convertView.findViewById(R.id.fd_business_credit_listitem_tv);
		ImageView picImg = (ImageView) convertView.findViewById(R.id.fd_business_credit_listitem_image);
		if(creditList!=null && creditList.size()>0) {
			FDCredit credit = creditList.get(position);
			if (credit.getCreditCode().equals("21002")) {
				picImg.setBackgroundResource(R.drawable.fd_business_credit_unhappy);
			} else if (credit.getCreditCode().equals("21003")) {
				picImg.setBackgroundResource(R.drawable.fd_business_credit_peace);
			} else if (credit.getCreditCode().equals("21004")){
				picImg.setBackgroundResource(R.drawable.fd_business_credit_smile);
			}else if (credit.getCreditCode().equals("21001")){
				picImg.setBackgroundResource(R.drawable.fd_business_credit_unreview);
			}else {
				picImg.setVisibility(View.GONE);
			}
			nameTextView.setText(creditList.get(position).getCreditName());
		}
		
		return convertView;
	}
	
}
