package com.kiwi.stripes;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kiwi.stripes.actions.BasicAction;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

//import com.easedone.jbiandangent.bo.User;

@Intercepts({LifecycleStage.HandlerResolution})
public class SecurityInterceptor implements Interceptor 
{
	static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	/** Intercepts execution and checks that the user has appropriate permissions. */
	public Resolution intercept(ExecutionContext ctx) throws Exception 
	{
		Resolution resolution = ctx.proceed();
		
		if (isPermitted(ctx.getActionBean(), ctx.getActionBeanContext())) {
			return resolution;
		}else if (!isLoggedIn(ctx.getActionBeanContext())) {
			return new StreamingResolution("text", JsonTools.getRetJson(176, "请先登录", ""));
//			return new RedirectResolution("/views/secure/Unauthorized.jsp");
//		}else {
////			return new StreamingResolution("text", JsonTools.getRetJson(1, this.errMsgList.get(0), this.errMsgList));
//
//			return new RedirectResolution("/act/user/login");
		}else{
			return resolution;
		}
	}
	
	/** Returns true if the user is logged in. */
	protected boolean isLoggedIn(ActionBeanContext ctx) {
		if ( ((MyActionBeanContext) ctx).getUserSession() == null) return false;
		if ( ((MyActionBeanContext) ctx).getUserSession().getUser() == null) return false;
		
		return true;
	}
	/** Returns true if the user is permitted to invoke the event requested. */
	protected boolean isPermitted(ActionBean abean, ActionBeanContext ctx) 
	{
		String uri1 = ctx.getRequest().getRequestURI(); 
		String contextPath = ctx.getRequest().getContextPath();
		StringBuffer sb = new StringBuffer();
		String ln = System.lineSeparator();
		sb.append("Event: " + ctx.getEventName()+ ln)
		.append("URI: " + uri1 + ln)
		.append("CtxPath: " + contextPath + ln)
		;
		for (Enumeration<String> e = ctx.getRequest().getParameterNames(); e.hasMoreElements();){
			String n = e.nextElement();
			sb.append("\t"+n + "\t= " + ctx.getRequest().getParameter(n)+ln);
		}
		
		String needuser = (String)ctx.getRequest().getAttribute("needuser");
		String needroot = (String)ctx.getRequest().getAttribute("needroot");
		String needloginagain = (String)ctx.getRequest().getAttribute("needloginagain");
		sb.append("needuser:"+((BasicAction)abean).needUser(ctx.getEventName())+ln)
		.append("needroot:"+needroot+ln)
		.append("needloginagain:"+needloginagain);
		logger.debug(sb.toString());
		
//		return !((MyActionBeanContext)ctx).getNeeduser();
		return !((BasicAction)abean).needUser(ctx.getEventName());
		
//		if (!uri1.endsWith("/"))
//			uri1 = uri1+ "/";
//		if (!(ctx.getEventName().equals("adduser")) 
//				&&(
//				uri1.equals(path2+"/act/user/login/")
//				|| uri1.equals(path2+"/act/user/")
//				|| uri1.startsWith(path2+"/m/")
//				)){
//			return true;
//		}else{
//			User auser = ((MyActionBeanContext)ctx).getUser();
//			if (auser == null){
//				return false;
//			}else{
//				uri1 = uri1.substring(contextPath.length());
//				if (uri1.charAt(uri1.length()-1) != '/'){
//					uri1 = uri1 + "/";
//				}
//				uri1 = uri1+ctx.getEventName()+"/";
//				if (uri1.endsWith("/index/")){
//					uri1 = uri1.substring(0,uri1.length()-6);
//				}
//				if (uri1.endsWith("/index/")){
//					uri1 = uri1.substring(0,uri1.length()-6);
//				}
//				System.out.println("uri1:"+uri1);
//				if (auser.hasRights(uri1)){
//					return true;
//				}
//				return false;
//			}
//		}
	}
	

}
