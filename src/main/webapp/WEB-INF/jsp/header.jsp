<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fruid">
		<div class="navbar navbar-default navbar-fixed-top" role="navigation"
			style="padding-left: 30px;">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="##" data-toggle="dropdown"
					class="dropdown-toggle">作业管理<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="homework/selectAllHomeworkByPage?currentPage=1&act=select">查询作业</a></li>
						<li><a href="homework/toAddHomework">添加作业</a></li>
						<li><a href="homework/selectAllHomeworkByPage?currentPage=1&act=updateSelect">修改作业</a></li>
						<li><a href="homework/selectAllHomeworkByPage?currentPage=1&act=deleteSelect">删除作业</a></li>
					</ul>
				</li>
				<li><a href="card/toUpdatePwd">修改密码</a></li>
				<li><a href="card/loginOut">安全退出</a></li>
			</ul>
		</div>
	</div>
</body>
</html>