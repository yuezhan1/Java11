<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>头部显示</title>
</head>

<body>


		<c:if test="${empty loginUser }">
			<div >
				<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
			</div>
		
		</c:if>
		<c:if test="${not empty loginUser }">
			<div >
				<a  href="${pageContext.request.contextPath }/exit.jsp"><span>${loginUser.user_name }</span></a>
			</div>
		</c:if>
		<div>
			<a  href="${pageContext.request.contextPath }/register.jsp">注册</a>
		</div>
		
		<div>
			<ul >
				<li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
				<li><a href="${pageContext.request.contextPath }/product?method=myOrder">我的订单</a></li>				
				<li><a href="${pageContext.request.contextPath }/index.jsp">回到首页</a></li>
			</ul>
		</div>
		




</body>
</html>