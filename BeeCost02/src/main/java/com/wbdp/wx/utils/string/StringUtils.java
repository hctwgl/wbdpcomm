package com.wbdp.wx.utils.string;

/**
 * Created by CatalpaFlat on 2017/7/9.
 */
public class StringUtils {
    /**
     * 隐藏身份证号中间几位（保密）
     * @param idCard
     * @return
     */
    public static String changeIDCardToCover(String idCard){
        return idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})","$1****$2");
    }

    /**
     * 隐藏手机号中间几位（保密）
     * @param phone
     * @return
     */
    public static String changePhoneoCover(String phone){
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }


    /**
     * 隐藏银行卡号中间几位（保密）
     * @param phone
     * @return
     */
    public static String changeIDCardCover(String phone){
    	int length = phone.length();
    	if(length<19)
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{9})","$1****$2");
    	else
    		return phone.replaceAll("(\\d{3})\\d{6}(\\d{10})","$1****$2");
    }
}
