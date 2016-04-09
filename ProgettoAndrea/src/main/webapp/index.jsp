<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="${pageContext.request.contextPath}/assets/application.min.css"
	rel="stylesheet" id="bootstrap" />
<script
	src="${pageContext.request.contextPath}/assets/application.min.js"></script>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>

	<c:url value="/informativa/accetta" var="messageUrl" />

	<span class="label label-default">FARE LA LOGIN</span>	


	<div>
		<form:form action="${messageUrl}" class="form-horizontal">

			<div class="form-group">
				<label for="username" class="control-label col-md-3">Username</label>
				<div class="col-md-5">
					<input type="text" class="form-control" id="username"
						name="username">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="control-label col-md-3">Password</label>
				<div class="col-md-5">
					<input type="password" class="form-control" id="password"
						name="password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-5">
					<button type="submit" class="btn btn-default">Sign in</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
