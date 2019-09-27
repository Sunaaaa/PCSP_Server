package com.pcsp.mypcsp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pcsp.mypcsp.stubwebserver.info.CarInfo;


public class CarInfoDAO {
   private Connection con;  
   
   public CarInfoDAO(Connection con) {
      super();
      this.con = con;
   }
   
   @SuppressWarnings("null")
   public ArrayList<CarInfo> getCarInfo(String carNum) {
      ArrayList<CarInfo> carInfoList = new ArrayList<CarInfo>();
      //ads
      //CarInfo carInfo = new CarInfo();
      try {
         
         String sql2 = "select * from car join "
               + "user on car.carOwnerNum = user.userNum where carNum = ?";
         // 차 carNum으로 1개 
         String sql3 = "select * from car "
               + "join user on car.carOwnerNum = user.userNum "
               + "join cartype on car.cartypeNum = carType.carTypenum "
               + "where carNum = ?";
         // 모든 carInfo
         String sql = "select * from car "
               + "join user on car.carOwnerNum = user.userNum "
               + "join cartype on car.cartypeNum = carType.carTypenum";
               
      
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, carNum);
         
         ResultSet rs = pstmt.executeQuery();
         
         while (rs.next()) {
            CarInfo carInfo = new CarInfo();
            carInfo.setCarLat(rs.getString("carLat"));
            carInfo.setCarLong(rs.getString("carLong"));
            carInfo.setCarName(rs.getString("carName"));
            carInfo.setCarNum(rs.getString("carNum"));
            carInfo.setCarRentFee(rs.getString("carRentFee"));
            carInfo.setCarStatus(rs.getString("carStatus"));
            carInfo.setImageURL(rs.getString("carImageURL"));
            carInfo.setUserID(rs.getString("userId"));
            carInfo.setUserName(rs.getString("userName"));
            carInfoList.add(carInfo);
         }
         rs.close();
         pstmt.close();

      }catch (Exception e) {
         // TODO: handle exception
         System.out.println("돈 가져왕" + e.toString());
      }
      return carInfoList;
   }
   
}
