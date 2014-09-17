package com.wondersgroup.fdpublicapp.search.mode;


/**  
 *  菜肴
 * @author chengshaohua
 *
 */
public class FDDish {
	private int cuisineId;
	private String cuisineName;
	private int cusineType;
	private String cusineTypeValue;
	private boolean selected;
	
	public int getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}
	public String getCuisineName() {
		return cuisineName;
	}
	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}
	public int getCusineType() {
		return cusineType;
	}
	public void setCusineType(int cusineType) {
		this.cusineType = cusineType;
	}
	public String getCusineTypeValue() {
		return cusineTypeValue;
	}
	public void setCusineTypeValue(String cusineTypeValue) {
		this.cusineTypeValue = cusineTypeValue;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
