<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>

<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form action="controller" method="post">
				<c:choose>
					<c:when test="${not empty car}">
						<input type="hidden" name="command" value="CarUpdate">	
						<input type="hidden" name="car_id" value="${car.id}">	
					</c:when>
					<c:otherwise>
						<input type="hidden" name="command" value="CarCreate">
					</c:otherwise>
				</c:choose>
					<h4><strong><fmt:message key="car.brand"/></strong>:</h4>
					<input class="form-control" style="width: 40%"  type="text" name="brand" value="${car.brand}" required="required">
					
					<h4><strong><fmt:message key="car.model"/></strong>:</h4>
					<input class="form-control" style="width: 40%"  type="text" name="model" value="${car.model}" required="required">
					
					<h4><strong><fmt:message key="car.class"/></strong>:</h4>
					 <select style="width: 40%" class="form-control" name="class" required="required">
						<c:forEach var="c" items="${classes}">
							<c:choose>
							<c:when test="${not empty car and c.id == car.carClass.id}">
								<option value="${ c.id}" selected="selected">${ c.carClass}</option >
							</c:when>
							<c:otherwise>
								<option value="${ c.id}">${ c.carClass}</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<h4><strong><fmt:message key="car.vin"/></strong>:</h4>
					<input class="form-control" style="width: 40%"  type="text" name="vin" value="${car.VIN}" required="required">

					<h4><strong><fmt:message key="car.Number"/></strong>:</h4>
					<input class="form-control" style="width: 40%" type="text" name="text" value="${car.number}" required="required">

					<h4><strong><fmt:message key="car.price"/>(UAN)</strong>:</h4>
					<input class="form-control" style="width: 40%" min="0" max="100000" type="number" name="price" value="${car.price}" required="required">
					
					<input  style="width: 10%" type="submit" class="btn btn-success" value="OK">

				</form>
			</div>
		</div>
	</div>	
<foot:footer/>
</body>
</html>