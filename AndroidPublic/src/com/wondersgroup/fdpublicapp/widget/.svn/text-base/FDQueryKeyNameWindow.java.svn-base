package com.wondersgroup.fdpublicapp.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDKeyWordQueryListener;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.adapter.FDRestaurantSeekNameAdapter;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 餐厅关键字查询
 * @author chengshaohua
 *
 */
public class FDQueryKeyNameWindow extends FDPopupWindow implements OnClickListener, OnEditorActionListener {
	private ListView keyWordListview;
	private FDRestaurantSeekNameAdapter keyWordAdapter;
	private List<FDRestaurant> resultKeyWordList = new ArrayList<FDRestaurant>();
	private LinearLayout listLl;
	private LinearLayout clearLayout;
	private RelativeLayout backImg;
	private TextView clearText;
	private EditText inputEdit;
	private FDKeyWordQueryListener keyWordQueryListener;
	
	public FDQueryKeyNameWindow(Context context, FDKeyWordQueryListener fdKeyWordQueryListener) {
		super(context);
        this.keyWordQueryListener = fdKeyWordQueryListener;
		
		initSeekKeyNameView();
	}

	public void initSeekKeyNameView() {
		View keyNameLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_name_keywords, null);
		listLl=(LinearLayout) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_layout);
		clearLayout=(LinearLayout) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_clear_layout);
		inputEdit=(EditText) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_edit);
		clearText=(TextView) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_deleted_tv);
		backImg = (RelativeLayout) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_rl);
		backImg.setOnClickListener(this);
		clearText.setOnClickListener(this);
		inputEdit.setOnClickListener(this);
		inputEdit.setOnEditorActionListener(this);
		setContentView(keyNameLayout);
		
		keyWordListview = (ListView) keyNameLayout.findViewById(R.id.fd_restaurant_seek_name_keyword_list);
		keyWordListview.setCacheColorHint(Color.TRANSPARENT);
		keyWordAdapter = new FDRestaurantSeekNameAdapter(context, resultKeyWordList);
		keyWordListview.setAdapter(keyWordAdapter);
		keyWordListview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
				if(keyWordQueryListener!=null) {
					FDRestaurant restaurant = (FDRestaurant) adapter.getItemAtPosition(position);
					keyWordQueryListener.onKeyWordCallback(restaurant);
					FDQueryKeyNameWindow.this.dismiss();
				}
			}
		});
	}
	
	public void onClick(View view) {
		if (view == backImg) {
			this.dismiss();
		}
		if(view==inputEdit){
			listLl.setVisibility(View.VISIBLE);
			clearLayout.setVisibility(View.GONE);
		}
		if(view==clearText){
			clearText.setText("没有历史记录");
		}
	}
	
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {  
        switch(actionId){  
        case EditorInfo.IME_NULL:  
            break;  
        case EditorInfo.IME_ACTION_SEND:  
            break;  
        case EditorInfo.IME_ACTION_DONE:  
        	loadSearchDetailList(view.getText().toString().trim());
        }
        return false;
	}
	
	// 餐厅关键字信息
	public void loadSearchDetailList(String keyName) {
		if (keyName == null || "".equals(keyName)) return;

		Map<String,String> keywordParam = new HashMap<String,String>();
		keywordParam.put(FDConst.FD_QUERY_CONDITION_KEYNAME, keyName);
		FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
		searchWrapper.getRestaurant(-1, -1, keywordParam, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDRestaurant> restaurantPage = (FDResultPage<FDRestaurant>) callback;
				resultKeyWordList = restaurantPage.getResultList();
				if (resultKeyWordList != null) {
					if(resultKeyWordList.size()==0) {
						clearLayout.setVisibility(View.VISIBLE);
						clearText.setText("没有查到记录.");
					}else {
						keyWordAdapter.setKeyWordList((ArrayList<FDRestaurant>) resultKeyWordList);
						keyWordAdapter.notifyDataSetChanged();
					}
				}
			}
		});
		
	}
	
	public void setKeyWordQueryListener(FDKeyWordQueryListener keyWordQueryListener) {
		this.keyWordQueryListener = keyWordQueryListener;
	}
	
}
