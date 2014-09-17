package com.wondersgroup.fdpublicapp.home.safety.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.home.safety.adapter.FDSafetyInfoAdapter;
import com.wondersgroup.fdpublicapp.home.safety.service.FDSafetWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 食品安全信息
 * @author chengshaohua
 *
 */
public class FDSafetyInfoFragment extends FDListViewFragment {
    public int type;
	public ArrayList<FDNoteComment> safetyInfoList = new ArrayList<FDNoteComment>();
	public FDSafetyInfoAdapter safetyInfoAdapter;
	
	public FDSafetyInfoFragment(int safetyType) {
		this.type = safetyType;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSafetyInfoView();
		
		return fragmentView;
	}	
	
	public void initSafetyInfoView() {
		safetyInfoAdapter = new FDSafetyInfoAdapter(context,safetyInfoList);         
		fdListView.setAdapter(safetyInfoAdapter);
		setListViewTitle("食品安全信息");
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDSafetyInfoFragment.this.getActivity().finish();
			}
		});
		
		loadFragmentPageList();
	}
	
	public void loadFragmentPageList() {
		FDSafetWrapper safetyWrapper = new FDSafetWrapper(context);
		safetyWrapper.getFoodSafetyInfo(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDNoteComment> resultPage = (FDResultPage<FDNoteComment>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDNoteComment> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						safetyInfoList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						safetyInfoList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					safetyInfoAdapter.setSearchTableData(safetyInfoList);
					onListViewLoad(totalRecord);
				}
			}
		});
	}
}
