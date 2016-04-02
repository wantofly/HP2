package com.kiwi.stripes.actions;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/err")
public class ErrorAction extends BasicAction
{
	private String message;
	
	@Override
	protected boolean needUserSignIn(String eventName)
	{
		return false;
	}
	
	@Override
	protected boolean needUser(String eventName)
	{
		return false;
	}
	
    @DefaultHandler
    public Resolution error()
    {
    	if(message == null)
    		message = "-";
    	return new ForwardResolution("/WEB-INF/page/error.jsp");
    }

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
    
    
}
