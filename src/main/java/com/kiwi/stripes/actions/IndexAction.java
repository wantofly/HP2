package com.kiwi.stripes.actions;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/act/home")
public class IndexAction extends BasicAction
{
    @DefaultHandler
    public Resolution index()
    {
    	return new ForwardResolution("/WEB-INF/page/index.jsp");
    }
    @HandlesEvent("page")
    public Resolution page()
    {
    	return new ForwardResolution("/WEB-INF/page/index2.jsp");
    }
}
