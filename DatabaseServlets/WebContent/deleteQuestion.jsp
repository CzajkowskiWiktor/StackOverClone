<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Question</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="delete_question" align="center">
		<h1 class="delete_question-title">Delete Question From StackOverClone</h1>
		<form class="delete_question__form" action="/../DatabaseServlets/deleteQuestion" method="post">
			<div class="delete_question__form-box">
				<label for="id">Question ID:</label><br>
			  	<input type="number" id="id" name="id" placeholder="Enter a question's ID"><br>
			</div>
		  	<br>
		  	<div class="delete_question__form-box--buttons">
		  		<button class="submit" type="submit">Delete</button>
	    		<button class="cancel" type="button" onclick="history.back()">Cancel</button>
		  	</div>
		</form>
	</div>
</body>
</html>