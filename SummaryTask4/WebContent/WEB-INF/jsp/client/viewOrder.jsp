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
			<div class="col-lg-9">

				<form action="controller" method="post">
				<input type="hidden" name="command" value="Payment">
				<input type="hidden" name="car_id" value="${car.id}">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="vin"><fmt:message key="car.vin" /></label> <input
								type="text" class="form-control" id="vin" disabled="disabled"
								value="${car.VIN }" required="required">
						</div>
						<div class="form-group col-md-6">
							<label for="number"><fmt:message key="car.Number" /></label> <input
								type="text" class="form-control" id="number" disabled="disabled"
								value="${car.number }" required="required">
						</div>
					</div>
					<div class="form-group">
						<label for="session"><fmt:message key="order.user.session" /></label>
						<input type="text" class="form-control" name="pass_session" id="session"
							placeholder="<fmt:message  key="order.user.session"/>" required="required">
					</div>
					<div class="form-group">
						<label for="num"><fmt:message key="order.user.number" /></label> <input
							type="text" class="form-control" name="pass_number" id="num"
							placeholder="<fmt:message  key="order.user.number"/>" required="required">
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="date"><fmt:message key="order.date" /></label> <input
								type="date" class="form-control" name="date_end" id="date" required="required">
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<label class="form-check-label"> <input
								class="form-check-input" name="driver" type="checkbox"> <fmt:message
									key="order.driver" />
							</label>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="order.accept" />
					</button>
				</form>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>