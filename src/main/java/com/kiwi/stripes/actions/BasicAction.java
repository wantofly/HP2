package com.kiwi.stripes.actions;


import javax.servlet.http.Cookie;

import com.kiwi.stripes.MyActionBeanContext;
import com.kiwi.stripes.UserSession;
import com.kiwi.stripes.bo.User;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

public class BasicAction implements ActionBean, ValidationErrorHandler
{
	protected MyActionBeanContext context;
	protected String msg;
	
	public MyActionBeanContext getContext() {
		return context;
	}
	public void setContext(ActionBeanContext arg0) {
		this.context = (MyActionBeanContext)arg0;
	}
	public void setContext(MyActionBeanContext context) {
		this.context = context;
	}
	@Override
	public Resolution handleValidationErrors(ValidationErrors arg0)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ValidationErrors : " + arg0);
		return null;
	}
	protected void addSucMsg(String keyname, String ... params){
		getContext().getMessages("RETMSG").add(new LocalizableMessage(keyname,params));
	}
	protected Resolution forwardError(String message)
	{
		return new ForwardResolution(ErrorAction.class).addParameter("message",message);
	}
	private Resolution gotoSignIn()
	{
		return new ForwardResolution(SignInAction.class,"signInRequest");
	}
	public UserSession getUserSession()
	{
		return this.context.getUserSession();
	}
	
	public void setUserSession(UserSession session)
	{
		this.context.setUserSession(session);
	}
	public User getUser()
	{
		if(this.getUserSession() != null)
			return this.getUserSession().getUser();
		return null;
	}
	public boolean isIn(){
		if(this.getUserSession() != null)
			return true;
		return false;
	}
	protected boolean needUserSignIn(String eventName)
	{
		return false;
	}
	
	protected boolean needUser(String eventName)
	{
		return true;
	}
	
	protected boolean needAdmin(String eventName)
	{
		return false;
	}
	protected void setCookie(String name,String value,int maxAge)
	{
    	Cookie cookie = new Cookie(name,value);
    	cookie.setMaxAge(maxAge);
    	cookie.setPath("/");
    	this.context.getResponse().addCookie(cookie);
	}
	protected String getCookie(String key)
	{
		Cookie[] cookieList = context.getRequest().getCookies();
		if(cookieList != null)
		{
			for(Cookie cookie : cookieList)
			{
				if(cookie.getName().equals(key))
				{
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
}
