<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			<div class="span4 offset4 dialog">
				<div class="block">
					<div class="block-heading">注册</div>
					<div class="block-body">
						<form>
							<label>First Name</label> <input type="text" class="span12">
							<label>Last Name</label> <input type="text" class="span12">
							<label>Email Address</label> <input type="text" class="span12">
							<label>用户名</label> <input type="text" class="span12">
							<label>密码</label> <input type="password" class="span12">
							<a href="index.html" class="btn btn-primary pull-right">Sign
								Up!</a> <label class="remember-me"><input type="checkbox">
								I agree with the <a href="terms-and-conditions.html">Terms
									and Conditions</a></label>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<p>
					<a href="privacy-policy.html">Privacy Policy</a>
				</p>
			</div>
		</div>
	</div>
	<script src="/internship/hlib/bootstrap/js/bootstrap.js"></script>
</body>
</html>