package com.wondersgroup.fdpublicapp.common.service;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;

import android.content.Context;
import android.util.Log;

public class FDClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private Context context;
	
	public FDClientHttpRequestInterceptor(Context context) {
		this.context = context;
	}
	
	public ClientHttpResponse intercept(HttpRequest request, byte[] byteArray, ClientHttpRequestExecution execution) throws IOException {

	    // add deviceSn to header
//	    request.getHeaders().add("deviceSn", (String)ServiceManager.getServerHash().get(ServiceConst.FD_DEVICE_DEFAULT_SN));
		FDAppContext application = (FDAppContext) context.getApplicationContext();
	    if(application!=null && application.getLoginInfo()!=null) {
	    	request.getHeaders().add("token", application.getLoginInfo().getUsername());
			System.out.println("Header username verify  ########################################### "+application.getLoginInfo().getUsername());
	    }
	    // set default media type
	    if(request.getMethod().equals(HttpMethod.POST)&& request.getHeaders().getContentType() !=null && !request.getHeaders().getContentType().getType().equals("multipart")){
	    	Charset utf8 = Charset.forName("UTF-8");
			MediaType mediaType = new MediaType("application", "json", utf8);
			request.getHeaders().setContentType(mediaType);
	    }
	    // execute the request
	    ClientHttpResponse response = execution.execute(request, byteArray);
	    return response;
	}
}
