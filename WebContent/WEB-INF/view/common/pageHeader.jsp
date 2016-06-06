<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 头部开始 -->
<div class="header navbar navbar-inverse navbar-fixed-top" >

	<!-- 开始顶部导航栏 -->
	<div class="header-inner" >
		<!-- 开始logo -->
	<a class="navbar-brand" style="min-width: 317px;"><span >实习经费管理系统<small><small>@理工大学</small></small></span></a>
		<!-- 结束logo -->

		<!-- 开始菜单显示开关响应 -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse"> <img
			src="<c:url value='/images/menu-toggler.png'/>" alt="" />
		</a>
		<!-- 结束菜单显示开关响应 -->

		<!-- 开始顶部导航菜单 -->
		<ul class="nav navbar-nav pull-right">

			<!-- 开始用户登录下拉 -->
			<li class="dropdown user"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"> <img alt=""
					src="<c:url value='/images/defaultHead1.jpg'/>"  />&nbsp; <span
					class="username">${sessionScope.userAuth.username}</span>&nbsp;-&nbsp;
					<span class="username">${sessionScope.userAuth.userRole.name}</span>
					<i class="icon-angle-down"></i>
			</a>
				<ul class="dropdown-menu">
					<!-- <li><a href="extra_profile.html"><i class="icon-user"></i>我的账户</a>
					</li> -->
					<li class="divider"></li>

					<li><a href="<c:url value = '/user/loginout'/>"><i class="icon-off"></i>注销登录</a></li>
				</ul></li>
			<!-- 结束用户登录下拉 -->

		</ul>
		<!-- 开始顶部导航菜单 -->

	</div>
	<!-- 结束顶部导航栏 -->
</div>
<!-- 结束头部 -->