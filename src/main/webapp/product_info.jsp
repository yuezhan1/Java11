<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>



<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<!-- 商品详细信息 -->


				<h4>
					<a href="javascript:;">${product.product_name }</a>
				</h4>
				<span> <span> <span>￥</span>${product.product_price }
				</span>
				</span>
				<hr>
				<ul>
					<li><label>库存:</label> <span> 1000+</span></li>
				</ul>
				<hr>
				<p>${product.product_description }</p>
				<div>

					<div class="form-group">
						<label>购买数量</label> <input id="quantity" name="buyNum" min="1"
							value="1" type="number">
					</div>
					<div class="button-group">
						<div>
							<a href="javascript:;" onclick="addCart()"><span>添加到购物车</span></a>
						</div>
						<c:if test="${!empty currentPage }">
							<div>
								<a
									href="${pageContext.request.contextPath }/product?method=productList&cid=${cid}&currentPage=${currentPage}"><span>返回商品列表</span></a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>