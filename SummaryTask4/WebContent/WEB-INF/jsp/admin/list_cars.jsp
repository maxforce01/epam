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
			<form action="controller" method="get">
				<input type="hidden" name="command" value="CarCreateView">
				<button class="btn btn-warning"><i class="fas fa-plus"></i><fmt:message key="car.create" /></button>
			</form>
			
			<c:choose>
				<c:when test="${not empty cars }">
					<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col"><fmt:message key="car.brand" /></th>
							<th scope="col"><fmt:message key="car.model" /></th>
							<th scope="col"><fmt:message key="car.class" /></th>
							<th scope="col"><fmt:message key="car.Number" /></th>
							<th scope="col"><fmt:message key="car.price" /></th>
							<th scope="col"><fmt:message key="car.action" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="car" items="${cars}">
							<tr>
								<th scope="row">${car.id }</th>
								<td>${car.brand }</td>
								<td>${car.model }</td>
								<td>${car.carClass.carClass }</td>
								<td>${car.number }</td>
								<td>${car.price }</td>
								<td>
								<div class="row">
									<form action="controller" method="get">
										<input type="hidden" name="command" value="CarUpdateView" >
										<input type="hidden" name="car_id" value="${car.id}">
										<button class="btn btn-info"><i class="fas fa-edit"></i></button>
									</form>
									<form action="controller" method="post">
										<input type="hidden" name="command" value="CarDelete" >
										<input type="hidden" name="car_id" value="${car.id}">
										<button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
									</form>
								</div>
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