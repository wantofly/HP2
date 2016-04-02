<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/WEB-INF/layout/default.jsp" pageTitle="HPSite" >
<s:layout-component name="html_head">
	<link type="text/css" rel="stylesheet" href="css/index.css" />
</s:layout-component>
<s:layout-component name="body">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="row">
				<div class="col-md-3">
					<div class="span5">
						<img id="qrcode-index-id" src="img/qrCode.jpg" alt="打开微信，扫一扫，关注我们。"/>
					</div>
				</div>
				<div class="col-md-7">
					<h2>沪牌不求人</h2>
					<p>我们致力于帮助广大沪牌竞拍者更及时有效了解沪牌最新资讯，提供超一流拍牌不求人软件和大数据分析策略，帮助您拍得心仪沪牌哦！</p>
				</div>
			</div>

		</div>


		<%--<div class="page-header">--%>
			<div class="row">
				<div class="col-md-5 page-header">
					<h3>拍牌历史数据</h3>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-6 page-header">
					<h3>拍牌流程</h3>
				</div>
			</div>

		<%--</div>--%>

		<div class="row">
			<div class="col-md-5">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>月份</th>
							<th>参与人数</th>
							<th>投放额度</th>
							<th>最低成交价格</th>
							<th>命中率</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2016年2月</td>
							<td align="right">196470</td>
							<td align="right">8363</td>
							<td align="right">83,200</td>
							<td>4.25%</td>
						</tr>
						<tr>
							<td>2016年1月</td>
							<td align="right">187533</td>
							<td align="right">9409</td>
							<td align="right">82,200</td>
							<td>5.01%</td>
						</tr>
						<tr>
							<td>2015年12月</td>
							<td align="right">179133</td>
							<td align="right">7698</td>
							<td align="right">84,500</td>
							<td>4.29%</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-6">
				<div class="list-group">
					<a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414055485&idx=1&sn=7299713ba5b6538dceb022c601b35ac4#rd" class="list-group-item" target="_Blank">
						<h5 class="list-group-item-heading"><b>预备篇</b></h5>
						<p class="list-group-item-text">如果你是还木有买过标书的拍牌小白，或者又要去国拍行续费的拍牌老鸟，本篇教你国拍行半日游各类注意事项。</p>
					</a> <a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414092628&idx=1&sn=2346b7a6041d2d27ede7fc79e3049816#rd" class="list-group-item" target="_Blank">
						<h5 class="list-group-item-heading"><b>网拍篇</b></h5>
						<p class="list-group-item-text">继上一篇沪牌竞拍流程预备篇，今天我们来带你领略一下每个月那激动人心的“乐透”一小时拍牌时间要做什么，那最最令人心跳加速又神往不已的11点29分开始的一分钟又要做什么呢？请点我。</p>
					</a> <a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414457016&idx=1&sn=73ea8af871042a652dce0bfd9eaa5886#rd" class="list-group-item" target="_Blank">
						<h5 class="list-group-item-heading"><b>原则篇</b></h5>
						<p class="list-group-item-text">不理解原则，何以拍沪牌？本篇是针对“沪牌竞拍流程网拍篇”的补充篇，又名“原则篇”。我们会基于修改出价界面左边栏的重要信息先做个详解，从而引出沪牌竞拍的成交原则。</p>
					</a>
				</div>
			</div>
		</div>

		<div class="page-header">
			<h2>技巧</h2>
		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="span4">
					<div class="media">
						<a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414130524&idx=1&sn=054966dd0aecfa131177a50c664da6e5#rd" class="pull-left" target="_Blank">
							<img src="img/strategy1.jpg" class="media-object" alt='' /></a>
						<div class="media-body">
							<h4 class="media-heading">沪牌竞拍策略传统篇</h4>
							本篇讲述了常见的三种沪牌竞拍策略，是我们讨论其他进阶和骨灰级策略的学习基础。
						</div>
					</div>
					<div class="media">
						<a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414504409&idx=1&sn=2ec257520dc208b5752b197921125d50#rd" class="pull-left" target="_Blank">
							<img src="img/strategy2.jpg" class="media-object" alt='' /></a>
						<div class="media-body">
							<h4 class="media-heading">为什么沪牌这么难拍中—各有各的死法</h4>
							在理解了沪牌这个非传统的竞拍成交原则,“价高优先，同价则时间优先”之后，我们来看一下为什么沪牌这么难拍中？大家又都是挂在了哪个环节?
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-4">
				<div class="span6">

					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">黄牛为什么愿意拍不中倒贴钱给你</h3>
						</div>

							<table class="table table-bordered ">
								<tr><td><a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414204432&idx=1&sn=7db8fc1370e828624d23f46c5d240042#rd" target="_Blank">
									&nbsp;&nbsp;先来听一个狗蛋和他老婆小红妹妹的有趣故事吧
								</a></td></tr>
								</tr><td><a href="http://mp.weixin.qq.com/s?__biz=MzA5Njk5NDI0MA==&mid=414268040&idx=1&sn=2bd78ef5ebdef03a59fced5dc72d8119#rd" target="_Blank">
									&nbsp;&nbsp;看完了上面的故事，我们来听听这个中原因吧
								</a></td></tr>
							</table>

					</div>



				</div>
			</div>
		</div>
<%--
		<div class="page-header">
			<h1>内容</h1>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
			</div>
			<!-- /.col-sm-4 -->
			<div class="col-sm-4">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
			</div>
			<!-- /.col-sm-4 -->
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">Panel content</div>
				</div>
			</div>
			<!-- /.col-sm-4 -->
		</div>--%>

		<div class="page-header">
			<h1>成功案例</h1>
		</div>
		<div class="well">
			<p>这是一段非常具有传奇色彩的成功案例，宇文化及通过软件拍到了98张沪牌。</p>
		</div>

		<div class="page-header">
			<h1>跑马灯</h1>
		</div>
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img data-src="holder.js/1140x500/auto/#777:#555/text:First slide"
						alt="First slide">
				</div>
				<div class="item">
					<img data-src="holder.js/1140x500/auto/#666:#444/text:Second slide"
						alt="Second slide">
				</div>
				<div class="item">
					<img data-src="holder.js/1140x500/auto/#555:#333/text:Third slide"
						alt="Third slide">
				</div>
			</div>
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>


</s:layout-component>

</s:layout-render>

