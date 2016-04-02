<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">沪牌不求人</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><s:link beanclass="com.kiwi.stripes.actions.IndexAction">进入首页</s:link></a></li>
					<li><a href="#about">关于我们</a></li>
					<li><a href="#contact">联系方式</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
			        <c:choose>
			        <c:when test="${actionBean.user != null}">
			        <li><a href="#">[ ${actionBean.user.userName} ]</a></li>
			        <li><s:link beanclass="com.kiwi.stripes.actions.SignOutAction">退出登录</s:link></li>
			        </c:when>
			        <c:otherwise>
			        <li><s:link beanclass="com.kiwi.stripes.actions.SignInAction" event=""><s:param name="userName" value="" />用户登录</s:link></li>
			        <li><s:link beanclass="com.kiwi.stripes.actions.SignUpAction" event=""><s:param name="userName" value="" />用户注册</s:link></li>
			        </c:otherwise></c:choose>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多内容 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
							<li><a href="#">我的信息</a></li>
							<li><a href="#">沪牌日历</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">优惠信息</li>
							<li><a href="#">我的优惠码</a></li>
							<li><a href="#">我的推荐</a></li>
							<li><a href="/act/signOut">退出登录</a></li>
						</ul>
			        </li>
			      </ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
