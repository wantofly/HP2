package com.kiwi.stripes.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kiwi.stripes.JsonTools;
import com.kiwi.stripes.UserSession;
import com.kiwi.stripes.bo.User;
import com.kiwi.stripes.dao.DB;
import com.kiwi.stripes.dao.UserDao;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

@UrlBinding("/act/signUp/{$event}")
public class SignUpAction extends BasicAction implements ValidationErrorHandler
{
	private static Logger logger = LoggerFactory.getLogger(SignUpAction.class);
	
	@ValidateNestedProperties({
		@Validate(field="userName", on={"signUp/jsonsub"}, required=true, minlength=5, maxlength=20),
        @Validate(field="password", on={"signUp/jsonsub"}, required=true, minlength=5, maxlength=20),
		@Validate(field="recommendCode", on={"signUp/jsonsub"}, required=false, minlength=3, maxlength=10),
	})
	private User inputUser;
	
	@Validate(required=true, on={"signUp/smsSend"}, minlength=11, maxlength=11)
	private String mobile;
	private String vcodeup;

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
    public Resolution signUp()
    {
        return new ForwardResolution("/WEB-INF/page/signUp.jsp");
    }
    public static void main(String[] args) {
    	String code = "";
    	java.util.Random r=new java.util.Random(System.currentTimeMillis());
    	for(int i=0;i<10;i++){
    		code = String.valueOf(r.nextInt());
    		code = code.substring(code.length()-6);
    		System.out.println(code);
    	}
    }
    private static String getNewRecommendCode(){
    	String code = "";
    	java.util.Random r=new java.util.Random(System.currentTimeMillis());
    	code = String.valueOf(r.nextInt());
    	code = code.substring(code.length()-6);
    	return code;
    }
    private static String getVCode(){
    	String code = "";
    	java.util.Random r=new java.util.Random(System.currentTimeMillis());
    	code = String.valueOf(r.nextInt());
    	code = code.substring(code.length()-6);
    	return code;
    }
    @HandlesEvent("smsSend") 
    public Resolution smsSend()
    {
    	String vcode = getVCode();
    	logger.info("smsSend -> "+vcode);
    	this.getContext().getRequest().getSession().setAttribute("mobvcode", vcode);
    	System.out.println(vcode);
//    	SendSmsUtil.sendSmsVerificationCode(null,mobile,vcode);
    	return new StreamingResolution("text", JsonTools.getRetJson(0, "success", ""));
    }
    @HandlesEvent("smsVerify") 
    public Resolution smsVerify()
    {
    	String vcode = (String)this.getContext().getRequest().getSession().getAttribute("mobvcode");
		if (vcode != null && vcode.trim().length()==6 && vcode.equals(vcodeup)){
			SqlSession session= DB.get();
	    	try{
	    		UserDao userDao = session.getMapper(UserDao.class);
	        	try{
	        		if ((this.getUser().getStatus() & 2) != 2)
	        			this.getUser().setStatus(this.getUser().getStatus() + 2);
	    			int cnt = userDao.updMobVerified(this.getUser());
	    			if (cnt == 0){
	    				return new StreamingResolution("text", JsonTools.getRetJson(3, "系统错误：12", this.getUser().getUserName()));
	    			}
	        	}catch(Exception ex){
	        		return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", inputUser));
	    		}
	        	return new StreamingResolution("text", JsonTools.getRetJson(0, "success", ""));
	    	} finally {
				if (session != null) {session.close();}
	    	}
		}
    	return new StreamingResolution("text", JsonTools.getRetJson(1, "短信验证错误", ""));
    }
    @HandlesEvent("jsonsub") 
    public Resolution jsonsub()
	{
    	logger.info("signIn submit json: "+this.getContext().getEventName());
    	errMsgList = new ArrayList<String>();
    	User auser;
    	SqlSession session= DB.get();
    	try{
    		UserDao userDao = session.getMapper(UserDao.class);
        	try{
    			auser = userDao.getUser(inputUser);
    			if (auser != null){
    				return new StreamingResolution("text", JsonTools.getRetJson(3, "用户名已经存在", auser.getUserName()));
    			}
        	}catch(Exception ex){
        		return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", inputUser));
    		}
    		
    		// 设置成功登录后的Session和Cookie
    		auser = new User();
    		auser.setUserName(inputUser.getUserName());
    		auser.setPassword(inputUser.getPassword());
    		auser.setStatus(0);
    		auser.setMobile(inputUser.getUserName());
    		auser.setExpiryDay(null);
    		auser.setRecommendCode(getNewRecommendCode());
    		auser.setRecommendFrom(inputUser.getRecommendFrom());
    		auser.setLoginCount(1);
    		auser.setLoginMax(11);
    		auser.setCreateBy("user");
    		auser.setUpdateBy("user");
    		
    		String vcode = (String) this.getContext().getRequest().getSession().getAttribute("mobvcode");
    		if (vcode != null && vcode.trim().length()==6 && vcode.equals(inputUser.getMobcode())){
    			auser.setStatus(2);  //mobile verified.
    		}
    		try{
    			User srcUser = userDao.getRecommendSourceUser(inputUser);
    			if (srcUser != null){
    				auser.setRecommendFrom(String.valueOf(srcUser.getUserId()));
    			}
    			int cnt = userDao.addUser(auser); 
    			auser = userDao.getUser(auser);
    			DB.commit(session);
        	}catch(Exception ex){
        		return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", inputUser));
    		}
    	} finally {
			if (session != null) {session.close();}
    	}
		UserSession userSession = new UserSession();
		userSession.setUser(auser);
		userSession.setSignedIn(true);
		this.setUserSession(userSession);
		
		this.setCookie("userName", auser.getUserName(), 3600*24*7);
		
		return new StreamingResolution("text", JsonTools.getRetJson(0, "success", auser));
	}
    @HandlesEvent("forget") 
    public Resolution forget()
    {
    	logger.info("forget: "+this.getContext().getEventName());
    	errMsgList = new ArrayList<String>();
    	User auser;
    	SqlSession session= DB.get();
    	try{
    		UserDao userDao = session.getMapper(UserDao.class);
    		
    		// 设置成功登录后的Session和Cookie
    		auser = new User();
    		auser.setUserName(inputUser.getUserName());
    		auser.setPassword(inputUser.getPassword());
    		
    		String vcode = (String) this.getContext().getRequest().getSession().getAttribute("mobvcode");
    		if (vcode != null && vcode.trim().length()==6 && vcode.equals(inputUser.getMobcode())){
    			auser.setStatus(2);  //mobile verified.
	    		try{
	    			int cnt = userDao.updPassword(auser);
	    			DB.commit(session);
	    		}catch(Exception ex){
	    			return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", inputUser));
	    		}
    		}else{
    			return new StreamingResolution("text", JsonTools.getRetJson(3, "手机校验码错误", inputUser));
    		}
    	} finally {
    		if (session != null) {session.close();}
    	}
    	UserSession userSession = new UserSession();
    	userSession.setUser(auser);
    	userSession.setSignedIn(true);
    	this.setUserSession(userSession);
    	
    	this.setCookie("userName", auser.getUserName(), 3600*24*7);
    	
    	return new StreamingResolution("text", JsonTools.getRetJson(0, "success", auser));
    }
    
//    @HandlesEvent("submit") 
//    public Resolution signUpSubmit() throws Exception
//    {
//    	System.out.println("userName = "+userName);
//    	logger.debug("+signIn:SignInSubmitAction");
//    	
//    	errMsgList = new ArrayList<String>();
//    	
////    	// 检查冲突
////		User existUser = UserDao.queryUserByName(userName);
////		if(existUser != null) {
////			return this.forwardError("对不起，输入的手机号码已经被注册使用。");
////		}
//		
//		// 注册用户
//		User user = new User();
//		user.setUserName(userName);
//		user.setPassword(passWord);
//		
//		SqlSession session= DB.get();
//    	UserDao userDao = session.getMapper(UserDao.class);
//    	try{
//    		User auser = userDao.getUserLogin(user);
//    		if (auser != null){
////    			return this.forwardError("User Name Exists.");
//    			this.errMsgList.add("用户名已经存在！");
//    			return this.reSignUp();
//    		}
//    		
//    		int cnt = userDao.addUser(user); 
//    		session.commit();
//    		System.out.println(cnt+"/"+user.getUserId());
//    	} catch (Exception ex){
//    		System.out.println(ex.getMessage());
//    	} finally {
//			if (session != null) {session.close();}
//		}
//    	
//		System.out.println("user = "+user);
//		// 设置成功登录后的Session和Cookie
//		
//		UserSession userSession = new UserSession();
//		userSession.setUser(user);
//		userSession.setSignedIn(true);
//		this.setUserSession(userSession);
//		
//		this.setCookie("userName", this.userName, 3600*24*7);
//		
//		System.out.println("Go to Index");
//       	return new RedirectResolution(IndexAction.class);
//    }
    
    private Resolution reSignUp()
    {
		return new ForwardResolution("/WEB-INF/page/signUp.jsp");
    }
    
	public List<String> getErrMsgList()
	{
		return errMsgList;
	}

	public void setErrMsgList(List<String> errMsgList)
	{
		this.errMsgList = errMsgList;
	}

	public User getInputUser() {
		return inputUser;
	}

	public void setInputUser(User inputUser) {
		this.inputUser = inputUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
