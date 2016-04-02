package com.kiwi.stripes;

import net.sourceforge.stripes.action.ActionBeanContext;


public class MyActionBeanContext extends ActionBeanContext
{
	public UserSession getUserSession()
	{
		return (UserSession)getRequest().getSession().getAttribute("userSession");
	}
	public void setUserSession(UserSession session)
	{
		getRequest().getSession().setAttribute("userSession",session);
	}
	public void setNeeduser(String val){
		if (val.equals("needuser"))
			this.getRequest().setAttribute("needuser", true);
		else
			this.getRequest().setAttribute("needuser", false);
	}
	public boolean getNeeduser(String val){
		boolean nu = (Boolean)this.getRequest().getAttribute("needuser");
		return nu;
	}
	public void setNeedroot(String val){
		if (val.equals("needroot"))
			this.getRequest().setAttribute("needroot", true);
		else
			this.getRequest().setAttribute("needroot", false);
	}
	public void setNeedloginagain(String val){
		if (val.equals("needloginagain"))
			this.getRequest().setAttribute("needloginagain", true);
		else
			this.getRequest().setAttribute("needloginagain", false);
	}
}
