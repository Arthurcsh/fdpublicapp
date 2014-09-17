package com.wondersgroup.fdpublicapp.home.ranking.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  会员排行榜 adapter
 */
public class FDRankingMemberAdapter extends BaseAdapter {

	private Context activity;
	public ArrayList<FDUser> membersList;
	
	public FDRankingMemberAdapter(Context activity,ArrayList<FDUser> memberslist) {
		super();
		this.activity = activity;
		this.membersList = memberslist;
	}

	public int getCount() {
		return membersList==null?0:membersList.size();
	}

	public Object getItem(int position) {
		return membersList==null?null:membersList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDUser rankingMember = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_ranking_member_item, null);
			TextView memberNameText = (TextView) convertView.findViewById(R.id.ranking_member_name_tv);
			if(membersList!=null) {
				rankingMember = membersList.get(position);
				memberNameText.setText(rankingMember.getName());
			}
			convertView.setTag(rankingMember);
		} else {
			rankingMember = (FDUser) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(ArrayList<FDUser> members) {
		this.membersList = members;
		this.notifyDataSetChanged();
	}
}
