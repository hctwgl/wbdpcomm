package com.wbdp.tsp.entity;

import java.sql.Date;

/**
 * 
 * @author wisedata004
 *      wbdp_user表实体类
 */
public class UserEntity {

	private int ID;               //ID
	private String UserName;      //用户名
	private String Password;      //用户密码
	private String Name;          //用户姓名
	private String ContactTel;    //联系电话
	private String UserWX;        //用户微信号
	private String Email;         //邮箱
	private int UserStatus;       //用户状态—1：正常；2. 暂停
	private int CustomerID;       //客户ID
	private int UserType;         //客户类型—1：维泽管理员；2：维泽客服；3：客户管理员；4：客户用户
    private Date SaveDate;        //录入时间
    private Date UpdateDate;      //更新时间
    private String EditBy;        //录入者  
    
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContactTel() {
		return ContactTel;
	}
	public void setContactTel(String contactTel) {
		ContactTel = contactTel;
	}
	public String getUserWX() {
		return UserWX;
	}
	public void setUserWX(String userWX) {
		UserWX = userWX;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getUserStatus() {
		return UserStatus;
	}
	public void setUserStatus(int userStatus) {
		UserStatus = userStatus;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getUserType() {
		return UserType;
	}
	public void setUserType(int userType) {
		UserType = userType;
	}
	public Date getSaveDate() {
		return SaveDate;
	}
	public void setSaveDate(Date saveDate) {
		SaveDate = saveDate;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
	public String getEditBy() {
		return EditBy;
	}
	public void setEditBy(String editBy) {
		EditBy = editBy;
	}
	
}
