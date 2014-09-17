package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

/**
 * 食材原料
 * 
 * @author chengshaohua
 * 
 */
public class FDInputMaterial {
	private int id;
	private String name;                 // 规格
	private String spec;
	private String manufacture;          // 生产企业
	private String placeOfProduction;    // 产地
	private int qualityGuaranteeDays;    // 保质天数
	private int manufactureDateOffset;   // 生产采购间隔天数
	private int status;
	private String productionBarcode;    // 商品条码
	private String barcode;              // 系统生成的条码
	private int typeGeneral;
	private int pTypeCustomId;           // 自定义类别(使用新类别时为null)
	private String pCustomTypeName;      // 自定义类别名称(自动添加新自定义类别使用)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getPlaceOfProduction() {
		return placeOfProduction;
	}

	public void setPlaceOfProduction(String placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}

	public int getQualityGuaranteeDays() {
		return qualityGuaranteeDays;
	}

	public void setQualityGuaranteeDays(int qualityGuaranteeDays) {
		this.qualityGuaranteeDays = qualityGuaranteeDays;
	}

	public int getManufactureDateOffset() {
		return manufactureDateOffset;
	}

	public void setManufactureDateOffset(int manufactureDateOffset) {
		this.manufactureDateOffset = manufactureDateOffset;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProductionBarcode() {
		return productionBarcode;
	}

	public void setProductionBarcode(String productionBarcode) {
		this.productionBarcode = productionBarcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getTypeGeneral() {
		return typeGeneral;
	}

	public void setTypeGeneral(int typeGeneral) {
		this.typeGeneral = typeGeneral;
	}

	public int getpTypeCustomId() {
		return pTypeCustomId;
	}

	public void setpTypeCustomId(int pTypeCustomId) {
		this.pTypeCustomId = pTypeCustomId;
	}

	public String getpCustomTypeName() {
		return pCustomTypeName;
	}

	public void setpCustomTypeName(String pCustomTypeName) {
		this.pCustomTypeName = pCustomTypeName;
	}

}
