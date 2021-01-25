<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Question</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="add_question" align="center">
		<h1 class="add_question-title">Add New Question</h1>
		<form class="add_question__form" action="/../DatabaseServlets/AddQuestion" method="post">
			<div class="add_question__form-box">
				<label for="title">Question title:</label><br>
			  	<input type="text" id="title" name="title" placeholder="Enter a title"><br>
			</div>
			<div class="add_question__form-box">
				<label for="question_text">Question text:</label><br>
			  	<textarea id="question_text" name="question_text" placeholder="Enter your question text"></textarea><br>
			</div>
		  	<br>
		  	<div class="add_question__form-box--buttons">
		  		<button class="submit" type="submit">ADD</button>
	    		<button class="cancel" type="button" onclick="history.back()">Cancel</button>
		  	</div>
		</form>
	</div>
</body>
</html>