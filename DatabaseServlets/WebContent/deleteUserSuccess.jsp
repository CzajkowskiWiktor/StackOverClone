<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User deleted</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section class="user_deleted">
		<h1 class="user_deleted-title">User has been successfully deleted!</h1>
		<div class="user_deleted-buttons">
			<a class="button" href="../DatabaseServlets/adminMain.jsp">main page</a>
			<a class="back" onclick="history.back()">Cancel</a>
		</div>
	</section>
</body>
</html>