<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<link rel="stylesheet" type="text/css"
	href="/internship/hlib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/internship/hsrc/css/theme.css">
<link rel="stylesheet"
	href="/internship/hlib/font-awesome/css/font-awesome.css">

<script src="/internship/hlib/jquery-1.8.1.min.js"
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
				<a class="brand" href=""><span class="first">重庆理工大学</span>
					<span class="second">实习经费管理系统</span></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="dialog span4">
				<div class="block">
					<div class="block-heading">用户登录</div>
					<div class="block-body">
						<form action="user/login" method="post">
							<label>用户名</label> <input type="text" name="username" class="span12"> <label>密码</label>
							<input type="password" name="password" class="span12"> <input
								type="submit" class="btn btn-primary pull-right" value="登录"/> <label
								class="remember-me"><input type="checkbox">记住我</label>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<p class="pull-right" style="">
					<a href="http://www.portnine.com" target="blank">注册</a>
				</p>

				<p>
					<a href="reset-password.html">忘记密码?</a>
				</p>
			</div>
		</div>
	</div>
	<script src="/internship/hlib/bootstrap/js/bootstrap.js"></script>
</body>
</html>