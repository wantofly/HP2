package com.kiwi.stripes.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiwi.stripes.JsonTools;
import com.kiwi.stripes.addon.MyDateTypeConverter;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

@UrlBinding("/act/{event}/{id}/{fromdate}")
public class MsgListAction extends PagedBasicAction
{
	@Validate(converter=MyDateTypeConverter.class)
	private Date fromdate;
	
	private String id;
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@DefaultHandler
	public Resolution index()
	{
		if (id == null || id.equals("")){
			return new StreamingResolution("text", JsonTools.getRetJson(2, "id not supply."));
		}
		System.out.println(this.fromdate);
		List<Object> list = new ArrayList<Object>();
		
//		list = MessageSvc.instance.getTMessageList(id, fromdate, 4);
		
		return new StreamingResolution("text", JsonTools.getRetJson(0, "success", list));
	}

	@HandlesEvent("dtl")
	public Resolution dtl() {
		if (id == null || id.equals("")){
			return new StreamingResolution("text", JsonTools.getRetJson(2, "id not supply."));
		}
		Object obj = new Object();
//		TMessageWithBLOBs obj = MessageSvc.instance.getTMessage(id);
		
		return new StreamingResolution("text", JsonTools.getRetJson(0, "success", obj));
	}

	public Resolution handleValidationErrors(ValidationErrors validationErrors)
			throws Exception {
		return new ForwardResolution("/views/goods/goodslist.jsp");
	}
	
	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
