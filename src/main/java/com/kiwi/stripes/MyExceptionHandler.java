package com.kiwi.stripes;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.cayenne.conf.ServletUtil;

import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.exception.ExceptionHandler;
import net.sourceforge.stripes.exception.StripesServletException;

public class MyExceptionHandler implements ExceptionHandler {

	@Override
	public void init(Configuration configuration) throws Exception {
	}

	@Override
	public void handle(Throwable throwable, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
//		ServletUtil.getSessionContext(request.getSession()).rollbackChanges();
		//TODO:
		System.out.println(throwable.getMessage());
		throw new StripesServletException(throwable);
//		if (AppProperties.isDevMode()) {
//			throw new StripesServletException(throwable);
//		}
//		else {
//			request.setAttribute("exception", throwable);
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
//		}
	}
}
		