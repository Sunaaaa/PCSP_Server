package com.pcsp.mypcsp.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.pcsp.mypcsp.dao.UserDAO;
import com.pcsp.mypcsp.vo.UserVO;

public class UserService {
	
	public UserVO getUser(String userId, String userPw) {
		Connection con = null;
		UserVO userVO = null;
		
		try {
			con = common.DBTemplate2.getConnection();
			
			System.out.println("MYSQL DATABASE con 가져옴!!!!");
			System.out.println("con이 뭐드라" + con.toString());
			 
			UserDAO dao = new UserDAO(con);
			userVO = dao.getUser(userId, userPw);
			
			System.out.println("null1" + userVO.toString());

			System.out.println("null2");
			
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

}
