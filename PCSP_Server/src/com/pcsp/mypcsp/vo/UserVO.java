package com.pcsp.mypcsp.vo;

public class UserVO {
	int UserNum;
	String userName;
	String userId;
	String userPw;
	String userPhoneNum;
	String userAddress;
	int areaNum;
	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}
		
	public UserVO(int userNum, String userName, String userId, String userPw, String userPhoneNum, String userAddress,
			int areaNum) {
		super();
		UserNum = userNum;
		this.userName = userName;
		this.userId = userId;
		this.userPw = userPw;
		this.userPhoneNum = userPhoneNum;
		this.userAddress = userAddress;
		this.areaNum = areaNum;
	}

	
	
	@Override
	public String toString() {
		return this.getUserNum() + "," + this.getUserName() + "," + this.getUserId() + "," + this.getUserPw() + "," + this.getUserPhoneNum() + "," + this.getUserAddress() + "," + this.getAreaNum();
	}

	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public int getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(int areaNum) {
		this.areaNum = areaNum;
	}
	
	
}
