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
					<li class="active"><a href="#">进入首页</a></li>
					<li><a href="#about">关于我们</a></li>
					<li><a href="#contact">联系方式</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">更多内容 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">我的信息</a></li>
							<li><a href="#">沪牌日历</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">优惠信息</li>
							<li><a href="#">我的优惠码</a></li>
							<li><a href="#">我的推荐</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

<script type="text/javascript">
<!--
function AddFavorite(sURL, sTitle)
{
    try {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e) {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}

function SetHome(obj,vrl)
{
	try {
		obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
	}
	catch(e) {
		if(window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
			} 
			catch (e) { 
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage',vrl);
		}
    }
}

//-->
</script>


