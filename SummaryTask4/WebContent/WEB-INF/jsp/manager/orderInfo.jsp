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
				<h4><strong><fmt:message key="account.user.name"/></strong>:${orderUser.login}</h4>
				<h4><strong><fmt:message key="order.user.session"/></strong>:${order.session }</h4>
				<h4><strong><fmt:message key="order.user.number"/></strong>:${order.number}</h4>
				<h4><strong><fmt:message key="car.vin"/></strong>:${car.VIN}</h4>
				<h4><strong><fmt:message key="car.Number"/></strong>:${car.number }</h4>
				<h4><strong><fmt:message key="order.date.start"/></strong>:${order.dateStart }</h4>
				<h4><strong><fmt:message key="order.date.end"/></strong>:${order.dateEnd }</h4>
				<h4><strong><fmt:message key="order.money"/></strong>:${order.payment }(UAN)</h4>
				<c:if test="${order.status == 'DENIED'}"><h4><strong><fmt:message key="order.cause"/></strong>:${order.cause}</h4></c:if>
				<c:choose>
					<c:when test="${order.status == 'UNCHECKED'}">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="OrderAccept">
							<input type="hidden" name="order_id" value="${order.id}">
							<select id="inp" style="width: 40%" onchange="fun()" class="form-control" name="status" required="required">
								<option value="ACCEPTED"><fmt:message key="order.accept"/></option>
								<option value="DENIED"><fmt:message key="order.refuse"/></option>
							</select>
							<h4 id="lbl" style="visibility: hidden;" ><strong><fmt:message key="order.cause"/></strong>:</h4>
							<input id = "dam" class="form-control" style="width: 40%; visibility: hidden;" type="text" name="cause">
							<input class="btn btn-success" type="submit" value="OK">
						</form>
					</c:when>
					<c:when test="${order.status == 'ACCEPTED'}">
						<form action="controller" method="get">
						<input type="hidden" name="command" value="OrderReturnView">
						<input type="hidden" name="order_id" value="${order.id}">
						<input type="submit" class="btn btn-success" value="<fmt:message key="order.return"/>">
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function fun() {
		var inp = document.getElementById("inp");
		var lbl = document.getElementById("lbl");
		var dam = document.getElementById("dam");
		if(inp.value == 'ACCEPTED'){
			dam.removeAttribute('required');
			dam.style.visibility = "hidden";
			lbl.style.visibility = "hidden";
			}else{
				dam.setAttribute('required','required');
				dam.style.visibility = "visible";
				lbl.style.visibility = "visible";
		}
	}
</script>
<foot:footer/>
</body>
</html>