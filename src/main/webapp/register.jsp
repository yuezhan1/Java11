<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>


</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<div>
		<form action="${pageContext.request.contextPath}/user?method=register" method="post" id="myform">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">用户名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="username" id="username"
						placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="password" id="password"
						placeholder="请输入密码">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">确认密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="repassword" id="repassword"
						placeholder="请输入确认密码">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">电话</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="telphone"
						placeholder="请输入电话">
				</div>
			</div>

			<div>
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					男<input type="radio" name="sex" value="男" id="sex1"> 女<input
						type="radio" name="sex" value="女" id="sex2">
				</div>
			</div>

			<div>
				<div class="col-sm-10">
					<button type="submit" class="btn btn-default">注册</button>
				</div>
			</div>



		</form>
	</div>
</body>
</html>