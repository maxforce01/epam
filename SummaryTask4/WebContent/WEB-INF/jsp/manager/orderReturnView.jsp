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
					<input type="hidden" name="command" value="OrderReturn">
					<input type="hidden" name="order_id" value="${order.id}">
					<h4><strong><fmt:message key="account.user.name"/></strong>:</h4>
					<input class="form-control" style="width: 40%" type="text" value="${orderUser.login}" disabled="disabled">
					<h4><strong><fmt:message key="order.user.session"/></strong>:</h4>
					<input class="form-control" style="width: 40%" type="text" value="${order.session }" disabled="disabled">
					<h4><strong><fmt:message key="order.user.number"/></strong>:</h4>
					<input class="form-control" style="width: 40%" type="text" value="${order.number}" disabled="disabled">
					<h4><strong><fmt:message key="car.vin"/></strong>:</h4>
					<input class="form-control" style="width: 40%"  type="text" value="${car.VIN}" disabled="disabled">
					<h4><strong><fmt:message key="car.Number"/></strong>:</h4>
					<input class="form-control" style="width: 40%" type="text" value="${car.number}" disabled="disabled">
					<h4><strong><fmt:message key="order.damage"/></strong>:</h4>
					<input  class="form-control" style="width: 40%" type="number" min="0" max="100" name="damage" required="required">
					<input  type="submit" class="btn btn-success" value="OK">
				</form>
			</div>
		</div>
	</div>	
<foot:footer/>
</body>
</html>