<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">


</head>

<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${order.oid}</th>
						</tr>
						<tr class="warning">
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${cart.cartItems}" var="entry">
							<tbody>
								<c:forEach items="${cart.cartItems}" var="entry">
									<tr>
										<td>${entry.value.product.product_name }</td>
										<td>${entry.value.product.product_price }</td>
										<td>${entry.value.buyNum}</td>
										<td>${entry.value.subtotal}</td>
								</c:forEach>

							</tbody>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${order.total }元</strong>
			</div>

		</div>

		<div>
			<hr />
			<form id="orderForm" class="form-horizontal"
				style="margin-top: 5px; margin-left: 150px;"
				action="${pageContext.request.contextPath }/product" method="post">

				<!-- method的名字是通过比导弹提交 -->
				<input type="hidden" name="method" value="confirmOrder">
				<!-- 传递订单id -->
				<input type="hidden" name="oid" value="${order.oid  }">
				<div class="form-group">
					<label for="username" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="address"
							name="addr" value=" ">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-1 control-label"
						name="name">收货人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="inputPassword3"
							name="name" value="${user.name }">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-1 control-label"
						name="telephone">电话</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="confirmpwd"
							name="tel" value="${user.telephone }">
					</div>
				</div>

				<hr />
				<!-- 点击提交订单 -->
				<p style="text-align: right; margin-right: 100px;">
					<a href="javascript:;" onclick="confirmOrder()"> 
					<input type="button" value="提交订单"> 
					</a>
				</p>
				<hr />
		</div>
		</form>
	</div>

	</div>
</body>

</html>