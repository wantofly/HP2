<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<stripes:layout-definition>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>${pageTitle}</title>

<!-- Bootstrap core CSS -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<!-- link href="../../dist/css/bootstrap-theme.min.css" rel="stylesheet" -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Custom styles for this template -->
<link type="text/css" rel="stylesheet" href="/css/default.css" />
<link type="text/css" rel="stylesheet" href="/css/font.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
<stripes:layout-component name="html_head" />
</head>
<body role="document" class="font-hei">

	<jsp:include page="/WEB-INF/frag/nav.jsp" />
	
	<div class="container theme-showcase" role="main">
	
		<stripes:layout-component name="body" />
	</div>
	<jsp:include page="/WEB-INF/frag/footer.jsp" />

</body>
</html>
</stripes:layout-definition>