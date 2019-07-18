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
			<div class="col-md-2">
				<form method="get" action="controller">
					<input type="hidden" name="command" value="FilterCarList">
					<div class="row">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button class="btn btn-outline-success " type="submit">Search</button>
							</div>
							<input  class="form-control mr-sm-2" name="searchValue"
								type="search" placeholder="Search" aria-label="Search">
						</div>
					</div>
					<div class="custom-control custom-switch mb-3">
						<input type="checkbox" name="brand" class="custom-control-input"
							id="customSwitch1"> <label class="custom-control-label"
							for="customSwitch1">Brand</label>
					</div>
					<div class="custom-control custom-switch mb-3">
						<input type="checkbox" name="model" class="custom-control-input"
							id="customSwitch2"> <label class="custom-control-label"
							for="customSwitch2">Model</label>
					</div>
					<label>Class</label>
					<select class="form-control mr-sm-2" name="class">
						<option selected="selected" value="null">--none--</option>
						<c:forEach var="cClass" items="${classes}">
							<option value="${cClass.id }">${cClass.carClass }</option>
						</c:forEach>
					</select>
					<div class="form-group">
   						 <label for="formControlRange">Price(0-100.000)</label>
   						 <input type="range" name="price" value="0" min="0" max="100000" class="form-control-range" list="tickmarks" id="formControlRange">
  					<datalist id="tickmarks">
  					<option value="0" label="0">
  					<option value="10000">
  					<option value="20000">
  					<option value="30000">
  					<option value="40000">
  					<option value="50000" label="50">
  					<option value="60000">
  					<option value="70000">
  					<option value="80000">
  					<option value="90000">
  					<option value="100000" label="100">
					</datalist>
  					</div>
				</form>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="SortCarList">
					<label>Sort by</label>
					<select class="form-control" onchange="this.form.submit()" name="sort">
						<option>-none-</option>
						<option value="price"><fmt:message key="car.price" /></option>
						<option value="model"><fmt:message key="car.model" /></option>
						<option value="brand"><fmt:message key="car.brand" /></option>
					</select>
				</form>
			</div>
			<div class="col-md-10">
			<c:choose>
				<c:when test="${not empty cars }">
					<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col"><fmt:message key="car.brand" /></th>
							<th scope="col"><fmt:message key="car.model" /></th>
							<th scope="col"><fmt:message key="car.class" /></th>
							<th scope="col"><fmt:message key="car.Number" /></th>
							<th scope="col"><fmt:message key="car.price" /></th>
							<th scope="col"><fmt:message key="car.action" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="car" items="${cars}">
							<tr>
								<th scope="row">${car.id }</th>
								<td>${car.brand }</td>
								<td>${car.model }</td>
								<td>${car.carClass.carClass }</td>
								<td>${car.number }</td>
								<td>${car.price }</td>
								<td>
									<form action="controller" method="get">
										<input type="hidden" name="command" value="ViewOrder">
										<input type="hidden" name="car_id" value="${car.id}">
										<input class="btn btn-info" type="submit"
											value="<fmt:message key="car.order" />">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise><h4><fmt:message key="error.empty"/></h4></c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<foot:footer/>
</body>
</html>