package com.wbdp.wx.model;

/**
 * 银行卡
 * Created by wisedata005 on 2017/7/5.
 */
public class UserBankCard {
    /**银行类型*/
    private String bankType;
    /**银行卡号*/
    private String bankCard;
    /**原银行卡号*/
    private String NbankCard;
    /**开户行*/
    private  String openBank;

    public String getNbankCard() {
		return NbankCard;
	}

	public void setNbankCard(String nbankCard) {
		NbankCard = nbankCard;
	}

	public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

	@Override
	public String toString() {
		return "[bankType=" + bankType + ", bankCard=" + bankCard
				+ ", NbankCard=" + NbankCard + ", openBank=" + openBank + "]";
	}

    
}
