package com.wondersgroup.fdpublicapp.home.advert.service;

import android.content.Context;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseWrapper;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDCallbackListenerAdapter;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.FDRestClient;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;

public class FDAdvertWrapper extends FDBaseWrapper {

	public FDAdvertWrapper(Context context) {
		super(context);
	}

	public void getAdvertPage(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_HOME_SAFETY_ADVERT_PAGE)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载食品安全信息..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("safety info page -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDReply> safetyPage = resultParser.getResultPageWithData(FDReply.class);
					callback.onCallback(safetyPage.getResultList());
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
