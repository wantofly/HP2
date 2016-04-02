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

@UrlBinding("/act/usersvc/{$event}")
public class UserSvcAction extends BasicAction implements ValidationErrorHandler
{
	private static Logger logger = LoggerFactory.getLogger(UserSvcAction.class);

	private User inputUser;
	private String name;
	
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
		return true;
	}
	
	@Override
	protected boolean needUser(String eventName)
	{
		return true;
	}
	
    @DefaultHandler
    public Resolution signIn()
    {
    	return getBasicInfo();
    }
    
    @HandlesEvent("getbasicinfo") 
    public Resolution getBasicInfo()
	{
    	if (inputUser == null || inputUser.getUserName() == null || inputUser.getUserName().trim().equals("")){
    		return new StreamingResolution("text", JsonTools.getRetJson(20, "用户名为空", ""));
    	}
    	logger.info("/act/signIn/jsonsub： name "+ inputUser.getUserName());
    	if (!inputUser.getUserName().trim().equals(this.getUser().getUserName())){
    		return new StreamingResolution("text", JsonTools.getRetJson(21, "只能查询自己的信息", ""));
    	}
    	
    	SqlSession session= DB.get();
    	UserDao userDao = session.getMapper(UserDao.class);
    	User auser = null;
    	try{
    		auser = userDao.getUser(inputUser);
    	}catch(Exception ex){
    		return new StreamingResolution("text", JsonTools.getRetJson(3, "系统异常", auser));
    	} finally {
			if (session != null) {session.close();}
		}
    	if (auser == null){
    		return new StreamingResolution("text", JsonTools.getRetJson(21, "系统错误：找不到本用户", ""));
    	}
    	auser.setPassword(""); //do not return this.
		return new StreamingResolution("text", JsonTools.getRetJson(0, "success", auser));
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

	public User getInputUser() {
		return inputUser;
	}

	public void setInputUser(User inputUser) {
		this.inputUser = inputUser;
	}

}
