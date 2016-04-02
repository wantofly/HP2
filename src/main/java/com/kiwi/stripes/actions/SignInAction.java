package com.kiwi.stripes.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kiwi.stripes.Helper;
import com.kiwi.stripes.JsonTools;
import com.kiwi.stripes.UserSession;
import com.kiwi.stripes.bo.User;
import com.kiwi.stripes.dao.DB;
import com.kiwi.stripes.dao.UserDao;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

@UrlBinding("/act/signIn/{$event}")
public class SignInAction extends BasicAction implements ValidationErrorHandler
{
	private static Logger logger = LoggerFactory.getLogger(SignInAction.class);
	
	@Validate(required=true, on={"signIn/submit","signIn/jsonsub"}, minlength=3, maxlength=11)
	private String userName;
	@Validate(required=true, on={"signIn/submit","signIn/jsonsub"}, minlength=3, maxlength=12)
	private String passWord;
	
	private String name;
	private String pass;
	
	private List<String> errMsgList;
	
	public Resolution handleValidationErrors(ValidationErrors errors) throws Exception
    {
		this.errMsgList = new ArrayList<String>();
			
		for(String key : errors.keySet())
		{
			List<ValidationError> errList = errors.get(key);
			for(ValidationError err : errList)
			{
				this.errMsgList.add(err.getMessage(Locale.getDefault()));
			}
		}
		return new StreamingResolution("text", JsonTools.getRetJson(1, this.errMsgList.get(0), this.errMsgList));
    }
	
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
    public Resolution signIn()
    {
        return new ForwardResolution("/WEB-INF/page/signIn.jsp");
    }
    @HandlesEvent("jsonsub") 
    public Resolution jsonsub()
	{
    	errMsgList = new ArrayList<String>();
    	User auser = new User();
    	auser.setUserName(name);
    	auser.setPassword(pass);
    	logger.info("/act/signIn/jsonsub： name " + name);
    	SqlSession session= DB.get();
    	UserDao userDao = session.getMapper(UserDao.class);
    	try{
			auser = userDao.getUserLogin(auser);
    	}catch(Exception ex){
    		return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", auser));
    	} finally {
			if (session != null) {session.close();}
		}
		System.out.println("user = "+auser);
		
		if(auser == null)
			return new StreamingResolution("text", JsonTools.getRetJson(1, "用户名或密码错误", auser));
		
		if(auser.getPassword().equals(Helper.getPassWordCode(this.pass)) == false)
			return new StreamingResolution("text", JsonTools.getRetJson(2, "密码错了", auser));
		
		// 设置成功登录后的Session和Cookie
		
		UserSession userSession = new UserSession();
		userSession.setUser(auser);
		userSession.setSignedIn(true);
		this.setUserSession(userSession);
		
		this.setCookie("userName", this.userName, 3600*24*7);
		
		return new StreamingResolution("text", JsonTools.getRetJson(0, "success", auser));
	}
    
    @HandlesEvent("submit") 
    public Resolution signInSubmit() throws Exception
    {
    	System.out.println("userName = "+userName);
    	logger.info("+signIn:SignInSubmitAction");
    	
    	errMsgList = new ArrayList<String>();
    	User auser = new User();
    	auser.setUserName(userName);
    	auser.setPassword(passWord);
    	SqlSession session= DB.get();
    	UserDao userDao = session.getMapper(UserDao.class);
    	try{
    		auser = userDao.getUserLogin(auser); 
    	} finally {
			if (session != null) {session.close();}
		}
		System.out.println("user = "+auser);
		
		if(auser == null)
		{
			errMsgList.add("登录失败");
			return this.reSignIn();
		}
		
		if(auser.getPassword().equals(Helper.getPassWordCode(this.passWord)) == false)
		{
			errMsgList.add("登录失败");
			return this.reSignIn();
		}
		
		// 设置成功登录后的Session和Cookie
		
		UserSession userSession = new UserSession();
		userSession.setUser(auser);
		userSession.setSignedIn(true);
		this.setUserSession(userSession);
		
		this.setCookie("userName", this.userName, 3600*24*7);
		
		System.out.println("Go to Index");
       	return new RedirectResolution(IndexAction.class);
    }
    
    private Resolution reSignIn()
    {
		return new ForwardResolution("/WEB-INF/page/signIn.jsp");
    }
    
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}
	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public List<String> getErrMsgList()
	{
		return errMsgList;
	}

	public void setErrMsgList(List<String> errMsgList)
	{
		this.errMsgList = errMsgList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
