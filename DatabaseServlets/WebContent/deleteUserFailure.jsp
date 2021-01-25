<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete User Failure</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<section class="user_deleted">
		<h1 class="user_deleted-title"><span class="user-deleted-failure">Failure!</span></h1>
		<h1 class="user_deleted-title">User has not been deleted!</h1>
		<div class="user_deleted-buttons">
			<a class="button" href="../DatabaseServlets/adminMain.jsp">main page</a>
			<a class="back" onclick="history.back()">Go Back</a>
		</div>
	</section>

</body>
</html>