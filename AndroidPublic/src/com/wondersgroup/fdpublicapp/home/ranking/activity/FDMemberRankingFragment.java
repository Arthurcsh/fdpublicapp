package com.wondersgroup.fdpublicapp.home.ranking.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.ranking.adapter.FDRankingMemberAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.search.mode.FDCondition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author chengshaohua
 *  会员排行
 */
public class FDMemberRankingFragment extends FDSearchFragment {

	public View memberConditionView;
	public ArrayList<FDUser> membersRankingList = new ArrayList<FDUser>();
	public FDRankingMemberAdapter memberRankingAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSellerRankingData();
		initSellerRankingView();
		setSearchRankingCondition();
		
		return memberConditionView;
	}	
	
	public void initSellerRankingData() {
		for(int i=0;i<9;i++) {
			FDUser member = new FDUser();
			member.setId(5);
			member.setName("周楚楚");
			member.setSex(1);
			membersRankingList.add(member);
		}
	}
	
	public void initSellerRankingView() {
		memberConditionView = LayoutInflater.from(context).inflate(R.layout.fd_ranking_member_condiction, null);
		LinearLayout backLayout = (LinearLayout) memberConditionView.findViewById(R.id.fd_ranking_member_back_label);
		LinearLayout rankingLayout = (LinearLayout) memberConditionView.findViewById(R.id.fd_member_ranking_xml);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDMemberRankingFragment.this.getActivity().finish();
			}
		});
		rankingLayout.addView(searchResultTableView);
		memberRankingAdapter = new FDRankingMemberAdapter(this.getActivity(),membersRankingList);         
		xlistView.setAdapter(memberRankingAdapter);   
		xlistView.setOnItemClickListener(null);
	}
	
	public void setSearchRankingCondition() {
		Bundle bundle = getActivity().getIntent().getExtras();
		String queryCondition = null;
		if(bundle!=null && bundle.containsKey(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY)) {
			FDCondition bundleCondition = bundle.getParcelable(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY);
//			searchAreaView.initConditionQuery(bundleCondition);
			// 初始化条件BarItem
			if(bundleCondition!=null) {
				queryCondition = bundleCondition.getQueryConditions();
			}
		}
		getRequestParams(queryCondition);
//		loadSearchDetailList(null);
	}

	public void setSearchLocationCondition() {
		
	}

	public void initRestaurantListView() {
		
	}
}
