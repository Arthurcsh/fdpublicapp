package com.wondersgroup.fdpublicapp.common.impl;

import java.util.List;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

public interface FDRestaurantImpl {

	public List<FDRestaurant> getRestaurants();
	
	public void setSearchLocationCondition();
}
