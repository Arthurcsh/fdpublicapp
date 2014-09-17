package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;


/**
 * 通用点评DTO - (营养餐、菜肴)
 * @author chengshaohua
 *
 */
public class FDCommentInfo {
	private int stuMealId;
	private int outputMatId;
	private FDComment content;
	
	public int getStuMealId() {
		return stuMealId;
	}
	public void setStuMealId(int stuMealId) {
		this.stuMealId = stuMealId;
	}
	public int getOutputMatId() {
		return outputMatId;
	}
	public void setOutputMatId(int outputMatId) {
		this.outputMatId = outputMatId;
	}
	public FDComment getContent() {
		return content;
	}
	public void setContent(FDComment content) {
		this.content = content;
	}
	
	
}
