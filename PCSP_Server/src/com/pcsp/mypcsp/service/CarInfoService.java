package com.pcsp.mypcsp.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.pcsp.mypcsp.dao.CarInfoDAO;
import com.pcsp.mypcsp.stubwebserver.info.CarInfo;

public class CarInfoService {
   public CarInfo getCarInfo(String carNum) {
      Connection con = null;
      CarInfo carInfo = null;
      
      try {
         con = common.DBTemplate2.getConnection();
         System.out.println("MYSQL DATABASE con 가져옴!!!!!!!!! , con : "+ con.toString());
         CarInfoDAO dao = new CarInfoDAO(con);
         
         // 여기 이상함.......................................................
         
         // carInfo = dao.getCarInfo(carNum);
         System.out.println("carInfo : " + carInfo.toString());
      
      }catch (Exception e) {
         // TODO: handle exception
         System.out.println("여기가 널이더냐 : " + e.toString());
      }finally {         
         try {
            con.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("여기가 널이더냐22 : " + e.toString());
            e.printStackTrace();
         }
      }      
      return carInfo;
   }
}



/*
public class UserService {
   
   public UserVO getUser(String userId, String userPw) {
      Connection con = null;
      UserVO userVO = null;
      
      try {
         con = common.DBTemplate2.getConnection();
         System.out.println("MYSQL DATABASE con 가져옴! , con : "+ con.toString());
         UserDAO dao = new UserDAO(con);
         userVO = dao.getUser(userId, userPw);
         System.out.println("userVO : " + userVO.toString());
      
      }catch (Exception e) {
         // TODO: handle exception
         System.out.println("여기가 널이더냐 : " + e.toString());
      }finally {         
         try {
            con.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("여기가 널이더냐22 : " + e.toString());
            e.printStackTrace();
         }
      }      
      return userVO;
   }

}*/