<%@ page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border: solid 1px blue;
}

th {
	border: solid 1px blue;
}
</style>
</head>
<body>
	<table>
		<c:forEach var="i" begin="0" end="9">
			<tr>
				<c:forEach var="j" begin="0" end="9">
					<c:choose>
						<c:when test="${i==0 && j==0}">
							<th></th>
						</c:when>
						<c:when test="${i==0}">
							<th>${j}</th>
						</c:when>
						<c:when test="${j==0}">
							<th>${i}</th>
						</c:when>
						<c:otherwise>
							<th>${i*j}</th>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>