package com.kiwi.stripes.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.kiwi.stripes.bo.User;

public class DaoTest {
	public static void main(String[] args) throws Exception {
		getUser();
//		addUser();
	}
	
	public static void getUser() throws Exception{
		SqlSession session= DB.get();
		try {
			UserDao userDao = DB.get().getMapper(UserDao.class);  
			User user = new User();  
			user.setUserName("name");  
			User users = userDao.getUserLogin(user); 
			System.out.println(users.getPassword());
		}finally{
			session.close();
		}
	}
	public static void addUser() throws Exception{
		User auser = new User();
		auser.setUserName("name");
		auser.setPassword("pass");
		auser.setLoginCount(3);
		auser.setStatus(1);
		auser.setLoginMax(2);
		auser.setRecommendCode("ss");
		auser.setRecommendFrom("lsl");
		auser.setExpiryDay(new Date());
		auser.setCreateTs(new Date());
		
		SqlSession session= DB.get();
		try {
			UserDao userDao = session.getMapper(UserDao.class);  
			int cnt = userDao.addUser(auser); 
			System.out.println(" ===> " + cnt);
			System.out.println(" ===> " + auser.getUserId());
			DB.commit(session);
		}finally{
			session.close();
		}
	}
}
