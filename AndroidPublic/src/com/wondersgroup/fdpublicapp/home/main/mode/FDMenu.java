package com.wondersgroup.fdpublicapp.home.main.mode;

import java.util.ArrayList;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author chengshaohua
 *   餐厅菜单
 */
public class FDMenu implements Parcelable{

	private int id;
	private int type;
	private int sortNumber;
	private String name;
	private float price;
	private String description;
	private FDCuisine cuisine;
	private ArrayList<FDIngredientGroup> ingredientGroupList;
	private ArrayList<FDImage> menuImages;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public FDCuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(FDCuisine cuisine) {
		this.cuisine = cuisine;
	}
	public ArrayList<FDIngredientGroup> getIngredientGroupList() {
		return ingredientGroupList;
	}
	public void setIngredientGroupList(
			ArrayList<FDIngredientGroup> ingredientGroupList) {
		this.ingredientGroupList = ingredientGroupList;
	}
	
	public ArrayList<FDImage> getMenuImages() {
		return menuImages;
	}
	public void setMenuImages(ArrayList<FDImage> menuImages) {
		this.menuImages = menuImages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static final Parcelable.Creator<FDMenu> CREATOR = new Creator<FDMenu>() {  
        public FDMenu createFromParcel(Parcel source) {  
        	FDMenu menu = new FDMenu();  
        	menu.id = source.readInt(); 
        	menu.type = source.readInt();
        	menu.sortNumber = source.readInt();
        	menu.name = source.readString();
        	menu.price = source.readFloat();
        	menu.description = source.readString();
        	menu.ingredientGroupList = source.readArrayList(FDIngredientGroup.class.getClassLoader());
        	menu.menuImages = source.readArrayList(FDImage.class.getClassLoader());
        	
            return menu;  
        }  
        public FDMenu[] newArray(int size) {  
            return new FDMenu[size];  
        }  
    }; 
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(type);
		dest.writeInt(sortNumber);
		dest.writeString(name);
		dest.writeFloat(price);
		dest.writeString(description);
		dest.writeList(ingredientGroupList);
		dest.writeList(menuImages);
	}
}
