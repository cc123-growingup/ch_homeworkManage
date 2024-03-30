<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<div class="bg-primary"  style="width:70%; height: 60px;padding-top: 0.5px;">
	       <h3 align="center">添加作业</h3>
	   </div><br>
		<form:form action="homework/addHomework?act=add" method="post" class="form-horizontal" modelAttribute="homework"  enctype="multipart/form-data" >
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">姓名</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入姓名" path="name"/>
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">电话号码</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入电话" path="telephone"/>
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">E-Mail</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入E-Mail" path="email"/>
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">专业</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入专业" path="major"/>
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">角色</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入角色（教师/学生）" path="post"/>
				</div>
			</div>
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">地址</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" placeholder="请输入地址" path="address"/>
				</div>
			</div>
			<div class="form-group has-success">
				  <label class="col-sm-2 col-md-2 control-label">文件</label>
				  <div class="col-sm-4 col-md-4">
		  				<input type="file" placeholder="请选择需要上传的文件"  name="homework_file" class="form-control" />
				  </div>
			 </div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit"class="btn btn-success" >添加</button>
					<button type="reset" class="btn btn-primary" >重置</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>