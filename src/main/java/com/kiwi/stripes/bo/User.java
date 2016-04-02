package com.kiwi.stripes.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Kyle
 *CREATE TABLE `user` (
  `userId` varchar(50) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `expiryDay` date DEFAULT NULL,
  `recommendCode` varchar(30) DEFAULT NULL,
  `recommendFrom` varchar(30) DEFAULT NULL,
  `loginCount` int(2) DEFAULT NULL,
  `loginMax` int(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0: not verified 1: verified 2: frozen due to password wrong',
  `createTs` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateTs` datetime DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class User extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static String tableName = "hpuser";
	
	public static long newid = 0;
	
	private String userId;
	private String userName;
	private String password;
	private String mobile;
	private String email;
	private Date expiryDay;
	private String recommendCode;
	private String recommendFrom;
	private int loginCount;
	private int loginMax;
	private int status;
	private Date createTs;
	private Date updateTs;
	private String createBy;
	private String updateBy;
	private String mobcode;

	public long generateId(){
		if (newid == 0)
			return newid = 1+(System.currentTimeMillis()-1457280996713L)/600000;
		return ++newid;
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpiryDay() {
		return expiryDay;
	}
	public void setExpiryDay(Date expiryDay) {
		this.expiryDay = expiryDay;
	}
	public String getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}
	public String getRecommendFrom() {
		return recommendFrom;
	}
	public void setRecommendFrom(String recommendFrom) {
		this.recommendFrom = recommendFrom;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public int getLoginMax() {
		return loginMax;
	}
	public void setLoginMax(int loginMax) {
		this.loginMax = loginMax;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public Date getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getTablename() {
		return tableName;
	}
	public String getMobcode() {
		return mobcode;
	}
	public void setMobcode(String mobcode) {
		this.mobcode = mobcode;
	}
	
	
}
