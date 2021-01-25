<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Answer</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="delete_answer" align="center">
		<h1 class="delete_answer-title">Delete Answer From StackOverClone</h1>
		<form class="delete_answer__form" action="/../DatabaseServlets/deleteAnswer" method="post">
			<div class="delete_answer__form-box">
			  	<input type="hidden" id="id" name="id" value="${param.id}"><br>
			</div>
		  	<br>
		  	<div class="delete_answer__form-box--buttons">
		  		<button class="submit" type="submit">Delete</button>
	    		<button class="cancel" type="button" onclick="history.back()">Cancel</button>
		  	</div>
		</form>
	</div>
</body>
</html>