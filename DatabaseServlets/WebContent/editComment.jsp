<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit the Comment</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="add_question" align="center">
		<h1 class="add_question-title">Edit Comment</h1>
		<form class="add_question__form" action="/../DatabaseServlets/editComment" method="post">
			<div class="add_question__form-box">
				<label for="question_text">New Comment text:</label><br>
				<input id="questionID" name="questionID" type="hidden" value="${param.id}">
			  	<textarea id="question_text" name="question_text" placeholder="Enter your new comment text"></textarea><br>
			</div>
		  	<br>
		  	<div class="add_question__form-box--buttons">
		  		<button class="submit" type="submit">Change</button>
	    		<button class="cancel" type="button" onclick="history.back()">Cancel</button>
		  	</div>
		</form>
	</div>
</body>
</html>