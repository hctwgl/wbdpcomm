package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Wbl_BeeEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private Long id;

    private String beewx;

    private String phone;

    private String beename;

    private Integer sex;

    private String beecard;

    private String cardimage;

    private Integer marriage;

    private String education;

    private String creatby;

    private String creatdate;

    private String updateby;

    private String updatetime;
    
    private Integer beestatus;
    
    private String company;
    
    private String insurancenum;
    
    private Integer isNoOlder;
   
    private Integer beeType;
    
    private String depart;
    
	//客户地址
    private List<Wbl_BeeaddressEntity> BeeaddressList;
    //客户常用联系人  
    private List<Wbl_LinkmanEntity> LinkmanList;  
    //客户银行资料
    private List<Wbl_BankcardEntity> BankcardList;
    //客户社保信息
    private List<Wbl_SocialdataEntity> SocialdataList;
    //客户划分信用额度
    private List<Wbl_PollenEntity> PollenList;
    
    
    public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getBeestatus() {
		return beestatus;
	}

	public void setBeestatus(Integer beestatus) {
		this.beestatus = beestatus;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeewx() {
        return beewx;
    }

    public void setBeewx(String beewx) {
        this.beewx = beewx == null ? null : beewx.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBeename() {
        return beename;
    }

    public void setBeename(String beename) {
        this.beename = beename == null ? null : beename.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBeecard() {
        return beecard;
    }

    public void setBeecard(String beecard) {
        this.beecard = beecard == null ? null : beecard.trim();
    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage == null ? null : cardimage.trim();
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getCreatby() {
        return creatby;
    }

    public void setCreatby(String creatby) {
        this.creatby = creatby == null ? null : creatby.trim();
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = df.format(creatdate);
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = df.format(updatetime);
    }

	public List<Wbl_BeeaddressEntity> getBeeaddressList() {
		return BeeaddressList;
	}

	public void setBeeaddressList(List<Wbl_BeeaddressEntity> beeaddressList) {
		BeeaddressList = beeaddressList;
	}

	public List<Wbl_LinkmanEntity> getLinkmanList() {
		return LinkmanList;
	}

	public void setLinkmanList(List<Wbl_LinkmanEntity> linkmanList) {
		LinkmanList = linkmanList;
	}

	public List<Wbl_BankcardEntity> getBankcardList() {
		return BankcardList;
	}

	public void setBankcardList(List<Wbl_BankcardEntity> bankcardList) {
		BankcardList = bankcardList;
	}

	public List<Wbl_SocialdataEntity> getSocialdataList() {
		return SocialdataList;
	}

	public void setSocialdataList(List<Wbl_SocialdataEntity> socialdataList) {
		SocialdataList = socialdataList;
	}

	public List<Wbl_PollenEntity> getPollenList() {
		return PollenList;
	}

	public void setPollenList(List<Wbl_PollenEntity> pollenList) {
		PollenList = pollenList;
	}

	public String getInsurancenum() {
		return insurancenum;
	}

	public void setInsurancenum(String insurancenum) {
		this.insurancenum = insurancenum;
	}
    
    
    
}