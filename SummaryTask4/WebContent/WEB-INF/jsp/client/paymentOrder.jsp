<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<br />
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-9">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="CreateOrder"> 
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="payment"><fmt:message key="order.payment" /></label> <input
								type="number" min="${order.payment / 2}" max="${order.payment}"
								class="form-control" name="payment" id="payment" value="${order.payment}"
								required="required">
								<input type="submit" class="btn btn-success" value="<fmt:message key="order.accept" />">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>