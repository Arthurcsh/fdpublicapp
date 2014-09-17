package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDFragmentAdapter;
import com.wondersgroup.fdpublicapp.home.safety.adapter.FDCommentAdapter;
import com.wondersgroup.fdpublicapp.home.safety.mode.FDSafetyComment;
import com.wondersgroup.fdpublicapp.home.safety.mode.FDSafetyInfo;
import com.wondersgroup.fdpublicapp.search.views.FDSearchTableListView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  帖子详情
 */
public class FDSchoolNoteDetailActivity extends FDBaseActivity implements OnClickListener{
	private FDSafetyInfo safetyInfoDetail;
	private FDSearchTableListView safetyCommentListView;
	private LJListView xListView;
	private FDFragmentAdapter safetyInfoDetailAdapter;
	private LinearLayout backLayout;
	private TextView safetyTitleView;
	private TextView safetyContentView;
	private ArrayList<FDSafetyComment> commentsList= new ArrayList<FDSafetyComment>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_safety_info_detail);
		
		initNoteDetailDate();
		initNoteDetailView();
	} 

	public void initNoteDetailDate() {
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null && bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) {
			safetyInfoDetail = bundle.getParcelable(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		}
		for(int i=0;i<6;i++) {
			FDSafetyComment comment = new FDSafetyComment();
			comment.setId(i);
			comment.setCommenterName("Luccy");
			comment.setContent("很好的食品安全小知识，收藏了");
			comment.setCommentDate("2013-12-1 12:00");
			commentsList.add(comment);
		}
	}
	
	private void initNoteDetailView() {
		backLayout = (LinearLayout) this.findViewById(R.id.fd_safety_detail_header_back_label);
		safetyTitleView = (TextView) this.findViewById(R.id.safety_detail_title_textview);
		safetyContentView = (TextView) this.findViewById(R.id.safety_detail_content_textview);
		LinearLayout detailCommentLayout = (LinearLayout) this.findViewById(R.id.fd_safety_detail_comment_xml);
		backLayout.setOnClickListener(this);
		
		if(safetyInfoDetail!=null) {
			safetyTitleView.setText(safetyInfoDetail.getTitle());
			safetyContentView.setText(safetyInfoDetail.getContent());
		}
		
		this.safetyCommentListView = new FDSearchTableListView(this);
		this.xListView = (LJListView) safetyCommentListView.getLJListView();
		detailCommentLayout.addView(safetyCommentListView);
		
		FDCommentAdapter commentAdapter = new FDCommentAdapter(this, commentsList);
		xListView.setAdapter(commentAdapter);
		xListView.setOnItemClickListener(null);
		
	}

	public void onClick(View view) {
		if(view == backLayout) {
			this.finish();
		}
	}
	
  	
  	
}
