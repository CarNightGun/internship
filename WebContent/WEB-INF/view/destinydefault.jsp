<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>布局</title>

<link type="text/css" href="/internship/default2.css" rel="stylesheet" />
</head>
<body>

<div class="defaut">
	<div class="headbar">
		<h1>导航条</h1>${content }
	</div>
	<div class="mid">
		<div class="list">
			<h2>左侧列表</h2>
			<ul>
				<li><a href="/internship/href/changeContent/t1">个人中心</a></li>
				<li><a href="/internship/href/changeContent/t2">统计报表</a></li>
				<li><a href="/internship/href/changeContent/t3">业务流程</a></li>
			</ul>
		</div>
		
		<div class="content">
			
			<% 
			String content = (String)pageContext.getAttribute("content");
			if(content == null){
			%>
			<h1>欢迎来到demo</h1>
			<%}
			else{
				System.out.println("content:  "+content);
				out.print(content);
			}
			
			
			%>
		</div>
	</div>
	
	<div class="footer">
		脚步
	</div>
</div>


</body>
</html>