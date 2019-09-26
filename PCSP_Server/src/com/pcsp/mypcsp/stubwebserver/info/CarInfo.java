package com.pcsp.mypcsp.stubwebserver.info;

public class CarInfo {
   String UserID;
   String UserName;

   String carNum;
   String carName;
   String carStatus;
   String carLong;
   String carLat;
   String carRentFee;
   String ImageURL;
   
   
   public CarInfo() {
      super();
   }

   public CarInfo(String userID, String userName, String carNum, String carName, String carStatus, String carLong,
         String carLat, String carRentFee, String imageURL) {
      super();
      UserID = userID;
      UserName = userName;
      this.carNum = carNum;
      this.carName = carName;
      this.carStatus = carStatus;
      this.carLong = carLong;
      this.carLat = carLat;
      this.carRentFee = carRentFee;
      ImageURL = imageURL;
   }

   @Override
   public String toString() {
      return "CarInfo [UserID=" + UserID + ", UserName=" + UserName + ", carNum=" + carNum + ", carName=" + carName
            + ", carStatus=" + carStatus + ", carLong=" + carLong + ", carLat=" + carLat + ", carRentFee="
            + carRentFee + ", ImageURL=" + ImageURL + "]";
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

   public String getCarName() {
      return carName;
   }

   public void setCarName(String carName) {
      this.carName = carName;
   }

   public String getCarStatus() {
      return carStatus;
   }

   public void setCarStatus(String carStatus) {
      this.carStatus = carStatus;
   }

   public String getCarLong() {
      return carLong;
   }

   public void setCarLong(String carLong) {
      this.carLong = carLong;
   }

   public String getCarLat() {
      return carLat;
   }

   public void setCarLat(String carLat) {
      this.carLat = carLat;
   }

   public String getCarRentFee() {
      return carRentFee;
   }

   public void setCarRentFee(String carRentFee) {
      this.carRentFee = carRentFee;
   }

   public String getImageURL() {
      return ImageURL;
   }

   public void setImageURL(String imageURL) {
      ImageURL = imageURL;
   }

}