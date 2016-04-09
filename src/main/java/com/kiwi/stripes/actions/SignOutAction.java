package com.kiwi.stripes.actions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kiwi.stripes.JsonTools;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/act/signOut/{$event}")
public class SignOutAction extends BasicAction
{
	private static Logger logger = LoggerFactory.getLogger(SignOutAction.class);
	
	@Override
	public boolean needUserSignIn(String eventName)
	{
		return false;
	}
	
	@Override
	public boolean needUser(String eventName)
	{
		return false;
	}
	@Override
	public boolean needAdmin(String eventName)
	{
		return false;
	}
	
	
    @DefaultHandler
    public Resolution signOut()
    {
    	logger.info("+signOut:SignOutAction");
    	this.setCookie("userName",null,0);
		this.setUserSession(null);
        return new RedirectResolution(IndexAction.class);
    }
    
    @HandlesEvent("json")
    public Resolution signOutJson()
    {
    	logger.info("+signOut:SignOutAction");
    	
    	this.setCookie("userName",null,0);
		this.setUserSession(null);
		
		return new StreamingResolution("text", JsonTools.getRetJson(0, "成功退出", ""));
    }
}
