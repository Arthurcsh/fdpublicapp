package com.wondersgroup.fdpublicapp.search.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Administrator
 *  食材原料
 */
public class FDIngredients implements Parcelable {

	private int id;
	private int type;                 // 类型
	private String name;              // 名称
	private String specifications;    // 规格
	private float  weight;            // 重量
	private String purchaseDate;      // 进货日期
	private String guaranteeDate;     // 保质期
	private String manufacturer;      // 生产商
	private String imageFile;
	
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
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getGuaranteeDate() {
		return guaranteeDate;
	}
	public void setGuaranteeDate(String guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	public static final Parcelable.Creator<FDIngredients> CREATOR = new Creator<FDIngredients>() {  
        public FDIngredients createFromParcel(Parcel source) {  
        	FDIngredients ingredient = new FDIngredients();  
        	ingredient.id = source.readInt(); 
        	ingredient.type = source.readInt();
        	ingredient.name = source.readString();
        	ingredient.specifications = source.readString();
        	ingredient.weight = source.readFloat();
        	ingredient.purchaseDate = source.readString();
        	ingredient.guaranteeDate = source.readString();
        	ingredient.manufacturer = source.readString();
        	ingredient.imageFile = source.readString();
        	
            return ingredient;  
        }  
        public FDIngredients[] newArray(int size) {  
            return new FDIngredients[size];  
        }  
    }; 
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(type);
		dest.writeString(name);
		dest.writeString(specifications);
		dest.writeFloat(weight);
		dest.writeString(purchaseDate);
		dest.writeString(guaranteeDate);
		dest.writeString(manufacturer);
		dest.writeString(imageFile);
	}   
	
	
}
