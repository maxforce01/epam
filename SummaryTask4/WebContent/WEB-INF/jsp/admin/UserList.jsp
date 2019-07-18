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
			<c:when test="${not empty users }">
						<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col"><fmt:message key="account.user.name"/></th>
							<th scope="col"><fmt:message key="account.user.email" /></th>
							<th scope="col"><fmt:message key="account.user.role" /></th>
							<th scope="col"><fmt:message key="account.user.status" /></th>
							<th scope="col"><fmt:message key="car.action" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<th scope="row">${user.id }</th>
								<td>${user.login }</td>
								<td>${user.email }</td>
								<td>
									<c:choose>
										<c:when test="${user.roleId eq '1'}">ADMIN</c:when>
										<c:when test="${user.roleId eq '2'}">MANAGER</c:when>
										<c:when test="${user.roleId eq '3'}">CLIENT</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${user.status}">Banned</c:when>
										<c:otherwise>Unbanned</c:otherwise>
									</c:choose>		
								</td>						
								<td>
								<div class="row">
									<c:choose>
										<c:when test="${user.status eq '0' }">
											<form action="controller" method="post">
												<input type="hidden" name="command" value="BanCommand" >
												<input type="hidden" name="user_id" value="${user.id}">
												<button class="btn btn-danger"><i class="fas fa-lock"></i></button>
											</form>
										</c:when>
										<c:otherwise>
											<form action="controller" method="post">
												<input type="hidden" name="command" value="UnBanCommand" >
												<input type="hidden" name="user_id" value="${user.id}">
												<button class="btn btn-info"><i class="fas fa-unlock-alt"></i></button>
											</form>
										</c:otherwise>
									</c:choose>	
										<c:if test="${user.roleId ne '2' and user.roleId ne '1'}">
											<form action="controller" method="post">
												<input type="hidden" name="command" value="UpdateManagerCommand" >
												<input type="hidden" name="user_id" value="${user.id}">
												<button class="btn btn-info"><i class="fas fa-universal-access"></i></button>
											</form>
										</c:if>
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