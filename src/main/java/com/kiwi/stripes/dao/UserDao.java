package com.kiwi.stripes.dao;

import java.util.List;

import com.kiwi.stripes.bo.User;

public interface UserDao 
{
	 public User getUser(User user) throws Exception;
	 public User getUserLogin(User user) throws Exception;
	 public List<User> getUsers(User user) throws Exception;
	 public User getRecommendSourceUser(User user) throws Exception;
	 
	 public int addUser(User user) throws Exception;
	 public int updMobVerified(User user) throws Exception;
	 public int updPassword(User user) throws Exception;
}
