<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>

</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	

		<c:forEach items="${pageBean.list }" var="pro">

				<a href="${pageContext.request.contextPath }/product?method=productInfo&product_id=${pro.product_id}&cid=${cid}&currentPage=${pageBean.currentPage}">
					<img src="${pro.product_image }" alt="图片找到不啦">
				</a>
				<a class="caption" align="center" href="${pageContext.request.contextPath }/product?method=productInfo&product_id=${pro.product_id}&cid=${cid}&currentPage=${pageBean.currentPage}">
					<h3>${pro.product_name }</h3>
					<p>售价:${pro.product_price }</p>
				</a>
					</c:forEach>

	

	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-bottom: 1px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">

			<!-- 上一页 -->
			<c:if test="${pageBean.currentPage==1 }">
				<li class="disabled"><a href="javascript:void(0);"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:if test="${pageBean.currentPage!=1 }">
				<li><a
					href="${pageContext.request.contextPath}/product?method=productList&cid=${cid}&currentPage=${pageBean.currentPage-1 }"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>


			<!-- 显示每一页 -->
			<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
				<!-- 判断是否是当前页 -->
				<c:if test="${page==pageBean.currentPage }">
					<li class="active"><a href="javascript:void(0);">${page }</a></li>
				</c:if>
				<c:if test="${page!=pageBean.currentPage }">
					<li><a
						href="${pageContext.request.contextPath}/product?method=productList&cid=${cid}&currentPage=${page }">${page }</a></li>
				</c:if>
			</c:forEach>


			<!-- 下一页 -->
			<c:if test="${pageBean.currentPage==pageBean.totalPage }">
				<li class="disabled"><a href="javascript:void(0);"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
			<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
				<li><a
					href="${pageContext.request.contextPath}/product?method=productList&cid=${cid}&currentPage=${pageBean.currentPage+1 }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>

		</ul>
	</div>
	<!-- 分页结束 -->
</body>
</html>