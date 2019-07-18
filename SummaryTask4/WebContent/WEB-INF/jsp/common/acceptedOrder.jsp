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
			<div class="col-lg-12">
				<br /> <br />
				<c:choose>
					<c:when test="${not empty success}">
						<div class="alert alert-success" role="alert">
							<strong>Success !</strong>
							<fmt:message key="${success }" />
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-success" role="alert">
							<strong>Success !</strong>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<foot:footer />
</body>
</html>