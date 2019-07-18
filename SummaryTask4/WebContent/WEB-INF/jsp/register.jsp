<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style type="text/css">
	<%@include file="../style/vendor/bootstrap/css/bootstrap.min.css" %>
	<%@include file="../style/fonts/font-awesome-4.7.0/css/font-awesome.min.css" %>
	<%@include file="../style/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" %>
	<%@include file="../style/vendor/css-hamburgers/hamburgers.min.css" %>
	<%@include file="../style/vendor/animate/animate.css" %>
	<%@include file="../style/vendor/animsition/css/animsition.min.css" %>
	<%@include file="../style/vendor/select2/select2.min.css" %>
	<%@include file="../style/vendor/daterangepicker/daterangepicker.css" %>
 	<%@include file="../style/css/main.css" %>
	<%@include file="../style/css/util.css" %>
</style>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form method="post"  action="controller?command=register" class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-51"> Registration </span>


					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Username is required">
						<input class="input100" type="text" name="login"
							placeholder="Login"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Username is required">
						<input class="input100" type="email" name="email"
							placeholder="Email"> <span class="focus-input100"></span>
					</div>


					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Password is required">
						<input class="input100" type="password" name="password"
							placeholder="Password"> <span class="focus-input100"></span>
					</div>


					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Password is required">
						<input class="input100" type="password" name="password-confirm"
							placeholder="Password confirmation"> <span
							class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn">Register</button>
					</div>
					<a href="controller?command=loginPage">You have already have an account?</a>
				</form>
			</div>
		</div>
	</div>

</body>
</html>