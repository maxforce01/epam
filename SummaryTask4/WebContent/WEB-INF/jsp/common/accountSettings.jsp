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
			<div class="col-lg-10">
				<form method="post" action="controller">
				<input type="hidden" name="command" value="updateSettings">
					<h1>
						<fmt:message key="account.account_page" />
					</h1>
					<h4>
						<fmt:message key="account.user.name" />
					</h4>
					<input class="form-control" type="text" name="login"
						value="${user.login}" />
					<h4>
						<fmt:message key="account.user.email" />
					</h4>
					<input class="form-control" type="email" name="email"
						value="${user.email}" />
					<h4>
						<fmt:message key="account.user.locale" />
					</h4>
					<select class="form-control" name="locale">
						<c:forEach var="localeName" items="${locales}">
							<option value="${localeName}">${localeName}</option>
						</c:forEach>
					</select>
					<input class="btn btn-success" type="submit" value="Accept">
				</form>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>