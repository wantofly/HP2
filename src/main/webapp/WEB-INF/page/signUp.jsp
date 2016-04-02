<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/WEB-INF/layout/default.jsp" pageTitle="HPSite" >
<s:layout-component name="html_head">

</s:layout-component>
<s:layout-component name="body">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>沪牌不求人</h1>
			<p>通过本站大家可以轻松拍买到心仪的沪牌咯</p>
		</div>
		<div class="page-header">
			<h1>注册用户</h1>
		</div>

		<div class="row">
			<div class="col-md-6">
				<p>请输入手机号码进行注册。</p>
	<table border="0" width="90%">
	<c:choose>
	<c:when test="${actionBean.errMsgList != null}">
	<c:forEach items="${actionBean.errMsgList}" var="errMsg">
	<tr>
		<td align="left" colspan="0">
<div class="alert alert-danger alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> ${errMsg}
</div>
		</td>
	</tr>
	</c:forEach>
	</c:when>
	</c:choose>
	</table>
			</div>
			<div class="col-md-6">
			
			
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">用户注册</h3>
			  </div>
			  <div class="panel-body">
			    <s:form beanclass="com.kiwi.stripes.actions.SignUpAction">
						<div class="input-group" style="margin-bottom:10px;">
						  <span class="input-group-addon" id="sizing-addon2">用户名</span>
						  <input type="text" class="form-control" name="userName" placeholder="手机号码" aria-describedby="sizing-addon2">
						</div>
						<div class="input-group" style="margin-bottom:10px;">
						  <span class="input-group-addon" id="sizing-addon2">密码</span>
						  <input type="text" class="form-control" name="passWord" placeholder="密码" aria-describedby="sizing-addon2">
						</div>
						<div class="input-group" style="margin-bottom:10px;">
						  <span class="input-group-addon" id="sizing-addon2">密码确认</span>
						  <input type="text" class="form-control" placeholder="确认密码" aria-describedby="sizing-addon2">
						</div>
						<input class="btn btn-info" type="reset" value="Clear"/>
							<s:submit class="btn btn-success" name="submit" value="Login" />
				</s:form>
			  </div>
			</div>
				
			</div>
		</div>


</s:layout-component>

</s:layout-render>

