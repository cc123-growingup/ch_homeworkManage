<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
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
<link rel="stylesheet" href="static/css/bootstrap.min.css" />
</head>
<body>
	<!-- 加载header.jsp -->
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<br><br><br>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">作业详情</h3>
			</div>
			<div class="panel-body">
				<div class="table table-responsive">
					<table class="table table-bordered table-hover">
						<tbody class="text-center">
							<tr>
								<th>姓名</th>
								<td>${homework.name}</td>
							</tr>
							<tr>
								<th>电话</th>
								<td>${homework.telephone}</td>
							</tr>
							<tr>
								<th>E-Mail</th>
								<td>${homework.email}</td>
							</tr>
							<tr>
								<th>专业</th>
								<td>${homework.major}</td>
							</tr>
							<tr>
								<th>角色</th>
								<td>${homework.post}</td>
							</tr>
							<tr>
								<th>地址</th>
								<td>${homework.address}</td>
							</tr>																																										
							<tr>
								<th>文件</th>
									<td>
										${homework.homework}
										<input id = "${homework.homework}" class="namelist" type="button" value="下载" onclick="downname(this)">
									</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>