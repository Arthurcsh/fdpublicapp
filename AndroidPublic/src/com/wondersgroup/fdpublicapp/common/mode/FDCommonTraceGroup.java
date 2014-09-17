package com.wondersgroup.fdpublicapp.common.mode;

import java.util.List;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;

/**
 * 食材追溯
 * @author chengshaohua
 *
 */
public class FDCommonTraceGroup {

	private int traceType;
	private List<FDIngredientGroup> ingredientGroupsList;
	
	public int getTraceType() {
		return traceType;
	}
	public void setTraceType(int traceType) {
		this.traceType = traceType;
	}
	public List<FDIngredientGroup> getIngredientGroupsList() {
		return ingredientGroupsList;
	}
	public void setIngredientGroupsList(List<FDIngredientGroup> ingredientGroupsList) {
		this.ingredientGroupsList = ingredientGroupsList;
	}
	
	
}
