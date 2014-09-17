package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.ArrayList;

/**
 *  食材追溯
 * @author chengshaohua
 *
 */
public class FDMealTrace {

	private FDOutputMat info;
	private ArrayList<FDInputBatch> traceList;
	
	public FDOutputMat getInfo() {
		return info;
	}
	public void setInfo(FDOutputMat info) {
		this.info = info;
	}
	public ArrayList<FDInputBatch> getTraceList() {
		return traceList;
	}
	public void setTraceList(ArrayList<FDInputBatch> traceList) {
		this.traceList = traceList;
	}
	
}
