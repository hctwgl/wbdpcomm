package com.wbdp.wx.model;

/**
 * 基本资料
 * Created by wisedata005 on 2017/7/5.
 */
public class UserBasicInfo {
    private Integer id;
    /**姓名*/
    private String name;
    /**性别*/
    private Integer gender;
    /**身份证号*/
    private String IDNum;
    /**原来身份证号*/
    private String NIDNum;
    /**婚姻状态*/
    private Integer maritalStatus;
    /**公司名称*/
    private String companyName;
    /**公司省份*/
    private String companyProvince;
    /**公司市*/
    private String companyCity;
    /**公司区*/
    private String companyArea;
    /**公司地址*/
    private String companyAddress;
    /**家庭省份*/
    private String homeProvince;
    /**家庭市*/
    private String homeCity;
    /**家庭区*/
    private String homeArea;
    /**家庭地址*/
    private String homeAddress;
    /**收货省份*/
    private String goodsProvince;
    /**收货市*/
    private String goodsCity;
    /**收货区*/
    private String goodsArea;
    /**收货地址*/
    private String goodsAddress;
    /**学历*/
    private String education;
    /**额度*/
    private Object pollen;
    /**车险保单号*/
    private String insuranceNum;
	/**车牌号*/
	private String plateNum;
	/**保险公司*/
	private String insuranceComp;
	/**是否是电信宽带老用户*/
	private Integer isNoOlder;
	/**
	 * 客户类型，0为其他客户，1为集团公对公客户，2为集团公对私客户
	 */
	private Integer beeType;
	
	public Integer getBeeType() {
		return beeType;
	}

	public void setBeeType(Integer beeType) {
		this.beeType = beeType;
	}

	public Integer getIsNoOlder() {
		return isNoOlder;
	}

	public void setIsNoOlder(Integer isNoOlder) {
		this.isNoOlder = isNoOlder;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getInsuranceComp() {
		return insuranceComp;
	}

	public void setInsuranceComp(String insuranceComp) {
		this.insuranceComp = insuranceComp;
	}

	public String getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public String getNIDNum() {
		return NIDNum;
	}

	public void setNIDNum(String nIDNum) {
		NIDNum = nIDNum;
	}

	public Object getPollen() {
		return pollen;
	}

	public void setPollen(Object pollen) {
		this.pollen = pollen;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getHomeProvince() {
        return homeProvince;
    }

    public void setHomeProvince(String homeProvince) {
        this.homeProvince = homeProvince;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeArea() {
        return homeArea;
    }

    public void setHomeArea(String homeArea) {
        this.homeArea = homeArea;
    }

    public String getGoodsProvince() {
        return goodsProvince;
    }

    public void setGoodsProvince(String goodsProvince) {
        this.goodsProvince = goodsProvince;
    }

    public String getGoodsCity() {
        return goodsCity;
    }

    public void setGoodsCity(String goodsCity) {
        this.goodsCity = goodsCity;
    }

    public String getGoodsArea() {
        return goodsArea;
    }

    public void setGoodsArea(String goodsArea) {
        this.goodsArea = goodsArea;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", gender="
				+ gender + ", IDNum=" + IDNum + ", NIDNum=" + NIDNum
				+ ", maritalStatus=" + maritalStatus + ", companyName="
				+ companyName + ", companyProvince=" + companyProvince
				+ ", companyCity=" + companyCity + ", companyArea="
				+ companyArea + ", companyAddress=" + companyAddress
				+ ", homeProvince=" + homeProvince + ", homeCity=" + homeCity
				+ ", homeArea=" + homeArea + ", homeAddress=" + homeAddress
				+ ", goodsProvince=" + goodsProvince + ", goodsCity="
				+ goodsCity + ", goodsArea=" + goodsArea + ", goodsAddress="
				+ goodsAddress + ", education=" + education + ", pollen="
				+ pollen + ", insuranceNum=" + insuranceNum + ", plateNum="
				+ plateNum + ", insuranceComp=" + insuranceComp + "]";
	}

	
	

}
