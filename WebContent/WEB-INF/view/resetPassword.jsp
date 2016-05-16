<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
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
				<a class="brand" href=""><span class="first">重庆理工大学</span> <span
					class="second">实习经费管理系统</span></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
    <div class="span4 offset4 dialog">
        <div class="block">
            <div class="block-heading">重置你的登录密码</div>
            <div class="block-body">
                <form action="user/resetPassword" method="post">
                    <label>电子邮件地址</label>
                    <input type="email" name="email" class="span12"></input>
                    <input type="submit" class="btn btn-primary pull-right" value="重置密码"/>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
        <a href="/internship/href/login">用户登录</a>
    </div>
</div>
	</div>
	<script src="/internship/hlib/bootstrap/js/bootstrap.js"></script>
</body>
</html>