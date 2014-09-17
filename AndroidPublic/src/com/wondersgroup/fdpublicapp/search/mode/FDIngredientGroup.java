package com.wondersgroup.fdpublicapp.search.mode;

import java.util.ArrayList;


import android.os.Parcel;
import android.os.Parcelable;

/**
 *  食材原料类集
 * @author chengshaohua
 *
 */
public class FDIngredientGroup implements Parcelable{

	private int id;
	private int type;
	private String name;
	private ArrayList<FDIngredients> ingredients;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<FDIngredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<FDIngredients> ingredients) {
		this.ingredients = ingredients;
	}
	
	public static final Parcelable.Creator<FDIngredientGroup> CREATOR = new Creator<FDIngredientGroup>() {  
        public FDIngredientGroup createFromParcel(Parcel source) {  
        	FDIngredientGroup ingredientGroup = new FDIngredientGroup();  
        	ingredientGroup.id = source.readInt(); 
        	ingredientGroup.type = source.readInt();
        	ingredientGroup.name = source.readString();
        	ingredientGroup.ingredients = source.readArrayList(FDIngredients.class.getClassLoader());
        	
            return ingredientGroup;  
        }  
        public FDIngredientGroup[] newArray(int size) {  
            return new FDIngredientGroup[size];  
        }  
    }; 
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(type);
		dest.writeString(name);
		dest.writeList(ingredients);
	}
	
	
}
