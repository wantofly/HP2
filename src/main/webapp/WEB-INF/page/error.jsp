<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/WEB-INF/layout/default.jsp" pageTitle="HPSite" >
<s:layout-component name="html_head">

</s:layout-component>
<s:layout-component name="body">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="page-header">
			<h1>错误信息</h1>
		</div>

		<div class="row">
			<div class="col-md-6">
				<p><%=request.getAttribute("javax.servlet.error.message")%></p>
			</div>
		</div>
</s:layout-component>

</s:layout-render>

