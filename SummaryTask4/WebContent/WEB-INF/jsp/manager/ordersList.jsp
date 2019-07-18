<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<br />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
			
			<c:choose>
				<c:when test="${not empty orders }">
				<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col"><fmt:message key="order.number" /></th>
							<th scope="col"><fmt:message key="order.date" /></th>
							<th scope="col"><fmt:message key="order.money" /></th>
							<th scope="col"><fmt:message key="order.status" /></th>
							<th scope="col"><fmt:message key="order.info" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<th scope="row">${order.id }</th>
								<td>${order.dateEnd }</td>
								<td>${order.payment }(UAN)</td>
								<td>${order.status }</td>
								<td>
									<form action="controller" method="get">
										<input type="hidden" name="command" value="OrderInfo" >
										<input type="hidden" name="order_id" value="${order.id}">
										<input class="btn btn-info" type="submit" value="<fmt:message key="order.info" />">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		
				</c:when>
				
				<c:otherwise>
					<h4><fmt:message key="error.empty"/></h4>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>