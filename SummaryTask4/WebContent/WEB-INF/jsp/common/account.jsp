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
			<h1><fmt:message key="account.account_page" /></h1>
					<h4><fmt:message key="account.user.name" />: ${user.login}</h4>
					<h4><fmt:message key="account.user.email" />: ${user.email}</h4>
					<h4><fmt:message key="account.user.role" />: ${userRole.name}</h4>
					<h4><fmt:message key="account.user.locale" />: ${user.locale}</h4>
				<form action="controller" method="post">
				<input type="hidden" name="command" value = "viewSettings">
				<input type="submit" class="btn btn-success" value="<fmt:message key="account.user.settings"/>">
				</form>
				
				<c:if test="${not empty fine}">
					<h4><fmt:message key="account.user.fine"/> ${fine.fine} (UAN)</h4>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="FinePayment">
						<input style="width: 40%" type="number" class="form-control form-control-sm" name="payment" min=0 max ="${fine.fine}">
						<br/>
						<input type="submit" class="btn btn-success" value="<fmt:message key="account.user.fine.accept"/>">
					</form>
				</c:if>
				<c:if test="${userRole eq 'MANAGER'}">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="CallFines">
						<button class="btn btn-warning"><fmt:message key="account.fine.call"/></button>
					</form>
				</c:if>
				<c:if test="${userRole eq 'ADMIN'}">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="OrdersReport">
						<label><fmt:message key="order.date.start"/></label>
						<input class = "form-control" style="width: 30%" type="date" name = "start">
						<label><fmt:message key="order.date.end"/></label>
						<input class = "form-control" style="width: 30%" type="date" name = "end">
						<button class="btn btn-warning"><fmt:message key="account.create.report"/></button>
					</form>				
				</c:if>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>