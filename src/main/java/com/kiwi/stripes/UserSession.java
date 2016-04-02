package com.kiwi.stripes;


import java.io.Serializable;

import com.kiwi.stripes.bo.User;

public class UserSession implements Serializable
{
	public static final int TTL = 3600;
	private static final long serialVersionUID = 6724271668289444338L;
	
	private boolean signedIn;
	private User user;
	
	public UserSession()
	{
		
	}

	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	public boolean isSignedIn()
	{
		return signedIn;
	}

	public void setSignedIn(boolean signedIn)
	{
		this.signedIn = signedIn;
	}

}
