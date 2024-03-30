<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	function deleteHomework(tid){
		if(window.confirm("真的删除该作业吗？")){
			$.ajax(
				{
					//请求路径
					url : "homework/delete",
					//请求类型
					type : "post",
					//data表示发送的数据
					data : {
						id : tid
					},
					//成功响应的结果
					success : function(obj){//obj响应数据
						//获取路径
						var pathName=window.document.location.pathname;
						//截取，得到项目名称
						var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
						window.location.href = projectName + obj;
					},
			        error : function() {
			            alert("处理异常！");
			        }
				}	
			);
		}
	}
	</script>
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
				<h3 class="panel-title">作业列表</h3>
			</div>
			<div class="panel-body">
				<div class="table table-responsive">
					<table class="table table-bordered table-hover">
						<tbody class="text-center">
							<tr>
								<th>作业id</th>
								<th>姓名</th>
								<th>专业</th>
								<th>角色</th>
								<th>操作</th>
							</tr>
							<c:if test="${totalPage != 0 }">
								<c:forEach items="${allHomework}" var="homework">
									<tr>
										<td>${homework.id}</td>
										<td>${homework.name}</td>
										<td>${homework.major}</td>
										<td>${homework.post}</td>  
										<td>
											<a href="homework/detail?id=${homework.id}&act=detail" target="_blank">详情</a> &nbsp;&nbsp;&nbsp;&nbsp;
											<a href="homework/detail?id=${homework.id}&act=update">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="javascript:deleteHomework('${homework.id}')" >删除</a>
										</td>									
									</tr>
								</c:forEach>
									<tr>
										<td colspan="5" align="right">
											<ul class="pagination">
												<li><a>第<span>${currentPage}</span>页</a></li>
												<li><a>共<span>${totalPage}</span>页</a></li>
												<li>
													<c:if test="${currentPage != 1}">
														<a href="homework/selectAllHomeworkByPage?currentPage=${currentPage - 1}">上一页</a>
													</c:if>
													<c:if test="${currentPage != totalPage}">
														<a href="homework/selectAllHomeworkByPage?currentPage=${currentPage + 1}">下一页</a>
													</c:if>
												</li>
											</ul>
										</td>
									</tr>
								</c:if>
								<tr><td colspan="5" align="center"><a href="homework/toAddHomework">添加作业</a></td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>