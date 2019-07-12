<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>默认页面</title>
</head>
<body>
	<% response.sendRedirect(request.getContextPath()+"/product?method=index"); %>
</body>
</html>