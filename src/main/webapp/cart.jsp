<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>



</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<table class="table table-hover">
		<caption>购物车</caption>
		<thead>
			<tr>
				<th>商品</th>
				<th>价格</th>
				<th>数量</th>
				<th>小计</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${cart.cartItems}" var="entry">
			<tbody>
				<c:forEach items="${cart.cartItems}" var="entry">
					<tr>
						<td>${entry.value.product.product_name }</td>
						<td>${entry.value.product.product_price }</td>
						<td>${entry.value.buyNum}</td>
						<td>${entry.value.subtotal}</td>
						<td><a href="javascript:void(0);"
							onclick="delProFromCart('${entry.value.product.product_id}')"
							class="delete">删除</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</c:forEach>
	</table>
	
	<div>
		<a
			href="${pageContext.request.contextPath }/product?method=submitOrder">
			<input type="button" width="100" value="提交订单" name="submit">
		</a>
	</div>
</body>
</html>