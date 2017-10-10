package com.wbdp.wx.enums;

/**
 * Created by wisedata005 on 2017/7/4.
 */
public enum ResultEnum {

    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    NOCODE(1,"code为空"),
    NOOPPENDID(1,"opendId获取失败"),
    EMPTPAHONE(2,"手机号为空"),
    SENTMESSAGE_ERROR(3,"短信发送失败"),
    EMPTVERIFIECODE(4,"验证码为空"),
    EMPTVOPPENDID(5,"微信用户openid为空"),
    ERRORVERIFIECODE(6,"短信验证码错误"),
    EMTYUSERBASICINFO(7,"基本资料为空"),
    EMTNAME(8,"姓名为空"),
    EMTGENDER(9,"性别为空"),
    EMTYTIDNUM(10,"身份证号为空"),
    EMTYMARITALSTATUS(11,"婚姻状况为空"),
    EMTYCOMPANYNAME(12,"公司名称为空"),
    EMTYCOMPANYADDRESS(13,"公司地址为空"),
    EMTYHOMEADDRESS(14,"家庭地址为空"),
    EMTGOODSADDRESS(15,"收货地址为空"),
    EMTEDUCATION(16,"学历为空"),
    EMTYUSERCONTACTS(17,"常用联系人为空"),
    EMTYRELATIONSHIP(18,"关系为空"),
    EMTYPHONE(19,"手机号为空"),
    EMTALLSSA(20,"社保账号全为空"),
    EMTSSA(21,"社保账号为空"),
    EMTPASSWORD(22,"社保密码为空"),
    EMTYUSERBANKCARD(23,"银行卡全为空"),
    EMTYBANKTYPE(24,"银行卡类型为空"),
    EMTYBANKCARD(25,"银行卡号为空"),
    EMTYIDCRADPOSITIVE(26,"身份证正面为空"),
    EMTYIDCRADWRONGSIDE(27,"身份证反面为空"),
    EMTYCOMPANYPROVINCE(28,"公司省份为空"),
    EMTYCOMPANYCITY(29,"公司市为空"),
    EMTYCOMPANYAREA(30,"公司区为空"),
    EMTYHOMEPROVINCE(31,"家庭省份为空"),
    EMTYHOMECITY(32,"家庭市为空"),
    EMTYHOMEAREA(33,"家庭区为空"),
    EMTYGOODSPROVINCE(34,"收货省份为空"),
    EMTYGOODSCITY(35,"收货市为空"),
    EMTYGOODSAREA(36,"收货区为空"),
    EMTYOPENBANK(37,"开户行为空"),
    APPLYFAILURE(38,"申请失败"),
    UPDATEERROR(39,"修改失败"),
    ADDERROR(40,"添加失败"),
    EMTYORDER(41,"订单为空"),
    EMTYSALEMAN(42,"业务员为空"),
    NOREGISTER(43,"未注册"),
    ISBLACKBEE(44,"黑名单"),
    NOCREDITCARD(45,"信用卡全为空"),
    NOCREDITCARD1(46,"信用卡号为空"),
    NOCREDITCARD2(47,"银行卡名空"),
    EMTYINSNUM(48,"车险保单号名空"),
    EMTYSSINCC(49,"额度信息为空"),
    AUDITFAILED(50,"资料审核未通过"),
    PUSHEMTY_ERROR(51,"订单推送实体为空"),
    PRIMATY_SHCOOL(100,"你可能还在上小学"),
    MIDDLE_SHCOOL(101,"你可能还在上初中"),
    ORDERSTATUSEMPTY(53,"订单状态为空"), 
    GOODNAMEEMPTY(54,"商品名为空"),
    GOODATTRIBUTEEMPTY(55,"商品属性为空"),
    PUSHJSONEMTY_ERROR(52,"订单推送json解析错误");
    
    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }
}
