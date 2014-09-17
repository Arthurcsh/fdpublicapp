package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.ArrayList;
import java.util.Date;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  出产品菜肴
 * @author chengshaohua
 *
 */
public class FDOutputMat implements Parcelable{
	private int id;
	private int typeGeneral;               // 产出品分类
	private String name;                   // 名称
	private String spec;                   // 规格
	private int guaranteeValue;            // 保质期
    private int guaranteeUnit;             // 保质期单位类型
    private String guaranteeUnitValue;     // 保质期单位
    private int cuisineType;               // 菜肴分类
    private String placeOfProduction;      // 产地
    private float qty;                     // 重量
    private float outputMatUsedQty;        // 营养餐中该产出品的数量，qty是单个产出品的重量
    private int linkedOutputMatId;         // 营养餐中产出品的id，当此id不为0时，说明此产出品已经被营养餐匹配
    private String manufacture;            // 生产企业
    private Date createDate;               // 创建时间
    private String mainMat;                // 主材
    private String subMat;                 // 辅材
    private String description;            // 描述
    private ArrayList<FDImage> picList;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeGeneral() {
		return typeGeneral;
	}
	public void setTypeGeneral(int typeGeneral) {
		this.typeGeneral = typeGeneral;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getGuaranteeUnit() {
		return guaranteeUnit;
	}
	public void setGuaranteeUnit(int guaranteeUnit) {
		this.guaranteeUnit = guaranteeUnit;
	}
	public int getGuaranteeValue() {
		return guaranteeValue;
	}
	public void setGuaranteeValue(int guaranteeValue) {
		this.guaranteeValue = guaranteeValue;
	}
	public String getGuaranteeUnitValue() {
		return guaranteeUnitValue;
	}
	public void setGuaranteeUnitValue(String guaranteeUnitValue) {
		this.guaranteeUnitValue = guaranteeUnitValue;
	}
	public int getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(int cuisineType) {
		this.cuisineType = cuisineType;
	}
	public String getPlaceOfProduction() {
		return placeOfProduction;
	}
	public void setPlaceOfProduction(String placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getOutputMatUsedQty() {
		return outputMatUsedQty;
	}
	public void setOutputMatUsedQty(float outputMatUsedQty) {
		this.outputMatUsedQty = outputMatUsedQty;
	}
	public int getLinkedOutputMatId() {
		return linkedOutputMatId;
	}
	public void setLinkedOutputMatId(int linkedOutputMatId) {
		this.linkedOutputMatId = linkedOutputMatId;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMainMat() {
		return mainMat;
	}
	public void setMainMat(String mainMat) {
		this.mainMat = mainMat;
	}
	public String getSubMat() {
		return subMat;
	}
	public void setSubMat(String subMat) {
		this.subMat = subMat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(ArrayList<FDImage> picList) {
		this.picList = picList;
	}


	public static final Parcelable.Creator<FDOutputMat> CREATOR = new Creator<FDOutputMat>() {  
        public FDOutputMat createFromParcel(Parcel source) {  
        	FDOutputMat outputMat = new FDOutputMat();  
        	outputMat.id = source.readInt(); 
        	outputMat.typeGeneral = source.readInt(); 
        	outputMat.name = source.readString();
        	outputMat.spec = source.readString();
        	outputMat.guaranteeValue = source.readInt();
        	outputMat.guaranteeUnit = source.readInt();
        	outputMat.guaranteeUnitValue = source.readString();
        	outputMat.cuisineType = source.readInt();
        	outputMat.placeOfProduction = source.readString();
        	outputMat.qty = source.readFloat();
        	outputMat.outputMatUsedQty = source.readFloat();
        	outputMat.linkedOutputMatId = source.readInt();
        	outputMat.manufacture = source.readString();
        	outputMat.mainMat = source.readString();
        	outputMat.subMat = source.readString();
        	outputMat.description = source.readString();
        	outputMat.picList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return outputMat;  
        }  
        public FDOutputMat[] newArray(int size) {  
            return new FDOutputMat[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(typeGeneral);
		dest.writeString(name);
		dest.writeString(spec);
		dest.writeInt(guaranteeValue);
		dest.writeInt(guaranteeUnit);
		dest.writeString(guaranteeUnitValue);
		dest.writeInt(cuisineType);
		dest.writeString(placeOfProduction);
		dest.writeFloat(qty);
		dest.writeFloat(outputMatUsedQty);
		dest.writeInt(linkedOutputMatId);
		dest.writeString(manufacture);
		dest.writeString(mainMat);
		dest.writeString(subMat);
		dest.writeString(description);
		dest.writeList(picList);
	}
    
    
}
