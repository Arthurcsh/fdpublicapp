package com.wondersgroup.fdpublicapp.search.mode;

import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;

/**
 * 点评餐厅(提交)
 * 
 * @author chengshaohua
 * 
 */
public class FDCommentRestaurant {
	private int companyId;                  // 接受评价的餐馆id
	private int foodFreshLvl;
	private int infoTranspLvl;
	private int tasteLvl;
	private int serviceLvl;
	private int priceLvl;
	private int averageComsumption;
	private int delFlag;
	private FDNoteComment content;
	private String recommendCuisineIds;     // 推荐菜肴id列表
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getFoodFreshLvl() {
		return foodFreshLvl;
	}
	public void setFoodFreshLvl(int foodFreshLvl) {
		this.foodFreshLvl = foodFreshLvl;
	}
	public int getInfoTranspLvl() {
		return infoTranspLvl;
	}
	public void setInfoTranspLvl(int infoTranspLvl) {
		this.infoTranspLvl = infoTranspLvl;
	}
	public int getTasteLvl() {
		return tasteLvl;
	}
	public void setTasteLvl(int tasteLvl) {
		this.tasteLvl = tasteLvl;
	}
	public int getServiceLvl() {
		return serviceLvl;
	}
	public void setServiceLvl(int serviceLvl) {
		this.serviceLvl = serviceLvl;
	}
	public int getPriceLvl() {
		return priceLvl;
	}
	public void setPriceLvl(int priceLvl) {
		this.priceLvl = priceLvl;
	}
	public int getAverageComsumption() {
		return averageComsumption;
	}
	public void setAverageComsumption(int averageComsumption) {
		this.averageComsumption = averageComsumption;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public FDNoteComment getContent() {
		return content;
	}
	public void setContent(FDNoteComment content) {
		this.content = content;
	}
	public String getRecommendCuisineIds() {
		return recommendCuisineIds;
	}
	public void setRecommendCuisineIds(String recommendCuisineIds) {
		this.recommendCuisineIds = recommendCuisineIds;
	}
	
	
}
