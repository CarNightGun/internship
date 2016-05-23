<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<link rel="stylesheet" type="text/css"
	href="/internship/hlib_old/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/internship/hsrc/css/theme.css">
<link rel="stylesheet"
	href="/internship/hlib_old/font-awesome/css/font-awesome.css">

<link rel="stylesheet" href="/internship/hsrc/css/textalign.css">

<script src="/internship/hlib_old/jquery-1.8.1.min.js"
	type="text/javascript"></script>


<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
				</ul>
				<a class="brand" href=""><span class="first">重庆理工大学</span> <span
					class="second">实习经费管理系统</span></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
		<form:form modelAttribute="contentModel" method="post">
			<div class="dialog span4">
				<div class="block">
					<div class="block-heading">用户登录</div>
					<div class="block-body">
							<label class="text-error text-center"><form:errors path="errorLoginInfo" class="field-has-error"></form:errors></label>  
							<label>用户名</label> 
							<form:input path="username" name="username" class="span12" autocomplete="on" placeholder="用户名" /> 
							<label>密码</label> <form:password path="password" name="password" class="span12" autocomplete="off" placeholder="密码"/> 
							<input type="submit"class="btn btn-primary pull-right" value="登录" /> 
							<label class="remember-me"><input type="checkbox" name="remember" value="1">记住我</label>
							<div class="clearfix"></div>
						
					</div>
				</div>
				<div class="row-fluid">
					<p class="span4 gt-left">
						<a href="<c:url value='/internship/href/resetPassword' />" id="forget-password" >忘记密码?</a>
						
					</p>

					<p class="span4 gt-center">
						<%-- <a class="text-error"><form:errors path="errorLoginInfo" class="field-has-error"></form:errors></a> --%>
					</p>
					<p class="span4 gt-right">
						<%-- <a href="<c:url value='/user/register'/> " id="register-btn" target="blank">注册</a> --%>
					</p>
				</div>
			</div>
		</form:form>
		
		</div>
	</div>
	
	<%@ include file="../common/pageFooter.jsp"%>
	
	<script src="/internship/hlib_old/bootstrap/js/bootstrap.js"></script>
</body>
</html>