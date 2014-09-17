package com.wondersgroup.fdpublicapp.personal.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;

/**
 * @author mengke
 */
public class FDUserCenterLostPwSecAccountFragment extends Fragment {
	
	private TextView nextStepTextView;
	private OnClickListener nextStepOnClickListener;
	
	public FDUserCenterLostPwSecAccountFragment(OnClickListener nextStepOnClickListener){
		super();
		this.nextStepOnClickListener = nextStepOnClickListener;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fd_usercenter_lost_pw_sec_account, null);
        nextStepTextView = (TextView) view.findViewById(R.id.textv_fd_usercenter_lost_pw_sec_account_next);
        nextStepTextView.setOnClickListener(nextStepOnClickListener);
        return view;
    }
	
	/*private OnClickListener nextStepOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			FragmentTransaction transaction = fragmentManager
					.beginTransaction();
			Fragment fragment = new FDUserCenterLostPwSecQuesFragment();
			transaction.replace(R.layout.fd_usercenter_lost_pw_sec_account, fragment);
			transaction.commit();
		}
	};*/
}
