package com.pcsp.mypcsp.stubwebserver.info;

public class CarInfo {
	   String UserID ;
	   String UserName;
	   String carNum;
	   public CarInfo() {};
	   public CarInfo(String userID, String userName, String carNum) {
	      super();
	      UserID = userID;
	      UserName = userName;
	      this.carNum = carNum;
	   }
	   public String getUserID() {
	      return UserID;
	   }
	   public void setUserID(String userID) {
	      UserID = userID;
	   }
	   public String getUserName() {
	      return UserName;
	   }
	   public void setUserName(String userName) {
	      UserName = userName;
	   }
	   public String getCarNum() {
	      return carNum;
	   }
	   public void setCarNum(String carNum) {
	      this.carNum = carNum;
	   }
}
