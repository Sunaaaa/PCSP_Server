package com.pcsp.mypcsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pcsp.mypcsp.vo.UserVO;

public class UserDAO {
	private Connection con;

	public UserDAO(Connection con) {
		super();
		this.con = con;
	}
	
	@SuppressWarnings("null")
	public UserVO getUser(String userId, String userPw) {
		
		UserVO userVO = new UserVO(0,"","","","","",0);
		
		try {
			String sql = "select * from user where userid = ? and userpw = ?";			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userVO.setUserNum(rs.getInt("userNum"));
				userVO.setUserName(rs.getString("userName"));
				userVO.setUserId(rs.getString("userId"));
				userVO.setUserPw(rs.getString("userPw"));
				userVO.setUserPhoneNum(rs.getString("userPhoneNum"));
				userVO.setUserAddress(rs.getString("userAddress"));
				userVO.setAreaNum(rs.getInt("areaNum"));
				
			}
			
					
			rs.close();
			pstmt.close();
			
			System.out.println("UserVO");

			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("돈 가져왕" + e.toString());
		}
		return userVO;
	}
	

}
